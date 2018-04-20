package com.sxjs.common.model;

import android.content.Context;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * @author：LiuXiaoDong on 2018/4/20 18:07.
 *
 * DataManager的包装基类,供各module继承
 */

public class BaseDataManager  {

    private DataManager mDataManager;

    public BaseDataManager(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    protected void saveSPData(String key , String value){
        mDataManager.saveSPData(key,value);
    }

    public void saveSPMapData(Map<String,String> map){
        mDataManager.saveSPMapData(map);
    }

    public String getSPData(String key){
        return mDataManager.getSPData(key);
    }

    public void deleteSPData(){
        mDataManager.deleteSPData();
    }

    public Map<String ,String> getSPMapData(){
        return mDataManager.getSPMapData();
    }

    protected Disposable changeIOToMainThread(Observable<ResponseBody> observable , DisposableObserver<ResponseBody> consumer ){
        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(consumer);
    }

    protected  <S> S getService(Class<S> serviceClass){
        return mDataManager.getService(serviceClass);
    }

    protected Context getContext(){
        return mDataManager.getContext();
    }
}
