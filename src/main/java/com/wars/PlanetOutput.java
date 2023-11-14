package com.wars;

public record PlanetOutput(
        String name,
        String weather,
        String terrain,
        Integer numberOfAppearancesInMovies
) {
}
