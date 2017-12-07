package com.zjj.framedemo.modules;

import com.rambler.common.MvpView;
import com.zjj.framedemo.model.LoginResult;

/**
 * @author zhoujunjie on 2017/12/7.
 */

public interface LoginView extends MvpView{

    void showLoading();

    void showError(String string);

    void showSuccess(LoginResult result);
}
