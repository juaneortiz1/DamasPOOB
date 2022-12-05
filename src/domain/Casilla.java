package domain;

import java.awt.*;

/**
 * Clase que genera una casilla de forma logica
 */
public class Casilla {

    private  int posX,posY;
    private String nombre;

    /**
     * Clase constructora de Casilla
     * @param x posicion del objeto en x
     * @param y posicion del objeto en y
     */
    public Casilla(int x, int y ){
        this.posX = x;
        this.posY = y;
    }
    /**
     * Retorna la posicion en x del objeto
     * @return
     */
    public int getX() {
        return posX;
    }

    /**
     * Retorna posicion del objeto en y
     *
     * @param y entero que establece la posicion en y
     * @return
     */
    public int getY(int y) {
        return posY;
    }

}
