package com.zjj.framedemo.modules.share;

import android.Manifest;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.rambler.mvp.lce.MvpLceActivity;
import com.rambler.swipetoload.SwipeToLoadLayout;
import com.rambler.util.ToastUtil;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.zjj.framedemo.R;
import com.zjj.framedemo.dagger.AppModule;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.functions.Action1;

/**
 * Created by zhoujunjie on 2018/4/18.
 */

public class SystemShareActivity extends MvpLceActivity<SwipeToLoadLayout,List<SystemShareModel>,SystemShareView,SystemSharePresenter> implements SystemShareView{

    @Inject
    SystemSharePresenter presenter;

    @BindView(R.id.swipe_target)
    RecyclerView recyclerView;

    private List<SystemShareModel> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_share);
        ButterKnife.bind(this);
        requestPermission();
        presenter.getImage(true);
    }

    @Override
    protected void injectDependencies() {
        DaggerSystemShareComponent.builder().appModule(new AppModule(this)).build().inject(this);
    }

    @Override
    public SystemSharePresenter createPresenter() {
        return presenter;
    }

    private void requestPermission() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        if (aBoolean) {
                            Log.e("rx permission:","获取成功");
                        } else {
                            Log.e("rx permission:","获取失败");
                        }
                    }
                });

    }

    @OnClick({R.id.btn_system_share,R.id.btn_system_share_weixin})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_system_share:

                break;
            case R.id.btn_system_share_weixin:
                shareToWeChat(this);
                break;
        }
    }

    /**
     * 检查是否安装微信
     * @param context
     * @param packageName
     * @return
     */
    private static boolean checkInstallation(Context context, String packageName) {
        try {
            context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    File pic ;

    public void shareToWeChat(Context context) {
        // TODO: 2015/12/13 将需要分享到微信的图片准备好
        try {
            if (!checkInstallation(context, "com.tencent.mm")) {
                ToastUtil.show(this, R.string.share_no_wechat);
                return;
            }
            Intent intent = new Intent();
            //分享精确到微信的页面，朋友圈页面，或者选择好友分享页面
            ComponentName comp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareToTimeLineUI");
            intent.setComponent(comp);
            intent.setAction(Intent.ACTION_SEND_MULTIPLE);
            intent.setType("image/*");
//        intent.setType("text/plain");
            //添加Uri图片地址
//        String msg=String.format(getString(R.string.share_content), getString(R.string.app_name), getLatestWeekStatistics() + "");
            String msg = context.getString(R.string.share_content);
            intent.putExtra("Kdescription", msg);
            ArrayList<Uri> imageUris = new ArrayList<Uri>();
            // TODO: 2016/3/8 根据不同图片来设置分享
            File dir = context.getExternalFilesDir(null);
            if (dir == null || dir.getAbsolutePath().equals("")) {
                dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
            }
            /**添加多张网络图片 */
            File pic = new File(dir, "bigbang.jpg");
            pic.deleteOnExit();
            BitmapDrawable bitmapDrawable;
            if (Build.VERSION.SDK_INT < 22) {
                bitmapDrawable = (BitmapDrawable) context.getResources().getDrawable(R.mipmap.ic_launcher);
            } else {
                bitmapDrawable = (BitmapDrawable) context.getDrawable(R.mipmap.ic_launcher);
            }
            try {
                bitmapDrawable.getBitmap().compress(Bitmap.CompressFormat.JPEG, 75, new FileOutputStream(pic));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                imageUris.add(Uri.fromFile(pic));
            } else {
                /** 此处可添加多张图片进行分享
                 *
                 * 此处需要注意: 读取文件的权限需要申请
                 */
                //修复微信在7.0崩溃的问题
                Uri uri = Uri.parse(android.provider.MediaStore.Images.Media.insertImage(context.getContentResolver(), pic.getAbsolutePath(), "bigbang.jpg", null));
                imageUris.add(uri);
                imageUris.add(uri);
                imageUris.add(uri);
            }

            intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris);
            ((Activity) context).startActivityForResult(intent, 1000);
        } catch (Throwable e) {
            ToastUtil.show(this, e.toString());
        }
    }


    @Override
    public void setData(List<SystemShareModel> data) {
        mData = data;
    }

    @Override
    public void loadData(boolean pullToRefresh) {

    }
}
