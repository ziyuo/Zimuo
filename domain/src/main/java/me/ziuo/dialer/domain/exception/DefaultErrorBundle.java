package me.ziuo.dialer.domain.exception;

/**
 * Created by ziyuo on 2016/1/24.
 * 包裹异常用来去处理默认错误
 */
public class DefaultErrorBundle implements ErrorBundle {

    private static final String DEFAULT_ERROR_MSG="Unknown error";

    private final Exception exception;

    public DefaultErrorBundle(Exception exception) {
        this.exception = exception;
    }

    @Override
    public Exception getException() {
        return this.exception;
    }

    @Override
    public String getErrorMessage() {
        return (this.exception!=null)?this.exception.getMessage():DEFAULT_ERROR_MSG;
    }
}
