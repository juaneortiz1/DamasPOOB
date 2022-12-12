package presentation;

import javax.swing.*;
import java.awt.*;

/**
 *
 */
public class Puntaje  extends JPanel {
    /**
     * Constructor de de la clase de puntaje
     */
    public Puntaje(){
        setBackground(new Color(29,114,162));
        prepareElements();
        prepareActions();
    }

    private void prepareActions() {
    }

    private void prepareElements() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setPreferredSize(new Dimension(screenSize.width, screenSize.height));
    }
}
