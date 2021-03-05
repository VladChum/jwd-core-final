package com.epam.jwd.core_final.domain;

import com.epam.jwd.core_final.service.impl.FindMissionService;
import com.epam.jwd.core_final.service.impl.FindSpacemapService;

import java.time.LocalDate;
import java.util.List;

/**
 * Expected fields:
 * <p>
 * missions name {@link String}
 * start date {@link java.time.LocalDate}
 * end date {@link java.time.LocalDate}
 * distance {@link Long} - missions distance
 * assignedSpaceShift {@link Spaceship} - not defined by default
 * assignedCrew {@link java.util.List<CrewMember>} - list of missions members based on ship capacity - not defined by default
 * missionResult {@link MissionResult}
 * from {@link Planet}
 * to {@link Planet}
 */
public class FlightMission extends AbstractBaseEntity {
    private LocalDate startDate;
    private LocalDate endDate;
    private Long distance;
    private List<CrewMember> assignedCrew;
    private Spaceship spaceship;
    private MissionResult missionResult;
    private Planet from;
    private Planet to;

    public FlightMission(String missionName, LocalDate startDate, LocalDate endDate,
                         Planet from, Planet to, Spaceship spaceship,
                         List<CrewMember> crewMembers, MissionResult missionResult) {
        super(missionName);
        this.startDate = startDate;
        this.endDate = endDate;
        this.from = from;
        this.to = to;
        this.missionResult = missionResult;
        this.assignedCrew = crewMembers;
        this.spaceship = spaceship;
        this.distance = Long.valueOf(FindSpacemapService.getInstance().getDistanceBetweenPlanets(from, to));
    }

    @Override
    public String toString() {
        return  getId() + "\t" +
                getName() + "\t" +
                startDate + "\t" +
                endDate + "\t" +
                distance + "\t" +
//                assignedCrew + "\t" +
                spaceship + "\t" +
                missionResult + "\t" +
                from + "\t" +
                to;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public Long getDistance() {
        return distance;
    }

    public List<CrewMember> getAssignedCrew() {
        return assignedCrew;
    }

    public Spaceship getSpaceship() {
        return spaceship;
    }

    public void setSpaceship(Spaceship spaceship) {
        this.spaceship = spaceship;
    }

    public MissionResult getMissionResult() {
        return missionResult;
    }

    public void setMissionResult(MissionResult missionResult) {
        this.missionResult = missionResult;
    }

    public Planet getFrom() {
        return from;
    }

    public void setFrom(Planet from) {
        this.from = from;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Planet getTo() {
        return to;
    }

    public void setTo(Planet to) {
        this.to = to;
    }
}
