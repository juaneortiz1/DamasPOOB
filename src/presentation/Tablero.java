package presentation;
import domain.Board;
import domain.Checkers;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tablero extends JPanel {
    private Casilla[][] casillas;
    private Ficha[][] fichas;
    private Checkers checkers;
    private DamasPOOBGUI damasPOOBGUI;


    private Ficha fichaSelect, casillaSelect;

    public Tablero(DamasPOOBGUI damasPOOBGUI) {
        //casillas = new Board(checkers);
        casillas = new Casilla[10][10];
        fichas = new Ficha[10][10];
        checkers = new Checkers();
        this.damasPOOBGUI = damasPOOBGUI;
        prepararAcciones();
    }
    public void prepararAcciones(){
        prepareElementsBoard();
        makeMatriz();
    }

    private void prepareElementsBoard() {
        for(int i = 0; i < casillas.length; i++){
            for( int j = 0; j < casillas[0].length; j++){
                CasillaNormal casilla = new CasillaNormal();
                Ficha ficha = new Ficha(Color.RED, j, i);
                if (((j % 2 == 0) && (i % 2 == 0)) || ((j % 2 == 1) && (i % 2 == 1))) {
                    casilla.setColor(Color.WHITE);
                    casillas[i][j] = casilla;
                } else {
                    casilla.setColor(Color.BLACK);
                    casillas[i][j] = casilla;
                    if(j < 4 ){
                        fichas[i][j] = ficha;
                        prepareActions(fichas[i][j]);
                    } else if (j > 3 && j < 6) {
                        ficha.changeColor(Color.BLACK);
                        fichas[i][j] = ficha;
                        prepareActions(ficha);
                    }else if(j > 5){
                        ficha.changeColor(Color.BLUE);
                        fichas[i][j] = ficha;
                        prepareActions(fichas[i][j]);
                    }
                    casillas[i][j].add(fichas[i][j]);
                }
            }
        }
    }
    private void prepareActions(Ficha ficha) {
        if(ficha.getColor() == Color.black){
            ficha.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    reservarcasillas(ficha);
                }
            });
        }else{
            ficha.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    reservarFicha(ficha);
                }
            });
        }

    }

    public void makeMatriz(){
        JPanel panelmatriz = new JPanel();
        panelmatriz.setLayout(new GridLayout(10, 10, 10, 10));
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

    public void reservarFicha(Ficha ficha){
        fichaSelect = ficha;
        System.out.println("x1 "+fichaSelect.getX()+" y1 "+fichaSelect.getY());
    }

    public void reservarcasillas(Ficha ficha){
        casillaSelect = ficha;
        System.out.println("  x2 "+casillaSelect.getX()+" y2 "+casillaSelect.getY());
        move(fichaSelect, casillaSelect);
    }

    public void move(Ficha ficha, Ficha casilla){
        if(ficha != null && casilla != null){
            checkers.executeMove(ficha.getX(), ficha.getY(), casilla.getX(), casilla.getY());
            paint();
        }
    }

    public void paint() {
        Color[][] newColors = checkers.getColorMatriz();
        for (int i = 0; i < casillas.length; i++) {
            for (int j = 0; j < casillas[0].length; j++) {
                if (fichas[i][j] != null) {
                    fichas[i][j].changeColor(newColors[i][j]);
                }
            }
        }
    }














}
