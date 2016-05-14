package me.ziuo.dialer.domain.exception;

/**
 * Created by ziyuo on 2016/1/24.
 * 该接口通过包裹一个{@link java.lang.Exception } 来处理错误
 */
public interface ErrorBundle {
    Exception getException();

    String getErrorMessage();
}
