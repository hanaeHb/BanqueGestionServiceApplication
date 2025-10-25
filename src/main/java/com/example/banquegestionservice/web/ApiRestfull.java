package com.example.banquegestionservice.web;

import com.example.banquegestionservice.dto.RequestCompteDto;
import com.example.banquegestionservice.dto.ResponseCompteDto;
import com.example.banquegestionservice.service.CompteServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/comptes")
public class ApiRestfull {
    private CompteServiceImpl compteService;
    public ApiRestfull(CompteServiceImpl compteService) {
        this.compteService = compteService;
    }

    @PostMapping
    public ResponseEntity<ResponseCompteDto> add(@RequestBody RequestCompteDto requestCompteDto) {
       ResponseCompteDto responseCompteDto = compteService.Add_Compte(requestCompteDto);
       return ResponseEntity.ok(responseCompteDto);

    }

    @GetMapping
    public ResponseEntity<List<ResponseCompteDto>> getAll() {

        List<ResponseCompteDto> compteDtos = compteService.GETALLCompte();
        return ResponseEntity.ok(compteDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseCompteDto> getCompteById(@PathVariable Integer id) {
      ResponseCompteDto responseCompteDto = compteService.GETCompteById(id);
      return ResponseEntity.ok(responseCompteDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseCompteDto> update(@PathVariable Integer id,
                                                    @RequestBody RequestCompteDto requestCompteDto) {
        ResponseCompteDto responseCompteDto = compteService.UPDATECompte(id, requestCompteDto);
        return ResponseEntity.ok(responseCompteDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        compteService.DELETECompteBYID(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/crediter/{id}/{m}")
    ResponseEntity<ResponseCompteDto> crediter(@PathVariable Integer id, @PathVariable Double m) {
        ResponseCompteDto responseCompteDto = compteService.Crediter(id, m);
        return ResponseEntity.ok(responseCompteDto);
    }

    @PatchMapping("/debiter/{id}/{m}")
    ResponseEntity<ResponseCompteDto> debiter(@PathVariable Integer id, @PathVariable Double m) {
        ResponseCompteDto responseCompteDto = compteService.Debiter(id, m);
        return ResponseEntity.ok(responseCompteDto);
    }
}
