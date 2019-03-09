package com.reynaldiwijaya.smartrt.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.reynaldiwijaya.smartrt.R;
import com.reynaldiwijaya.smartrt.model.Laporan.LaporanItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterReport extends RecyclerView.Adapter<AdapterReport.ViewHolder> {

    private TextView tvJudul, tvContent, tvDate, tvReply;

    private Context context;
    private List<LaporanItem> laporanItemList;

    public AdapterReport(Context context, List<LaporanItem> laporanItemList) {
        this.context = context;
        this.laporanItemList = laporanItemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_report, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        viewHolder.tvJudul.setText(laporanItemList.get(i).getJudul());
        viewHolder.tvContent.setText(laporanItemList.get(i).getLaporan());
        viewHolder.tvDate.setText(laporanItemList.get(i).getTglLapor());

        ColorGenerator colorGenerator = ColorGenerator.MATERIAL;
        int color = colorGenerator.getRandomColor();
        viewHolder.cvReport.setCardBackgroundColor(color);

                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Dialog dialog = new Dialog(context);
                        dialog.setContentView(R.layout.activity_detail_report);
                        dialog.setCanceledOnTouchOutside(true);
                        dialog.setCancelable(true);

                        tvJudul = dialog.findViewById(R.id.tv_judul);
                        tvContent = dialog.findViewById(R.id.tv_content);
                        tvDate = dialog.findViewById(R.id.tv_date);
                        tvReply = dialog.findViewById(R.id.tv_reply);

                        tvJudul.setText(laporanItemList.get(i).getJudul());
                        tvContent.setText(laporanItemList.get(i).getLaporan());
                        tvDate.setText(laporanItemList.get(i).getTglLapor());
                        tvReply.setText(laporanItemList.get(i).getReply());

                        dialog.show();


                    }
                });
    }

    @Override
    public int getItemCount() {
        return laporanItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_judul)
        TextView tvJudul;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.tv_date)
        TextView tvDate;
        @BindView(R.id.cv_report)
        CardView cvReport;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
