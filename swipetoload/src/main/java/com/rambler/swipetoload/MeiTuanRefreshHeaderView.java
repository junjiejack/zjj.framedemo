package com.rambler.swipetoload;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by lixiaofan on 16/10/9.
 */
public class MeiTuanRefreshHeaderView extends SwipeRefreshHeaderLayout {

    private ImageView ivRelease;

    private ImageView ivSuccess;

    private int mHeaderHeight;

    private boolean rotated = false;

    private AnimationDrawable mAnimRelease;

    private AnimationDrawable mAnimSuccess;

    public MeiTuanRefreshHeaderView(Context context) {
        this(context, null);
    }

    public MeiTuanRefreshHeaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MeiTuanRefreshHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mHeaderHeight = getResources().getDimensionPixelOffset(R.dimen.refresh_header_height_meituan);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        ivRelease = (ImageView) findViewById(R.id.ivRelease);
        ivSuccess = (ImageView) findViewById(R.id.ivSuccess);
        mAnimRelease = (AnimationDrawable) ivRelease.getBackground();
        mAnimSuccess = (AnimationDrawable) ivSuccess.getBackground();
    }

    @Override
    public void onRefresh() {
        ivRelease.setVisibility(GONE);
        ivSuccess.setVisibility(VISIBLE);
        if (!mAnimSuccess.isRunning()){
            mAnimSuccess.start();
        }
    }

    @Override
    public void onPrepare() {
        Log.d("TwitterRefreshHeader", "onPrepare()");
    }

    @Override
    public void onMove(int y, boolean isComplete, boolean automatic) {
        if (!isComplete) {
            ivRelease.setVisibility(VISIBLE);
            ivSuccess.setVisibility(GONE);
            if (y > mHeaderHeight) {
                if (!rotated) {
                    //下拉高度超过要求高度,播放release动画
                    handleScale (1.0f);
                    if (!mAnimRelease.isRunning()){
                        mAnimRelease.start();
                    }
                    rotated = true;
                }
            } else if (y < mHeaderHeight) {
                if (rotated) {
                    //取消下拉,release动画停止,复原
                    if (mAnimRelease.isRunning()){
                        mAnimRelease.stop();
                    }
                    mAnimRelease.selectDrawable(0);
                    ivRelease.clearAnimation();
                    rotated = false;
                }else{
                    //开始下拉,设置刷新图标缩放
                    float scale = y * 1.0f / mHeaderHeight;
                    handleScale (scale);
                }
            }
        }
    }

    private void handleScale(float scale) {
        if (scale <= 1.0f) {
            ViewCompat.setScaleX(ivRelease, scale);
            ViewCompat.setPivotY(ivRelease, ivRelease.getHeight());
            ViewCompat.setScaleY(ivRelease, scale);
        }
    }

    @Override
    public void onRelease() {
        Log.d("TwitterRefreshHeader", "onRelease()");
    }

    @Override
    public void onComplete() {
        rotated = false;
        ivRelease.clearAnimation();
        ivRelease.setVisibility(GONE);
        ivSuccess.setVisibility(VISIBLE);
    }

    @Override
    public void onReset() {
        rotated = false;
        mAnimSuccess.stop();
        ivSuccess.clearAnimation();
        ivSuccess.setVisibility(GONE);
        mAnimRelease.stop();
        ivRelease.clearAnimation();
        ivRelease.setVisibility(GONE);
    }

}
