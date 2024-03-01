package com.thesis.iwantmylawyer.user.lawyer;

public class LawyerNotFoundException extends RuntimeException {
    public LawyerNotFoundException (String message) {
        super(message);
    }
}
