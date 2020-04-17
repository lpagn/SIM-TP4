package ar.edu.itba.paw.sds;

public class SpaceShip {

    Vector position;
    Vector v;
    double radio  = 5e+06;

    public SpaceShip(Vector position, Vector v) {
        this.position = position;
        this.v = v;
    }

    public String toOvito(){
        return position.toString() +" "+radio;
    }

    public boolean crash(Planet mars) {
        return Math.sqrt(Math.pow(mars.position.x -this.position.x,2) + Math.pow(mars.position.y -this.position.y,2)) <= mars.radius;
    }
}
