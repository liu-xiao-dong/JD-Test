package com.example.app;

import android.app.Application;

import com.example.app_common.CommonModule;

/**
 * @author admin
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CommonModule.init(this);
    }

}
