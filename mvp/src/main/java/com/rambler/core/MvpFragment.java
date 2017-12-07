package com.rambler.core;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Toast;

import com.rambler.Constants;
import com.rambler.base.BaseNavigationFragment;
import com.rambler.common.MvpPresenter;
import com.rambler.common.MvpRxView;
import com.rambler.common.MvpView;
import com.rambler.mvp.delegte.BaseMvpDelegateCallback;
import com.rambler.mvp.delegte.FragmentMvpDelegate;
import com.rambler.mvp.delegte.FragmentMvpDelegateImpl;


public abstract class MvpFragment<V extends MvpView, P extends MvpPresenter<V>> extends BaseNavigationFragment
        implements BaseMvpDelegateCallback<V, P>, MvpRxView {

  protected FragmentMvpDelegate<V, P> mvpDelegate;

  protected P presenter;

  public abstract P createPresenter();

  @NonNull
  protected FragmentMvpDelegate<V, P> getMvpDelegate() {
    if (mvpDelegate == null) {
      mvpDelegate = new FragmentMvpDelegateImpl<>(this);
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

  @Override
  public boolean isRetainInstance() {
    return getRetainInstance();
  }

  @Override
  public boolean shouldInstanceBeRetained() {
    FragmentActivity activity = getActivity();
    boolean changingConfig = activity != null && activity.isChangingConfigurations();
    return getRetainInstance() && changingConfig;
  }

  @NonNull
  @Override
  public V getMvpView() {
    return (V) this;
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    getMvpDelegate().onViewCreated(view, savedInstanceState);
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    getMvpDelegate().onDestroyView();
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getMvpDelegate().onCreate(savedInstanceState);
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    getMvpDelegate().onDestroy();
  }

  @Override
  public void onPause() {
    super.onPause();
    getMvpDelegate().onPause();
  }

  @Override
  public void onResume() {
    super.onResume();
    getMvpDelegate().onResume();
  }

  @Override
  public void onStart() {
    super.onStart();
    getMvpDelegate().onStart();
  }

  @Override
  public void onStop() {
    super.onStop();
    getMvpDelegate().onStop();
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    getMvpDelegate().onActivityCreated(savedInstanceState);
  }

  @Override
  public void onAttach(Activity activity) {
    super.onAttach(activity);
    getMvpDelegate().onAttach(activity);
  }

  @Override
  public void onDetach() {
    super.onDetach();
    getMvpDelegate().onDetach();
  }

  @Override
  public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    getMvpDelegate().onSaveInstanceState(outState);
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
    Toast.makeText(this.getActivity(), msg, Toast.LENGTH_SHORT).show();
  }

}

