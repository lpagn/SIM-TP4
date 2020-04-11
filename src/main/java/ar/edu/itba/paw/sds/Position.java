package ar.edu.itba.paw.sds;

public class Position {
    double x;
    double y;

    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return x +" "+ y;
    }
}
