package ar.edu.itba.paw.sds;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class InitialData {
    public static Vector SUN_POSITION = new Vector(0,0);
    public static Vector SUN_VELOCITY = new Vector(0,0);

    public static Vector EARTH_POSITION = new Vector(-1.436232264182898E+08 *1000,-4.222184246295860E+07 *1000);
    public static Vector EARTH_VELOCITY =  new Vector(7.917904169940719E+00 *1000,-2.867871052093815E+01 *1000);


    public static double SPACESHIP_MASS = 2+Math.pow(10,5);//kg
    public static Vector SPACESHIP_POSITION = new Vector((-1.443100400036066E+08*1000-InitialData.EARTH_RADIUS-1500 ),
            (-4.114399753573054E+07/-1.443100400036066E+08)*(-1.443100400036066E+08*1000-InitialData.EARTH_RADIUS-1500));


    public static Vector MARS_POSITION = new Vector(-2.471238977495339E+07 *1000,-2.183737229441134E+08 *1000);
    public static Vector MARS_VELOCITY = new Vector(2.499118636997282E+01 *1000,-6.412328574419259E-01 *1000);


    public static Vector VENUS_POSITION = new Vector(-9.902516511043201E+07 * 1000, 4.149224174387732E+07 * 1000);
    public static Vector VENUS_VEOLOCITY = new Vector(-1.369224128810956E+01 * 1000, -3.246328019868529E+01 *1000);

    //fuente pagina oficial de la nasa
    public static double SUN_MASS = 1.989 * Math.pow(10,30);//kg
    public static double SUN_RADIUS = 3e+07*1000;//m
    public static double SUN_GRAVITY = 274;//m/s²

    public static double EARTH_MASS = 5.97219*Math.pow(10,24);//kg
    public static double EARTH_RADIUS = 2e+07*1000; //m
    public static double EARTH_GRAVITY = 9.807;//m/s²

    public static double MARS_MASS = 6.4171 * Math.pow(10,23); //kg
    public static double MARS_RADIUS = 1e+07*1000;//m
    public static double MARS_GRAVITY = 3.711;//m/s²

    public static double SUN_TO_EARTH = 149.6 * 1000000 *1000;//m

    public static double SUN_TO_MARS = 219.69 *1000000 *1000; //m

    //constante universal gravitacional universal
    public static double g = 6.693 * Math.pow(10,-11);
}
