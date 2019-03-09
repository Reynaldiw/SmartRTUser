package com.reynaldiwijaya.smartrt.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.reynaldiwijaya.smartrt.Helper.Constant;
import com.reynaldiwijaya.smartrt.R;
import com.reynaldiwijaya.smartrt.model.NewsIndonesia.ArticlesItem;
import com.reynaldiwijaya.smartrt.ui.NewsIndonesia.DetailNewsActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterNewsIndonesia extends RecyclerView.Adapter<AdapterNewsIndonesia.ViewHolder> {

    private Context context;
    private List<ArticlesItem> articlesItems;

    public AdapterNewsIndonesia(Context context, List<ArticlesItem> articlesItems) {
        this.context = context;
        this.articlesItems = articlesItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_news, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.tvJudul.setText(articlesItems.get(i).getTitle());
        viewHolder.tvSubSubtitle.setText(articlesItems.get(i).getDescription());
        viewHolder.tvPenulis.setText(articlesItems.get(i).getSource().getName());
        RequestOptions options = new RequestOptions().error(R.drawable.ic_broken_image).placeholder(R.drawable.ic_broken_image);
        Glide.with(context)
                .load(articlesItems.get(i).getUrlToImage())
                .into(viewHolder.imgBerita);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArticlesItem articlesItem = new ArticlesItem();
                articlesItem.setTitle(articlesItems.get(i).getTitle());
                articlesItem.setAuthor(articlesItems.get(i).getAuthor());
                articlesItem.setContent(articlesItems.get(i).getContent());
                articlesItem.setPublishedAt(articlesItems.get(i).getPublishedAt());
                articlesItem.setUrlToImage(articlesItems.get(i).getUrlToImage());
                articlesItem.setUrl(articlesItems.get(i).getUrl());

                context.startActivity(new Intent(context, DetailNewsActivity.class).putExtra(Constant.OBJ, articlesItem));
            }
        });

    }

    @Override
    public int getItemCount() {
        return articlesItems.size();
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
