package com.reynaldiwijaya.smartrt.ui.Store;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.reynaldiwijaya.smartrt.Adapter.AdapterStore;
import com.reynaldiwijaya.smartrt.R;
import com.reynaldiwijaya.smartrt.model.Store.StoreItem;
import com.reynaldiwijaya.smartrt.ui.Store.Presenter.StoreContract;
import com.reynaldiwijaya.smartrt.ui.Store.Presenter.StorePresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

public class StoreActivity extends AppCompatActivity implements StoreContract.View {
    @BindView(R.id.rv_store)
    RecyclerView rvStore;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    private StorePresenter storePresenter = new StorePresenter(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        ButterKnife.bind(this);

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                storePresenter.getDataStore();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        storePresenter.getDataStore();
    }

    @OnClick(R.id.fab)
    public void onViewClicked() {
        startActivity(new Intent(this, InsertStoreActivity.class));
    }

    @Override
    public void showProgress() {
       swipeRefresh.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        swipeRefresh.setRefreshing(false);
    }

    @Override
    public void showDataStore(List<StoreItem> storeItemList) {
        rvStore.setLayoutManager(new LinearLayoutManager(this));
        rvStore.setAdapter(new AdapterStore(this, storeItemList));
    }

    @Override
    public void showFailureMessage(String msg) {
        Toasty.error(this, msg, Toasty.LENGTH_SHORT).show();
    }

}
