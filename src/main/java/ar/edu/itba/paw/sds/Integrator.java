package ar.edu.itba.paw.sds;

public class Integrator {
	
	double tf = 5;//[s]
	
	double step = 0.00001;//[s]
	
	Force force = new AmortigForce();
	
	double r0;
	double v0;
	double m;
	
	public double getStep() {
		return step;
	}

	public void setStep(double step) {
		this.step = step;
	}

	double error = 0;
	
	public Integrator(Force f) {
		this.force = f;
	}
	
	public double solution(double t) {
		return force.solution(t);
	}
	
	public String stepSolution() {
		StringBuilder ret = new StringBuilder("");
		double dt = 0;
		while(dt<=tf) {
			solution(dt);
			ret.append(String.format("%.4f %.5f\n",dt,solution(dt)));
			dt+=step;
		}
		return ret.toString();
	}
	
	public double f(double r , double v) {
		return force.force(r,v);
	}
	
	public double a(double r , double v) {
		return force.a(r,v);
	}
	
}


