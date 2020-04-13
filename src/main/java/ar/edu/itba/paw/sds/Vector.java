package ar.edu.itba.paw.sds;

public class Vector {
    double x;
    double y;
    public Vector(double x,double y){
        this.x = x;
        this.y = y;
    }
    public void sum(Vector v){
        this.x += v.x;
        this.y += v.y;
    }

    public void sum(double x, double y ){
        this.x+=x;
        this.y+=y;
    }
}
