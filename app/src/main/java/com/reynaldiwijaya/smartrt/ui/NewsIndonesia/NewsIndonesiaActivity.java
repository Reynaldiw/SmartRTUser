package com.reynaldiwijaya.smartrt.ui.NewsIndonesia;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.reynaldiwijaya.smartrt.Adapter.AdapterNewsIndonesia;
import com.reynaldiwijaya.smartrt.R;
import com.reynaldiwijaya.smartrt.model.NewsIndonesia.ArticlesItem;
import com.reynaldiwijaya.smartrt.ui.NewsIndonesia.Presenter.Main.NewsContract;
import com.reynaldiwijaya.smartrt.ui.NewsIndonesia.Presenter.Main.NewsPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

public class NewsIndonesiaActivity extends AppCompatActivity implements NewsContract.View {

    private NewsPresenter newsPresenter = new NewsPresenter(this);

    @BindView(R.id.rv_news)
    RecyclerView rvNews;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_indonesia);
        ButterKnife.bind(this);

        newsPresenter.getData();

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                newsPresenter.getData();
            }
        });
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
    public void showData(List<ArticlesItem> newsItemList) {
        rvNews.setLayoutManager(new LinearLayoutManager(this));
        rvNews.setAdapter(new AdapterNewsIndonesia(this, newsItemList));
    }

    @Override
    public void showFailureMessage() {
        Toasty.error(this, "Gagal", Toasty.LENGTH_SHORT).show();
    }
}
