package Test;

import domain.Checkers;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CheckersTest {
// {"r","b","_",".","t","m","j"}
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
    void getNextMove() {

    }

    @Test
    void goMove() {
    }

    @Test
    void eat() {
    }

    @Test
    void goMoveQueen() {
    }

    @Test
    void addPower() {
    }

    @Test
    void goPower() {
    }

    @Test
    void executeMoveM() {
        Checkers checkers = new Checkers();
        checkers.getBoard()[2][5] = "t";
        checkers.executeMove(1,6,2,5);
        assertEquals(checkers.getBoard()[7][4],"b");
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