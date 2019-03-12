package com.reynaldiwijaya.smartrt.ui.Profile;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.reynaldiwijaya.smartrt.Api.ApiClient;
import com.reynaldiwijaya.smartrt.Api.ApiInterface;
import com.reynaldiwijaya.smartrt.Helper.Constant;
import com.reynaldiwijaya.smartrt.Helper.SessionManager;
import com.reynaldiwijaya.smartrt.R;
import com.reynaldiwijaya.smartrt.model.Profile.ResponseProfile;
import com.reynaldiwijaya.smartrt.model.Profile.UserItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {

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
    Unbinder unbinder;
    @BindView(R.id.btn_logut)
    Button btnLogut;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    private SessionManager sm;
    private ApiInterface apiInterface;
    private String idUser;
    private List<UserItem> userItemList;


    public ProfileFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_profile, container, false);
        unbinder = ButterKnife.bind(this, view);

        sm = new SessionManager(getActivity());

        setUpList();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.i("Profile", sm.getIdUser());

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setUpList();
            }
        });

    }

    private void setUpList() {
        RequestOptions requestOptions = new RequestOptions().error(R.drawable.avatar);
        Glide.with(getActivity())
                .load(Constant.IMAGE_USER_URL + sm.getImageUser())
                .apply(requestOptions)
                .into(imgUser);

        tvNama.setText(sm.getNameUser());
        tvLevel.setText(sm.getLevel());
        tvJenkel.setText(sm.getJenkel());
        tvProfesi.setText(sm.getProfesi());
        tvNoKtp.setText(sm.getNoKtp());
        tvBirth.setText(sm.getTglLahir());
        tvHome.setText(sm.getAlamat());
        tvCall.setText(sm.getNoTlp());
        tvStatus.setText(sm.getStatus());
        tvEmail.setText(sm.getEmail());

        swipeRefresh.setRefreshing(false);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.btn_logut})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_logut:
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Are you sure want to logout ?");
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sm.Logout();
                        getActivity().finish();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                break;
        }
    }
}
