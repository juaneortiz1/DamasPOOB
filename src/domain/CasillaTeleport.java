package domain;

import java.awt.*;

/**
 * Casilla que teletransporta una ficha a otra casilla teleport
 */
public class CasillaTeleport extends Casilla {
    private Color color;
    private String name;
    private  int x,y;
    /**
     * Clase constructora de Casilla
     * @param x    posicion del objeto en x
     * @param y    posicion del objeto en y
     * @param name
     */
    public CasillaTeleport(int x, int y, String name) {
        super(x, y, name);
        this.x = x;
        this.y = y;
        this.name = name;
        this.color  = Color.green;
    }

    public Color getColor() {
        return color;
    }


    public int getX() {
        return x;
    }


    public int getY() {
        return y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

}



