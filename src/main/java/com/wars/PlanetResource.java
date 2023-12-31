package com.wars;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.UUID;

@Path(Paths.ROOT_PATH)
public interface PlanetResource {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Response createPlanet(@Valid PlanetInput input);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{planetId}")
    Response getPlanetById(@PathParam("planetId") UUID planetId);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/name/{planetName}")
    Response getPlanetByName(@PathParam("planetName") @NotBlank String planetName);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    Response getAllPlanets(@BeanParam QueryParams queryParams);

    @DELETE
    @Path("/{planetId}")
    @Produces(MediaType.APPLICATION_JSON)
    Response deletePlanetById(@PathParam("planetId") UUID planetId);
}
