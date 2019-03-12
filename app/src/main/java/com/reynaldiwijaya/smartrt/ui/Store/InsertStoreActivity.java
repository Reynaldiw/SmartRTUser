package com.reynaldiwijaya.smartrt.ui.Store;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.reynaldiwijaya.smartrt.Helper.Constant;
import com.reynaldiwijaya.smartrt.Helper.SessionManager;
import com.reynaldiwijaya.smartrt.R;
import com.reynaldiwijaya.smartrt.ui.Store.Presenter.InsertStoreContract;
import com.reynaldiwijaya.smartrt.ui.Store.Presenter.InsertStorePresenter;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class InsertStoreActivity extends AppCompatActivity implements InsertStoreContract.View {


    @BindView(R.id.edt_deskripsi)
    TextInputEditText edtDeskripsi;
    @BindView(R.id.btn_send)
    Button btnSend;
    @BindView(R.id.edt_alamat)
    TextInputEditText edtAlamat;
    @BindView(R.id.img_foto_store)
    ImageView imgFotoStore;
    @BindView(R.id.btn_selected_photo)
    Button btnSelectedPhoto;
    @BindView(R.id.edt_nama_toko)
    TextInputEditText edtNamaToko;
    @BindView(R.id.edt_noTlp)
    TextInputEditText edtNoTlp;
    @BindView(R.id.btn_cancel)
    Button btnCancel;

    private ProgressDialog progressDialog;
    private InsertStorePresenter insertStorePresenter = new InsertStorePresenter(this, this);
    private SessionManager sm;
    private Uri filepath;
    private String mediapath;
    private String id_user, nama_toko, alamat, deskripsi, no_tlp, konfirmasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_store);
        ButterKnife.bind(this);

        sm = new SessionManager(this);

    }

    @OnClick(R.id.btn_send)
    public void onViewClicked() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Mendaftarkan toko anda disini, membutuhkan konfirmasi dari Ketua RT." +
                " Apakah anda ingin mengirimkan konfirmasi ke ketua RT ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                getData();
                checkData();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void checkData() {

        boolean isValid = true;

        if (TextUtils.isEmpty(id_user)) {
            Toasty.error(this, "Id User null", Toasty.LENGTH_SHORT).show();
            isValid = false;
        }
        if (TextUtils.isEmpty(nama_toko)) {
            edtNamaToko.setError(getString(R.string.error_message));
            edtNamaToko.requestFocus();
            isValid = false;
        }
        if (TextUtils.isEmpty(deskripsi)) {
            edtDeskripsi.setError(getString(R.string.error_message));
            edtDeskripsi.requestFocus();
            isValid = false;
        }
        if (TextUtils.isEmpty(alamat)) {
            edtAlamat.setError(getString(R.string.error_message));
            edtAlamat.requestFocus();
            isValid = false;
        }
        if (TextUtils.isEmpty(no_tlp)) {
            edtNoTlp.setError(getString(R.string.error_message));
            edtNoTlp.requestFocus();
            isValid = false;
        }
        if (isValid) {

            mediapath = getPath(filepath);
            File imageFile = new File(mediapath);

            RequestBody image = RequestBody.create(MediaType.parse("multipart/form-data"), imageFile);
            MultipartBody.Part partImage = MultipartBody.Part.createFormData("foto", imageFile.getName(), image);

            RequestBody id = RequestBody.create(MediaType.parse("multipart/form-data"), id_user);
            RequestBody toko = RequestBody.create(MediaType.parse("multipart/form-data"), nama_toko);
            RequestBody alamatToko = RequestBody.create(MediaType.parse("multipart/form-data"), alamat);
            RequestBody desc = RequestBody.create(MediaType.parse("multipart/form-data"), deskripsi);
            RequestBody tlp = RequestBody.create(MediaType.parse("multipart/form-data"), no_tlp);
            RequestBody konfirmasiToko = RequestBody.create(MediaType.parse("multipart/form-data"), konfirmasi);

            insertStorePresenter.getDataStore(id, toko, alamatToko, desc, tlp, partImage, konfirmasiToko);
        }
    }

    private void getData() {
        id_user = sm.getIdUser();
        nama_toko = edtNamaToko.getText().toString();
        deskripsi = edtDeskripsi.getText().toString();
        alamat = edtAlamat.getText().toString();
        no_tlp = edtNoTlp.getText().toString();

        if (sm.getKonfirmasi().equals("2")) {
            konfirmasi = "1";
        } else {
            konfirmasi = "0";
        }
    }

    @Override
    public void showProgress() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading ...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void showSuccesMessage(String msg) {
        Toasty.success(this, msg, Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void showFailureMessage(String msg) {
        Toasty.error(this, msg, Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.btn_selected_photo, R.id.btn_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_selected_photo:
                showFileChooser(Constant.REQ_CHOOSE_FILE_NEWS);
                break;
            case R.id.btn_cancel:
                finish();
                break;
        }
    }

    private void showFileChooser(int reqChooseFileNews) {
        Intent toGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(toGallery, reqChooseFileNews);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode  == Constant.REQ_CHOOSE_FILE_NEWS) {
            if (resultCode == RESULT_OK) {
                if (data.getData() != null) {
                    filepath = data.getData();
                }
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filepath);
                    imgFotoStore.setImageBitmap(bitmap);
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

        cursor = getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, MediaStore.Images
                        .Media._ID + " = ? ", new String[]{document_id}, null);


        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();

        return path;

    }
}
