package com.sxjs.common.util;

import android.graphics.drawable.Drawable;
import android.support.annotation.IdRes;

import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * @author：admin on 2017/4/17 17:08.
 * 加载图片修改参数的帮助类
 */

public class ImageLoaderUtil {

    private SimpleDraweeView draweeView;

    private GenericDraweeHierarchy hierarchy;

    private ImageLoaderUtil(SimpleDraweeView draweeView) {
        this.draweeView = draweeView;
        hierarchy = draweeView.getHierarchy();
    }

    /**
     * 获取当前util实例
     * @param draweeView imageView实例
     * @return 新实例
     */
    public static ImageLoaderUtil getInstance(SimpleDraweeView draweeView){
        return new ImageLoaderUtil(draweeView);
    }

    /**
     * 设置占位图片
     * @param id 图片资源id
     * @return this
     */
    public ImageLoaderUtil setPlaceholderImage(@IdRes int id){
        hierarchy.setPlaceholderImage(id);
        return this;
    }

    /**
     * 设置占位图片
     * @param drawable drawable
     * @return this
     */
    public ImageLoaderUtil setPlaceholderImage(Drawable drawable){
        hierarchy.setPlaceholderImage(drawable);
        return this;
    }

    /**
     * 设置加载失败图片
     * @param drawable drawable
     * @param scaleType ScalingUtils.ScaleType
     * @return this
     */
    public ImageLoaderUtil setFailureImage(Drawable drawable , ScalingUtils.ScaleType scaleType){
        hierarchy.setFailureImage(drawable, scaleType);
        return this;
    }

    /**
     * 设置加载失败图片
     * @param id 图片资源id
     * @param scaleType ScalingUtils.ScaleType
     * @return this
     */
    public ImageLoaderUtil setFailureImage(@IdRes int id , ScalingUtils.ScaleType scaleType){
        hierarchy.setFailureImage(id, scaleType);
        return this;
    }

    /**
     * 设置显示图片缩放类型
     * @param scaleType facebook包下ScalingUtils.ScaleType
     * @return this
     */
    public ImageLoaderUtil setActualImageScaleType(ScalingUtils.ScaleType scaleType){
        hierarchy.setActualImageScaleType(scaleType);
        return this;
    }

    /**
     * 设置图片的原角半径（原来为圆角的不能修改为圆圈，反之亦然）
     * @param radius 圆角半径
     * @return this
     */
    public ImageLoaderUtil setRoundingParams(float radius){
        RoundingParams roundingParams = hierarchy.getRoundingParams();
        if(roundingParams == null){
            hierarchy.setRoundingParams(new RoundingParams().setCornersRadius(radius));
        }else{
            roundingParams.setCornersRadius(radius);
            hierarchy.setRoundingParams(roundingParams);
        }

        return this;
    }

    /**
     * 设置图片渐退时间
     * @param fadeDuration 时间毫秒
     * @return this
     */
    public ImageLoaderUtil setFadeDuration(int fadeDuration){
        hierarchy.setFadeDuration(fadeDuration);
        return this;
    }

    /**
     * 设置背景图
     * @param drawable drawable
     * @return this
     */
    public ImageLoaderUtil setBackgroundImage(Drawable drawable){
        hierarchy.setBackgroundImage(drawable);
        return this;
    }

    /**
     * 设置加载失败后显示点击重新加载图
     * @param id 资源id
     * @return this
     */
    public ImageLoaderUtil setRetryImage(@IdRes int id){
        hierarchy.setRetryImage(id);
        return this;
    }

    /**
     * 设置叠加图（最后被绘制）
     * @param drawable drawable
     * @return this
     */
    public ImageLoaderUtil setOverlayImage(Drawable drawable){
        hierarchy.setOverlayImage(drawable);
        return this;
    }






}
