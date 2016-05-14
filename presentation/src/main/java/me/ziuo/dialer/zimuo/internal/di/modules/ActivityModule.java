package me.ziuo.dialer.zimuo.internal.di.modules;

import android.app.Activity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import me.ziuo.dialer.zimuo.internal.di.scopes.PerActivity;

/**
 * Created by wangbokai on 2016/3/6.
 */
@Module
public class ActivityModule {
    private final Activity activity;

    /**
     * Build 这个Module时需要
     * @param activity
     */
    public ActivityModule(Activity activity){this.activity=activity;}

    @Provides @PerActivity
    Activity activity(){return this.activity;}
    @Provides @PerActivity @Named("activity1")
    Activity activity1(){return this.activity;}

}
