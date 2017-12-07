package com.rambler.mvp.delegte;

import android.os.Bundle;

import com.rambler.common.MvpPresenter;
import com.rambler.common.MvpView;

public interface ActivityMvpDelegate<V extends MvpView, P extends MvpPresenter<V>> {

  public void onCreate(Bundle bundle);

  public void onDestroy();

  public void onPause();

  public void onResume();

  public void onStart();

  public void onStop();

  public void onRestart();

  public void onContentChanged();

  public void onSaveInstanceState(Bundle outState);

  public void onPostCreate(Bundle savedInstanceState);

  public Object onRetainCustomNonConfigurationInstance();

  public Object getNonMosbyLastCustomNonConfigurationInstance();
}
