package com.reynaldiwijaya.smartrt.ui.Register;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.reynaldiwijaya.smartrt.Helper.Constant;
import com.reynaldiwijaya.smartrt.R;

import net.gotev.uploadservice.MultipartUploadRequest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.btn_selected_photo)
    Button btnSelectedPhoto;
    @BindView(R.id.img_user)
    CircleImageView imgUser;
    @BindView(R.id.edt_namaLengkap)
    TextInputEditText edtNamaLengkap;
    @BindView(R.id.no_KTP)
    EditText noKTP;
    @BindView(R.id.alamat)
    EditText alamat;
    @BindView(R.id.status)
    EditText status;
    @BindView(R.id.rb_laki2)
    RadioButton rbLaki2;
    @BindView(R.id.rb_perempuan)
    RadioButton rbPerempuan;
    @BindView(R.id.rg_jenisKelamin)
    RadioGroup rgJenisKelamin;
    @BindView(R.id.profesi)
    EditText profesi;
    @BindView(R.id.edt_noTelp)
    EditText edtNoTelp;
    @BindView(R.id.edt_email)
    EditText edtEmail;
    @BindView(R.id.edt_username)
    EditText edtUsername;
    @BindView(R.id.edt_password)
    EditText edtPassword;
    @BindView(R.id.btn_send)
    Button btnSend;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.ib_date)
    ImageButton ibDate;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.scrollView)
    ScrollView scrollView;

    private Uri filepath;
    private String mediaPath;
    private static final String TAG = "RegisterActivity";
    private String nama_lengkap, edtAlamat, edtStatus, tglLahir, jenkel, edtProfesi, email, username, password, foto, level;
    private String no_ktp, no_tlp, konfirmasi;
    private Bitmap mPhoto;
    private ProgressDialog progressDialog;
    private DatePickerDialog.OnDateSetListener mDateSetDialog;

    private String blockCharacterSet = "~#^|$%&*! ";
    private InputFilter filter = new InputFilter() {
        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

            if (source != null && blockCharacterSet.contains("" + source)) {
                return "";
            } else {
                Toasty.error(RegisterActivity.this, "Tidak boleh ada penggunaan ~#^|$%&*! dan spasi", Toasty.LENGTH_SHORT).show();
            }

            return null;
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        edtEmail.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);

        btnSend.setOnClickListener(this);
        rbLaki2.setOnClickListener(this);
        rbPerempuan.setOnClickListener(this);

        mDateSetDialog = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = year + "-" + month + "-" + dayOfMonth;
                tvDate.setText(date);
            }
        };

    }

    @OnClick({R.id.btn_send, R.id.rb_laki2, R.id.rb_perempuan, R.id.tv_login, R.id.ib_date})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(getString(R.string.isi_keterangan));
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d(TAG, "Button Send was Clicked");
                        getData();
                        checkInput();

                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                break;

            case R.id.rb_laki2:
                jenkel = rbLaki2.getText().toString();
                break;

            case R.id.rb_perempuan:
                jenkel = rbPerempuan.getText().toString();
                break;
            case R.id.tv_login:
                finish();
                break;

            case R.id.ib_date:
                getDate();
                break;

        }
    }

    private void getDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(
                this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                mDateSetDialog,
                year, month, day);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    private void getData() {
        nama_lengkap = edtNamaLengkap.getText().toString();
        edtAlamat = alamat.getText().toString();
        edtStatus = status.getText().toString();
        tglLahir = tvDate.getText().toString();
        edtProfesi = profesi.getText().toString();
        email = edtEmail.getText().toString();
        username = edtUsername.getText().toString().toLowerCase();
        password = edtPassword.getText().toString().toLowerCase();
        level = "Warga";
        no_tlp = edtNoTelp.getText().toString();
        no_ktp = noKTP.getText().toString();
        konfirmasi = "0";

        if (edtUsername.getText().toString().contains(" ")) {
            edtUsername.setError("Tidak menggunakan spasi ada spasi");
            edtUsername.requestFocus();
        }
    }

    private void checkInput() {
        if (TextUtils.isEmpty(nama_lengkap)) {
            edtNamaLengkap.setError(getString(R.string.error_message));
            edtNamaLengkap.requestFocus();
        } else if (TextUtils.isEmpty(edtAlamat)) {
            alamat.setError(getString(R.string.error_message));
            alamat.requestFocus();
        } else if (TextUtils.isEmpty(edtStatus)) {
            status.setError(getString(R.string.error_message));
            status.requestFocus();
        } else if (TextUtils.isEmpty(tglLahir)) {
            tvDate.setError(getString(R.string.error_message));
            tvDate.requestFocus();
        } else if (TextUtils.isEmpty(edtProfesi)) {
            profesi.setError(getString(R.string.error_message));
            profesi.requestFocus();
        } else if (TextUtils.isEmpty(email)) {
            edtEmail.setError(getString(R.string.error_message));
            edtEmail.requestFocus();
        } else if (TextUtils.isEmpty(username)) {
            edtUsername.setError(getString(R.string.error_message));
            edtUsername.requestFocus();
        } else if (TextUtils.isEmpty(password)) {
            edtPassword.setError(getString(R.string.error_message));
            edtPassword.requestFocus();
        } else if (imgUser.getDrawable() == null) {
            Toast.makeText(this, "No Images", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(no_tlp)) {
            edtNoTelp.setError(getString(R.string.error_message));
            edtNoTelp.requestFocus();
        } else if (TextUtils.isEmpty(no_ktp)) {
            noKTP.setError(getString(R.string.error_message));
            noKTP.requestFocus();
        } else if (TextUtils.isEmpty(jenkel)) {
            Toast.makeText(this, "Silahkan pilih jenis kelamin", Toast.LENGTH_SHORT).show();
        } else {
            uploadData();
            finish();
        }
    }

    private void uploadData() {
        showProgress();
        try {
            mediaPath = getPath(filepath);
            Toasty.success(this, "Succes to Send", Toasty.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.d("Error Message", "Msg; " + e.getMessage());
            Toasty.error(this, "Images Terlalu Besar, Silahkan pilih images yang lebih kecil", Toasty.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        try {
            new MultipartUploadRequest(this, Constant.UPLOAD_REGISTER_URL)
                    .addFileToUpload(mediaPath, "foto")
                    .addParameter("nama_lengkap", nama_lengkap)
                    .addParameter("no_ktp", no_ktp)
                    .addParameter("alamat", edtAlamat)
                    .addParameter("status", edtStatus)
                    .addParameter("tgl_lahir", tglLahir)
                    .addParameter("jenkel", jenkel)
                    .addParameter("profesi", edtProfesi)
                    .addParameter("no_tlp", no_tlp)
                    .addParameter("email", email)
                    .addParameter("username", username)
                    .addParameter("password", password)
                    .addParameter("konfirmasi", konfirmasi)
                    .addParameter("level", level)
                    .setMaxRetries(2)
                    .startUpload();
            hideProgressDialog();

        } catch (MalformedURLException e) {
            e.printStackTrace();
            hideProgressDialog();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            hideProgressDialog();
        }

    }

    private void hideProgressDialog() {
        progressDialog.dismiss();
    }

    private void showProgress() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading ...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }


    @OnClick(R.id.btn_selected_photo)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_selected_photo:
                showFileChooser(Constant.REQ_CHOOSE_FILE_REGISTER);
                break;
        }
    }

    private void showFileChooser(int requestCode) {
        Intent toGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(toGallery, requestCode);
        Log.i("Gallery", "Masuk Galley");

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == Constant.REQ_CHOOSE_FILE_REGISTER) {
                if (data != null) {
                    filepath = data.getData();
//                    Uri selectedImage = data.getData();
                }

                try {
                    mPhoto = MediaStore.Images.Media.getBitmap(getContentResolver(), filepath);
                    imgUser.setImageBitmap(mPhoto);
                    btnSelectedPhoto.setAlpha(0f);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private String getPath(Uri filepath) {
        Cursor cursor = getContentResolver().query(filepath, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();

        cursor = getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, MediaStore.Images
                        .Media._ID + " = ? ", new String[]{document_id}, null);

        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();

        return path;
    }
}
