package com.zjj.framedemo.modules.baopay;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.juhe.juhesdk.JuHeAliPay;
import com.juhe.juhesdk.JuHeWxPay;
import com.zjj.framedemo.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.util.List;

/**
 * 下单界面
 */
public class OrderActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv_back;
    private Button mSubmitBtn;
    private LinearLayout llWeixinPay;
    private LinearLayout llZhifubaoPay;
    private LinearLayout ll_log;
    private boolean isCheckedWeixin = true;
    private boolean isCheckedZhifubao = false;
    private String testTokenId;
    private ImageView imgWeixinChecked;
    private ImageView imgZhifubaoChecked;
    private ImageView iv_logo;
    private TextView tvAmount;
    private int cj = 2;//默认生产环境
    private int amg = 0;//默认0分
    private boolean isAlipay = false;

    private JuHeAliPay juHeAliPay;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();

            //微信请求
            String weiXinValue = data.getString("weiXinValue");
            if (weiXinValue != null) {
                if (!weiXinValue.equals("")) {
                    //log
                    printLog("订单号返回成功!");

                    callWeiXin(weiXinValue);
                } else if (weiXinValue.equals("")) {
                    //log
                    printLog("订单号返回失败!");
                }
            }


            //支付宝请求
            String aliPayValue = data.getString("aliPayValue");
            if (aliPayValue != null) {
                if (!aliPayValue.equals("")) {
                    //log
                    printLog("订单号返回成功!");

                    calllAliPay(aliPayValue);
                    Log.d("aliPayValue值", aliPayValue);
                } else if (aliPayValue.equals("")) {
                    //log
                    printLog("订单号返回失败!");
                }
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        initView();
        initData();
    }

    private void initView() {
        iv_logo = (ImageView) findViewById(R.id.iv_logo);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        tvAmount = (TextView) findViewById(R.id.tv_amount);
        mSubmitBtn = (Button) findViewById(R.id.btn_sure_pay);
        llWeixinPay = (LinearLayout) findViewById(R.id.ll_weixin_pay);
        llZhifubaoPay = (LinearLayout) findViewById(R.id.ll_zhifubao_pay);
        ll_log = (LinearLayout) findViewById(R.id.ll_log);
        imgWeixinChecked = (ImageView) findViewById(R.id.img_weixin_checked);
        imgZhifubaoChecked = (ImageView) findViewById(R.id.img_zhifubao_checked);

        iv_logo.setOnClickListener(this);
        iv_back.setOnClickListener(this);
        llWeixinPay.setOnClickListener(this);
        llZhifubaoPay.setOnClickListener(this);
        mSubmitBtn.setOnClickListener(this);
    }

    private void initData() {
        amg = getIntent().getIntExtra("INPUTAMOUNT", 1);
        tvAmount.setText(amg + "");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sure_pay:
                ll_log.removeAllViews();

                //微信
                if (isCheckedWeixin) {
                    //log
                    printLog("请求微信订单号");
                    //调用微信
                    new Thread(weiXinTokenIdNetworkTask).start();
                }
                //支付宝
                if (isCheckedZhifubao) {
                    //log
                    printLog("请求支付宝订单号");
                    //调用支付宝
                    new Thread(aliPayTokenIdNetworkTask).start();

                }

                break;
            case R.id.ll_weixin_pay:
                if (!isCheckedWeixin) {
                    isCheckedWeixin = !isCheckedWeixin;
                    isCheckedZhifubao = !isCheckedZhifubao;
                    imgWeixinChecked.setImageDrawable(getResources().getDrawable(R.drawable.checked));
                    imgZhifubaoChecked.setImageDrawable(getResources().getDrawable(R.drawable.unchecked));
                }
                break;
            case R.id.ll_zhifubao_pay:
                if (!isCheckedZhifubao) {
                    isCheckedWeixin = !isCheckedWeixin;
                    isCheckedZhifubao = !isCheckedZhifubao;
                    imgWeixinChecked.setImageDrawable(getResources().getDrawable(R.drawable.unchecked));
                    imgZhifubaoChecked.setImageDrawable(getResources().getDrawable(R.drawable.checked));
                }
                break;
//            case R.id.iv_logo:
//                new EnvironmnetDialog(OrderActivity.this) {
//                    @Override
//                    protected void setEnvironment(int cjE) {
//                        cj = cjE;
//                        this.dismiss();
//                    }
//                }.show();
//                break;
            case R.id.iv_back:
                finish();
                break;
            default:
                break;
        }
    }

    /**
     * 微信相关操作
     *
     * @param testTokenId
     */
    //微信:1、请求TokenID
    Runnable weiXinTokenIdNetworkTask = new Runnable() {
        @Override
        public void run() {
            //测试:请求订单号(TokenId)
            String url = "http://vgw.baofoo.com/fi-test//appWeChat?cj=" + cj + "&amg=" + amg + "&appId=wx5f490891bf38366c";//amg:金额,cj:环境(1测试2正式3准生产)
            Log.d("微信请求TokenId地址", url);

            //测试:Get请求
            HttpGet httpRequst = new HttpGet(url);
            try {
                HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequst);
                if (httpResponse.getStatusLine().getStatusCode() == 200) {
                    HttpEntity httpEntity = httpResponse.getEntity();
                    String entityString = EntityUtils.toString(httpEntity);
                    Log.d("微信:Token请求返回", entityString);
                    String testTokenId = entityString.substring(entityString.indexOf("=") + 1, entityString.length());
                    Log.d("Token返回的TokenId", testTokenId);

                    Message msg = new Message();
                    Bundle data = new Bundle();
                    data.putString("weiXinValue", testTokenId);
                    msg.setData(data);
                    handler.sendMessage(msg);
                } else {
                    Message msg = new Message();
                    Bundle data = new Bundle();
                    data.putString("weiXinValue", "");
                    msg.setData(data);
                    handler.sendMessage(msg);
                }
            } catch (Exception e) {
                e.printStackTrace();

                Message msg = new Message();
                Bundle data = new Bundle();
                data.putString("weiXinValue", "");
                msg.setData(data);
                handler.sendMessage(msg);
            }
        }
    };

    //微信:2、启动微信客户端
    private void callWeiXin(String testTokenId) {

        //log
        printLog("调用微信支付...");

        //调用微信支付
        JuHeWxPay juHeWxPay = new JuHeWxPay(OrderActivity.this, testTokenId, "");
        juHeWxPay.execute();
    }

    /**
     * 支付宝相关操作
     */
    //支付宝:1、请求tokenId
    Runnable aliPayTokenIdNetworkTask = new Runnable() {
        @Override
        public void run() {
            //商户请求token_id
            String url = "http://vgw.baofoo.com/fi-test//appAliPay?cj=" + cj + "&amg=" + amg;
            HttpGet httpRequst = new HttpGet(url);
            try {
                HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequst);
                if (httpResponse.getStatusLine().getStatusCode() == 200) {
                    HttpEntity httpEntity = httpResponse.getEntity();
                    String entityString = EntityUtils.toString(httpEntity);
                    Log.d("Token:AliPay请求返回", entityString);
                    testTokenId = entityString.substring(entityString.indexOf("=") + 1, entityString.length());
                    Log.d("Token:AliPay返回的TokenId", testTokenId);

                    Message msg = new Message();
                    Bundle data = new Bundle();
                    data.putString("aliPayValue", testTokenId);
                    msg.setData(data);
                    handler.sendMessage(msg);
                } else {
                    //处理错误
                    Message msg = new Message();
                    Bundle data = new Bundle();
                    data.putString("aliPayValue", "");
                    msg.setData(data);
                    handler.sendMessage(msg);
                }
            } catch (Exception e) {
                e.printStackTrace();

                Message msg = new Message();
                Bundle data = new Bundle();
                data.putString("aliPayValue", "");
                msg.setData(data);
                handler.sendMessage(msg);
            }
        }
    };

    //支付宝:2、得到订单号(tokenId)后调用支付宝支付
    private void calllAliPay(String testTokenId) {

        //log
        printLog("调用支付宝支付...");
        isAlipay = true;

        /**
         * 调用支付宝
         * 1、默认样式:new JuHeAliPay(this, testTokenId)
         * 2、透明样式:new JuHeAliPay(this, testTokenId, R.style.Dialog_Fullscreen)
         */
        juHeAliPay = new JuHeAliPay(this, testTokenId, R.style.Dialog_Fullscreen) {
            @Override
            protected void ret(String result) {
                //result 是json窜,retCode如下:
                //0000	交易成功
                //0008	报文交易要素缺失memberId|transid缺少
                //0050	支付超时
                //1000	系统异常，请稍后再试
                //0301	系统繁忙，请稍后再试
                //0312	此笔订单未支付成功

                //log 显示结果
                printLog("支付宝返回:" + result);

                //app回到前台
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        moveTaskToFront();
                    }
                },1500);
            }
        };
        juHeAliPay.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(isAlipay) {
            isAlipay = false;

            /**
             *  跳转到支付宝,BaofooJuHeAliPayTask默认是等待支付结果(等待商户支付,默认是5分钟等待)
             *  如果不想等待,使用这个方法,手动获取支付宝支付结果,结果会在BaofooJuHeAliPayTask的回调中立即返回(想使用这个功能,开启这段代码即可)
             *  注意:此方法为调用支付宝回调结果查询,不可单独作为结果查询(比如app被杀掉,重新进入支付页面,不能立即调用这个方法)
             */
            juHeAliPay.toGetAliPayResult();
        }
    }

    /**
     * log打印
     * @param msg
     */
    private void printLog(String msg) {
        TextView textView = new TextView(this);
        textView.setTextSize(12);
        textView.setText("--> " + msg);

        ll_log.addView(textView);
    }

    /**
     * 将app唤醒到前台
     */
    public void moveTaskToFront() {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> task_info = manager.getRunningTasks(20);
        for (ActivityManager.RunningTaskInfo info : task_info) {
            if (info.topActivity.getPackageName().indexOf("com.juhe") != -1) {//替换成你的项目包名
                manager.moveTaskToFront(info.id, 0);
                return;
            }
        }
    }
}
