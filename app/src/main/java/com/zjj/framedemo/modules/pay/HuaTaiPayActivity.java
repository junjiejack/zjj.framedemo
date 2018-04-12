package com.zjj.framedemo.modules.pay;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

import com.google.gson.Gson;
import com.rambler.core.MvpActivity;
import com.rambler.util.NetworkUtils;
import com.zjj.framedemo.R;
import com.zjj.framedemo.dagger.AppModule;
import com.zjj.framedemo.model.PayInfo;
import com.zjj.framedemo.model.SceneInfo;

import java.util.HashMap;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author zhoujunjie on 2017/12/11.
 */

public class HuaTaiPayActivity extends MvpActivity<HuaTaiPayView, HuaTaiPayPresenter> implements HuaTaiPayView {

    @Inject
    HuaTaiPayPresenter presenter;
    @BindView(R.id.btn_hua_tai)
    Button btnHuaTaiPay;
    @BindView(R.id.web_view)
    WebView webView;


    @NonNull
    @Override
    public HuaTaiPayPresenter createPresenter() {
        return presenter;
    }

    @Override
    protected void injectDependencies() {
        DaggerHuaTaiPayComponent.builder().appModule(new AppModule(this)).build().inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hua_tai_pay);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_hua_tai)
    public void onClick(View view) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("money", 1);
        presenter.getPayInfo(map);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void showLoading() {
        mDialog.show();
    }

    private PayInfo payInfo;

    @Override
    public void showSuccess(PayInfo info) {
        mDialog.dismiss();
        payInfo = info;
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
        postWebView();
    }

    private void postWebView() {
        //需要访问的网址
        String payUrl = payInfo.getUrl();
        webView.loadDataWithBaseURL(payUrl, setFormData(), "text/html", "UTF-8", null);
    }

    private String setFormData() {
        StringBuilder sb = new StringBuilder();
        Gson gson = new Gson();
        SceneInfo sceneInfo = gson.fromJson(payInfo.getSceneInfo(), SceneInfo.class);

        sb.append("<form id=\"testForm\" name=\"testForm\" action=\"" + payInfo.getUrl() + "\" method=\"GET\">");
        sb.append("<input id=\"from\" type=\"submit\" name=\"Submit\" value=\"确认提交\" style=\"outline: none; border:none; width: 100%;  height: 45px;  background-color: #06acf9;  border-radius: 5px;  text-align: center;  line-height: 45px;  font-size: 24px;  color: #fff;\" />");
        sb.append("<input type=\"hidden\" name=\"channelCode\" value=\"" + payInfo.getChannel_code() + "\" /><br>");
        sb.append("<input type=\"hidden\" name=\"tradeName\" value=\"" + payInfo.getTrade_name() + "\" /><br>");
        sb.append("<input type=\"hidden\" name=\"tradeType\" value=\"" + payInfo.getTrade_type() + "\" /><br>");
        sb.append("<input type=\"hidden\" name=\"tradeTime\" value=\"" + payInfo.getTrade_time() + "\" /><br>");
        sb.append("<input type=\"hidden\" name=\"orderNo\" value=\"" + payInfo.getOrder_no() + "\" /><br>");
        sb.append("<input type=\"hidden\" name=\"money\" value=\"" + payInfo.getMoney() + "\" /><br>");
        sb.append("<input type=\"hidden\" type=\"text\" name=\"sign\" value=\"" + payInfo.getSign() + "\" /><br>");
        sb.append("<input type=\"hidden\" name=\"IsWXH5\" value=\"" + payInfo.getIsWXH5() + "\" /><br>");
        sb.append("<input type=\"hidden\" name=\"spbillCreateIp\" value=\"" + payInfo.getSpbillCreateIp() + "\" /><br>");
        sb.append("<input type=\"hidden\" name=\"sceneInfo\" value='{\"h5_info\":{\"type\":\"" + sceneInfo.getH5_info().getType() + "\", \"app_name\":\"" + sceneInfo.getH5_info().getApp_name() + "\", \"package_name\": \"" + sceneInfo.getH5_info().getPackage_name() + "\"}}'/><br>");
        sb.append("</form>");
        sb.append("<script type=\"text/javascript\">");
        sb.append("document.testForm.submit()");
        sb.append("</script>");

        return sb.toString();
    }

}
