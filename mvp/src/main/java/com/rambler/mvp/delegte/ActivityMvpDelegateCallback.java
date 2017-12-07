package com.rambler.mvp.delegte;


import com.rambler.common.MvpPresenter;
import com.rambler.common.MvpView;

public interface ActivityMvpDelegateCallback<V extends MvpView, P extends MvpPresenter<V>>
    extends BaseMvpDelegateCallback<V, P> {

  public Object onRetainNonMosbyCustomNonConfigurationInstance();

  public Object getLastCustomNonConfigurationInstance();

  public Object getNonMosbyLastCustomNonConfigurationInstance();
}
