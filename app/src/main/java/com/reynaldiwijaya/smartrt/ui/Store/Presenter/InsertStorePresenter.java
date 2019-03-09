package com.reynaldiwijaya.smartrt.ui.Store.Presenter;

import android.content.Context;
import android.net.Uri;

import com.reynaldiwijaya.smartrt.Api.ApiClient;
import com.reynaldiwijaya.smartrt.Api.ApiInterface;
import com.reynaldiwijaya.smartrt.Helper.Constant;
import com.reynaldiwijaya.smartrt.model.Store.ResponseStore;
import com.reynaldiwijaya.smartrt.ui.Store.InsertStoreActivity;

import net.gotev.uploadservice.MultipartUploadRequest;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertStorePresenter implements InsertStoreContract.Presenter {
    private InsertStoreContract.View view;
    private Context context;

    public InsertStorePresenter(InsertStoreContract.View view, Context context) {
        this.view = view;
        this.context = context;
    }


    @Override
    public void getDataStore(String id_user, String nama_toko, String alamat, String deskripsi, String no_tlp, String path) {
        view.showProgress();

        try {
            new MultipartUploadRequest(context, Constant.UPLOAD_URL_STORE)
                    .addFileToUpload(path, "foto")
                    .addParameter("id_user", id_user)
                    .addParameter("nama_toko", nama_toko)
                    .addParameter("deskripsi", deskripsi)
                    .addParameter("alamat", alamat)
                    .addParameter("no_tlp", no_tlp)
                    .setMaxRetries(2)
                    .startUpload();
            view.hideProgress();
            view.showSuccesMessage("Succes to Send");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            view.hideProgress();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            view.hideProgress();
        }

    }
}
