package org.braulioecheverria.models;

/**
 *
 * @author Brau
 */
public class Alumno {
    private String carne;
    private String noExpediente;
    private String apellidos;
    private String nombres;
    private String email;

    public Alumno() {
    }

    public Alumno(String carne, String noExpediente, String apellidos, String nombres, String email) {
        this.carne = carne;
        this.noExpediente = noExpediente;
        this.apellidos = apellidos;
        this.nombres = nombres;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCarne() {
        return carne;
    }

    public void setCarne(String carne) {
        this.carne = carne;
    }

    public String getNoExpediente() {
        return noExpediente;
    }

    public void setNoExpediente(String noExpediente) {
        this.noExpediente = noExpediente;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    @Override
    public String toString() {
        return "{ " + "\n" +
                "   Carnet: " + carne + ", " + "\n" + 
                "   # de expediente: " + noExpediente + ", " + "\n" + 
                "   Apellidos: " + apellidos + ", " + "\n" + 
                "   Nombres: " + nombres + ", " + "\n" + 
                "   Email: " + email + ", " + "\n" + 
                "}";
    }
    
}
