package de.fi.webapp.presentation.controller;


import de.fi.webapp.presentation.dto.PersonDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/personen")
public class PersonenController {

    @Operation(summary = "Liefert eine Person")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Person gefunden",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PersonDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "ungueltige ID",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Person nicht gefunden",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "internal server error",
                    content = @Content)})

    @GetMapping(path="/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<PersonDTO> findPersonById(@PathVariable UUID id) {
        if(id.toString().endsWith("7")) {

            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new PersonDTO(UUID.randomUUID(), "John", "Doe"));
    }

    @GetMapping(path="", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<PersonDTO>> findAllPersonen(
            @RequestParam(required = false, defaultValue = "Fritz") String vorname,
            @RequestParam(required = false, defaultValue = "Schmitt")String nachname
    ) {

        System.out.println(String.format("Vorname = %s, Nachname = %s", vorname, nachname));
        List<PersonDTO> personen = List.of(

                new PersonDTO(UUID.randomUUID(), "John", "Doe"),
                new PersonDTO(UUID.randomUUID(), "John", "Rambo"),
                new PersonDTO(UUID.randomUUID(), "John", "McClaine"),
                new PersonDTO(UUID.randomUUID(), "John", "Wick"),
                new PersonDTO(UUID.randomUUID(), "John Boy", "Walton")

        );

        return ResponseEntity.ok(personen);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersonen(@PathVariable  UUID id) {
        if(id.toString().endsWith("7")) {
            return ResponseEntity.notFound().build();
        }
        System.out.println("Person mit der ID " + id.toString() + " wird geloescht");
        return ResponseEntity.ok().build();
    }
    @PutMapping(path="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updatePerson(@PathVariable  UUID id,@Valid @RequestBody PersonDTO personDTO) {
        if(! id.equals(personDTO.getId())) {
            throw new IllegalArgumentException("Upps");
        }
        if(id.toString().endsWith("7")) {
            return ResponseEntity.notFound().build();
        }
        System.out.println("Person mit der ID " + id.toString() + " wird geaendert");
        return ResponseEntity.ok().build();
    }

    @PostMapping(path="", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> save(@Valid @RequestBody PersonDTO personDTO, UriComponentsBuilder uriBuilder ){
        // Person speichern
        UriComponents uriComponents = uriBuilder.path("/personen/{id}").buildAndExpand(personDTO.getId());
        return ResponseEntity.created(uriComponents.toUri()).build();
    }
}
