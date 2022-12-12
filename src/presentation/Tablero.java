package presentation;
import domain.Checkers;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;


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
        String[] options ={"Nuevo Juego?","No,Gracias"};
        if(checkers.getBluecheckers() == 0){
            var option = JOptionPane.showOptionDialog(null, "Ganaste " + jugador2+"!", "Felicidades",
                    0, 3, null, options, options[0]);
            switch (option){
                case 0 :{
                    damasPOOBGUI.setVisible(false);
                    DamasPOOBGUI gui = new DamasPOOBGUI();
                    gui.setVisible(true);
                }
                case  1:{
                    damasPOOBGUI.setVisible(false);
                    System.exit(0);
                }
            }
        }
        else {
            var option = JOptionPane.showOptionDialog(null, "Ganaste " + jugador1+"!", "Felicidades",
                    0, 3, null, options, options[0]);
            switch (option){
                case 0 :{
                    damasPOOBGUI.setVisible(false);
                    DamasPOOBGUI gui = new DamasPOOBGUI();
                    gui.setVisible(true);
                }
                case  1:{
                    damasPOOBGUI.setVisible(false);
                }
            }
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
            ficha.addActionListener(e -> reservarFicha(ficha));
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

    /**
     * Señala la puntiación Actual en el juego
     */
    public void makePuntuacionActual(){
        puntaje.setText(jugador1+"       "+ checkers.getBluecheckers() +"            "+ jugador2 +"       "+ checkers.getRedcheckers());
        puntaje.setFont(new Font("Verdana", Font.PLAIN, 12));
        espacioDePuntaje.add(puntaje);
        add(espacioDePuntaje,BorderLayout.EAST);
    }

    /**
     * Reserva una ficha
     * @param ficha Objeto ficha
     */
    public void reservarFicha(Ficha ficha){
        if(fichaSelect == null){
            int[][] vewAl;
            fichaSelect = ficha;
            vewAl = checkers.goMove(ficha.getX(),ficha.getY());
            for (int[] ints : vewAl) {
                if (fichas[ints[0]][ints[1]] != null) {
                    fichas[ints[0]][ints[1]].acive();
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
     * @param ficha Objeto tipo Fichas
     * @param casilla Objeto tipo casillas
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
        if(checkers.getBluecheckers() <= 18 || checkers.getRedcheckers() <= 18){
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
