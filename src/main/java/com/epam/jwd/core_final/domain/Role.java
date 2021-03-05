package com.epam.jwd.core_final.domain;

import com.epam.jwd.core_final.exception.UnknownEntityException;

import java.util.Arrays;

public enum Role implements BaseEntity {
    MISSION_SPECIALIST(1L),
    FLIGHT_ENGINEER(2L),
    PILOT(3L),
    COMMANDER(4L);

    private final Long id;

    Role(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    /**
     * todo via java.lang.enum methods!
     */
    @Override
    public String getName() {
        return name();
    }

    /**
     * todo via java.lang.enum methods!
     * @throws UnknownEntityException if such id does not exist
     */
    public static Role resolveRoleById(int id) throws UnknownEntityException{
        return Arrays.stream(Role.values())
                .filter(role -> role.id.equals((long) id))
                .findFirst()
                .orElseThrow(() -> new UnknownEntityException("Impossible to resolve rank by specified id"));

    }
}
