package com.example.doan_android_2020._general.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.doan_android_2020.R;
import com.example.doan_android_2020._general.models.Item;
import com.example.doan_android_2020.activitis.ItemDetailActivity;

import java.util.ArrayList;

import jp.wasabeef.glide.transformations.MaskTransformation;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Item> items;

    public ItemAdapter(Context context, ArrayList<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View itemsView = inflater.inflate(R.layout.item_items, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemsView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ViewHolder holder, final int position) {
        Item item = items.get(position);

        Glide.with(context).load(item.getImageItem()).placeholder(R.drawable.placeholder)
                .fitCenter().apply(new RequestOptions().override(360)).
                into(holder.item_items_image);
        if (!item.getItemPromotion().isItemPromotionFreeship()) {
            Glide.with(context).load(R.drawable.watermark_freeship).into(holder.item_items_watermark);
        } else if (!item.getItemPromotion().isItemPromotionBigSale()) {
            Glide.with(context).load(R.drawable.watermark_bigsale).into(holder.item_items_watermark);
        } else if (!item.getItemPromotion().isItemPromotionBestSeller()) {
            Glide.with(context).load(R.drawable.watermark_bestsellers).into(holder.item_items_watermark);
        }

        if (!item.getNameItem().equals("")) {
            holder.item_items_name.setText(item.getNameItem() + "");
        }
        if (!String.valueOf(item.getPriceItem()).equals("")) {
            holder.item_items_price.setText(item.getPriceItem() + " ₫");
        }
        holder.setOnItemClick(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                Intent intent = new Intent(context, ItemDetailActivity.class);

                intent.putExtra("ItemIds",items.get(position).getIdItem());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public interface ItemClickListener {
        void onClick(View view, int position,boolean isLongClick);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ItemClickListener onItemClick;

        ImageView item_items_image;
        ImageView item_items_watermark;
        TextView item_items_name;
        TextView item_items_price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView = itemView.findViewById(R.id.itemView);
            item_items_image = itemView.findViewById(R.id.item_items_image);
            item_items_watermark = itemView.findViewById(R.id.item_items_watermark);
            item_items_name = itemView.findViewById(R.id.item_items_name);
            item_items_price = itemView.findViewById(R.id.item_items_price);

            itemView.setOnClickListener(this);
        }

        public void setOnItemClick(ItemClickListener onItemClick) {
            this.onItemClick = onItemClick;
        }

        @Override
        public void onClick(View v) {
            onItemClick.onClick(v,getAdapterPosition(),false);
        }
    }
}
