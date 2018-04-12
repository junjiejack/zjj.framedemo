package com.zjj.framedemo.modules.premium;

import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rambler.mvp.lce.MvpLceActivity;
import com.rambler.mvp.model.EmptyEntity;
import com.rambler.swipetoload.OnLoadMoreListener;
import com.rambler.swipetoload.OnRefreshListener;
import com.rambler.swipetoload.SwipeToLoadLayout;
import com.rambler.util.SharedPreferencesUtil;
import com.zjj.framedemo.R;
import com.zjj.framedemo.adapter.MyPremiumAdapter;
import com.zjj.framedemo.dagger.AppModule;
import com.zjj.framedemo.model.MyPremiumModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by lixiaofan on 2016/10/21.
 */

public class PremiumActivity extends MvpLceActivity<SwipeToLoadLayout,MyPremiumModel, PremiumView, PremiumPresenter>
        implements PremiumView, OnRefreshListener, OnLoadMoreListener {
    @Inject
    PremiumPresenter mPresenter;

    Map<String, String> mParams = new HashMap();
    int mPage = 1;
    int mPageSize = 10;
//    @Inject
    MyPremiumAdapter mAdapter;

    String mUserId;

    @BindView(R.id.toolbar_left_btn)
    Button toolbarLeftBtn;
    @BindView(R.id.toolbar_left_tv)
    TextView toolbarLeftTv;
    @BindView(R.id.toolbar_title_tv)
    TextView toolbarTitleTv;
    @BindView(R.id.toolbar_right_btn)
    Button toolbarRightBtn;
    @BindView(R.id.toolbar_right_tv)
    TextView toolbarRightTv;
    @BindView(R.id.swipe_target)
    RecyclerView mRecyclerView;
    @BindView(R.id.contentView)
    SwipeToLoadLayout swipeToLoadLayout;

    private List<MyPremiumModel.RowsBean> list = new ArrayList<>();

    @Override
    protected void injectDependencies() {
        DaggerPremiumComponent.builder().appModule(new AppModule(this)).build().inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserId = SharedPreferencesUtil.getSingleton(this).getString("token", "");
        setContentView(R.layout.activity_my_premium);
        ButterKnife.bind(this);
        initView();
        loadData(false);
    }

    private void initView() {
        // 设置左边按钮的显示
        toolbarLeftBtn.setVisibility(View.VISIBLE);
        // 设置标题
        toolbarTitleTv.setVisibility(View.VISIBLE);
        toolbarTitleTv.setText("我的战绩");
        toolbarTitleTv.setTextSize(18);
        // 设置recylerView的布局方式
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // 设置适配器
        mAdapter = new MyPremiumAdapter(list,this);
        mRecyclerView.setAdapter(mAdapter);
        // 下拉刷新和加载更多
        swipeToLoadLayout.setOnRefreshListener(this);
        swipeToLoadLayout.setOnLoadMoreListener(this);
        // 每个条目的点击事件
        // 设置view的滚动监听事件
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                // 判断状态
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (!ViewCompat.canScrollVertically(recyclerView, 1)) {
                        // 加载更多
                        swipeToLoadLayout.setLoadingMore(true);
                    }
                }
            }
        });
    }

    @Override
    public void showError(String errorMsg, int type, boolean pullToRefresh) {
        super.showError(errorMsg, type, pullToRefresh);
        mDialog.dismiss();
        swipeToLoadLayout.setRefreshing(false);
        swipeToLoadLayout.setLoadingMore(false);
        if (mPage > 1) {
            mPage = mPage - 1;
        }
    }


    @Override
    public void setData(MyPremiumModel data) {
        mDialog.dismiss();
        swipeToLoadLayout.setRefreshing(false);
        swipeToLoadLayout.setLoadingMore(false);
        if (mPage == 1) {
            mAdapter.getData().clear();
        }
        mAdapter.getData().addAll(data.getRows());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        mParams.put("page", String.valueOf(mPage));
        mParams.put("rows", String.valueOf(mPageSize));
//        mPresenter.getPremiumList(pullToRefresh, mParams);
        mPresenter.getPremium(mParams);

        mDialog.show();
    }

    @Override
    public EmptyEntity emptyData(MyPremiumModel data) {
        mDialog.dismiss();
        return null;
    }


    @Override
    public PremiumPresenter createPresenter() {
        return mPresenter;
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onRefresh() {
        mPage = 1;
        loadData(true);
    }

    @Override
    public void onLoadMore() {
        mPage = mPage + 1;
        loadData(true);
    }

    @Override
    public void showSuccess(MyPremiumModel model) {
        mDialog.dismiss();
        System.out.println("model123:"+model);
    }

    @Override
    public void showError(String error) {
        mDialog.dismiss();
        System.out.println("showerror:"+error);
    }
}

