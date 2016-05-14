package me.ziuo.dialer.domain.repository;

import me.ziuo.dialer.domain.User;
import rx.Observable;

/**
 * Created by ziyuo on 2016/1/24.
 * 用来作为仓库得到用户相关数据的接口
 */
public interface UserRepository {
    /**
     *
     * 得到一个发送{@link User}类型数据的{@link Observable}
     * @return
     */
    Observable<User> user(int userId);
}
