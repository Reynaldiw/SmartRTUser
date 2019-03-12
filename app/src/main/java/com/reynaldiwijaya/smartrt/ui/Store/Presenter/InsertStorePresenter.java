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

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertStorePresenter implements InsertStoreContract.Presenter {
    private InsertStoreContract.View view;
    private Context context;

    private ApiInterface apiInterface;

    public InsertStorePresenter(InsertStoreContract.View view, Context context) {
        this.view = view;
        this.context = context;
    }


    @Override
    public void getDataStore(RequestBody id_user, RequestBody nama_toko, RequestBody alamat, RequestBody deskripsi, RequestBody no_tlp, MultipartBody.Part path, RequestBody konfirmasi) {
        view.showProgress();

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseStore> call = apiInterface.sendDataStore(id_user, nama_toko, deskripsi, alamat, no_tlp,konfirmasi, path);
        call.enqueue(new Callback<ResponseStore>() {
            @Override
            public void onResponse(Call<ResponseStore> call, Response<ResponseStore> response) {
                view.hideProgress();

                ResponseStore responseStore = response.body();

                if (responseStore.getResult().equals("1")) {
                    view.showSuccesMessage(responseStore.getMsg());
                }else {
                    view.showFailureMessage(responseStore.getMsg());
                }
            }

            @Override
            public void onFailure(Call<ResponseStore> call, Throwable t) {
                view.hideProgress();
                view.showFailureMessage(t.getMessage());
            }
        });
    }
}
