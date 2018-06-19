package com.yammobots.samplenetwork.mvp.presenters;

import com.yammobots.samplenetwork.mvp.views.BaseView;

/**
 * Created by yepyaesonetun on 6/18/18.
 **/

public abstract class BasePresenter<T extends BaseView> {

    protected T mView;

    public BasePresenter(T mView) {
        this.mView = mView;
    }

    public void onStart(){}

    public void onCreate(){}

    public void onPause(){}

    public void onResume(){}

    public void onStop(){}

    public void onDestory(){}
}
