package ar.edu.itba.paw.sds;

public class LeapFrogPlanetsX {

    double step;

    Planet planet;

    Planet[] influence;

    double f(double r){
        return planet.frx(influence,r).x;
    }

    public LeapFrogPlanetsX(double step, Planet planet, Planet[] influence) {
        this.step = step;
        this.planet = planet;
        this.influence = influence;

    }

    double vaux(double r , double vauxlast) {
        return vauxlast + (step/planet.mass)*f(r) ;
    }

    double r(double r , double vaux) {
        return r + vaux*step ;
    }

    double v(double vlastaux , double vaux) {
        return (vlastaux+vaux)/2 ;
    }

    public String stepLeapFrog() {
        StringBuilder ret = new StringBuilder("");
        double dt = 0;
        double rk = planet.position.x;
        double vk = planet.v.x;
        double vauxlast = 0;
        double vaux = 0;
        //TODO: cambiar esto del dt
        while(dt<1000) {
            vaux = vaux(rk,vaux);
            vk = v(vauxlast,vaux);
            rk = r(rk,vk);
            vauxlast = vaux;

            ret.append(String.format("%.4f %.5f\n",dt,rk));

            dt+=step;
        }

        return ret.toString();
    }

}
