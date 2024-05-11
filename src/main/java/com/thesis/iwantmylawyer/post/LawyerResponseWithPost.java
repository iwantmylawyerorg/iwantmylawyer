package com.thesis.iwantmylawyer.post;

import java.io.Serial;
import java.io.Serializable;

public record LawyerResponseWithPost(String id, String name, String lastName, String lawyerPhoto) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
}