package com.reynaldiwijaya.smartrt.ui.NewsIndonesia;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.reynaldiwijaya.smartrt.Helper.Constant;
import com.reynaldiwijaya.smartrt.R;
import com.reynaldiwijaya.smartrt.model.NewsIndonesia.ArticlesItem;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailNewsActivity extends AppCompatActivity {

    @BindView(R.id.ivGambarBerita)
    ImageView ivGambarBerita;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.tv_judul)
    TextView tvJudul;
    @BindView(R.id.tvTglTerbit)
    TextView tvTglTerbit;
    @BindView(R.id.tvPenulis)
    TextView tvPenulis;
    @BindView(R.id.wv_content_berita)
    WebView wvContentBerita;
    private String judul, content, author, time, urlImage, urlContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_news);
        ButterKnife.bind(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getData();
        tampilBerita();

    }

    private void getData() {
        ArticlesItem articlesItem = getIntent().getParcelableExtra(Constant.OBJ);

        judul = articlesItem.getTitle();
        author = articlesItem.getAuthor();
        content = articlesItem.getContent();
        time = articlesItem.getPublishedAt();
        urlImage = articlesItem.getUrlToImage();
        urlContent = articlesItem.getUrl();

    }

    @SuppressLint("SetJavaScriptEnabled")
    private void tampilBerita() {
        tvJudul.setText(judul);
        tvPenulis.setText(author);
        tvTglTerbit.setText(time);
        Glide.with(this)
                .load(urlImage)
                .into(ivGambarBerita);

        wvContentBerita.getSettings().setJavaScriptEnabled(true);
//        String url = "text/html; charset=utf-8";
//        String url2 = "UTF-8";
        wvContentBerita.loadUrl(urlContent);

        getSupportActionBar().setTitle(judul);

    }
}
