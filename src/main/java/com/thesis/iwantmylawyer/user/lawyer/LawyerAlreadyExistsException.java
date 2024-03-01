package com.thesis.iwantmylawyer.user.lawyer;

public class LawyerAlreadyExistsException extends RuntimeException {
    public LawyerAlreadyExistsException (String message) {
        super(message);
    }
}
