package com.rambler.mvp.delegte;

import android.support.annotation.NonNull;

import com.rambler.common.MvpPresenter;
import com.rambler.common.MvpView;

public interface BaseMvpDelegateCallback<V extends MvpView, P extends MvpPresenter<V>> {

  @NonNull
  public P createPresenter();

  public P getPresenter();

  public void setPresenter(P presenter);

  public V getMvpView();

  public boolean isRetainInstance();

  public void setRetainInstance(boolean retainingInstance);

  public boolean shouldInstanceBeRetained();
}

