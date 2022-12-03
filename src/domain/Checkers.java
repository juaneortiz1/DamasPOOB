package domain;

import java.awt.*;
import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;

/**
 * Genera el juego de manera logica
 */
public class Checkers {

    public  static int SIZE = 10;

    private String[][] board;

    private Color[][] boardColor;
    private int redcheckers,bluecheckers,holderX,holderY,timer;

    private char turno;
    private String holder;

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
    public String[][] getBoard() {
        return board;
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
        board[2][5] = "t";
        board[7][4] = "t" ;
        board[5][4] = "m";
        board[3][4] = "j";
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
     * Reali
     *
     * za el movimiento de las fichas
     * @param x posicion x en el tablero del elemento
     * @param y posicion y en el tablero del elemento
     * @return matriz de numeros enteros
     */
    public int[][] goMove(int x, int y) {
        int viewAll[][] = new int[8][2];
        int vectorX[] = {-1,1,-1,1};
        int vectorY[] = {-1,-1,1,1};
        int in = board[x][y].equals("r") ? 2 : 0, en = board[x][y] == "b" ? 2 : 4;
        String siguiente;
        siguiente = board[x][y].equals("r") || board[x][y].equals("qr") ? "b" : "r";
        siguiente = board[x][y].equals("b") || board[x][y].equals("qb") ? "r" : "b";
        for (int i = in; i < en; i++) {
            if (vectorX[i] + x >= 0 && vectorX[i] + x < 10 && vectorY[i] + y >= 0 && vectorY[i] + y < 10) {
                viewAll[i][0] = !board[vectorX[i] + x][vectorY[i] + y].equals(board[x][y]) && !board[vectorX[i] + x][vectorY[i] + y].equals(siguiente) ? x + vectorX[i] : 0;
                viewAll[i][1] = !board[vectorX[i] + x][vectorY[i] + y].equals(board[x][y]) && !board[vectorX[i] + x][vectorY[i] + y].equals(siguiente) ? y + vectorY[i] : 0;
                if (viewAll[i][0] == 0 && viewAll[i][1] == 0 && vectorX[i] * 2 + x >= 0 && vectorX[i] * 2 + x < 10 && vectorY[i] * 2 + y >= 0 && vectorY[i] * 2 + y < 10) {
                    viewAll[i][0] = board[vectorX[i] + x][vectorY[i] + y].equals(siguiente) && board[vectorX[i] * 2 + x][vectorY[i] * 2 + y].equals("_") ? x + vectorX[i] * 2 : 0;
                    viewAll[i][1] = board[vectorX[i] + x][vectorY[i] + y].equals(siguiente) && board[vectorX[i] * 2 + x][vectorY[i] * 2 + y].equals("_") ? y + vectorY[i] * 2 : 0;
                }
            }
        }
        return viewAll;
    }

    public void addPower(){
        for(int i = 0; i < 10 ; i++) {
            if (board[i][0].equals("b")) {
                goPower( i,  0, "reina",  'b');
            }
            if (board[i][9].equals("r")) {
                goPower( i,  9, "reina",  'r');
            }
        }
    }
    public void goPower(int x, int y, String power, char bando) {
        if(bando == 'r') {
            if (power.equals("reina")) {
                board[x][y] = "qr";
            }
        }
        else{
            if (power.equals("reina")) {
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
                        board[(xfrom + xto) / 2][(yfrom + yto) / 2].equals("b"))
                    isValid =  true;
                else if (turno == 'b' && (yto - yfrom == -2) &&
                        board[(xfrom + xto) / 2][(yfrom + yto) / 2].equals("r"))
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
        if((board[xto][yto] == "m")){
            if(board[xto][yto-2] == "r" || board[xto][yto-2] == "b"){
                if(board[xto][yto-2] == "r"){
                    board[xto][yto-2] = "_";
                    redcheckers--;
                }
                else{
                    board[xto][yto-2] = "_";
                    bluecheckers--;
                }
            }
            if(board[xto - 1][yto - 1] == "r" || board[xto - 1][yto - 1] == "b"){
                if(board[xto - 1][yto - 1] == "r"){
                    board[xto - 1][yto - 1] = "_";
                    redcheckers--;
                }
                else{
                    board[xto - 1][yto - 1] = "_";
                    bluecheckers--;
                }
            }
            if(board[xto - 1][yto + 1] == "r" || board[xto - 1][yto + 1] == "b"){
                if(board[xto - 1][yto + 1] == "r"){
                    board[xto - 1][yto + 1] = "_";
                    redcheckers--;
                }
                else{
                    board[xto - 1][yto + 1] = "_";
                    bluecheckers--;
                }
            }
            if(board[xto - 2][yto] == "r" || board[xto - 2][yto] == "b"){
                if(board[xto - 2][yto] == "r"){
                    board[xto - 2][yto] = "_";
                    redcheckers--;
                }
                else{
                    board[xto - 2][yto] = "_";
                    bluecheckers--;
                }
            }
            if(board[xto + 2][yto] == "r" || board[xto + 2][yto] == "b"){
                if(board[xto + 2][yto] == "r"){
                    board[xto + 2][yto] = "_";
                    redcheckers--;
                }
                else{
                    board[xto + 2][yto] = "_";
                    bluecheckers--;
                }
            }
            if(board[xto - 1][yto + 1] == "r" || board[xto - 1][yto + 1] == "b"){
                if(board[xto - 1][yto + 1] == "r"){
                    board[xto - 1][yto + 1] = "_";
                    redcheckers--;
                }
                else{
                    board[xto - 1][yto + 1] = "_";
                    bluecheckers--;
                }
            }
            if(board[xto + 1][yto + 1] == "r" || board[xto + 1][yto + 1] == "b"){
                if(board[xto + 1][yto + 1] == "r"){
                    board[xto + 1][yto + 1] = "_";
                    redcheckers--;
                }
                else{
                    board[xto + 1][yto + 1] = "_";
                    bluecheckers--;
                }
            }
            if(board[xto][yto + 2] == "r" || board[xto][yto + 2] == "b"){
                if(board[xto][yto + 2] == "r"){
                    board[xto][yto + 2] = "_";
                    redcheckers--;
                }
                else{
                    board[xto][yto + 2] = "_";
                    bluecheckers--;
                }
            }
            if(board[xfrom][yfrom] == "r" ){
                redcheckers--;
            }
            else{
                bluecheckers--;
            }
            board[xto][yto] = "_";


        }else if((board[xfrom][yfrom] == "b" || board[xfrom][yfrom] == "r") && board[xto][yto] == "t") {
            if((board[xfrom][yfrom] == "b")){
                board[xto][yto] = "_";
                board[xfrom][yfrom] = "_";
                for(int i = 0; i < board.length; i++){
                    for(int j = 0; j < board[i].length; j++){
                        if(board[i][j] == "t"){
                            board[i][j] = "b";
                        }
                    }
                }
            }
            else {
                board[xto][yto] = "_";
                board[xfrom][yfrom] = "_";
                for(int i = 0; i < board.length; i++){
                    for(int j = 0; j < board[i].length; j++){
                        if(board[i][j] == "t"){
                            board[i][j] = "r";
                        }
                    }
                }

            }
        }
        else if((board[xfrom][yfrom] == "b" || board[xfrom][yfrom] == "r") && board[xto][yto] == "j"){
            holder = board[xfrom][yfrom];
            holderX = xto;
            holderY = yto;
            board[xfrom][yfrom] = "_";
            board[xto][yto] = "jr";
            timer = 4;

        }

        else {
            String comodin = board[xfrom][yfrom];
            board[xfrom][yfrom] = board[xto][yto];
            board[xto][yto] = comodin;
            }
            if (Math.abs(xto - xfrom) == 2) {
                board[(xfrom + xto) / 2][(yfrom + yto) / 2] = "_";
                if (turno == 'r') {
                    bluecheckers--;
                } else {
                    redcheckers--;
                }
            }
            timer--;
            if(timer == 0){
                board[holderX][holderY] = holder;
            }
            System.out.println(timer);
            colorMatriz();
            printBoard();

    }

    /**
     * fabrica la matriz que representa el tablero de forma logica
     */
    public void colorMatriz(){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j].equals("r")){
                    boardColor[i][j] = Color.RED;
                }if (board[i][j].equals("_")) {
                    boardColor[i][j] = Color.BLACK;
                }if(board[i][j].equals("b")){
                    boardColor[i][j] = Color.BLUE;
                }if(board[i][j].equals("jr")){
                    boardColor[i][j] = Color.CYAN;
                }if(board[i][j].equals("m")){
                    boardColor[i][j] = Color.GREEN;
                }if(board[i][j].equals("t")){
                    boardColor[i][j] = Color.magenta;
                }if(board[i][j].equals("j")){
                    boardColor[i][j] = Color.PINK;
                }if(board[i][j].equals("qb")){
                    boardColor[i][j] = Color.BLUE;
                }if(board[i][j].equals("qr")){
                    boardColor[i][j] = Color.RED;
                }
            }
        }

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