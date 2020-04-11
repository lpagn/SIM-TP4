package ar.edu.itba.paw.sds;

public class Beeman extends Integrator {

	public Beeman() {
		super();
	}
	
	public String stepBeeman() {
		StringBuilder ret = new StringBuilder("");
		BeemanUtil b = new BeemanUtil(step,r0,v0);
		while(b.dt < tf) {
			b.next();
			ret.append(String.format("%.4f %.5f\n",b.dt,b.rk));
		}
		return ret.toString();
	}
	
}



