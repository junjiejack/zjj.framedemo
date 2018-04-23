package com.zjj.framedemo.modules.share;

import com.zjj.framedemo.base.MvpLceRxPresenter;
import com.zjj.framedemo.retrofit.Api;

import java.util.List;

import javax.inject.Inject;


/**
 * @author zhoujunjie on 2017/12/7.
 */

public class SystemSharePresenter extends MvpLceRxPresenter<SystemShareView,List<SystemShareModel>> {

    private Api api;

    @Inject
    public SystemSharePresenter(Api api) {
        this.api = api;
    }

    public void getImage(boolean pullToRefresh) {
        subscribe(api.getImage(), pullToRefresh);
    }

}
