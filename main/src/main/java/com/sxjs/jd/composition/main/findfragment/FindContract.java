package com.sxjs.jd.composition.main.findfragment;

import com.sxjs.jd.entities.FindsBean;

/**
 * @author admin
 */

public interface FindContract {
    interface View {
        void setFindData(FindsBean find);
        void setMoreData(FindsBean find);
    }

    interface Presenter {
        void getFindData();
        void getMoreFindData();
    }

}