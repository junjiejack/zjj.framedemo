package com.rambler.mvp.lce;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.rambler.Constants;
import com.rambler.R;
import com.rambler.common.MvpPresenter;
import com.rambler.common.lce.MvpLceView;
import com.rambler.core.MvpFragment;
import com.rambler.mvp.model.EmptyEntity;


public abstract class MvpLceFragment<CV extends View, M, V extends MvpLceView<M>, P extends MvpPresenter<V>>
    extends MvpFragment<V, P> implements MvpLceView<M> {

  protected ImageView loadingView;
  protected CV contentView;
  protected View errorView;
  protected TextView tvError, tvRetry;
  protected ImageView ivError;

  @CallSuper
  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    loadingView = (ImageView)view.findViewById(R.id.loadingView);
    Glide.with(this).load(R.drawable.loading).into(loadingView);
    contentView = (CV) view.findViewById(R.id.contentView);
    errorView = view.findViewById(R.id.errorView);
    tvError = (TextView) view.findViewById(R.id.tv_error);
    ivError = (ImageView) view.findViewById(R.id.iv_error);
    tvRetry = (TextView) view.findViewById(R.id.tv_retry);
    if (loadingView == null) {
      throw new NullPointerException(
          "Loading view is null! Have you specified a loading view in your layout xml file?"
              + " You have to give your loading View the id R.id.loadingView");
    }

    if (contentView == null) {
      throw new NullPointerException(
          "Content view is null! Have you specified a content view in your layout xml file?"
              + " You have to give your content View the id R.id.contentView");
    }

    if (errorView == null) {
      throw new NullPointerException(
          "Error view is null! Have you specified a content view in your layout xml file?"
              + " You have to give your error View the id R.id.errorView");
    }

      tvRetry.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        onErrorViewClicked();
      }
    });
  }

    @Override
    public void showLoading(boolean pullToRefresh) {

        if (!pullToRefresh) {
            animateLoadingViewIn();
        }

        // otherwise the pull to refresh widget will already display a loading animation
    }

    protected void animateLoadingViewIn() {
        LceAnimator.showLoading(loadingView, contentView, errorView);
    }

    @Override
    public void showContent() {
        animateContentViewIn();
    }

    protected void animateContentViewIn() {
        LceAnimator.showContent(loadingView, contentView, errorView);
    }

    protected void showLightError(String msg) {
        if (getActivity() != null) {
            Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
        }
    }

    protected void onErrorViewClicked() {
        loadData(false);
    }

    @Override
    public void showError(String errorMsg, int errorImg, int type, boolean pullToRefresh) {
        if (pullToRefresh) {
            showLightError(errorMsg);
        } else {
            if(type == Constants.RESPONSE_NETWORK_ERROR){
                ivError.setImageResource(R.mipmap.status_network);
            } else {
                ivError.setImageResource(R.mipmap.status_unknown);
            }
            tvRetry.setVisibility(View.VISIBLE);
            tvError.setText(errorMsg);
            animateErrorViewIn();
        }
    }


    @Override
    public void showError(String errorMsg, int type, boolean pullToRefresh) {
        showError(errorMsg, -1, type, pullToRefresh);
    }

    @Override
    public void showEmpty(EmptyEntity msg) {
        if(msg.isLoadMore()){
            showLightError(msg.getDescription());
        }else {
            if(msg.getImage()!= -1){
                ivError.setImageResource(msg.getImage());
            } else {
                ivError.setImageResource(R.mipmap.status_network);
            }
            tvRetry.setVisibility(View.GONE);
            tvError.setText(msg.getDescription());
            animateErrorViewIn();
        }
    }

    protected void animateErrorViewIn() {
        LceAnimator.showErrorView(loadingView, contentView, errorView);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        loadingView = null;
        contentView = null;
        errorView = null;
    }

    @Override
    public EmptyEntity emptyData(M data){
        return null;
    }
}
