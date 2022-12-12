package domain;

import javax.swing.*;
import java.awt.Color;
import java.util.*;

/**
 * Genera el juego de manera logica
 */
public class Checkers {
    public static int SIZE = 10;
    private final String[][] board;
    private final Ficha[][] fichas;
    private final Casilla[][] casillas;
    private final Casilla[] casillasEspeciales;
    private final Color[][] boardColor;
    private final Icon[][] iconBoard;
    private int redcheckers, bluecheckers, holderX, holderY, timerCasilla, timerFicha;
    private Color colorUp;

    /**
     * Metodo construtor de la clase checkers
     */
    public Checkers() {
        board = new String[SIZE][SIZE];
        boardColor = new Color[SIZE][SIZE];
        iconBoard = new Icon[SIZE][SIZE];
        fichas = new Ficha[SIZE][SIZE];
        casillas = new Casilla[SIZE][SIZE];
        casillasEspeciales = new Casilla[4];
        redcheckers = 20;
        bluecheckers = 20;
        generateMatrix();
    }
    public String[][] getBoard() {
        return board;
    }
    /**
     * Genera una matriz de caracteres
     * @return elementos de tipo char
     */
    public String[][] generateMatrix() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (((j % 2 == 0) && (i % 2 == 0)) || ((j % 2 == 1) && (i % 2 == 1))) {
                    board[i][j] = ".";
                } else {
                    FichaNormal ficha = new FichaNormal(i, j, "r", Color.red);
                    CasillaNormal casilla = new CasillaNormal(i, j, "_");
                    if (j < 4) {
                        fichas[i][j] = ficha;
                        board[i][j] = fichas[i][j].getName();
                    } else if (j < 6) {
                        casillas[i][j] = casilla;
                        board[i][j] = casillas[i][j].getName();
                    } else {
                        ficha.setName("b");
                        ficha.setColor(Color.blue);
                        fichas[i][j] = ficha;
                        board[i][j] = fichas[i][j].getName();
                    }
                }
            }
        }
        CasillaMine casilla = new CasillaMine(5, 4, "m");
        CasillaTeleport casilla1 = new CasillaTeleport(2, 5, "t");
        CasillaTeleport casilla2 = new CasillaTeleport(7,4,"t");
        CasillaJail casilla3 = new CasillaJail(3,4,"j");

        casillasEspeciales[0] = casilla;
        casillasEspeciales[1] = casilla3;
        casillasEspeciales[2] = casilla1;
        casillasEspeciales[3] = casilla2;

        for (Casilla casillasEspeciale : casillasEspeciales) {
            int minFila = 4;
            int maxFila = 5;
            int minCol = 0;
            int maxCol = 4;
            int[] coldeF4 = {1, 3, 5, 7, 9};
            int[] coldeF5 = {0, 2, 4, 6, 8};

            Random rand = new Random();


            int randomFila = rand.nextInt((maxFila - minFila) + 1) + minFila;

            if (randomFila == 4) {
                Random rand1 = new Random();
                int randomCol = rand1.nextInt((maxCol - minCol) + 1) + minCol;


                System.out.println(randomCol);
                System.out.println(randomFila);

                casillas[coldeF4[randomCol]][randomFila] = casillasEspeciale;
                casillas[coldeF4[randomCol]][randomFila].setName(casillasEspeciale.getName());
                casillas[coldeF4[randomCol]][randomFila].setX(coldeF4[randomCol]);
                casillas[coldeF4[randomCol]][randomFila].setY(randomFila);

                board[coldeF4[randomCol]][randomFila] = casillasEspeciale.getName();
            } else {
                Random rand1 = new Random();
                int randomCol = rand1.nextInt((maxCol - minCol) + 1) + minCol;


                System.out.println(randomCol);
                System.out.println(randomFila);

                casillas[coldeF5[randomCol]][randomFila] = casillasEspeciale;
                casillas[coldeF5[randomCol]][randomFila].setName(casillasEspeciale.getName());
                casillas[coldeF5[randomCol]][randomFila].setX(coldeF4[randomCol]);
                casillas[coldeF5[randomCol]][randomFila].setY(randomFila);

                board[coldeF5[randomCol]][randomFila] = casillasEspeciale.getName();
            }

        }

        return board;
    }
    /**
     * retorna numero de fichas del jugador 2 actuales
     */
    public int getBluecheckers() {
        return bluecheckers;
    }
    /**
     * retorna numero de fichas del jugador 1 actuales
     */
    public int getRedcheckers() {
        return redcheckers;
    }
    /**
     * imprime el tablero
     */
    public void printBoard() {
        int i, j;
        System.out.println("  0 1 2 3 4 5 6 7 8 9 X");
        for (i = 0; i < SIZE; i++) {
            System.out.print((i) + " ");
            for (j = 0; j < SIZE; j++) {
                System.out.print(board[j][i] + " ");
            }
            System.out.println();
        }
        System.out.println("y");
    }

    /**
     * Realiza el movimiento de las fichas
     *
     * @param x posicion x en el tablero del elemento
     * @param y posicion y en el tablero del elemento
     * @return matriz de numeros enteros
     */
    public int[][] goMove(int x, int y) {
        int[][] viewAll = fichas[x][y].makeMove(x, y);
        int[][] view = new int[4][2];
        for (int i = 0; i < viewAll.length; i++) {
            if ((viewAll[i][0] >= 0 && viewAll[i][0] < 10) && (viewAll[i][1] >= 0 && viewAll[i][1] < 10)) {
                if (board[viewAll[i][0]][viewAll[i][1]].equals("_")||board[viewAll[i][0]][viewAll[i][1]].equals("j")||board[viewAll[i][0]][viewAll[i][1]].equals("t")||board[viewAll[i][0]][viewAll[i][1]].equals("m")) {
                    view[i][0] = viewAll[i][0];
                    view[i][1] = viewAll[i][1];
                } else if (!fichas[x][y].tipe(board[viewAll[i][0]][viewAll[i][1]]) && (fichas[x][y].makeEat(x, y, i)[0][0] >= 0 && fichas[x][y].makeEat(x, y, i)[0][0] < 10) && (fichas[x][y].makeEat(x, y, i)[0][1] >= 0 && fichas[x][y].makeEat(x, y, i)[0][1] < 10) && !board[viewAll[i][0]][viewAll[i][1]].equals("_")) {
                    view[i][0] = fichas[x][y].makeEat(x, y, i)[0][0];
                    view[i][1] = fichas[x][y].makeEat(x, y, i)[0][1];
                } else {
                    view[i][0] = 0;
                    view[i][1] = 0;
                }
            }
        }
        return view;
    }

    /**
     * Convierte a una ficha en una ficha especial
     */
    public void addPower() {
        for (int i = 0; i < 10; i++) {
            if (board[i][0].equals("b")) {
                goPower( i,  0,   'b');
            }
            if (board[i][9].equals("r")) {
                goPower( i,  9,   'r');
            }
        }
    }

    /**
     * Permite escoger en que ficha especial se desea convertir la normal
     * @param x entero que representa la posicion en x de la fichas
     * @param y entero que representa la posicion en y de la fichas
     * @param bando caracter que representa el bando del que se cambiará la ficha
     */
    public void goPower(int x, int y, char bando) {
        String[] powers = {"reina","ninja","zombie"};
        var power = JOptionPane.showOptionDialog(null, "Select one:", "Let's play a game!",
                0, 3, null, powers, powers[0]);
        System.out.println(power);
        Ficha ficha = null;
        if (bando == 'r') {
            switch (power) {
                case 0: {ficha = new FichaReina(x, y, "qr");
                    break;
                }
                case 1: {ficha = new FichaNinja(x, y, "nr");
                    break;
                }
                case 2: {ficha = new FichaZombie(x, y, "zr");
                    break;
                }
            }
        } else {
            switch (power) {
                case 0: {ficha = new FichaReina(x, y, "qb");
                    break;
                }
                case 1: {ficha = new FichaNinja(x, y, "nb");
                    break;
                }
                case 2: {ficha = new FichaZombie(x, y, "zb");
                    break;
                }
            }
        }
        fichas[x][y] = ficha;
        assert ficha != null;
        board[x][y] = ficha.getName();
    }

    /**
     * Asigna que clase de comportamiento tendrá una ficha al posicionarse sobre una casilla
     * @param xfrom Posición de partida en x
     * @param yfrom Posición de partida en y
     * @param xto Posición de destino en x
     * @param yto Posición de destino en y
     */
    public void powerCasillas(int xfrom, int yfrom, int xto, int yto){
        switch (casillas[xto][yto].getName()) {
            case "m" : {
                CasillaMine casillaMine = new CasillaMine(xto, yto, "m");
                int[][] explodes = casillaMine.perimeter(xto, yto);
                for (int[] explode : explodes) {
                    if ((explode[0] < 10 && 0 <= (explode[0]) || (((explode[1] < 10 && 0 <= explode[1]))))) {
                        if (fichas[explode[0]][explode[1]] != null && !fichas[explode[0]][explode[1]].getName().equals("_")) {
                            redOrBlue(fichas[explode[0]][explode[1]],explode[0],explode[1]);
                        }
                    }
                }
                break;
            }
            case "t" : {
                CasillaNormal againNormal = new CasillaNormal(xto, yto, "_");
                casillas[xto][yto] = againNormal;
                board[xto][yto] = againNormal.getName();
                for (int i = 0; i < board.length; i++) {
                    for (int j = 0; j < board.length; j++) {
                        if (board[i][j].equals("t")) {
                            System.out.println(board[i][j]);
                            FichaNormal fichan = new FichaNormal(xfrom, yfrom, fichas[xfrom][yfrom].getName(), fichas[xfrom][yfrom].getColor());
                            fichas[i][j] = fichan;
                            FichaNormal fDead = new FichaNormal(xfrom, yfrom, "_", Color.BLACK);
                            fichas[xfrom][yfrom] = fDead;
                            System.out.println(i +" "+j);
                            board[xfrom][yfrom] = fichas[xfrom][yfrom].getName();
                            System.out.println(fichas[i][j].getName());
                            board[i][j] = fichas[i][j].getName();
                            System.out.println(board[i][j]);
                        }
                    }
                }
                break;
            }
            case "j" : {
                holderX = xto;
                holderY = yto;
                colorUp = fichas[xfrom][yfrom].getColor();
                fichas[xfrom][yfrom].setColor(Color.orange);
                timerCasilla = 4;
                break;
            }
        }
    }
    /**
     * Ejecuta el movimiento
     * @param xfrom Posicion en x del punto de partida
     * @param yfrom Posicion en y del punto de partida
     * @param xto   Posicion en x del punto de llegada
     * @param yto   Posicion en y del puntode llegada
     */

    public void executeMove(int xfrom, int yfrom, int xto, int yto) {
        if(casillas[xto][yto] != null){
            powerCasillas( xfrom, yfrom, xto,  yto);
        }
        Ficha ficha = fichas[xfrom][yfrom];
        fichas[xto][yto] = ficha;
        FichaNormal fDead = new FichaNormal(xfrom, yfrom, "_", Color.BLACK);
        fichas[xfrom][yfrom] = fDead;
        board[xfrom][yfrom] = fichas[xfrom][yfrom].getName();
        board[xto][yto] = fichas[xto][yto].getName();

        if (Math.abs(xto - xfrom) == 2) {
            if(fichas[(xfrom + xto) / 2][(yfrom + yto) / 2].getName().equals("zb") || fichas[(xfrom + xto) / 2][(yfrom + yto) / 2].getName().equals("zr")){
                holderX = (xfrom + xto) / 2;
                holderY = (yfrom + yto) / 2;
                timerFicha = 4;
            }
            redOrBlue(fichas[(xfrom + xto) / 2][(yfrom + yto) / 2],(int)((xfrom + xto) / 2),(int)((yfrom + yto) / 2));
        }
        timerCasilla--;
        timerFicha--;

        if (timerCasilla == 0) {
            fichas[holderX][holderY].setColor(colorUp);
        }

        if (timerFicha == 0) {
            if(board[holderX][holderY].equals("_")) {
                fichas[holderX][holderY].toRevive();
                board[holderX][holderY] = fichas[holderX][holderY].getName();
                bluecheckers++;
            }
        }

        colorMatriz();
        printBoard();
    }

    /**
     * Señala el turno de la ficha actual
     * @param ficha Objeto ficha
     * @param x entero que señala la posicion en x de la ficha
     * @param y entero que señala la posicion en y de la ficha
     */
    public void redOrBlue(Ficha ficha, int x, int y){
         if("r".equals(ficha.getName()) || "qr".equals(ficha.getName()) || "zr".equals(ficha.getName()) || "nr".equals(ficha.getName())) {
             redcheckers--;
         } else {
             bluecheckers--;
         }
        fichas[x][y].isdead();
        board[x][y] = fichas[x][y].getName();
    }
    /**
     * fabrica la matriz que representa el tablero de forma logica
     */
    public void colorMatriz() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (fichas[i][j] != null) {
                    boardColor[i][j] = fichas[i][j].getColor();
                } else if (casillas[i][j] != null) {
                    boardColor[i][j] = casillas[i][j].getColor();
                }
            }
        }
    }

    /**
     * Retorna una matriz de colores
     *
     * @return Matriz de elementos tipo color
     */
    public Color[][] getColorMatriz() {
        return boardColor;
    }
    public Icon[][] getIconMatriz() {
        return iconBoard;
    }






}