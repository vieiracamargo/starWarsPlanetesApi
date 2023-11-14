package com.wars;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PlanetMapper {
    public Planet mapInputToEntity(PlanetInput input){
        return new Planet(
                input.name(),
                input.weather(),
                input.terrain(),
                input.numberOfAppearancesInMovies()
        );
    }
}
