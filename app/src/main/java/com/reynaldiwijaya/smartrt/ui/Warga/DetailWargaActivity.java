package com.reynaldiwijaya.smartrt.ui.Warga;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.reynaldiwijaya.smartrt.Helper.Constant;
import com.reynaldiwijaya.smartrt.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class DetailWargaActivity extends AppCompatActivity {


    @BindView(R.id.img_user)
    CircleImageView imgUser;
    @BindView(R.id.tv_nama)
    TextView tvNama;
    @BindView(R.id.tv_level)
    TextView tvLevel;
    @BindView(R.id.tv_jenkel)
    TextView tvJenkel;
    @BindView(R.id.tv_profesi)
    TextView tvProfesi;
    @BindView(R.id.tv_noKtp)
    TextView tvNoKtp;
    @BindView(R.id.tv_home)
    TextView tvHome;
    @BindView(R.id.tv_birth)
    TextView tvBirth;
    @BindView(R.id.tv_status)
    TextView tvStatus;
    @BindView(R.id.tv_email)
    TextView tvEmail;
    @BindView(R.id.tv_call)
    TextView tvCall;
    @BindView(R.id.line1)
    LinearLayout line1;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    private Bundle bundle;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setColorMode(getResources().getColor(R.color.colorPrimary));


        setContentView(R.layout.activity_detail_warga);

        ButterKnife.bind(this);

        bundle = new Bundle();
        bundle = getIntent().getExtras();

        if (bundle != null) {
            showData();
        }

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                showData();
            }
        });
    }

    private void showData() {
        swipeRefresh.setRefreshing(false);
        RequestOptions options = new RequestOptions().error(R.drawable.avatar);

        Glide.with(DetailWargaActivity.this)
                .load(Constant.IMAGE_USER_URL + bundle.getString(Constant.FOTO))
                .apply(options)
                .into(imgUser);

        tvNama.setText(bundle.getString(Constant.NAMA));
        tvLevel.setText(bundle.getString(Constant.LEVEL));
        tvNoKtp.setText(bundle.getString(Constant.NO_KTP));
        tvJenkel.setText(bundle.getString(Constant.JENKEL));
        tvStatus.setText(bundle.getString(Constant.STATUS));
        tvBirth.setText(bundle.getString(Constant.DATE));
        tvProfesi.setText(bundle.getString(Constant.PROFESI));
        tvEmail.setText(bundle.getString(Constant.EMAIL));
        tvCall.setText(bundle.getString(Constant.NO_TLP));
        tvHome.setText(bundle.getString(Constant.ALAMAT));
    }
}
