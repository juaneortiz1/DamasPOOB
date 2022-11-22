package presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class SelectDifficulty extends JPanel {
    private DamasPOOBGUI damasPOOBGUI;
    private JButton opNormal, opQuickTime;

    private Image image;
    private Tablero tableroNormal, tableroQuicktime;
    public SelectDifficulty(DamasPOOBGUI damasPOOBGUI){
        setBackground(new Color(29,114,162));
        this.damasPOOBGUI = damasPOOBGUI;
        prepareElements();
        prepareActions();
    }

    private void prepareActions() {
        tableroNormal();
        tableroQuicktime();
    }

    private void tableroQuicktime() {
        SelectDifficulty panel = this;
        opQuickTime.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableroQuicktime = new Tablero(damasPOOBGUI);
                tableroQuicktime.setVisible(true);
                setVisible(false);
                damasPOOBGUI.remove(panel);
                damasPOOBGUI.add(tableroQuicktime);
            }
        });
    }

    private void tableroNormal() {
        SelectDifficulty panel = this;
        opNormal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableroNormal = new Tablero(damasPOOBGUI);
                tableroNormal.setVisible(true);
                setVisible(false);
                damasPOOBGUI.remove(panel);
                damasPOOBGUI.add(tableroNormal);
            }
        });
    }

    private void prepareElements() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setPreferredSize(new Dimension(screenSize.width, screenSize.height));

        opNormal = new JButton("Normal");
        opQuickTime = new JButton("Modo QuickTime");
        JPanel panelBotones = new JPanel();

        panelBotones.setPreferredSize(new Dimension(350, 300));
        add(panelBotones);
        panelBotones.setBorder(new EmptyBorder(135, 10, 0, 10));

        panelBotones.setLayout(new GridLayout(2, 1,5 ,10));
        panelBotones.add(opNormal);
        panelBotones.add(opQuickTime);
        panelBotones.setBackground(new Color(29,114,162));

        image = loadImage("https://i.pinimg.com/originals/4d/97/71/4d97712bd9f8616740f0c520ee7bf635.gif");
    }


    private Image loadImage(String url) {
        try {
            getToolkit();
            final Image img = Toolkit.getDefaultToolkit().createImage(new URL(url));
            getToolkit();
            Toolkit.getDefaultToolkit().prepareImage(img, -1, -1, null);
            return img;
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
}