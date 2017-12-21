package com.zjj.framedemo.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zjj.framedemo.R;
import com.zjj.framedemo.model.MyPremiumModel;

import java.util.List;



/**
 * @author kevin on 2017/11/1.
 */
public class MyPremiumAdapter extends BaseQuickAdapter<MyPremiumModel.RowsBean, BaseViewHolder> {
    private Context context;
    public MyPremiumAdapter(List<MyPremiumModel.RowsBean> data, Context context) {
        super(R.layout.item_list_my_detail, data);
        this.context = context;
    }
    @Override
    protected void convert(BaseViewHolder helper, final MyPremiumModel.RowsBean item) {
        helper.setText(R.id.tv_premium_date,item.getYear()+"年"+item.getMonth()+"月");
        helper.setText(R.id.tv_premium_total, String.format(context.getResources().getString(R.string.price_value_unit), (double)item.getPremium()/100));
//        helper.setText(R.id.tv_premium_car, String.format(context.getResources().getString(R.string.price_value_unit), (double)item.getPrice_zcb()/100));
//        helper.setText(R.id.tv_premium_personal, String.format(context.getResources().getString(R.string.price_value_unit), (double)item.getPrice_ygb()/100));
//        helper.setText(R.id.tv_premium_shop, String.format(context.getResources().getString(R.string.price_value_unit), (double)item.getPrice_spb()/100));
    }
}