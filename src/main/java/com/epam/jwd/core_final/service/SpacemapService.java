package com.epam.jwd.core_final.service;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Planet;

public interface SpacemapService {

    Planet getRandomPlanet();

    // Dijkstra ?
    int getDistanceBetweenPlanets(Planet first, Planet second);

    Planet createPlanet(Planet planet) throws RuntimeException;

}
