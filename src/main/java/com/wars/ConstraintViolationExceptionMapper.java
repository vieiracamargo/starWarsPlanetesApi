package com.wars;

import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {
    @Override
    public Response toResponse(ConstraintViolationException e) {
        var errors = e.getConstraintViolations()
                .stream()
                .map(Message::fromConstraintViolation)
                .toList();

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(new ApiError(errors))
                .build();
    }
}
