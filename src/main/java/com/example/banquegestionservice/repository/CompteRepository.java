package com.example.banquegestionservice.repository;

import com.example.banquegestionservice.entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CompteRepository extends JpaRepository<Compte, Integer> {

}
