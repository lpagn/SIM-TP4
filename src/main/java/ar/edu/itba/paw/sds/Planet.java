package ar.edu.itba.paw.sds;

public class Planet {
    String name;

    double mass;
    double radius;

    Position start;
    Velocity v0;


    public Planet(String name, double mass, double radius, Position start, Velocity v0) {
        this.name = name;
        this.mass = mass;
        this.radius = radius;
        this.start = start;
        this.v0 = v0;
    }

    public void move(Planet [] systme){

    }
}
