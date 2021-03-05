package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.domain.Planet;
import com.epam.jwd.core_final.exception.DuplicateException;
import com.epam.jwd.core_final.service.SpacemapService;

import java.util.Collection;
import java.util.List;
import java.util.Random;

public class FindSpacemapService  implements SpacemapService {
    private static FindSpacemapService instance;

    private final Collection<Planet> PLANET_CASH  =
            NassaContext.getInstance().retrieveBaseEntityList(Planet.class);


    public static FindSpacemapService getInstance() {
        if (instance == null) {
            instance = new FindSpacemapService();
        }
        return instance;
    }

    @Override
    public Planet getRandomPlanet() {
        Random random = new Random();
        return (Planet) PLANET_CASH.toArray()[random.nextInt(PLANET_CASH.size())];
    }

    @Override
    public int getDistanceBetweenPlanets(Planet first, Planet second) {
        double distance;
        distance = Math.sqrt(Math.pow(second.point.getX() - first.point.getX(), 2)
                + Math.pow(second.point.getY() - first.point.getY(), 2));
        return (int) Math.round(distance);
    }

    @Override
    public Planet createPlanet(Planet planet) throws DuplicateException {
        boolean isDuplicate = PLANET_CASH.stream()
                .noneMatch(planet1 -> planet1.getName().equals(planet.getName()));

        if (isDuplicate) {
            PLANET_CASH.add(planet);
        }
        return planet;
    }

    @Override
    public List<Planet> findAllPlanet() {
        return (List<Planet>) NassaContext.getInstance().retrieveBaseEntityList(Planet.class);
    }
}
