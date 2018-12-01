package com.sxjs.jd.composition.main;

import java.util.Map;

/**
 * Created by admin on 2017/3/12.
 */

public interface MainContract {
 interface View {
    void setText(String text);

    void showProgressDialogView();

    void hiddenProgressDialogView();
}

 interface Presenter {
    void getText();

    void destory();

    void saveData();

    Map getData();
}

}