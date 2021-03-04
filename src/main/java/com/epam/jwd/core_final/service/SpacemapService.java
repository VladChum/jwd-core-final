package com.epam.jwd.core_final.service;

import com.epam.jwd.core_final.domain.Planet;

import java.util.List;

public interface SpacemapService {

    Planet getRandomPlanet();

    int getDistanceBetweenPlanets(Planet first, Planet second);

    Planet createPlanet(Planet planet) throws RuntimeException;

    List<Planet> findAllPlanet();
}
