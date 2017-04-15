package com.sxjs.common.arouter;

import android.content.Context;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.DegradeService;

/**
 * @author：admin on 2017/4/10 15:03.
 */
// 实现DegradeService接口，并加上一个Path内容任意的注解即可
@Route(path = "/xxx/xxx")
public class DegradeServiceImpl implements DegradeService {
    @Override
    public void onLost(Context context, Postcard postcard) {
        Toast.makeText(context, "该功能暂不可用", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void init(Context context) {

    }
}
