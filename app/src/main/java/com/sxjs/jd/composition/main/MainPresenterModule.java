package com.sxjs.jd.composition.main;

import dagger.Module;
import dagger.Provides;

/**
 * Created by admin on 2017/3/12.
 */
@Module
public class MainPresenterModule {
    private MainContract.View view;

    public MainPresenterModule(MainContract.View view) {
        this.view = view;
    }

    @Provides
    MainContract.View providerMainContractView(){
        return view;
    }
}
