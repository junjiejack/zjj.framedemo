package com.zjj.framedemo.modules.third;

import android.os.Bundle;

import com.rambler.base.BaseActivity;
import com.rengwuxian.materialedittext.MaterialAutoCompleteTextView;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.rengwuxian.materialedittext.MaterialMultiAutoCompleteTextView;
import com.zjj.framedemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhoujunjie on 2018/4/12.
 */

public class MaterialEditActivity extends BaseActivity {

    @BindView(R.id.material_normal)
    MaterialEditText materialNormal;
    @BindView(R.id.material_auto_complete)
    MaterialAutoCompleteTextView materialAutoComplete;
    @BindView(R.id.material_multi_auto_complete)
    MaterialMultiAutoCompleteTextView materialMultiAutoComplete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_edit_text);
        ButterKnife.bind(this);
    }

}
