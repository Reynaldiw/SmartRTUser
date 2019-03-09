package com.reynaldiwijaya.smartrt.ui.Report;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.reynaldiwijaya.smartrt.Api.ApiClient;
import com.reynaldiwijaya.smartrt.Api.ApiInterface;
import com.reynaldiwijaya.smartrt.Helper.SessionManager;
import com.reynaldiwijaya.smartrt.R;
import com.reynaldiwijaya.smartrt.model.Laporan.ResponseLaporan;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReportFragment extends Fragment {


    @BindView(R.id.edt_judul)
    TextInputEditText edtJudul;
    @BindView(R.id.edt_lapran)
    TextInputEditText edtLapran;
    @BindView(R.id.btn_send)
    Button btnSend;
    Unbinder unbinder;
    @BindView(R.id.ib_date)
    ImageButton ibDate;
    @BindView(R.id.tv_date)
    TextView tvDate;

    private ApiInterface apiInterface;
    private String iduser, judul, laporan, tgl_lapor;
    private SessionManager sm;
    private ProgressDialog progressDialog;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    public ReportFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_report, container, false);
        unbinder = ButterKnife.bind(this, view);

        sm = new SessionManager(getActivity());
        iduser = sm.getIdUser();

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                Log.d("TAG", year + "-" + month + "-" + dayOfMonth);
                String date = year + "-" + month + "-" + dayOfMonth;
                tvDate.setText(date);
            }
        };

        return view;
    }

    private void checkData() {

        boolean isValid = true;

        if (TextUtils.isEmpty(judul)) {
            edtJudul.setError(getString(R.string.error_message));
            edtJudul.requestFocus();
            isValid = false;
            return;
        }
        if (TextUtils.isEmpty(laporan)) {
            edtLapran.setError(getString(R.string.error_message));
            edtLapran.requestFocus();
            isValid = false;
            return;
        }
        if (TextUtils.isEmpty(tgl_lapor)) {
           Toasty.error(getActivity(), "Tanggal Lapor harus diisi", Toasty.LENGTH_SHORT).show();
           isValid = false;
           return;
        }

        if (isValid) {
            sendData();
        }

    }

    private void sendData() {
        showProgressDialog("Loading ...");

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseLaporan> call = apiInterface.laporan(iduser, judul, laporan, tgl_lapor);
        call.enqueue(new Callback<ResponseLaporan>() {
            @Override
            public void onResponse(Call<ResponseLaporan> call, Response<ResponseLaporan> response) {
                progressDialog.dismiss();

                ResponseLaporan responseLaporan = response.body();

                String result = responseLaporan.getResult();

                if (result.equals("sukses")) {
                    Toasty.success(getActivity(), responseLaporan.getPesan(), Toast.LENGTH_LONG).show();
                } else {
                    Toasty.error(getActivity(), responseLaporan.getPesan(), Toasty.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<ResponseLaporan> call, Throwable t) {
                Toasty.error(getActivity(), t.getMessage(), Toasty.LENGTH_SHORT).show();
            }
        });
    }

    private void showProgressDialog(String title) {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage(title);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    private void getData() {
        judul = edtJudul.getText().toString();
        laporan = edtLapran.getText().toString();
        tgl_lapor = tvDate.getText().toString();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.btn_send, R.id.ib_date})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_send:
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Are you sure want send this report ?");
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getData();
                        checkData();
                        clearData();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                break;
            case R.id.ib_date:
                getDate();
                break;
        }
    }

    private void getDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(
                getActivity(),
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                mDateSetListener,
                year,month,day);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

    }

    private void clearData() {
        edtJudul.setText("");
        tvDate.setText("Select Date");
        edtLapran.setText("");
    }

    @OnClick(R.id.ib_date)
    public void onViewClicked() {
    }
}
