package de.fi.webapp.presentation.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class SchweinDTO {
    private UUID id;
    private String name;

    @NotNull
    @DecimalMin("10")
    private int gewicht;
}
