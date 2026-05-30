package GUI;

import javax.swing.*;
import java.awt.*;

public class PanelPrincipal extends JPanel {

    public PanelPrincipal() {
        setLayout(new FlowLayout());

        JLabel etiqueta = new JLabel("aña");
        JButton boton = new JButton("Aceptar");

        add(etiqueta);
        add(boton);
    }

}
