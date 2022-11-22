package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DamasPOOBGUI extends JFrame {
    private Menu venmenu;
    private static final Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    ;
    private static final int WIDTH = 800;
    private static final int HIGH = 720;
    private static final Dimension DIMENSION =
            new Dimension(WIDTH, HIGH);
    public DamasPOOBGUI() {
        prepareActions();
        prepareElements();
    }
    public  void prepareActions(){
        salir();
    }
    public void prepareElements(){
        setTitle("DamasPOOB");
        setSize(DIMENSION);
        venmenu = new Menu(this);
        add(venmenu);
    }

    public void salir() {
        DamasPOOBGUI frame = this;
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                JPanel panel = new JPanel();
                panel.setSize(new Dimension(250, 250));
                panel.setLayout(null);
                JLabel label2 = new JLabel("Do you wanna exit?");
                label2.setVerticalAlignment(SwingConstants.TOP);
                label2.setHorizontalAlignment(SwingConstants.CENTER);
                label2.setBounds(20, 80, 200, 20);
                panel.add(label2);
                UIManager.put("OptionPane.minimumSize", new Dimension(400, 200));
                int res = JOptionPane.showConfirmDialog(null, panel, "Exit message",
                        JOptionPane.YES_NO_OPTION);
                if (res == 0) {
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                } else if (res == 1) {
                    frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

                }
            }
        });
    }
    public static void main(String args[]){
        DamasPOOBGUI gui = new DamasPOOBGUI();
        gui.setVisible(true);
    }
}
