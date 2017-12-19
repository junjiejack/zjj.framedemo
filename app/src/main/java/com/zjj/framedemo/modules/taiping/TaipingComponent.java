package com.zjj.framedemo.modules.taiping;

import com.zjj.framedemo.dagger.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author zhoujunjie on 2017/12/7.
 */

@Singleton
@Component(modules = AppModule.class)
public interface TaipingComponent {

    void inject(TaipingActivity loginActivity);

    TaipingPresenter presenter();
}
