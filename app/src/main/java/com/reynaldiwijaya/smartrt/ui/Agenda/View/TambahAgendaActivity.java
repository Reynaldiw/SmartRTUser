package com.reynaldiwijaya.smartrt.ui.Agenda.View;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.reynaldiwijaya.smartrt.R;
import com.reynaldiwijaya.smartrt.ui.Agenda.Presenter.AgendaContract;
import com.reynaldiwijaya.smartrt.ui.Agenda.Presenter.AgendaPresenter;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

public class TambahAgendaActivity extends AppCompatActivity implements AgendaContract.View {

    @BindView(R.id.edt_judul)
    TextInputEditText edtJudul;
    @BindView(R.id.edt_content)
    TextInputEditText edtContent;
    @BindView(R.id.edt_tempat)
    TextInputEditText edtTempat;
    @BindView(R.id.ib_date)
    ImageButton ibDate;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.btn_send)
    Button btnSend;
    @BindView(R.id.btn_cancel)
    Button btnCancel;

    private ProgressDialog progressDialog;
    private final AgendaPresenter agendaPresenter = new AgendaPresenter(this);
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_agenda);
        ButterKnife.bind(this);

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = year + "-" + month + "-" + dayOfMonth;
                tvDate.setText(date);
            }
        };


    }

    private void getDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(
                this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                mDateSetListener,
                year, month, day);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

    }

    @Override
    public void showProgress() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading ... ");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void showFailureMessage(String msg) {
        Toasty.error(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSuccesMessage(String msg) {
        Toasty.success(this, msg, Toast.LENGTH_SHORT).show();
        finish();

    }

    @Override
    public void clearData() {
        edtJudul.setText("");
        edtTempat.setText("");
        edtContent.setText("");
        tvDate.setText("");
    }

    @OnClick({R.id.btn_send, R.id.btn_cancel, R.id.ib_date})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_send:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(getString(R.string.keterangan_agenda));
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        agendaPresenter.postAgenda(edtJudul.getText().toString(),
                                edtContent.getText().toString(),
                                edtTempat.getText().toString(),
                                tvDate.getText().toString(), 0);
                        finish();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

                break;
            case R.id.btn_cancel:
                finish();
                break;
            case R.id.ib_date:
                getDate();
                break;
        }
    }
}
