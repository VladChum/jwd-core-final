package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.service.SpaceshipService;
import com.epam.jwd.core_final.strategy.impl.SpaceshipWriteStrategy;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        return (List<Spaceship>) NassaContext.getInstance().retrieveBaseEntityList(Spaceship.class);
    }

    @Override
    public List<Spaceship> findAllSpaceshipsByCriteria(Criteria<? extends Spaceship> criteria) {
        if (criteria == null) {
            return findAllSpaceships();
        }
        List<Spaceship> spaceships = findAllSpaceships();
        SpaceshipCriteria spaceshipCriteria = (SpaceshipCriteria) criteria;

        return spaceships.stream().filter(spaceship -> (
                (spaceship.getName().equals(spaceshipCriteria.getName()) || spaceshipCriteria.getName() == null)
                        && (spaceship.getId() == spaceshipCriteria.getId()
                        || spaceshipCriteria.getId() == null)
                        && (spaceship.getReadyForNextMissions() == spaceshipCriteria.getReadyForNextMissions()
                        || spaceshipCriteria.getReadyForNextMissions() == null)
                        && (spaceship.getFlightDistance() == spaceshipCriteria.getFlightDistance()
                        || spaceshipCriteria.getFlightDistance() == null)
        )).collect(Collectors.toList());
    }

    @Override
    public Optional<Spaceship> findSpaceshipByCriteria(Criteria<? extends Spaceship> criteria) {
        List<Spaceship> spaceships = findAllSpaceships();
        SpaceshipCriteria spaceshipCriteria = (SpaceshipCriteria) criteria;

        return spaceships.stream().filter(spaceship -> (
                (spaceshipCriteria.getName() == null || spaceship.getName().equals(spaceshipCriteria.getName()))
                        && (spaceshipCriteria.getFlightDistance() == null
                        || spaceship.getFlightDistance() >= spaceshipCriteria.getFlightDistance())
                        && (spaceshipCriteria.getReadyForNextMissions() == null
                        || spaceship.getReadyForNextMissions() == spaceshipCriteria.getReadyForNextMissions())
                        && (spaceshipCriteria.getId() == null
                        || spaceship.getId() >= spaceshipCriteria.getId())
        )).findFirst();
    }

    @Override
    public Spaceship updateSpaceshipDetails(Spaceship spaceship) {
        return null;
    }

    @Override
    public void assignSpaceshipOnMission(Spaceship spaceship) throws RuntimeException {
        spaceship.setReadyForNextMissions(false);
    }

    @Override
    public Spaceship createSpaceship(Spaceship spaceship) throws RuntimeException {
        boolean isDuplicate = SPACESHIP_CASH.stream()
                .noneMatch(member -> member.getName().equals(spaceship.getName()));

        if (isDuplicate) {
        SPACESHIP_CASH.add(spaceship);
            try {
                SpaceshipWriteStrategy.getInstance().writeToFile(spaceship);
            } catch (InvalidStateException e) {
                e.printStackTrace();
            }
        }
        return spaceship;
    }
}
