package me.ziuo.dialer.domain.executor;

import java.util.concurrent.Executor;

/**
 * Created by ziyuo on 2016/1/23.
 * 线程执行器，用来管理程序中执行的线程池
 * 父接口只包含一个待实现方法：void execute(Runnable command)
 */
public interface ThreadExecutor extends Executor {
}
