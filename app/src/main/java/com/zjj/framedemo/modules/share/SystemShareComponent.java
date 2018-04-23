package com.zjj.framedemo.modules.share;

import com.zjj.framedemo.dagger.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author zhoujunjie on 2017/12/7.
 */

@Singleton
@Component(modules = AppModule.class)
public interface SystemShareComponent {

    void inject(SystemShareActivity activity);

    SystemSharePresenter presenter();
}
