package com.rambler.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class BaseNavigationFragment extends BaseToolbarFragment {

    public static final String NAVIGATION_FRAGMENT_TITLE = "navigation_fragment_title";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView tv = new TextView(getActivity());
        tv.setText("Not developed yet. Support latter...");
        tv.setGravity(Gravity.CENTER);
        return tv;
    }
}
