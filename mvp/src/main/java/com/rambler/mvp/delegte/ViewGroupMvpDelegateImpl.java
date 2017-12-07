package com.rambler.mvp.delegte;

import com.rambler.common.MvpPresenter;
import com.rambler.common.MvpView;

public class ViewGroupMvpDelegateImpl<V extends MvpView, P extends MvpPresenter<V>>
    implements ViewGroupMvpDelegate<V, P> {

  protected BaseMvpDelegateCallback<V, P> delegateCallback;
  protected MvpInternalDelegate<V, P> internalDelegate;

  public ViewGroupMvpDelegateImpl(BaseMvpDelegateCallback<V, P> delegateCallback) {
    if (delegateCallback == null) {
      throw new NullPointerException("MvpDelegateCallback is null!");
    }
    this.delegateCallback = delegateCallback;
  }

  protected MvpInternalDelegate<V, P> getInternalDelegate() {
    if (internalDelegate == null) {
      internalDelegate = new MvpInternalDelegate<>(delegateCallback);
    }

    return internalDelegate;
  }

  @Override
  public void onAttachedToWindow() {
    getInternalDelegate().createPresenter();
    getInternalDelegate().attachView();
  }

  @Override
  public void onDetachedFromWindow() {
    getInternalDelegate().detachView();
  }
}
