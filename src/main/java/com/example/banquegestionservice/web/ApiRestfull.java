package com.example.banquegestionservice.web;

import com.example.banquegestionservice.dto.RequestCompteDto;
import com.example.banquegestionservice.dto.ResponseCompteDto;
import com.example.banquegestionservice.service.CompteServiceImpl;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@OpenAPIDefinition(
        info = @Info(
                title = "Gestion des comptes bancaire",
                description = "cette offre tous les méthodes pour gérer les comptes",
                version = "1.0.0"
        ),
        servers = @Server(
                url = "http://localhost:8081"
        )
)


@RestController
@RequestMapping("/v1/comptes")
public class ApiRestfull {
    private CompteServiceImpl compteService;
    public ApiRestfull(CompteServiceImpl compteService) {
        this.compteService = compteService;
    }



    @Operation(
            summary = " Ajouter un compte",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = RequestCompteDto.class )
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "bien enregiter",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseCompteDto.class )
                            )
                    ),

                    @ApiResponse(responseCode = "4xx",description = "erreur client"),
                    @ApiResponse(responseCode = "5xx",description = "erreur serveur"),
            }
    )

    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @PostMapping
    public ResponseEntity<ResponseCompteDto> add(@RequestBody RequestCompteDto requestCompteDto) {
       ResponseCompteDto responseCompteDto = compteService.Add_Compte(requestCompteDto);
       return ResponseEntity.ok(responseCompteDto);

    }

    @Operation(
            summary = " récuperer liste des compte",

            responses = {
                    @ApiResponse(responseCode = "200", description = "bien enregiter",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ResponseCompteDto.class ))
                            )
                    ),
                    @ApiResponse(responseCode = "4xx",description = "erreur client"),
                    @ApiResponse(responseCode = "5xx",description = "erreur serveur"),
            }
    )

    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<ResponseCompteDto>> getall(){
        List<ResponseCompteDto> compteDTos = compteService.GETALLCompte();
        return ResponseEntity.ok(compteDTos);

    }

    @Operation(
            summary = " récupérer compte par Id",
            parameters = @Parameter(name = "id", required = true),
            responses = {
                    @ApiResponse(responseCode = "200", description = "bien récuperer",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseCompteDto.class )
                            )
                    ),
                    @ApiResponse(responseCode = "4xx",description = "erreur client"),
                    @ApiResponse(responseCode = "5xx",description = "erreur serveur"),
            }
    )

    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseCompteDto> getCompteById(@PathVariable Integer id) {
      ResponseCompteDto responseCompteDto = compteService.GETCompteById(id);
      return ResponseEntity.ok(responseCompteDto);
    }

    @Operation(
            summary = " Modifier un compte",
            parameters = @Parameter(name = "id", required = true),
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = RequestCompteDto.class )
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "bien modifier",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseCompteDto.class )
                            )
                    ),

                    @ApiResponse(responseCode = "4xx",description = "erreur client"),
                    @ApiResponse(responseCode = "5xx",description = "erreur serveur"),
            }
    )

    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ResponseCompteDto> update(@PathVariable Integer id,
                                                    @RequestBody RequestCompteDto requestCompteDto) {
        ResponseCompteDto responseCompteDto = compteService.UPDATECompte(id, requestCompteDto);
        return ResponseEntity.ok(responseCompteDto);
    }

    @Operation(
            summary = " supprimer compte par Id",
            parameters = @Parameter(name = "id", required = true),
            responses = {
                    @ApiResponse(responseCode = "200", description = "bien supprimer"),
                    @ApiResponse(responseCode = "4xx",description = "erreur client"),
                    @ApiResponse(responseCode = "5xx",description = "erreur serveur"),
            }
    )

    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        compteService.DELETECompteBYID(id);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = " cette methode pour créditer un compte",
            parameters = {
                    @Parameter(name = "id",required = true),
                    @Parameter(name = "m", required = true)
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = " Solde bien modifier",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseCompteDto.class )
                            )
                    ),
                    @ApiResponse(responseCode = "4xx",description = "erreur client"),
                    @ApiResponse(responseCode = "5xx",description = "erreur serveur"),
            }
    )

    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @PatchMapping("/crediter/{id}/{m}")
    ResponseEntity<ResponseCompteDto> crediter(@PathVariable Integer id, @PathVariable Double m) {
        ResponseCompteDto responseCompteDto = compteService.Crediter(id, m);
        return ResponseEntity.ok(responseCompteDto);
    }

    @Operation(
            summary = " cette methode pour debiter un compte",
            parameters = {
                    @Parameter(name = "id",required = true),
                    @Parameter(name = "m", required = true)
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = " Solde bien modifier",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseCompteDto.class )
                            )
                    ),
                    @ApiResponse(responseCode = "4xx",description = "erreur client"),
                    @ApiResponse(responseCode = "5xx",description = "erreur serveur"),
            }
    )

    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @PatchMapping("/debiter/{id}/{m}")
    ResponseEntity<ResponseCompteDto> debiter(@PathVariable Integer id, @PathVariable Double m) {
        ResponseCompteDto responseCompteDto = compteService.Debiter(id, m);
        return ResponseEntity.ok(responseCompteDto);
    }
}
