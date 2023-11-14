package com.wars;

import jakarta.validation.constraints.NotBlank;

public record PlanetInput(
        @NotBlank
        String name,
        @NotBlank
        String weather,
        @NotBlank
        String terrain
) {
}
