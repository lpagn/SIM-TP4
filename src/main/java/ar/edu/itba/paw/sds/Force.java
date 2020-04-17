package ar.edu.itba.paw.sds;

public interface Force {
	double force(double r , double v);
	double a(double r , double v);
	double solution(double t);
}
