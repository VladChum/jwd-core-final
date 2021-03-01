package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.domain.Planet;
import com.epam.jwd.core_final.service.SpacemapService;

public class FindSpacemapService  implements SpacemapService {
    private static FindSpacemapService instance;

    public static FindSpacemapService getInstance() {
        if (instance == null) {
            instance = new FindSpacemapService();
        }
        return instance;
    }

    @Override
    public Planet getRandomPlanet() {
        return null;
    }

    @Override
    public int getDistanceBetweenPlanets(Planet first, Planet second) {
        double distance;
        distance = Math.sqrt(Math.pow(second.point.getX() - first.point.getX(), 2)
                + Math.pow(second.point.getY() - first.point.getY(), 2));
        return (int) Math.round(distance);
    }

    @Override
    public Planet createPlanet(Planet planet) throws RuntimeException {
        return null;
    }
}
