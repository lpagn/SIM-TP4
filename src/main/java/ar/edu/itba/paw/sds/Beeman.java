package ar.edu.itba.paw.sds;

public class Beeman extends Integrator {
	
	AmortigForce f = new AmortigForce();
	
	public Beeman(AmortigForce f) {
		super(f);
		this.f = f;
	}
	
	public String stepBeeman() {
		StringBuilder ret = new StringBuilder("");
		BeemanUtil b = new BeemanUtil(step,f.r0,f.v0,f);
		while(b.dt < tf) {
			b.next();
			ret.append(String.format("%.4f %.5f\n",b.dt,b.rk));
		}
		return ret.toString();
	}
	
}



