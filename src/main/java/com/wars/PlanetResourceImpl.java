package com.wars;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class PlanetResourceImpl implements PlanetResource {

    private final PlanetService planetService;

    public PlanetResourceImpl(PlanetService planetService) {
        this.planetService = planetService;
    }
    public Response createPlanet(PlanetInput input ){
        Planet planet = parseToPlanet(input);
        planetService.createPlanet(planet);
        return Response
                .status(Response.Status.CREATED)
                .build();
    }

    private Planet parseToPlanet(PlanetInput input) {
       return new Planet(
                input.name(),
                input.weather(),
                input.terrain(),
                input.numberOfAppearancesInMovies()
        );
    }
}
