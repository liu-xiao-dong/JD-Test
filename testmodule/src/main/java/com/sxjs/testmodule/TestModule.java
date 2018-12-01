package com.sxjs.testmodule;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.template.IProvider;

/**
 * @author liuxiaodong
 * @date 2018/12/1
 * @description
 */
@Route(path = "/TestModule/TestModule", name = "模块初始化")
public class TestModule implements IProvider {
    private static final String TAG = "TestModule";
    @Override
    public void init(Context context) {
        Log.e(TAG, "TestModule调用了初始化" );
    }
}
