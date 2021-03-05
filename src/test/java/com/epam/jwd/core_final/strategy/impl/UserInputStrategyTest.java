package com.epam.jwd.core_final.strategy.impl;

import com.epam.jwd.core_final.domain.MissionResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserInputStrategyTest {
    @Test
    void choseMissionStatus_WhereMissionPlaned() {
        assertEquals(MissionResult.PLANNED, UserInputStrategy.getInstance().choseMissionStatus(3));
    }

    @Test
    void choseMissionStatus_WhereMissionNotCancelled() {
        assertNotEquals(MissionResult.CANCELLED, UserInputStrategy.getInstance().choseMissionStatus(3));
    }
}