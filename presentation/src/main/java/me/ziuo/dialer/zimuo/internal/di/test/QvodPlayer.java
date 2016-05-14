package me.ziuo.dialer.zimuo.internal.di.test;

import android.util.Log;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by wangbokai on 2016/3/5.
 */
@Singleton
public class QvodPlayer {
    private final static String TAG="ziuo_qvob";
    @Inject
    public QvodPlayer(){}

    public void play(String s) {
        Log.d(TAG, s + ", this is " + toString());
    }
}
