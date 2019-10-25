package kr.co.bnksys.todoapp.data.base;

import androidx.annotation.Nullable;

public class WrapperError extends RuntimeException {

    private String errorCode;
    private Integer statusCode;
    private String message;

    public WrapperError(Integer statusCode, String errorCode, String message) {
        this.statusCode = statusCode;
        this.errorCode = errorCode;
        this.message = message;
    }

    public WrapperError(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    @Nullable
    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "WrapperError{" +
                "errorCode=" + errorCode +
                ", statusCode='" + statusCode + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}