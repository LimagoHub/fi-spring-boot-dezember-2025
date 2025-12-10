package de.fi.webapp.presentation.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public class SchweinDTO {

    @NotNull
    private UUID id;
    @NotNull
    @Size(min = 2, max = 100)
    private String name;

    @NotNull
    @DecimalMin("10")
    private int gewicht;

    public SchweinDTO() {
    }

    public SchweinDTO(final UUID id, final String name, final int gewicht) {
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
        final StringBuilder sb = new StringBuilder("SchweinDTO{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", gewicht=").append(gewicht);
        sb.append('}');
        return sb.toString();
    }
}
