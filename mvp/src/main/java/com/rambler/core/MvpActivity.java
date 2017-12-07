package com.rambler.core;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.rambler.Constants;
import com.rambler.base.BaseToolbarActivity;
import com.rambler.common.MvpPresenter;
import com.rambler.common.MvpRxView;
import com.rambler.common.MvpView;
import com.rambler.mvp.delegte.ActivityMvpDelegate;
import com.rambler.mvp.delegte.ActivityMvpDelegateCallback;
import com.rambler.mvp.delegte.ActivityMvpDelegateImpl;

public abstract class MvpActivity<V extends MvpView, P extends MvpPresenter<V>>
        extends BaseToolbarActivity implements ActivityMvpDelegateCallback<V, P>, MvpRxView {

  protected ActivityMvpDelegate mvpDelegate;
  protected P presenter;
  protected boolean retainInstance;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getMvpDelegate().onCreate(savedInstanceState);
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    getMvpDelegate().onDestroy();
  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    getMvpDelegate().onSaveInstanceState(outState);
  }

  @Override
  protected void onPause() {
    super.onPause();
    getMvpDelegate().onPause();
  }

  @Override
  protected void onResume() {
    super.onResume();
    getMvpDelegate().onResume();
  }

  @Override
  protected void onStart() {
    super.onStart();
    getMvpDelegate().onStart();
  }

  @Override
  protected void onStop() {
    super.onStop();
    getMvpDelegate().onStop();
  }

  @Override
  protected void onRestart() {
    super.onRestart();
    getMvpDelegate().onRestart();
  }

  @Override
  public void onContentChanged() {
    super.onContentChanged();
    getMvpDelegate().onContentChanged();
  }

  @Override
  protected void onPostCreate(Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    getMvpDelegate().onPostCreate(savedInstanceState);
  }


  @NonNull
  public abstract P createPresenter();

  @NonNull
  protected ActivityMvpDelegate<V, P> getMvpDelegate() {
    if (mvpDelegate == null) {
      mvpDelegate = new ActivityMvpDelegateImpl(this);
    }

    return mvpDelegate;
  }

  @NonNull
  @Override
  public P getPresenter() {
    return presenter;
  }

  @Override
  public void setPresenter(@NonNull P presenter) {
    this.presenter = presenter;
  }

  @NonNull
  @Override
  public V getMvpView() {
    return (V) this;
  }

  @Override
  public boolean isRetainInstance() {
    return retainInstance;
  }

  @Override
  public boolean shouldInstanceBeRetained() {
    return retainInstance && isChangingConfigurations();
  }

  @Override
  public void setRetainInstance(boolean retainInstance) {
    this.retainInstance = retainInstance;
  }

  @Override
  public Object onRetainNonMosbyCustomNonConfigurationInstance() {
    return null;
  }

  @Override
  public final Object onRetainCustomNonConfigurationInstance() {
    return getMvpDelegate().onRetainCustomNonConfigurationInstance();
  }

  @Override
  public final Object getNonMosbyLastCustomNonConfigurationInstance() {
    return getMvpDelegate().getNonMosbyLastCustomNonConfigurationInstance();
  }

  @Override
  public void showError(String errorMsg, int type) {
     if(mDialog != null)
          mDialog.dismiss();
      showLightError(errorMsg);
  }

  @Override
  public void showError(String errorMsg) {
    showError(errorMsg, Constants.RESPONSE_UNKNOWN_ERROR);
  }


  protected void showLightError(String msg) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
  }

}
