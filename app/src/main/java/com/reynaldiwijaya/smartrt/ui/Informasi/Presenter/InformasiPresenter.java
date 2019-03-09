package com.reynaldiwijaya.smartrt.ui.Informasi.Presenter;

import com.reynaldiwijaya.smartrt.Api.ApiClient;
import com.reynaldiwijaya.smartrt.Api.ApiInterface;
import com.reynaldiwijaya.smartrt.model.Informasi.ResponseGetInformasi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InformasiPresenter implements InformasiContract.Presenter {

    private ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
    private InformasiContract.View view;

    public InformasiPresenter(InformasiContract.View view) {
        this.view = view;
    }

    @Override
    public void getInformasi() {
        view.showProgress();

        Call<ResponseGetInformasi> call = apiInterface.getInformasi();
        call.enqueue(new Callback<ResponseGetInformasi>() {
            @Override
            public void onResponse(Call<ResponseGetInformasi> call, Response<ResponseGetInformasi> response) {
                view.hideProgress();

                if (response.body() != null) {
                    ResponseGetInformasi responseGetInformasi = response.body();

                    if (responseGetInformasi.getNews() != null) {
                        view.showInformasi(responseGetInformasi.getNews());

                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseGetInformasi> call, Throwable t) {
                view.hideProgress();

                view.showFailureMessage(t.getMessage());
            }
        });

    }
}
