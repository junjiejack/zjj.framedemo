package com.zjj.framedemo.utils;

import java.io.UnsupportedEncodingException;

/**
 * Created by mayn on 2017/11/9.
 */

public class BaseUtil {
    /**
     * desc:将数组转为16进制
     * @param byteArray
     * @return
     * modified:
     */
    public static String byteArrayToHexStr(byte[] byteArray) {
        if (byteArray == null){
            return null;
        }
        char[] hexArray = "0123456789ABCDEF".toCharArray();
        char[] hexChars = new char[byteArray.length * 2];
        for (int j = 0; j < byteArray.length; j++) {
            int v = byteArray[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }


    public static byte[] hexStrToByteArray(String str)
    {
        if (str == null) {
            return null;
        }
        if (str.length() == 0) {
            return new byte[0];
        }
        byte[] byteArray = new byte[str.length() / 2];
        for (int i = 0; i < byteArray.length; i++){
            String subStr = str.substring(2 * i, 2 * i + 2);
            byteArray[i] = ((byte)Integer.parseInt(subStr, 16));
        }
        return byteArray;
    }

    public static String toUtf8(String str) throws UnsupportedEncodingException {
        return new String(str.getBytes("UTF-8"),"UTF-8");
    }

    public static String toGBK(String str) throws UnsupportedEncodingException {
        return new String(str.getBytes("GBK"),"GBK");
    }

    public static String getTime(String seconds) {
        int second = Integer.valueOf(seconds);
        int minute = second / 60;
        if(minute <= 0) {
            return "00:"+ String.valueOf(second/10)+String.valueOf(second%10);
        }
        second = second % 60;
        int hours = minute / 60;
        if(hours <= 0) {
            return String.valueOf(minute/10)+String.valueOf(minute%10)+":"
                    +String.valueOf(second/10)+String.valueOf(second%10);
        }else {
            return String.valueOf(hours/10)+String.valueOf(hours%10)+":"
                    +String.valueOf(minute/10)+String.valueOf(minute%10)+":"
                    +String.valueOf(second/10)+String.valueOf(second%10);
        }
    }

    public static boolean isNumeric(String str)
    {
        for (int i = 0; i < str.length(); i++) {
            System.out.println(str.charAt(i));
            if (!Character.isDigit(str.charAt(i)))
            {
                return false;
            }
        }
        return true;
    }
}
