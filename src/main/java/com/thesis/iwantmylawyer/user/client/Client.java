package com.thesis.iwantmylawyer.user.client;

import com.thesis.iwantmylawyer.user.Role;
import com.thesis.iwantmylawyer.user.User;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class Client extends User {

    public Client(String email, String password, String firstName, String lastName, String telephoneNo, Role role) {
        super(email, password, firstName, lastName, telephoneNo, role);
    }
}
