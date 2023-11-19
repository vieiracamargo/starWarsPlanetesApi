package com.wars;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class PlanetNotFoundExceptionMapper implements ExceptionMapper<PlanetNotFoundException> {
    @Override
    public Response toResponse(PlanetNotFoundException planetNotFoundException) {
        return Response.status(Response.Status.NOT_FOUND)
                .entity(Message.fromException(planetNotFoundException))
                .build();
    }
}
