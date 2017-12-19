package com.zjj.framedemo.base;

import com.zjj.framedemo.BuildConfig;

/**
 * @author zhoujunjie on 2017/12/7.
 */

public class BaseSetting {

    public static final String BUILD_TYPE_PD = "release";
    public static final String BUILD_TYPE_QA = "debug";

    public static String baseShareUrl = BaseSetting.BASE_SHARE_URL;
    public static String baseUrl = BaseSetting.BASE_DEBUG_URL;

    /** 测试华泰支付 */
    public static final String HUA_TAI_URL = "http://pay.lisea.cn";

    /**
     * 测试 产品 分享 环境的url
     */
    public static final String BASE_DEBUG_URL = "http://test.jubao56.com";
    public static final String BASE_RELEASE_URL = "https://www.jubao56.com";
    public static final String BASE_SHARE_URL = "http://www.jubao56.com";

    static {
        try {
            if (BUILD_TYPE_PD.equals(BuildConfig.BUILD_TYPE)) {
                baseUrl = BaseSetting.BASE_RELEASE_URL;
                baseShareUrl = BaseSetting.BASE_SHARE_URL;
            } else if (BUILD_TYPE_QA.equals(BuildConfig.BUILD_TYPE)) {
                baseUrl = BaseSetting.BASE_DEBUG_URL;
                baseShareUrl = BaseSetting.BASE_DEBUG_URL;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
