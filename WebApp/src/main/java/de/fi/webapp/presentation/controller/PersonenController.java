package de.fi.webapp.presentation.controller;


import de.fi.webapp.presentation.dto.PersonDTO;
import de.fi.webapp.presentation.mapper.PersonDTOMapper;
import de.fi.webapp.service.PersonService;
import de.fi.webapp.service.PersonenServiceException;
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

    private final PersonService personService;
    private final PersonDTOMapper mapper;

    public PersonenController(final PersonService personService, final PersonDTOMapper mapper) {
        this.personService = personService;
        this.mapper = mapper;
    }

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
    public ResponseEntity<PersonDTO> findPersonById(@PathVariable UUID id) throws PersonenServiceException {
        return ResponseEntity.of(personService.findeNachId(id).map(mapper::convert));
    }

    @GetMapping(path="", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<PersonDTO>> findAllPersonen (
            @RequestParam(required = false, defaultValue = "Fritz") String vorname,
            @RequestParam(required = false, defaultValue = "Schmitt")String nachname
    ) throws PersonenServiceException{

        System.out.println(String.format("Vorname = %s, Nachname = %s", vorname, nachname));
        return ResponseEntity.ok(mapper.convert(personService.findeAlle()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersonen(@PathVariable  UUID id) throws PersonenServiceException {
        if(personService.loesche(id)) return ResponseEntity.ok().build();
        return ResponseEntity.notFound().build();
    }
    @PutMapping(path="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updatePerson(@PathVariable  UUID id,@Valid @RequestBody PersonDTO personDTO) throws PersonenServiceException {
        if(personService.aendern(mapper.convert(personDTO)))return ResponseEntity.ok().build();

        return ResponseEntity.notFound().build();
    }

    @PostMapping(path="", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> save(@Valid @RequestBody PersonDTO personDTO, UriComponentsBuilder uriBuilder ) throws PersonenServiceException {
        personService.speichern(mapper.convert(personDTO));
        UriComponents uriComponents = uriBuilder.path("/personen/{id}").buildAndExpand(personDTO.getId());
        return ResponseEntity.created(uriComponents.toUri()).build();
    }
}
