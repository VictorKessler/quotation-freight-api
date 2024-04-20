package com.victorkessler.quotationfreight.infrastructure.response;

import java.util.List;

public class RouteResponse {
    private List<Route> routes;
    private List<Waypoint> waypoints;
    private String code;
    private String uuid;

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    public List<Waypoint> getWaypoints() {
        return waypoints;
    }

    public void setWaypoints(List<Waypoint> waypoints) {
        this.waypoints = waypoints;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "RouteResponse{" +
                "routes=" + routes +
                ", waypoints=" + waypoints +
                ", code='" + code + '\'' +
                ", uuid='" + uuid + '\'' +
                '}';
    }

    public static class Route {
        private String weightName;
        private double weight;
        private double duration;
        private double distance;
        private List<Leg> legs;
        private String geometry;

        public String getWeightName() {
            return weightName;
        }

        public void setWeightName(String weightName) {
            this.weightName = weightName;
        }

        public double getWeight() {
            return weight;
        }

        public void setWeight(double weight) {
            this.weight = weight;
        }

        public double getDuration() {
            return duration;
        }

        public void setDuration(double duration) {
            this.duration = duration;
        }

        public double getDistance() {
            return distance;
        }

        public void setDistance(double distance) {
            this.distance = distance;
        }

        public List<Leg> getLegs() {
            return legs;
        }

        public void setLegs(List<Leg> legs) {
            this.legs = legs;
        }

        public String getGeometry() {
            return geometry;
        }

        public void setGeometry(String geometry) {
            this.geometry = geometry;
        }

        @Override
        public String toString() {
            return "Route{" +
                    "weightName='" + weightName + '\'' +
                    ", weight=" + weight +
                    ", duration=" + duration +
                    ", distance=" + distance +
                    ", legs=" + legs +
                    ", geometry='" + geometry + '\'' +
                    '}';
        }
    }

    public static class Leg {
        private List<Admin> admins;
        private double weight;
        private double duration;
        private List<String> steps;
        private double distance;
        private String summary;

        public List<Admin> getAdmins() {
            return admins;
        }

        public void setAdmins(List<Admin> admins) {
            this.admins = admins;
        }

        public double getWeight() {
            return weight;
        }

        public void setWeight(double weight) {
            this.weight = weight;
        }

        public double getDuration() {
            return duration;
        }

        public void setDuration(double duration) {
            this.duration = duration;
        }

        public List<String> getSteps() {
            return steps;
        }

        public void setSteps(List<String> steps) {
            this.steps = steps;
        }

        public double getDistance() {
            return distance;
        }

        public void setDistance(double distance) {
            this.distance = distance;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        @Override
        public String toString() {
            return "Leg{" +
                    "admins=" + admins +
                    ", weight=" + weight +
                    ", duration=" + duration +
                    ", steps=" + steps +
                    ", distance=" + distance +
                    ", summary='" + summary + '\'' +
                    '}';
        }
    }

    public static class Admin {
        private String iso31661Alpha3;
        private String iso31661;

        public String getIso31661Alpha3() {
            return iso31661Alpha3;
        }

        public void setIso31661Alpha3(String iso31661Alpha3) {
            this.iso31661Alpha3 = iso31661Alpha3;
        }

        public String getIso31661() {
            return iso31661;
        }

        public void setIso31661(String iso31661) {
            this.iso31661 = iso31661;
        }

        @Override
        public String toString() {
            return "Admin{" +
                    "iso31661Alpha3='" + iso31661Alpha3 + '\'' +
                    ", iso31661='" + iso31661 + '\'' +
                    '}';
        }
    }

    public static class Waypoint {
        private double distance;
        private String name;
        private List<Double> location;

        public double getDistance() {
            return distance;
        }

        public void setDistance(double distance) {
            this.distance = distance;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Double> getLocation() {
            return location;
        }

        public void setLocation(List<Double> location) {
            this.location = location;
        }

        @Override
        public String toString() {
            return "Waypoint{" +
                    "distance=" + distance +
                    ", name='" + name + '\'' +
                    ", location=" + location +
                    '}';
        }
    }
}

