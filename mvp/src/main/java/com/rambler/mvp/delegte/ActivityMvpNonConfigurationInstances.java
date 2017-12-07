package com.rambler.mvp.delegte;

import com.rambler.common.MvpPresenter;
import com.rambler.common.MvpView;

class ActivityMvpNonConfigurationInstances<V extends MvpView, P extends MvpPresenter<V>> {

  P presenter;

  Object nonMosbyCustomConfigurationInstance;

  ActivityMvpNonConfigurationInstances(P presenter, Object nonMosbyCustomConfigurationInstance) {
    this.presenter = presenter;
    this.nonMosbyCustomConfigurationInstance = nonMosbyCustomConfigurationInstance;
  }
}
