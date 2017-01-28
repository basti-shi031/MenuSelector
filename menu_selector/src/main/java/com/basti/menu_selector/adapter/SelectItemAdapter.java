package com.basti.menu_selector.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.basti.menu_selector.R;
import com.basti.menu_selector.data.SelectItem;
import com.basti.menu_selector.mInterface.OnItemClickListener;

import java.util.List;

/**
 * Created by Boateng17 on 2017/1/28.
 */
public class SelectItemAdapter extends RecyclerView.Adapter<SelectItemAdapter.SelectButtonViewHolder> {

    private List<SelectItem> selectItemList;
    private OnItemClickListener onItemClickListener;

    public SelectItemAdapter(List<SelectItem> selectItemList) {
        this.selectItemList = selectItemList;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public SelectButtonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_select_button_rv, parent, false);
        return new SelectButtonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SelectButtonViewHolder holder, final int position) {
        SelectItem selectItem = selectItemList.get(position);
        holder.selectTextView.setText(selectItem.getSelectItem());

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
        return selectItemList == null ? 0 : selectItemList.size();
    }

    class SelectButtonViewHolder extends RecyclerView.ViewHolder {

        private TextView selectTextView;

        public SelectButtonViewHolder(View itemView) {
            super(itemView);
            selectTextView = (TextView) itemView.findViewById(R.id.select_tv);
        }
    }

}
