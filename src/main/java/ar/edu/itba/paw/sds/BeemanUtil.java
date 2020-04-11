package ar.edu.itba.paw.sds;

public class BeemanUtil extends Integrator {
	public double dt = 0;
	public double step;
    public double rk;
    public double vk;
    
    private double ak;
    private double aklast;
    private double aknext;
    private boolean initial;    
    
    public BeemanUtil(double step, double v0, double r0) {
        vk=v0;
        rk=r0;
        ak= a(r0,v0);
        this.step= step;
        initial= true;
    }
    
    private double r() {
        return rk + vk*step + (2*ak/3-aklast/6)*Math.pow(step,2);
    }
    
    private double vp() {
        return vk + (3*ak/2-aklast/2)*step;
    }
    
    private double v() {
        return vk + (aknext/3+5*ak/6-aklast/6)*step;
    }
    
    public MultipleValueReturn<Double,Double> next() {
        if(initial) {
            initial = false;
            double position = rk + step * vk + Math.pow(step,2) * ak * 0.5;
            double velocity = vk + step * ak;
            aknext = a(position, velocity);
            rk = position;
            vk = velocity;
        }
        else {
            aklast = ak;
            ak = aknext;
            rk = r();
            double vp = vp();
            aknext = a(rk,vp);
            vk = v();
        }
        dt += step; 
        return new MultipleValueReturn<>(rk,vk);
    }
}
