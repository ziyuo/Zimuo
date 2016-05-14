package me.ziuo.dialer.zimuo.internal.di.test.trees;

import android.content.Context;
import android.util.Log;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by wangbokai on 2016/3/5.
 *
 * case 1:全为构造器注入时
 * case 2:构造器和属性注入注入时
 *
 * 结论：结果是一样的。
 *
 *ziuo_Pump: Pump:me.ziuo.dialer.zimuo.internal.di.test.trees.Pump@685610c is Pumping !
  D/ziuo_Pump: Pump:me.ziuo.dialer.zimuo.internal.di.test.trees.Pump@685610c is Pumping !
 D/ziuo_Heater: Heater:me.ziuo.dialer.zimuo.internal.di.test.trees.Heater@5a42a55 is Heating !
 D/ziuo_CafffeMaker: CafffeMaker:me.ziuo.dialer.zimuo.internal.di.test.trees.CaffeMaker@e8a1c6a is Making !
 */
/*
@Singleton
*/
public class CaffeMaker implements ICaffeMaker {

    /**
     * case2 由 case1中构造器注入字段 改写成了 字段Member_Inject
     * 结论 正常 和Case1 一致，成功注入
     * 注意点：Member_Inject 时属性的声明不能为私有
     */
     IPump pump;
     IHeater heater;
   /* @Inject
    public CaffeMaker(Heater heater,Context context){this.heater=heater;
    if(context!=null){
        Log.d("ziuo_CaffeMaker","Context is "+context.toString());
    }
    }*/
    @Inject
    public CaffeMaker(IHeater heater,IPump pump){
        this.heater=heater;
        this.pump=pump;
    }
/*
case 1
    public CaffeMaker(Pump pump,Heater heater){this.heater=heater;this.pump=pump}
*/

    public void work(){
        if(pump!=null)
        pump.work();
        else {
            Log.d("ziuo_CafffeMaker","属性注入 pump 失败！");
        }
        heater.work();
        Log.d("ziuo_CafffeMaker", "CafffeMaker:" + this.toString() + " is Making !");

    }
}
