package com.sxjs.jd.composition.main.classificationfragment;

import java.util.List;

/**
 * Created by admin on 2017/3/12.
 */

public interface ClassificationContract {
 interface View {
    void setTypeOfNameData(List<String> list);

     void setTypeIconsData(String text);

}

 interface Presenter {
    void getTypeOfNameData();

     void getTypeIconsData();
}

}