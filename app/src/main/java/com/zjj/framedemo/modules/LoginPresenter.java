package com.zjj.framedemo.modules;

import com.rambler.common.MvpBasePresenter;
import com.zjj.framedemo.model.LoginResult;
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

public class LoginPresenter extends MvpBasePresenter<LoginView> {

    private JubaoApi api;
    private Subscriber loginSubscriber;

    @Inject
    public LoginPresenter(JubaoApi api) {
        this.api = api;
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
                    getView().showSuccess(result);
                }
            }
        };
        api.getAccessToken(map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(loginSubscriber);
    }

}
