package de.fi.webapp;


import de.fi.webapp.persitence.entity.PersonEntity;
import de.fi.webapp.persitence.entity.PersonRepository;
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
        PersonEntity person = new PersonEntity(UUID.fromString("4ea2578b-9361-4a1b-bde2-95db9b725682"), "John", "Rambo");
        personRepository.save(person);
    }
}
