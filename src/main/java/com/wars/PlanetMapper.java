package com.wars;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PlanetMapper {
    public Planet mapInputToEntity(PlanetInput input){
        return new Planet(
                input.name(),
                input.weather(),
                input.terrain(),
                null
        );
    }

    public PlanetOutput mapEntityToOutput(Planet planet){
        return new PlanetOutput(
                planet.getName(),
                planet.getWeather(),
                planet.getTerrain(),
                planet.getNumberOfAppearancesInMovies()
        );
    }
}
