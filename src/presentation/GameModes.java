package presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameModes extends JPanel {
    private DamasPOOBGUI damasPOOBGUI;
    private JButton opjvj,opjvc;
    private SelectDifficulty selectDifficulty;
    public GameModes(DamasPOOBGUI damasPOOBGUI){
        setBackground(new Color(29,114,162));
        this.damasPOOBGUI = damasPOOBGUI;
        prepareElements();
        prepareActions();
    }

    private void prepareActions() {
        jvj();
        jvc();
    }

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
