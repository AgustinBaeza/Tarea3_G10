package GUI;

import Logica.Deposito;
import Logica.OpcProducto;
import Logica.moneda.Moneda;
import Logica.producto.Producto;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;

public class PanelDeposito<T> {

    private int x,y,ancho,alto;
    private Deposito<T> deposito;

    private Image imgCoca, imgSprite, imgFanta, imgSuper8, imgSnickers;
    private Image imgM100, imgM500, imgM1000;


    public PanelDeposito(int x, int y, int ancho, int alto, Deposito<T> deposito){
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.deposito = deposito;
        cargarImagenes();
    }

    // metodo para cargar individualmente cada imagen
    private Image cargar(String rutaImagen, SizeImagen sizes) {
        URL urlImagen = getClass().getResource(rutaImagen);

        if (urlImagen != null) {
            ImageIcon icono = new ImageIcon(urlImagen);
            Image imgEscalada = icono.getImage().getScaledInstance(sizes.getAncho(), sizes.getAlto(), Image.SCALE_SMOOTH);
            return imgEscalada;
        }
        return null;
    }

    // metodo para cargar todas las imagenes requeridas
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

    public void paintComponent(Graphics g) {

        // deposito representado como un rectangulo
        Color CELESTE = new Color(220, 240, 255);
        g.setColor(CELESTE);
        g.fillRect(x, y, ancho, alto);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, ancho, alto);

        // lista auxiliar de elementos del deposito para no alterar lista original
        ArrayList<T> elementos = deposito.getDeposito();

        // bucle que recorre horizontalmente para hacer dibujo de cada
        for (int i = 0; i < elementos.size(); i++) {
            T objeto = elementos.get(i);

            int objetoX, objetoY;
            int objetoAncho, objetoAlto;

            // dimensiones del objeto a renderizar como imagen
            if (objeto instanceof Producto) {
                objetoAncho = SizeImagen.PRODUCTO.getAncho();
                objetoAlto = SizeImagen.PRODUCTO.getAlto();
            } else {
                objetoAncho = SizeImagen.MONEDA.getAncho();
                objetoAlto = SizeImagen.MONEDA.getAlto();
            }

            // posicion horizontal del objeto con un espaciado de 7 pixeles entre cada uno REVISAR, antes era objancho+8
            objetoX = x + 10 + i * (objetoAncho + 7);

            // posicion vertical del objeto centrado en cada rectangulo de deposito
            objetoY = y + (alto / 2) - (objetoAlto / 2);

            // actualizacion de coordenadas y renderizacion del objeto
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
    }

    // metodo para dibujar cada producto bebida o dulce individualmente
    private void dibujarProducto(Graphics g, Producto prod, int prodX, int prodY, int prodAncho, int prodAlto){
        Image img = obtenerImagenProducto(prod.getProducto());
        g.drawImage(img, prodX, prodY, null);

        // numero de serie debajo de cada producto
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 10));
        g.drawString("#" + prod.getNumSerie(), prodX + 2, prodY + prodAlto - 2);
    }

    // segun la opcion del producto, retornara la imagen correspondiente
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

    // metodo para dibujar moneda individualmente
    private void dibujarMoneda(Graphics g, Moneda mon, int monX, int monY, int monAncho, int monAlto) {
        Image img = obtenerImagenMoneda(mon.getValor());
        g.drawImage(img, monX, monY, null);
    }

    // retorna segun el valor de la moneda
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

    // setter de la posicion del deposito completo
    public void setXY(int nuevoX, int nuevoY) {
        this.x = nuevoX;
        this.y = nuevoY;
    }
}
