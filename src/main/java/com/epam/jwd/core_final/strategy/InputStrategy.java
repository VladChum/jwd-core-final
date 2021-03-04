package com.epam.jwd.core_final.strategy;

import com.epam.jwd.core_final.domain.MissionResult;

public interface InputStrategy {
    MissionResult inputMissionResult();

    String inputName();

    Long inputID();

    Long inputDistance();
    Boolean inputReadyForNextMissions();
}
