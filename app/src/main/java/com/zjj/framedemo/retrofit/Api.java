package com.zjj.framedemo.retrofit;

import com.zjj.framedemo.model.PayInfo;
import com.zjj.framedemo.modules.share.SystemShareModel;

import java.util.HashMap;
import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * @author zhoujunjie on 2017/12/7.
 * 请求的网络接口
 */

public interface Api {

    @POST("/pay/init")
    Observable<PayInfo> getPayInfo(@Body HashMap<String,Integer> map);

    @GET("/users")
    Observable<List<SystemShareModel>> getImage();

}
