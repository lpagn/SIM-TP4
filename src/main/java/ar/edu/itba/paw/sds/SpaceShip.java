package ar.edu.itba.paw.sds;

public class SpaceShip {

    Position position;
    Velocity v;
    double radio  = 5e+06;

    public SpaceShip(Position position, Velocity v) {
        this.position = position;
        this.v = v;
    }

    public String toOvito(){
        return position.toString() +" "+radio;
    }
}
