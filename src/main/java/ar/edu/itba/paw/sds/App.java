package ar.edu.itba.paw.sds;

import java.io.FileWriter;
import java.io.IOException;

public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "SIM-TP4");
        Integrator i = new Integrator();
            
    	String analytical = i.stepSolution();
    	
    	write("analytical.txt",analytical);
    	
    	Beeman b = new Beeman();
    	
    	String beeman = b.stepBeeman();
    	
    	
    	System.out.println(b.r0);
    	System.out.println(b.v0);
    	System.out.println(b.f(b.r0,b.v0));
    	System.out.println(b.step);
    	
    	write("beeman.txt",beeman);
    	
    	LeapFrog l = new LeapFrog();
    	
    	String leapfrog = l.stepLeapFrog();
    	
    	write("leapfrog.txt",leapfrog);
    	
    	Gear g = new Gear();
    	
        String gear = g.stepGear();
        
        write("gear.txt",gear);
        
        Euler e = new Euler();
        
        String euler = e.stepEuler();
        
        write("euler.txt",euler);

    }
    
    private static void write (String filename , String value) {
    	try {
  	      FileWriter myWriter = new FileWriter(filename);
  	      myWriter.write(value);
  	      myWriter.close();
  	      System.out.println("Successfully wrote to the file.");
  	    } catch (IOException e) {
  	      System.out.println("An error occurred.");
  	      e.printStackTrace();
  	    }
    }
    
}




