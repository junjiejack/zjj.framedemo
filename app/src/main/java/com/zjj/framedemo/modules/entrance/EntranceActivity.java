package com.zjj.framedemo.modules.entrance;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rambler.base.BaseActivity;
import com.zjj.framedemo.AppInterfaceTest;
import com.zjj.framedemo.R;
import com.zjj.framedemo.modules.LoginActivity;
import com.zjj.framedemo.modules.baopay.OrderActivity;
import com.zjj.framedemo.modules.custom.CustomTagActivity;
import com.zjj.framedemo.modules.insure.InsureActivity;
import com.zjj.framedemo.modules.pay.HuaTaiPayActivity;
import com.zjj.framedemo.modules.premium.PremiumActivity;
import com.zjj.framedemo.modules.taiping.TaipingActivity;
import com.zjj.framedemo.utils.DisplayUtil;
import com.zjj.framedemo.view.FlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zhoujunjie on 2018/4/11.
 */

public class EntranceActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrance);
        ButterKnife.bind(this);
        performRequestPermissions(new String[] {
                Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE
        }, 123, null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                AppInterfaceTest test = new AppInterfaceTest();
                try {
                    test.testPolicyJsonBean();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    @OnClick({R.id.btn_login,R.id.btn_hua_tai,R.id.btn_bao_pay,R.id.btn_tai_ping_pay,R.id.btn_flow_layout,
            R.id.btn_list,R.id.btn_tou_bao,R.id.btn_tag})
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.btn_login :
                intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_hua_tai:
                intent = new Intent(this, HuaTaiPayActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_bao_pay:
                intent = new Intent(this, OrderActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_tai_ping_pay:
                intent = new Intent(this, TaipingActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_flow_layout:
                showCheckDialog();
                break;
            case R.id.btn_list:
                intent = new Intent(this, PremiumActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_tou_bao:
                intent = new Intent(this, InsureActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_tag:
                intent = new Intent(this,CustomTagActivity.class);
                startActivity(intent);
                break;
        }

    }

    private String[] mDatas = new String[]{"整车保","普货保","员工保","商铺档口火灾保","大宗货物保","冷链保"};

    private void showCheckDialog() {
        final Dialog dialog = new Dialog(this, R.style.MyDialog);
        final View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_sift_insurance, null);
        dialog.setContentView(dialogView);
        ViewGroup.LayoutParams layoutParams = dialogView.getLayoutParams();
        layoutParams.width = getResources().getDisplayMetrics().widthPixels;
        dialogView.setLayoutParams(layoutParams);
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.show();//显示对话框
        FlowLayout flowLayout = dialogView.findViewById(R.id.flow_layout);

        List<Boolean> list = new ArrayList<>();
        // 循环添加TextView到容器
        for (int i = 0; i < mDatas.length; i++) {
            list.add(false);
            final TextView view = new TextView(this);
            view.setText(mDatas[i]);
            view.setPadding(DisplayUtil.dip2px(this,10), DisplayUtil.dip2px(this,5), DisplayUtil.dip2px(this,10), DisplayUtil.dip2px(this,5));
            view.setGravity(Gravity.CENTER);
            view.setTextSize(18);
            view.setClickable(true);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            view.setTextColor(getResources().getColorStateList(R.color.btn_sift_selector));
            view.setBackgroundResource(R.drawable.bg_btn_circle_sift_selector);
            flowLayout.addView(view);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
