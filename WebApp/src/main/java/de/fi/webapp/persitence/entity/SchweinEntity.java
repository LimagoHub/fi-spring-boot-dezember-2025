package de.fi.webapp.persitence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "tbl_schweine")
public class SchweinEntity {


    @Id
    private UUID id;
    @Column(length = 50, nullable = false)
    private String name;
    @Column(nullable = false)
    private int gewicht;

    public SchweinEntity() {
    }

    public SchweinEntity(final UUID id, final String name, final int gewicht) {
        this.id = id;
        this.name = name;
        this.gewicht = gewicht;
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getGewicht() {
        return gewicht;
    }

    public void setGewicht(final int gewicht) {
        this.gewicht = gewicht;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SchweinEntity{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", gewicht=").append(gewicht);
        sb.append('}');
        return sb.toString();
    }
}
