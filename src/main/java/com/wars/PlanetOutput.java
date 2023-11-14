package com.wars;

import jakarta.json.bind.annotation.JsonbProperty;

public record PlanetOutput(
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
