package com.example.app;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.sxjs.common.base.BaseActivity;

/**
 * @author liuxiaodong
 * @date 2018/12/1
 * @description
 */
@Route(path = "/app/ModuleTestActivity")
public class ModuleTestActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_test);

        findViewById(R.id.goMain).setOnClickListener(v -> {
            ARouter.getInstance().build("/main/MainActivity").greenChannel().navigation();
        });
        findViewById(R.id.goTest).setOnClickListener(v -> {
            ARouter.getInstance().build("/test1/activity").greenChannel().navigation();
        });
    }
}
