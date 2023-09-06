package org.braulioecheverria.dao;

import java.sql.*;

/**
 *
 * @author Brau
 */
public class Conexion {
    private Connection conexion;
    private static Conexion instancia;
    
    private Conexion(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            conexion = DriverManager.getConnection
                                ("jdbc:mysql://localhost:3306/admin_educacion?useSSL=false&useUnicode=true&characterEncoding=UTF-8",
                                        "root","root");
        }catch(ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e){
            
        }
    }
    
    public static synchronized Conexion getInstancia(){
        if(instancia == null){
            instancia = new Conexion();
        }
        return instancia;
    }
    
    public Connection getConexion(){
        return conexion;
    }
    
    public void setConexion(Connection conexion){
        this.conexion = conexion;
    }
    
}
