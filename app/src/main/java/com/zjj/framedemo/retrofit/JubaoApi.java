package com.zjj.framedemo.retrofit;

import com.zjj.framedemo.model.LoginResult;
import com.zjj.framedemo.model.TaipingResult;

import java.util.HashMap;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * @author zhoujunjie on 2017/12/13.
 */

public interface JubaoApi {

    @POST("/v1/cmn/user/login")
    Observable<LoginResult> getAccessToken(@Body HashMap<String,String> map);

    @POST("/v1/zcb/deposit/new")
    Observable<TaipingResult> getTaiping(@Body HashMap map);
}
