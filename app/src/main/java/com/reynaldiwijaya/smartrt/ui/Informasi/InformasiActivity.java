package com.reynaldiwijaya.smartrt.ui.Informasi;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.reynaldiwijaya.smartrt.Adapter.AdapterInformasi;
import com.reynaldiwijaya.smartrt.Api.ApiClient;
import com.reynaldiwijaya.smartrt.Api.ApiInterface;
import com.reynaldiwijaya.smartrt.Helper.Constant;
import com.reynaldiwijaya.smartrt.Helper.SessionManager;
import com.reynaldiwijaya.smartrt.R;
import com.reynaldiwijaya.smartrt.model.Informasi.NewsItem;
import com.reynaldiwijaya.smartrt.model.Informasi.ResponseInformasi;
import com.reynaldiwijaya.smartrt.ui.Informasi.Presenter.InformasiContract;
import com.reynaldiwijaya.smartrt.ui.Informasi.Presenter.InformasiPresenter;

import net.gotev.uploadservice.MultipartUploadRequest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InformasiActivity extends AppCompatActivity implements InformasiContract.View {

    @BindView(R.id.shimmer_view_container)
    ShimmerFrameLayout shimmerViewContainer;
    @BindView(R.id.rv_main)
    RecyclerView rvMain;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;

    private ProgressDialog progressDialog;

    private InformasiPresenter informasiPresenter = new InformasiPresenter(this);
    private Dialog dialog;
    private Bitmap bitmap;
    private Uri filepath;
    private String judul, content, date, id_user, path, konfirmasi;
    private SessionManager sm;
    private ImageView ivPreview;
    private Button btn_selected_photo;
    private TextInputEditText edt_judul;
    private TextInputEditText edt_content;
    private Button btn_send;
    private Button btn_cancel;
    private ApiInterface apiInterface;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informasi);
        ButterKnife.bind(this);

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                informasiPresenter.getInformasi();
            }
        });

        sm = new SessionManager(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        informasiPresenter.getInformasi();
    }

    @Override
    protected void onPause() {
        super.onPause();

        shimmerViewContainer.stopShimmerAnimation();
    }

    @Override
    public void showProgress() {
        swipeRefresh.setRefreshing(true);
        shimmerViewContainer.startShimmerAnimation();
    }

    @Override
    public void hideProgress() {
        swipeRefresh.setRefreshing(false);
        shimmerViewContainer.stopShimmerAnimation();
        shimmerViewContainer.setVisibility(View.GONE);
    }

    @Override
    public void showInformasi(List<NewsItem> newsItemList) {
        rvMain.setVisibility(View.VISIBLE);
        rvMain.setLayoutManager(new LinearLayoutManager(this));
        rvMain.setAdapter(new AdapterInformasi(this, newsItemList));
    }

    @Override
    public void showFailureMessage(String msg) {
        Toasty.error(this, msg, Toasty.LENGTH_SHORT).show();
    }

    @OnClick(R.id.fab)
    public void onViewClicked() {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.activity_tambah_news);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);

        ivPreview = dialog.findViewById(R.id.img_foto_berita);
        btn_selected_photo = dialog.findViewById(R.id.btn_selected_photo);
        edt_judul = dialog.findViewById(R.id.edt_judul);
        edt_content = dialog.findViewById(R.id.edt_content);
        btn_send = dialog.findViewById(R.id.btn_send);
        btn_cancel = dialog.findViewById(R.id.btn_cancel);

        btn_selected_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser(Constant.REQ_CHOOSE_FILE_NEWS);
            }
        });

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(InformasiActivity.this);
                builder.setMessage(getString(R.string.keterengan_berita));
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getData();
                        chekData();
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
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void clearData() {
        edt_judul.setText("");
        edt_content.setText("");
    }

    private void getData() {
        judul = edt_judul.getText().toString();
        content = edt_content.getText().toString();
        date = getCurentDate();

        if (sm.getKonfirmasi().equals("2")) {
            konfirmasi = "1";
        } else {
            konfirmasi = "0";
        }

        id_user = sm.getIdUser();
        Log.i("judul", judul);
        Log.i("content", content);
        Log.i("date", date);
        Log.i("id", sm.getIdUser());
    }

    private void chekData() {

        boolean isValid = true;

        if (TextUtils.isEmpty(judul)) {
            edt_judul.setError(getString(R.string.error_message));
            edt_judul.requestFocus();
            isValid = false;
            return;
        }
        if (TextUtils.isEmpty(content)) {
            edt_content.setError(getString(R.string.error_message));
            edt_content.requestFocus();
            isValid = false;
            return;
        }
        if (ivPreview.getDrawable() == null) {
            Toast.makeText(this, "Gambar tidak boleh kosong", Toast.LENGTH_SHORT).show();
            isValid = false;
            return;
        }

        if(isValid) {
            sendData();
            clearData();
        }
    }

    private void showFileChooser(int requestCode) {
        Intent toGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(toGallery, requestCode);
        Log.i("Gallery", "Masuk Galley");
    }

    private void sendData() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        path = getPath(filepath);
        File imageFile = new File(path);

        RequestBody image = RequestBody.create(MediaType.parse("multipart/form-data"), imageFile);
        MultipartBody.Part partImage = MultipartBody.Part.createFormData("foto", imageFile.getName(), image);

        RequestBody id = RequestBody.create(MediaType.parse("multipart/form-data"), id_user);
        RequestBody judulinformasi = RequestBody.create(MediaType.parse("multipart/form-data"), judul);
        RequestBody contentInformasi = RequestBody.create(MediaType.parse("multipart/form-data"), content);
        RequestBody tanggal = RequestBody.create(MediaType.parse("multipart/form-data"), date);
        RequestBody konfirmasiInformasi = RequestBody.create(MediaType.parse("multipart/form-data"), konfirmasi);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseInformasi> call = apiInterface.informasi(id, judulinformasi, contentInformasi, tanggal, konfirmasiInformasi, partImage);
        call.enqueue(new Callback<ResponseInformasi>() {
            @Override
            public void onResponse(Call<ResponseInformasi> call, Response<ResponseInformasi> response) {
                progressDialog.dismiss();

                ResponseInformasi responseInformasi = response.body();

                if (responseInformasi.getResult().equals("1")) {
                    Toasty.success(InformasiActivity.this, responseInformasi.getMsg(), Toasty.LENGTH_SHORT).show();
                    dialog.dismiss();
                } else {
                    Toasty.error(InformasiActivity.this, responseInformasi.getMsg(), Toasty.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseInformasi> call, Throwable t) {
                progressDialog.dismiss();
                Toasty.error(InformasiActivity.this, t.getMessage(), Toasty.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("TAG", "Masuk OnActivityResult");
        if (requestCode == Constant.REQ_CHOOSE_FILE_NEWS) {
            if (resultCode == RESULT_OK) {
                if (data.getData() != null) {
                    filepath = data.getData();
                    Log.i("TAGO", filepath.toString());

                }

                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filepath);
                    ivPreview.setImageBitmap(bitmap);
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

    public String getCurentDate() {
        @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd 'at' HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
