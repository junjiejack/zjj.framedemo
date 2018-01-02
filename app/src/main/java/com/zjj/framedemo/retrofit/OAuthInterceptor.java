package com.zjj.framedemo.retrofit;

import android.content.Context;
import android.text.TextUtils;

import com.rambler.util.SharedPreferencesUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class OAuthInterceptor implements Interceptor {

  private Context mContext;

  public OAuthInterceptor(Context context) {
    mContext = context;
  }

  @Override
  public Response intercept(Chain chain) throws IOException {
    Request originalRequest = chain.request();
    Request.Builder requestBuilder = originalRequest.newBuilder()
        //Basic Authentication,也可用于token验证,OAuth验证
        .header("Charset", "UTF-8").header("Accept-Encoding", "gzip,deflate");
    /**--------------------仅供测试使用--------------------------*/
//    SharedPreferencesUtil.getSingleton(mContext).save("token","8359|Plrx441AYkDbXb0uOsqx7ZCBsRo5t0KOgqWf0MXp4/ME2KYFKQYifW6ukXAD04TMSZ4iWg3JFNy8/2WxRh2toQ==");
    if (!TextUtils.isEmpty(
        SharedPreferencesUtil.getSingleton(mContext).getString("token", ""))) {
      String basic = "Bearer " + SharedPreferencesUtil.getSingleton(mContext)
          .getString("token", "");
      requestBuilder.addHeader("Authorization", basic);
    }
    Request request = requestBuilder.build();
    Response originalResponse = chain.proceed(request);
    return originalResponse;
  }
}