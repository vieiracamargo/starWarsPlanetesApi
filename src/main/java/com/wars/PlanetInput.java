package com.wars;

import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.validation.constraints.NotBlank;

public record PlanetInput(
        @NotBlank
        @JsonbProperty("name")
        String name,
        @NotBlank
        @JsonbProperty("weather")
        String weather,
        @NotBlank
        @JsonbProperty("terrain")
        String terrain
) {
}
