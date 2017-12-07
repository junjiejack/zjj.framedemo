package com.rambler;

/**
 * Created by maocheng on 16/9/19.
 */

public class Constants {
    public static final int RESPONSE_NETWORK_ERROR = 0;
    public static final int RESPONSE_UNKNOWN_ERROR = 1;
    public static final int RESPONSE_SERVER_ERROR = 2;
    public static final int RESPONSE_DATA_EMPTY = 3;
    public static final int RESPONSE_AUTH_ERROR = 4;

    public static final String KEY_UPDATE_TIME = "KEY_UPDATE_TIME";

    //登录信息缓存
    public static final String SP_USER_TOKEN = "SP_USER_TOKEN";
    public static final String SP_USER_ID = "SP_USER_ID";
    public static final String SP_USER_REFRESH_TOKEN = "SP_USER_REFRESH_TOKEN";
    public static final String SP_USER_NAME = "SP_USER_NAME";
    public static final String SP_USER_PHONE = "SP_USER_PHONE";
    public static final String SP_LOGIN_AUTHCODE = "SP_LOGIN_AUTHCODE";
    public static final String SP_HAS_PASSWORD = "SP_HAS_PASSWORD";
    public static final String SP_USER_REAL_NAME = "SP_USER_REAL_NAME";
    public static final String SP_USER_NICK_NAME = "SP_USER_NICK_NAME";
    public static final String SP_USER_HEAD_IMAGE = "SP_USER_HEAD_IMAGE";
    public static final String SP_USER_DEMAND_COUNT = "SP_USER_DEMAND_COUNT";
    public static final String SP_USER_RESUME_COUNT = "SP_USER_RESUME_COUNT";

    public static final String SP_USER_SERVICE_CITY = "SP_USER_SERVICE_CITY";
    public static final String SP_USER_SERVICE_CITY_NAME = "SP_USER_SERVICE_CITY_NAME";

    public static final String SP_USER_LOCATION = "SP_USER_LOCATION";

    public static final String SP_GUIDE = "SP_GUIDE";

    //验证码获取周期
    public static final int VERIFY_CYCLE = 60 * 1000;
}
