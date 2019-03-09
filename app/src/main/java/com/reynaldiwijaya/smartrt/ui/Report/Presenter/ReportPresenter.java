package com.reynaldiwijaya.smartrt.ui.Report.Presenter;

import com.reynaldiwijaya.smartrt.Api.ApiClient;
import com.reynaldiwijaya.smartrt.Api.ApiInterface;
import com.reynaldiwijaya.smartrt.model.Laporan.ResponseLaporanKonfirmasi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReportPresenter implements ReportContract.Presenter {

    private ApiInterface apiInterface;

    private ReportContract.View view;

    public ReportPresenter(ReportContract.View view) {
        this.view = view;
    }

    @Override
    public void getLaporanKonfirmasi() {
        view.showProgress();

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseLaporanKonfirmasi> call = apiInterface.getLaporanKonfirmasi();
        call.enqueue(new Callback<ResponseLaporanKonfirmasi>() {
            @Override
            public void onResponse(Call<ResponseLaporanKonfirmasi> call, Response<ResponseLaporanKonfirmasi> response) {
                view.hideProgress();

                        if (response.body().getLaporan() != null) {

                            view.showData(response.body().getLaporan());
                            view.hidePesan();

                        } else {
                            view.showPesan();
                        }
            }

            @Override
            public void onFailure(Call<ResponseLaporanKonfirmasi> call, Throwable t) {
                view.hideProgress();
                view.showFailureMessage(t.getMessage());

            }
        });

    }
}
