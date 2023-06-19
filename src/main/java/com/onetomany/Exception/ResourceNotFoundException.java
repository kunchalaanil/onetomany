package com.onetomany.Exception;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message, String id, Long postId) {

        super(message);
    }
}

