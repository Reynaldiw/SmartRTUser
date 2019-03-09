package com.reynaldiwijaya.smartrt.ui.Store.Presenter;

import com.reynaldiwijaya.smartrt.Api.ApiClient;
import com.reynaldiwijaya.smartrt.Api.ApiInterface;
import com.reynaldiwijaya.smartrt.model.Store.ResponseDataStore;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StorePresenter implements StoreContract.Presenter{
    private StoreContract.View view;

    public StorePresenter(StoreContract.View view) {
        this.view = view;
    }

    private ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

    @Override
    public void getDataStore() {
        view.showProgress();

        Call<ResponseDataStore> call = apiInterface.getDataStore();
        call.enqueue(new Callback<ResponseDataStore>() {
            @Override
            public void onResponse(Call<ResponseDataStore> call, Response<ResponseDataStore> response) {
                view.hideProgress();

                if (response.body() != null) {
                    ResponseDataStore responseDataStore = response.body();

                    if (responseDataStore.getStore() != null) {
                        view.showDataStore(responseDataStore.getStore());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseDataStore> call, Throwable t) {
                view.hideProgress();
                view.showFailureMessage(t.getMessage());
            }
        });
    }
}
