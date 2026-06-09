package GUI;

import Logica.Comprador;
import Logica.OpcProducto;
import Logica.moneda.*;
import Logica.producto.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Panel grafico del comprador
 * Sirve para poder seleccionar una o mas monedas, elegir un producto,
 * ejecutar la compra y visualizar informacion como saldo, total ingresado,
 * vuelto recibido y mensajes del resultado de la compra
 */
public class PanelComprador {

    /** posicion y dimensiones del panel en la ventana */
    private int x, y, ancho, alto;
    /** instancia comprador */
    private Comprador comprador;

    /** seleccion del usuario*/
    private ArrayList<Integer> monedasSeleccionadas = new ArrayList<>();
    private int totalIngresado = 0;
    private int productoSel = -1;

    /** paneles para visualizar la billetera y el vuelto*/
    private PanelDeposito<Moneda> panelBilletera;
    private PanelDeposito<Moneda> panelVuelto;

    /** Margenes clicables */
    private static final int ZONA_ANCHO = 90;
    private static final int ZONA_ALTO = 28;
    private static final int MARGEN = 15;

    /** visibilidad del producto comprado */
    private Producto productoMostar = null;
    private String mensajePanel = null;

    /**
     * Constructor del panel comprador
     * Inicializa la posicion, dimensiones, comprador asociado y los paneles
     * graficos que muestran la billetera y el vuelto recibido
     *
     * @param x coordenada horizontal del panel
     * @param y coordenada vertical del panel
     * @param ancho ancho del panel
     * @param alto alto del panel
     * @param comprador comprador asociado a la interfaz grafica
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
     * Dibuja todos los elementos visuales del comprador
     * Incluye el fondo del panel, saldo, total ingresado, botones de monedas,
     * botones de productos, boton de compra, mensajes, billetera y vuelto recibido
     * @param g contexto grafico utilizado para dibujar el panel
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
        g.drawString("Total ingresado: $" + totalIngresado, x + MARGEN + 120, y + 40);

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

    /**
     * Dibuja un texto en forma de etiqueta
     * @param g contexto grafico utilizado para dibujar
     * @param str texto que se desea mostrar
     * @param x coordenada horizontal del texto
     * @param y coordenada vertical del texto
     * */
    public void dibujarEtiqueta(Graphics g, String str, int x, int y){
        g.setFont(new Font("Arial", Font.BOLD, 11));
        g.setColor(Color.BLUE);
        g.drawString(str, x, y);
    }

    /**
     * Dibuja una zona clickeable para seleccionar monedas
     * Cada zona representa un valor de moneda. Si el usuario selecciona una moneda
     * de ese valor, la zona cambia de color para indicar la seleccion
     *
     * @param g contexto grafico utilizado para dibujar
     * @param texto texto mostrado en la zona de moneda
     * @param valor valor numerico de la moneda
     * @param indice posicion de la moneda dentro del grupo de botones
     */
    public void dibujarZonaMoneda(Graphics g, String texto, int valor, int indice){
        int zonaX = x + MARGEN + indice * (ZONA_ANCHO + 8);
        int zonaY = y + 75;

        if (monedasSeleccionadas.contains(valor)) {
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

    /**
     * Dibuja una zona clickeable para seleccionar productos
     * Cada zona muestra el nombre y precio del producto. Si el producto esta
     * seleccionado, se cambia de color para que resalte la seleccion del producto
     *
     * @param g contexto grafico utilizado para dibujar
     * @param str nombre del producto mostrado en pantalla
     * @param opcion producto disponible segun OpcProducto
     * @param indice posicion del producto dentro de la lista visual
     */
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

    /**
     * Dibuja el boton que permite hacer la compra
     * @param g contexto grafico utilizado para dibujar el boton
     */
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


    /**
     * Dibuja el mensaje informativo del panel comprador
     * El mensaje puede indicar instrucciones iniciales, compra exitosa,
     * dinero insuficiente, falta de stock o seleccion incompleta
     *
     * @param g contexto grafico utilizado para dibujar el mensaje
     */
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
            g.drawString("Seleccione monedas, producto y presione COMPRAR", mensajeX + 8, mensajeY + 15);
        }
    }

    /**
     * Dibuja la informacion del ultimo producto recibido por el comprador
     * Si se compro un producto se muestra su nombre y numero de serie
     * dentro del panel
     *
     * @param g contexto grafico utilizado para dibujar la informacion
     */
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

    /**
     * Procesa los clicks realizados dentro del panel comprador
     * Detecta si el usuario selecciono una moneda, un producto
     * o el boton de compra
     *
     * @param mouseX coordenada horizontal del click
     * @param mouseY coordenada vertical del click
     * @return true si el click fue procesado dentro del panel, false si fue fuera del panel
     */
    public boolean procesarClick(int mouseX, int mouseY){

        if (!dentroDelPanel(mouseX, mouseY)) {
            return false;
        }

        if (clickEnZonaMoneda(mouseX, mouseY, 0)) {
            agregarMonedaSeleccionada(100);
            return true;
        }

        if (clickEnZonaMoneda(mouseX, mouseY, 1)) {
            agregarMonedaSeleccionada(500);
            return true;
        }

        if (clickEnZonaMoneda(mouseX, mouseY, 2)) {
            agregarMonedaSeleccionada(1000);
            return true;
        }

        if (clickEnZonaProducto(mouseX, mouseY, 0)) {
            productoSel = OpcProducto.COCA_COLA.getTag();
            return true;
        }

        if (clickEnZonaProducto(mouseX, mouseY, 1)) {
            productoSel = OpcProducto.SPRITE.getTag();
            return true;
        }

        if (clickEnZonaProducto(mouseX, mouseY, 2)) {
            productoSel = OpcProducto.FANTA.getTag();
            return true;
        }

        if (clickEnZonaProducto(mouseX, mouseY, 3)) {
            productoSel = OpcProducto.SNICKERS.getTag();
            return true;
        }

        if (clickEnZonaProducto(mouseX, mouseY, 4)) {
            productoSel = OpcProducto.SUPER8.getTag();
            return true;
        }

        if (clickEnBotonComprar(mouseX, mouseY)) {
            ejecutarCompra();
            return true;
        }

        return true;
    }

    /**
     * Agrega una moneda seleccionada por el usuario
     * Cada click sobre una moneda agrega su valor a la lista de monedas
     * seleccionadas y actualiza el total ingresado
     * @param valorMoneda valor de la moneda seleccionada
     */
    private void agregarMonedaSeleccionada(int valorMoneda) {
        monedasSeleccionadas.add(valorMoneda);
        totalIngresado += valorMoneda;
        mensajePanel = "Total ingresado: $" + totalIngresado;
        System.out.println("Moneda agregada: $" + valorMoneda + " | Total: $" + totalIngresado);
    }

    /**
     * Verifica si el click del mouse se realizo dentro de una zona de moneda
     * Calcula la posicion de la zona segun el indice de la moneda y compara
     * las coordenadas del click con los limites del rectangulo
     * @param mouseX coordenada horizontal del click
     * @param mouseY coordenada vertical del click
     * @param indice posicion de la moneda dentro del grupo de botones
     * @return true si el click esta dentro de la zona de moneda, false en caso contrario
     */
    private boolean clickEnZonaMoneda(int mouseX, int mouseY, int indice) {
        int zonaX = x + MARGEN + indice * (ZONA_ANCHO + 8);
        int zonaY = y + 75;
        return mouseX >= zonaX && mouseX <= zonaX + ZONA_ANCHO && mouseY >= zonaY && mouseY <= zonaY + ZONA_ALTO;
    }

    /**
     * Verifica si el click del mouse se realizo dentro de una zona de producto
     * Calcula la posicion de la zona segun el indice del producto y compara
     * las coordenadas del click con los limites del rectangulo correspondiente
     * @param mouseX coordenada horizontal del click
     * @param mouseY coordenada vertical del click
     * @param indice posicion del producto dentro de la lista visual
     * @return true si el click esta dentro de la zona de producto, false en caso contrario
     */
    private boolean clickEnZonaProducto(int mouseX, int mouseY, int indice) {
        int zonaX = x + MARGEN;
        int zonaY = y + 150 + indice * (ZONA_ALTO + 8);
        int zonaAncho = ancho - 2 * MARGEN;

        return mouseX >= zonaX && mouseX <= zonaX + zonaAncho && mouseY >= zonaY && mouseY <= zonaY + ZONA_ALTO;
    }

    /**
     * Verifica si el click del mouse se realizo sobre el boton de compra
     * Compara las coordenadas del click con los limites del rectangulo
     * correspondiente al boton COMPRAR
     * @param mouseX coordenada horizontal del click
     * @param mouseY coordenada vertical del click
     * @return true si el click esta dentro del boton comprar, false en caso contrario
     */
    private boolean clickEnBotonComprar(int mouseX, int mouseY) {

        int botonX = x + MARGEN;
        int botonY = y + 330;
        int botonAncho = ancho - 2 * MARGEN;
        int botonAlto = 30;

        return mouseX >= botonX && mouseX <= botonX + botonAncho
                && mouseY >= botonY && mouseY <= botonY + botonAlto;
    }

    /**
     * Ejecuta la compra usando las monedas y el producto seleccionado por el usuario
     * El metodo valida que exista al menos una moneda seleccionada y un producto seleccionado
     * Luego envia la lista de monedas seleccionadas al comprador para realizar la compra
     * Despues actualiza el mensaje mostrado en pantalla, guarda el ultimo producto recibido
     * y limpia la seleccion para poder hacer una nueva compra.
     */
    private void ejecutarCompra(){

        if (monedasSeleccionadas.isEmpty() || productoSel == -1) {
            mensajePanel = "Seleccione moneda y producto.";
            System.out.println(mensajePanel);
            return;
        }

        comprador.comprar(monedasSeleccionadas, productoSel);

        productoMostar = comprador.getProductoRecibido();
        mensajePanel = comprador.getMensaje();

        System.out.println(mensajePanel);

        monedasSeleccionadas.clear();
        totalIngresado = 0;
        productoSel = -1;
    }

    /**
     * Verifica si una coordenada pertenece al area del comprador
     * @param mx coordenada horizontal del mouse
     * @param my coordenada vertical del mouse
     * @return true si el punto esta dentro del panel, false en caso contrario
     */
    public boolean dentroDelPanel(int mx, int my) {
        return mx >= x && mx <= x + ancho && my >= y && my <= y + alto;
    }

    /**
     * Retorna el ultimo producto recibido por el comprador.
     *
     * @return ultimo producto recibido, o null si no se ha comprado ningun producto
     */
    public Producto getProductoMostrado() {
        return productoMostar;
    }



}
