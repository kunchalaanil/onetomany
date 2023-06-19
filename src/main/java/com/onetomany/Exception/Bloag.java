package com.onetomany.Exception;

import org.springframework.http.HttpStatus;

public class Bloag extends RuntimeException {


    public Bloag(HttpStatus status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    private HttpStatus status;
    private String message;
}

