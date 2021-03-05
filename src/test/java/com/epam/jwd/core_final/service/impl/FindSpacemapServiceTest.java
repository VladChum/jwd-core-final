package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.domain.Planet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FindSpacemapServiceTest {

    @Test
    void getInstance() {
        Planet first = new Planet("first", 2, 2);
        Planet second = new Planet("second", 5, 6);
        assertEquals(5, FindSpacemapService.getInstance().getDistanceBetweenPlanets(first, second));
    }

    @Test
    void getInstance_Negative() {
        Planet first = new Planet("first", 5, 7);
        Planet second = new Planet("second", 12, 6);
        assertNotEquals(20, FindSpacemapService.getInstance().getDistanceBetweenPlanets(first, second));
    }
}