package com.sxjs.jd.composition.main.findfragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by admin on 2017/3/12.
 */
@Module
public class FindPresenterModule {
    private FindContract.View view;

    public FindPresenterModule(FindContract.View  view) {
        this.view = view;
    }

    @Provides
    FindContract.View providerMainContractView(){
        return view;
    }
}
