package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.context.impl.MenuCreatMission;
import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.factory.EntityFactory;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.util.List;

public class FlightMissionFactory implements EntityFactory<FlightMission> {
    private static final Logger logger = Logger.getLogger(FlightMissionFactory.class);

    private static FlightMissionFactory instance;

    public static FlightMissionFactory getInstance() {
        if (instance == null) {
            instance = new FlightMissionFactory();
        }
        return instance;
    }

    @Override
    public FlightMission create(Object... args) {
        int name = 0;
        int startDate = 1;
        int endDate = 2;
        int from = 3;
        int to = 4;
        int spaceship = 5;
        int crewMember = 6;
        int missionResult = 7;
        logger.log(Level.DEBUG, "Start flight mission factory ");
        return new FlightMission((String) args[name],
                (LocalDate) args[startDate],
                (LocalDate) args[endDate],
                (Planet) args[from],
                (Planet) args[to],
                (Spaceship) args[spaceship],
                (List<CrewMember>) args[crewMember],
                (MissionResult) args[missionResult]);
    }
}
