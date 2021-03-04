package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.domain.Planet;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.assertEquals;

public class FindSpacemapServiceTest {
    FindSpacemapService findSpacemapService;

    @BeforeEach
    void initializeSpacemapService() {
        findSpacemapService = FindSpacemapService.getInstance();
    }

    @Test
    void getDistanceBetweenPlanets() {
        Planet.Point firstPoint = new Planet.Point(2, 2);
        Planet.Point secondPoint = new Planet.Point(5, 6);

        Planet first = new Planet("first", firstPoint);
        Planet second = new Planet("second", secondPoint);
        int actual = findSpacemapService.getInstance().getDistanceBetweenPlanets(first, second);
        int expect = 5;
        assertEquals(expect, actual);
    }
}
