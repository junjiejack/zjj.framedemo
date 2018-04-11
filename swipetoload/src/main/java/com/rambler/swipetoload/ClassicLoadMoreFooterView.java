package com.rambler.swipetoload;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by Aspsine on 2015/9/2.
 */
public class ClassicLoadMoreFooterView extends SwipeLoadMoreFooterLayout {
    private TextView tvLoadMore;
    private ProgressBar progressBar;

    private int mFooterHeight;

    public ClassicLoadMoreFooterView(Context context) {
        this(context, null);
    }

    public ClassicLoadMoreFooterView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ClassicLoadMoreFooterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mFooterHeight = getResources().getDimensionPixelOffset(R.dimen.load_more_footer_height_classic);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        tvLoadMore = (TextView) findViewById(R.id.tvLoadMore);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
    }

    @Override
    public void onPrepare() {
    }

    @Override
    public void onMove(int y, boolean isComplete, boolean automatic) {
        if (!isComplete) {
            progressBar.setVisibility(GONE);
            if (-y >= mFooterHeight) {
                tvLoadMore.setText("松开加载更多");
            } else {
                tvLoadMore.setText("上拉加载更多");
            }
        }
    }

    @Override
    public void onLoadMore() {
        tvLoadMore.setText("加载中...");
        progressBar.setVisibility(VISIBLE);
    }

    @Override
    public void onRelease() {

    }

    @Override
    public void onComplete() {
        progressBar.setVisibility(GONE);
    }

    @Override
    public void onReset() {
    }
}
