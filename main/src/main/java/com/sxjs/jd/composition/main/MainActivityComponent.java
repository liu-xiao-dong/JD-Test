package com.sxjs.jd.composition.main;

import com.sxjs.common.AppComponent;
import com.sxjs.common.PerActivity;

import dagger.Component;

/**
 * Created by admin on 2017/3/12.
 */
@PerActivity
@Component(dependencies = AppComponent.class , modules = MainPresenterModule .class)
public interface MainActivityComponent {
    void inject(MainActivity activity);
}
