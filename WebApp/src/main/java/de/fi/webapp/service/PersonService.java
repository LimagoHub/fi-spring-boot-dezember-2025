package de.fi.webapp.service;

import de.fi.webapp.service.model.Person;

import java.util.Optional;
import java.util.UUID;

public interface PersonService {
    void speichern(Person person) throws PersonenServiceException ;
    boolean aendern(Person person) throws PersonenServiceException ;
    boolean loesche(UUID id) throws PersonenServiceException ;
    Optional<Person> findeNachId(UUID id) throws PersonenServiceException ;
    Iterable<Person> findeAlle() throws PersonenServiceException ;
}
