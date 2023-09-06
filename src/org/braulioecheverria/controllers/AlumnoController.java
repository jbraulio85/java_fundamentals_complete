package org.braulioecheverria.controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.braulioecheverria.dao.Conexion;
import org.braulioecheverria.models.Alumno;

/**
 *
 * @author Brau
 */
public class AlumnoController {
    private static AlumnoController instancia;
    Alumno al = new Alumno();
    
    private AlumnoController(){}
    
    public static synchronized AlumnoController getInstancia(){
        if(instancia == null){
            instancia = new AlumnoController();
        }
        return instancia;
    }
    
    public Alumno buscarAlumno(String carne){
        try{
            PreparedStatement sp = Conexion.getInstancia().getConexion().prepareCall("call sp_buscar_alumno(?);");
            sp.setString(1, carne);
            ResultSet resultado = sp.executeQuery();
            while(resultado.next()){
                al.setCarne(resultado.getNString(1));
                al.setNoExpediente(resultado.getNString(2));
                al.setApellidos(resultado.getNString(3));
                al.setNombres(resultado.getNString(4));
                al.setEmail(resultado.getNString(5));
            }
            resultado.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return al;
    }
    
    public ResultSet listarAlumnos(){
        ResultSet resultado = null;
        try{
            PreparedStatement sp = Conexion.getInstancia().getConexion().prepareCall("call sp_listar_alumnos();");
            resultado = sp.executeQuery();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return resultado;
    }
    
    public void eliminarAlumno(String carne){
        try{
            PreparedStatement sp = Conexion.getInstancia().getConexion().prepareCall("call sp_eliminar_alumno(?);");
            sp.setString(1, carne);
            sp.execute();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    public Alumno actualizarAlumno(String carne, String noExpediente, String apellidos, String nombres, String email){
        try{
            PreparedStatement sp =Conexion.getInstancia().getConexion().prepareCall("call sp_actualizar_alumno(?,?,?,?,?);");
            sp.setString(1, carne);
            sp.setString(2, noExpediente);
            sp.setString(3, apellidos);
            sp.setString(4, nombres);
            sp.setString(5, email);
            sp.execute();
            al = buscarAlumno(carne);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return al;
    }
    
    public Alumno agregarAlumno(String carne, String apellidos, String nombres, String noExpediente, String email){
        try{
            PreparedStatement sp = Conexion.getInstancia().getConexion().prepareCall("call sp_agregar_alumno(?,?,?,?,?)");
            sp.setString(1, carne);
            sp.setString(2, noExpediente);
            sp.setString(3, apellidos);
            sp.setString(4, nombres);
            sp.setString(5, email);
            sp.execute();
            al = buscarAlumno(carne);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return al;
    }

    public String obtenerUltimoCarne(){
        String carneDevuelto = null;
        try{
            PreparedStatement sp = Conexion.getInstancia().getConexion().prepareCall("select * from vw_listarCarne;");
            ResultSet respuesta = sp.executeQuery();
            if(respuesta.next()){
                carneDevuelto = respuesta.getString(1);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return carneDevuelto;
    }
}
