package domain;

import presentation.CasillaNormal;

import javax.swing.*;
import java.awt.*;

public class Casilla {
    private Color color;
    private Casilla[][]Casillas;
    private  int x,y;
    public Casilla(Color color,int x, int y ){
        this.color = color;
        this.x = x;
        this.y = y;
    }

    public Color getColor() {
        return color;
    }

    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
