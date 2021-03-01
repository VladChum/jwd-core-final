package com.epam.jwd.core_final.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * Expected fields:
 * <p>
 * location location could be a simple class Point with 2 coordinates
 */
public class Planet extends AbstractBaseEntity{
    public Point point;

    public Planet(String name, Point point) {
        super(name);
        this.point = point;
    }

    public Planet(String name, int x, int y) {
        super(name);
        this.point = new Point(x, y);
    }
    @Override
    public Long getId() {
        return super.getId();
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public String toString() {
        return "Planet{" +
                "point=" + point +
                '}';
    }

    public static class Point {
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
    }
}
