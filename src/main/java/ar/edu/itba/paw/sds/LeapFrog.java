package ar.edu.itba.paw.sds;

public class LeapFrog extends Integrator{
	public LeapFrog() {
		super();
	}
	
	double vaux(double r , double vauxlast , double v) {
		return vauxlast + (step/m)*f(r,v) ;
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
		double rk = r0;
		double vk = v0;
		double vauxlast = 0;
		double vaux = 0;
		while(dt<tf) {
			vaux = vaux(rk,vaux,vk);
			vk = v(vauxlast,vaux);
			rk = r(rk,vk);
			vauxlast = vaux;
			
			ret.append(String.format("%.4f %.5f\n",dt,rk));
			
			dt+=step;
		}
		
		return ret.toString();
	}
	
}

