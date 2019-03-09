package com.reynaldiwijaya.smartrt.ui.Agenda.Presenter;

import com.reynaldiwijaya.smartrt.Api.ApiClient;
import com.reynaldiwijaya.smartrt.Api.ApiInterface;
import com.reynaldiwijaya.smartrt.model.Agenda.ResponseAgenda;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgendaPresenter implements AgendaContract.Presenter {

    private final AgendaContract.View view;

    private ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

    public AgendaPresenter(AgendaContract.View view) {
        this.view = view;
    }


    @Override
    public void postAgenda(String judul, String content, String tempat, String tanggal, int konfirmasi) {
        if (judul == null || judul.isEmpty()) {
            view.showFailureMessage("Judul Kosong");
            return;
        }
        if (content == null || judul.isEmpty()) {
            view.showFailureMessage("Content Kosong");
            return;
        }
        if (tempat == null || tempat.isEmpty()) {
            view.showFailureMessage("Field tempat tidak boleh kosong");
            return;
        }
        if (tanggal == null || tanggal.isEmpty()) {
            view.showFailureMessage("Field tanggal tidak boleh kosong");
            return;
        }

        view.showProgress();

        Call<ResponseAgenda> call = apiInterface.agenda(judul, content, tempat, tanggal, konfirmasi);
        call.enqueue(new Callback<ResponseAgenda>() {
            @Override
            public void onResponse(Call<ResponseAgenda> call, Response<ResponseAgenda> response) {
                view.hideProgress();
                if (response.body() != null) {
                    ResponseAgenda responseAgenda = response.body();

                    if (responseAgenda.getPesan() != null) {
                        view.showSuccesMessage(responseAgenda.getPesan());
                        view.clearData();
                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseAgenda> call, Throwable t) {
                view.hideProgress();
                view.showFailureMessage(t.getMessage());
            }
        });
    }
}
