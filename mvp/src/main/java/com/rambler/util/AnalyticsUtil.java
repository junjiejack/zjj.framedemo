package com.rambler.util;

import android.content.Context;
import com.umeng.analytics.MobclickAgent;
import retrofit2.adapter.rxjava.HttpException;

/**
 * 数据统计辅助类
 *
 * @author lixiaofan on 2017/3/1.
 */

public class AnalyticsUtil {

  /**
   * 初始化统计配置
   */
  public static void initConfigure() {
    MobclickAgent.setDebugMode(false);
    MobclickAgent.openActivityDurationTrack(false);
  }

  /**
   * 统计页面暂停数据
   * @param context 上下文
   * @param pageName 页面名称
   */
  public static void onPause(Context context, String pageName) {
    MobclickAgent.onPageEnd(pageName);
    MobclickAgent.onPause(context);
  }

  /**
   * 统计页面显示数据
   * @param context 上下文
   * @param pageName 页面名称
   */
  public static void onResume(Context context, String pageName) {
    MobclickAgent.onPageStart(pageName);
    MobclickAgent.onResume(context);
  }

  /**
   * 统计fragment暂停数据
   * @param pageName 页面名称
   */
  public static void onPageEnd(String pageName) {
    MobclickAgent.onPageEnd(pageName);
  }

  /**
   * 统计fragment显示数据
   *
   * @param pageName 页面名称
   */
  public static void onPageStart(String pageName) {
    MobclickAgent.onPageStart(pageName);
  }

  /**
   * 上报错误日志
   * @param context 上下文
   * @param error 错误信息
   */
  public static void reportError(Context context, String error) {
    MobclickAgent.reportError(context, error);
  }

  /**
   * 上报错误日志
   * @param context 上下文
   * @param error 错误堆栈信息
   */
  public static void reportError(Context context, Throwable error) {
    if (error instanceof HttpException) {
      HttpException exception = (HttpException) error;
      if (exception != null
          && exception.response() != null
          && exception.response().raw() != null
          && exception.response().raw().request() != null) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("网络情况：" + (NetworkUtils.is3G(context) ? "mobile" : "wifi") +"\n");
        stringBuilder.append(exception.response().raw().request().toString());
        if(exception.response().raw().request().headers() != null){
          stringBuilder.append(exception.response().raw().request().headers().toString());
        }
        stringBuilder.append(exception.response().raw().toString());
        MobclickAgent.reportError(context, stringBuilder.toString());
        return;
      }
    }
    MobclickAgent.reportError(context, error);
  }
}
