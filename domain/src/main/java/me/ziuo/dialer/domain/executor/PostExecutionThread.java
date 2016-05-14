package me.ziuo.dialer.domain.executor;

import rx.Scheduler;

/**
 * Created by ziyuo on 2016/1/23
 * 可以将执行器从任何线程切换到另一个线程。
 * 例如，经常用来封装UI线程。当后台任务完成后用来刷新UI线程的视图
 * 用于RxJava
 */
public interface PostExecutionThread {
    Scheduler getScheduler();
}
