package com.sxjs.jd.composition.main.homefragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by admin on 2017/3/12.
 */
@Module
public class HomePresenterModule {
    private HomeContract.View view;

    public HomePresenterModule(HomeContract.View  view) {
        this.view = view;
    }

    @Provides
    HomeContract.View providerMainContractView(){
        return view;
    }
}
