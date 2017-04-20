package com.sxjs.testmodule;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.sxjs.common.base.BaseActivity;
import com.sxjs.common.util.ImageLoaderUtil;
import com.sxjs.common.widget.imageview.ExpandImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * 22@author：admin on 2017/4/10 14:50.
 */
@Route(path = "/test1/activity")
public class TestActivity extends BaseActivity implements View.OnClickListener, EasyPermissions.PermissionCallbacks {


    private static final int RC_CAMERA_AND_LOCATION = 123;
    @BindView(R2.id.expand_img)
    ExpandImageView expandImg;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity);
        unbinder = ButterKnife.bind(this);
        ImageLoaderUtil.getInstance(expandImg)
                .setOverlayImage(getResources().getDrawable(R.drawable.over))
                .setRoundingParams(10);
    }


    @AfterPermissionGranted(RC_CAMERA_AND_LOCATION)
    public void testPermissionRequest() {
        String[] perms = {Manifest.permission.CAMERA, Manifest.permission.ACCESS_FINE_LOCATION};
        if (EasyPermissions.hasPermissions(this, perms)) {
            // Already have permission, do the thing
            // ...
            showShortToast("已经获取权限了");
        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(this, "权限请求",
                    RC_CAMERA_AND_LOCATION, perms);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        showShortToast("我同意了你的权限" + perms.toString());

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        showShortToast("我拒绝了你的权限" + perms.toString());
    }

    /**
     * 运行时id会和编译时id发生变化 所以运行时判断必须用R.id
     *
     * @param view
     */
    @OnClick({R2.id.show_toast, R2.id.clear_cache})
    public void onClick(View view) {
        if (view.getId() == R.id.show_toast) {
            testPermissionRequest();
        } else if (view.getId() == R.id.clear_cache) {
            Fresco.getImagePipeline().clearDiskCaches();
            showShortToast("缓存已清理");
        }
    }
}
