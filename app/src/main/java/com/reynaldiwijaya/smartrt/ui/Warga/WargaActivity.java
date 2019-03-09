package com.reynaldiwijaya.smartrt.ui.Warga;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.reynaldiwijaya.smartrt.Adapter.AdapterWarga;
import com.reynaldiwijaya.smartrt.Api.ApiClient;
import com.reynaldiwijaya.smartrt.Api.ApiInterface;
import com.reynaldiwijaya.smartrt.R;
import com.reynaldiwijaya.smartrt.model.Warga.ResponseWarga;
import com.reynaldiwijaya.smartrt.model.Warga.UserItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WargaActivity extends AppCompatActivity {
    Unbinder unbinder;
    @BindView(R.id.rv_warga)
    RecyclerView rvWarga;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;

    private List<UserItem> userItemList;
    private ApiInterface apiInterface;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_warga);
        ButterKnife.bind(this);

        userItemList = new ArrayList<>();
        showData();

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                showData();
            }
        });
    }

    private void showData() {
        swipeRefresh.setRefreshing(true);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseWarga> call = apiInterface.warga();
        call.enqueue(new Callback<ResponseWarga>() {
            @Override
            public void onResponse(Call<ResponseWarga> call, Response<ResponseWarga> response) {
                swipeRefresh.setRefreshing(false);

                ResponseWarga responseWarga = response.body();

                if (responseWarga != null) {
                    userItemList.clear();
                    userItemList = responseWarga.getUser();
                    setUpList();
                }
            }

            @Override
            public void onFailure(Call<ResponseWarga> call, Throwable t) {
                swipeRefresh.setRefreshing(false);
                Toasty.error(WargaActivity.this, t.getMessage(), Toasty.LENGTH_SHORT).show();

            }
        });
    }

    private void setUpList() {
        rvWarga.setLayoutManager(new LinearLayoutManager(this));
        rvWarga.setAdapter(new AdapterWarga(this, userItemList));
    }

    private void showProgress() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading ...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }
}
