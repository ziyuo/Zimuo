package me.ziuo.dialer.domain.interactor;

import javax.inject.Inject;

import me.ziuo.dialer.domain.executor.PostExecutionThread;
import me.ziuo.dialer.domain.executor.ThreadExecutor;
import me.ziuo.dialer.domain.repository.UserRepository;
import rx.Observable;

/**
 * Created by ziyuo on 2016/1/24.
 */
public class GetUserDetails extends UseCase {

    private final int userId;
    private final UserRepository userRepository;
    @Inject
    protected GetUserDetails(int userId,UserRepository userRepository,ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.userId=userId;
        this.userRepository=userRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.userRepository.user(userId);
    }
}
