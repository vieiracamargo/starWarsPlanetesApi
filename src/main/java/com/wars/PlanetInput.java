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
        public PlanetInput(
                           String name,
                           String weather,
                           String terrain
        ) {
                this.name = name.strip();
                this.weather = weather;
                this.terrain = terrain;
        }


}
