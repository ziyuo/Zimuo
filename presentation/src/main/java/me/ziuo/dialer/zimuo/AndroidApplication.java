package me.ziuo.dialer.zimuo;

import android.app.Application;

import me.ziuo.dialer.zimuo.internal.di.components.ApplicationComponent;
import me.ziuo.dialer.zimuo.internal.di.components.DaggerApplicationComponent;
import me.ziuo.dialer.zimuo.internal.di.modules.ApplicationModule;

/**
 * Created by wangbokai on 2016/3/5.
 */
public class AndroidApplication extends Application{
    private ApplicationComponent applicationComponent;

    @Override public void onCreate() {
        super.onCreate();
        this.initializeInjector();
    }

    private void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }
}
