package ar.edu.itba.paw.sds;

import java.util.ArrayList;

public class GearForPlanetsY {
    private static final int size = 6;

    private double step;
    public double dt = 0;
    public ArrayList<Double> r;
    Planet planet;
    Planet[] influence;

    public double a(double y, double noSirve) {
        return planet.fr(influence).y / planet.mass;
    }


    private static double[] alfalist = {3.0 / 16, 251.0 / 360, 1, 11.0 / 18, 1.0 / 6, 1.0 / 60};

    public GearForPlanetsY(double step, Planet planet, Planet[] influence) {
        this.step = step;
        this.planet = planet;
        this.influence = influence;
        r = new ArrayList<>();
        r.add(planet.position.y);
        r.add(planet.v.y);
        r.add(a(r.get(0), r.get(1)));
        r.add(a(r.get(1), r.get(2)));
        r.add(a(r.get(2), r.get(3)));
        r.add(a(r.get(3), r.get(4)));

    }

    public MultipleValueReturn<Double, Double> next() {
        ArrayList<Double> rP = new ArrayList<>();
        rP.add(rpq(0));
        rP.add(rpq(1));
        rP.add(rpq(2));
        rP.add(rpq(3));
        rP.add(rpq(4));
        rP.add(rpq(5));
        double dR2 = (a(rP.get(0), rP.get(1)) - rP.get(2)) * Math.pow(step, 2) / 2;
        r.set(0, rpc(0, rP.get(0), dR2));
        r.set(1, rpc(1, rP.get(1), dR2));
        r.set(2, rpc(2, rP.get(2), dR2));
        r.set(3, rpc(3, rP.get(3), dR2));
        r.set(4, rpc(4, rP.get(4), dR2));
        r.set(5, rpc(5, rP.get(5), dR2));

        dt += step;

        return new MultipleValueReturn<>(r.get(0), r.get(1));
    }

    private double rpq(int q) {
        int i = size;
        double rp = 0, x;
        while (i > q) {
            x = i - q - 1;
            x = (x == 0) ? 1 : x;
            rp = (rp + r.get(i - 1) * Math.pow(step, i - q - 1)) / x;
            i--;
        }
        return rp;
    }

    private int factorial(int n) {
        if (n == 1 || n == 0) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }

    private double rpc(int q, double rpq, double dR2) {
        return rpq + alfalist[q] * dR2 * factorial(q) / Math.pow(step, q);
    }
}

