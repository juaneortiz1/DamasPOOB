package domain;

import java.awt.*;
import java.util.Objects;

public class FichaNormal implements Ficha {
    private int x, y, live;
    private String name;
    private Color color;
    public FichaNormal(int x, int y, String name,Color color) {
        super();
        this.x = x;
        this.y = y;
        this.name = name;
        this.color = color;
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
    public  void toRevive(){}
    public  void isdead(){
        live -= 1;
        if(live < 1){
            dead();
            setColor(Color.BLACK);
        }
    }
    public boolean type(String ficha){
        boolean out = false;
        if(Objects.equals(name, "b")){
            out = Objects.equals(ficha, "b") || Objects.equals(ficha, "qb");
        }else if(Objects.equals(name, "r")){
            out = Objects.equals(ficha, "r") || Objects.equals(ficha, "qr");
        }
        return out;
    }

    @Override
    public int[][] makeMove(int x, int y){
        int[][] move = new int[2][2];
        int my = 1;
        if(getName().equals("b")){my = -1;}
        move[0][0] = x-1;
        move[0][1] = y+my;
        move[1][0] = x+1;
        move[1][1] = y+my;
        return move;
    }
    @Override
    public int[][] makeEat(int x, int y, int i){
        int[] vx = {-2,2,-2,2};
        int[] vy = {-2,-2,2,2};
        int[][] move = new int[1][2];
        if(getName().equals("r")){i += 2;}
        move[0][0] = x+vx[i];
        move[0][1] = y+vy[i];
        return move;
    }
}
