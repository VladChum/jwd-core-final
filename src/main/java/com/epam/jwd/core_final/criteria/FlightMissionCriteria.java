package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.domain.Spaceship;

import java.time.LocalDate;
import java.util.List;

/**
 * Should be a builder for {@link com.epam.jwd.core_final.domain.FlightMission} fields
 */
public class FlightMissionCriteria extends Criteria<FlightMission> {
    private String missionName;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long distance;
    private List<CrewMember> assignedCrew;
    private Spaceship spaceship;
    private MissionResult missionResult;

    public FlightMissionCriteria(Builder builder) {
        super(builder);
        missionName = builder.missionName;
        startDate = builder.startDate;
        endDate = builder.endDate;
        distance = builder.distance;
        assignedCrew = builder.assignedCrew;
        spaceship = builder.spaceship;
        missionResult = builder.missionResult;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getMissionName() {
        return missionName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
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

    public MissionResult getMissionResult() {
        return missionResult;
    }

    public static class Builder extends Criteria.Builder<Builder> {
        private String missionName;
        private LocalDate startDate;
        private LocalDate endDate;
        private Long distance;
        private List<CrewMember> assignedCrew;
        private Spaceship spaceship;
        private MissionResult missionResult;

        public Criteria<FlightMission> builder() {
            return new FlightMissionCriteria(this);
        }

        public Builder missionName(String missionName) {
            this.missionName = missionName;
            return this;
        }

        public Builder startDate(LocalDate startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder endDate(LocalDate endDate) {
            this.endDate = endDate;
            return this;
        }

        public Builder distance(Long distance) {
            this.distance = distance;
            return this;
        }

        public Builder assignedCrew(List<CrewMember> assignedCrew) {
            this.assignedCrew = assignedCrew;
            return this;
        }

        public Builder spaceship(Spaceship spaceship) {
            this.spaceship = spaceship;
            return this;
        }

        public Builder missionResult(MissionResult missionResult) {
            this.missionResult = missionResult;
            return this;
        }
    }
}
