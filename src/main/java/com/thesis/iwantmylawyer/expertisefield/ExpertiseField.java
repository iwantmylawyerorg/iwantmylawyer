package com.thesis.iwantmylawyer.expertisefield;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ExpertiseField {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;


    public ExpertiseField(String name) {
        this.name = name;
    }
}
