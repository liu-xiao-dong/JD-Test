package com.sxjs.jd.composition.login;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.sxjs.jd.R;
import com.sxjs.common.base.BaseActivity;

/**
 * @author：admin on 2017/4/10 15:23.
 */
@Route(path = "/test/login")
public class LoginActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
    }
}
