package com.zjj.framedemo.modules.taiping;

import com.rambler.common.MvpBasePresenter;
import com.zjj.framedemo.model.LoginResult;
import com.zjj.framedemo.model.TaipingResult;
import com.zjj.framedemo.retrofit.Api;
import com.zjj.framedemo.retrofit.JubaoApi;
import com.zjj.framedemo.retrofit.RxBaseSubscriber;

import java.util.HashMap;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * @author zhoujunjie on 2017/12/7.
 */

public class TaipingPresenter extends MvpBasePresenter<TaipingView> {

    private JubaoApi jubaoApi;
    private Subscriber loginSubscriber;
    private Subscriber subscriber;

    @Inject
    public TaipingPresenter(JubaoApi jubaoApi,Api api) {
        this.jubaoApi = jubaoApi;
    }

    public void getLoginInfo(final HashMap<String,String> map) {
        if (isViewAttached()) {
            getView().showLoading();
        }
        loginSubscriber = new RxBaseSubscriber<LoginResult>() {

            @Override
            public void _onError(Throwable throwable) {
                if (isViewAttached()) {
                    getView().showError(throwable.getMessage());
                }
            }

            @Override
            public void _onNext(LoginResult result) {
                if (isViewAttached()) {
                    getView().showLoginSuccess(result);
                }
            }
        };
        jubaoApi.getAccessToken(map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(loginSubscriber);
    }

    public void getTaipingResult(final HashMap map) {
        if (isViewAttached()) {
            getView().showLoading();
        }
        subscriber = new RxBaseSubscriber<TaipingResult>() {

            @Override
            public void _onError(Throwable throwable) {
                if (isViewAttached()) {
                    getView().showError(throwable.getMessage());
                }
            }

            @Override
            public void _onNext(TaipingResult result) {
                if (isViewAttached()) {
                    getView().showPaySuccess(result);
                }
            }
        };
        jubaoApi.getTaiping(map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
    }

}
