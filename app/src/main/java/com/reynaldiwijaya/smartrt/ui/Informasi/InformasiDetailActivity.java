package com.reynaldiwijaya.smartrt.ui.Informasi;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.reynaldiwijaya.smartrt.Helper.Constant;
import com.reynaldiwijaya.smartrt.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class InformasiDetailActivity extends AppCompatActivity {

    @BindView(R.id.ivGambarBerita)
    ImageView ivGambarBerita;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsing)
    CollapsingToolbarLayout collapsing;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.img_profile)
    CircleImageView imgProfile;
    @BindView(R.id.tv_nama)
    TextView tvNama;
    @BindView(R.id.tv_noTlp)
    TextView tvNoTlp;
    @BindView(R.id.img_call)
    ImageButton imgCall;

    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informasi_detail);
        ButterKnife.bind(this);

        bundle = getIntent().getExtras();

        if (bundle != null) {
            showData();
        }

    }

    private void showData() {
        RequestOptions requestOptions = new RequestOptions().error(R.drawable.avatar).placeholder(R.drawable.avatar);
        Glide.with(this)
                .load(Constant.IMAGE_INFORMASI_URL + bundle.getString(Constant.FOTO_INFORMASI))
                .apply(requestOptions)
                .into(ivGambarBerita);

        Glide.with(this)
                .load(Constant.IMAGE_USER_URL + bundle.getString(Constant.FOTO))
                .apply(requestOptions)
                .into(imgProfile);

        tvContent.setText(bundle.getString(Constant.CONTENT));
        tvDate.setText(bundle.getString(Constant.DATE));
        tvNama.setText(bundle.getString(Constant.NAMA));
        tvNoTlp.setText(bundle.getString(Constant.NO_TLP));


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing);
        collapsingToolbarLayout.setTitle(bundle.getString(Constant.JUDUL));

        collapsingToolbarLayout.setCollapsedTitleTextColor(
                ContextCompat.getColor(this, android.R.color.black));
        collapsingToolbarLayout.setExpandedTitleColor(
                ContextCompat.getColor(this, android.R.color.white));
    }

    @OnClick(R.id.img_call)
    public void onViewClicked() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + bundle.getString(Constant.NO_TLP)));
        startActivity(intent);
    }
}
