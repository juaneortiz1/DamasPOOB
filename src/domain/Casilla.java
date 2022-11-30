package domain;

import java.awt.*;

/**
 * Clase que genera una casilla de forma logica
 */
public class Casilla {
    private Color color;
    private Casilla[][]Casillas;
    private  int x,y;

    /**
     * Clase constructora de Casilla
     * @param color objeto tipo color
     * @param x posicion del objeto en x
     * @param y posicion del objeto en y
     */
    public Casilla(Color color,int x, int y ){
        this.color = color;
        this.x = x;
        this.y = y;
    }

    /**
     * Retorna el color del objeto
     * @return Color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Retorna la posicion en x del objeto
     * @return
     */
    public int getX() {
        return x;
    }

    /**
     * Retorna posicion del objeto en y
     *
     * @param y entero que establece la posicion en y
     * @return
     */
    public int getY(int y) {
        return y;
    }

}
