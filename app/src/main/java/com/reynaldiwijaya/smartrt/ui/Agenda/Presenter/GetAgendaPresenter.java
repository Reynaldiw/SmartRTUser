package com.reynaldiwijaya.smartrt.ui.Agenda.Presenter;

import com.reynaldiwijaya.smartrt.Api.ApiClient;
import com.reynaldiwijaya.smartrt.Api.ApiInterface;
import com.reynaldiwijaya.smartrt.model.Agenda.ResponseGetAgenda;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetAgendaPresenter implements GetAgendaContract.Presenter {

    private ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

    public GetAgendaPresenter(GetAgendaContract.View view) {
        this.view = view;
    }

    private final GetAgendaContract.View view;

    @Override
    public void getDataAgenda() {
        view.showProgress();

        Call<ResponseGetAgenda> call = apiInterface.getAgenda();
        call.enqueue(new Callback<ResponseGetAgenda>() {
            @Override
            public void onResponse(Call<ResponseGetAgenda> call, Response<ResponseGetAgenda> response) {
                if (response.body() != null) {
                    ResponseGetAgenda responseGetAgenda = response.body();

                    if (responseGetAgenda.getAgenda() != null) {
                        view.hideProgress();
                        view.showDataAgenda(responseGetAgenda.getAgenda());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseGetAgenda> call, Throwable t) {
                view.hideProgress();
                view.showFailureMessage(t.getMessage());
            }
        });

    }
}
