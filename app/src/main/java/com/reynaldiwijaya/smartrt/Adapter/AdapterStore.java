package com.reynaldiwijaya.smartrt.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import com.reynaldiwijaya.smartrt.model.Store.StoreItem;
import com.reynaldiwijaya.smartrt.ui.Store.DetailStoreActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterStore extends RecyclerView.Adapter<AdapterStore.ViewHolder> {
    private Context context;
    private List<StoreItem> storeItemList;

    public AdapterStore(Context context, List<StoreItem> storeItemList) {
        this.context = context;
        this.storeItemList = storeItemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_store, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final StoreItem storeItem = storeItemList.get(i);

        viewHolder.tvNama.setText(storeItem.getNamaToko());

        RequestOptions requestOptions = new RequestOptions().error(R.drawable.ic_broken_image).placeholder(R.drawable.ic_broken_image);

        Glide.with(context)
                .load(Constant.IMAGE_STORE_URL + storeItem.getFotoToko())
                .apply(requestOptions)
                .into(viewHolder.imgStore);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(Constant.NAMA_TOKO, storeItem.getNamaToko());
                bundle.putString(Constant.DESKRIPSI, storeItem.getDeskripsi());
                bundle.putString(Constant.NO_TLP_STORE, storeItem.getNoTlpStore());
                bundle.putString(Constant.ALAMAT, storeItem.getAlamatToko());
                bundle.putString(Constant.NAMA, storeItem.getNamaLengkap());
                bundle.putString(Constant.NO_TLP, storeItem.getNoTlp());
                bundle.putString(Constant.FOTO, storeItem.getFoto());
                bundle.putString(Constant.FOTO_STORE, storeItem.getFotoToko());

                context.startActivity(new Intent(context, DetailStoreActivity.class).putExtras(bundle));
            }
        });
    }

    @Override
    public int getItemCount() {
        return storeItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_nama)
        TextView tvNama;
        @BindView(R.id.img_store)
        ImageView imgStore;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
