package GUI;

import javax.swing.*;
import java.awt.*;
import Logica.*;
import Logica.producto.*;

public class PanelPrincipal extends JPanel {

    private PanelDeposito<Bebida> panelDeposito;

    public PanelPrincipal() {
        Deposito<Bebida> dep = new Deposito<>();
        panelDeposito = new PanelDeposito<>(50,50,300,100,dep);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        panelDeposito.paintComponent(g);
    }
}
