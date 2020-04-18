package ar.edu.itba.paw.sds;

import java.io.FileWriter;
import java.io.IOException;

public class App2 {
    public static void main(String[] args) throws IOException {

        double L = InitialData.SUN_TO_EARTH*3;


        Planet sun = new Planet("sun",InitialData.SUN_MASS,InitialData.SUN_RADIUS,InitialData.SUN_GRAVITY,InitialData.SUN_POSITION,InitialData.SUN_VELOCITY);
        Planet earth = new Planet("earth",InitialData.EARTH_MASS,InitialData.EARTH_RADIUS,InitialData.EARTH_GRAVITY,InitialData.EARTH_POSITION,InitialData.EARTH_VELOCITY);
        Planet mars = new Planet("mars",InitialData.MARS_MASS,InitialData.MARS_RADIUS,InitialData.MARS_GRAVITY,InitialData.MARS_POSITION,InitialData.MARS_VELOCITY);


        Planet spaceSheep = new Planet("spaceShip",1,InitialData.MARS_RADIUS,InitialData.SPACESHIP_MASS,spaceShipPosition(earth,sun),spaceShipVelocity(earth,13,sun));



        FileWriter fw = new FileWriter("sim.txt");



        final Planet[] planetsForSun = new Planet[]{earth,mars};
        final Planet[] planetsForEarth = new Planet[]{sun,mars};
        final Planet[] planetsForMars = new Planet[]{sun,earth};
        final Planet[] planetsForSpaceShip = new Planet[]{sun,earth,mars};

        double step = 5;

        earth.acc= earth.fr(planetsForEarth).nMult(1/earth.mass);
        mars.acc = mars.fr(planetsForMars).nMult(1/mars.mass);
        spaceSheep.acc = spaceSheep.fr(planetsForSpaceShip).nMult(1/spaceSheep.mass);

        for (int i = 0,dia=0; i < 86400* 360 *2; i+=step) {
            if (i%86400==0){
                dia ++;
                if(dia >=70){
                    if(dia == 70) {
                        spaceSheep = new Planet("spaceShip", InitialData.SPACESHIP_MASS, InitialData.MARS_RADIUS, 0, spaceShipPosition(earth, sun), spaceShipVelocity(earth, 8, sun));
                    }
                    fw.write("8\n\n");
                    fw.write(earth.toOvito()+"\n" +
                            mars.toOvito()+"\n" +
                            sun.toOvito()+"\n" +
                            spaceSheep.toOvito() + "\n" +
                            ovitoVlock()+"\n");
                }else{
                    fw.write("7\n\n");
                    fw.write(earth.toOvito()+"\n" +
                            mars.toOvito()+"\n" +
                            sun.toOvito()+"\n" +
                            //spaceSheep.toOvito() + "\n" +
                            ovitoVlock()+"\n");
                }
            }


            Planet auxEarth = VerletIntegrationPlanet.integrate(earth,new Planet[]{sun,mars,spaceSheep},step);
            Planet auxMars = VerletIntegrationPlanet.integrate(mars,new Planet[]{earth,sun,spaceSheep},step);
            Planet auxSpaceSheep = VerletIntegrationPlanet.integrate(spaceSheep,new Planet[]{sun,earth,mars},step);
            earth = auxEarth;
            mars = auxMars;
            spaceSheep = auxSpaceSheep;


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
        return "-1 "+(sides)+" "+(sides)+" "+"0 0 0 0"+"\n"+
                "-2 "+(sides)+" "+(-sides)+" "+"0 0 0 0"+"\n"+
                "-3 "+(-sides)+" "+(sides)+" "+"0 0 0 0"+"\n"+
                "-4 "+(-sides)+" "+(-sides)+" "+"0 0 0 0";
    }



    static Vector spaceShipVelocity(Planet partida, double v0, Planet sun){
        v0 = v0*1000;
        double varY =  sun.position.y - partida.position.y;
        double distancia = sun.position.distance(partida.position);

        double tita = Math.asin(varY/distancia);

        return new Vector(v0*Math.cos(tita)+partida.v.x,v0*Math.sin(tita)+partida.v.y);
    }
    static Vector spaceShipPosition(Planet partida, Planet sun){

        Vector vp = partida.position.nDiv(partida.position.module());
        double mod = vp.module();
        double modp = partida.position.module();
         Vector v = vp.nMult(partida.position.module()+1500);
        return vp.nMult(partida.position.module()+1500);

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
