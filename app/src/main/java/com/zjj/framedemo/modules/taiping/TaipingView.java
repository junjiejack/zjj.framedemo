package com.zjj.framedemo.modules.taiping;

import com.rambler.common.MvpView;
import com.zjj.framedemo.model.LoginResult;
import com.zjj.framedemo.model.TaipingResult;

/**
 * @author zhoujunjie on 2017/12/7.
 */

public interface TaipingView extends MvpView{

    void showLoading();

    void showError(String string);

    void showLoginSuccess(LoginResult result);

    void showPaySuccess(TaipingResult result);
}
