package com.reynaldiwijaya.smartrt.ui.Home.Presenter.UI;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.card.MaterialCardView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.reynaldiwijaya.smartrt.Adapter.AdapterInformasi;
import com.reynaldiwijaya.smartrt.Helper.Constant;
import com.reynaldiwijaya.smartrt.Helper.SessionManager;
import com.reynaldiwijaya.smartrt.R;
import com.reynaldiwijaya.smartrt.model.Informasi.NewsItem;
import com.reynaldiwijaya.smartrt.ui.Agenda.View.AgendaActivity;
import com.reynaldiwijaya.smartrt.ui.Home.Presenter.HomeContract;
import com.reynaldiwijaya.smartrt.ui.Home.Presenter.HomePresenter;
import com.reynaldiwijaya.smartrt.ui.Informasi.InformasiActivity;
import com.reynaldiwijaya.smartrt.ui.NewsIndonesia.NewsIndonesiaActivity;
import com.reynaldiwijaya.smartrt.ui.Report.ReportActivity;
import com.reynaldiwijaya.smartrt.ui.Store.StoreActivity;
import com.reynaldiwijaya.smartrt.ui.Warga.WargaActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;

public class HomeFragment extends Fragment implements HomeContract.View {

    @BindView(R.id.cv_news)
    MaterialCardView cvNews;
    @BindView(R.id.cv_citizen)
    MaterialCardView cvCitizen;
    @BindView(R.id.cv_store)
    MaterialCardView cvStore;
    @BindView(R.id.cv_agenda)
    MaterialCardView cvAgenda;
    @BindView(R.id.cv_laporan)
    MaterialCardView cvLaporan;
    @BindView(R.id.cv_home)
    MaterialCardView cvHome;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.rv_news)
    RecyclerView rvNews;
    Unbinder unbinder;
    @BindView(R.id.img_user)
    CircleImageView imgUser;
    @BindView(R.id.tv_nama)
    TextView tvNama;
    @BindView(R.id.tv_latestNes)
    TextView tvLatestNes;
    @BindView(R.id.cv_news_indonesia)
    MaterialCardView cvNewsIndonesia;

    private HomePresenter homePresenter = new HomePresenter(this);
    private SessionManager sm;
    private ProgressDialog progressDialog;

    public HomeFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);

        sm = new SessionManager(getActivity());


        RequestOptions requestOptions = new RequestOptions().error(R.drawable.avatar);
        Glide.with(getActivity())
                .load(Constant.IMAGE_USER_URL + sm.getImageUser())
                .apply(requestOptions)
                .into(imgUser);

        tvNama.setText(sm.getNameUser());

        homePresenter.getData();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.cv_news, R.id.cv_citizen, R.id.cv_store, R.id.cv_agenda, R.id.cv_news_indonesia, R.id.cv_laporan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cv_news:
                startActivity(new Intent(getActivity(), InformasiActivity.class));
                break;
            case R.id.cv_citizen:
                startActivity(new Intent(getActivity(), WargaActivity.class));
                break;
            case R.id.cv_store:
                startActivity(new Intent(getActivity(), StoreActivity.class));
                break;
            case R.id.cv_agenda:
                startActivity(new Intent(getActivity(), AgendaActivity.class));
                break;
            case R.id.cv_news_indonesia:
                startActivity(new Intent(getActivity(), NewsIndonesiaActivity.class));
                break;
            case R.id.cv_laporan:
                startActivity(new Intent(getActivity(), ReportActivity.class));
                break;
        }
    }

    @Override
    public void showProgress() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void showFailureMsg(String msg) {
        Toasty.error(getActivity(), msg, Toasty.LENGTH_SHORT).show();
    }

    @Override
    public void showData(List<NewsItem> newsItemList) {
        rvNews.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        rvNews.setAdapter(new AdapterInformasi(getActivity(), newsItemList));
        rvNews.setHasFixedSize(true);
    }

}
