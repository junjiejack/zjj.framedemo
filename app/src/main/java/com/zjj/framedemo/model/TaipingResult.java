package com.zjj.framedemo.model;

/**
 * @author zhoujunjie on 2017/12/13.
 */

public class TaipingResult {


    /**
     * data : {"id":3,"user_id":1,"product":2,"type":1,"amount":20000,"status":1,"trade_no":"0300201708061723","bill_id":"78061723","pay_url":"https://baoxian.itaiping.com/ipay/web/webPay.html?billId=78061723&sign=CDEA05AE188A7696E7CA44E37045926C","pay_date":"0001-01-01T00:00:00Z","callback_ip":"","created_at":"2017-08-26T10:50:10.344118633+08:00","updated_at":"2017-08-26T10:50:10.344118633+08:00"}
     * err_code : 0
     */

    private DataBean data;
    private int err_code;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getErr_code() {
        return err_code;
    }

    public void setErr_code(int err_code) {
        this.err_code = err_code;
    }

    public static class DataBean {
        /**
         * id : 3
         * user_id : 1
         * product : 2
         * type : 1
         * amount : 20000
         * status : 1
         * trade_no : 0300201708061723
         * bill_id : 78061723
         * pay_url : https://baoxian.itaiping.com/ipay/web/webPay.html?billId=78061723&sign=CDEA05AE188A7696E7CA44E37045926C
         * pay_date : 0001-01-01T00:00:00Z
         * callback_ip :
         * created_at : 2017-08-26T10:50:10.344118633+08:00
         * updated_at : 2017-08-26T10:50:10.344118633+08:00
         */

        private int id;
        private int user_id;
        private int product;
        private int type;
        private int amount;
        private int status;
        private String trade_no;
        private String bill_id;
        private String pay_url;
        private String pay_date;
        private String callback_ip;
        private String created_at;
        private String updated_at;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getProduct() {
            return product;
        }

        public void setProduct(int product) {
            this.product = product;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getTrade_no() {
            return trade_no;
        }

        public void setTrade_no(String trade_no) {
            this.trade_no = trade_no;
        }

        public String getBill_id() {
            return bill_id;
        }

        public void setBill_id(String bill_id) {
            this.bill_id = bill_id;
        }

        public String getPay_url() {
            return pay_url;
        }

        public void setPay_url(String pay_url) {
            this.pay_url = pay_url;
        }

        public String getPay_date() {
            return pay_date;
        }

        public void setPay_date(String pay_date) {
            this.pay_date = pay_date;
        }

        public String getCallback_ip() {
            return callback_ip;
        }

        public void setCallback_ip(String callback_ip) {
            this.callback_ip = callback_ip;
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

        @Override
        public String toString() {
            return "DataBean{" +
                    "id=" + id +
                    ", user_id=" + user_id +
                    ", product=" + product +
                    ", type=" + type +
                    ", amount=" + amount +
                    ", status=" + status +
                    ", trade_no='" + trade_no + '\'' +
                    ", bill_id='" + bill_id + '\'' +
                    ", pay_url='" + pay_url + '\'' +
                    ", pay_date='" + pay_date + '\'' +
                    ", callback_ip='" + callback_ip + '\'' +
                    ", created_at='" + created_at + '\'' +
                    ", updated_at='" + updated_at + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "TaipingResult{" +
                "data=" + data +
                ", err_code=" + err_code +
                '}';
    }
}
