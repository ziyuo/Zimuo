package me.ziuo.dialer.zimuo.internal.di.test.trees;

import android.util.Log;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by wangbokai on 2016/3/5.
 */
@Singleton
public class Pump implements IPump{

    @Inject
    public Pump(){}

    public void work(){
        Log.d("ziuo_Pump","Pump:"+this.toString()+" is Pumping !");
    }
}
