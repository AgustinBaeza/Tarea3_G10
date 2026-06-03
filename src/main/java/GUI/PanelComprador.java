package GUI;

import Logica.Comprador;
import Logica.OpcProducto;
import Logica.moneda.*;
import Logica.producto.*;
import java.awt.*;

public class PanelComprador {

    /** posicion y dimensiones del panel en la ventana */
    private int x, y, ancho, alto;
    /** instancia comprador */
    private Comprador comprador;

    /** seleccion del usuario*/
    //aqui va a ir
    private int MonedaSel = -1;
    private int productoSel = -1;

    /** paneles para visualizar la billetera y el vuelto*/
    //aqui van a ir
    private PanelDeposito<Moneda> panelBilletera;
    private PanelDeposito<Moneda> panelVuelto;

    /** Margenes clicables */
    //aqui van a ir
    private static final int ZONA_ANCHO = 0;
    private static final int ZONA_ALTO = 0;
    private static final int MARGEN = 0;

    /** visibilidad del producto comprado */
    private Producto productoMostar = null;

    /**
     * Constructor de la clase
     * @param x coordenada x del panel dentro del panel principal
     * @param y coordenada y del panel dentro del panel principal
     * @param ancho ancho del panel
     * @param alto alto del panel
     * @param comprador instancia del comprador
     */
    public PanelComprador(int x, int y, int ancho, int alto, Comprador comprador) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.comprador = comprador;

        // deposito billetera
        panelBilletera =  new PanelDeposito<>( x + MARGEN, y + alto + 110, ancho - 2 * MARGEN, 50, comprador.getBilletera() );

        // deposito vuelto recibido
        panelVuelto = new PanelDeposito<>( x + MARGEN, y + alto + 110, ancho - 2 * MARGEN, 50, comprador.getVueltoRec()  );
    }

    /**
     * Dibuja el panel del comprador: fondo, zonas de selección, billetera, vuelto y producto recibido.
     * @param g contexto gráfico utilizado para realizar el dibujo
     */
    public void paintComponent(Graphics g){
        //fondo
        g.setColor(Color.CYAN);
        g.fillRect(x, y, ancho, alto);
        g.setColor(Color.RED);
        g.drawRect(x, y, ancho, alto);

        //titulo
        g.setFont(new Font("Arial", Font.BOLD, 13));
        g.setColor(Color.BLACK);
        g.drawString("Comprador", x + ancho / 2 - 40, y +20);

        //saldo
        g.setFont(new Font("Arial", Font.BOLD, 11));
        g.drawString("Saldo: $" + comprador.getTotalBilletera(), x + MARGEN, y + 38);

        //zona monedas
        dibujarEtiqueta( g, "Insertar moneda: ", x +MARGEN, y+ 55);

    }

    /** Dibuja un texto en forma de etiqueta */
    public void dibujarEtiqueta(Graphics g, String str, int x, int y){
        g.setFont(new Font("Arial", Font.BOLD, 11));
        g.setColor(Color.BLUE);
        g.drawString(str, x, y);
    }

    public void dibujarZonaMoneda(Graphics g, String texto, int valor, int indice){

    }

    public void dibujarZonaProducto(Graphics g, String str, OpcProducto opcion, int indice){

    }

    public void dibujarBotonComprar(Graphics g){

    }

    private void dibujarProductoRecibido(Graphics g){

    }

    public boolean procesarClick(){

        return true;
    }

    private void ejecutarCompra(){

    }

    public boolean dentroDelPanel(int mx, int my) {
        return mx >= x && mx <= x + ancho && my >= y && my <= y + alto;
    }



}
