package com.hindu.newslettersubscription.common;

import org.springframework.http.HttpStatus;

public enum ErrorCode {


    NOT_FOUND("HINDU_0001", HttpStatus.NOT_FOUND);

    private final String status;
    private final HttpStatus httpStatus;

    ErrorCode(final String status, final HttpStatus httpStatus) {
        this.status = status;
        this.httpStatus = httpStatus;
    }

    public String getStatus() {
        return status;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
