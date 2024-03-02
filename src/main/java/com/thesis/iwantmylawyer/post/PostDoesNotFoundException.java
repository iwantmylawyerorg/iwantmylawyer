package com.thesis.iwantmylawyer.post;

public class PostDoesNotFoundException extends RuntimeException {
    public PostDoesNotFoundException(String message) {
        super(message);
    }
}
