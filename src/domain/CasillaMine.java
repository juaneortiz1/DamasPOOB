package domain;

import java.awt.*;

/**
 * Clase que genera una casilla que explota y asesina a las fichas asu alrededor
 */
public class CasillaMine extends Casilla{
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
    public CasillaMine(int x, int y, String name) {
        super(x, y, name);
        this.x = x;
        this.y = y;
        this.name = name;
        this.color  = Color.DARK_GRAY;
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

    /**
     * Devuelve una matriz de posiciones con las fichas alrededor de de la mina
     * @param x
     * @param y
     * @return
     */
    public int[][] perimeter(int x ,int y){
        int[][] perimeter = new int[5][2];
        perimeter[0][0] = x;
        perimeter[0][1] = y;

        perimeter[1][0] = x - 1;
        perimeter[1][1] = y - 1;

        perimeter[2][0] = x + 1;
        perimeter[2][1] = y - 1;

        perimeter[3][0] = x - 1;
        perimeter[3][1] = y + 1;

        perimeter[4][0] = x + 1;
        perimeter[4][1] = y + 1;
        return perimeter;
    }
}
