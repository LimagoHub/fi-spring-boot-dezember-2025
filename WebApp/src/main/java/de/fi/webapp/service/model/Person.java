package de.fi.webapp.service.model;

import java.util.Objects;
import java.util.UUID;

public class Person {

    private UUID id;
    private String vorname;
    private String nachname;

    public Person() {
    }

    public Person(final UUID id, final String vorname, final String nachname) {
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
    public boolean equals(final Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        final Person person = (Person) o;
        return Objects.equals(id, person.id) && Objects.equals(vorname, person.vorname) && Objects.equals(nachname, person.nachname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, vorname, nachname);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Person{");
        sb.append("id=").append(id);
        sb.append(", vorname='").append(vorname).append('\'');
        sb.append(", nachname='").append(nachname).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
