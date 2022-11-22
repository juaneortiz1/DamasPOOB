package domain;

public class Board {
    private char[][] board; // Stores the checkerboard, with chars 'r','b','_'
    private char[][] casillas;
    private char[][] fichas;
    private Checkers checkers;

    public Board(Checkers checkers) {
        casillas = new char[10][10];
        this.checkers = checkers;
        casillas = checkers.generateMatrix();
    }
    
}


