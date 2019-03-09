package com.reynaldiwijaya.smartrt.ui.Report;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.reynaldiwijaya.smartrt.Adapter.AdapterReport;
import com.reynaldiwijaya.smartrt.R;
import com.reynaldiwijaya.smartrt.model.Laporan.LaporanItem;
import com.reynaldiwijaya.smartrt.ui.Report.Presenter.ReportContract;
import com.reynaldiwijaya.smartrt.ui.Report.Presenter.ReportPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

public class ReportActivity extends AppCompatActivity implements ReportContract.View {

    @BindView(R.id.tv_pesan)
    TextView tvPesan;
    @BindView(R.id.rv_report)
    RecyclerView rvReport;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;

    private List<LaporanItem> laporanItems = new ArrayList<>();
    private ReportPresenter reportPresenter = new ReportPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        ButterKnife.bind(this);

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                laporanItems.clear();
                reportPresenter.getLaporanKonfirmasi();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        laporanItems.clear();
        reportPresenter.getLaporanKonfirmasi();
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
    public void showData(List<LaporanItem> laporanItemList) {
        laporanItems.addAll(laporanItemList);
        initAdapter();
    }

    private void initAdapter() {
        rvReport.setLayoutManager(new LinearLayoutManager(this));
        rvReport.setAdapter(new AdapterReport(this, laporanItems));
    }

    @Override
    public void showFailureMessage(String message) {
        Toasty.error(this, message, Toasty.LENGTH_SHORT).show();
    }

    @Override
    public void showPesan() {
        tvPesan.setVisibility(View.VISIBLE);
        initAdapter();

    }

    @Override
    public void hidePesan() {
        tvPesan.setVisibility(View.GONE);
        initAdapter();
    }
}
