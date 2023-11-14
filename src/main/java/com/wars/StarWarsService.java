package com.wars;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path(Paths.STAR_WARS_SERVICE)
@RegisterRestClient(configKey = "star-wars-api")
public interface StarWarsService {
    @GET
    Response getAllPlanets();
}
