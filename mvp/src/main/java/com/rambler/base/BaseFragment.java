package com.rambler.base;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;
import com.rambler.R;
import com.rambler.common.MvpView;
import com.rambler.util.AnalyticsUtil;

import org.greenrobot.eventbus.EventBus;

public abstract class BaseFragment extends Fragment implements MvpView {
  public MaterialDialog mDialog;
  protected View mBackBtn;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    int layoutRes = getLayoutRes();
    if (layoutRes == 0) {
      throw new IllegalArgumentException(
          "getLayoutRes() returned 0, which is not allowed. "
              + "If you don't want to use getLayoutRes() but implement your own view for this "
              + "fragment manually, then you have to override onCreateView();");
    } else {
      View v = inflater.inflate(layoutRes, container, false);
      return v;
    }
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    injectDependencies();
    mDialog = new MaterialDialog.Builder(getActivity())
        .content("加载数据中...")
        .canceledOnTouchOutside(false)
        .widgetColor(Color.parseColor("#00aaee"))
        .progress(true, 0).build();
    mBackBtn = view.findViewById(R.id.toolbar_left_layout);
    if (mBackBtn != null) {
      mBackBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          BaseFragment.this.getActivity().finish();
        }
      });
    }
    if(useEventBus()){
      EventBus.getDefault().register(this);
    }
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    if(useEventBus()){
      EventBus.getDefault().unregister(this);
    }
    if (mDialog != null) {
      if (mDialog.isShowing()) {
        mDialog.dismiss();
      }
      mDialog = null;
    }
  }

  /**
   * 是否使用eventBus,默认为使用(false)，
   *
   * @return
   */
  public boolean useEventBus() {
    return false;
  }

  protected void injectDependencies() {

  }

  protected int getLayoutRes() {
    return 0;
  }

  @Override
  public void onResume() {
    super.onResume();
    //数据统计
    AnalyticsUtil.onPageStart(getClass().getSimpleName());
  }

  @Override
  public void onPause() {
    super.onPause();
    //数据统计
    AnalyticsUtil.onPageEnd(getClass().getSimpleName());
  }
}
