package me.ziuo.dialer.data.exception;

import me.ziuo.dialer.domain.exception.ErrorBundle;

/**
 * Created by ziyuo on 2016/1/24.
 */
public class RepositoryErrorBundle implements ErrorBundle{
    private final Exception exception;

    public RepositoryErrorBundle(Exception exception) {
        this.exception = exception;
    }

    @Override
    public Exception getException() {
        return exception;
    }

    @Override
    public String getErrorMessage() {
        String message = "";
        if (this.exception != null) {
            this.exception.getMessage();
        }
        return message;
    }
}
