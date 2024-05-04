package com.thesis.iwantmylawyer.user;

import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public UserResponse convert(User from){
        return new UserResponse(from.getId(),from.getEmail(), from.getFirstName(), from.getLastName(),from.getRole());
    }
}
