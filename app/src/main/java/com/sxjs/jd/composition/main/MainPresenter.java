package com.sxjs.jd.composition.main;

import android.util.Log;

import com.sxjs.common.model.DataManager;
import com.sxjs.common.model.http.NoNetWorkException;
import com.sxjs.jd.composition.BasePresenter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import okhttp3.ResponseBody;

/**
* Created by admin on 2017/03/12
*/

public class MainPresenter extends BasePresenter implements MainContract.Presenter{
    private DataManager mDataManager;

    private MainContract.View mMainView;
    private static final String TAG = "MainPresenter";

    @Inject
    public MainPresenter(DataManager mDataManager, MainContract.View view) {
        this.mDataManager = mDataManager;
        this.mMainView = view;

    }


    @Override
    public void getText() {
        mMainView.showProgressDialogView();
        Disposable disposable = mDataManager.getMainData(0, 10, new DisposableObserver<ResponseBody>() {
            @Override
            public void onNext(ResponseBody responseBody) {
                try {
                    mMainView.setText(responseBody.string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable e) {
                if(e instanceof NoNetWorkException){
                    Log.e(TAG, "onError: "+e.getCause());
                }
                mMainView.hiddenProgressDialogView();
            }

            @Override
            public void onComplete() {
                mMainView.hiddenProgressDialogView();
            }
        });
        addDisposabe(disposable);

    }

    @Override
    public void destory() {
        if(disposables != null){
            disposables.clear();
        }
    }

    @Override
    public void saveData() {
        HashMap<String, String> map = new HashMap<>();
        map.put("username","xiaoming");
        map.put("password","123456");
        mDataManager.saveSPMapData(map);
    }

    @Override
    public Map<String, String> getData() {
        return mDataManager.getSPMapData();
    }

}