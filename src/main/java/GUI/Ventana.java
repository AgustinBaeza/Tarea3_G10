package GUI;

import javax.swing.*;

/**
 * Ventana principal de la aplicacion grafica
 * Esta clase crea la ventana que contiene el panel principal del sistema
 * comprador y expendedor. Configura el titulo, tamaño, cierre de la ventana,
 * posicion inicial y visibilidad
 */
public class Ventana extends JFrame {

    /**
     * Constructor de la ventana principal
     * Crea la ventana, agrega el PanelPrincipal y deja visible
     * la interfaz grafica del programa
     */
    public Ventana() {
        setSize(1450, 850);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(new PanelPrincipal());

        setLocationRelativeTo(null);
        setVisible(true);


    }

}
