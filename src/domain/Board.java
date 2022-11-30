package domain;

/**
 * Crea el tablero en el dominio
 */

public class Board {
    private String[][] board;
    private String[][] casillas;
    private String[][] fichas;
    private Checkers checkers;

    /**
     * Constructor de la clase checkers
     * @param checkers Logica del juego
     */
    public Board(Checkers checkers) {
        casillas = new String[10][10];
        this.checkers = checkers;
        casillas = checkers.generateMatrix();
    }
    
}


