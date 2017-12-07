package com.zjj.framedemo.modules;

import com.zjj.framedemo.dagger.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author zhoujunjie on 2017/12/7.
 */

@Singleton
@Component(modules = AppModule.class)
public interface LoginComponent {

    void inject(LoginActivity loginActivity);

    LoginPresenter presenter();
}
