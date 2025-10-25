package com.example.banquegestionservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor @Getter
@Setter
public class RequestCompteDto {

    private String nom;
    private String tel;
    private Double solde;
}
