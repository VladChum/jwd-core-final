package com.epam.jwd.core_final.domain;

import java.util.Map;

/**
 * crew {@link java.util.Map<Role, Short>}
 * flightDistance {@link Long} - total available flight distance
 * isReadyForNextMissions {@link Boolean} - true by default. Set to false, after first failed mission
 */
public class Spaceship extends AbstractBaseEntity {
    //todo

    private Map<Role, Short> crew;
    private Long flightDistance;
    private Boolean isReadyForNextMissions = true;

    public Spaceship(String name, Long flightDistance, Map<Role, Short> crew) {
        super(name);
        this.crew = crew;
        this.flightDistance = flightDistance;
        this.isReadyForNextMissions = true;
    }

    @Override
    public String toString() {
        return getId() + "\t" +
                crew + "\t" +
                isReadyForNextMissions + "\t" +
                flightDistance + "\t" +
                getName();
    }

    public Map<Role, Short> getCrew() {
        return crew;
    }

    public void setCrew(Map<Role, Short> crew) {
        this.crew = crew;
    }

    public Long getFlightDistance() {
        return flightDistance;
    }

    public Boolean getReadyForNextMissions() {
        return isReadyForNextMissions;
    }

    public void setReadyForNextMissions(Boolean readyForNextMissions) {
        isReadyForNextMissions = readyForNextMissions;
    }
}
