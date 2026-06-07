package GUI;

import Logica.Deposito;
import Logica.producto.Bebida;

import javax.swing.*;

public class Ventana extends JFrame {

    public Ventana() {
        setTitle("Prueba");
        setSize(1450, 850);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        add(new PanelPrincipal());

        setLocationRelativeTo(null);
        setVisible(true);


    }

}
