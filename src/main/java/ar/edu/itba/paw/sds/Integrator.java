package ar.edu.itba.paw.sds;

public class Integrator {
	//parameters
	double m = 70; //[kg]
	double k = 100000;//[N/m]
	double gamma = 100;//[kg/s]
	double tf = 5;//[s]
	double A = 1;
	//variables
	double r0 = 1; //[m]
	double v0 = -1*A*gamma/(2*m);//[m/s]
	double step = 0.0000001;//[s]
	
	public double getStep() {
		return step;
	}

	public void setStep(double step) {
		this.step = step;
	}

	double error = 0;
	
	public Integrator() {}
		
	public double solution(double t) {
		return A*Math.exp(-1*(gamma/(2*m))*t)*Math.cos((Math.sqrt((k/m)-(gamma*gamma/(4*m*m))))*t);
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
		return -1*k*r - gamma*v;
	}
	
	public double a(double r , double v) {
		return f(r,v) / m;
	}
	
}


