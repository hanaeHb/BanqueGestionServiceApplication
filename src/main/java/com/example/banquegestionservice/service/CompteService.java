package com.example.banquegestionservice.service;

import com.example.banquegestionservice.dto.RequestCompteDto;
import com.example.banquegestionservice.dto.ResponseCompteDto;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

public interface CompteService {
    public ResponseCompteDto Add_Compte(RequestCompteDto requestCompteDto);
    public List<ResponseCompteDto> GETALLCompte();
    public ResponseCompteDto GETCompteById(Integer id);
    public ResponseCompteDto UPDATECompte(Integer id , RequestCompteDto requestCompteDto);
    public void DELETECompteBYID(Integer id);
    public ResponseCompteDto Crediter(Integer id, Double m);
    public ResponseCompteDto Debiter(Integer id, Double m);
}
