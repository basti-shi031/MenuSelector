package com.basti.menu_selector.data;

/**
 * 顶部选择菜单Module
 * <p>
 * Created by Boateng17 on 2017/1/28.
 */

public class SelectItem {

    long id;
    String selectItem;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSelectItem() {
        return selectItem;
    }

    public void setSelectItem(String selectItem) {
        this.selectItem = selectItem;
    }

    public SelectItem(long id, String selectItem) {

        this.id = id;
        this.selectItem = selectItem;
    }
}
