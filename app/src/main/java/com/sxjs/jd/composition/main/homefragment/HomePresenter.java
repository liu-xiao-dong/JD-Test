package com.sxjs.jd.composition.main.homefragment;

import android.util.Log;

import com.sxjs.common.base.rxjava.ErrorDisposableObserver;
import com.sxjs.jd.MainDataManager;
import com.sxjs.jd.composition.BasePresenter;
import com.sxjs.jd.entities.HomeIndex;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

/**
 * @author admin
 */

public class HomePresenter extends BasePresenter implements HomeContract.Presenter{
    private MainDataManager mDataManager;

    private HomeContract.View mHomeView;
    @Inject
    public HomePresenter(MainDataManager mDataManager, HomeContract.View view) {
        this.mDataManager = mDataManager;
        this.mHomeView = view;

    }

    @Override
    public void getHomeIndexData(int flag) {
        addDisposabe(mDataManager.getData(new ErrorDisposableObserver<HomeIndex>() {
            @Override
            public void onNext(HomeIndex homeIndex) {
                mHomeView.setHomeIndexData(homeIndex);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("TAG", "onError: "+e );
                mHomeView.setHomeIndexData(null);
            }

            @Override
            public void onComplete() {
            }
        },HomeIndex.class, flag == 1 ?"homeindex.txt" : "homeindexevent.txt"));
    }



    @Override
    public void getRecommendedWares() {
        addDisposabe(mDataManager.getData(new DisposableObserver<HomeIndex>() {
            @Override
            public void onNext(HomeIndex homeIndex) {
                mHomeView.setRecommendedWares(homeIndex);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        },HomeIndex.class, "recommend.txt"));
    }

    @Override
    public void getMoreRecommendedWares() {
        addDisposabe(mDataManager.getData(new DisposableObserver<HomeIndex>() {
            @Override
            public void onNext(HomeIndex homeIndex) {
                mHomeView.setMoreRecommendedWares(homeIndex);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        },HomeIndex.class, "recommended.txt"));
    }
}
