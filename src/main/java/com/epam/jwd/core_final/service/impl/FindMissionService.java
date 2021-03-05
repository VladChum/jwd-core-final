package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.criteria.FlightMissionCriteria;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.exception.DuplicateException;
import com.epam.jwd.core_final.service.MissionService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FindMissionService implements MissionService {

    private static FindMissionService instance;

    private final Collection<FlightMission> FLIGHT_MISSION_CASH  =
            NassaContext.getInstance().retrieveBaseEntityList(FlightMission.class);

    public static FindMissionService getInstance() {
        if (instance == null) {
            instance = new FindMissionService();
        }
        return instance;
    }

    @Override
    public List<FlightMission> findAllMissions() {
        return (List<FlightMission>) NassaContext.getInstance().retrieveBaseEntityList(FlightMission.class);
    }

    @Override
    public List<FlightMission> findAllMissionsByCriteria(Criteria<? extends FlightMission> criteria) {
        if (criteria == null) {
            return findAllMissions();
        }

        List<FlightMission> flightMissions = findAllMissions();
        FlightMissionCriteria flightMissionCriteria = (FlightMissionCriteria) criteria;

        return flightMissions.stream().filter(flightMission -> (
                (flightMission.getName().equals(flightMissionCriteria.getName())
                        || flightMissionCriteria.getName() == null)
                        && (flightMissionCriteria.getDistance().equals(flightMission.getDistance())
                        || flightMissionCriteria.getDistance() == null)
                        && (flightMissionCriteria.getStartDate().equals(flightMission.getStartDate())
                        || flightMissionCriteria.getStartDate() == null)
        )).collect(Collectors.toList());
    }

    @Override
    public Optional<FlightMission> findMissionByCriteria(Criteria<? extends FlightMission> criteria) {
        List<FlightMission> missions = findAllMissions();
        FlightMissionCriteria flightMissionCriteria = (FlightMissionCriteria) criteria;

        return missions.stream().filter(mission -> (
                        (flightMissionCriteria.getName() == null
                                || mission.getName().equals(flightMissionCriteria.getName()))
                                && (flightMissionCriteria.getDistance() == null
                                || flightMissionCriteria.getDistance().equals(mission.getDistance()))
                                && (flightMissionCriteria.getMissionResult() == null
                                || flightMissionCriteria.getMissionResult().equals(mission.getMissionResult())
                                && (flightMissionCriteria.getId() == null
                                || flightMissionCriteria.getId().equals(mission.getId())))
                )
        ).findFirst();
    }

    @Override
    public FlightMission updateSpaceshipDetails(FlightMission flightMission) {
        if (flightMission.getMissionResult().equals(MissionResult.FAILED)) {
            flightMission.getSpaceship().setReadyForNextMissions(false);
        }
        return flightMission;
    }

    @Override
    public FlightMission createMission(FlightMission flightMission) throws DuplicateException {
        boolean isDuplicate = true;
        if (FLIGHT_MISSION_CASH.size() != 0) {
            isDuplicate =FLIGHT_MISSION_CASH.stream()
                    .noneMatch(member -> member.getName().equals(flightMission.getName()));

        }
        if (isDuplicate) {
            FLIGHT_MISSION_CASH.add(flightMission);
        }
//        else {
//            throw new DuplicateException("Impossible to create duplicate mission!");
//        }
        return flightMission;
    }
}
