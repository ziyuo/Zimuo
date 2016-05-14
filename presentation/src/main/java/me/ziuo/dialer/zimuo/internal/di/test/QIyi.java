package me.ziuo.dialer.zimuo.internal.di.test;

import android.util.Log;

import javax.inject.Inject;

/**
 * Created by wangbokai on 2016/3/5.
 * 该类作为 {@link IQiyi} 接口的实现类暴露给子图
 *
 */
public class QIyi implements IQiyi {


    private final static String TAG="ziuo";
    @Inject
    public QIyi(){}

    @Override
    public void play(String s) {
        Log.d(TAG,s+", this is "+toString());
    }
}
