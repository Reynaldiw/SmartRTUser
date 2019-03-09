package com.reynaldiwijaya.smartrt.ui.NewsIndonesia.Presenter.Main;

import com.reynaldiwijaya.smartrt.Api.ApiClientNews;
import com.reynaldiwijaya.smartrt.Api.ApiInterface;
import com.reynaldiwijaya.smartrt.model.NewsIndonesia.ResponseNewsIndonesia;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsPresenter implements NewsContract.Presenter{

    private ApiInterface apiInterface;

    private NewsContract.View view;

    public NewsPresenter(NewsContract.View view) {
        this.view = view;
    }

    @Override
    public void getData() {
        view.showProgress();

        apiInterface = ApiClientNews.getClientNews().create(ApiInterface.class);
        Call<ResponseNewsIndonesia> call = apiInterface.getNewsIndonesia();
        call.enqueue(new Callback<ResponseNewsIndonesia>() {
            @Override
            public void onResponse(Call<ResponseNewsIndonesia> call, Response<ResponseNewsIndonesia> response) {
                view.hideProgress();

                if (response.body() != null && response.body().getArticles() != null) {
                    ResponseNewsIndonesia responseNewsIndonesia = response.body();

                    if (responseNewsIndonesia.getStatus().equals("ok")) {

                        view.showData(responseNewsIndonesia.getArticles());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseNewsIndonesia> call, Throwable t) {
                view.hideProgress();
                view.showFailureMessage();

            }
        });

    }
}
