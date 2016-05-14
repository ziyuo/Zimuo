package me.ziuo.dialer.data.exception;

/**
 * Created by ziyuo on 2016/1/24.
 */
public class NetworkConnectionException extends Exception {

    public NetworkConnectionException() {
        super();
    }
    public NetworkConnectionException(String detailMessage) {
        super(detailMessage);
    }
    public NetworkConnectionException(Throwable throwable) {
        super(throwable);
    }
    public NetworkConnectionException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }
}
