package com.zjj.framedemo.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.DisplayMetrics;


/**
 * @author kevin on 2017/11/6.
 */

public class DisplayUtil {

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

//    public static boolean checkPhone(Context context, String phone) {
//        if (TextUtils.isEmpty(phone.trim())) {
//            ToastUtil.showToast(context, R.string.tv_empty_phone_hint);
//            return false;
//        } else if (phone.length() < 11) {
//            ToastUtil.showToast(context,R.string.tv_length_phone_hint);
//            return false;
//        }
//        return true;
//    }
//
//    public static boolean checkCode(Context context, String code) {
//        if (TextUtils.isEmpty(code.trim())) {
//            ToastUtil.showToast(context,R.string.tv_empty_code_hint);
//            return false;
//        } else if (code.length() < 6) {
//            ToastUtil.showToast(context,R.string.tv_error_code_hint);
//            return false;
//        }
//        return true;
//    }
//
//    public static boolean checkPwd(Context context, String pwd) {
//        if (TextUtils.isEmpty(pwd.trim())) {
//            ToastUtil.showToast(context,R.string.tv_empty_pwd_hint);
//            return false;
//        } else if (pwd.length() < 6) {
//            ToastUtil.showToast(context,R.string.tv_length_pwd_hint);
//            return false;
//        }
//        return true;
//    }
//
//    public static boolean checkPwdEnsure(Context context, String pwd, String pwdEnsure) {
//        if (checkPwd(context, pwd.trim())) {
//            if (!pwd.equals(pwdEnsure)) {
//                ToastUtil.showToast(context,R.string.tv_ensure_pwd_hint);
//                return false;
//            }
//        }
//        return true;
//    }
//
//
//    public static boolean checkIdNumber(Context context, String idNumber) {
//        if (TextUtils.isEmpty(idNumber.trim())) {
//            ToastUtil.showToast(context,R.string.tv_empty_id_number_hint);
//            return false;
//        } else if (idNumber.length() != 18) {
//            ToastUtil.showToast(context,R.string.tv_error_id_number_hint);
//            return false;
//        }
//        return true;
//    }

    public static int getScreenWidth(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    public static int getScreenHeight(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

    /**
     * 返回当前程序版本名
     */
    public static String getAppVersionName(Context context) {
        String versionName = "";
        try {
            // ---get the package info---
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
        }
        return versionName;
    }

    /**
     * 身份证号替换，保留前四位和后四位
     *
     * 如果身份证号为空 或者 null ,返回null ；否则，返回替换后的字符串；
     *
     * @param idCard 身份证号
     * @return
     */
    public static String idCardReplaceWithStar(String idCard) {

        if (idCard.isEmpty()) {
            return "";
        } else {
            return replaceAction(idCard, "(?<=\\d{4})\\d(?=\\d{4})");
        }
    }

    /**
     * 银行卡替换，保留后四位
     *
     * 如果银行卡号为空 或者 null ,返回null ；否则，返回替换后的字符串；
     *
     * @param bankCard 银行卡号
     * @return
     */
    public static String bankCardReplaceWithStar(String bankCard) {

        if (bankCard.isEmpty()) {
            return "";
        } else {
            StringBuilder builder = new StringBuilder();
            for (int i = 1; i <= 15;i++) {
                if (i%5 == 0) {
                    builder.append(" ");
                } else {
                    builder.append("*");
                }
            }
            builder.append(bankCard.substring(bankCard.length()-4,bankCard.length()));
            return builder.toString();
        }
    }

    /**
     * 手机号替换，保留前三位和后四位
     *
     * 如果手机号为空 或者 null ,返回null ；否则，返回替换后的字符串；
     *
     * @param idCard 身份证号
     * @return
     */
    public static String phoneNumReplaceWithStar(String idCard) {

        if (idCard.isEmpty()) {
            return "";
        } else {
            return replaceAction(idCard, "(?<=\\d{3})\\d(?=\\d{4})");
        }
    }

    /**
     * 实际替换动作
     *
     * @param regular  正则
     * @return
     */
    private static String replaceAction(String string, String regular) {
        return string.replaceAll(regular, "*");
    }

}
