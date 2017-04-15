package com.sxjs.common.widget.headerview;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sxjs.common.R;
import com.sxjs.common.widget.pulltorefresh.PtrFrameLayout;
import com.sxjs.common.widget.pulltorefresh.PtrUIHandler;
import com.sxjs.common.widget.pulltorefresh.indicator.PtrIndicator;


/**
 * Created by admin on 2017/2/23.
 */
public class JDHeaderView extends PtrFrameLayout implements PtrUIHandler {


    private TextView status_text;
    private ImageView ren;
    private ImageView hezi;
    private int viewHeight;
    private ImageView donghua;
    private AnimationDrawable drawable;
    private RefreshDistanceListener listener;
    /**
     * 自开始下拉 0.2倍height内是否执行了缩放，
     */
    private boolean isScale;

    public void setOnRefreshDistanceListener(RefreshDistanceListener listener) {
        this.listener = listener;
    }

    public JDHeaderView(Context context) {
        super(context);
        initView();
    }

    public JDHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public JDHeaderView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    private void initView() {

        View view = View.inflate(this.getContext(), R.layout.jingdongheaderviewlayout,null);
        status_text = (TextView) view.findViewById(R.id.status_test);
        ren = (ImageView) view.findViewById(R.id.ren);
        hezi = (ImageView) view.findViewById(R.id.hezi);
        donghua = (ImageView) view.findViewById(R.id.donghua);
        drawable = (AnimationDrawable) donghua.getDrawable();
        setRatioOfHeaderHeightToRefresh(1.0f);
        setHeaderView(view);
        addPtrUIHandler(this);
    }

    @Override
    public void onUIReset(PtrFrameLayout frame) {
        drawable.stop();
        donghua.setVisibility(View.GONE);
        ren.setVisibility(View.VISIBLE);
        hezi.setVisibility(View.VISIBLE);
    }

    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {

        if(frame.isPullToRefresh()){
            status_text.setText("松开刷新...");
        }else{
            status_text.setText("下拉刷新...");

        }
    }

    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {
        ren.setVisibility(View.GONE);
        hezi.setVisibility(View.GONE);
        donghua.setVisibility(View.VISIBLE);
        drawable.start();
        status_text.setText("更新中...");
    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame) {
    }

    private static final String TAG = "JDHeaderView";
    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
        //ptrIndicator.setRatioOfHeaderHeightToRefresh(1.0f);
        final int mOffsetToRefresh = frame.getOffsetToRefresh();
        final int currentPos = ptrIndicator.getCurrentPosY();
        final int lastPos = ptrIndicator.getLastPosY();
        if(listener != null){
            listener.onPositionChange(currentPos);
        }
        if(viewHeight == 0)
        viewHeight = ptrIndicator.getHeaderHeight();
        float v = currentPos * 1.0f / viewHeight;
        if(v > 1)v= 1;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            //此处防止首次下拉到0.2height时突然缩小
            if(!isScale && v <= 0.2){
                isScale = true;
                setImgScale(0.2f);
            }
            if(v > 0.2){
                setImgScale(v);
            }

        }

        if (currentPos < mOffsetToRefresh && lastPos >= mOffsetToRefresh) {
            if (isUnderTouch && status == PtrFrameLayout.PTR_STATUS_PREPARE) {
                status_text.setText("下拉刷新...");

            }
        } else if (currentPos > mOffsetToRefresh && lastPos <= mOffsetToRefresh) {
            if (isUnderTouch && status == PtrFrameLayout.PTR_STATUS_PREPARE) {
                status_text.setText("松开刷新...");

            }
        }

    }

    private void setImgScale(float v) {
        ren.setScaleY(v);
        ren.setScaleX(v);
        hezi.setScaleY(v);
        hezi.setScaleX(v);
    }

    public interface RefreshDistanceListener{
        void onPositionChange(int currentPosY);
    }


}
