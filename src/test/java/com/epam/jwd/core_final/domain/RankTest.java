package com.epam.jwd.core_final.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RankTest {
    @Test
    void resolveRankById() {
        assertEquals(Rank.FIRST_OFFICER, Rank.resolveRankById(3));
    }
}