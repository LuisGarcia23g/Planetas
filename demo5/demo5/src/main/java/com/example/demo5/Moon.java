package com.example.demo5;

public class Moon extends CelestialBody {
    private final String parentPlanet;

    public Moon(String name, double mass, String parentPlanet, String image) {
        super(name, mass, image);
        this.parentPlanet = parentPlanet;
    }

    public String getParentPlanet() {
        return parentPlanet;
    }

    @Override
    protected double getEscapeVelocity() {
        final double G = 6.67430e-11;
        double radius = 1.737e6; // radio medio de la Luna en metros
        return Math.sqrt(2 * G * getMass() / radius);
    }
}
