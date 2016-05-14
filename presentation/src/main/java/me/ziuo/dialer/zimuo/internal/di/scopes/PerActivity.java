package me.ziuo.dialer.zimuo.internal.di.scopes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by wangbokai on 2016/3/6.
 * 这个注解与Activity中的生命周期
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {}
