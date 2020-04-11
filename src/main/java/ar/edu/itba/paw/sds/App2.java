package ar.edu.itba.paw.sds;

import javax.print.DocFlavor;
import java.awt.peer.SystemTrayPeer;

public class App2 {
    public static void main(String[] args){
        //fuente wikipedia
        double SUN_MASS = 1.989 * Math.pow(10,30);//kg
        double SUN_RADIO = 696.340;//km
        double SUN_TO_EARTH = 149.6 * 1000000;//km
        double SUN_TO_MARS = 220.06 *1000000; //km

        //datos de la pagina de la nasa para el 6 de abril
        //2020-Apr-06 00:00     20 26 26.34 -20 17 27.0   0.73   4.42 1.42085243473863 -13.6722273  72.3643 /L  40.4743
        //angulo entre la tierra y marte desde el sol(creo) 40.4743
        //a partir de esto...
        double L = SUN_TO_EARTH*3;
        Position SUN_POSITION = new Position(L/2,L/2);
        Position EARTH_POSITION = new Position(L/2+SUN_TO_EARTH,L/2);
        Position MARS_POSITION = new Position(L/2+Math.cos(40.4743)*SUN_TO_MARS,L/2+Math.sin(40.4743)*SUN_TO_MARS);

        System.out.println("7\ncoment\n" +
                MARS_POSITION+"\n" +
                EARTH_POSITION+"\n" +
                SUN_POSITION+ "\n" +
                "0 0\n0 "+L+"\n"+L+" 0\n"+L+" "+L+"\n" );

    }
}
