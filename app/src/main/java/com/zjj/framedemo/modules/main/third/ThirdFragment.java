package com.zjj.framedemo.modules.main.third;

import android.content.Intent;
import android.view.View;

import com.rambler.base.BaseFragment;
import com.zjj.framedemo.R;
import com.zjj.framedemo.modules.third.MaterialEditActivity;

import butterknife.OnClick;

/**
 * Created by zhoujunjie on 2018/4/11.
 */

public class ThirdFragment extends BaseFragment {

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_third;
    }

    @OnClick({R.id.btn_material_edit_text})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.btn_material_edit_text:
                intent.setClass(getActivity(), MaterialEditActivity.class);
                break;
        }
        startActivity(intent);
    }
}
