package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.service.MissionService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class FindMissionService implements MissionService {
    private static final Logger logger = Logger.getLogger(FindMissionService.class);

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
        return new ArrayList<>(FLIGHT_MISSION_CASH);
    }

    @Override
    public List<FlightMission> findAllMissionsByCriteria(Criteria<? extends FlightMission> criteria) {
        return null;
    }

    @Override
    public Optional<FlightMission> findMissionByCriteria(Criteria<? extends FlightMission> criteria) {
        return Optional.empty();
    }

    @Override
    public FlightMission updateSpaceshipDetails(FlightMission flightMission) {
        return null;
    }

    @Override
    public FlightMission createMission(FlightMission flightMission) {
        boolean isDuplicate = true;
        if (FLIGHT_MISSION_CASH.size() != 0) {
            isDuplicate =FLIGHT_MISSION_CASH.stream()
                    .noneMatch(member -> member.getName().equals(flightMission.getName()));

        }
        if (isDuplicate) {
            FLIGHT_MISSION_CASH.add(flightMission);
        }
        return flightMission;
    }
}
