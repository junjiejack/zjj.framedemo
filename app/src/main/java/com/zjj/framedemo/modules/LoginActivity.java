package com.zjj.framedemo.modules;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.rambler.core.MvpActivity;
import com.zjj.framedemo.R;
import com.zjj.framedemo.dagger.AppModule;
import com.zjj.framedemo.model.LoginResult;
import com.zjj.framedemo.view.ClearEditText;

import java.util.HashMap;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends MvpActivity<LoginView,LoginPresenter> implements LoginView{

    @Inject
    LoginPresenter mLoginPresenter;
    @Bind(R.id.edit_username)
    ClearEditText editUserName;
    @Bind(R.id.edit_pwd)
    ClearEditText editPwd;
    @Bind(R.id.btn_login)
    Button btnLogin;

    @NonNull
    @Override
    public LoginPresenter createPresenter() {
        return mLoginPresenter;
    }

    @Override
    protected void injectDependencies() {
        DaggerLoginComponent.builder().appModule(new AppModule(this)).build().inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    /** 点击登录 */
    @OnClick(R.id.btn_login)
    public void onClick(View view) {
        HashMap<String,String> map = new HashMap<>();
        map.put("mobile",editUserName.getText().toString().trim());
        map.put("password",editPwd.getText().toString().trim());
        mLoginPresenter.getLoginInfo(map);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }


    @Override
    public void showLoading() {
        mDialog.show();
    }

    @Override
    public void showSuccess(LoginResult result) {
        mDialog.dismiss();
        Toast.makeText(this,result.getData().getAccess_token()+"",Toast.LENGTH_SHORT).show();
    }
}
