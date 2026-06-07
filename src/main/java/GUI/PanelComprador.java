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
    private static final int ZONA_ANCHO = 90;
    private static final int ZONA_ALTO = 28;
    private static final int MARGEN = 15;

    /** visibilidad del producto comprado */
    private Producto productoMostar = null;
    private String mensajePanel = null;

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
        panelBilletera =  new PanelDeposito<>( x + MARGEN, y + 440, ancho - 2 * MARGEN, 158, comprador.getBilletera() );

        // deposito vuelto recibido
        panelVuelto = new PanelDeposito<>( x + MARGEN, y + 620, ancho - 2 * MARGEN, 140, comprador.getVueltoRec()  );
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
        g.drawString("Saldo: $" + comprador.getTotalBilletera(), x + MARGEN, y + 40);

        // Etiquetas principales
        dibujarEtiqueta(g, "Insertar moneda:", x + MARGEN, y + 65);
        dibujarZonaMoneda(g, "$100", 100, 0);
        dibujarZonaMoneda(g, "$500", 500, 1);
        dibujarZonaMoneda(g, "$1000", 1000, 2);
        dibujarEtiqueta(g, "Elegir producto:", x + MARGEN, y + 135);
        dibujarZonaProducto(g, "Coca Cola", OpcProducto.COCA_COLA, 0);
        dibujarZonaProducto(g, "Sprite", OpcProducto.SPRITE, 1);
        dibujarZonaProducto(g, "Fanta", OpcProducto.FANTA, 2);
        dibujarZonaProducto(g, "Snickers", OpcProducto.SNICKERS, 3);
        dibujarZonaProducto(g, "Super8", OpcProducto.SUPER8, 4);
        dibujarBotonComprar(g);
        dibujarMensaje(g);
        dibujarProductoRecibido(g);
        dibujarEtiqueta(g, "Billetera:", x + MARGEN, y + 430);
        dibujarEtiqueta(g, "Vuelto recibido:", x + MARGEN, y + 614);

        // Dibujar depósitos del comprador
        panelBilletera.paintComponent(g);
        panelVuelto.paintComponent(g);

    }

    /** Dibuja un texto en forma de etiqueta */
    public void dibujarEtiqueta(Graphics g, String str, int x, int y){
        g.setFont(new Font("Arial", Font.BOLD, 11));
        g.setColor(Color.BLUE);
        g.drawString(str, x, y);
    }

    public void dibujarZonaMoneda(Graphics g, String texto, int valor, int indice){
        int zonaX = x + MARGEN + indice * (ZONA_ANCHO + 8);
        int zonaY = y + 75;

        if (MonedaSel == valor) {
            g.setColor(Color.YELLOW);
        }
        else {
            g.setColor(Color.LIGHT_GRAY);
        }

        g.fillRect(zonaX, zonaY, ZONA_ANCHO, ZONA_ALTO);
        g.setColor(Color.BLACK);
        g.drawRect(zonaX, zonaY, ZONA_ANCHO, ZONA_ALTO);
        g.setFont(new Font("Arial", Font.BOLD, 11));
        g.drawString(texto, zonaX + 22, zonaY + 18);
    }

    public void dibujarZonaProducto(Graphics g, String str, OpcProducto opcion, int indice){
        int zonaX = x + MARGEN;
        int zonaY = y + 150 + indice * (ZONA_ALTO + 8);
        int zonaAncho = ancho - 2 * MARGEN;

        if (productoSel == opcion.getTag()) {
            g.setColor(Color.YELLOW);
        } else {
            g.setColor(Color.WHITE);
        }

        g.fillRect(zonaX, zonaY, zonaAncho, ZONA_ALTO);
        g.setColor(Color.BLACK);
        g.drawRect(zonaX, zonaY, zonaAncho, ZONA_ALTO);
        g.setFont(new Font("Arial", Font.BOLD, 11));
        g.drawString(str + " - $" + opcion.getPrecio(), zonaX + 10, zonaY + 18);
    }

    public void dibujarBotonComprar(Graphics g){
        int botonX = x + MARGEN;
        int botonY = y + 330;
        int botonAncho = ancho - 2 * MARGEN;
        int botonAlto = 30;

        g.setColor(Color.GREEN);
        g.fillRect(botonX, botonY, botonAncho, botonAlto);
        g.setColor(Color.BLACK);
        g.drawRect(botonX, botonY, botonAncho, botonAlto);
        g.setFont(new Font("Arial", Font.BOLD, 13));
        g.drawString("COMPRAR", botonX + botonAncho / 2 - 35, botonY + 20);

    }

    private void dibujarMensaje(Graphics g) {

        int mensajeX = x + MARGEN;
        int mensajeY = y + 375;
        int mensajeAncho = ancho - 2 * MARGEN;
        int mensajeAlto = 24;

        g.setColor(Color.WHITE);
        g.fillRect(mensajeX, mensajeY, mensajeAncho, mensajeAlto);
        g.setColor(Color.BLACK);
        g.drawRect(mensajeX, mensajeY, mensajeAncho, mensajeAlto);
        g.setFont(new Font("Arial", Font.BOLD, 10));

        if (mensajePanel != null) {
            g.drawString(mensajePanel, mensajeX + 8, mensajeY + 15);
        } else {
            g.drawString("Seleccione moneda, producto y presione COMPRAR", mensajeX + 8, mensajeY + 15);
        }
    }

    private void dibujarProductoRecibido(Graphics g){
        g.setFont(new Font("Arial", Font.BOLD, 10));
        g.setColor(Color.BLACK);

        if (productoMostar != null) {
            g.drawString(
                    "Ultimo producto: " + productoMostar.getProducto() + " #" + productoMostar.getNumSerie(),
                    x + MARGEN,
                    y + 415
            );
        }

    }

    public boolean procesarClick(int mouseX, int mouseY){

        if (!dentroDelPanel(mouseX, mouseY)) {
            return false;
        }

        System.out.println("Click dentro de PanelComprador en X: " + mouseX + " Y: " + mouseY);
        if (clickEnZonaMoneda(mouseX, mouseY, 0)) {
            MonedaSel = 100;
            System.out.println("Moneda seleccionada: $100");
            return true;
        }

        if (clickEnZonaMoneda(mouseX, mouseY, 1)) {
            MonedaSel = 500;
            System.out.println("Moneda seleccionada: $500");
            return true;
        }

        if (clickEnZonaMoneda(mouseX, mouseY, 2)) {
            MonedaSel = 1000;
            System.out.println("Moneda seleccionada: $1000");
            return true;
        }

        if (clickEnZonaProducto(mouseX, mouseY, 0)) {
            productoSel = OpcProducto.COCA_COLA.getTag();
            System.out.println("Producto seleccionado: Coca Cola");
            return true;
        }

        if (clickEnZonaProducto(mouseX, mouseY, 1)) {
            productoSel = OpcProducto.SPRITE.getTag();
            System.out.println("Producto seleccionado: Sprite");
            return true;
        }

        if (clickEnZonaProducto(mouseX, mouseY, 2)) {
            productoSel = OpcProducto.FANTA.getTag();
            System.out.println("Producto seleccionado: Fanta");
            return true;
        }

        if (clickEnZonaProducto(mouseX, mouseY, 3)) {
            productoSel = OpcProducto.SNICKERS.getTag();
            System.out.println("Producto seleccionado: Snickers");
            return true;
        }

        if (clickEnZonaProducto(mouseX, mouseY, 4)) {
            productoSel = OpcProducto.SUPER8.getTag();
            System.out.println("Producto seleccionado: Super8");
            return true;
        }

        if (clickEnBotonComprar(mouseX, mouseY)) {
            ejecutarCompra();
            return true;
        }

        return true;
    }
    private boolean clickEnZonaMoneda(int mouseX, int mouseY, int indice) {
        int zonaX = x + MARGEN + indice * (ZONA_ANCHO + 8);
        int zonaY = y + 75;
        return mouseX >= zonaX && mouseX <= zonaX + ZONA_ANCHO && mouseY >= zonaY && mouseY <= zonaY + ZONA_ALTO;
    }

    private boolean clickEnZonaProducto(int mouseX, int mouseY, int indice) {
        int zonaX = x + MARGEN;
        int zonaY = y + 150 + indice * (ZONA_ALTO + 8);
        int zonaAncho = ancho - 2 * MARGEN;

        return mouseX >= zonaX && mouseX <= zonaX + zonaAncho && mouseY >= zonaY && mouseY <= zonaY + ZONA_ALTO;
    }

    private boolean clickEnBotonComprar(int mouseX, int mouseY) {

        int botonX = x + MARGEN;
        int botonY = y + 330;
        int botonAncho = ancho - 2 * MARGEN;
        int botonAlto = 30;

        return mouseX >= botonX && mouseX <= botonX + botonAncho
                && mouseY >= botonY && mouseY <= botonY + botonAlto;
    }

    private void ejecutarCompra(){
        if (MonedaSel == -1 || productoSel == -1) {
            mensajePanel = "Seleccione moenda y producto.";
            System.out.println(mensajePanel);
            return;
        }

        comprador.comprar(MonedaSel, productoSel);
        productoMostar = comprador.getProductoRecibido();
        mensajePanel = comprador.getMensaje();

        System.out.println(mensajePanel);

        MonedaSel = -1;
        productoSel = -1;

    }

    public boolean dentroDelPanel(int mx, int my) {
        return mx >= x && mx <= x + ancho && my >= y && my <= y + alto;
    }



}
