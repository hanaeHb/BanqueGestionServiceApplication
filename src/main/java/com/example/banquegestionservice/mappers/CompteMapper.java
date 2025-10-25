package com.example.banquegestionservice.mappers;

import com.example.banquegestionservice.dto.RequestCompteDto;
import com.example.banquegestionservice.dto.ResponseCompteDto;
import com.example.banquegestionservice.entities.Compte;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CompteMapper {

    public Compte DTO_TO_ENTITY(RequestCompteDto requestCompteDto) {
        Compte compte = new Compte();
        BeanUtils.copyProperties(requestCompteDto, compte);
        return  compte;
    }

    public ResponseCompteDto ENTITY_TO_DTO(Compte compte) {
        ResponseCompteDto responseCompteDto = new ResponseCompteDto();
        BeanUtils.copyProperties(compte, responseCompteDto);
        return responseCompteDto;

    }
}
