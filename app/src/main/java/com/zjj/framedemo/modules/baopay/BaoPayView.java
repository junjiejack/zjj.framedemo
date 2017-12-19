package com.zjj.framedemo.modules.baopay;

import com.rambler.common.MvpView;
import com.zjj.framedemo.model.PayInfo;

/**
 * @author zhoujunjie on 2017/12/7.
 */

public interface BaoPayView extends MvpView{

    void showLoading();

    void showError(String string);

    void showSuccess(PayInfo info);
}
