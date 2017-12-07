package com.zjj.framedemo.retrofit;

import rx.Subscriber;

/**
 * Created by lixiaofan on 2017/2/9.
 */

public abstract class RxBaseSubscriber<T>  extends Subscriber<T> {
    @Override
    public void onNext(T t) {
        _onNext(t);
    }
    @Override
    public void onError(Throwable e) {
//        if (NetworkUtils.isNetworkAvailable(AppContext.getInstance())) {
//            AnalyticsUtil.reportError(AppContext.getInstance(), e);
//        }
        _onError(e);
    }

    @Override
    public void onCompleted() {

    }

    protected abstract void _onNext(T t);

    protected abstract void _onError(Throwable e);

}