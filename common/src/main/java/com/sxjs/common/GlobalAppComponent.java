package com.sxjs.common;

import android.content.Context;

/**
 * @author：admin on 2017/4/15 15:26.
 *
 */

public class GlobalAppComponent {
    private static AppComponent mAppComponent;

    /**
     * 初始化全局AppComponent
     * @param context applicationContext
     */
    public static void init(Context context){
        if(mAppComponent == null){
            mAppComponent = DaggerAppComponent.builder()
                    .applicationModule(new ApplicationModule(context.getApplicationContext()))
                    .build();
        }
    }

    public static AppComponent getAppComponent() {
        return mAppComponent;
    }
}
