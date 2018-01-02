package com.zjj.framedemo.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zjj.framedemo.R;

import java.util.List;

/**
 * @author zhoujunjie on 2017/12/23.
 */

public class SelectAddressAdapter extends BaseQuickAdapter<String,BaseViewHolder > {

    private Context context;

    public SelectAddressAdapter(List<String> data, Context context) {
        super(R.layout.spinner_dropdown_item, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.cb_address,item.toString());
    }
}
