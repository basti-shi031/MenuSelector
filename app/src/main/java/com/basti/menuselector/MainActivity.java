package com.basti.menuselector;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.basti.menu_selector.MenuSelector;
import com.basti.menu_selector.data.DropItem;
import com.basti.menu_selector.data.SelectItem;
import com.basti.menu_selector.mInterface.OnMenuSelectListener;

public class MainActivity extends AppCompatActivity {

    MenuSelector menuSelector;
    Animation dropdown_in, dropdown_out, dropdown_mask_out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initAnim();

        menuSelector = (MenuSelector) findViewById(R.id.menuSelector);

        SelectItem selectItem1 = new SelectItem(0, "标签1");
        SelectItem selectItem2 = new SelectItem(0, "标签2");
        SelectItem selectItem3 = new SelectItem(0, "标签3");
        SelectItem selectItem4 = new SelectItem(0, "标签4");
        SelectItem selectItem5 = new SelectItem(0, "标签5");
        menuSelector.addSelectItem(selectItem1);
        menuSelector.addSelectItem(selectItem2);
        menuSelector.addSelectItem(selectItem3);
        menuSelector.addSelectItem(selectItem4);
        menuSelector.addSelectItem(selectItem5);

        menuSelector.setOnMenuSelectListener(new OnMenuSelectListener() {
            @Override
            public void onItemSelected(int position) {
                menuSelector.clearDropItem();
                randomCreateDropItem((position + 1) * 3);
                menuSelector.setDropInAnimation(dropdown_in);
            }

            @Override
            public void onDropItemSelected(int position) {
                menuSelector.setDropOutAnimation(dropdown_out);
                menuSelector.setDropOutAnimation(dropdown_mask_out);
            }
        });


    }

    private void randomCreateDropItem(int count) {

        int randomCount = (int) (Math.random() * 10) + 1;
        for (int i = 0; i < count; i++) {
            menuSelector.addDropItem(new DropItem(i, "选项".concat(String.valueOf(i))));
        }
    }

    //初始化动画
    private void initAnim() {
        dropdown_in = AnimationUtils.loadAnimation(this, com.basti.menu_selector.R.anim.dropdown_in);
        dropdown_out = AnimationUtils.loadAnimation(this, com.basti.menu_selector.R.anim.dropdown_out);
        dropdown_mask_out = AnimationUtils.loadAnimation(this, com.basti.menu_selector.R.anim.dropdown_mask_out);
    }
}
