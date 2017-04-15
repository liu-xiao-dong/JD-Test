package com.sxjs.common.widget.percentlayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.sxjs.common.R;

/**
 * Created by zhy on 15/6/30.
 */
public class PercentLinearLayout extends LinearLayout {

    /**
     * 纵横比测量
     */
    private final PercentAspectRatioMeasure.Spec mMeasureSpec = new PercentAspectRatioMeasure.Spec();
    private PercentLayoutHelper mPercentLayoutHelper;
    /**
     * 宽高显示比例
     */
    private float mAspectRatio = -1f;

    public PercentLinearLayout(Context context) {
        this(context,null);
    }

    public PercentLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initCustomAttrs(context, attrs);
        mPercentLayoutHelper = new PercentLayoutHelper(this);
    }

    private void initCustomAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PercentLayout_Layout);
        mAspectRatio = typedArray.getFloat(R.styleable.PercentLayout_Layout_layout_selfAspectRatio,-1f);
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mPercentLayoutHelper.adjustChildren(widthMeasureSpec, heightMeasureSpec);
        if(mAspectRatio == -1f){
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
        else{
            changeAspectRatio(widthMeasureSpec, heightMeasureSpec);
            super.onMeasure(mMeasureSpec.width, mMeasureSpec.height);
        }
        if (mPercentLayoutHelper.handleMeasuredStateTooSmall()) {
            if(mAspectRatio == -1f){
                super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            }
            else{
                changeAspectRatio(widthMeasureSpec, heightMeasureSpec);
                super.onMeasure(mMeasureSpec.width, mMeasureSpec.height);
            }
        }
    }

    private void changeAspectRatio(int widthMeasureSpec, int heightMeasureSpec) {
        mMeasureSpec.width = widthMeasureSpec;
        mMeasureSpec.height = heightMeasureSpec;
        PercentAspectRatioMeasure.updateMeasureSpec(
                mMeasureSpec,
                mAspectRatio,
                getLayoutParams(),
                getPaddingLeft() + getPaddingRight(),
                getPaddingTop() + getPaddingBottom());
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        mPercentLayoutHelper.restoreOriginalParams();
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }


    public static class LayoutParams extends LinearLayout.LayoutParams
            implements PercentLayoutHelper.PercentLayoutParams {
        private PercentLayoutHelper.PercentLayoutInfo mPercentLayoutInfo;

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            mPercentLayoutInfo = PercentLayoutHelper.getPercentLayoutInfo(c, attrs);
        }

        @Override
        public PercentLayoutHelper.PercentLayoutInfo getPercentLayoutInfo() {
            return mPercentLayoutInfo;
        }

        @Override
        protected void setBaseAttributes(TypedArray a, int widthAttr, int heightAttr) {
            PercentLayoutHelper.fetchWidthAndHeight(this, a, widthAttr, heightAttr);
        }

        public LayoutParams(int width, int height) {
            super(width, height);
        }


        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }

        public LayoutParams(MarginLayoutParams source) {
            super(source);
        }

    }

}
