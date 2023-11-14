package com.wars;

import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.UUID;

@Path(Paths.ROOT_PATH)
public interface PlanetResource {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    Response createPlanet(@Valid PlanetInput input);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{planetId}")
    Response getPlanetById(@PathParam("planetId") UUID planetId);
}
