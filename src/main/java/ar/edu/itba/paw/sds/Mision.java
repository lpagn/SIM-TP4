package ar.edu.itba.paw.sds;

import java.io.FileWriter;
import java.io.IOException;

public class Mision {
	
	public double fly(int deltaDay , double step , double ventana) throws IOException {

        //fuente pagina oficial de la nasa
		
        double SUN_MASS = 1.989 * Math.pow(10,30);//kg
        double SUN_RADIUS = 3e+07*1000;//m
        double SUN_GRAVITY = 274;//m/s²

        double EARTH_MASS = 5.97219*Math.pow(10,24);//kg
        double EARTH_RADIUS = 2e+07*1000; //m
        double EARTH_GRAVITY = 9.807;//m/s²

        double MARS_MASS = 6.4171 * Math.pow(10,23); //kg
        double MARS_RADIUS = 1e+07*1000;//m
        double MARS_GRAVITY = 3.711;//m/s²

        double SUN_TO_EARTH = 149.6 * 1000000 *1000;//m

        double SUN_TO_MARS = 219.69 *1000000 *1000; //m

        //constante universal gravitacional universal
        
        double g = 6.693 * Math.pow(10,-11);

        double L = SUN_TO_EARTH*3;
        Vector SUN_POSITION = new Vector(0,0);
        Vector SUN_VELOCITY = new Vector(0,0);

        Vector EARTH_POSITION = new Vector(-1.436232264182898E+08 *1000,-4.222184246295860E+07 *1000);
        Vector EARTH_VELOCITY =  new Vector(7.917904169940719E+00 *1000,-2.867871052093815E+01 *1000);

        Vector SPACESHIP_POSITION = new Vector((-1.443100400036066E+08*1000-EARTH_RADIUS-1500 ),
                (-4.114399753573054E+07/-1.443100400036066E+08)*(-1.443100400036066E+08*1000-EARTH_RADIUS-1500));


        Vector MARS_POSITION = new Vector(-2.471238977495339E+07 *1000,-2.183737229441134E+08 *1000);
        Vector MARS_VELOCITY = new Vector(2.499118636997282E+01 *1000,-6.412328574419259E-01 *1000);

        Planet sun = new Planet("sun",SUN_MASS,SUN_RADIUS,SUN_GRAVITY,SUN_POSITION,SUN_VELOCITY);
        Planet earth = new Planet("earth",EARTH_MASS,EARTH_RADIUS,EARTH_GRAVITY,EARTH_POSITION,EARTH_VELOCITY);
        Planet mars = new Planet("mars",MARS_MASS,MARS_RADIUS,MARS_GRAVITY,MARS_POSITION,MARS_VELOCITY);


        Planet spaceSheep = new Planet("spaceShip",1,MARS_RADIUS,0,SPACESHIP_POSITION,EARTH_VELOCITY);
        
        
        
        boolean launched = false;
        
        double time = 0;


        FileWriter fw = new FileWriter("sim.txt");

        final Planet[] planetsForSun = new Planet[]{earth,mars};
        final Planet[] planetsForEarth = new Planet[]{sun,mars};
        final Planet[] planetsForMars = new Planet[]{sun,earth};
        final Planet[] planetsForSpaceShip = new Planet[]{sun,earth,mars};

        //double step = 5;

        earth.acc= earth.fr(planetsForEarth).nMult(1/earth.mass);
        mars.acc = mars.fr(planetsForMars).nMult(1/mars.mass);
        spaceSheep.acc = spaceSheep.fr(planetsForSpaceShip).nMult(1/spaceSheep.mass);
        
        int days = 0;
        
        while (time < ventana) {
        	if(days==deltaDay) {
        		launched =true;
        		Vector s = new Vector((earth.position.x-EARTH_RADIUS-1500 ),earth.position.y-EARTH_RADIUS-1500);
        		spaceSheep.setPosition(s);
        		spaceSheep.v = spaceShipVelocity(earth,8,sun);
        	}
        	if(time % 86400 == 0) {	
        		days++;
	    		if(launched) {
	                fw.write("8\n\n");
	                fw.write(launchedStr(earth,mars,sun,spaceSheep));
	    		}else {
	                fw.write("8\n\n");
	                fw.write(launchedStr(earth,mars,sun));
	    		}
        	}
        	
            Planet auxEarth = integrationVerletPlanet(earth,new Planet[]{sun,mars,spaceSheep},step);
            
            Planet auxMars = integrationVerletPlanet(mars,new Planet[]{earth,sun,spaceSheep},step);
            
            if(launched) {
            	Planet auxSpaceSheep = integrationVerletPlanet(spaceSheep,new Planet[]{sun,earth,mars},step) ;
            	spaceSheep = auxSpaceSheep;
            }
            
            earth = auxEarth;
            mars = auxMars;
            
            time += step;
        }
        fw.close();

		return step;
	}
	
	public static String ovitoVlock(){
        double sides = 219.69 *1000000 * 1.5 *1000; //m
        return (sides)+" "+(sides)+" "+"0 0 0 0"+"\n"+
                (sides)+" "+(-sides)+" "+"0 0 0 0"+"\n"+
                (-sides)+" "+(sides)+" "+"0 0 0 0"+"\n"+
                (-sides)+" "+(-sides)+" "+"0 0 0 0";
    }

    static Planet integrationVerletPlanet(Planet p, Planet[] planets, double dt){

        Vector position = p.position;
        Vector velocity = p.v;
        Vector acceleration = p.acc;
        velocity = velocity.nSum(acceleration.nMult(dt/2));
        position = position.nSum(velocity.nMult(dt));
        acceleration = p.fr(planets).nDiv(p.mass);
        velocity = velocity.nSum(acceleration.nMult(dt/2));

        return new Planet(p.name,p.mass,p.radius,position,velocity,acceleration);
    }

    public static  double ePij(double mi,double mj,double rij){
        double g = 6.693 * Math.pow(10,-11);
        return -g*((mi*mj)/(rij));
    }

    public static double fij(double mi, double mj, double rij, double eij){
        double g = 6.693 * Math.pow(10,-11);
        return g * ((mi*mj)/(Math.pow(rij,2))) * eij;
    }
	
    public String launchedStr(Planet earth , Planet mars , Planet sun) {
    	return earth.toOvito()+"\n" +mars.toOvito()+"\n" +sun.toOvito()+"\n" + "0 0 0 0 0 0" + "\n" +ovitoVlock()+"\n";
    }
    
    public String launchedStr(Planet earth , Planet mars , Planet sun , Planet spaceSheep) {
    	return earth.toOvito()+"\n" +mars.toOvito()+"\n" +sun.toOvito()+"\n" +spaceSheep.toOvito() + "\n" +ovitoVlock()+"\n";
    }

    static Vector spaceShipVelocity(Planet partida, double v0, Planet sun){
        v0 = v0*1000;
        double varY =  sun.position.y - partida.position.y;
        double distancia = sun.position.distance(partida.position);

        double tita = Math.asin(varY/distancia);

        return new Vector(v0*Math.cos(tita)+partida.v.x,v0*Math.sin(tita)+partida.v.y);
    }
    static Vector spaceShipPosition(Planet partida, Planet sun){

        return new Vector((partida.position.x-partida.radius-1500 ),
                (partida.position.y/partida.position.x)*(partida.position.x-1500));

    }
    
}
