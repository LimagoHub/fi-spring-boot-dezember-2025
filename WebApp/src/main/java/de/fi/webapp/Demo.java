package de.fi.webapp;


import de.fi.webapp.persitence.entity.PersonEntity;

import de.fi.webapp.persitence.repository.PersonRepository;
import de.fi.webapp.service.MailServiceDummy;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class Demo {

    //private final PersonRepository personRepository;
    private final MailServiceDummy mailServiceDummy;

    public Demo(final MailServiceDummy mailServiceDummy) {
        this.mailServiceDummy = mailServiceDummy;
    }

    @PostConstruct
    public void play() {
        System.out.println(mailServiceDummy);//personRepository.findByVorname("John").forEach(System.out::println);

    }
}
