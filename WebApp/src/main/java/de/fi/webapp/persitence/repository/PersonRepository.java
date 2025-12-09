package de.fi.webapp.persitence.repository;

import de.fi.webapp.persitence.entity.PersonEntity;
import de.fi.webapp.persitence.entity.TinyPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PersonRepository extends CrudRepository<PersonEntity, UUID> {

    Iterable<PersonEntity> findByVorname(String vorname);

    @Query("select p from PersonEntity p")
    Iterable<PersonEntity> xyz();

    @Query("select p.id, p.nachname from PersonEntity p")
    Iterable<Object[]> findProjection();


    @Query("select new de.fi.webapp.persitence.entity.TinyPerson(p.id, p.nachname) from PersonEntity p")
    Iterable<TinyPerson> findTinies();
}
