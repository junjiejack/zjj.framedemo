package com.zjj.framedemo;


import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.RadioGroup;

import com.rambler.base.BaseActivity;
import com.zjj.framedemo.modules.main.custom.CustomFragment;
import com.zjj.framedemo.modules.main.home.HomeFragment;
import com.zjj.framedemo.modules.main.other.OtherFragment;
import com.zjj.framedemo.modules.main.third.ThirdFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.rb_group)
    RadioGroup radioGroup;

    private List<Fragment> fragments;
    private FragmentPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initFragments();
        initViewPager();
        initListener();
    }

    private void initFragments() {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new CustomFragment());
        fragments.add(new ThirdFragment());
        fragments.add(new OtherFragment());
    }

    private void initViewPager() {
        pagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        };
        viewPager.setAdapter(pagerAdapter);
    }

    private void initListener() {
        radioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {
            case R.id.rb_home:
                viewPager.setCurrentItem(0,false);
                break;
            case R.id.rb_custom:
                viewPager.setCurrentItem(1,false);
                break;
            case R.id.rb_third:
                viewPager.setCurrentItem(2,false);
                break;
            case R.id.rb_other:
                viewPager.setCurrentItem(3,false);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
