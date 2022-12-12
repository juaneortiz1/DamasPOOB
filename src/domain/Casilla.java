package domain;

import java.awt.*;

/**
 * Clase que genera una casilla de forma logica
 */
public class Casilla {
    private Color color;
    private String name;
    private  int x,y;

    /**
     * Clase constructora de Casilla
     * @param x posicion del objeto en x
     * @param y posicion del objeto en y
     * @param name Nombre de la casilla
     */
    public Casilla(int x, int y, String name){
        color = Color.BLACK;
        this.x = x;
        this.y = y;
        this.name = name;
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
     * @return entero
     */
    public int getX() {
        return x;
    }
    public void setX(int x) {this.x = x;}
    public void setY(int y) {this.y = y;}
    /**
     * Retorna posicion del objeto en y
     *
     * @param y entero que establece la posicion en y
     * @return entero
     */
    public int getY(int y) {
        return y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
