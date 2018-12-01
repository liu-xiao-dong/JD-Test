package com.example.app;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.launcher.ARouter;
import com.sxjs.common.base.BaseActivity;

/**
 * @author liuxiaodong
 * @date 2018/12/1
 * @description
 */
public class SplashActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(BuildConfig.DEBUG && !BuildConfig.DEBUG_ALL){
                    ARouter.getInstance().build("/app/ModuleTestActivity").greenChannel().navigation(SplashActivity.this);
                }else{
                    ARouter.getInstance().build("/main/MainActivity").greenChannel().navigation(SplashActivity.this);
                }
                finish();
            }
        },2000);
    }
}
