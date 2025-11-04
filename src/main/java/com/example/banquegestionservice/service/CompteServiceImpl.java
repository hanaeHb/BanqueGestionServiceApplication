package com.example.banquegestionservice.service;

import com.example.banquegestionservice.dto.RequestCompteDto;
import com.example.banquegestionservice.dto.ResponseCompteDto;
import com.example.banquegestionservice.entities.Compte;
import com.example.banquegestionservice.mappers.CompteMapper;
import com.example.banquegestionservice.repository.CompteRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompteServiceImpl implements CompteService {


    private CompteRepository compteRepository;
    private CompteMapper compteMapper;

    public CompteServiceImpl(CompteRepository compteRepository, CompteMapper compteMapper) {
        this.compteRepository = compteRepository;
        this.compteMapper = compteMapper;
    }

    @Override
    public ResponseCompteDto Add_Compte(RequestCompteDto requestCompteDto) {
        Compte compte = compteMapper.DTO_TO_ENTITY(requestCompteDto);
        Compte savedCompte = compteRepository.save(compte);
        return compteMapper.ENTITY_TO_DTO(savedCompte);
    }
    @Override
    public List<ResponseCompteDto> GETALLCompte() {
        List<Compte> comptes = compteRepository.findAll();
        List<ResponseCompteDto> CompteDtos = new ArrayList<>(); //vide
        for (Compte compte : comptes) {
                CompteDtos.add(compteMapper.ENTITY_TO_DTO(compte));
        }
        return null;
    }

    @Override
    public ResponseCompteDto GETCompteById(Integer id) {
       Compte compte = compteRepository.findById(id).orElseThrow();
        return compteMapper.ENTITY_TO_DTO(compte);
    }

    @Override
    public ResponseCompteDto UPDATECompte(Integer id, RequestCompteDto requestCompteDto) {
        Compte new_compte = compteMapper.DTO_TO_ENTITY(requestCompteDto);
        Compte compte = compteRepository.findById(id).orElseThrow();

        if(new_compte.getNom()!=null) compte.setNom(new_compte.getNom());
        if(new_compte.getTel()!=null) compte.setTel(new_compte.getTel());
        if(new_compte.getSolde()!=null) compte.setSolde(new_compte.getSolde());

        Compte saved = compteRepository.save(compte);
        return compteMapper.ENTITY_TO_DTO(saved);
    }

    @Override
    public void DELETECompteBYID(Integer id) {
        compteRepository.deleteById(id);
    }

    @Override
    public ResponseCompteDto Crediter(Integer id, Double m) {
        Compte compte = compteRepository.findById(id).orElseThrow();
        compte.setSolde(compte.getSolde() + m);
        Compte saved = compteRepository.save(compte);
        return compteMapper.ENTITY_TO_DTO(saved);
    }

    @Override
    public ResponseCompteDto Debiter(Integer id, Double m) {
        Compte compte = compteRepository.findById(id).orElseThrow();
        if(compte.getSolde() >= m) {
            compte.setSolde(compte.getSolde() - m);
        }
        Compte saved = compteRepository.save(compte);
        return compteMapper.ENTITY_TO_DTO(saved);
    }
}
