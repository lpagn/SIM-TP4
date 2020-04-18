package ar.edu.itba.paw.sds;

public class VerletIntegrationPlanet {
    public static Planet integrate(Planet p, Planet[] planets, double dt){

        Vector position = p.position;
        Vector velocity = p.v;
        Vector acceleration = p.acc;
        velocity = velocity.nSum(acceleration.nMult(dt/2));
        position = position.nSum(velocity.nMult(dt));
        acceleration = p.fr(planets).nDiv(p.mass);
        velocity = velocity.nSum(acceleration.nMult(dt/2));

        return new Planet(p.name,p.mass,p.radius,position,velocity,acceleration);
    }
}
