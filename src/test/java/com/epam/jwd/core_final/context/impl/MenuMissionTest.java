package com.epam.jwd.core_final.context.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MenuMissionTest {
    @Test
    void handleUserInput_InputFive_return1() {
        assertEquals(1, MenuMission.getInstance().handleUserInput(5));
    }
}