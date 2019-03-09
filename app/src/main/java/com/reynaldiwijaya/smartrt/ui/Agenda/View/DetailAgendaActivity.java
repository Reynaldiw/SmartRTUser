package com.reynaldiwijaya.smartrt.ui.Agenda.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.reynaldiwijaya.smartrt.Helper.Constant;
import com.reynaldiwijaya.smartrt.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailAgendaActivity extends AppCompatActivity {

    @BindView(R.id.tv_judul)
    TextView tvJudul;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_tempat)
    TextView tvTempat;
    @BindView(R.id.tv_tanggal)
    TextView tvTanggal;

    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_agenda);
        ButterKnife.bind(this);

        bundle = getIntent().getExtras();

        if (bundle != null) {
            setUpList();
        }
    }

    private void setUpList() {
        tvJudul.setText(bundle.getString(Constant.JUDUL));
        tvContent.setText(bundle.getString(Constant.CONTENT));
        tvTanggal.setText(bundle.getString(Constant.TANGGAL));
        tvTempat.setText(bundle.getString(Constant.TEMPAT));
    }
}
