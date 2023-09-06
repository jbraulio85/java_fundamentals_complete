package org.braulioecheverria.views;
import java.util.Scanner;
import java.util.InputMismatchException;
import org.braulioecheverria.utils.Lector;

/**
 *
 * @author Brau
 */
public class MenuGeneralView {
    private final Scanner leer = Lector.getInstancia();
     private final AlumnoView av = AlumnoView.getInstancia();
     private final SalonView sv = SalonView.getInstancia();
     private int op = 0;
    
    private static MenuGeneralView instancia;
    
    private MenuGeneralView(){}
    
    public static synchronized MenuGeneralView getInstancia(){
        if(instancia == null){
            instancia = new MenuGeneralView();
        }
        return instancia;
    }
    
    public void arranque(){
        try{
            boolean validInput = false;
            while(!validInput){
                System.out.println("Bienvenido al sistema de gestión Educativa " + "\n" +
                        "Ingresa el número de la opción que deseas " + "\n" +
                        "1. Administración de Alumnos"+ "\n" +
                        "2. Administración de Salones"+ "\n" +
                        "0. Salir");
                op = leer.nextInt();
                switch(op){
                    case 1:
                        administradorAlumnos();
                        break;
                    case 2:
                        administradorSalones();
                        break;
                }
            }
        }catch(InputMismatchException e){
            System.out.println("Ingresaste una letra en lugar de un número, intenta de nuevo");
        }
    }
    
    private void administradorAlumnos(){
        try{
            boolean validInput = false;
            while(!validInput){
                System.out.println(" " + "\n" +
                        "***** ADMINISTRACIÓN DE ALUMNOS ***** " + "\n" +
                        "Ingresa el número de la opción que deseas " + "\n" +
                        "1. Buscar Alumno"+ "\n" +
                        "2. Listar Alumnos"+ "\n" +
                        "3. Editar Alumno"+ "\n" +
                        "4. Eliminar Alumno"+ "\n" +
                        "5. Agregar Alumno"+ "\n" +
                        "0. Regresar");
                op = leer.nextInt();
                leer.nextLine();
                switch(op){
                    case 1:
                        av.buscar();
                        break;
                    case 2:
                        av.listarAlumnos();
                        break;
                    case 3:
                        av.actualizarAlumno();
                        break;
                    case 4:
                        av.eliminarAlumno();
                        break;
                    case 5:
                        av.agregarAlumno();
                        break;
                    case 0:
                        arranque();
                }
            }
        }catch(InputMismatchException e){
            System.out.println("Ingresaste una letra en lugar de un número, intenta de nuevo");
        }
    }

    private void administradorSalones(){
        try{
            boolean validInput = false;
            while(!validInput){
                System.out.println(" " + "\n" +
                        "***** ADMINISTRACIÓN DE SALONES ***** " + "\n" +
                        "Ingresa el número de la opción que deseas " + "\n" +
                        "1. Buscar Salón"+ "\n" +
                        "2. Listar Salones"+ "\n" +
                        "3. Editar Salón"+ "\n" +
                        "4. Eliminar Salón"+ "\n" +
                        "5. Agregar Salón"+ "\n" +
                        "0. Regresar");
                op = leer.nextInt();
                leer.nextLine();
                switch(op){
                    case 1:
                        sv.buscar();
                        break;
                    case 2:
                        sv.detalleSalones();
                        break;
                    case 3:
                        av.actualizarAlumno();
                        break;
                    case 4:
                        sv.eliminarSalon();
                        break;
                    case 5:
                        sv.agregarSalon();
                        break;
                    case 0:
                        arranque();
                }
            }
        }catch(InputMismatchException e){
            System.out.println("Ingresaste una letra en lugar de un número, intenta de nuevo");
        }
    }

}
