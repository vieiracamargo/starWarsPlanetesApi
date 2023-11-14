package com.wars;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class PlanetService {
    private final PlanetRepository planetRepository;

    private final PlanetMapper planetMapper;

    public PlanetService(PlanetRepository planetRepository, PlanetMapper planetMapper) {
        this.planetRepository = planetRepository;
        this.planetMapper = planetMapper;
    }

    @Transactional
    public void createPlanet(PlanetInput input) {
        Planet planet = planetMapper.mapInputToEntity(input);
        planetRepository.persist(planet);
    }
}
