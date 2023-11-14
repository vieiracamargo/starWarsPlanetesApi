package com.wars;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
@QuarkusTest
class PlanetResourceImplTest {

    PlanetResource resource;


    PlanetService service;

    @BeforeEach
    void setup(){
        service = Mockito.mock(PlanetService.class);
        resource = new PlanetResourceImpl(service);
    }
    @Test
    @Transactional
    void test_should_persist_planet_entity(){
        PlanetInput input = new PlanetInput(
                "Tatooine",
                "Árido",
                "Deserto",
                5
        );

        Response response = resource.createPlanet(input);
        Assertions.assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
        Mockito.verify(service).createPlanet(Mockito.any(Planet.class));
        Mockito.verifyNoMoreInteractions(service);
    }
}