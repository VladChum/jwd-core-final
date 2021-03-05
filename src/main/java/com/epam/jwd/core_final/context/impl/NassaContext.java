package com.epam.jwd.core_final.context.impl;


import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.domain.BaseEntity;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Planet;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.strategy.impl.CrewMemberFetchStrategy;
import com.epam.jwd.core_final.strategy.impl.PlanetFetchStrategy;
import com.epam.jwd.core_final.strategy.impl.SpaceshipFetchStrategy;
import com.epam.jwd.core_final.util.PropertyReaderUtil;

import java.util.ArrayList;
import java.util.Collection;

// todo
public class NassaContext implements ApplicationContext {

    private static NassaContext instance;

    public static NassaContext getInstance() {
        if (instance == null) {
            instance = new NassaContext();
        }
        return instance;
    }

    // no getters/setters for them
    private Collection<CrewMember> crewMembers = new ArrayList<>();
    private Collection<Spaceship> spaceships = new ArrayList<>();
    private Collection<Planet> planetMap = new ArrayList<>();
    private Collection<Planet> flightMissions = new ArrayList<>();

    @Override
    public <T extends BaseEntity> Collection<T> retrieveBaseEntityList(Class<T> tClass) {
        if (tClass.equals(CrewMember.class)) {
            return (Collection<T>) crewMembers;
        } else if (tClass.equals(Spaceship.class)) {
            return (Collection<T>) spaceships;
        } else if (tClass.equals(Planet.class)) {
            return (Collection<T>) planetMap;
        } else {
            return (Collection<T>) flightMissions;
        }
    }

    /**
     * You have to read input files, populate collections
     * @throws InvalidStateException
     */
    @Override
    public void init() throws InvalidStateException {
        //throw new InvalidStateException();
        PropertyReaderUtil.loadProperties();
        String crewMemberPathName = PropertyReaderUtil.applicationProperties.getCrewFileName();
        CrewMemberFetchStrategy.getInstance().fetchFromFile("input/" + crewMemberPathName);

        String spaceshipsPathName = PropertyReaderUtil.applicationProperties.getSpaceshipsFileName();
        SpaceshipFetchStrategy.getInstance().fetchFromFile("input/" + spaceshipsPathName);

        String planetMapPathName = "spacemap";
        PlanetFetchStrategy.getInstance().fetchFromFile("input/" + planetMapPathName);
    }
}