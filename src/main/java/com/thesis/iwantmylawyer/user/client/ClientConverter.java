package com.thesis.iwantmylawyer.user.client;

import org.springframework.stereotype.Component;

@Component
public class ClientConverter {

    public ClientResponse convert(Client from){
        return new ClientResponse(
                from.getId(),
                from.getEmail(),
                from.getFirstName(),
                from.getLastName(),
                from.getTelephoneNo()
        );
    }
}
