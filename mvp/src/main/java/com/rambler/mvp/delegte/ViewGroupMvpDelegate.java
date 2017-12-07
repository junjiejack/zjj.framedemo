package com.rambler.mvp.delegte;


import com.rambler.common.MvpPresenter;
import com.rambler.common.MvpView;

public interface ViewGroupMvpDelegate<V extends MvpView, P extends MvpPresenter<V>> {

  public void onAttachedToWindow();

  public void onDetachedFromWindow();

}
