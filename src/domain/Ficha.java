package domain;

import java.awt.*;

public interface Ficha {
    public default int getX() {return 0;}
    private void setX(int x) {}
    public default int getY() {return 0;}
    public default void setColor(Color color) {}
    public default Color getColor() {return null;}
    public default void setY(int y) {}
    public default int getLive() {return 0;}
    public default void setLive(int live) {}
    public default String getName() {return null;}
    public default void setName(String name) {}
    public default int[][] makeMove(int x, int y){return null;}
    public default int[][] makeEat(int x, int y, int i){return null;}
    public default void dead(){}
    public default void isdead(){}
    public default boolean tipe(String ficha){return false;}
}
