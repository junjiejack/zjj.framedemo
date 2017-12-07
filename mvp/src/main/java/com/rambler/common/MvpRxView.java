package com.rambler.common;

/**
 * Created by maocheng on 16/10/12.
 */

public interface MvpRxView extends MvpView {
    public void showError(String errorMsg, int type);
    public void showError(String errorMsg);
}
