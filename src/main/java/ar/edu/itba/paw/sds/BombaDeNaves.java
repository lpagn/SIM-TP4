package ar.edu.itba.paw.sds;

import java.util.LinkedList;
import java.util.List;

public class BombaDeNaves {

    public static void main(String[] args){

        Planet earth = new Planet("earth",
                InitialData.EARTH_MASS,
                InitialData.EARTH_RADIUS,
                InitialData.EARTH_GRAVITY,
                InitialData.EARTH_POSITION,
                InitialData.EARTH_VELOCITY);

        Planet mars = new Planet("mars",
                InitialData.MARS_MASS,
                InitialData.MARS_RADIUS,
                InitialData.MARS_GRAVITY,
                InitialData.MARS_POSITION,
                InitialData.MARS_VELOCITY);

        Planet sun = new Planet("sun",
                InitialData.SUN_MASS,
                InitialData.SUN_RADIUS,
                InitialData.SUN_GRAVITY,
                InitialData.SUN_POSITION,
                InitialData.SUN_VELOCITY);

        Planet spaceship = new Planet("spaceship",
                InitialData.SPACESHIP_MASS,
                InitialData.MARS_RADIUS,
                InitialData.MARS_GRAVITY,
                InitialData.SPACESHIP_POSITION,
                new Vector(0,0));


        List<Planet> spaceships = new LinkedList<>();

        //second per day 86400
        int time = 0;
        double step = 5;
        while(time <= 86400 * 360 +2){




            //move planets
            //Planet auxEarth = VerletIntegrationPlanet.integrate(earth,new Planet[]{sun, earth, mars})



            time += step;
        }
    }

}
