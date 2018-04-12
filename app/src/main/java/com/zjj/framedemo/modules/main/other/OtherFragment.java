package com.zjj.framedemo.modules.main.other;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.rambler.base.BaseFragment;
import com.zjj.framedemo.R;
import com.zjj.framedemo.modules.coordinatorlayout.CLCollapsingActivity;
import com.zjj.framedemo.modules.coordinatorlayout.ToolBarHideActivity;

import butterknife.OnClick;

/**
 * Created by zhoujunjie on 2018/4/11.
 */

public class OtherFragment extends BaseFragment {

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_other;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @OnClick({R.id.btn_cl_collapsing,R.id.btn_cl_toolbar})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.btn_cl_collapsing:
                intent.setClass(getActivity(), CLCollapsingActivity.class);
                break;
            case R.id.btn_cl_toolbar:
                intent.setClass(getActivity(), ToolBarHideActivity.class);
                break;
        }
        startActivity(intent);
    }
}
