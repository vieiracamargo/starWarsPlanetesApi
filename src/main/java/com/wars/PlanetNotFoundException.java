package com.wars;

public class PlanetNotFoundException extends RuntimeException {
    public PlanetNotFoundException(String message) {
        super(message);
    }

    public PlanetNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
