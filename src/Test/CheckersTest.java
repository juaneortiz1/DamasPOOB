package Test;

import domain.Checkers;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CheckersTest {
    @Test
    void generateMatrix() {
        Checkers checkers = new Checkers();
        ArrayList<String> casillas = new ArrayList<String>();
        String[] staticList ={"r","b","_",".","t","m","j"};
        casillas.addAll(List.of(staticList));
        for(int i = 0; i < Checkers.SIZE; i++){
            for(int j = 0; j < Checkers.SIZE; j++){
                if(!casillas.contains(checkers.getBoard()[i][j])){
                    fail("Petó");
                }
            }
        }
    }

    @Test
    void getBluecheckers() {
        Checkers checkers = new Checkers();
        assertEquals(checkers.getBluecheckers(),20);
    }

    @Test
    void getRedcheckers() {
        Checkers checkers = new Checkers();
        assertEquals(checkers.getRedcheckers(),20);
    }


    @Test
    void goMove() {
    }
    @Test
    void addPower() {
    }

    @Test
    void goPower() {
    }

    @Test
    void executeMoveT() {
        Checkers checkers = new Checkers();
        checkers.getBoard()[2][5] = "t";
        checkers.executeMove(1,6,2,5);
        assertEquals(checkers.getBoard()[7][4],"b");
    }
    @Test
    void executeMoveJ() {
        Checkers checkers = new Checkers();
        checkers.getBoard()[3][4] = "j";
        checkers.executeMove(2,3,3,4);
        assertEquals(checkers.getBoard()[3][4],  "jr");
        checkers.executeMove(1,0,1,0);
        checkers.executeMove(1,0,1,0);
        checkers.executeMove(1,0,1,0);
        assertEquals(checkers.getBoard()[3][4],"r");
    }
    @Test
    void executeMoveM() {
        Checkers checkers = new Checkers();
        checkers.getBoard()[5][4] = "m";
        checkers.executeMove(6,3,5,4);
        assertEquals(checkers.getBoard()[5][2],"_");
        assertEquals(checkers.getBoard()[4][3],"_");
        assertEquals(checkers.getBoard()[6][3],"_");
        assertEquals(checkers.getBoard()[5][4],"_");
        assertEquals(checkers.getBoard()[5][6],"_");
        assertEquals(checkers.getBluecheckers(),19);
        assertEquals(checkers.getRedcheckers(),17);
    }

    @Test
    void colorMatriz() {
        Checkers checkers = new Checkers();
        ArrayList<String> casillas = new ArrayList<String>();
        String[] staticList ={"r","b","_",".","t","m","j"};
        casillas.addAll(List.of(staticList));
        String[][] board = checkers.getBoard();
        Color[][] boardColor = checkers.getColorMatriz();
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j].equals("r")){
                    boardColor[i][j] = Color.RED;
                } else if (board[i][j].equals("_")) {
                    boardColor[i][j] = Color.BLACK;
                } else if(board[i][j].equals("b")){
                    boardColor[i][j] = Color.BLUE;
                }
            }
        }
        for(int i = 0; i < Checkers.SIZE; i++){
            for(int j = 0; j < Checkers.SIZE; j++){
                if(board[i][j].equals("r")){
                    assertEquals(boardColor[i][j],Color.RED);
                } else if (board[i][j].equals("_")) {
                    assertEquals(boardColor[i][j],Color.BLACK);
                } else if(board[i][j].equals("b")){
                    assertEquals(boardColor[i][j],Color.BLUE);
                }
            }
        }
    }
}