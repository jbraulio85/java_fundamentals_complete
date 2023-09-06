package org.braulioecheverria.views;

import org.braulioecheverria.controllers.SalonController;
import org.braulioecheverria.models.Salon;
import org.braulioecheverria.utils.Lector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SalonView {
    private static SalonView instancia;
    private Scanner leer = Lector.getInstancia();
    private SalonController salonC = SalonController.getInstancia();
    private Salon salon = new Salon();
    private SalonView(){}

    public static synchronized SalonView getInstancia(){
        if(instancia == null){
            instancia = new SalonView();
        }
        return instancia;
    }

    public void buscar(){
        System.out.println("Ingrese el nombre del salón que desea buscar");
        String nombreSalon = leer.nextLine();
        try {
            salon = salonC.buscarSalon(nombreSalon);
            if(!salon.getSalonId().equals(null)){
                System.out.println(salon);
            }else {
                System.out.println("Error: Salón no encontrado.");
            }
        }catch (NullPointerException e){
            System.out.println("Error!!!" + "\n"+
                    "No Existe el salón con el nombre " + nombreSalon);
        }
    }

    public void agregarSalon() {
        ResultSet respuesta = salonC.listarSalones();
        System.out.println("Los salones en lista son: " + "\n" +
                "{ ");
        try{
            while (respuesta.next()){
                salon.setNombreSalon(respuesta.getNString(4));
                System.out.println(salon.nombreSalones());
            }
        }catch (SQLException e){
            System.out.printf(e.getMessage());
        }
        System.out.println("}" + "\n" +
                "***********************************************" + "\n"+
                "Ingresa el nombre del salón");
        String nombreSalon = leer.nextLine();
        System.out.println("Ingresa la capacidad del salón");
        int capacidad = leer.nextInt();
        leer.nextLine();
        System.out.println("Ingresa la descripción del laborarorio (para qué carrera será su función)");
        String descripcion = leer.nextLine();
        salon = salonC.agregarSalon(capacidad,descripcion,nombreSalon);
        if(salon.getSalonId().equals(null)){
            System.out.println("Error: Existió un error al momento de almacenar el registro");
        }else{
            System.out.println("Salón agregado exitosamente!!!");
        }
    }

    public void detalleSalones(){
        ResultSet respuesta = salonC.listarSalones();
        try{
            while (respuesta.next()){
                salon.setCapacidad(respuesta.getInt(2));
                salon.setDescripcion(respuesta.getNString(3));
                salon.setNombreSalon(respuesta.getString(4));
                System.out.println(salon);
            }
        }catch (SQLException e){
            System.out.printf(e.getMessage());
        }
    }

    public void eliminarSalon(){
        ResultSet resultado = salonC.listarSalones();
        System.out.println("Los salones en lista son: " + "\n" +
                "{ ");
        try{
            while (resultado.next()){
                salon.setNombreSalon(resultado.getNString(4));
                System.out.println(salon.nombreSalones());
            }
        }catch (SQLException e){
            System.out.printf(e.getMessage());
        }
        System.out.println("}" + "\n" +
                "***********************************************" + "\n"+
                "Ingresa el nombre del salón que deseas eliminar");
        String nombreSalon = leer.nextLine();
        salonC.eliminarSalon(nombreSalon);
        System.out.println("Registro eliminado exitosamente!!!");
    }

    public void actualizarSalon(){
        ResultSet resultado = salonC.listarSalones();
        System.out.println("Los salones en lista son: " + "\n" +
                "{ ");
        try{
            while (resultado.next()){
                salon.setNombreSalon(resultado.getNString(4));
                System.out.println(salon.nombreSalones());
            }
        }catch (SQLException e){
            System.out.printf(e.getMessage());
        }
        System.out.println("}" + "\n" +
                "***********************************************" + "\n"+
                "Ingresa el nombre del salón que deseas actualizar");
        String nombreSalon = leer.nextLine();
        salon = salonC.buscarSalon(nombreSalon);
    }
}
