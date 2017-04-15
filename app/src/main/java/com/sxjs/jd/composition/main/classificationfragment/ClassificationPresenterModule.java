package com.sxjs.jd.composition.main.classificationfragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by admin on 2017/3/12.
 */
@Module
public class ClassificationPresenterModule {
    private ClassificationContract.View  view;

    public ClassificationPresenterModule(ClassificationContract.View  view) {
        this.view = view;
    }

    @Provides
    ClassificationContract.View providerMainContractView(){
        return view;
    }
}
