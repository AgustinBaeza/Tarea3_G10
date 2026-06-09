package GUI;

import javax.swing.*;
import java.awt.*;
import Logica.*;
import Logica.producto.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 * Panel principal de la interfaz grafica
 * Esta clase logra "unir" el PanelExpendedor y el PanelComprador
 * Tambien recibe los eventos del mouse y los distribuye a los paneles
 * para que el usuario pueda interectuar
 */
public class PanelPrincipal extends JPanel {

    private Expendedor expendedor;
    private Comprador comprador;
    private PanelExpendedor panelExpendedor;
    private PanelComprador panelComprador;

    /**
     * Constructor del panel principal
     * Crea las instancias del expendedor y del comprador, inicializa sus paneles
     * graficos y agrega el listener del mouse para procesar los clicks
     */
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

                if (panelComprador.getProductoMostrado() != null) {
                    panelExpendedor.mostrarProductoComprado(panelComprador.getProductoMostrado());
                }

                repaint();
            }
        });
    }

    /**
     * Dibuja los componentes principales de la interfaz
     * Se encarga de actualizar visualmente el expendedor y el comprador
     * cada vez que el panel se repinta
     * @param g contexto grafico utilizado para dibujar los paneles
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        panelExpendedor.paintComponent(g);
        panelComprador.paintComponent(g);
    }
}
