package de.fi.webapp.service.config;

import de.fi.webapp.persitence.repository.PersonRepository;
import de.fi.webapp.service.PersonService;
import de.fi.webapp.service.internal.PersonServiceImpl;
import de.fi.webapp.service.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.List;

@Configuration
public class PersonenConfig {


    @Bean
    @Qualifier("antipathen")
    public List<String> createAntipathen() {
        return List.of("Attila", "Peter", "Paul", "Mary");
    }

    @Bean
    @Qualifier("fruits")
    public List<String> createFruits() {
        return List.of("Banana", "Apple", "Cherry", "Raspberry");
    }

    @Bean
    public PersonService createPersonService(
            final PersonRepository personRepository,
            final PersonMapper personMapper,
            @Qualifier("antipathen") List<String> antipathen
    ) {
        return new PersonServiceImpl(personRepository, personMapper, antipathen);
    }

}
