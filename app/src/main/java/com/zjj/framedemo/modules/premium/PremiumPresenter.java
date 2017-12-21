package com.zjj.framedemo.modules.premium;

import com.zjj.framedemo.base.MvpLceRxPresenter;
import com.zjj.framedemo.model.MyPremiumModel;
import com.zjj.framedemo.retrofit.JubaoApi;
import com.zjj.framedemo.retrofit.RxBaseSubscriber;

import java.util.Map;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * @author zhoujunjie on 2017/12/7.
 */

public class PremiumPresenter extends MvpLceRxPresenter<PremiumView,MyPremiumModel> {

    private JubaoApi api;
    private Subscriber subscriber;

    @Inject
    public PremiumPresenter(JubaoApi api) {
        this.api = api;
    }

    public void getPremiumList(boolean pullToRefresh, Map<String, String> params) {
        subscribe(api.getPremiumList(params), pullToRefresh);
    }


    public void getPremium(final Map<String,String> map) {
        if (isViewAttached()) {
            getView().showLoading(false);
        }
        subscriber = new RxBaseSubscriber<MyPremiumModel>() {

            @Override
            public void _onError(Throwable throwable) {
                if (isViewAttached()) {
                    getView().showError(throwable.getMessage());
                }
            }

            @Override
            public void _onNext(MyPremiumModel result) {
                if (isViewAttached()) {
                    getView().showSuccess(result);
                }
            }
        };
        api.getPremiumList(map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
    }

}
