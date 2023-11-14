package com.wars;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PlanetInput(
        @NotBlank
        String name,
        @NotBlank
        String weather,
        @NotBlank
        String terrain,
        @NotNull
        Integer numberOfAppearancesInMovies
) {
}
