package domain;

/**
 * Crea el tablero en el dominio
 */

public class Board {
    private char[][] board;
    private char[][] casillas;
    private char[][] fichas;
    private Checkers checkers;

    /**
     * Constructor de la clase checkers
     * @param checkers Logica del juego
     */
    public Board(Checkers checkers) {
        casillas = new char[10][10];
        this.checkers = checkers;
        casillas = checkers.generateMatrix();
    }
    
}


