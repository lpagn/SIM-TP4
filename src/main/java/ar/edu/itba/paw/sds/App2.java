package ar.edu.itba.paw.sds;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class App2 {
    public static void main(String[] args) throws IOException {

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



        FileWriter fw = new FileWriter("sim.txt");
        GearUtil gearUtilEY, gearUtilEX, gearUtilMX,gearUtilMY,gearUtilSX,gearUtilSY, gearUtilNX, gearUtilNY;



        final Planet[] planetsForSun = new Planet[]{earth,mars};
        final Planet[] planetsForEarth = new Planet[]{sun,mars};
        final Planet[] planetsForMars = new Planet[]{sun,earth};
        final Planet[] planetsForSpaceShip = new Planet[]{sun,earth,mars};

        double step = 5;
        GearForPlanetsX gex=new GearForPlanetsX(step,earth,planetsForEarth),
                gmx=new GearForPlanetsX(step,mars,planetsForMars),
                gsx=new GearForPlanetsX(step,sun,planetsForSun),
                gnx=new GearForPlanetsX(step,spaceSheep,planetsForSpaceShip);

        GearForPlanetsY gey=new GearForPlanetsY(step,earth,planetsForEarth),
                gmy=new GearForPlanetsY(step,mars,planetsForMars),
                gsy=new GearForPlanetsY(step,sun,planetsForSun),
                gny=new GearForPlanetsY(step,spaceSheep,planetsForSpaceShip);



        earth.acc= earth.fr(planetsForEarth).nMult(1/earth.mass);
        mars.acc = mars.fr(planetsForMars).nMult(1/mars.mass);
        spaceSheep.acc = spaceSheep.fr(planetsForSpaceShip).nMult(1/spaceSheep.mass);
        for (int i = 0; i < 100000000; i++) {
            if (i%100000==0){
                fw.write("8\n\n");
                fw.write(earth.toOvito()+"\n" +
                        mars.toOvito()+"\n" +
                        sun.toOvito()+"\n" +
                        spaceSheep.toOvito() + "\n" +
                        ovitoVlock()+"\n");
            }



            /*
            MultipleValueReturn<Double,Double> nextEX = gearUtilEX.next();
            MultipleValueReturn<Double,Double> nextEY = gearUtilEY.next();
            MultipleValueReturn<Double,Double> nextMX = gearUtilMX.next();
            MultipleValueReturn<Double,Double> nextMY = gearUtilMY.next();
            MultipleValueReturn<Double,Double> nextSX = gearUtilSX.next();
            MultipleValueReturn<Double,Double> nextSY = gearUtilSY.next();
            MultipleValueReturn<Double,Double> nextNX = gearUtilNX.next();
            MultipleValueReturn<Double,Double> nextNY = gearUtilNY.next();

            */
            /*
            MultipleValueReturn<Double,Double> nextEX = gex.next();
            MultipleValueReturn<Double,Double> nextEY = gey.next();
            MultipleValueReturn<Double,Double> nextMX = gmx.next();
            MultipleValueReturn<Double,Double> nextMY = gmy.next();
            MultipleValueReturn<Double,Double> nextSX = gsx.next();
            MultipleValueReturn<Double,Double> nextSY = gsy.next();
            MultipleValueReturn<Double,Double> nextNX = gnx.next();
            MultipleValueReturn<Double,Double> nextNY = gny.next();



            earth.setPosition(new Vector(nextEX.a,nextEY.a));
            earth.setV(new Vector(nextEX.b,nextEY.b));
            mars.setPosition(new Vector(nextMX.a,nextMY.a));
            mars.setV(new Vector(nextMX.b,nextMY.b));
            sun.setPosition(new Vector(nextSX.a,nextSY.a));
            sun.setV(new Vector(nextSX.b,nextSY.b));
            spaceSheep.setPosition(new Vector(nextNX.a,nextNY.a));
            */


            Planet auxEarth = integrationVerletPlanet(earth,new Planet[]{sun,mars,spaceSheep},step);
            Planet auxMars = integrationVerletPlanet(mars,new Planet[]{earth,sun,spaceSheep},step);
            Planet auxSpaceSheep = integrationVerletPlanet(spaceSheep,new Planet[]{sun,earth,mars},step);
            earth = auxEarth;
            mars = auxMars;
            spaceSheep = auxSpaceSheep;


            //System.out.println(earth.position.toString());



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










    static Planet integrationVerletPlanet(Planet p, Planet[] planets, double dt){

        Vector position = p.position;
        Vector velocity = p.v;
        Vector acceleration = p.acc;
        velocity = velocity.getAdded(acceleration.nMult(dt/2));
        position = position.getAdded(velocity.nMult(dt));
        acceleration = p.fr(planets).nDiv(p.mass);
        velocity = velocity.getAdded(acceleration.nMult(dt/2));

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



}
