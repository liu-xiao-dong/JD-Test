package com.sxjs.jd.composition.main.classificationfragment;

import com.sxjs.jd.MainDataManager;
import com.sxjs.jd.composition.BasePresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by admin on 2017/3/22.
 */

public class ClassificationPresenter extends BasePresenter implements ClassificationContract.Presenter{
    private MainDataManager mDataManager;

    private ClassificationContract.View mClassificationView;
    @Inject
    public ClassificationPresenter(MainDataManager mDataManager, ClassificationContract.View view) {
        this.mDataManager = mDataManager;
        this.mClassificationView = view;

    }

    @Override
    public void getTypeOfNameData() {
        List<String> typeOfNameData = mDataManager.getTypeOfNameData();
        mClassificationView.setTypeOfNameData(typeOfNameData);
    }

    @Override
    public void getTypeIconsData() {

    }
}
