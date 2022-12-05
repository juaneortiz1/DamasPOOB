package domain;

import java.awt.*;
import java.util.Objects;

public class FichaReina implements Ficha {
    private int x, y, live;
    private String name;
    private Color color;
    public FichaReina(int x, int y, String name) {
        super();
        this.x = x;
        this.y = y;
        this.name = name;
        color = Objects.equals(name, "qb") ? Color.BLUE: Color.RED;
        live = 1;
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
    public  void dead(){setName("_");}
    public  void isdead(){
        live -= 1;
        if(live < 1){
            dead();
            setColor(Color.BLACK);
        }
    }
    public boolean tipe(String ficha){
        boolean out = false;
        if(Objects.equals(name, "qb")){
            out = Objects.equals(ficha, "b") || Objects.equals(ficha, "qb") || Objects.equals(ficha, "nb") || Objects.equals(ficha, "zb");
        }else if(Objects.equals(name, "qr")){
            out = Objects.equals(ficha, "r") || Objects.equals(ficha, "qr") || Objects.equals(ficha, "nr") || Objects.equals(ficha, "zr");;
        }
        return out;
    }
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
    public int[][] makeEat(int x, int y, int i){
        int[] vx = {-2,2,-2,2};
        int[] vy = {-2,-2,2,2};
        int[][] move = new int[1][2];
        move[0][0] = x+vx[i];
        move[0][1] = y+vy[i];
        return move;
    }
}