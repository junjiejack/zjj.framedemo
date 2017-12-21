package com.zjj.framedemo.modules.premium;

import com.rambler.common.lce.MvpLceView;
import com.zjj.framedemo.model.MyPremiumModel;

/**
 * @author zhoujunjie on 2017/12/7.
 */

public interface PremiumView extends MvpLceView<MyPremiumModel>{

    void showSuccess(MyPremiumModel model);

    void showError(String error);
}
