package com.wars;

public class PlanetAlreadyPersistedException extends RuntimeException {
    public PlanetAlreadyPersistedException(String message) {
        super(message);
    }

    public PlanetAlreadyPersistedException(String message, Throwable cause) {
        super(message, cause);
    }
}
