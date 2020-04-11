package ar.edu.itba.paw.sds;

import com.sun.javafx.geom.transform.BaseTransform;

import javax.print.DocFlavor;
import java.awt.peer.SystemTrayPeer;

public class App2 {
    public static void main(String[] args){

        //fuente wikipedia
        double SUN_MASS = 1.989 * Math.pow(10,30);//kg
        double SUN_RADIUS = 696.340;//km

        double EARTH_MASS = 5.972*Math.pow(10,24);//kg
        double EARTH_RADIUS = 6.371; //km

        double MARS_MASS = 6.39 * Math.pow(10,23); //kg
        double MARS_RADIUS = 3389.5;//km

        double SUN_TO_EARTH = 149.6 * 1000000;//km
        double SUN_TO_MARS = 220.06 *1000000; //km

        //datos de la pagina de la nasa para el 6 de abril
        //2020-Apr-06 00:00     20 26 26.34 -20 17 27.0   0.73   4.42 1.42085243473863 -13.6722273  72.3643 /L  40.4743
        //angulo entre la tierra y marte desde el sol(creo) 40.4743
        //a partir de esto...
        //angulo en radianes 0.7064097974

        double L = SUN_TO_EARTH*3;
        Position SUN_POSITION = new Position(L/2,L/2);
        Position EARTH_POSITION = new Position(L/2+SUN_TO_EARTH,L/2);

        Position MARS_POSITION = new Position(L/2+Math.cos(0.7064097974)*SUN_TO_MARS,L/2+Math.sin(0.7064097974)*SUN_TO_MARS);

        Planet sun = new Planet("sun",SUN_MASS,SUN_RADIUS,SUN_POSITION,new Velocity(0,0));
        Planet earth = new Planet("earth",EARTH_MASS,EARTH_RADIUS,EARTH_POSITION,new Velocity(0,0));
        Planet mars = new Planet("mars",MARS_MASS,MARS_RADIUS,MARS_POSITION,new Velocity(0,0));


        System.out.println("7\ncoment\n" +
                MARS_POSITION+"\n" +
                EARTH_POSITION+"\n" +
                SUN_POSITION+ "\n" +
                "0 0\n0 "+L/1E7+"\n"+L/1E7+" 0\n"+L/1E7+" "+L/1E7+"\n" );

    }
}
