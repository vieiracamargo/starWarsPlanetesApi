package com.wars;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
public class Planet {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "weather", nullable = false)
    private String weather;
    @Column(name = "terrain", nullable = false)
    private String terrain;
    @Column(name = "number_of_appearances_in_movies", nullable = false)
    private Integer numberOfAppearancesInMovies;
    public Planet(String name, String weather, String terrain, Integer numberOfAppearancesInMovies) {
        this.name = name;
        this.weather = weather;
        this.terrain = terrain;
        this.numberOfAppearancesInMovies = numberOfAppearancesInMovies;
    }

    public Planet() {

    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public String getWeather() {
        return weather;
    }

    public String getTerrain() {
        return terrain;
    }

    public Integer getNumberOfAppearancesInMovies() {
        return numberOfAppearancesInMovies;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public void setTerrain(String terrain) {
        this.terrain = terrain;
    }

    public void setNumberOfAppearancesInMovies(Integer numberOfAppearancesInMovies) {
        this.numberOfAppearancesInMovies = numberOfAppearancesInMovies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Planet planet = (Planet) o;
        return Objects.equals(uuid, planet.uuid) && Objects.equals(name, planet.name) && Objects.equals(weather, planet.weather) && Objects.equals(terrain, planet.terrain) && Objects.equals(numberOfAppearancesInMovies, planet.numberOfAppearancesInMovies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, name, weather, terrain, numberOfAppearancesInMovies);
    }
}
