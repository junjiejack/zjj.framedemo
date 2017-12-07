package com.rambler.mvp.delegte;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.rambler.common.MvpPresenter;
import com.rambler.common.MvpView;

public class FragmentMvpDelegateImpl<V extends MvpView, P extends MvpPresenter<V>>
    implements FragmentMvpDelegate<V, P> {

  protected BaseMvpDelegateCallback<V, P> delegateCallback;
  protected MvpInternalDelegate<V, P> internalDelegate;

  public FragmentMvpDelegateImpl(BaseMvpDelegateCallback<V, P> delegateCallback) {
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
  public void onCreate(Bundle saved) {

  }

  @Override
  public void onDestroy() {

  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    getInternalDelegate().createPresenter();
    getInternalDelegate().attachView();
  }

  @Override
  public void onDestroyView() {
    getInternalDelegate().detachView();
  }

  @Override
  public void onPause() {

  }

  @Override
  public void onResume() {

  }

  @Override
  public void onStart() {

  }

  @Override
  public void onStop() {

  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {

  }

  @Override
  public void onAttach(Activity activity) {

  }

  @Override
  public void onDetach() {

  }

  @Override
  public void onSaveInstanceState(Bundle outState) {

  }
}
