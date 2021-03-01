package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.factory.EntityFactory;

import java.time.LocalDate;

public class FlightMissionFactory implements EntityFactory<FlightMission> {
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
        int distance = 3;
        return new FlightMission((String) args[name],
                (LocalDate) args[startDate],
                (LocalDate) args[endDate],
                (Long) args[distance]);
    }
}
