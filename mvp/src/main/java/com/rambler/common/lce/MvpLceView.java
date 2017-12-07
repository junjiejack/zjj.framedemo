package com.rambler.common.lce;

import com.rambler.common.MvpView;
import com.rambler.mvp.model.EmptyEntity;

public interface MvpLceView<M> extends MvpView {

  public void showLoading(boolean pullToRefresh);

  public void showContent();

  public void showError(String errorMsg, int errorImg, int type, boolean pullToRefresh);

  public void showError(String errorMsg, int type, boolean pullToRefresh);

  public void showEmpty(EmptyEntity msg);

  public void setData(M data);

  public void loadData(boolean pullToRefresh);

  public EmptyEntity emptyData(M data);
}
