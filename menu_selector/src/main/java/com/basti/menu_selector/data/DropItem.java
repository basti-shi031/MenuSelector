package com.basti.menu_selector.data;

/**
 * 下拉菜单item
 *
 * Created by Boateng17 on 2017/1/28.
 */

public class DropItem {

    long id;
    String dropItem;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDropItem() {
        return dropItem;
    }

    public void setDropItem(String dropItem) {
        this.dropItem = dropItem;
    }

    public DropItem() {

    }

    public DropItem(long id, String dropItem) {

        this.id = id;
        this.dropItem = dropItem;
    }
}
