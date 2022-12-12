package domain;

import java.awt.*;

/**
 * Lugar donde puede ser posicionada una ficha
 */
public class CasillaNormal extends Casilla{
    private Color color;
    private String name;
    private  int x,y;
    /**
     * Clase constructora de Casilla
     *
     * @param x    posicion del objeto en x
     * @param y    posicion del objeto en y
     * @param name
     */
    public CasillaNormal(int x, int y, String name) {
        super(x, y, name);
        this.x = x;
        this.y = y;
        this.name = name;
        this.color  =  Color.BLACK;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


