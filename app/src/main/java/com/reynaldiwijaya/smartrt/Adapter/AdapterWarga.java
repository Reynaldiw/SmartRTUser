package com.reynaldiwijaya.smartrt.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.reynaldiwijaya.smartrt.Helper.Constant;
import com.reynaldiwijaya.smartrt.R;
import com.reynaldiwijaya.smartrt.model.Warga.UserItem;
import com.reynaldiwijaya.smartrt.ui.Warga.DetailWargaActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterWarga extends RecyclerView.Adapter<AdapterWarga.ViewHolder> {
    private Bundle bundle;

    private final Context context;
    private final List<UserItem> userItemList;

    public AdapterWarga(Context context, List<UserItem> userItemList) {
        this.context = context;
        this.userItemList = userItemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_warga, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final UserItem userItem = userItemList.get(i);

        viewHolder.tvName.setText(userItem.getNamaLengkap());
        viewHolder.tvStatus.setText(userItem.getLevel());

        RequestOptions options = new RequestOptions().error(R.drawable.avatar).placeholder(R.drawable.avatar);

        Glide.with(context)
                .load(Constant.IMAGE_USER_URL + userItem.getFoto())
                .apply(options)
                .into(viewHolder.imgProfile);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle = new Bundle();
                final String user_id = userItem.getIdUser();
                bundle.putString(Constant.NAMA, userItem.getNamaLengkap());
                bundle.putString(Constant.ID_USER, user_id);
                bundle.putString(Constant.NO_KTP, userItem.getNoKtp());
                bundle.putString(Constant.ALAMAT, userItem.getAlamat());
                bundle.putString(Constant.STATUS, userItem.getStatus());
                bundle.putString(Constant.DATE, userItem.getTglLahir());
                bundle.putString(Constant.JENKEL, userItem.getJenkel());
                bundle.putString(Constant.PROFESI, userItem.getProfesi());
                bundle.putString(Constant.NO_TLP, userItem.getNoTlp());
                bundle.putString(Constant.EMAIL, userItem.getEmail());
                bundle.putString(Constant.FOTO, userItem.getFoto());
                bundle.putString(Constant.LEVEL, userItem.getLevel());

                context.startActivity(new Intent(context, DetailWargaActivity.class).putExtras(bundle));
            }
        });

    }

    @Override
    public int getItemCount() {
        return userItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_profile)
        CircleImageView imgProfile;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_status)
        TextView tvStatus;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
