package com.zjj.framedemo.modules.insure;

import com.zjj.framedemo.dagger.AppModule;
import com.zjj.framedemo.modules.baopay.BaoPayActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author zhoujunjie on 2017/12/7.
 */

@Singleton
@Component(modules = AppModule.class)
public interface InsureComponent {

    void inject(BaoPayActivity activity);

    InsurePresenter presenter();
}
