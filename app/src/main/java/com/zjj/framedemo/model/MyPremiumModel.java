package com.zjj.framedemo.model;

import java.util.List;

/**
 * @author zhoujunjie on 2017/12/20.
 */

public class MyPremiumModel {/**
 * err_code : 0
 * rows : [{"channel_id":67,"year":2016,"month":12,"price_total":972500,"price_zcb":972500,"price_ygb":0,"price_spb":0,"is_settlement":false},{"channel_id":67,"year":2016,"month":11,"price_total":805600,"price_zcb":805600,"price_ygb":0,"price_spb":0,"is_settlement":false},{"channel_id":67,"year":2016,"month":10,"price_total":577700,"price_zcb":577700,"price_ygb":0,"price_spb":0,"is_settlement":false},{"channel_id":67,"year":2016,"month":9,"price_total":592600,"price_zcb":592600,"price_ygb":0,"price_spb":0,"is_settlement":false},{"channel_id":67,"year":2016,"month":8,"price_total":585100,"price_zcb":585100,"price_ygb":0,"price_spb":0,"is_settlement":false},{"channel_id":67,"year":2016,"month":7,"price_total":657600,"price_zcb":657600,"price_ygb":0,"price_spb":0,"is_settlement":false},{"channel_id":67,"year":2016,"month":6,"price_total":289500,"price_zcb":289500,"price_ygb":0,"price_spb":0,"is_settlement":false},{"channel_id":67,"year":2016,"month":5,"price_total":55000,"price_zcb":55000,"price_ygb":0,"price_spb":0,"is_settlement":false},{"channel_id":67,"year":2016,"month":4,"price_total":40000,"price_zcb":40000,"price_ygb":0,"price_spb":0,"is_settlement":false},{"channel_id":67,"year":2016,"month":3,"price_total":20000,"price_zcb":20000,"price_ygb":0,"price_spb":0,"is_settlement":false}]
 */

private int err_code;
    private List<RowsBean> rows;

    public int getErr_code() {
        return err_code;
    }

    public void setErr_code(int err_code) {
        this.err_code = err_code;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean {

        /**
         * id : 2
         * channel_id : 0
         * year : 2015
         * month : 12
         * product_type : 2
         * product_name : 聚保整车保
         * premium : 60
         * created_at : 2017-12-19T12:43:39+08:00
         * updated_at : 2017-12-19T12:43:39+08:00
         */

        private int id;
        private int channel_id;
        private int year;
        private int month;
        private int product_type;
        private String product_name;
        private int premium;
        private String created_at;
        private String updated_at;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getChannel_id() {
            return channel_id;
        }

        public void setChannel_id(int channel_id) {
            this.channel_id = channel_id;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public int getProduct_type() {
            return product_type;
        }

        public void setProduct_type(int product_type) {
            this.product_type = product_type;
        }

        public String getProduct_name() {
            return product_name;
        }

        public void setProduct_name(String product_name) {
            this.product_name = product_name;
        }

        public int getPremium() {
            return premium;
        }

        public void setPremium(int premium) {
            this.premium = premium;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }
    }
}

