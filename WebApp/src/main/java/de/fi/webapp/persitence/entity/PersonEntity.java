package de.fi.webapp.persitence.entity;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tbl_personen")// Mapping
public class PersonEntity {

    @Id
    @Column(nullable = false, unique = true)
    private UUID id;
    @Column(length = 20, nullable = false)
    private String vorname;
    @Column(length = 20, nullable = false)
    private String nachname;

    public PersonEntity() {
    }

    public PersonEntity(final UUID id, final String vorname, final String nachname) {
        this.id = id;
        this.vorname = vorname;
        this.nachname = nachname;
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(final String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(final String nachname) {
        this.nachname = nachname;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PersonEntity{");
        sb.append("id=").append(id);
        sb.append(", vorname='").append(vorname).append('\'');
        sb.append(", nachname='").append(nachname).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(final Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        final PersonEntity that = (PersonEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(vorname, that.vorname) && Objects.equals(nachname, that.nachname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, vorname, nachname);
    }
}
