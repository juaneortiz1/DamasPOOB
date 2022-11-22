package presentation;

import javax.swing.*;
import java.awt.*;

public class Ficha extends JButton {
    private Color color;
    private int x,y;

    public Ficha(Color color, int y, int x){
        this.color = color;
        this.setBackground(color);
        this.x = x;
        this.y = y;
    }
    public void changeColor(Color color){
        this.color = color;
        this.setBackground(color);
    }

    public void acive(Color color){
        this.setForeground(color);
    }
    public Color getColor(){
        return color;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }
}
