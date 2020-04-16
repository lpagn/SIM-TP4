package ar.edu.itba.paw.sds;

import java.io.FileWriter;
import java.io.IOException;
import java.util.DuplicateFormatFlagsException;

public class App2 {
    public static void main(String[] args) throws IOException {

        //fuente wikipedia
        double SUN_MASS = 1.989 * Math.pow(10,30);//kg
        double SUN_RADIUS = 3e+07*1000;//m
        double SUN_GRAVITY = 274;//m/s²

        double EARTH_MASS = 5.972*Math.pow(10,24);//kg
        double EARTH_RADIUS = 2e+07*1000; //m
        double EARTH_GRAVITY = 9.807;//m/s²

        double MARS_MASS = 6.39 * Math.pow(10,23); //kg
        double MARS_RADIUS = 1e+07*1000;//m
        double MARS_GRAVITY = 3.711;//m/s²

        double SUN_TO_EARTH = 149.6 * 1000000 *1000;//m

        double SUN_TO_MARS = 219.69 *1000000 *1000; //m

        //constante universal gravitacional universal
        double g = 6.693 * Math.pow(10,-11);


        //datos de la pagina de la nasa para el 6 de abril
        //MARS
        //2458945.500000000 = A.D. 2020-Apr-06 00:00:00.0000 TDB
        // X =-2.539920339307203E+07 Y =-2.172958779434001E+08 Z =-3.962679881359786E+06
        // VX= 2.497698760925531E+01 VY=-6.462813052661543E-01 VZ=-6.261703733326136E-01
        // LT= 7.298754446700864E+02 RG= 2.188111535914882E+08 RR=-2.246137840781029E+00
        //2458945.500000000 = A.D. 2020-Apr-06 00:00:00.0000 TDB
        // X =-2.471238977495339E+07 Y =-2.183737229441134E+08 Z =-3.969416598472059E+06
        // VX= 2.499118636997282E+01 VY=-6.412328574419259E-01 VZ=-6.265701850072929E-01
        // LT= 7.331852732434470E+02 RG= 2.198034152350546E+08 RR=-2.161369574531268E+00

        //EARTH
        //2458945.500000000 = A.D. 2020-Apr-06 00:00:00.0000 TDB
        // X =-1.443100400036066E+08 Y =-4.114399753573054E+07 Z = 8.678019419107586E+03
        // VX= 7.903705408731369E+00 VY=-2.868375896918129E+01 VZ= 1.417804269163270E-03
        // LT= 5.005486447654526E+02 RG= 1.500607085628039E+08 RR= 2.637630950536662E-01


        //angulo entre la tierra y marte desde el sol(creo) 40.4743
        //a partir de esto...
        //angulo en radianes 0.7064097974


        double L = SUN_TO_EARTH*3;
        Position SUN_POSITION = new Position(0,0);
        Velocity SUN_VELOCITY = new Velocity(0,0);

        Position EARTH_POSITION = new Position(-1.443100400036066E+08*1000,-4.114399753573054E+07*1000);
        Velocity EARTH_VELOCITY =  new Velocity(7.903705408731369E+00*1000,-2.868375896918129E+01*1000);

        Position SPACESHIP_POSITION = new Position((-1.443100400036066E+08*1000-EARTH_RADIUS-1500 ),
                (-4.114399753573054E+07/-1.443100400036066E+08)*(-1.443100400036066E+08*1000-EARTH_RADIUS-1500));


        Position MARS_POSITION = new Position(-2.539920339307203E+07 *1000,-2.172958779434001E+08 *1000);
        Velocity MARS_VELOCITY = new Velocity(2.497698760925531E+01 *1000,-6.462813052661543E-01 *1000);

        final Planet sun = new Planet("sun",SUN_MASS,SUN_RADIUS,SUN_GRAVITY,SUN_POSITION,SUN_VELOCITY);
        final Planet earth = new Planet("earth",EARTH_MASS,EARTH_RADIUS,EARTH_GRAVITY,EARTH_POSITION,EARTH_VELOCITY);
        final Planet mars = new Planet("mars",MARS_MASS,MARS_RADIUS,MARS_GRAVITY,MARS_POSITION,MARS_VELOCITY);


        final Planet spaceSheep = new Planet("spaceShip",1,MARS_RADIUS,0,SPACESHIP_POSITION,EARTH_VELOCITY);



        FileWriter fw = new FileWriter("sim.txt");
        GearUtil gearUtilEY, gearUtilEX, gearUtilMX,gearUtilMY,gearUtilSX,gearUtilSY, gearUtilNX, gearUtilNY;
        final Planet[] planetsForSun = new Planet[]{earth,mars};
        final Planet[] planetsForEarth = new Planet[]{sun,mars};
        final Planet[] planetsForMars = new Planet[]{earth,sun};
        final Planet[] planetsForSpaceShip = new Planet[]{earth,sun,mars};

        double step = 0.00001;
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

        gearUtilMX = new GearUtil(step, mars.position.x, mars.v.x, new Force() {
            @Override
            public double force(double r, double v) {
                return mars.frx(planetsForMars,r).x;
                //return mars.fr(planetsForMars).x;
            }

            @Override
            public double a(double r, double v) {
                return mars.frx(planetsForMars,r).x/mars.mass;
                //return mars.fr(planetsForMars).x/mars.mass;
            }

            @Override
            public double solution(double t) {
                return 0;
            }
        });

        gearUtilMY = new GearUtil(step, mars.position.y, mars.v.y, new Force() {
            @Override
            public double force(double r, double v) {
                return mars.fry(planetsForMars,r).y;
                //return mars.fr(planetsForMars).y;
            }

            @Override
            public double a(double r, double v) {
                return mars.fry(planetsForMars,r).y/mars.mass;
                //return mars.fr(planetsForMars).y;
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


        gearUtilNX = new GearUtil(step, spaceSheep.position.x, spaceSheep.v.x, new Force() {
            @Override
            public double force(double r, double v) {
                return spaceSheep.frx(planetsForMars,r).x;
                //return mars.fr(planetsForMars).x;
            }

            @Override
            public double a(double r, double v) {
                return spaceSheep.frx(planetsForSpaceShip,r).x/spaceSheep.mass;
                //return mars.fr(planetsForMars).x/mars.mass;
            }

            @Override
            public double solution(double t) {
                return 0;
            }
        });

        gearUtilNY = new GearUtil(step, spaceSheep.position.y, spaceSheep.v.y, new Force() {
            @Override
            public double force(double r, double v) {
                return spaceSheep.fry(planetsForSpaceShip,r).y;
                //return mars.fr(planetsForMars).y;
            }

            @Override
            public double a(double r, double v) {
                return spaceSheep.fry(planetsForSpaceShip,r).y/spaceSheep.mass;
                //return mars.fr(planetsForMars).y;
            }

            @Override
            public double solution(double t) {
                return 0;
            }
        });
        
        for (int i = 0; i < 1000; i++) {


            fw.write("8\n\n");
            fw.write(earth.toOvito()+"\n" +
                    mars.toOvito()+"\n" +
                    sun.toOvito()+"\n" +
                    spaceSheep.toOvito() + "\n" +
                    ovitoVlock()+"\n");

            MultipleValueReturn<Double,Double> nextEX = gearUtilEX.next();
            MultipleValueReturn<Double,Double> nextEY = gearUtilEY.next();
            MultipleValueReturn<Double,Double> nextMX = gearUtilMX.next();
            MultipleValueReturn<Double,Double> nextMY = gearUtilMY.next();
            MultipleValueReturn<Double,Double> nextSX = gearUtilSX.next();
            MultipleValueReturn<Double,Double> nextSY = gearUtilSY.next();
            MultipleValueReturn<Double,Double> nextNX = gearUtilNX.next();
            MultipleValueReturn<Double,Double> nextNY = gearUtilNY.next();

            earth.setPosition(new Position(nextEX.a,nextEY.a));
            earth.setV(new Velocity(nextEX.b,nextEY.b));
            mars.setPosition(new Position(nextMX.a,nextMY.a));
            mars.setV(new Velocity(nextMX.b,nextMY.b));
            sun.setPosition(new Position(nextSX.a,nextSY.a));
            sun.setV(new Velocity(nextSX.b,nextSY.b));
            spaceSheep.setPosition(new Position(nextNX.a,nextNY.a));

            /*
            fw.write("3\n\n");
            fw.write(earth.position.toString()+" "+ earth.radius+ " "+earth.fr.x+" "+earth.fr.y+"\n" +
                    mars.position.toString()+ " "+ mars.radius+ " "+mars.fr.x+" "+mars.fr.y+"\n" +
                    sun.position.toString()+" "+sun.radius+ " "+0+" "+0+"\n");
            */

        }
        fw.close();


        Vector frSol = sun.fr(new Planet[]{earth,mars});
        Vector frMars = mars.fr(new Planet[]{sun,earth});
        Vector frEarth = earth.fr(new Planet[]{sun,mars});
        System.out.println("fuerza resultante sol "+ frSol.x +" "+ frSol.y);
        System.out.println("fuerza resultante marte "+ frMars.x +" "+ frMars.y);
        System.out.println("fuerza resultante tierra "+ frEarth.x +" "+ frEarth.y);



    }

    public static String ovitoVlock(){
        double sides = 219.69 *1000000 * 1.5 *1000; //m
        return (sides)+" "+(sides)+" "+"0 0 0 0"+"\n"+
                (sides)+" "+(-sides)+" "+"0 0 0 0"+"\n"+
                (-sides)+" "+(sides)+" "+"0 0 0 0"+"\n"+
                (-sides)+" "+(-sides)+" "+"0 0 0 0";
    }


    public static  double ePij(double mi,double mj,double rij){
        double g = 6.693 * Math.pow(10,-11);
        return -g*((mi*mj)/(rij));
    }

    public static double fij(double mi, double mj, double rij, double eij){
        double g = 6.693 * Math.pow(10,-11);
        return g * ((mi*mj)/(Math.pow(rij,2))) * eij;
    }
}
