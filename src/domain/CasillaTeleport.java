package domain;

import java.awt.*;

public class CasillaTeleport extends Casilla{
    private String name;
    private  int x,y;
    private Color color;
    /**
     * Clase constructora de Casilla
     *
     * @param x    posicion del objeto en x
     * @param y    posicion del objeto en y
     * @param name
     */
    public CasillaTeleport(int x, int y, String name) {
        super(x, y, name);
        this.x = x;
        this.y = y;
        this.name = name;
        this.color = Color.MAGENTA;
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
