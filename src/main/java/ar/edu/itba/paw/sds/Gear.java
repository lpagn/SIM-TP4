package ar.edu.itba.paw.sds;

public class Gear extends Integrator {
	
	AmortigForce f;
	
	public Gear(AmortigForce f) {
		super(f);
		this.f = f;
	}
	
	//A partir del step = 0.00001 comienza a dar resultados razonables
	
	//double step = 0.00001;
	
	double alfa0 = 3/16;
	double alfa1 = 251/360;
	double alfa2 = 1;
	double alfa3 = 11/18;
	double alfa4 = 1/6;
	double alfa5 = 1/60;
	
	double[] alfalist = {alfa0,alfa1,alfa2,alfa3,alfa4,alfa5}; // estos double no se estan tomando bien
	
	double r = r0;
	double r1 = v0;
	double r2 = 0;
	double r3 = 0;
	double r4 = 0;
	double r5 = 0;
		
	double [] rlist = {r,r1,r2,r3,r4,r5};
	
	double rp5 = 0;
	double rp4 = 0;
	double rp3 = 0;
	double rp2 = 0;
	double rp1 = 0;
	double rp  = 0;
	
	double [] rplist = {rp,rp1,rp2,rp3,rp4,rp5};
	
	double fk;
	double ak;
	double da;
	double dR2;
	
	double rp5c;
	double rp4c;
	double rp3c;
	double rp2c;
	double rp1c;
	double rpc ; 
	
	public String stepGear () {
		StringBuilder ret = new StringBuilder(" ");
		GearUtil g = new GearUtil(step,f.r0,f.v0,f);
		while(g.dt<tf) {
			g.next();
			ret.append(String.format("%.4f %.5f\n",g.dt,g.r.get(0)));
		}
		
		/*
		r = r0;
		r1 = v0;
		r2 = -1*(k/m)*(r-r0);
		r3 = -1*(k/m)*r1;
		r4 = Math.pow((k/m),2)*(r-r0);
		r5 = Math.pow((k/m),2)*r1;
		
		while(dt<tf) {
				
			rlist[0] = r;
			rlist[1] = r1;
			rlist[2] = r2;
			rlist[3] = r3;
			rlist[4] = r4;
			rlist[5] = r5;
			
			rp5 = rpi(5,5);
			rp4 = rpi(4,5);
			rp3 = rpi(3,5);
			rp2 = rpi(2,5);
			rp1 = rpi(1,5);
			rp  = rpi(0,5);
			
			rplist[0] = rp;
			rplist[1] = rp1;
			rplist[2] = rp2;
			rplist[3] = rp3;
			rplist[4] = rp4;
			rplist[5] = rp5;
			
			fk = f(rp,rp1);
			ak = fk/m;
			da = ak - rp2;
			dR2 = (da*step*step)/2;
			
			rp5c = correct(5);
			rp4c = correct(4);
			rp3c = correct(3);
			rp2c = correct(2);
			rp1c = correct(1);
			rpc  = correct(0);
						
			ret.append(String.format("%.4f %.5f\n",dt,r));
			
			r  = rpc;
			r1 = rp1c;
			r2 = rp2c;
			r3 = rp3c;
			r4 = rp4c;
			r5 = rp5c;
			
			dt+=step;
			
		}
		*/
		return ret.toString();
		
	}
	
	double rpi(int from , int to) {
		double ret = 0;
		//StringBuilder str = new StringBuilder("");
		int i = from;
		int j = 0;
		while(i <= to) {
			ret += rlist[i]*(Math.pow(step,j)/factorial(j)) ;
			//str.append(String.format("r%d*(t^%d/%d!) + ",i,j,j));
			i++;
			j++;
		}
		//System.out.println(str);
		return ret;
	}
	
	private double correct(int q) {
		return rplist[q] + alfalist[q]*dR2*(factorial(q)/Math.pow(step,q));
	}

	private int factorial(int n) {
		if(n==1||n==0) {
			return 1;
		}
		else {
			return n*factorial(n-1);
		}
	}
	
	
}

