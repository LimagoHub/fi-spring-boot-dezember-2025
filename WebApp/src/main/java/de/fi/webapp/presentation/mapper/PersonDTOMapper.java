package de.fi.webapp.presentation.mapper;

import de.fi.webapp.presentation.dto.PersonDTO;
import de.fi.webapp.service.model.Person;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonDTOMapper {

    PersonDTO convert(Person person);
    Person convert(PersonDTO dto);
    Iterable<PersonDTO> convert(Iterable<Person> persons);
}
