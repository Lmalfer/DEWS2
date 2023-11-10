package model;

/**
 *
 * La clase Nomia Calcula el sueldo base del empleado a partir de su categoría
 */

public class Nomina {

    /**
     * Array constante que almacena los sueldos base en diferentes niveles.
     */
    private static final int SUELDO_BASE[] =
            {50000, 70000, 90000, 110000, 130000,
                    150000, 170000, 190000, 210000, 230000};

    /**
     * Bonus por año trabajado
     */
    private double bonus = 5000;

    /**Asignación del sueldo base en relación a la categoría designada.
     *
     * @param categoria
     * @param anyos
     * @return el sueldo base del empleado
     */
    public double sueldo(int categoria, double anyos) {
        double sueldo;

        switch (categoria) {
            case 1:
                categoria = SUELDO_BASE[0];
                break;
            case 2:
                categoria = SUELDO_BASE[1];
                break;
            case 3:
                categoria = SUELDO_BASE[2];
                break;
            case 4:
                categoria = SUELDO_BASE[3];
                break;
            case 5:
                categoria = SUELDO_BASE[4];
                break;
            case 6:
                categoria = SUELDO_BASE[5];
                break;
            case 7:
                categoria = SUELDO_BASE[6];
                break;
            case 8:
                categoria = SUELDO_BASE[7];
                break;
            case 9:
                categoria = SUELDO_BASE[8];
                break;
            case 10:
                categoria = SUELDO_BASE[9];
                break;
        }
        sueldo = categoria + bonus * anyos;
        return sueldo;
    }

}