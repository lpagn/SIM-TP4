package ar.edu.itba.paw.sds;

public class Euler extends Integrator {
	public Euler() {
		super();
	}
	
	double r(double r , double v) {
		return r + v*step + (f(r,v)/(2*m))*step*step;
	}
	
	double v(double r , double v) {
		return v + f(r,v)*(step/m);
	}
	
	public String stepEuler() {
		StringBuilder ret = new StringBuilder("");
		double dt = 0;
		double rk = r0;
		double vk = v0;
		while(dt<=tf) {
			double rknext = r(rk,vk);
			double vknext = v(rk,vk);

			ret.append(String.format("%.4f %.5f\n",dt,rk));
			
			dt+=step;
			rk = rknext;
			vk = vknext;
		}
		
		return ret.toString();
	}
}
