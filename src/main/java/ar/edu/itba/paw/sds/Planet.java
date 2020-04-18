package ar.edu.itba.paw.sds;

public class Planet {
    String name;

    double g = 6.693 * Math.pow(10,-11);

    double mass;
    double radius;
    double gravity;

    Vector fr;

    Vector position;
    Vector v;

    Vector acc= new Vector(0,0);

    public Planet(String name, double mass, double radius, double gravity, Vector start, Vector v) {
        this.name = name;
        this.mass = mass;
        this.radius = radius;
        this.gravity = gravity;
        this.position = start;
        this.v = v;
    }

    public Planet(String name, double mass, double radius, Vector start, Vector v,Vector acc) {
        this.name = name;
        this.mass = mass;
        this.radius = radius;
        this.gravity = gravity;
        this.position = start;
        this.v = v;
        this.acc = acc;
    }

    @Override
    public String toString() {
        return "Planet{" +
                "name='" + name + '\'' +
                ", mass=" + mass +
                ", radius=" + radius +
                ", gravity=" + gravity +
                ", start=" + position +
                ", v0=" + v +
                '}';
    }

    public String toOvito(){
        if(this.name.compareTo("sun")==0){
            return "0 " + this.position.toString()+" "+ this.radius + " 1 1 0";
        }
        if(this.name.compareTo("earth")==0){
            return "1 " + this.position.toString()+" "+ this.radius + " 0 0 1";
        }
        if(this.name.compareTo("spaceShip") == 0){
            return "3 "  + this.position.toString()+" "+ this.radius + " 1 1 1";
        }

        return "2 " + this.position.toString()+" "+ this.radius + " 1 0 0";


    }

    public Vector fr(Planet[] planets){
        Vector res = new Vector(0,0);

        double d,modf;
        Vector versor;

        for (Planet p : planets) {

            d = this.position.distance(p.position);

            modf = -g*p.mass*this.mass/Math.pow(d,2);
            versor = (this.position.nRest(p.position).nDiv(this.position.nRest(p.position).module()));
            res.sum( modf * versor.x , modf * versor.y);
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
        return Math.sqrt(Math.pow(this.position.x-planet.position.x,2) + Math.pow(this.position.y-planet.position.y,2));
    }

    public void move(Planet [] systme){

    }

    public Vector getPosition() {
        return position;
    }

    public void setPosition(Vector position) {
        this.position = position;
    }

    public Vector getV() {
        return v;
    }

    public void setV(Vector v) {
        this.v = v;
    }


}
