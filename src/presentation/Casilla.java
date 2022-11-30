package presentation;

import javax.swing.*;
import java.awt.*;

/**
 * Clase padre de las casillas
 */
public  abstract class Casilla extends  JPanel{
    private int posicionX;
    private int posicionY;
    private Color color;

    /**
     * Retorna la posicion en x de la casilla
     * @return int
     */

    public int getPosicionX() {
        return posicionX;
    }

    /**
     * Retorna la posicion en y de la casilla
     * @return int
     */
    public int getPosicionY() {
        return posicionY;
    }

    public void getPoder() {}

    /**
     * establece la posicíon del objeto en x
     * @return int
     */
    public void setPosicionX(int posicionX) {
        this.posicionX = posicionX;
    }
    /**
     * establece la posicíon del objeto en y
     * @return int
     */
    public void setPosicionY(int posicionY) {
        this.posicionY = posicionY;
    }
    public void apagar(){this.setEnabled(false);}
    /**
     * Enciende el boton
     * */
    public void encender(){this.setEnabled(true);}

    /**
     * Retorna el color del objeto
     * @return Color
     */
    public Color getColor() {
        return color;
    }
    /**
     * Establece el color del objeto
     */
    public void setColor(Color color) {
        this.setBackground(color);
        this.color = color;
    }
}
