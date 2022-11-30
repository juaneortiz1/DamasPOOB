package domain;

import java.awt.*;
import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;

/**
 * Genera el juego de manera logica
 */
public class Checkers {

    private final static int SIZE = 10;
    private String[][] board;

    private Color[][] boardColor;
    private int redcheckers;
    private int bluecheckers;
    private char turno;
    /**
     * Metodo construtor de la clase checkers
    */
    public Checkers() {

        board = new String[SIZE][SIZE];
        boardColor = new Color[SIZE][SIZE];
        redcheckers = 20;
        bluecheckers = 20;
        turno = 'r';

        generateMatrix();
    }
    /**
     * Genera una matriz de caracteres
     * @return elementos de tipo char
     */
    public String[][] generateMatrix(){
        for (int i=0;i<SIZE;i++) {
            for (int j = 0; j < SIZE; j++) {
                if (((j % 2 == 0) && (i % 2 == 0)) || ((j % 2 == 1) && (i % 2 == 1))) {
                    board[i][j] = ".";
                }else{
                    if (j < 4) {
                        board[i][j] = "r";
                    } else if (j>3 && j < 6) {
                        board[i][j] = "_";
                    } else if (j > 5) {
                        board[i][j] = "b";
                    }
                }
            }
        }
        return  board;
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
        int i,j;
        System.out.println("  0 1 2 3 4 5 6 7 8 9 X");
        for (i=0;i<SIZE;i++) {
            System.out.print((i+1) + " ");
            for (j=0;j<SIZE;j++) {
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

        if (turno =='r')
            System.out.println("It is your turn, red.");
        else
            System.out.println("It is your turn, blue.");

        boolean moved = false;
        while (!moved) {
            int xfrom = stdin.nextInt();
            int yfrom = stdin.nextInt();

            int xto = stdin.nextInt();
            int yto = stdin.nextInt();
            validMove( xfrom, yfrom,  xto,  yto);
        }

        if (turno == 'r')
            turno = 'b';
        else
            turno = 'r';
    }
    /**
     * Realiza el movimiento de las fichas
     * @param x posicion x en el tablero del elemento
     * @param y posicion y en el tablero del elemento
     * @return matriz de numeros enteros
     */
    public int[][] goMove(int x, int y) {
        int vewAl[][] = new int[4][4];
        int select = board[x][y] == "r" ? 1 : -1;
        if(board[x][y] == "qr" || board[x][y] == "br"){
            return  goMoveQueen( x,  y) ;
        }

        if(y+1 < 10 && select + y >= 0) {
            if (x - 1 >= 0) {
                if (board[x - 1][select + y] == "_") {
                    vewAl[0][0] = x - 1;
                    vewAl[0][1] = select + y;
                }
            }
            if (x + 1 < 10) {
                if (board[x + 1][select + y] == "_") {
                    vewAl[1][0] = x + 1;
                    vewAl[1][1] = select + y;
                }
            }
        }
        vewAl = eat(x, y, vewAl, select);
        return vewAl;
    }

    public int[][] eat(int x, int y, int[][] vewAl, int select){
        select = select == 1 ? 2  : -2;
        if(select + y < 10 && select + y >= 0) {
            if (x - 2 >= 0) {
                if (board[x - 2][select + y] == "_") {
                    vewAl[2][0] = x - 2;
                    vewAl[2][1] = select + y;
                }
            }
            if (x + 2 < 10) {
                if (board[x + 2][select + y] == "_") {
                    vewAl[3][0] = x + 2;
                    vewAl[3][1] = select + y;
                }
            }
        }
        return vewAl;
    }
    public int[][] goMoveQueen(int x, int y) {
        ArrayList<Integer> pochita = new ArrayList<>();
        int vewAl[][] = new int[4][4];
        System.out.println(x + " a " + y);
        for(int i = x-1; i < x+1; i+=2){
            for(int j = y-1; j < x+1; j+=2){
                if(i >= 0 && i < 10 && j >= 0 && j < 10){
                    if(board[i][j] == "_"){
                        pochita.add(i);
                        pochita.add(j);
                    }
                }
            }
        }
        for(int i = x-2; i < x+2; i+=4){
            for(int j = y-2; j < x+2; j+=4){
                if(i >= 0 && i < 10 && j >= 0 && j < 10){
                    if(board[i][j] == "_"){
                        pochita.add(i);
                        pochita.add(j);
                    }
                }
            }
        }
        int w = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                vewAl[i][j] = pochita.get(w);
                w += 1;
            }
        }
        return vewAl;
    }
    public void addPower(){
        for(int i = 0; i < 10 ; i++) {
            if (board[i][0] == "b") {
                goPower( i,  0, "reina",  'b');
            }
            if (board[i][9] == "r") {
                goPower( i,  9, "reina",  'r');
            }
        }
    }
    public void goPower(int x, int y, String power, char bando) {
        if(bando == 'r') {
            if (power == "reina") {
                board[x][y] = "qr";
            }
        }
        else{
            if (power == "reina") {
                board[x][y] = "qb";
            }
        }
    }
    /**
     * Valida si un movieminto si es valido
     * @param xfrom posicion en x de origen
     * @param yfrom posicion en y de origen
     * @param xto posicion en x de destino
     * @param yto posicion en y de destino
     */
    public void validMove(int xfrom, int yfrom, int xto, int yto) {
        Boolean isValid;
        boolean moved = false;

        System.out.println(xfrom);
        System.out.println(yfrom);
        System.out.println(xto);
        System.out.println(yto);
        if (xfrom < 0 || xfrom > 9 || yfrom < 0 || yfrom > 9 ||
                xto < 0 || xto > 9 || yto < 0 || yto > 9){
            isValid = false;
        }




            if (Math.abs(xfrom-xto)==1) {
                if ((turno == 'r') && (yto - yfrom == 1))
                    isValid =  true;
                else if ((turno == 'b') && (yto - yfrom == -1))
                    isValid = true;
            }

            else if (Math.abs(xfrom-xto)==2) {
                if (turno == 'r' && (yto - yfrom == 2) &&
                        board[(xfrom+xto)/2][(yfrom+yto)/2] == "b")
                    isValid =  true;
                else if (turno == 'b' && (yto - yfrom == -2) &&
                        board[(xfrom+xto)/2][(yfrom+yto)/2] == "r")
                    isValid = true;
            }


        if(isValid = true){
            executeMove( xfrom, yfrom, xto, yto);
            moved = true;
        }
    }

    /**
     * Ejecuta el movimiento
     * @param xfrom Posicion en x del punto de partida
     * @param yfrom Posicion en y del punto de partida
     * @param xto Posicion en x del punto de llegada
     * @param yto Posicion en y del puntode llegada
     */

    public void executeMove(int xfrom, int yfrom, int xto, int yto) {
        String comodin = board[xfrom][yfrom];
        board[xfrom][yfrom] = board[xto][yto];
        board[xto][yto] = comodin;

        if (Math.abs(xto - xfrom) == 2) {
            board[(xfrom + xto) / 2][(yfrom + yto) / 2] = "_";
            if (turno == 'r') {
                System.out.println("aqui");
                bluecheckers--;
            } else {
                System.out.println("aqui");
                redcheckers--;
            }
        }
        colorMatriz();
        printBoard();
    }

    /**
     * fabrica la matriz que representa el tablero de forma logica
     */
    public void colorMatriz(){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j] == "r"){
                    boardColor[i][j] = Color.RED;
                } else if (board[i][j] == "_") {
                    boardColor[i][j] = Color.BLACK;
                } else if(board[i][j] == "b"){
                    boardColor[i][j] = Color.BLUE;
                }
            }
        }
        board[5][2] = "t";
    }

    /**
     * Retorna una matriz de colores
     * @return Matriz de elementos tipo color
     */
    public Color[][] getColorMatriz(){
        return boardColor;
    }

    /**
     * Determina si el juego ya ha acabado
     * @return boolean
     */
    public boolean gameOver() {
        return (redcheckers == 0 || bluecheckers == 0);
    }

    /**
     * determina quien es el ganador
     * @return
     */
    public String winnerIs() {
        if (bluecheckers == 0)
            return "red";
        else
            return "black";
    }

    public static void main(String args[]) throws IOException {

        Checkers game = new Checkers();
        game.printBoard();

        while (!game.gameOver()) {
            game.getNextMove();
            game.printBoard();
        }
        System.out.println("The winner is " + game.winnerIs());
    }
}