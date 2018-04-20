package com.sxjs.jd.composition.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.widget.FrameLayout;

import com.sxjs.common.widget.bottomnavigation.BadgeItem;
import com.sxjs.common.widget.bottomnavigation.BottomNavigationBar;
import com.sxjs.common.widget.bottomnavigation.BottomNavigationItem;
import com.sxjs.jd.MainDataManager;
import com.sxjs.jd.R;
import com.sxjs.common.base.BaseActivity;
import com.sxjs.jd.composition.main.classificationfragment.ClassificationFragment;
import com.sxjs.jd.composition.main.findfragment.FindFragment;
import com.sxjs.jd.composition.main.homefragment.MainHomeFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity implements MainContract.View, BottomNavigationBar.OnTabSelectedListener {

    @Inject
    MainPresenter presenter;
    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar bottomNavigationBar;
    @BindView(R.id.main_container)
    FrameLayout mainContainer;
    private MainHomeFragment mMainHomeFragment;
    private ClassificationFragment mClassificationFragment;
    private FragmentManager mFragmentManager;
    private FindFragment mFindFragment;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        mFragmentManager = getSupportFragmentManager();
        initView();
        initData();

    }

    public void initView() {
        mMainHomeFragment = (MainHomeFragment) mFragmentManager.findFragmentByTag("home_fg");
        mClassificationFragment = (ClassificationFragment) mFragmentManager.findFragmentByTag("class_fg");
        mFindFragment = (FindFragment) mFragmentManager.findFragmentByTag("find_fg");

        if(mMainHomeFragment == null){
            mMainHomeFragment = MainHomeFragment.newInstance();
            addFragment(R.id.main_container, mMainHomeFragment, "home_fg");
        }
        if(mClassificationFragment == null){
            mClassificationFragment = ClassificationFragment.newInstance();
            addFragment(R.id.main_container, mClassificationFragment, "class_fg");
        }

        if(mFindFragment == null){
            mFindFragment = FindFragment.newInstance();
            addFragment(R.id.main_container, mFindFragment, "find_fg");
        }
        mFragmentManager.beginTransaction().show(mMainHomeFragment).hide(mClassificationFragment).hide(mFindFragment)
                .commitAllowingStateLoss();

        DaggerMainActivityComponent.builder()
                .appComponent(getAppComponent())
                .mainPresenterModule(new MainPresenterModule(this, MainDataManager.getInstance(mDataManager)))
                .build()
                .inject(this);
        initBottomNavigation();

    }

    private void initBottomNavigation() {
        BadgeItem numberBadgeItem = new BadgeItem()
                .setBorderWidth(4)
                .setBackgroundColorResource(R.color.colorAccent)
                .setText("99+")
                .setHideOnSelect(false);

        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        //bottomNavigationBar.setMode(BottomNavigationBar.MODE_SHIFTING);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        //bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE);
        //bottomNavigationBar.setAutoHideEnabled(true);


        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.axh, "").setInactiveIconResource(R.drawable.axg).setActiveColorResource(R.color.colorAccent))
                .addItem(new BottomNavigationItem(R.drawable.axd, "").setInactiveIconResource(R.drawable.axc).setActiveColorResource(R.color.colorAccent))
                .addItem(new BottomNavigationItem(R.drawable.axf, "").setInactiveIconResource(R.drawable.axe).setActiveColorResource(R.color.colorAccent))
                .addItem(new BottomNavigationItem(R.drawable.axb, "").setInactiveIconResource(R.drawable.axa).setActiveColorResource(R.color.colorAccent).setBadgeItem(numberBadgeItem))
                .addItem(new BottomNavigationItem(R.drawable.axj, "").setInactiveIconResource(R.drawable.axi).setActiveColorResource(R.color.colorAccent))
                .setFirstSelectedPosition(0)
                .initialise();

        bottomNavigationBar.setTabSelectedListener(this);
    }

    private static final String TAG = "MainActivity";


    public void initData() {
        presenter.getText();
    }

    private String text;

    @Override
    public void setText(String text) {

        this.text = text;
    }

    @Override
    public void showProgressDialogView() {
        showProgressDialog();
    }

    @Override
    public void hiddenProgressDialogView() {
        hiddenProgressDialog();
    }



    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("text", text);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            String text = savedInstanceState.getString("text");
            this.text = text;

        }
    }


    @Override
    public void onTabSelected(int position) {
        if(position == 0){
            mFragmentManager.beginTransaction().hide(mFindFragment).hide(mClassificationFragment).show(mMainHomeFragment)
                    .commitAllowingStateLoss();
        }
        else if(position == 1){
            mFragmentManager.beginTransaction().hide(mFindFragment).hide(mMainHomeFragment).show(mClassificationFragment)
                    .commitAllowingStateLoss();
        }
        else if(position == 2){
            mFragmentManager.beginTransaction().hide(mClassificationFragment).hide(mMainHomeFragment).show(mFindFragment)
                    .commitAllowingStateLoss();
        }
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    @Override
    protected void onStart() {
        super.onStart();

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.destory();
        }


    }

}
