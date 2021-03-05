package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.Planet;

public class PlanetCriteria extends Criteria<Planet> {
    private Planet.Point point;

    public PlanetCriteria (Builder builder) {
        super(builder);
        point = builder.point;
    }

    public static PlanetCriteria.Builder builder() {
        return new PlanetCriteria.Builder();
    }

    public Planet.Point getPoint() {
        return point;
    }

    public static class Builder extends Criteria.Builder<PlanetCriteria.Builder> {
        private Planet.Point point;

        public Criteria<Planet> builder() {
            return new PlanetCriteria(this);
        }

        public PlanetCriteria.Builder point(Planet.Point point) {
            this.point = point;
            return this;
        }
    }
}
