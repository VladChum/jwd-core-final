package com.epam.jwd.core_final.strategy.impl;

import com.epam.jwd.core_final.domain.MissionResult;
import org.apache.log4j.Level;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class MissionCreatStrategyTest {

    @Test
    void missionStatus_ShouldReturnMissionStatus_WhereMissionPlaned() {
        MissionResult missionResult = MissionResult.PLANNED;
        assertEquals(missionResult, MissionCreatStrategy.getInstance()
                .missionStatus(LocalDate.parse("2022-03-03"), LocalDate.parse("2025-03-05")));
    }
}