package com.epam.jwd.core_final.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleTest {

    @Test
    void resolveRoleById_Positive(){
        assertEquals(Role.MISSION_SPECIALIST, Role.resolveRoleById(1));
    }

    @Test
    void resolveRoleById_Negative(){
        assertNotEquals(Role.MISSION_SPECIALIST, Role.resolveRoleById(4));
    }
}