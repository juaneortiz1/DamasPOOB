package presentation;

import javax.swing.*;
import java.awt.*;

/**
 * Clase que fabrica la ficha en las casillas del tablero
 */
public class Ficha extends JButton {
    private Color color;
    private int x,y;

    /**
     * Constructor de la clase ficha
     * @param color Color del objeto
     * @param y Posicion en y del objeto
     * @param x Posicion en x del objeto
     */
    public Ficha(Color color, int y, int x){
        this.color = color;
        this.setBackground(color);
        this.x = x;
        this.y = y;
    }

    /**
     * Cambia el color del objeto
     * @param color Color del objeto
     */
    public void changeColor(Color color){
        this.color = color;
        this.setBackground(color);
    }

    /**
     * Desactiva el objeto
     */
    public void desacive(){
        this.setEnabled(false);
    }

    /**
     * Activa el objeto
     */
    public void acive(){ this.setEnabled(true);}

    /**
     * Retorna el color del objeto
     * @return Color
     */
    public Color getColor(){
        return color;
    }

    /**
     * Retorna la posicion en x del objeto
     * @return int
     */
    @Override
    public int getX() {
        return x;
    }

    /**
     * Retorna la posicion en y del objeto
     * @return int
     */
    @Override
    public int getY() {
        return y;
    }
}
