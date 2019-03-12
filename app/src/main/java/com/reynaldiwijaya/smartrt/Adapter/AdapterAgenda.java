package com.reynaldiwijaya.smartrt.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.reynaldiwijaya.smartrt.Helper.Constant;
import com.reynaldiwijaya.smartrt.R;
import com.reynaldiwijaya.smartrt.model.Agenda.AgendaItem;
import com.reynaldiwijaya.smartrt.ui.Agenda.View.DetailAgendaActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterAgenda extends RecyclerView.Adapter<AdapterAgenda.ViewHolder> {
    private final Context context;
    private List<AgendaItem> agendaItemList;

    public AdapterAgenda(Context context, List<AgendaItem> agendaItemList) {
        this.context = context;
        this.agendaItemList = agendaItemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_agenda, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final AgendaItem agendaItem = agendaItemList.get(i);

        viewHolder.tvJudul.setText(agendaItem.getJudul());
        viewHolder.tvContent.setText(agendaItem.getContent());
        viewHolder.tvTanggal.setText(agendaItem.getTanggal());


        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(Constant.KEY_ID, agendaItem.getIdJudul());
                bundle.putString(Constant.JUDUL, agendaItem.getJudul());
                bundle.putString(Constant.CONTENT, agendaItem.getContent());
                bundle.putString(Constant.TEMPAT, agendaItem.getTempat());
                bundle.putString(Constant.TANGGAL, agendaItem.getTanggal());

                context.startActivity(new Intent(context, DetailAgendaActivity.class).putExtras(bundle));
            }
        });
    }

    @Override
    public int getItemCount() {
        return agendaItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_judul)
        TextView tvJudul;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.tv_tanggal)
        TextView tvTanggal;
        @BindView(R.id.cv_agenda)
        CardView cvAgenda;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
