package me.ziuo.dialer.zimuo.internal.di.components;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import me.ziuo.dialer.zimuo.internal.di.modules.ApplicationModule;

/**
 * Created by wangbokai on 2016/3/5.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

/**
 * 保留下面的对象 给子图
 */
    Context context();

}
