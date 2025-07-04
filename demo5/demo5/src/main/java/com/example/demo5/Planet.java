package com.example.demo5;

public class Planet extends CelestialBody {
    private final int numberOfMoons;

    public Planet(String name, double mass, int numberOfMoons, String image) {
        super(name, mass, image);
        this.numberOfMoons = numberOfMoons;
    }

    public int getNumberOfMoons() {
        return numberOfMoons;
    }

    @Override
    protected double getEscapeVelocity() {
        final double G = 6.67430e-11;
        double radius = 6.371e6;
        return Math.sqrt(2 * G * getMass() / radius);
    }
}
