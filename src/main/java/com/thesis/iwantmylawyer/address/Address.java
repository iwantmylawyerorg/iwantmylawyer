package com.thesis.iwantmylawyer.address;


import com.thesis.iwantmylawyer.user.lawyer.Lawyer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String street;
    private String alley;
    private String city;
    private String state;
    private String postalCode;
    private String country;

    @OneToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    @JoinColumn
    private Lawyer lawyer;

    public Address(String street, String alley, String city, String state, String postalCode, String country, Lawyer lawyer) {
        this.street = street;
        this.alley = alley;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
        this.lawyer = lawyer;
    }
}
