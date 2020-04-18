package ar.edu.itba.paw.sds;

public class Vector{

    double x;
    double y;

    public Vector(double x,double y){
        this.x = x;
        this.y = y;
    }
    public void sumVec(Vector v){
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
    public Vector nSum(Vector v) { return new Vector(this.x + v.x, this.y + v.y);
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
    public double module() {
        return Math.sqrt(Math.pow(x,2) + Math.pow(y,2));
    }
    public double distance(Vector other) {
        return Math.sqrt(Math.pow(this.x - other.x,2)+Math.pow(this.y-other.y,2));
    }
}
