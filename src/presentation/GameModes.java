package presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase que pertenece a la pantalla de seleccion
 */
public class GameModes extends JPanel {
    private DamasPOOBGUI damasPOOBGUI;
    private JButton opjvj,opjvc;
    private SelectDifficulty selectDifficulty;

    /**
     * Constructor de la clase gamemodes
     * @param damasPOOBGUI Ventana principal del programa
     */
    public GameModes(DamasPOOBGUI damasPOOBGUI){
        setBackground(new Color(29,114,162));
        this.damasPOOBGUI = damasPOOBGUI;
        prepareElements();
        prepareActions();
    }

    /**
     * Prepara las acciones de la clase
     */
    private void prepareActions() {
        jvj();
        jvc();
    }

    /**
     * Ejecuta el modo de juego jugador vs computadora
     */
    private void jvc() {
        GameModes panel = this;
        opjvc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectDifficulty = new SelectDifficulty( damasPOOBGUI);
                selectDifficulty.setBackground(new Color(29,114,162));
                selectDifficulty.setVisible(true);
                setVisible(false);
                damasPOOBGUI.remove(panel);
                damasPOOBGUI.add(selectDifficulty);

            }
        });
    }

    /**
     * Ejecuta el modo de juego jugador vs jugador
     */
    private void jvj() {
        GameModes panel = this;
        opjvj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectDifficulty = new SelectDifficulty( damasPOOBGUI);
                selectDifficulty.setBackground(new Color(29,114,162));
                selectDifficulty.setVisible(true);
                setVisible(false);
                damasPOOBGUI.remove(panel);
                damasPOOBGUI.add(selectDifficulty);

            }
        });
    }

    /**
     * Prepara los elementos de la pantalla seleccion de
     * juego
     */
    private void prepareElements() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setPreferredSize(new Dimension(screenSize.width, screenSize.height));

        opjvc = new JButton("Jugador vs Computadora");
        opjvj = new JButton("Jugador vs Jugador");
        JPanel panelBotones = new JPanel();

        panelBotones.setPreferredSize(new Dimension(350, 300));
        add(panelBotones);
        panelBotones.setBorder(new EmptyBorder(135, 10, 0, 10));

        panelBotones.setLayout(new GridLayout(2, 1,5 ,10));
        panelBotones.add(opjvc);
        panelBotones.add(opjvj);
        panelBotones.setBackground(new Color(29,114,162));
    }


}
