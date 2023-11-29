package com.wars;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
class PlanetResourceImplTest {

    String baseUrl = "http://localhost:8081/api/v1/star-wars/planets";

    @Test
    void test_should_failed_to_create_planet_with_empty_params() {
        Map<String, String> planet = new HashMap<>();
        planet.put("name", "");
        planet.put("weather", "");
        planet.put("terrain", "");

        given()
                .contentType("application/json")
                .body(planet)
                .when()
                .post(baseUrl)
                .then()
                .statusCode(400)
                .body("errors", notNullValue())
                .body("errors", hasSize(3));
    }

    @Test
    void test_should_create_a_planet() {
        Map<String, String> planet = new HashMap<>();
        planet.put("name", "Yavin IV");
        planet.put("weather", "Árido");
        planet.put("terrain", "Deserto");

        given()
                .contentType("application/json")
                .body(planet)
                .when()
                .post(baseUrl)
                .then()
                .statusCode(201);
    }

    @Test
    void test_should_get_all_planets() {
        given().when()
                .get(baseUrl)
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    void test_should_get_a_planet_by_name() {
        var planetName = "Tatooine";
        var url = String.format("%s/name/%s", baseUrl, planetName);
        given()
                .when()
                .get(url)
                .then()
                .statusCode(200)
                .body("uuid", notNullValue())
                .body("name", equalTo(planetName))
                .body("terrain", equalTo("Desert"))
                .body("weather", equalTo("Sunny"));
    }

    @Test
    void test_should_get_a_planet_by_id() {
        var uuid = "123e4567-e89b-12d3-a456-426614174003";
        var url = String.format("%s/%s", baseUrl, uuid);
        given().when()
                .get(url)
                .then()
                .statusCode(200)
                .body("uuid", equalTo(uuid))
                .body("name", equalTo("Hoth"))
                .body("terrain", equalTo("Tundra"))
                .body("weather", equalTo("Frozen"));
    }

    @Test
    void test_should_delete_a_planet_by_id() {
        var uuid = "123e4567-e89b-12d3-a456-426614174003";
        var url = String.format("%s/%s", baseUrl, uuid);
        given().when()
                .delete(url)
                .then()
                .statusCode(204);
    }
}