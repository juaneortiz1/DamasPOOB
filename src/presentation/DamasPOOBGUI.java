package presentation;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Ventana principal del programa
 */
public class DamasPOOBGUI extends JFrame {
    private JMenuItem nuevo, abra,guarda,salir;
    private Menu venmenu;
    private static final Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    ;
    private static final int WIDTH = 800;
    private static final int HIGH = 720;
    private static final Dimension DIMENSION =
            new Dimension(WIDTH, HIGH);

    /**
     * Constructor de la clase
     */
    public DamasPOOBGUI() {
        prepareElements();
        prepareActions();

    }
    /**
     * Prepara los elementos de la clase
     */
    public void prepareElements(){
        setTitle("DamasPOOB");
        setSize(DIMENSION);
        venmenu = new Menu(this);
        add(venmenu);
        prepareElementosMenu();
    }

    /**
     * Prepara los elementos del menu
     */
    private void prepareElementosMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JFileChooser fileChooser = new JFileChooser();
        nuevo = new JMenuItem("Nuevo");
        abra = new JMenuItem("Abrir");
        guarda = new JMenuItem("Guardar");
        salir = new JMenuItem("Salir");

        menu.add(nuevo);
        menu.add(abra);
        menu.add(guarda);
        menu.add(salir);

        menuBar.add(menu);
        setJMenuBar(menuBar);
    }
    /**
     * Prepara las acciones de la clase
     */
    public  void prepareActions(){
        prepareActionsMenu();
        salir();
    }

    private void prepareActionsMenu() {
        nuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                opNuevo();
            }
        });
        salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salir();
            }
        });
        guarda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                opGuardar();
            }
        });
    }

    private void opGuardar() {
        JFileChooser fileChooser = new JFileChooser();
        //FILTRA TODOS LOS ARCHIVOS Y SOLO DEJA LOS VISIBLES
        //LOS QUE TENGAN EXTENSION .DAT.
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivo con extensión .DAT","DAT"));
        int seleccion = fileChooser.showSaveDialog(this);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            //this..guarde01(fileChooser.getSelectedFile());
        }
    }

    private void opNuevo() {
        DamasPOOBGUI frame = this;
        int resultado = JOptionPane.showConfirmDialog(frame, "¿Está seguro que quiere abrir una nueva pestaña?", "Nuevo", JOptionPane.YES_NO_OPTION);
        if (resultado == JOptionPane.YES_OPTION) {
            frame.setVisible(false);
            DamasPOOBGUI gui = new DamasPOOBGUI();
            gui.setVisible(true);
        } else if (resultado == JOptionPane.NO_OPTION) {
            frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        }
    }



    /**
     * Metodo que cierra el program al cerrar la ventana
     */
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
