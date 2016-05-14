package me.ziuo.dialer.domain.interactor;

import rx.Subscriber;

/**
 * Created by ziyuo on 2016/1/23.
 * 对 ObservableSubject 只做默认处理的Subscriber
 */
public class DefaultSubscriber<T> extends Subscriber<T> {
    @Override
    public void onCompleted() {
        //默认无操作
    }

    @Override
    public void onError(Throwable e) {
        /**
         * Todo:对异常对象进行处理
         * 逻辑待补全
         */

    }

    @Override
    public void onNext(T t) {
        //默认无操作

    }
}
