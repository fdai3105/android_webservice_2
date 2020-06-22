package com.example.doan_android_2020._general.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.doan_android_2020.R;

import java.util.List;

public class BannersAdapter extends RecyclerView.Adapter<BannersAdapter.ViewHolder> {
    private Context context;
    private List<String> bannerUlrs;

    public BannersAdapter(Context context, List<String> bannerUlrs) {
        this.context = context;
        this.bannerUlrs = bannerUlrs;
    }

    @NonNull
    @Override
    public BannersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View bannersView = inflater.inflate(R.layout.item_banner, parent, false);
        ViewHolder viewHolder = new ViewHolder(bannersView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BannersAdapter.ViewHolder holder, int position) {
        String url = bannerUlrs.get(position % bannerUlrs.size());
        Glide.with(context).load(url).centerCrop().into(holder.ivBanner);
    }

    @Override
    public int getItemCount() {
        //infinity loop
        return Integer.MAX_VALUE;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivBanner;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivBanner = itemView.findViewById(R.id.ivBanner);
        }
    }
}
