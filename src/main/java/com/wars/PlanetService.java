package com.wars;

import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.ServiceUnavailableException;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class PlanetService {
    private final PlanetRepository planetRepository;

    private final PlanetMapper planetMapper;

    private static final Logger LOGGER = Logger.getLogger(PlanetService.class);

    @RestClient
    StarWarsService starWarsService;

    public PlanetService(PlanetRepository planetRepository, PlanetMapper planetMapper) {
        this.planetRepository = planetRepository;
        this.planetMapper = planetMapper;
    }

    @Transactional
    public void createPlanet(PlanetInput input) {
        if (planetAlreadyPersisted(input)) {
            throw new PlanetAlreadyPersistedException(ExceptionMessage.PLANET_ALREADY_PERSISTED);
        }

        Planet planet = planetMapper.mapInputToEntity(input);
        String planetName = planet.getName();
        int numberOfAppearancesInMovies = getNumberOfAppearancesInMovies(planetName);
        planet.setNumberOfAppearancesInMovies(numberOfAppearancesInMovies);
        planetRepository.persist(planet);
    }

    @Transactional
    public PlanetOutput getPlanetById(UUID planetId) {
        Planet planet = planetRepository.findByIdOptional(planetId)
                .orElseThrow(() -> new PlanetNotFoundException(ExceptionMessage.PLANET_NOT_FOUND));

        return planetMapper.mapEntityToOutput(planet);
    }

    @Transactional
    public PlanetOutput getPlanetByName(String name) {
        return planetRepository.findByOptionalName(name)
                .orElseThrow(() -> new PlanetNotFoundException(ExceptionMessage.PLANET_NOT_FOUND));
    }

    @Transactional
    public List<PlanetOutput> getAllPlanets(QueryParams queryParams) {
        Sort sort = Sort.by(queryParams.getSort(), queryParams.getDirection());
        Page pageable = Page.of(queryParams.getPage(), queryParams.getSize());

        return planetRepository.findAll(sort)
                .page(pageable)
                .stream()
                .map(planetMapper::mapEntityToOutput)
                .toList();
    }

    @Transactional
    public void deletePlanetById(UUID planetId) {
        Planet planet = planetRepository.findByIdOptional(planetId)
                .orElseThrow(() -> new PlanetNotFoundException(ExceptionMessage.PLANET_NOT_FOUND));

        planetRepository.delete(planet);
    }

    @Retry(maxRetries = 3, delayUnit = ChronoUnit.SECONDS, delay = 2L, retryOn = ServiceUnavailableException.class)
    public Response getPlanetData(String planetName) {
        try {
            return starWarsService.getPlanet(planetName);
        } catch (Exception e) {
            LOGGER.error("Failed to connect with StarWars external api.", e);
            throw new ServiceUnavailableException();
        }
    }

    private int getNumberOfAppearancesInMovies(String planetName) {
        Response planets = getPlanetData(planetName);
        ResultsItem result = planets.results().stream()
                .filter(p -> planetName.equals(p.name()))
                .findAny()
                .orElseThrow(() -> new PlanetNotFoundException(ExceptionMessage.PLANET_NOT_FOUND));

        return result.films().size();
    }

    private boolean planetAlreadyPersisted(PlanetInput input) {
        Optional<PlanetOutput> response = planetRepository.findByOptionalName(input.name());
        return response.isPresent();
    }
}
