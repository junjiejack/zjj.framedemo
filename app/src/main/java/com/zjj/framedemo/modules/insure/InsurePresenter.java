package com.zjj.framedemo.modules.insure;

import com.rambler.common.MvpBasePresenter;
import com.zjj.framedemo.model.PayInfo;
import com.zjj.framedemo.retrofit.Api;
import com.zjj.framedemo.retrofit.RxBaseSubscriber;

import java.util.HashMap;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * @author zhoujunjie on 2017/12/7.
 */

public class InsurePresenter extends MvpBasePresenter<InsureView> {

    private Api api;
    private Subscriber loginSubscriber;

    @Inject
    public InsurePresenter(Api api) {
        this.api = api;
    }

    public void getPayInfo(final HashMap<String,Integer> map) {
        if (isViewAttached()) {
            getView().showLoading();
        }
        loginSubscriber = new RxBaseSubscriber<PayInfo>() {

            @Override
            public void _onError(Throwable throwable) {
                if (isViewAttached()) {
                    getView().showError(throwable.getMessage());
                }
            }

            @Override
            public void _onNext(PayInfo result) {
                if (isViewAttached()) {
                    getView().showSuccess(result);
                }
            }
        };
        api.getPayInfo(map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(loginSubscriber);
    }

}
