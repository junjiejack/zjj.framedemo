package com.zjj.framedemo.modules.baopay;

import com.zjj.framedemo.dagger.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author zhoujunjie on 2017/12/7.
 */

@Singleton
@Component(modules = AppModule.class)
public interface BaoPayComponent {

    void inject(BaoPayActivity activity);

    BaoPayPresenter presenter();
}
