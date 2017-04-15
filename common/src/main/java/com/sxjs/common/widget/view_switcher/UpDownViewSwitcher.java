package com.sxjs.common.widget.view_switcher;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.LayoutRes;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ViewSwitcher;

import com.sxjs.common.R;

import java.lang.ref.WeakReference;

/**
 * Created by admin on 2017/3/21.
 */

public class UpDownViewSwitcher extends ViewSwitcher {

    private boolean isUpToDown;
    private int animator_duration;
    private int switch_duration;
    private float animator_translate_distance;
    private int index;
    @LayoutRes
    private int resourse;
    private AutoPlayTask mAutoPlayTask;
    private SwitchNextViewListener listener;
    public UpDownViewSwitcher(Context context) {
        this(context, null);
    }

    public UpDownViewSwitcher(Context context, AttributeSet attrs) {
        super(context, attrs);
        parseAttrs(context, attrs);
        init();
    }

    private void parseAttrs(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.UpDownViewSwitcher, 0, 0);
            isUpToDown = typedArray.getBoolean(R.styleable.UpDownViewSwitcher_up_to_down, true);
            animator_duration = typedArray.getInteger(R.styleable.UpDownViewSwitcher_animator_duration, 300);
            switch_duration = typedArray.getInteger(R.styleable.UpDownViewSwitcher_switch_duration, 3000);
            animator_translate_distance = typedArray.getDimension(R.styleable.UpDownViewSwitcher_animator_translate_distance,40);
        }else{
            isUpToDown = true;
            animator_duration = 400;
            switch_duration = 3000;
            animator_translate_distance = getResources().getDimension(R.dimen.animator_translate_distance);
        }
    }

    public void setContentLayout(@LayoutRes int resourse){
        if (this.resourse == 0){
            this.resourse = resourse;
            setFactory();
        }
    }

    private void init() {
        mAutoPlayTask = new AutoPlayTask(this);
        initAnimation();
    }

    public void setFactory(){
        if(resourse != 0){
            super.setFactory(new ViewFactory() {
                @Override
                public View makeView() {
                    return View.inflate(getContext(),resourse,null);
                }
            });
            switchToNextView();
            startAutoPlay();
        }
    }


    private void initAnimation() {
        if(isUpToDown){
            //设置切入动画
            TranslateAnimation animationTop = new TranslateAnimation(0, 0, -animator_translate_distance, 0);
            animationTop.setDuration(animator_duration);
            setInAnimation(animationTop);
            //设置切出动画
            TranslateAnimation animationBottom = new TranslateAnimation(0, 0, 0, animator_translate_distance);
            animationBottom.setDuration(animator_duration);
            setOutAnimation(animationBottom);
        }else{
            //设置切入动画
            TranslateAnimation animationTop = new TranslateAnimation(0, 0, animator_translate_distance, 0);
            animationTop.setDuration(animator_duration);
            setInAnimation(animationTop);
            //设置切出动画
            TranslateAnimation animationBottom = new TranslateAnimation(0, 0, 0, -animator_translate_distance);
            animationBottom.setDuration(animator_duration);
            setOutAnimation(animationBottom);
        }

    }

    public void switchToNextView(){
        if(listener != null){
            listener.switchTONextView(this.getNextView(),index);
            this.showNext();
            index++;
        }
    }

    public void startAutoPlay() {
        postDelayed(mAutoPlayTask,switch_duration);

    }

    public void stopAutoPlay() {
        if (mAutoPlayTask != null) {
            removeCallbacks(mAutoPlayTask);
        }
    }


    private static class AutoPlayTask implements Runnable {
        private final WeakReference<UpDownViewSwitcher> mSwitcher;

        private AutoPlayTask(UpDownViewSwitcher switcher) {
            mSwitcher = new WeakReference<>(switcher);
        }

        @Override
        public void run() {
            UpDownViewSwitcher switcher = mSwitcher.get();
            if (switcher != null) {
                switcher.switchToNextView();
                switcher.startAutoPlay();
            }
        }
    }

    public interface SwitchNextViewListener {
        void switchTONextView(View nextView,int index);
    }

    public void setSwitcheNextViewListener(SwitchNextViewListener listener){
        this.listener = listener;
    }


}
