package com.zjj.framedemo.modules.insure;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.rambler.base.BaseActivity;
import com.zjj.framedemo.R;
import com.zjj.framedemo.adapter.SelectAddressAdapter;
import com.zjj.framedemo.utils.DisplayUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InsureActivity extends BaseActivity {

    @BindView(R.id.sp_start_city)
    Spinner spStartCity;
    @BindView(R.id.sp_start_district)
    Spinner spStartDistrict;
    @BindView(R.id.tv_end_city)
    TextView tvEndCity;
    @BindView(R.id.sp_end_district)
    Spinner spEndDistrict;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insure);
        ButterKnife.bind(this);
        initData();
    }

    @OnClick(R.id.tv_end_city)
    public void onClick(View view){
        final Dialog dialog = new Dialog(this);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_check_address, null);
        dialog.setContentView(dialogView);
        ViewGroup.LayoutParams layoutParams = dialogView.getLayoutParams();
        layoutParams.width = getResources().getDisplayMetrics().widthPixels - DisplayUtil.dip2px(this,100);
        dialogView.setLayoutParams(layoutParams);
        RecyclerView recyclerView = dialogView.findViewById(R.id.recycler_view);
        dialog.show();
        /** 加载适配器 */
        //数据
        final List<String> data_list = new ArrayList<String>();
        data_list.add("北京");
        data_list.add("上海");
        data_list.add("广州");
        data_list.add("深圳");
        data_list.add("请选择城市");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        SelectAddressAdapter adapter = new SelectAddressAdapter(data_list,this);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                tvEndCity.setText(data_list.get(position));
                dialog.dismiss();
            }
        });
    }



    private void initData() {

            //数据
            List<String> data_list = new ArrayList<String>();
            data_list.add("北京");
            data_list.add("上海");
            data_list.add("广州");
            data_list.add("深圳");
            data_list.add("请选择城市");

            //适配器
            SimpleArrayAdapter arrAdapter = new SimpleArrayAdapter(this, R.layout.spinner_normal_item, data_list);
            //设置样式
            arrAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
            //加载适配器
            spStartCity.setAdapter(arrAdapter);
            spStartCity.setSelection(data_list.size() - 1, true);


    }

    public class SimpleArrayAdapter<T> extends ArrayAdapter {
        //构造方法
        public SimpleArrayAdapter(Context context, int resource, List<T>  objects) {
            super(context, resource, objects);
        }
        //复写这个方法，使返回的数据没有最后一项
        @Override
        public int getCount() {
            // don't display last item. It is used as hint.
            int count = super.getCount();
            return count > 0 ? count - 1 : count;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
