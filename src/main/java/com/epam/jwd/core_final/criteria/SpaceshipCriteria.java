package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.Spaceship;

/**
 * Should be a builder for {@link Spaceship} fields
 */
public class SpaceshipCriteria extends Criteria<Spaceship> {
    private Long flightDistance;
    private Boolean isReadyForNextMissions = true;

    public SpaceshipCriteria(Builder builder) {
        super(builder);
        flightDistance = builder.flightDistance;
        isReadyForNextMissions = builder.isReadyForNextMissions;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Long getFlightDistance() {
        return flightDistance;
    }

    public Boolean getReadyForNextMissions() {
        return isReadyForNextMissions;
    }

    public static class Builder extends Criteria.Builder<Builder> {
        private Long flightDistance;
        private Boolean isReadyForNextMissions = true;

        public Criteria<Spaceship> builder() {
            return new SpaceshipCriteria(this);
        }

        public Builder flightDistance(Long flightDistance) {
            this.flightDistance = flightDistance;
            return this;
        }

        public Builder isReadyForNextMissions(Boolean isReadyForNextMissions) {
            this.isReadyForNextMissions = isReadyForNextMissions;
            return this;
        }
    }
}
