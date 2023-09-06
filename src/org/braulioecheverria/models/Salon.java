package org.braulioecheverria.models;

public class Salon {
    private String salonId;
    private int capacidad;
    private String descripcion;
    private String nombreSalon;

    public Salon() {
    }

    public Salon(String salonId, int capacidad, String descripcion, String nombreSalon) {
        this.salonId = salonId;
        this.capacidad = capacidad;
        this.descripcion = descripcion;
        this.nombreSalon = nombreSalon;
    }

    public String getSalonId() {
        return salonId;
    }

    public void setSalonId(String salonId) {
        this.salonId = salonId;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombreSalon(String nombreSalon) {
        return nombreSalon;
    }

    public void setNombreSalon(String nombreSalon) {
        this.nombreSalon = nombreSalon;
    }

    @Override
    public String toString() {
        return "{ " + "\n" +
                "   Salón: " + nombreSalon  + "\n" +
                "   Capacidad: " + capacidad + ", " + "\n" +
                "   Descripción: " + descripcion + ", " + "\n" +
                "}";
    }

    public String nombreSalones(){
    return " Salón: " + nombreSalon;
    }
}
