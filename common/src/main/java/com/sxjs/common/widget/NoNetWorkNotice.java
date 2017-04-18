package com.sxjs.common.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.provider.Settings;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import com.sxjs.common.R;

/**
 * @author：admin on 2017/4/11 17:26.
 */

public class NoNetWorkNotice {
    private WindowManager wdm;

    private View mView;
    private WindowManager.LayoutParams params;
    private boolean isShowing;
    private NoNetWorkNotice(final Activity context){

        wdm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        mView = View.inflate(context, R.layout.no_net_worke_layout , null);
        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.ACTION_SETTINGS);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.getApplicationContext().startActivity(intent);


            }
        });
        //设置LayoutParams(全局变量）相关参数
        params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                PixelFormat.TRANSLUCENT);

        params.gravity = Gravity.CENTER_HORIZONTAL | Gravity.TOP;


    }

    public  static NoNetWorkNotice getInstance(Activity context){
        return context == null ? null : new NoNetWorkNotice(context);
    }

    public void show(){
        isShowing = true;
        wdm.addView(mView, params);
    }

    public void cancel(){
        isShowing = false;
        //wdm.removeView(mView); //此句依然会导致MainActivity退出时导致泄漏窗体 所以使用下一句
        wdm.removeViewImmediate(mView);
        mView = null;
    }

    public boolean isShowing() {
        return isShowing;
    }


}
