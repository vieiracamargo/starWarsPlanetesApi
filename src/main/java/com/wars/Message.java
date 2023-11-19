package com.wars;

import jakarta.validation.ConstraintViolation;

public record Message(
        String field,
        String message
) {
    public static Message fromConstraintViolation(ConstraintViolation<?> constraintViolation) {
        return new Message(
                extractFieldName(constraintViolation.getPropertyPath().toString()),
                constraintViolation.getMessage()
        );
    }

    public static Message fromException(Exception e) {
        return new Message(
                null,
                e.getMessage()
        );
    }


    private static String extractFieldName(String propertyPath) {
        String[] pathElements = propertyPath.split("\\.");
        if (pathElements.length > 0) {
            return pathElements[pathElements.length - 1];
        }
        return propertyPath;
    }
}
