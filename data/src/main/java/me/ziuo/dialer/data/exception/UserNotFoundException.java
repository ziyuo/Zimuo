package me.ziuo.dialer.data.exception;

/**
 * Created by ziyuo on 2016/1/24.
 */
public class UserNotFoundException extends Exception{
    public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(final String message) {
        super(message);
    }

    public UserNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public UserNotFoundException(final Throwable cause) {
        super(cause);
    }
}
