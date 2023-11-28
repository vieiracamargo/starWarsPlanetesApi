package com.wars;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;

@QuarkusTest
class PlanetResourceImplTest {

    String baseUrl = "http://localhost:8081/api/v1/star-wars/planets";

    @Test
    void shouldGetAllPlanets() {
        given().when()
                .get(baseUrl)
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }
}