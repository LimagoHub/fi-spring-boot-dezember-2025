package de.fi.webapp;


import de.fi.webapp.persitence.entity.PersonEntity;

import de.fi.webapp.persitence.repository.PersonRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class Demo {

    private final PersonRepository personRepository;

    public Demo(final PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @PostConstruct
    public void play() {

        //personRepository.findByVorname("John").forEach(System.out::println);
        personRepository.findTinies().forEach(System.out::println);
    }
}
