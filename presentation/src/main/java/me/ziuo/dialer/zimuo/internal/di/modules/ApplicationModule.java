package me.ziuo.dialer.zimuo.internal.di.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.ziuo.dialer.zimuo.AndroidApplication;

/**
 * Created by wangbokai on 2016/3/5.
 * Dagger 管理 提供和Application 生命周期相一致的对象
 */
@Module
public class ApplicationModule {

    private final AndroidApplication application;
    public ApplicationModule(AndroidApplication application){this.application=application;}

    @Provides @Singleton
    Context provideApplicationContext(){return this.application;}

}
