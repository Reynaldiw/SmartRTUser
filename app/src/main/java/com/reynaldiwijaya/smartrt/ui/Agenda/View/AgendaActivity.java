package com.reynaldiwijaya.smartrt.ui.Agenda.View;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.reynaldiwijaya.smartrt.Adapter.AdapterAgenda;
import com.reynaldiwijaya.smartrt.R;
import com.reynaldiwijaya.smartrt.model.Agenda.AgendaItem;
import com.reynaldiwijaya.smartrt.ui.Agenda.Presenter.AgendaContract;
import com.reynaldiwijaya.smartrt.ui.Agenda.Presenter.AgendaPresenter;
import com.reynaldiwijaya.smartrt.ui.Agenda.Presenter.GetAgendaContract;
import com.reynaldiwijaya.smartrt.ui.Agenda.Presenter.GetAgendaPresenter;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

public class AgendaActivity extends AppCompatActivity implements GetAgendaContract.View {
    @BindView(R.id.shimmer_view_container)
    ShimmerFrameLayout shimmerViewContainer;
    @BindView(R.id.rv_agenda)
    RecyclerView rvAgenda;
    @BindView(R.id.fab)
    FloatingActionButton fab;

//    private Dialog dialog;
//    private TextInputEditText edtJudul, edtContent, edtTempat;
//    private ImageButton ibDate;
//    private Button btnSend, btnCancel;
//    private TextView tvDate;

    private final GetAgendaPresenter getAgendaPresenter = new GetAgendaPresenter(this);
//    private final AgendaPresenter agendaPresenter = new AgendaPresenter(this);
//    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);
        ButterKnife.bind(this);

//        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                month = month + 1;
//                String date = year + "-" + month + "-" + dayOfMonth;
//                tvDate.setText(date);
//            }
//        };
    }

//    private void getDate() {
//        Calendar cal = Calendar.getInstance();
//        int year = cal.get(Calendar.YEAR);
//        int month = cal.get(Calendar.MONTH);
//        int day = cal.get(Calendar.DAY_OF_MONTH);
//
//        DatePickerDialog dialog = new DatePickerDialog(
//                this,
//                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
//                mDateSetListener,
//                year, month, day);
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        dialog.show();
//
//    }

    @Override
    public void onResume() {
        super.onResume();
        shimmerViewContainer.startShimmerAnimation();
        getAgendaPresenter.getDataAgenda();
    }

    @Override
    public void onPause() {
        super.onPause();
        shimmerViewContainer.stopShimmerAnimation();
    }

    @Override
    public void showProgress() {
        shimmerViewContainer.startShimmerAnimation();
    }

    @Override
    public void hideProgress() {
        shimmerViewContainer.stopShimmerAnimation();
        shimmerViewContainer.setVisibility(View.GONE);
    }

    @Override
    public void showFailureMessage(String msg) {
        Toasty.error(this, msg, Toast.LENGTH_SHORT).show();
    }

//    @Override
//    public void showSuccesMessage(String msg) {
//        Toasty.success(this, msg, Toasty.LENGTH_SHORT).show();
//    }

    @Override
    public void showDataAgenda(List<AgendaItem> agendaItemList) {
        rvAgenda.setVisibility(View.VISIBLE);
        rvAgenda.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        rvAgenda.setAdapter(new AdapterAgenda(this, agendaItemList));
    }


    @OnClick(R.id.fab)
    public void onViewClicked() {

        startActivity(new Intent(this, TambahAgendaActivity.class));

//        dialog = new Dialog(AgendaActivity.this);
//        dialog.setContentView(R.layout.activity_tambah_agenda);
//        dialog.setCancelable(true);
//        dialog.setCanceledOnTouchOutside(false);
//
//        edtJudul = dialog.findViewById(R.id.edt_judul);
//        edtContent = dialog.findViewById(R.id.edt_content);
//        edtTempat = dialog.findViewById(R.id.edt_tempat);
//        btnSend = dialog.findViewById(R.id.btn_send);
//        btnCancel = dialog.findViewById(R.id.btn_cancel);
//        ibDate = dialog.findViewById(R.id.ib_date);
//        tvDate = dialog.findViewById(R.id.tv_date);
//
//        btnSend.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(AgendaActivity.this);
//                builder.setMessage(getString(R.string.keterangan_agenda));
//                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        agendaPresenter.postAgenda(edtJudul.getText().toString(),
//                                edtContent.getText().toString(),
//                                edtTempat.getText().toString(),
//                                tvDate.getText().toString(), 0);
//                    }
//                });
//                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
//                AlertDialog alertDialog = builder.create();
//                alertDialog.show();
//            }
//        });
//
//        btnCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });
//
//        ibDate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getDate();
//            }
//        });
//
//        dialog.show();
//
    }
}
