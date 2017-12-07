package com.zjj.framedemo.retrofit;

import com.zjj.framedemo.model.LoginResult;

import java.util.HashMap;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * @author zhoujunjie on 2017/12/7.
 * 请求的网络接口
 */

public interface Api {

    @POST("/v1/cmn/user/login")
    Observable<LoginResult> getAccessToken(@Body HashMap<String,String> map);
}
