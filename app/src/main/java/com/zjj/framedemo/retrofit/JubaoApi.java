package com.zjj.framedemo.retrofit;

import com.zjj.framedemo.model.CityModel;
import com.zjj.framedemo.model.ProvinceModel;
import com.zjj.framedemo.model.LoginResult;
import com.zjj.framedemo.model.MyPremiumModel;
import com.zjj.framedemo.model.TaipingResult;

import java.util.HashMap;
import java.util.Map;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * @author zhoujunjie on 2017/12/13.
 */

public interface JubaoApi {

    @POST("/v1/cmn/user/login")
    Observable<LoginResult> getAccessToken(@Body HashMap<String,String> map);

    @POST("/v1/zcb/deposit/new")
    Observable<TaipingResult> getTaiping(@Body HashMap map);

    @GET("/v1/cmn/biz/statisticsnew/list")
    Observable<MyPremiumModel> getPremiumList(@QueryMap Map<String,String> map);

    @GET("/v1/cmn/province/list")
    Observable<ProvinceModel> getProvinceList();

    @GET("/v1/cmn/city/list")
    Observable<CityModel> getCityList(@Query("province") String province);
}
