package ar.edu.itba.paw.sds;

import java.io.FileWriter;
import java.io.IOException;

public class App2 {
    public static void main(String[] args) throws IOException {



        double pastDistance= 0;
        boolean printed = false;
        int diaDespegue=150;
        double L = InitialData.SUN_TO_EARTH*3;
        FileWriter fwt = new FileWriter("diaVsT.txt");
        FileWriter fwd = new FileWriter("diaVsD.txt");

        for (int k = 100; k <200; k++) {
            diaDespegue = k;


            //for (int j = 0; j < 100; j++) {


                Planet sun = new Planet("sun", InitialData.SUN_MASS, InitialData.SUN_RADIUS, InitialData.SUN_GRAVITY, InitialData.SUN_POSITION, InitialData.SUN_VELOCITY);
                Planet earth = new Planet("earth", InitialData.EARTH_MASS, InitialData.EARTH_RADIUS, InitialData.EARTH_GRAVITY, InitialData.EARTH_POSITION, InitialData.EARTH_VELOCITY);
                Planet mars = new Planet("mars", InitialData.MARS_MASS, InitialData.MARS_RADIUS, InitialData.MARS_GRAVITY, InitialData.MARS_POSITION, InitialData.MARS_VELOCITY);


                Planet spaceSheep = new Planet("spaceShip", 1, InitialData.MARS_RADIUS, InitialData.SPACESHIP_MASS, spaceShipPosition(earth, sun), spaceShipVelocity(earth, 8, sun));


//                FileWriter fw = new FileWriter("simV0=" + (5 + j) + ".txt");
                FileWriter fw = new FileWriter("simDespegue=" + diaDespegue + ".txt");

                final Planet[] planetsForSun = new Planet[]{earth, mars};
                final Planet[] planetsForEarth = new Planet[]{sun, mars};
                final Planet[] planetsForMars = new Planet[]{sun, earth};
                final Planet[] planetsForSpaceShip = new Planet[]{sun, earth, mars};

                double step = 5;

                earth.acc = earth.fr(planetsForEarth).nMult(1 / earth.mass);
                mars.acc = mars.fr(planetsForMars).nMult(1 / mars.mass);
                spaceSheep.acc = spaceSheep.fr(planetsForSpaceShip).nMult(1 / spaceSheep.mass);

                printed = false;
                double md = Double.MAX_VALUE;
                int mdia=0;
                boolean reached = false;
                boolean st = false;
                int start = 0;
                double distance;
                double min = Double.MAX_VALUE;
                double[][] data = new double[2][2];
                for (int i = 0, dia = 0; i < 86400 * 360 * 2; i += step) {
                    if (i % 86400 == 0) {
                        dia++;
                        if (dia >= diaDespegue) {
                            if (dia == diaDespegue) {

                                spaceSheep = new Planet("spaceShip", InitialData.SPACESHIP_MASS, InitialData.MARS_RADIUS, 0, spaceShipPosition(earth, sun), spaceShipVelocity(earth, 8, sun));
                            }
                            /*fw.write("8\n\n");
                            fw.write(earth.toOvito() + "\n" +
                                    mars.toOvito() + "\n" +
                                    sun.toOvito() + "\n" +
                                    spaceSheep.toOvito() + "\n" +
                                    ovitoVlock() + "\n");*/
                        } else {
                            /*fw.write("7\n\n");
                            fw.write(earth.toOvito() + "\n" +
                                    mars.toOvito() + "\n" +
                                    sun.toOvito() + "\n" +
                                    //spaceSheep.toOvito() + "\n" +
                                    ovitoVlock() + "\n");*/
                        }
                    }


                    Planet auxEarth = VerletIntegrationPlanet.integrate(earth, new Planet[]{sun, mars, spaceSheep}, step);
                    Planet auxMars = VerletIntegrationPlanet.integrate(mars, new Planet[]{earth, sun, spaceSheep}, step);

                    earth = auxEarth;
                    mars = auxMars;

                    //dia >=diaDespegue

                    if (dia > diaDespegue) {

                        if (!st) {
                            start = i;
                        }
                        st = true;


                        Planet auxSpaceSheep = VerletIntegrationPlanet.integrate(spaceSheep, new Planet[]{sun, earth, mars}, step);
                        spaceSheep = auxSpaceSheep;

                        if (spaceSheep.passOrbit(mars, sun) && !reached) {
                            reached = true;
                            //fwt.write("" + (5 + j) + " " + (i - start) + "\n");
                            fwt.write("" + diaDespegue + " " + (i - start) + "\n");
                            System.out.println("velocidad de la nave = " + spaceSheep.v.toString());
                            System.out.println("cantidad de segundo pasados = " + (i - start));
                        }

                        if(spaceSheep.position.distance(mars.position) < md){
                            md = spaceSheep.position.distance(mars.position);
                            mdia = dia;

                            //System.out.println("min distance = "+spaceSheep.position.distance(mars.position));
                        }


                        distance = Math.sqrt(Math.pow(spaceSheep.position.x - mars.position.x, 2) + Math.pow(spaceSheep.position.y - mars.position.y, 2));
                        if (distance < min) {
                            min = distance;
                            data[0][0] = spaceSheep.v.x;
                            data[0][1] = spaceSheep.v.y;
                            data[1][0] = i - start;
                            data[1][1] = distance;
                        }


                    }


                }

                fw.close();
                fwd.write("" + mdia +" "+ md+"\n");
                System.out.println("dia despegue ="+diaDespegue+" dias de vuelo " + mdia +" distancia minima="+md);

            //}


        }
        fwt.close();
        fwd.close();



    }






    public static String ovitoVlock(){
        double sides = 219.69 *1000000 * 1.5 *1000; //m
        return "-1 "+(sides)+" "+(sides)+" "+"0 0 0 0"+"\n"+
                "-2 "+(sides)+" "+(-sides)+" "+"0 0 0 0"+"\n"+
                "-3 "+(-sides)+" "+(sides)+" "+"0 0 0 0"+"\n"+
                "-4 "+(-sides)+" "+(-sides)+" "+"0 0 0 0";
    }



    static Vector spaceShipVelocity(Planet partida, double v0, Planet sun){

        Vector departureV= new Vector(partida.v.x,partida.v.y);

        v0 = v0*1000;
        double varY =  sun.position.y - partida.position.y;
        double distancia = sun.position.distance(partida.position);

        double tita = Math.asin(varY/distancia);

        return new Vector(v0*Math.cos(tita)+partida.v.x,v0*Math.sin(tita)+partida.v.y);
    }
    static Vector spaceShipPosition(Planet partida, Planet sun){
        Vector planetPosition = new Vector(partida.position.x,partida.position.y);
        Vector versor = planetPosition.nDiv(planetPosition.module());
        double module = planetPosition.module();
        return versor.nMult(module+1500+partida.radius);

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
