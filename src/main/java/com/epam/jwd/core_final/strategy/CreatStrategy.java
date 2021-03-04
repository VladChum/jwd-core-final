package com.epam.jwd.core_final.strategy;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.domain.Spaceship;

import java.time.LocalDate;
import java.util.List;

public interface CreatStrategy {

    MissionResult missionStatus(LocalDate startDate, LocalDate endDate);

    Spaceship addSpaceship(MissionResult missionResult);

    List<CrewMember> addCrewMemberForMission(Spaceship spaceship, MissionResult missionResult);
}
