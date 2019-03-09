package com.reynaldiwijaya.smartrt.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.reynaldiwijaya.smartrt.Helper.Constant;
import com.reynaldiwijaya.smartrt.R;
import com.reynaldiwijaya.smartrt.model.Informasi.NewsItem;
import com.reynaldiwijaya.smartrt.ui.Informasi.InformasiDetailActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AdapterInformasi extends RecyclerView.Adapter<AdapterInformasi.ViewHolder> {
    private Context context;
    private List<NewsItem> newsItemList;

    public AdapterInformasi(Context context, List<NewsItem> newsItemList) {
        this.context = context;
        this.newsItemList = newsItemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_news, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final NewsItem newsItem = newsItemList.get(i);

        viewHolder.tvJudul.setText(newsItem.getJudul());
        viewHolder.tvSubSubtitle.setText(newsItem.getContent());
        viewHolder.tvPenulis.setText(newsItem.getNamaLengkap());

        RequestOptions requestOptions = new RequestOptions().placeholder(R.drawable.ic_broken_image).error(R.drawable.ic_broken_image);
        Glide.with(context)
                .load(Constant.IMAGE_INFORMASI_URL + newsItem.getFotoInformasi())
                .apply(requestOptions)
                .into(viewHolder.imgBerita);
        Log.d("FOTO_URL", newsItem.getFotoInformasi());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Bundle bundle = new Bundle();
                bundle.putString(Constant.JUDUL, newsItem.getJudul());
                bundle.putString(Constant.FOTO, newsItem.getFoto());
                bundle.putString(Constant.FOTO_INFORMASI, newsItem.getFotoInformasi());
                bundle.putString(Constant.CONTENT, newsItem.getContent());
                bundle.putString(Constant.NO_TLP, newsItem.getNoTlp());
                bundle.putString(Constant.DATE, newsItem.getTglNulis());
                bundle.putString(Constant.NAMA, newsItem.getNamaLengkap());

                context.startActivity(new Intent(context, InformasiDetailActivity.class).putExtras(bundle));
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsItemList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_berita)
        ImageView imgBerita;
        @BindView(R.id.tv_judul)
        TextView tvJudul;
        @BindView(R.id.tvSubSubtitle)
        TextView tvSubSubtitle;
        @BindView(R.id.tv_penulis)
        TextView tvPenulis;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
