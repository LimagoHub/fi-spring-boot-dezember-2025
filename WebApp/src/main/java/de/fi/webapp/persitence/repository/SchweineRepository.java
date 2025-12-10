package de.fi.webapp.persitence.repository;


import de.fi.webapp.persitence.entity.SchweinEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface SchweineRepository extends CrudRepository<SchweinEntity, UUID> {

    //@Query("update SchweinEntity s set s.gewicht = :gewicht where s.id=:id")
    //void updateSchwein(int gewicht, UUID id);
}
