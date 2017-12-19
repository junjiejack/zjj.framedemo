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