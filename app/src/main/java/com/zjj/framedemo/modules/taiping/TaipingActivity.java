package com.zjj.framedemo.modules.taiping;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

import com.rambler.core.MvpActivity;
import com.rambler.util.NetworkUtils;
import com.rambler.util.SharedPreferencesUtil;
import com.zjj.framedemo.R;
import com.zjj.framedemo.dagger.AppModule;
import com.zjj.framedemo.model.LoginResult;
import com.zjj.framedemo.model.TaipingResult;

import java.util.HashMap;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TaipingActivity extends MvpActivity<TaipingView,TaipingPresenter> implements TaipingView {

    @Inject
    TaipingPresenter presenter;
    @Bind(R.id.web_view)
    WebView webView;
    @Bind(R.id.btn_tai_ping)
    Button btnTaiping;

    @NonNull
    @Override
    public TaipingPresenter createPresenter() {
        return presenter;
    }

    @Override
    protected void injectDependencies() {
        DaggerTaipingComponent.builder().appModule(new AppModule(this)).build().inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tai_ping);
        ButterKnife.bind(this);
    }

    /** 点击登录 */
    @OnClick(R.id.btn_tai_ping)
    public void onClick(View view) {
        HashMap<String,String> map = new HashMap<>();
        map.put("mobile","18062007361");
        map.put("password","11111a");
        presenter.getLoginInfo(map);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }


    @Override
    public void showLoading() {
        mDialog.show();
    }

    @Override
    public void showLoginSuccess(LoginResult result) {
        mDialog.dismiss();
        SharedPreferencesUtil.getSingleton(this).save("token",result.getData().getAccess_token());
        HashMap map = new HashMap<>();
        map.put("type",2);
        map.put("redirect_url","http://www.jubao56.com/#/");
        map.put("amount",1);
        presenter.getTaipingResult(map);
    }

    @Override
    public void showPaySuccess(TaipingResult result) {
        mDialog.dismiss();
        btnTaiping.setVisibility(View.GONE);
        WebSettings webSettings = webView.getSettings();
        //设置WebView属性，能够执行Javascript脚本
        webSettings.setJavaScriptEnabled(true);
        //设置可以访问文件
        webSettings.setAllowFileAccess(true);

        if (NetworkUtils.isWifi(this)) {
            webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        } else {
            webSettings.setCacheMode(
                    WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }
        webSettings.setDomStorageEnabled(true);
        webSettings.setAppCacheEnabled(true);

        System.out.println("url123:"+result.getData().getPay_url());
        webView.loadUrl(result.getData().getPay_url());
//        webView.loadUrl("http://test.jubao56.com/spb/pay-online.html?705");
    }


}
