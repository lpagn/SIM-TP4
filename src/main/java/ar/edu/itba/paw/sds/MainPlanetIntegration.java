package ar.edu.itba.paw.sds;

import java.io.FileWriter;
import java.io.IOException;

public class MainPlanetIntegration {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("Main Planet Integration");
		//fuente wikipedia
        double SUN_MASS = 1.989 * Math.pow(10,30);//kg
        double SUN_RADIUS = 3e+07*1000;//m
        double SUN_GRAVITY = 274;//m/s²

        double EARTH_MASS = 5.97219*Math.pow(10,24);//kg
        double EARTH_RADIUS = 2e+07*1000; //m
        double EARTH_GRAVITY = 9.807;//m/s²

        double SUN_TO_EARTH = 149.6 * 1000000 *1000;//m

        //constante universal gravitacional universal
        double g = 6.693 * Math.pow(10,-11);
        
        double L = SUN_TO_EARTH*3;
        Position SUN_POSITION = new Position(0,0);
        Velocity SUN_VELOCITY = new Velocity(0,0);

        Position EARTH_POSITION = new Position(-1.436232264182898E+08 *1000,-4.222184246295860E+07 *1000);
        Velocity EARTH_VELOCITY =  new Velocity(7.917904169940719E+00 *1000,-2.867871052093815E+01 *1000);
        

        final Planet sun = new Planet("sun",SUN_MASS,SUN_RADIUS,SUN_GRAVITY,SUN_POSITION,SUN_VELOCITY);
        final Planet earth = new Planet("earth",EARTH_MASS,EARTH_RADIUS,EARTH_GRAVITY,EARTH_POSITION,EARTH_VELOCITY);
        
        FileWriter fw = new FileWriter("soltierra.txt");
        
        GearUtil gearUtilEY, gearUtilEX,gearUtilSX,gearUtilSY;



        final Planet[] planetsForSun = new Planet[]{earth};
        final Planet[] planetsForEarth = new Planet[]{sun};
        
        double step = 1;
        
        gearUtilEX = new GearUtil(step, earth.position.x, earth.v.x, new Force() {
            @Override
            public double force(double r, double v) {
                return  earth.frx(planetsForEarth,r).x;
                //return earth.fr(planetsForEarth).x;
            }

            @Override
            public double a(double r, double v) {
                return earth.frx(planetsForEarth,r).x/(earth.mass);
                //return earth.fr(planetsForEarth).y;
            }

            @Override
            public double solution(double t) {
                return 0;
            }
        });

        gearUtilEY = new GearUtil(step, earth.position.y, earth.v.y, new Force() {
            @Override
            public double force(double r, double v) {
                return earth.fry(planetsForEarth,r).y;
                //return earth.fr(planetsForEarth).y;
            }

            @Override
            public double a(double r, double v) {
                return earth.fry(planetsForEarth,r).y/earth.mass;
                //return earth.fr(planetsForEarth).y/earth.mass;
            }

            @Override
            public double solution(double t) {
                return 0;
            }
        });
        
        gearUtilSY = new GearUtil(step, sun.position.y, sun.v.y, new Force() {
            @Override
            public double force(double r, double v) {
                return sun.fry(planetsForSun,r).y;
                //return sun.fr(planetsForSun).y;
            }

            @Override
            public double a(double r, double v) {
                return sun.fry(planetsForSun,r).y/sun.mass;
                //return sun.fr(planetsForSun).y/sun.mass;
            }

            @Override
            public double solution(double t) {
                return 0;
            }
        });
        gearUtilSX = new GearUtil(step, sun.position.x, sun.v.x, new Force() {
            @Override
            public double force(double r, double v) {
                return sun.fry(planetsForSun,r).x;
                //return sun.fry(planetsForSun,r).x;
            }

            @Override
            public double a(double r, double v) {
                return sun.fry(planetsForSun,r).x/sun.mass;
                //return sun.fr(planetsForSun).x/sun.mass;
            }

            @Override
            public double solution(double t) {
                return 0;
            }
        });
        
        for (int i = 0; i < 1000; i++) {


            fw.write("3\n\n");
            fw.write(earth.toOvito()+"\n" + sun.toOvito()+"\n");

            MultipleValueReturn<Double,Double> nextEX = gearUtilEX.next();
            MultipleValueReturn<Double,Double> nextEY = gearUtilEY.next();
            
            MultipleValueReturn<Double,Double> nextSX = gearUtilSX.next();
            MultipleValueReturn<Double,Double> nextSY = gearUtilSY.next();


            //System.out.println("mio "+gex.next()+"luchos "+nextEX.a);

            earth.setPosition(new Position(nextEX.a,nextEY.a));
            earth.setV(new Velocity(nextEX.b,nextEY.b));
            sun.setPosition(new Position(nextSX.a,nextSY.a));
            sun.setV(new Velocity(nextSX.b,nextSY.b));

            /*
            fw.write("3\n\n");
            fw.write(earth.position.toString()+" "+ earth.radius+ " "+earth.fr.x+" "+earth.fr.y+"\n" +
                    mars.position.toString()+ " "+ mars.radius+ " "+mars.fr.x+" "+mars.fr.y+"\n" +
                    sun.position.toString()+" "+sun.radius+ " "+0+" "+0+"\n");
            */

        }
        fw.close();
        
	}

}
