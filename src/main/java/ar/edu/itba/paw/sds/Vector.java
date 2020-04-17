package ar.edu.itba.paw.sds;

import java.net.DatagramPacket;

public class Vector{
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

    public Vector nMult(double v) {
        return new Vector(this.x*v,this.y*v);
    }


    public Vector getAdded(Vector v) { return new Vector(this.x + v.x, this.y + v.y);
    }

    public Vector getMultiplied(double scalar) {
        return new Vector(x * scalar, y * scalar);
    }


    public String toString() {
        return this.x +" "+this.y;
    }

    public Vector nRest(Vector v) {
        return new Vector(this.x - v.x, this.y - v.y);
    }
    public Vector nDiv(double scalar) {
        return new Vector(x / scalar, y / scalar);
    }
    public void add(Vector v) {
        this.x += v.x;
        this.y += v.y;
    }
    public double distanceSq(Vector v) {
        double vx = v.x - this.x;
        double vy = v.y - this.y;
        return (vx * vx + vy * vy);
    }
    public double getLength() {
        return Math.sqrt(x * x + y * y);
    }


    public double distance(Vector other) {
        return Math.sqrt(Math.pow(this.x - other.x,2)+Math.pow(this.y-other.y,2));
    }
}
