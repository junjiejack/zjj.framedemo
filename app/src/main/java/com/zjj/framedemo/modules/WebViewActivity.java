package com.zjj.framedemo.modules;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.google.gson.Gson;
import com.rambler.base.BaseActivity;
import com.rambler.util.NetworkUtils;
import com.zjj.framedemo.R;
import com.zjj.framedemo.model.PayInfo;
import com.zjj.framedemo.model.SceneInfo;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author zhoujunjie on 2017/12/11.
 */

public class WebViewActivity extends BaseActivity {

    @BindView(R.id.web_view)
    WebView webView;
    private PayInfo payInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        payInfo = (PayInfo) getIntent().getSerializableExtra("info");
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
        String url = payInfo.getUrl();
        //post访问需要提交的参数
        webView.loadDataWithBaseURL(url,setFormData(), "text/html", "UTF-8",null);
    }

    private String setFormData() {
        StringBuilder sb = new StringBuilder();
        Gson gson = new Gson();
        SceneInfo sceneInfo = gson.fromJson(payInfo.getSceneInfo(), SceneInfo.class);
        sb.append("<form id=\"testForm\" name=\"testForm\" action=\"" + payInfo.getUrl() + "\" method=\"GET\">");
        sb.append("<input id=\"from\" type=\"submit\" name=\"Submit\" value=\"确认提交\" style=\"outline: none; border:none; width: 100%;  height: 45px;  background-color: #06acf9;  border-radius: 5px;  text-align: center;  line-height: 45px;  font-size: 24px;  color: #fff;\" />");
        sb.append("<input style=\"DISPLAY: none\" type=\"text\" name=\"channelCode\" value=\"" + payInfo.getChannel_code() + "\" /><br>");
        sb.append("<input style=\"DISPLAY: none\" type=\"text\" name=\"tradeName\" value=\"" + payInfo.getTrade_name() + "\" /><br>");
        sb.append("<input style=\"DISPLAY: none\" type=\"text\" name=\"tradeType\" value=\"" + payInfo.getTrade_type() + "\" /><br>");
        sb.append("<input style=\"DISPLAY: none\" type=\"text\" name=\"tradeTime\" value=\"" + payInfo.getTrade_time() + "\" /><br>");
        sb.append("<input style=\"DISPLAY: none\" type=\"text\" name=\"orderNo\" value=\"" + payInfo.getOrder_no() + "\" /><br>");
        sb.append("<input style=\"DISPLAY: none\" type=\"text\" name=\"money\" value=\"" + payInfo.getMoney() + "\" /><br>");
        sb.append("<input style=\"DISPLAY: none\" type=\"text\" name=\"sign\" value=\"" + payInfo.getSign() + "\" /><br>");
        sb.append("<input style=\"DISPLAY: none\" type=\"text\" name=\"IsWXH5\" value=\"" + payInfo.getIsWXH5() + "\" /><br>");
        sb.append("<input style=\"DISPLAY: none\" type=\"text\" name=\"spbillCreateIp\" value=\"" + payInfo.getSpbillCreateIp() + "\" /><br>");
        sb.append("<input style=\"DISPLAY: none\" type=\"text\" name=\"sceneInfo\" value='{\"h5_info\":{\"type\":\"" + sceneInfo.getH5_info().getType() + "\", \"app_name\":\"" + sceneInfo.getH5_info().getApp_name() + "\", \"package_name\": \"" + sceneInfo.getH5_info().getPackage_name() + "\"}}'/><br>");
        sb.append("</form>");
        return sb.toString();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
