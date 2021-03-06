package ar.edu.itba.paw.sds;

public class LeapFrogPlanetsY {
	double step;

    Planet planet;

    Planet[] influence;
    
    double dt;
    double rk;
    double vk;
    double vauxlast;
    double vaux;
    
    double f(double r){
        return planet.fr(influence).y;
    }

    public LeapFrogPlanetsY(double step, Planet planet, Planet[] influence) {
        this.step = step;
        this.planet = planet;
        this.influence = influence;
        this.dt = 0;
        this.rk = planet.position.y;
        this.vk = planet.v.y;
        this.vauxlast = 0;
        this.vaux = vaux(rk,vk);
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
    
    public MultipleValueReturn<Double,Double> stepLeapFrog() {
        vaux = vaux(rk,vaux);
        vk = v(vauxlast,vaux);
        rk = r(rk,vk);
        vauxlast = vaux;
        dt+=step;
        return new MultipleValueReturn<>(rk,vk);
    }
}
