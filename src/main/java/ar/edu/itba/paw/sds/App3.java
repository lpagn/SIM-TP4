package ar.edu.itba.paw.sds;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Date;

public class App3 {
    public static void main(String[] args) throws IOException {
    	
    	Date date = new Date();
    	System.out.println(date);
    	
    	Mision mision = new Mision();
    	
    	mision.fly(100,5,6.307e+7);
    	
        /*
        
        int deltaDia es la cantidad de dias desde el 6 de Abril de 2020 en el cual se lanza la nave.
        
        double step es el paso medido en segundos
        
        double ventana es la cantidad de segundos. SIEMPRE ES DOS ANOS . Hay alguna constante de Java ? 
        
        v0 es la velocidad inicial de la nave
        
        */
    }
}