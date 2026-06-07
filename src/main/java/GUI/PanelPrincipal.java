package GUI;

import javax.swing.*;
import java.awt.*;
import Logica.*;
import Logica.producto.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelPrincipal extends JPanel {

    private Expendedor expendedor;
    private Comprador comprador;
    private PanelExpendedor panelExpendedor;
    private PanelComprador panelComprador;

    public PanelPrincipal() {
        expendedor = new Expendedor(10);
        comprador = new Comprador(expendedor);
        panelExpendedor = new PanelExpendedor(20, 20, 760, 580, expendedor);
        panelComprador = new PanelComprador(820, 20, 600, 768, comprador);

        setBackground(Color.WHITE);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                int mouseX = e.getX();
                int mouseY = e.getY();

                System.out.println("Click en X: " + mouseX + " Y: " + mouseY);

                panelExpendedor.verificarClick(mouseX, mouseY);
                panelComprador.procesarClick(mouseX, mouseY);

                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        panelExpendedor.paintComponent(g);
        panelComprador.paintComponent(g);
    }
}
