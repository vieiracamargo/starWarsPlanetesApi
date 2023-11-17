package com.wars;

import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;
import java.util.Optional;
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
        if (planetAlreadyPersisted(input)) {
            throw new PlanetAlreadyPersistedException("This planet is already persisted in database");
        }

        Planet planet = planetMapper.mapInputToEntity(input);
        planetRepository.persist(planet);
    }

    @Transactional
    public PlanetOutput getPlanetById(UUID planetId) {
        Planet planet = planetRepository.findByIdOptional(planetId)
                .orElseThrow(() -> new PlanetNotFoundException("Planet not found"));

        int numberOfAppearancesInMovies = getNumberOfAppearancesInMovies(planet.getName());
        planet.setNumberOfAppearancesInMovies(numberOfAppearancesInMovies);
        return planetMapper.mapEntityToOutput(planet);
    }

    @Transactional
    public PlanetOutput getPlanetByName(String name) {
        Planet planet = planetRepository.findByOptionalName(name)
                .orElseThrow(() -> new PlanetNotFoundException("Planet not found"));

        int numberOfAppearancesInMovies = getNumberOfAppearancesInMovies(name);
        planet.setNumberOfAppearancesInMovies(numberOfAppearancesInMovies);
        return planetMapper.mapEntityToOutput(planet);
    }
    @Transactional
    public List<PlanetOutput> getAllPlanets(QueryParams queryParams) {
        Sort sort = Sort.by(queryParams.getSort(), queryParams.getDirection());
        Page pageable = Page.of(queryParams.getPage(), queryParams.getSize());

        return planetRepository.findAll(sort)
                .page(pageable)
                .list()
                .stream().map(planetMapper::mapEntityToOutput)
                .toList();
    }

    private int getNumberOfAppearancesInMovies(String planetName) {
        Response planets = starWarsService.getPlanet(planetName);
        ResultsItem result = planets.results().stream()
                .filter(p -> planetName.equals(p.name()))
                .findAny()
                .orElseThrow(() -> new PlanetNotFoundException("Planet not found"));

        return result.films().size();
    }

    private boolean planetAlreadyPersisted(PlanetInput input) {
        Optional<Planet> response = planetRepository.findByOptionalName(input.name());
        return response.isPresent();
    }
}
