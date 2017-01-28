package com.basti.menu_selector;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.LinearLayout;

import com.basti.menu_selector.adapter.DropItemSelectorAdapter;
import com.basti.menu_selector.adapter.SelectItemAdapter;
import com.basti.menu_selector.data.DropItem;
import com.basti.menu_selector.data.SelectItem;
import com.basti.menu_selector.mInterface.OnItemClickListener;
import com.basti.menu_selector.mInterface.OnMenuSelectListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Boateng17 on 2017/1/28.
 */

public class MenuSelector extends LinearLayout {

    int MAX_SELECT_ITEM_COUNT = 3;//顶部菜单最大数量，超过分多行

    RecyclerView selectItemRv, dropdownItemRv;//上部选择菜单和下拉菜单
    View mask;//蒙版

    List<SelectItem> selectItemList;
    List<DropItem> dropItemList;

    SelectItemAdapter selectItemAdapter;
    RecyclerView.Adapter dropItemAdapter;

    OnMenuSelectListener onMenuSelectListener;

    static final int COLLAPSE = 0;
    static final int EXPAND = 1;

    int state = COLLAPSE;
    int selectIndex = 0;//当前选择的顶部菜单Index

    public MenuSelector(Context context) {
        super(context);
        init();
    }

    public MenuSelector(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MenuSelector(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOrientation(VERTICAL);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.menu_selector, this, true);
        selectItemRv = (RecyclerView) view.findViewById(R.id.select_button_rv);
        dropdownItemRv = (RecyclerView) view.findViewById(R.id.dropdown_item_rv);
        mask = view.findViewById(R.id.mask);
        initSelectItem();
        initDropItem();
    }

    /**
     * 初始化下拉菜单
     */
    private void initDropItem() {
        dropItemList = new ArrayList<>();
        dropItemAdapter = new DropItemSelectorAdapter(dropItemList);
        dropdownItemRv.setLayoutManager(new LinearLayoutManager(getContext()));
        dropdownItemRv.setAdapter(dropItemAdapter);
        ((DropItemSelectorAdapter) dropItemAdapter).setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(int position, View view) {
                if (onMenuSelectListener != null) {
                    onMenuSelectListener.onDropItemSelected(position);
                }
                mask.setVisibility(GONE);
                state = COLLAPSE;
                clearDropItem();
            }
        });
    }

    /**
     * 初始化顶部菜单
     */
    private void initSelectItem() {
        selectItemList = new ArrayList<>();
        selectItemAdapter = new SelectItemAdapter(selectItemList);
        selectItemRv.setLayoutManager(new GridLayoutManager(getContext(), MAX_SELECT_ITEM_COUNT));
        selectItemRv.setAdapter(selectItemAdapter);
        selectItemAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(int position, View view) {
                /**
                 * 逻辑：
                 *  1. 当前是折叠：展开被点击项
                 *  2. 当前是展开：点击之前被点击项，收回
                 *                  点击其他项，展开
                 */
                switch (state) {
                    case COLLAPSE:
                        //折叠->展开
                        if (onMenuSelectListener != null) {
                            onMenuSelectListener.onItemSelected(position);
                        }
                        mask.setVisibility(VISIBLE);
                        state = EXPAND;
                        selectIndex = position;
                        break;
                    case EXPAND:
                        if (selectIndex == position) {
                            //点击的是之前选择项，收回
                            mask.setVisibility(GONE);
                            clearDropItem();
                            state = COLLAPSE;
                        } else {
                            //点击的是其他项，展开
                            if (onMenuSelectListener != null) {
                                onMenuSelectListener.onItemSelected(position);
                            }
                            mask.setVisibility(VISIBLE);
                            state = EXPAND;
                            selectIndex = position;
                        }

                        break;
                }
            }
        });
    }


    /**
     * 对外方法
     */

    //添加一个顶部菜单Item
    public void addSelectItem(SelectItem selectItem) {
        selectItemList.add(selectItem);
        selectItemAdapter.notifyDataSetChanged();
    }

    //设置点击事件
    public void setOnMenuSelectListener(OnMenuSelectListener onMenuSelectListener) {
        this.onMenuSelectListener = onMenuSelectListener;
    }


    //设置自定义下拉菜单适配器
    public void setDropItemAdapter(RecyclerView.Adapter adapter) {
        dropItemAdapter = adapter;
    }

    //添加下拉菜单
    public void addDropItem(DropItem dropItem) {
        dropItemList.add(dropItem);
        dropItemAdapter.notifyDataSetChanged();
    }

    //清除下拉菜单
    public void clearDropItem() {
        int size = dropItemList.size();
        dropItemList.clear();
        dropItemAdapter.notifyItemRangeRemoved(0, size);
    }

    //展开下拉菜单动画
    public void setDropInAnimation(Animation animation) {
        dropdownItemRv.clearAnimation();
        dropdownItemRv.startAnimation(animation);
    }

    //收回下拉菜单动画
    public void setDropOutAnimation(Animation animation) {
        dropdownItemRv.clearAnimation();
        dropdownItemRv.startAnimation(animation);
    }

    //蒙版动画
    public void setMaskAnimation(Animation animation) {
        mask.clearAnimation();
        mask.startAnimation(animation);
    }

}
