package com.wars;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.UUID;

@ApplicationScoped
public class PlanetService {
    private final PlanetRepository planetRepository;

    private final PlanetMapper planetMapper;

    @RestClient
    StarWarsService starWarsService;

    public PlanetService(PlanetRepository planetRepository, PlanetMapper planetMapper) {
        this.planetRepository = planetRepository;
        this.planetMapper = planetMapper;
    }

    @Transactional
    public void createPlanet(PlanetInput input) {
        Planet planet = planetMapper.mapInputToEntity(input);
        planetRepository.persist(planet);
    }

    public PlanetOutput getPlanetById(UUID planetId) {
        Planet planet = planetRepository.findById(planetId);
        Response planets = starWarsService.getAllPlanets();
        ResultsItem result = findPlanetByName(planet.getName(), planets);
        planet.setNumberOfAppearancesInMovies(getNumberOfAppearancesInMovies(result));
        return planetMapper.mapEntityToOutput(planet);
    }

    private ResultsItem findPlanetByName(String planetName, Response planets) {
        return planets.results().stream()
                .filter(p -> planetName.equals(p.name()))
                .findAny()
                .orElseThrow(()-> new PlanetNotFoundException("Planet not found"));
    }

    private static int getNumberOfAppearancesInMovies(ResultsItem result) {
        return result.films().size();
    }
}
