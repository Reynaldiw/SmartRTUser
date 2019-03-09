package com.reynaldiwijaya.smartrt.ui.Home.Presenter;

import com.reynaldiwijaya.smartrt.Api.ApiClient;
import com.reynaldiwijaya.smartrt.Api.ApiInterface;
import com.reynaldiwijaya.smartrt.model.Informasi.ResponseGetInformasi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePresenter implements HomeContract.Presenter {

    private final ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

    private final HomeContract.View view;

    public HomePresenter(HomeContract.View view) {
        this.view = view;
    }

    @Override
    public void getData() {
        Call<ResponseGetInformasi> call = apiInterface.getInformasi();
        call.enqueue(new Callback<ResponseGetInformasi>() {
            @Override
            public void onResponse(Call<ResponseGetInformasi> call, Response<ResponseGetInformasi> response) {
            if (response.body() != null) {
                ResponseGetInformasi responseGetInformasi = response.body();

                if (responseGetInformasi.getNews() != null) {
                    view.showData(responseGetInformasi.getNews());
                }
            }
            }

            @Override
            public void onFailure(Call<ResponseGetInformasi> call, Throwable t) {
                view.showFailureMsg(t.getMessage());
            }
        });
    }
}
