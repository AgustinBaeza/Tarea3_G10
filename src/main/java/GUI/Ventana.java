package GUI;

import javax.swing.*;

public class Ventana extends JFrame {

    public Ventana() {
        setTitle("Prueba");
        setSize(400,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(new PanelPrincipal());

        setLocationRelativeTo(null);
        setVisible(true);


    }

}
