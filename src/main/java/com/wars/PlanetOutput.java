package com.wars;

import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.json.bind.annotation.JsonbPropertyOrder;

import java.util.UUID;
@JsonbPropertyOrder({"uuid","name","weather","terrain","number_of_appearances_in_movies"})
@RegisterForReflection
public record PlanetOutput(
        @JsonbProperty("uuid")
        UUID uuid,
        @JsonbProperty("name")
        String name,
        @JsonbProperty("weather")
        String weather,
        @JsonbProperty("terrain")
        String terrain,
        @JsonbProperty("number_of_appearances_in_movies")
        Integer numberOfAppearancesInMovies
) {
}
