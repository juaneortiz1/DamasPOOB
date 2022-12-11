package presentation;
import domain.Board;
import domain.Checkers;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

/**
 * Clase que genera el tablero con el que interactara para jugar
 */
public class Tablero extends JPanel {
    private final Casilla[][] casillas;
    private final Ficha[][] fichas;
    private final Checkers checkers;
    private final JPanel  espacioDePuntaje;
    private final JLabel  puntaje;
    private final DamasPOOBGUI damasPOOBGUI;
    private String jugador1, jugador2;
    private Ficha fichaSelect, casillaSelect;
    private boolean turno;
    private boolean decide;

    /**
     * Constructor de la clase Tablero
     * @param damasPOOBGUI Ventana del programa
     */
    public Tablero(DamasPOOBGUI damasPOOBGUI) {
        setBackground(new Color(29,114,162));
        turno = false;
        decide= true ;
        casillas = new Casilla[10][10];
        fichas = new Ficha[10][10];
        checkers = new Checkers();
        espacioDePuntaje = new JPanel();
        puntaje = new JLabel();
        this.damasPOOBGUI = damasPOOBGUI;
        prepararAcciones();
    }

    /**
     * Prepara las acciones a ejecutar de la clase
     */
    public void prepararAcciones(){
        prepareElementsBoard();
        makeMatriz();
        putNames();
        makePuntuacionActual();
    }

    private void putNames() {
        jugador1 = JOptionPane.showInputDialog("Ingresa nombre del Jugador 1");
        jugador2 = JOptionPane.showInputDialog("Ingresa nombre del Jugador 2");
    }

    /**
     * Metodo que escoge el ganador
     */
    private void chooseWinner() {
        Tablero panel = this;
        JPanel winner;
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
        ImageIcon forFicha = new ImageIcon("chinese-checkers (1).png");

            for (int i = 0; i < casillas.length; i++) {
                for (int j = 0; j < casillas[0].length; j++) {
                    CasillaNormal casilla = new CasillaNormal();
                    Ficha ficha = new Ficha(Color.RED, j, i);
                    ficha.setPreferredSize(new Dimension(45, 45));
                    ficha.setIcon(new ImageIcon(forFicha.getImage().getScaledInstance(50,50, Image.SCALE_SMOOTH)));
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
                        } else if (j < 6) {
                            ficha.changeColor(Color.BLACK);
                            fichas[i][j] = ficha;
                            ficha.setIcon(null);
                            prepareActions(ficha);
                        } else {
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
        Border blackline = BorderFactory.createLineBorder(Color.BLACK,10);
        panelmatriz.setLayout(new GridLayout(10, 10));
        for(int i = 0; i < casillas.length; i++){
            for(int j = 0; j < casillas[0].length; j++) {
                panelmatriz.add(casillas[j][i]);
            }
        }
        panelmatriz.setBorder(blackline);

        this.add(panelmatriz, BorderLayout.CENTER);

        panelmatriz.setPreferredSize(new Dimension(500, 500));
        add(panelmatriz);
        damasPOOBGUI.add(this);
    }
    public void makePuntuacionActual(){
        System.out.println("deberia morir");
        puntaje.setText(jugador1+"       "+Integer.toString(checkers.getBluecheckers())+"            "+ jugador2 +"       "+Integer.toString(checkers.getRedcheckers()));
        puntaje.setFont(new Font("Verdana", Font.PLAIN, 12));
        espacioDePuntaje.add(puntaje);
        add(espacioDePuntaje,BorderLayout.EAST);
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
                        fichas[i][j].changeColor(newColors[i][j]);
                        if(newColors[i][j] != Color.BLACK){
                            ImageIcon forFicha = new ImageIcon("chinese-checkers (1).png");
                            fichas[i][j].setIcon(new ImageIcon(forFicha.getImage().getScaledInstance(45,45, Image.SCALE_SMOOTH)));
                        }
                        else{
                            fichas[i][j].setIcon(null);
                        }
                    }
                }
            }
            makePuntuacionActual();
        }
        else {
            chooseWinner();
        }

    }
}
