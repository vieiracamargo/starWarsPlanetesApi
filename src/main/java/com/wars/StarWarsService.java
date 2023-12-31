package com.wars;

import io.quarkus.cache.CacheResult;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path(Paths.STAR_WARS_SERVICE)
@RegisterRestClient(configKey = "star-wars-api")
public interface StarWarsService {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @CacheResult(cacheName = "planets")
    Response getPlanet(@QueryParam("search") String planetName);
}
