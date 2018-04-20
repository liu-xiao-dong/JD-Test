package com.sxjs.jd.composition.main.classificationfragment;

import com.sxjs.jd.MainDataManager;

import dagger.Module;
import dagger.Provides;

/**
 * Created by admin on 2017/3/12.
 */
@Module
public class ClassificationPresenterModule {
    private ClassificationContract.View  view;

    private MainDataManager mainDataManager;

    public ClassificationPresenterModule(ClassificationContract.View  view, MainDataManager mainDataManager) {
        this.view = view;
        this.mainDataManager = mainDataManager;
    }

    @Provides
    ClassificationContract.View providerMainContractView(){
        return view;
    }
    @Provides
    MainDataManager providerMainDataManager(){
        return mainDataManager;
    }
}
