package com.sxjs.jd.composition.main.homefragment;


import com.sxjs.common.base.baseadapter.entity.MultiItemEntity;
import com.sxjs.jd.data.Constant;

/**
 * Created by admin on 2017/2/22.
 */
public class MultipleItem implements  MultiItemEntity {
    private int positon = -1;
    private int spanSize;

    public int getSpanSize() {
        return 1;

    }

    @Override
    public int getItemType() {
        if (positon == 0){
            return Constant.TYPE_TOP_BANNER;
        }else if (positon < 11){
            return Constant.TYPE_ICON_LIST;
        }else if (positon == 11){
            return Constant.TYPE_SHOW_EVENT_3;
        }else if (positon == 12){
            return Constant.TYPE_FIND_GOOD_STUFF;
        }else if (positon == 13){
            return Constant.TYPE_WIDTH_PROPORTION_211;
        }else if (14<=positon && positon < 16){
            return Constant.TYPE_NEW_USER;
        }else if (positon == 16){
            return Constant.TYPE_JD_BULLETIN;
        }else if (17<=positon && positon <= 20){
            return Constant.TYPE_JD_SPIKE_HEADER;
        }else if (21<=positon && positon <= 24){
            return Constant.TYPE_JD_SPIKE_CONTENT;
        }else if (positon == 25){
            return Constant.TYPE_JD_BULLETIN;
        }else {
            return Constant.TYPE_NEW_USER;
        }

    }

    public MultipleItem(int positon) {
        this.positon = positon;
    }

    public int getPositon() {
        return positon;
    }
}
