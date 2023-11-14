package com.wars;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class PlanetRepository implements PanacheRepositoryBase<Planet, UUID> {
    public Optional<Planet> findByOptionalName(String name) {
        return find("name", name).firstResultOptional();
    }
}
