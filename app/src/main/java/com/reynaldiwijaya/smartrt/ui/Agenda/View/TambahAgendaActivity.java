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
import android.widget.Toast;

import com.reynaldiwijaya.smartrt.Helper.SessionManager;
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
    @BindView(R.id.btn_send)
    Button btnSend;
    @BindView(R.id.btn_cancel)
    Button btnCancel;
    @BindView(R.id.edt_date)
    TextInputEditText edtDate;

    private ProgressDialog progressDialog;
    private final AgendaPresenter agendaPresenter = new AgendaPresenter(this);
    private String judul, content, tempat, date, konfirmasi;
    private SessionManager sm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_agenda);
        ButterKnife.bind(this);

        sm = new SessionManager(this);

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
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
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

    @OnClick({R.id.btn_send, R.id.btn_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_send:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(getString(R.string.keterangan_agenda));
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getData();
                        agendaPresenter.postAgenda(judul, content, tempat, date, konfirmasi);
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
        }
    }

    private void getData() {
        judul = edtJudul.getText().toString();
        content = edtContent.getText().toString();
        tempat = edtTempat.getText().toString();
        date = edtDate.getText().toString();

        if (sm.getKonfirmasi().equals("2")) {
            konfirmasi = "1";
        } else {
            konfirmasi = "0";
        }
    }
}
