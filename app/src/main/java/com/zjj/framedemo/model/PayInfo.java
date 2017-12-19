package com.zjj.framedemo.model;

import java.io.Serializable;

/**
 * @author zhoujunjie on 2017/12/11.
 */

public class PayInfo implements Serializable{

    private String html;
    private String url; 		//string 	华泰在线支付url
    private String channel_code; 	//string 	渠道编码
    private String trade_type; 	//string 	交易类型
    private String trade_name; 	//string 	交易名称
    private String trade_time; 	//string 	交易时间
    private String order_no; 	//string  订单编号
    private String money; 		//string 	交易金额
    private String sign; 		//string 	数字签名
    private String IsWXH5; 		//string 	微信H5支付
    private String spbillCreateIp;  // 客户端IP
    private String sceneInfo;       // 场景信息

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getChannel_code() {
        return channel_code;
    }

    public void setChannel_code(String channel_code) {
        this.channel_code = channel_code;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getTrade_name() {
        return trade_name;
    }

    public void setTrade_name(String trade_name) {
        this.trade_name = trade_name;
    }

    public String getTrade_time() {
        return trade_time;
    }

    public void setTrade_time(String trade_time) {
        this.trade_time = trade_time;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getIsWXH5() {
        return IsWXH5;
    }

    public void setIsWXH5(String isWXH5) {
        IsWXH5 = isWXH5;
    }

    public String getSpbillCreateIp() {
        return spbillCreateIp;
    }

    public void setSpbillCreateIp(String spbillCreateIp) {
        this.spbillCreateIp = spbillCreateIp;
    }

    public String getSceneInfo() {
        return sceneInfo;
    }

    public void setSceneInfo(String sceneInfo) {
        this.sceneInfo = sceneInfo;
    }
}
