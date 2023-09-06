package org.braulioecheverria.views;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import org.braulioecheverria.controllers.AlumnoController;
import org.braulioecheverria.models.Alumno;
import org.braulioecheverria.utils.Lector;

/**
 *
 * @author Brau
 */
public class

AlumnoView {
    private static AlumnoView instancia;
    private Scanner leer = Lector.getInstancia();
    private Alumno al = new Alumno();
    private AlumnoController ac = AlumnoController.getInstancia();
    private AlumnoView(){}
    
    public static synchronized AlumnoView getInstancia(){
        if(instancia == null){
            instancia = new AlumnoView();
        }
        return instancia;
    }
    
    public void buscar(){
        boolean validInput = false;
        while(!validInput){
            try{
                    System.out.println("Ingresa el Carné del alumno a buscar: ");
                    int  carne = leer.nextInt();
                    al = ac.buscarAlumno(String.valueOf(carne));
                    validInput = true;
                    if(al.getCarne() != null){
                        System.out.println(al);
                    }else{
                        System.out.println("No se encontró el alumno con el carné " + carne);
                    }
            }catch(InputMismatchException e){
                System.out.println("Error: Ingresa un carné válido.");
                leer.next();
            }     
        }
    }
    
    public void listarAlumnos(){
        ResultSet respuesta = ac.listarAlumnos();
       try{
           while(respuesta.next()){
               al.setCarne(respuesta.getNString(1));
               al.setNoExpediente(respuesta.getNString(2));
               al.setApellidos(respuesta.getNString(3));
               al.setNombres(respuesta.getNString(4));
               al.setEmail(respuesta.getNString(5));
               System.out.println(al);
           }
       }catch(SQLException e){
           System.out.println(e.getMessage());
       }
    }
    
    public void eliminarAlumno(){
        boolean validInput = false;
        while(!validInput){
            try{
                    System.out.println("Ingresa el Carné del alumno a buscar: ");
                    int  carne = leer.nextInt();
                    al = ac.buscarAlumno(String.valueOf(carne));
                    if(al.getCarne() != null){
                        ac.eliminarAlumno(String.valueOf(carne));
                        System.out.println("Alumno eliminado exitosamente!!!");
                    }else{
                        System.out.println("No se encontró el alumno con el carné " + carne);
                    }
                    validInput = true;
            }catch(InputMismatchException e){
                System.out.println("Error: Ingresa un carné válido.");
                leer.next();
            }     
        }
    }
    
    public void actualizarAlumno(){
        boolean validInput = false;
        while(!validInput){
            try{
                    System.out.println("Ingresa el Carné del alumno a buscar: ");
                    int  carne = leer.nextInt();
                    leer.nextLine();
                    al = ac.buscarAlumno(String.valueOf(carne));
                    validInput = true;
                    if(al.getCarne() != null){
                        System.out.println("Ingresa el nuevo nombre: ");
                        String nombres = leer.nextLine();
                        System.out.println("Ingresa el nuevo apellido : ");
                        String apellidos = leer.nextLine();
                        String noExpediente = al.getNoExpediente();
                        System.out.println("Ingresa el nuevo email: ");
                        String email = leer.nextLine();
                        ac.actualizarAlumno(al.getCarne(), noExpediente, apellidos, nombres, email);
                        System.out.println("Alumno actualizado exitosamente!!!");
                    }else{
                        System.out.println("No se encontró el alumno con el carné " + carne);
                    }
            }catch(InputMismatchException e){
                System.out.println("Error: Ingresa un carné válido.");
                leer.next();
            }
        }
    }
    
    public void agregarAlumno(){
        String apellido = null;
        Date anio = new Date();
        SimpleDateFormat anioActual = new SimpleDateFormat("yyyy");
        String anioSistema = anioActual.format(anio);
        String carne = anioSistema + crearCarne();

        System.out.println("Ingresa el nombre del alumno: ");
        String nombres = leer.nextLine();
        System.out.println("Ingresa el apellido del alumno : ");
        String apellidos = leer.nextLine();
        String ultimosDigitos = carne.substring(carne.length() - 3);
        String noExpediente = "E" + anioSistema + "-" + ultimosDigitos;
        String[] primerApellido = apellidos.split(" ");
        int tamanio = primerApellido.length;
        apellido = quitarAcentos(primerApellido[0]);
        if(tamanio > 2){
           apellido = quitarAcentos(primerApellido[0] + primerApellido[1]);
        }
        String inicial = quitarAcentos( nombres.substring(0,1).toLowerCase());
        String email = inicial.toLowerCase() + apellido.toLowerCase() + "-" +carne +"@kinal.edu.gt";
        al = ac.agregarAlumno(carne,apellidos,nombres,noExpediente,email);
        if(al.getCarne() != carne){
            System.out.println("Registro agregado exitosamente!!!" + "\n" +
            "Los datos del alumnos son:" + "\n" +
                    " { " + "\n" +
                    "   Carné: " + carne + "\n" +
                    "   Email: " + email + "\n" +
                    " }"
            );
        }else {
            System.out.println("Error: Existió un error en proceo de inscripción del alumno");
        }
    }

    private String crearCarne(){
        String carneDevuelto = ac.obtenerUltimoCarne().substring(ac.obtenerUltimoCarne().length() - 3);
        int numero = (Integer.parseInt(carneDevuelto)+1);
        DecimalFormat formato = new DecimalFormat("000");
        String valorFormateado = formato.format(numero);
        return valorFormateado;
    }

    private String quitarAcentos(String texto){
        texto = texto.replaceAll("á","a");
        texto = texto.replaceAll("é","e");
        texto = texto.replaceAll("í","i");
        texto = texto.replaceAll("ó","o");
        texto = texto.replaceAll("ú","u");
        texto = texto.replaceAll("Á","a");
        texto = texto.replaceAll("É","e");
        texto = texto.replaceAll("Í","i");
        texto = texto.replaceAll("Ó","o");
        texto = texto.replaceAll("Ú","u");
        return texto;
    }
}
