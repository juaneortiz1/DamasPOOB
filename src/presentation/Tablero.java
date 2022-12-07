package presentation;
import domain.Board;
import domain.Checkers;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase que genera el tablero con el que interactara para jugar
 */
public class    Tablero extends JPanel {
    private Casilla[][] casillas;
    private Ficha[][] fichas;
    private Checkers checkers;
    private DamasPOOBGUI damasPOOBGUI;
    private Ficha fichaSelect, casillaSelect;
    private boolean turno;
    private boolean decide;
    private JPanel winner;

    /**
     * Constructor de la clase Tablero
     * @param damasPOOBGUI Ventana del programa
     */
    public Tablero(DamasPOOBGUI damasPOOBGUI) {
        turno = false;
        decide= true ;
        casillas = new Casilla[10][10];
        fichas = new Ficha[10][10];
        checkers = new Checkers();
        this.damasPOOBGUI = damasPOOBGUI;
        prepararAcciones();
    }

    /**
     * Prepara las acciones a ejecutar de la clase
     */
    public void prepararAcciones(){
        prepareElementsBoard();
        makeMatriz();


    }

    /**
     * Metodo que escoge el ganador
     */
    private void chooseWinner() {
        Tablero panel = this;
        if(checkers.getBluecheckers() == 0){
            winner = new JPanel();
            winner.setBackground(Color.RED);
            panel.setVisible(false);
            damasPOOBGUI.remove(panel);
            damasPOOBGUI.add(winner);
            winner.setVisible(true);

        }
        else {
            winner = new JPanel();
            winner.setBackground(Color.BLUE);
            panel.setVisible(false);
            damasPOOBGUI.remove(panel);
            damasPOOBGUI.add(winner);
            winner.setVisible(true);
        }
    }

    /**
     * Metodo que prepara los elementos de la clase
     */
    private void prepareElementsBoard() {

            for (int i = 0; i < casillas.length; i++) {
                for (int j = 0; j < casillas[0].length; j++) {
                    CasillaNormal casilla = new CasillaNormal();
                    Ficha ficha = new Ficha(Color.RED, j, i);
                    ficha.desacive();
                    if (((j % 2 == 0) && (i % 2 == 0)) || ((j % 2 == 1) && (i % 2 == 1))) {
                        casilla.setColor(Color.WHITE);
                        casillas[i][j] = casilla;
                    } else {
                        casilla.setColor(Color.BLACK);
                        casillas[i][j] = casilla;
                        if (j < 4) {
                            if (turno) {
                                ficha.acive();
                            }
                            fichas[i][j] = ficha;
                            prepareActions(fichas[i][j]);
                        } else if (j > 3 && j < 6) {
                            ficha.changeColor(Color.BLACK);
                            fichas[i][j] = ficha;
                            prepareActions(ficha);
                        } else if (j > 5) {
                            if (!turno) {
                                ficha.acive();
                            }
                            ficha.changeColor(Color.BLUE);
                            fichas[i][j] = ficha;
                            prepareActions(fichas[i][j]);
                        }
                        casillas[i][j].add(fichas[i][j]);
                    }
                }
            }
        }

    /**
     * Recibe una ficha como parametro para determinar su siguiente accion
     * @param ficha Elemento tipo ficha
     */
    private void prepareActions(Ficha ficha) {
            ficha.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    reservarFicha(ficha);
                }
            });
    }

    /**
     * Determina el turno actual
     */
    private void escogerTurno(){
        for (int i = 0; i < casillas.length; i++) {
            for (int j = 0; j < casillas[0].length; j++) {
                if(fichas[i][j] != null) {
                    fichas[i][j].desacive();
                }
            }
        }
        turno = !turno;
        active(turno);
    }

    /**
     * Activa las fichas del turno actual y los espacios posibles para moverse
     * @param player boolean
     */
    public void  active(boolean player){
        for (int i = 0; i < casillas.length; i++) {
            for (int j = 0; j < casillas[0].length; j++) {
                if(fichas[i][j] != null) {
                    if(player && fichas[i][j].getColor() == Color.RED){
                        fichas[i][j].acive();
                    }
                    if(!player && fichas[i][j].getColor() == Color.BLUE){
                        fichas[i][j].acive();
                    }
                }
            }
        }
    }

    /**
     * Fabrica una matriz de casillas
     */
    public void makeMatriz(){
        JPanel panelmatriz = new JPanel();
        panelmatriz.setLayout(new GridLayout(10, 10));
        for(int i = 0; i < casillas.length; i++){
            for(int j = 0; j < casillas[0].length; j++) {
                panelmatriz.add(casillas[j][i]);
            }
        }
        this.add(panelmatriz, BorderLayout.CENTER);
        panelmatriz.setPreferredSize(new Dimension(350, 300));
        add(panelmatriz);
        damasPOOBGUI.add(this);
    }

    /**
     *
     * @param ficha
     */
    public void reservarFicha(Ficha ficha){
        System.out.println(ficha.getColor());
        if(fichaSelect == null){
            int[][] vewAl;
            fichaSelect = ficha;
            vewAl = checkers.goMove(ficha.getX(),ficha.getY());
                for(int i = 0; i < vewAl.length; i++){
                    if(fichas[vewAl[i][0]][vewAl[i][1]] != null) {
                        fichas[vewAl[i][0]][vewAl[i][1]].acive();
                    }
                }
        }else{
            casillaSelect = ficha;
            move(fichaSelect, casillaSelect);
            checkers.addPower();
        }
    }

    /**
     * Mueve una ficha a un espacio vacio
     * @param ficha
     * @param casilla
     */
    public void move(Ficha ficha, Ficha casilla){
        if(ficha != null && casilla != null){
            checkers.executeMove(ficha.getX(), ficha.getY(), casilla.getX(), casilla.getY());
            paint();
            fichaSelect = null;
            casillaSelect = null;
        }
        escogerTurno();
    }

    /**
     * Pinta la matriz despues de cada accion
     */
    public void paint() {
        if(checkers.getBluecheckers() == 0 || checkers.getRedcheckers() == 0){
            decide = false;

        }
        if (decide) {
            Color[][] newColors = checkers.getColorMatriz();
            for (int i = 0; i < casillas.length; i++) {
                for (int j = 0; j < casillas[0].length; j++) {
                    if (fichas[i][j] != null) {
                        if (newColors[i][j] == Color.YELLOW ) {
                            Ficha reina = new FichaReina(i,j);
                            reina.changeColor(Color.YELLOW);
                            fichas[i][j] = reina;
                        } else{
                            fichas[i][j].changeColor(newColors[i][j]);
                        }
                    }
                }
            }
        }
        else {
            chooseWinner();
        }

    }














}
