package com.wars;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;

import java.util.UUID;

@ApplicationScoped
public class PlanetResourceImpl implements PlanetResource {

    private final PlanetService planetService;

    public PlanetResourceImpl(PlanetService planetService) {
        this.planetService = planetService;
    }
    public Response createPlanet(PlanetInput input){
        planetService.createPlanet(input);
        return Response
                .status(Response.Status.CREATED)
                .build();
    }

    @Override
    public Response getPlanetById(UUID planetId) {
        PlanetOutput output = planetService.getPlanetById(planetId);
        return Response.ok(output).build();
    }

    @Override
    public Response getPlanetByName(String planetName) {
        PlanetOutput output = planetService.getPlanetByName(planetName);
        return Response.ok(output).build();
    }
}
