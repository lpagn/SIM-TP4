package ar.edu.itba.paw.sds;

public class Planet {
    String name;

    double g = 6.693 * Math.pow(10,-11);

    double mass;
    double radius;
    double gravity;

    Position position;
    Velocity v0;

    public Planet(String name, double mass, double radius, double gravity, Position start, Velocity v0) {
        this.name = name;
        this.mass = mass;
        this.radius = radius;
        this.gravity = gravity;
        this.position = start;
        this.v0 = v0;
    }

    @Override
    public String toString() {
        return "Planet{" +
                "name='" + name + '\'' +
                ", mass=" + mass +
                ", radius=" + radius +
                ", gravity=" + gravity +
                ", start=" + position +
                ", v0=" + v0 +
                '}';
    }

    public String toOvito(){

        return this.position.toString()+" "+ this.radius;


    }

    public Vector fr(Planet[] planets){
        Vector res = new Vector(0,0);
        double d,modf,rx,ry;
        for (Planet planet : planets) {
            d = this.distance(planet);
            modf = -g*(planet.mass*this.mass)/d;
            rx = this.xDistance(planet)/d;
            ry = this.yDistance(planet)/d;
            res.sum( modf * rx , modf * ry);
        }
        return res;
    }
    public  double xDistance(Planet planet){
        return this.position.x - planet.position.x;

    }
    public  double yDistance(Planet planet){
        return this.position.y - planet.position.y;

    }
    public double distance(Planet planet ){
        return Math.sqrt(Math.pow(this.position.x-planet.position.x,2)+Math.pow(this.position.y-planet.position.y,2));
    }

    public void move(Planet [] systme){

    }
}
