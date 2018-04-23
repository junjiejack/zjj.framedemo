package com.zjj.framedemo.modules.main.custom;

import android.content.Intent;
import android.view.View;

import com.rambler.base.BaseFragment;
import com.zjj.framedemo.R;
import com.zjj.framedemo.modules.wechat.WechatNineActivity;

import butterknife.OnClick;

/**
 * Created by zhoujunjie on 2018/4/11.
 */

public class CustomFragment extends BaseFragment{


    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_custom;
    }

    @OnClick({R.id.btn_wechat_nine})
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.btn_wechat_nine:
                intent.setClass(getActivity(), WechatNineActivity.class);
                break;
        }
        startActivity(intent);
    }
}
