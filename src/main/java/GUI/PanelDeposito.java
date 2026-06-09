package GUI;

import Logica.Deposito;
import Logica.OpcProducto;
import Logica.moneda.Moneda;
import Logica.producto.Producto;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Clase que representa graficamente un deposito dentro de la interfaz
 * Logra visualizar productos o monedas en un Deposito, mostrando sus datos
 * @param <T> tipo de elemento (Moneda o Producto)
 */
public class PanelDeposito<T> {

    private int x,y,ancho,alto;
    private Deposito<T> deposito;

    private Image imgCoca, imgSprite, imgFanta, imgSuper8, imgSnickers;
    private Image imgM100, imgM500, imgM1000;

    /**
     * Crea un panel grafico asociado a un deposito
     *
     * @param x coordenada x
     * @param y coordenada y
     * @param ancho ancho del panel
     * @param alto alto del panel
     * @param deposito Deposito de los elementos que se mostraran
     */
    public PanelDeposito(int x, int y, int ancho, int alto, Deposito<T> deposito){
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.deposito = deposito;
        cargarImagenes();
    }

    /**
     * Carga una imagen desde los recursos del proyecto y la escala segun el ancho necesitado
     *
     * @param rutaImagen Ruta de la imagen
     * @param sizes Tamaño del escalar de la imagen
     * @return Image escalada o null si no se encuentra
     */
    private Image cargar(String rutaImagen, SizeImagen sizes) {
        URL urlImagen = getClass().getResource(rutaImagen);

        if (urlImagen != null) {
            ImageIcon icono = new ImageIcon(urlImagen);
            Image imgEscalada = icono.getImage().getScaledInstance(sizes.getAncho(), sizes.getAlto(), Image.SCALE_SMOOTH);
            return new ImageIcon(imgEscalada).getImage(); // solucion de que las imagenes no cargaban por completo al iniciar programa
        }
        return null;
    }

    /**
     * Carga todas las imagenes utilizadas por el panel
     */
    private void cargarImagenes() {
        imgCoca = cargar("/imagenes/cocacola.png", SizeImagen.PRODUCTO);
        imgSprite = cargar("/imagenes/sprite.png", SizeImagen.PRODUCTO);
        imgFanta = cargar("/imagenes/fanta.png", SizeImagen.PRODUCTO);
        imgSuper8 = cargar("/imagenes/super8.png", SizeImagen.PRODUCTO);
        imgSnickers = cargar("/imagenes/snickers.png", SizeImagen.PRODUCTO);

        imgM100 = cargar("/imagenes/moneda100.png", SizeImagen.MONEDA);
        imgM500 = cargar("/imagenes/moneda500.png", SizeImagen.MONEDA);
        imgM1000 = cargar("/imagenes/moneda1000.png", SizeImagen.MONEDA);
    }

    /**
     * Dibuja el deposito y todos los elemento contenidos en el.
     *
     * @param g contexto grafica utilizado para realizar el dibujo
     */
    public void paintComponent(Graphics g) {

        // deposito representado como un rectangulo
        Color CELESTE = new Color(220, 240, 255);
        g.setColor(CELESTE);
        g.fillRect(x, y, ancho, alto);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, ancho, alto);

        // lista auxiliar de elementos del deposito para no alterar lista original
        ArrayList<T> elementos = deposito.getDeposito();

        // ordenar monedas
        if (!elementos.isEmpty() && elementos.get(0) instanceof Moneda) {
            ArrayList<Moneda> monedas = (ArrayList<Moneda>) elementos;
            Collections.sort(monedas, Collections.reverseOrder());
        }

        // bucle que recorre horizontalmente para hacer dibujo de cada objeto
        for (int i = 0; i < elementos.size(); i++) {
            T objeto = elementos.get(i);

            int objetoX, objetoY;
            int objetoAncho, objetoAlto;

            // dimensiones del objeto a renderizar como imagen segun corresponda
            if (objeto instanceof Producto) {
                objetoAncho = SizeImagen.PRODUCTO.getAncho();
                objetoAlto = SizeImagen.PRODUCTO.getAlto();
            } else {
                objetoAncho = SizeImagen.MONEDA.getAncho();
                objetoAlto = SizeImagen.MONEDA.getAlto();
            }

            int espacio = 7;
            int inicioX = x + 10;
            int inicioY = y + 3;

            int maxPorFila = Math.max(1, (ancho - 20) / (objetoAncho + espacio));

            int fila = i / maxPorFila;
            int columna = i % maxPorFila;

            objetoX = inicioX + columna * (objetoAncho + espacio);
            objetoY = inicioY + fila * (objetoAlto + 5);

            // actualizacion de coordenadas (x,y) y renderizacion del objeto
            if (objeto instanceof Producto) {
                Producto prod = (Producto) objeto;
                prod.setXY(objetoX, objetoY);
                dibujarProducto(g, prod, objetoX, objetoY, objetoAncho, objetoAlto);
            } else {
                Moneda mon = (Moneda) objeto;
                mon.setXY(objetoX, objetoY);
                dibujarMoneda(g, mon, objetoX, objetoY, objetoAncho, objetoAlto);
            }
        }

        // stock en esquina superior derecha
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 12));
        g.drawString("x" + deposito.depositoSize(), x + ancho - 28, y + 15);
    }

    /**
     * Obtiene la imagen asociada a un tipo de producto
     *
     * @param tipo Tipo de producto
     * @return imagen correspondiente al producto
     */
    private Image obtenerImagenProducto(OpcProducto tipo) {
        switch (tipo) {
            case COCA_COLA:
                return imgCoca;
            case SPRITE:
                return imgSprite;
            case FANTA:
                return imgFanta;
            case SUPER8:
                return imgSuper8;
            case SNICKERS:
                return imgSnickers;
            default:
                return null;
        }
    }

    /**
     * Dibuja un producto en las coordenadas dadas y muestra el numero de serie
     *
     * @param g contexto grafico
     * @param prod producto a dibujar
     * @param prodX coordenada x del producto
     * @param prodY coordenada y del producto
     * @param prodAncho ancho de la imagen
     * @param prodAlto alto de la imagen
     */
    private void dibujarProducto(Graphics g, Producto prod, int prodX, int prodY, int prodAncho, int prodAlto){
        Image img = obtenerImagenProducto(prod.getProducto());
        g.drawImage(img, prodX, prodY, null);

        // numero de serie debajo de cada producto
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 10));
        g.drawString("#" + prod.getNumSerie(), prodX + 2, prodY + prodAlto - 2);
    }

    /**
     * Dibuja una moneda en las coordenadas y muestra su numero de serie
     *
     * @param g contexto grafico
     * @param mon moneda a dibujar
     * @param monX coordenada x de la moneda
     * @param monY coordenada y de la moneda
     * @param monAncho ancho de la imagen
     * @param monAlto alto de la imagen
     */
    private void dibujarMoneda(Graphics g, Moneda mon, int monX, int monY, int monAncho, int monAlto) {
        Image img = obtenerImagenMoneda(mon.getValor());
        g.drawImage(img, monX, monY, null);

        // numero de serie debajo de cada moneda
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 10));
        g.drawString("#" + mon.getSerie(), monX + 2, monY + monAlto - 2);
    }

    /**
     * Obtiene la imagen correspondiente al valor de una moneda
     *
     * @param valor valor de la moneda
     * @return imagen asociada a la moneda o null si no existe
     */
    private Image obtenerImagenMoneda(int valor) {
        if (valor == 1000){
            return imgM1000;
        }
        if (valor == 500){
            return imgM500;
        }
        if (valor == 100){
            return imgM100;
        }

        return null;
    }

    /**
     * Actualiza la posicion del panel dentro de la interfaz
     * @param nuevoX nueva coordenada x
     * @param nuevoY nueva coordenada y
     */
    public void setXY(int nuevoX, int nuevoY) {
        this.x = nuevoX;
        this.y = nuevoY;
    }
}
