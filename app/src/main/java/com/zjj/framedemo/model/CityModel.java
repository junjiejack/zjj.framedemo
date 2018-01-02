package com.zjj.framedemo.model;

import java.util.List;

/**
 * @author zhoujunjie on 2017/12/23.
 */

public class CityModel {


    /**
     * data : [{"id":157,"province":"湖北省","city":"武汉市","city_code":"027","latitude":"30.584355","longitude":"114.298572","created_at":"0001-01-01T00:00:00Z"},{"id":158,"province":"湖北省","city":"黄石市","city_code":"0714","latitude":"30.220074","longitude":"115.077048","created_at":"0001-01-01T00:00:00Z"},{"id":159,"province":"湖北省","city":"十堰市","city_code":"0719","latitude":"32.646907","longitude":"110.787916","created_at":"0001-01-01T00:00:00Z"},{"id":160,"province":"湖北省","city":"宜昌市","city_code":"0717","latitude":"30.702636","longitude":"111.290843","created_at":"0001-01-01T00:00:00Z"},{"id":161,"province":"湖北省","city":"襄阳市","city_code":"0710","latitude":"32.042426","longitude":"112.144146","created_at":"0001-01-01T00:00:00Z"},{"id":162,"province":"湖北省","city":"鄂州市","city_code":"0711","latitude":"30.396536","longitude":"114.890593","created_at":"0001-01-01T00:00:00Z"},{"id":163,"province":"湖北省","city":"荆门市","city_code":"0724","latitude":"31.03542","longitude":"112.204251","created_at":"0001-01-01T00:00:00Z"},{"id":164,"province":"湖北省","city":"孝感市","city_code":"0712","latitude":"30.926423","longitude":"113.926655","created_at":"0001-01-01T00:00:00Z"},{"id":165,"province":"湖北省","city":"荆州市","city_code":"0716","latitude":"30.326857","longitude":"112.23813","created_at":"0001-01-01T00:00:00Z"},{"id":166,"province":"湖北省","city":"黄冈市","city_code":"0713","latitude":"30.447711","longitude":"114.879365","created_at":"0001-01-01T00:00:00Z"},{"id":167,"province":"湖北省","city":"咸宁市","city_code":"0715","latitude":"29.832798","longitude":"114.328963","created_at":"0001-01-01T00:00:00Z"},{"id":168,"province":"湖北省","city":"随州市","city_code":"0722","latitude":"31.717497","longitude":"113.37377","created_at":"0001-01-01T00:00:00Z"},{"id":169,"province":"湖北省","city":"恩施土家族苗族自治州","city_code":"0718","latitude":"30.283114","longitude":"109.48699","created_at":"0001-01-01T00:00:00Z"},{"id":170,"province":"湖北省","city":"仙桃市","city_code":"0728","latitude":"30.364953","longitude":"113.453974","created_at":"0001-01-01T00:00:00Z"},{"id":171,"province":"湖北省","city":"潜江市","city_code":"2728","latitude":"30.421215","longitude":"112.896866","created_at":"0001-01-01T00:00:00Z"},{"id":172,"province":"湖北省","city":"天门市","city_code":"1728","latitude":"30.653061","longitude":"113.165862","created_at":"0001-01-01T00:00:00Z"},{"id":175,"province":"湖北省","city":"神农架林区","city_code":"1719","latitude":"31.744449","longitude":"110.671525","created_at":"0001-01-01T00:00:00Z"}]
     * err_code : 0
     */

    private int err_code;
    private List<DataBean> data;

    public int getErr_code() {
        return err_code;
    }

    public void setErr_code(int err_code) {
        this.err_code = err_code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 157
         * province : 湖北省
         * city : 武汉市
         * city_code : 027
         * latitude : 30.584355
         * longitude : 114.298572
         * created_at : 0001-01-01T00:00:00Z
         */

        private int id;
        private String province;
        private String city;
        private String city_code;
        private String latitude;
        private String longitude;
        private String created_at;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCity_code() {
            return city_code;
        }

        public void setCity_code(String city_code) {
            this.city_code = city_code;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }
    }
}
