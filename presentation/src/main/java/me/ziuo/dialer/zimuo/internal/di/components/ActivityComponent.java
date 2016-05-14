package me.ziuo.dialer.zimuo.internal.di.components;

import android.app.Activity;

import dagger.Component;
import me.ziuo.dialer.zimuo.internal.di.modules.ActivityModule;
import me.ziuo.dialer.zimuo.internal.di.scopes.PerActivity;
import me.ziuo.dialer.zimuo.view.MainActivity;

/**
 * Created by wangbokai on 2016/3/6.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class,modules = ActivityModule.class)
public interface ActivityComponent {

    /**
     * 经过测试发现，同一个对象不能同时在{@link ApplicationComponent}与本类中声明inject
     * 否则会报错{如截图}
     * 当然MainActivity 无法直接使用另一Scope中对象
     * @param mainActivity
     */
    void inject(MainActivity mainActivity);

    Activity activity();

}
