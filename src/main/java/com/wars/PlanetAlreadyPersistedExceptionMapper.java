package com.wars;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class PlanetAlreadyPersistedExceptionMapper implements ExceptionMapper<PlanetAlreadyPersistedException> {
    @Override
    public Response toResponse(PlanetAlreadyPersistedException planetAlreadyPersistedException) {

        return Response.status(Response.Status.CONFLICT)
                .entity(Message.fromException(planetAlreadyPersistedException))
                .build();
    }
}
