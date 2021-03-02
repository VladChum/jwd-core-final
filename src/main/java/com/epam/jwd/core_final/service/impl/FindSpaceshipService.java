package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.service.SpaceshipService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class FindSpaceshipService implements SpaceshipService {
    private static FindSpaceshipService instance;

    private final Collection<Spaceship> SPACESHIP_CASH  =
            NassaContext.getInstance().retrieveBaseEntityList(Spaceship.class);

    public static FindSpaceshipService getInstance() {
        if (instance == null) {
            instance = new FindSpaceshipService();
        }
        return instance;
    }

    @Override
    public List<Spaceship> findAllSpaceships() {
        return new ArrayList<>(SPACESHIP_CASH);
    }

    @Override
    public List<Spaceship> findAllSpaceshipsByCriteria(Criteria<? extends Spaceship> criteria) {
        return null;
    }

    @Override
    public Optional<Spaceship> findSpaceshipByCriteria(Criteria<? extends Spaceship> criteria) {
        return Optional.empty();
    }

    @Override
    public Spaceship updateSpaceshipDetails(Spaceship spaceship) {
        return null;
    }

    @Override
    public void assignSpaceshipOnMission(Spaceship crewMember) throws RuntimeException {

    }

    @Override
    public Spaceship createSpaceship(Spaceship spaceship) throws RuntimeException {
        boolean isDuplicate = SPACESHIP_CASH.stream()
                .noneMatch(member -> member.getName().equals(spaceship.getName()));

        if (isDuplicate) {
        SPACESHIP_CASH.add(spaceship);
        }
        return spaceship;
    }
}
