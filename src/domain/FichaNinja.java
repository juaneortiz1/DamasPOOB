package domain;

import java.awt.*;
import java.util.Objects;

/**
 * Clase de una ficha la cual cuenta con dos vidas en vez de una
 */
public class FichaNinja implements Ficha{
    private int x, y, live;
    private String name;
    private Color color;
    public FichaNinja(int x, int y, String name) {
        super();
        this.x = x;
        this.y = y;
        this.name = name;
        color = Objects.equals(name, "nb") ? Color.BLUE: Color.RED;
        live = 2;
    }
    public int getX() {return x;}
    public void setX(int x) {this.x = x;}
    public  int getY() {return y;}
    public  void setColor(Color color) {this.color = color;}
    public  Color getColor() {return color;}
    public  void setY(int y) {this.y = y;}
    public  int getLive() {return live;}
    public  void setLive(int live) {}
    public  String getName() {return name;}
    public  void setName(String name) {this.name = name;}

    /**
     * Asesina a la ficha
     */
    public  void dead(){setName("_");}
    public void toRevive(){}

    /**
     * Resta las vidas que tenga las fichas
     */
    public  void isdead(){
        live -= 1;
        if(live < 1){
            dead();
            setColor(Color.BLACK);
        }
    }

    /**
     * Determina el tipo de ficha
     * @param ficha String que identifica a la ficha
     * @return booleano
     */
    public boolean tipe(String ficha){
        boolean out = false;
        if(Objects.equals(name, "nb")){
            out = (Objects.equals(ficha, "r") || Objects.equals(ficha, "qr")) || (Objects.equals(ficha, "nr") || Objects.equals(ficha, "zr"));
        } else if (Objects.equals(name, "nr")){
            out = (Objects.equals(ficha, "b") || Objects.equals(ficha, "qb")) || (Objects.equals(ficha, "nb") || Objects.equals(ficha, "zb"));
        }
        return out;
    }

    /**
     * Determina que movimientos puede hacer la ficha
     * @param x Determina la posicion en x de la ficha
     * @param y Determina la posicion en y de la ficha
     * @return retorna una matriz  de enteros
     */
    @Override
    public int[][] makeMove(int x, int y){
        int[][] move = new int[4][2];
        move[0][0] = x-1;
        move[0][1] = y+1;
        move[1][0] = x+1;
        move[1][1] = y+1;
        move[2][0] = x-1;
        move[2][1] = y-1;
        move[3][0] = x+1;
        move[3][1] = y-1;
        return move;
    }

    /**
     * Determina si la ficha ninja puede comer alrededor de ella
     * @param x Determina la posicion en x de la ficha
     * @param y Determina la posicion en y de la ficha
     * @param i es el numero que se usará para saber en que casilla se movera para comer
     * @return retorna una matriz  de enteros
     */
    public int[][] makeEat(int x, int y, int i){
        int[] vx = {2,2,-2,-2};
        int[] vy = {-2,2,-2,2};
        int[][] move = new int[1][2];
        move[0][0] = x+vx[i];
        move[0][1] = y+vy[i];
        return move;
    }
}
