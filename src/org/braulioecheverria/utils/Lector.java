package org.braulioecheverria.utils;
import java.util.Scanner;
/**
 *
 * @author Brau
 */
public class Lector {
    private static Scanner instancia;
    
    private Lector(){
    }
    
    public static synchronized Scanner getInstancia(){
        if(instancia == null){
            instancia  = new Scanner(System.in, "UTF-8");
        }
        return instancia;
    }
}
