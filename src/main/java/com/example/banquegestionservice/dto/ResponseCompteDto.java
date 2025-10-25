package com.example.banquegestionservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ResponseCompteDto {

    private Integer id;
    private String nom;
    private String tel;
    private Double solde;
}
