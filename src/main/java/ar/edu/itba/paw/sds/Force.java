package ar.edu.itba.paw.sds;

public interface Force {
	//public double r0 = 0;
	//public double v0 = 0;
	//public double m = 0;
	double force(double r , double v);
	double a(double r , double v);
	double solution(double t);
}
