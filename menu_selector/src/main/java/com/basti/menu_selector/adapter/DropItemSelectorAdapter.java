package com.basti.menu_selector.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.basti.menu_selector.R;
import com.basti.menu_selector.data.DropItem;
import com.basti.menu_selector.mInterface.OnItemClickListener;

import java.util.List;

/**
 * Created by Boateng17 on 2017/1/28.
 */

public class DropItemSelectorAdapter extends RecyclerView.Adapter<DropItemSelectorAdapter.DropItemSelectViewHolder> {

    List<DropItem> dropItemList;
    OnItemClickListener onItemClickListener;

    public DropItemSelectorAdapter(List<DropItem> dropItemList) {
        this.dropItemList = dropItemList;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public DropItemSelectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_drop_item_rv, parent, false);
        return new DropItemSelectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final DropItemSelectViewHolder holder, int position) {
        DropItem dropItem = dropItemList.get(position);
        holder.dropItemTv.setText(dropItem.getDropItem());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.onClick(holder.getAdapterPosition(), view);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dropItemList == null ? 0 : dropItemList.size();
    }

    class DropItemSelectViewHolder extends RecyclerView.ViewHolder {
        TextView dropItemTv;

        public DropItemSelectViewHolder(View itemView) {
            super(itemView);
            dropItemTv = (TextView) itemView.findViewById(R.id.drop_item_tv);
        }
    }
}
