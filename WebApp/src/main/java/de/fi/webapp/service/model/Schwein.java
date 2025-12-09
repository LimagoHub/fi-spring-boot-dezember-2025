package de.fi.webapp.service.model;

import java.util.Objects;
import java.util.UUID;

public class Schwein {
    private UUID id;
    private String name;
    private int gewicht;

    public Schwein(final UUID id, final String name, final int gewicht) {
        this.id = id;
        this.name = name;
        this.gewicht = gewicht;
    }

    public UUID getId() {
        return id;
    }

    private void setId(final UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    private void setName(final String name) {
        this.name = name;
    }

    public int getGewicht() {
        return gewicht;
    }

    private void setGewicht(final int gewicht) {
        this.gewicht = gewicht;
    }

    public void fuettern() {
        setGewicht(getGewicht()+1);
    }

    public void taufen(String name) {
        setName(name);
    }

    @Override
    public boolean equals(final Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        final Schwein schwein = (Schwein) o;
        return gewicht == schwein.gewicht && Objects.equals(id, schwein.id) && Objects.equals(name, schwein.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, gewicht);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Schwein{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", gewicht=").append(gewicht);
        sb.append('}');
        return sb.toString();
    }
}
