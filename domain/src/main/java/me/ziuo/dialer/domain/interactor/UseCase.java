package me.ziuo.dialer.domain.interactor;

import me.ziuo.dialer.domain.executor.PostExecutionThread;
import me.ziuo.dialer.domain.executor.ThreadExecutor;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * Created by ziyuo on 2016/1/24.
 * 抽象类用例（整洁性架构里面的交互器）。
 * 此接口表示用于不同的使用场景一个执行单元（这意味着任何使用情况下，在应用程序应实现该抽象类）。
 * 按照惯例每个用例的实现将返回{@link rx.Subscriber}作为返回值；
 * 返回的Subscriber将在后台线程执行它的任务，将发布结果在UI线程。
 */
public abstract class UseCase {

    private final ThreadExecutor threadExecutor;
    private final PostExecutionThread postExecutionThread;
    private Subscription subscription = Subscriptions.empty();

    protected UseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
    }

    /**
     * 构建一个{@link rx.Observable} 用来在当前{@link UseCase}下执行
     */
    protected abstract Observable buildUseCaseObservable();

    /**
     * 执行当前用例
     *
     * @param UsecaseSubscriber 用来监听监听这个方法{@link #buildUseCaseObservable()}返回的{@link Observable}对象
     */
    public void execute(Subscriber UsecaseSubscriber) {
        this.subscription = this.buildUseCaseObservable()
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler())
                .subscribe(UsecaseSubscriber);
    }

    /**
     * 取消订阅当前用例中的{@link Subscription}
     */
    public void unsubscribe() {
        if (this.subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
