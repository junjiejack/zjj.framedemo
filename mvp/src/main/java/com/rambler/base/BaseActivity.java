package com.rambler.base;

import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.rambler.R;
import com.rambler.common.MvpView;
import com.rambler.util.AnalyticsUtil;
import com.rambler.util.PermissionsResultListener;

import org.greenrobot.eventbus.EventBus;

public class BaseActivity extends AppCompatActivity implements MvpView {
  public MaterialDialog mDialog;
  protected View mBackBtn;
  private PermissionsResultListener mListener;
  private int mRequestCode;

  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.injectDependencies();
    mDialog = new MaterialDialog.Builder(this)
        .content("加载数据中...")
        .canceledOnTouchOutside(false)
        .widgetColor(Color.parseColor("#00aaee"))
        .progress(true, 0).build();
    if(useEventBus()){
      EventBus.getDefault().register(this);
    }
  }

  public void onContentChanged() {
    super.onContentChanged();
    mBackBtn = findViewById(R.id.toolbar_left_layout);
    if (mBackBtn != null) {
      mBackBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          BaseActivity.this.finish();
        }
      });
    }
  }

  protected void injectDependencies() {
  }

  /**
   * 是否使用eventBus,默认为使用(false)，
   *
   * @return
   */
  public boolean useEventBus() {
    return false;
  }

  @Override
  protected void onResume() {
    super.onResume();
    //数据统计
    AnalyticsUtil.onResume(getApplicationContext(), getClass().getSimpleName());
  }

  @Override
  protected void onPause() {
    super.onPause();
    //数据统计
    AnalyticsUtil.onPause(getApplicationContext(), getClass().getSimpleName());
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    if(useEventBus()){
      EventBus.getDefault().unregister(this);
    }
    if (mDialog != null) {
      if (mDialog.isShowing()) {
        mDialog.dismiss();
      }
      mDialog = null;
    }
  }

  public void showMsg(String msg) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
  }

  @Override
  public boolean dispatchTouchEvent(MotionEvent event) {
    //注：回调 3
    return super.dispatchTouchEvent(event);
  }

  /**
   * 其他 Activity 继承 BaseActivity 调用 performRequestPermissions 方法
   *
   * @param permissions 要申请的权限数组
   * @param requestCode 申请标记值
   * @param listener 实现的接口
   */
  protected void performRequestPermissions(String[] permissions, int requestCode,
      PermissionsResultListener listener) {
    if (permissions == null || permissions.length == 0) {
      return;
    }
    mRequestCode = requestCode;
    mListener = listener;
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      if (checkEachSelfPermission(permissions)) {// 检查是否声明了权限
        requestEachPermissions(permissions, requestCode);
      } else {// 已经申请权限
        if (mListener != null) {
          mListener.onPermissionGranted();
        }
      }
    } else {
      if (mListener != null) {
        mListener.onPermissionGranted();
      }
    }
  }

  /**
   * 申请权限前判断是否需要声明
   */
  private void requestEachPermissions(String[] permissions, int requestCode) {
    if (shouldShowRequestPermissionRationale(permissions)) {// 需要再次声明
      showRationaleDialog(permissions, requestCode);
    } else {
      ActivityCompat.requestPermissions(BaseActivity.this, permissions, requestCode);
    }
  }

  /**
   * 弹出声明的 Dialog
   */
  private void showRationaleDialog(final String[] permissions, final int requestCode) {
    new MaterialDialog.Builder(this).title("提示")
        .content("为了应用可以正常使用，请您点击确认申请权限。")
        .positiveText("确认")
        .negativeText("取消")
        .positiveColorRes(R.color.black)
        .negativeColorRes(R.color.black)
        .widgetColorRes(R.color.black)
        .callback(new MaterialDialog.ButtonCallback() {
          @Override
          public void onPositive(MaterialDialog dialog) {
            dialog.dismiss();
            ActivityCompat.requestPermissions(BaseActivity.this, permissions, requestCode);
          }

          @Override
          public void onNegative(MaterialDialog dialog) {
            dialog.dismiss();
          }
        })
        .show();
  }

  /**
   * 再次申请权限时，是否需要声明
   */
  private boolean shouldShowRequestPermissionRationale(String[] permissions) {
    for (String permission : permissions) {
      if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
        return true;
      }
    }
    return false;
  }

  /**
   * 检察每个权限是否申请
   *
   * @return true 需要申请权限,false 已申请权限
   */
  private boolean checkEachSelfPermission(String[] permissions) {
    for (String permission : permissions) {
      if (ContextCompat.checkSelfPermission(this, permission)
          != PackageManager.PERMISSION_GRANTED) {
        return true;
      }
    }
    return false;
  }

  /**
   * 申请权限结果的回调
   */
  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
      @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    if (requestCode == mRequestCode) {
      if (checkEachPermissionsGranted(grantResults)) {
        if (mListener != null) {
          mListener.onPermissionGranted();
        }
      } else {// 用户拒绝申请权限
        if (mListener != null) {
          mListener.onPermissionDenied();
        }
      }
    }
  }

  /**
   * 检查回调结果
   */
  private boolean checkEachPermissionsGranted(int[] grantResults) {
    for (int result : grantResults) {
      if (result != PackageManager.PERMISSION_GRANTED) {
        return false;
      }
    }
    return true;
  }
}
