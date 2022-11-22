package presentation;

import javax.swing.*;
import java.awt.*;

public  abstract class Casilla extends  JPanel{
    private int posicionX;
    private int posicionY;
    private Color color;


    public int getPosicionX() {
        return posicionX;
    }

    public int getPosicionY() {
        return posicionY;
    }

    public void getPoder() {}


    public void setPosicionX(int posicionX) {
        this.posicionX = posicionX;
    }

    public void setPosicionY(int posicionY) {
        this.posicionY = posicionY;
    }

    public void apagar(){this.setEnabled(false);}
    /**
     * Enciende el boton
     * */
    public void encender(){this.setEnabled(true);}


    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.setBackground(color);
        this.color = color;
    }
}
