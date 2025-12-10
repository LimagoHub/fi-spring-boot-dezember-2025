package de.fi.webapp.service.internal;

import de.fi.webapp.persitence.repository.PersonRepository;
import de.fi.webapp.service.BlacklistService;
import de.fi.webapp.service.PersonService;
import de.fi.webapp.service.PersonenServiceException;
import de.fi.webapp.service.mapper.PersonMapper;
import de.fi.webapp.service.model.Person;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


//@Service
@Transactional(rollbackFor = PersonenServiceException.class, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper mapper;
    private final List<String> antipathen;

    public PersonServiceImpl(final PersonRepository personRepository, final PersonMapper mapper,@Qualifier("antipathen") final List<String> antipathen) {
        this.personRepository = personRepository;
        this.mapper = mapper;
        this.antipathen = antipathen;
    }

   /*
                person == null -> PSE
                vorname == null oder zu kurz ->PSE
                nachname dito

                Attila -> PSE

                exception in underlying service ->PSE

                happy day -> Person to Repo

             */



        @Override
    public void speichern(final Person person) throws PersonenServiceException {
        try {
            if(person == null)
                throw new PersonenServiceException("Person ist null");

            if(person.getVorname() == null || person.getVorname().length() < 2)
                throw new PersonenServiceException("Vorname zu kurz");

            if(person.getNachname() == null || person.getNachname().length() < 2)
                throw new PersonenServiceException("Vorname zu kurz");

            if(antipathen.contains(person.getVorname()) )
                throw new PersonenServiceException("Antipath");

            personRepository.save(mapper.convert(person));
        } catch (RuntimeException e) {
            throw new PersonenServiceException("Fehler beim Speichern der Person", e);
        }
    }

    @Override
    public boolean aendern(final Person person) throws PersonenServiceException {
        if(! personRepository.existsById(person.getId())) return false;
        speichern(person);
        return true;
    }

    @Override
    public boolean loesche(final UUID id) throws PersonenServiceException {
        try {
            if(! personRepository.existsById(id)) return false;
            personRepository.deleteById(id);
            return true;
        } catch (RuntimeException e) {
            throw new PersonenServiceException("Upps",e);
        }
    }
    @Transactional( isolation = Isolation.READ_UNCOMMITTED)

    @Override
    public Optional<Person> findeNachId(final UUID id) throws PersonenServiceException {
        try {
            return personRepository.findById(id).map(mapper::convert);
        } catch (Exception e) {
            throw new PersonenServiceException("Upps",e);
        }
    }

    @Override
    public Iterable<Person> findeAlle() throws PersonenServiceException {
        try {
            return mapper.convert(personRepository.findAll());
        } catch (Exception e) {
            throw new PersonenServiceException("Upps", e);
        }
    }
}
