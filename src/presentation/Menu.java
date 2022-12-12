package presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

/**
 * Clase que fabrica el menu de opciones del juego
 */
public class Menu extends JPanel{

    private JPanel panel;
    private JButton opjugar,oppuntajes;

    Image image;

    private JPanel tablero, tableroPuntajes;
    private GameModes gameModes;

    private DamasPOOBGUI damasPOOBGUI;

    /**
     * Constructor de la clase
     * @param damasPOOBGUI Ventana actual
     */
    public Menu(DamasPOOBGUI damasPOOBGUI){
        setBackground(new Color(29,114,162));
        this.damasPOOBGUI = damasPOOBGUI;
        prepareElements();
        prepareActions();
    }

    /**
     * Prepara las acciones de la clase
     */
    private void prepareActions() {
        jugar();
        puntaje();
    }

    /**
     * Metodo que cambia a la pantalla de modos de juegos
     */
    private void jugar() {
        Menu panel = this;
        opjugar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameModes = new GameModes(damasPOOBGUI);
                gameModes.setBackground(new Color(29,114,162));
                gameModes.setVisible(true);
                setVisible(false);
                damasPOOBGUI.remove(panel);
                damasPOOBGUI.add(gameModes);
            }
        });
    }

    /**
     * Metodo que cambia a la pantalla de puntaje
     */
    private void puntaje() {
        oppuntajes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableroPuntajes = new JPanel();
                tableroPuntajes.setBackground(Color.RED);
                tableroPuntajes.setVisible(true);
                setVisible(false);
                damasPOOBGUI.add(tableroPuntajes);
            }
        });
    }

    /**
     * Prepara los elementos de la clase
     */
    private void prepareElements() {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setPreferredSize(new Dimension(screenSize.width, screenSize.height));

        opjugar = new JButton("Jugar");
        oppuntajes = new JButton("Puntajes");
        JPanel vacio = new JPanel();
        vacio.setOpaque(false);
        JPanel panelBotones = new JPanel();
        panelBotones.setOpaque(false);

        panelBotones.setPreferredSize(new Dimension(350, 300));
        add(panelBotones);
        panelBotones.setBorder(new EmptyBorder(135, 10, 0, 10));

        panelBotones.setLayout(new GridLayout(3, 1,5 ,10));
        panelBotones.add(opjugar);
        panelBotones.add(vacio);
        panelBotones.add(oppuntajes);
        panelBotones.setBackground(new Color(29,114,162));

        image = loadImage("https://i.kym-cdn.com/photos/images/original/000/971/132/49d.gif");
    }

    /**
     * Carga la imagen preferida
     * @param url direccion de la imagen al cargar
     * @return Image imagen que
     */
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
     * Pinta la imagen seleccionada
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }

}
