package com.rambler.mvp.delegte;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.rambler.common.MvpPresenter;
import com.rambler.common.MvpView;

public interface FragmentMvpDelegate<V extends MvpView, P extends MvpPresenter<V>> {

  public void onCreate(Bundle saved);

  public void onDestroy();

  public void onViewCreated(View view, @Nullable Bundle savedInstanceState);

  public void onDestroyView();

  public void onPause();

  public void onResume();

  public void onStart();

  public void onStop();

  public void onActivityCreated(Bundle savedInstanceState);

  public void onAttach(Activity activity);

  public void onDetach();

  public void onSaveInstanceState(Bundle outState);
}
