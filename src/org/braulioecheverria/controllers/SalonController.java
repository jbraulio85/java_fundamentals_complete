package org.braulioecheverria.controllers;

import org.braulioecheverria.dao.Conexion;
import org.braulioecheverria.models.Salon;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SalonController {
    private static SalonController instancia;
    private Salon salon = new Salon();
    private SalonController(){}

    public static SalonController getInstancia(){
        if(instancia == null){
            instancia = new SalonController();
        }
        return instancia;
    }

    public Salon buscarSalon(String nombre){
        try {
            PreparedStatement sp = Conexion.getInstancia().getConexion().prepareCall("call sp_buscar_salon(?);");
            sp.setString(1,nombre);
            ResultSet respuesta = sp.executeQuery();
            while (respuesta.next()){
                salon.setSalonId(respuesta.getNString(1));
                salon.setCapacidad(respuesta.getInt(2));
                salon.setDescripcion(respuesta.getString(3));
                salon.setNombreSalon(respuesta.getNString(4));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return salon;
    }

    public Salon agregarSalon(int capacidad, String descripcion,String nombreSalon){
        try{
            PreparedStatement sp = Conexion.getInstancia().getConexion().prepareCall("call sp_agregar_salon(?,?,?)");
            sp.setInt(1,capacidad);
            sp.setString(2,descripcion);
            sp.setString(3,nombreSalon);
            sp.execute();
            salon = buscarSalon(nombreSalon);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return salon;
    }

    public ResultSet listarSalones(){
        ResultSet respuesta = null;
        try{
            PreparedStatement sp = Conexion.getInstancia().getConexion().prepareCall("call sp_listar_salones();");
             respuesta = sp.executeQuery();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return respuesta;
    }

    public void eliminarSalon(String nombreSalon){
        salon = buscarSalon(nombreSalon);
        try{
            PreparedStatement sp = Conexion.getInstancia().getConexion().prepareCall("call sp_eliminar_salon(?)");
            sp.setString(1,salon.getSalonId());
            sp.execute();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public Salon actualizarSalon(String salonId, int capacidad, String descripcion, String nombreSalon){
        try{
            PreparedStatement sp = Conexion.getInstancia().getConexion().prepareCall("call sp_actualizarSalon(?,?,?,?); ");
            sp.setString(1,salonId);
            sp.setInt(2,capacidad);
            sp.setString(3,descripcion);
            sp.setString(4,nombreSalon);
            sp.execute();
            salon = buscarSalon(nombreSalon);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return salon;
    }
}
