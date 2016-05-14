package me.ziuo.dialer.zimuo.internal.di.test.trees;

import android.util.Log;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by wangbokai on 2016/3/5.
 */
@Singleton
public class Heater implements IHeater {
    private Pump pump;
    @Inject
    public Heater(Pump pump){this.pump=pump;}

    public void work(){
        pump.work();
        Log.d("ziuo_Heater", "Heater:" + this.toString() + " is Heating !");

    }
}
