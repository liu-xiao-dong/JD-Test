package com.sxjs.common.model.dao;

import android.content.Context;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by admin on 2017/3/10.
 */
@Singleton
public class DataBaseHelper {
    private Context context;
    @Inject
    public DataBaseHelper(Context context) {
        this.context = context;
    }
}
