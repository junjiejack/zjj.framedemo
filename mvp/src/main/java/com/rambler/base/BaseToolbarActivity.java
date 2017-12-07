package com.rambler.base;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.rambler.R;


/**
 * Created by maocheng on 16/10/11.
 */

public class BaseToolbarActivity extends BaseActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            this.setSupportActionBar(toolbar);
//            final ActionBar actionbar = getSupportActionBar();
//            actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
//            actionbar.setDisplayShowTitleEnabled(false);
//            actionbar.setElevation(0);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
