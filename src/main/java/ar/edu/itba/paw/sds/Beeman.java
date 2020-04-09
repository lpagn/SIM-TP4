package ar.edu.itba.paw.sds;

public class Beeman extends Integrator {
	
	public Beeman() {
		super();
	}
	
	double r(double r , double v , double flast) {
		return r + v*step + (2/3)*(f(r,v)/m)*step*step - (1/6)*(flast/m)*step*step;
	}
	
	double vp(double r , double v , double flast) {
		return v + (3/2)*(f(r,v)/m)*step - (1/2)*flast*step ;
	}
	
	double v(double r , double v , double flast , double fnext) {
		return v + (1/3)*(fnext/m)*step + (5/6)*f(r,v)*step - (1/6)*(flast/m)*step;
	}
	
	public String stepBeeman() {
		StringBuilder ret = new StringBuilder("");
		double dt = 0;
		double vp = 0;
		double fklast = 0;
		double fknext = 0;
		double rknext = 0;
		double vknext = 0;
		
		double rk = r0;
		double vk = v0;
		
		while(dt<=tf) {
			rknext = r(rk,vk,fklast);
			vp = vp(rk,vp,fklast);
			fknext = f(rknext,vp);
			vknext = v(rk,vk,fklast,fknext);
			
			error = Math.pow((solution(dt) - rk),2);
			ret.append(String.format("%.4f %.5f\n",dt,rk));
			
			dt+=step;
			rk = rknext;
			vk = vknext;
			fklast = f(rk,vk);
		}
		
		return ret.toString();
	}
	
}




