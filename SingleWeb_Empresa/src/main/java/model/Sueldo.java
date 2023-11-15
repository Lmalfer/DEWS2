package model;

/**
 * Clase que representa los datos de un sueldo asociado a un empleado.
 */
public class Sueldo {

    private String dni;
    private double sueldo;

    /**
     * Constructor de la clase Sueldo.
     *
     * @param dni     El DNI del empleado.
     * @param sueldo  El sueldo asociado al empleado.
     */
    public Sueldo(String dni, double sueldo) {
        this.dni = dni;
        this.sueldo = sueldo;
    }

    /**
     * Obtiene el DNI del empleado asociado al sueldo.
     *
     * @return El DNI del empleado.
     */
    public String getDni() {
        return dni;
    }

    /**
     * Obtiene el sueldo asociado al empleado.
     *
     * @return El sueldo del empleado.
     */
    public double getSueldo() {
        return sueldo;
    }

}