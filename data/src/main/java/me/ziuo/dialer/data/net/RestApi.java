package me.ziuo.dialer.data.net;

import me.ziuo.dialer.data.entry.UserEntry;
import rx.Observable;

/**
 * Created by ziyuo on 2016/1/24.
 */
public interface RestApi {

    String API_BASE_URL = "http://www.android10.org/myapi/";

    /**
     * 用来得到一个用户资料的API，形式如下 that + id+'json'
     */
    String API_URL_GET_USER_DETAILS = API_BASE_URL + "user_";

    /**
     * 检索发送一个{@link UserEntry}的 {@link rx.Observable}
     *
     * @param userId
     * @return
     */
    Observable<UserEntry> userEntryById(final int userId);

}
