package ar.edu.itba.paw.sds;

public class AmortigForce implements Force {
	
	//parameters
	double m = 70; //[kg]
	double k = 100000;//[N/m]
	double gamma = 100;//[kg/s]
	double A = 1;
	//variables
	public double r0 = 1; //[m]
	public double v0 = -1*A*gamma/(2*m);//[m/s]
	
	AmortigForce(){
		
	}
	
	AmortigForce(double m, double k,double gamma,double A,double r0){
		this.m = m;
		this.k = k;
		this.gamma = gamma;
		this.A = A;
		this.r0 = r0;
	}
	
	@Override
	public double force(double r, double v) {
		return -1*k*r - gamma*v;
	}

	@Override
	public double solution(double t) {
		return A*Math.exp(-1*(gamma/(2*m))*t)*Math.cos((Math.sqrt((k/m)-(gamma*gamma/(4*m*m))))*t);
	}

	@Override
	public double a(double r , double v) {
		return force(r,v)/m;
	}

}
