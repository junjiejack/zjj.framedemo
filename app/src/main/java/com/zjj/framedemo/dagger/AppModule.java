package com.zjj.framedemo.dagger;

import android.app.Activity;
import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.zjj.framedemo.base.BaseSetting;
import com.zjj.framedemo.retrofit.Api;
import com.zjj.framedemo.retrofit.JubaoApi;
import com.zjj.framedemo.retrofit.OAuthInterceptor;
import com.zjj.framedemo.retrofit.RetryCallAdapterFactory;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author zhoujunjie on 2017/12/7.
 * return singleton class or class
 * 通过依赖注入返回项目中需要的实例
 */

@Module
public class AppModule {

    private Activity context;

    public AppModule(Activity context) {
        this.context = context;
    }

    @Provides
    public Context provideContext() {
        return context;
    }

    @Provides @Singleton
    public RequestManager provideGlide() {
        return Glide.with(context);
    }

    @Provides @Singleton
    public Api provideApi() {
        /**1. 添加log日志拦截器,方便查看log日志*/
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        /**2. 创建okhttp */
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)                   // 添加日志拦截器
                .addNetworkInterceptor(interceptor)            // 添加网络拦截器
                .readTimeout(60, TimeUnit.SECONDS)             // 读取超时
                .writeTimeout(60, TimeUnit.SECONDS)            // 写超时
                .connectTimeout(60, TimeUnit.SECONDS)          // 链接超时
                .build();
        /**3. 创建retrofit */
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseSetting.GIT_HUB_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())         // 添加Gson解析
                .addCallAdapterFactory(RetryCallAdapterFactory.create())    // 添加重试机制
                .build();
        return retrofit.create(Api.class);
    }

    @Provides @Singleton
    public JubaoApi providePayApi() {
        /**1. 添加log日志拦截器,方便查看log日志*/
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        /**2. 创建okhttp */
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)                   // 添加日志拦截器
                .addInterceptor(new OAuthInterceptor(context))  // 添加头信息的拦截器
                .addNetworkInterceptor(interceptor)            // 添加网络拦截器
                .readTimeout(60, TimeUnit.SECONDS)             // 读取超时
                .writeTimeout(60, TimeUnit.SECONDS)            // 写超时
                .connectTimeout(60, TimeUnit.SECONDS)          // 链接超时
                .build();
        /**3. 创建retrofit */
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseSetting.BASE_DEBUG_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())         // 添加Gson解析
                .addCallAdapterFactory(RetryCallAdapterFactory.create())    // 添加重试机制
                .build();
        return retrofit.create(JubaoApi.class);
    }

}
