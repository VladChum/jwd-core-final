package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Planet;
import com.epam.jwd.core_final.factory.EntityFactory;

public class PlanetFactory implements EntityFactory {
    private static PlanetFactory instance;

    public static PlanetFactory getInstance() {
        if (instance == null) {
            instance = new PlanetFactory();
        }
        return instance;
    }

    @Override
    public Planet create(Object... args) {
        if (args.length == 3) {
            return new Planet((String) args[0], (Integer) args[1], (Integer) args[2]);
        }
        throw new IllegalArgumentException("Invalid number of arguments!!!");
    }
}
