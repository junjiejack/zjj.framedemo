package com.zjj.framedemo.model;

import java.util.List;

/**
 * @author zhoujunjie on 2017/12/23.
 */

public class ProvinceModel {

    /**
     * data : ["北京市","天津市","河北省","山西省","辽宁省","吉林省","黑龙江省","上海市","江苏省","浙江省","安徽省","福建省","江西省","山东省","河南省","湖北省","湖南省","广东省","广西壮族自治区","海南省","重庆市","四川省","贵州省","云南省","陕西省","宁夏回族自治区","内蒙古自治区","青海省","甘肃省","新疆维吾尔自治区"]
     * err_code : 0
     */

    private int err_code;
    private List<String> data;

    public int getErr_code() {
        return err_code;
    }

    public void setErr_code(int err_code) {
        this.err_code = err_code;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
