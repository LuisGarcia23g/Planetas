package com.example.demo5;

public abstract class CelestialBody implements SpaceEntity {
    private final String name;
    private final double mass;
    private final String image;

    public CelestialBody(String name, double mass, String image) {
        this.name = name;
        this.mass = mass;
        this.image = image;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getMass() {
        return mass;
    }

    @Override
    public String getImageUrl() {
        return image;
    }

    protected abstract double getEscapeVelocity();
}
