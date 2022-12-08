package domain;

import java.awt.Color;
import java.io.IOException;
import java.util.*;

import static java.lang.Integer.parseInt;

/**
 * Genera el juego de manera logica
 */
public class Checkers {
    public static int SIZE = 10;
    private final String[][] board;
    private final Ficha[][] fichas;
    private final Casilla[][] casillas;
    private final Color[][] boardColor;
    private int redcheckers, bluecheckers, holderX, holderY, timerCasilla, timerFicha;
    private char turno;
    private Color colorUp;

    /**
     * Metodo construtor de la clase checkers
     */
    public Checkers() {
        board = new String[SIZE][SIZE];
        boardColor = new Color[SIZE][SIZE];
        fichas = new Ficha[SIZE][SIZE];
        casillas = new Casilla[SIZE][SIZE];
        redcheckers = 20;
        bluecheckers = 20;
        turno = 'r';
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
        casillas[5][4] = casilla;
        casillas[2][5] = casilla1;
        casillas[9][4] = casilla2;
        casillas[3][4] = casilla3;
        board[5][4] = casilla.getName();
        board[2][5] = casilla1.getName();
        board[9][4] = casilla2.getName();
        board[3][4] = casilla3.getName();
        System.out.println(casilla1.getColor());
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
     * Determina el siguiente movimeinto
     */
    public void getNextMove() throws IOException {
        Scanner stdin = new Scanner(System.in);
        boolean moved = false;
        while(!moved) {
            int xfrom = stdin.nextInt();
            int yfrom = stdin.nextInt();

            int xto = stdin.nextInt();
            int yto = stdin.nextInt();
            executeMove(xfrom, yfrom, xto, yto);
        }
        if (turno == 'r') {
            turno = 'b';
        }else {
            turno = 'r';
        }
    }
    /**
     * Reali
     * za el movimiento de las fichas
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


    public void addPower() {

        for (int i = 0; i < 10; i++) {
            if (board[i][0].equals("b")) {
                //goPower(i, 0, "reina", 'b');
                //goPower( i,  0, "ninja",  'b');
                goPower( i,  0, "zombie",  'b');
            }
            if (board[i][9].equals("r")) {
                //goPower(i, 9, "reina", 'r');
                //goPower( i,  9, "ninja",  'r');
                goPower( i,  9, "zombie",  'r');
            }
        }
    }
    public void goPower(int x, int y, String power, char bando) {
        System.out.println(power);
        Ficha ficha = null;
        if (bando == 'r') {
            switch (power) {
                case "reina": {ficha = new FichaReina(x, y, "qr");
                    break;
                }
                case "ninja": {ficha = new FichaNinja(x, y, "nr");
                    break;
                }
                case "zombie": {ficha = new FichaZombie(x, y, "zr");
                    break;
                }
            }
        } else {
            switch (power) {
                case "reina": {ficha = new FichaReina(x, y, "qb");
                    break;
                }
                case "ninja": {ficha = new FichaNinja(x, y, "nb");
                    break;
                }
                case "zombie": {ficha = new FichaZombie(x, y, "zb");
                    break;
                }
            }
        }
        fichas[x][y] = ficha;
        assert ficha != null;
        board[x][y] = ficha.getName();
    }
    public void powerCasillas(int xfrom, int yfrom, int xto, int yto){
        switch (casillas[xto][yto].getName()) {
            case "m" : {
                CasillaMine casillaMine = new CasillaMine(xto, yto, "m");
                int[][] explodes = casillaMine.perimeter(xto, yto);
                for (int[] explode : explodes) {
                    if ((explode[0] < 10 && 0 <= (explode[0]) || (((explode[1] < 10 && 0 <= explode[1]))))) {
                        if (fichas[explode[0]][explode[1]] != null && !fichas[explode[0]][explode[1]].getName().equals("_")) {
                            fichas[explode[0]][explode[1]].isdead();
                            board[explode[0]][explode[1]] = fichas[explode[0]][explode[1]].getName();
                        }
                    }
                }
            }
            case "t" : {
                CasillaNormal againNormal = new CasillaNormal(xto, yto, "_");
                casillas[xto][yto] = againNormal;
                board[xto][yto] = againNormal.getName();
                for (int i = 0; i < board.length; i++) {
                    for (int j = 0; j < board.length; j++) {
                        if (board[i][j].equals("t")) {
                            Ficha ficha = fichas[xfrom][yfrom];
                            fichas[i][j] = ficha;
                            FichaNormal fDead = new FichaNormal(xfrom, yfrom, "_", Color.BLACK);
                            fichas[xfrom][yfrom] = fDead;
                            board[xfrom][yfrom] = fichas[xfrom][yfrom].getName();
                            board[i][i] = fichas[i][j].getName();
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
            fichas[(xfrom + xto) / 2][(yfrom + yto) / 2].isdead();
            board[(xfrom + xto) / 2][(yfrom + yto) / 2] = fichas[(xfrom + xto) / 2][(yfrom + yto) / 2].getName();
            System.out.println(fichas[(xfrom + xto) / 2][(yfrom + yto) / 2].getName());
            if (redOrBlue(fichas[(xfrom + xto) / 2][(yfrom + yto) / 2])) {
                redcheckers--;
            } else {
                bluecheckers--;
            }
        }
        timerCasilla--;
        timerFicha--;
        System.out.println(timerFicha);
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

    public boolean redOrBlue(Ficha ficha){
        return "r".equals(ficha.getName()) || "qr".equals(ficha.getName()) || "zr".equals(ficha.getName()) || "nr".equals(ficha.getName());
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

    /**
     * Determina si el juego ya ha acabado
     *
     * @return boolean
     */
    public boolean gameOver() {
        return (redcheckers == 0 || bluecheckers == 0);
    }

    /**
     * determina quien es el ganador
     * @return winer
     */
    public String winnerIs() {
        return (bluecheckers == 0) ? "red" : "black";
    }

    public static void main() throws IOException {
        Checkers game = new Checkers();
        game.printBoard();
        while (!game.gameOver()) {
            game.getNextMove();
            game.printBoard();
        }
        System.out.println("The winner is " + game.winnerIs());
    }
}