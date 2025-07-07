package com.demo.demo.structure.controller;

import com.demo.demo.structure.facade.UserControllerFacade;
import com.demo.demo.structure.mapper.UserControllerMapper;
import com.demo.demo.structure.model.dto.CreateUserDto;
import com.demo.demo.structure.model.dto.UpdateUserDto;
import com.demo.demo.structure.model.resource.CreateUserResource;
import com.demo.demo.structure.model.resource.GetUserResource;
import com.demo.demo.structure.model.resource.UserSearchResource;
import com.demo.demo.structure.model.resource.UserSearchResourceList;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User API", description = "Gestione CRUD degli utenti")
public class UserController {

    private UserControllerMapper mapper;
    private UserControllerFacade facade;

    @Operation(summary = "Importa utenti da file CSV", description = "Legge un file CSV e salva utenti nel database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Utenti importati con successo"),
            @ApiResponse(responseCode = "400", description = "Errore nel formato del file o dati malformati")
    })
    @PostMapping("/upload")
    public ResponseEntity<Void> uploadUsersFromCsv(@Parameter(description = "File CSV con utenti", required = true,
            content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE)) @RequestPart("file") MultipartFile file) {
        facade.insertUsersByCsv(file);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Crea un nuovo utente", description = "Salva un nuovo utente nel database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Utente creato con successo"),
            @ApiResponse(responseCode = "400", description = "Dati malformati nella richiesta")
    })
    @PostMapping
    public ResponseEntity<CreateUserResource> createUser(@RequestBody @Valid CreateUserDto dto) {
        return ResponseEntity.ok(mapper.fromBinToDto(facade.createUser(mapper.fromDtoToBin(dto))));
    }

    @Operation(summary = "Recupera un utente per ID", description = "Restituisce un utente specifico cercandolo tramite il suo UUID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Utente trovato e restituito correttamente"),
            @ApiResponse(responseCode = "404", description = "Utente non trovato per l'ID fornito"),
            @ApiResponse(responseCode = "400", description = "Richiesta malformata, per esempio un ID non valido")
    })
    @GetMapping("/{id}")
    public ResponseEntity<GetUserResource> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(mapper.fromBinToDtoGet(facade.getUser(mapper.fromDtoToBinGet(id))));
    }

    @Operation(summary = "Aggiorna un utente", description = "Aggiorna i dati di un utente esistente tramite il suo UUID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Utente aggiornato correttamente"),
            @ApiResponse(responseCode = "404", description = "Utente non trovato per l'ID fornito"),
    })
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable Long id, @RequestBody UpdateUserDto updateUserDto) {
        facade.updateUser(mapper.fromDtoToBinUpdate(updateUserDto, id));
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Elimina un utente", description = "Rimuove un utente dal database tramite il suo UUID")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Utente eliminato con successo"),})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        facade.deleteUser(mapper.fromDtoToBinDelete(id));
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Ricerca utenti", description = "Ricerca utenti con filtri dinamici")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Utenti trovati con successo"),
            @ApiResponse(responseCode = "400", description = "Richiesta malformata")
    })
    @GetMapping("/search")
    public ResponseEntity<UserSearchResourceList> userSearch(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String lastname,
            @RequestParam(required = false) String email) {

        List<UserSearchResource> userResources = facade.userSearch(mapper.fromDtoToBinSearch(name, lastname, email))
                .getUserSearchOutputBinList().stream().map(mapper::fromBinToDtoResource)
                .toList();

        return ResponseEntity.ok(UserSearchResourceList.builder().userSearchResourceList(userResources).build());
    }

    @Autowired
    public void setMapper(UserControllerMapper mapper) {
        this.mapper = mapper;
    }

    @Autowired
    public void setFacade(UserControllerFacade facade) {
        this.facade = facade;
    }
}