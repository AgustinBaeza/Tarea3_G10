package GUI;

import Logica.DepositoRetiro;
import Logica.Expendedor;
import Logica.OpcProducto;
import Logica.producto.*;
import Logica.moneda.*;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class PanelExpendedor {

    private int x, y, ancho, alto;
    private Expendedor expendedor;

    private PanelDeposito<Bebida> panelCoca;
    private PanelDeposito<Bebida> panelSprite;
    private PanelDeposito<Bebida> panelFanta;
    private PanelDeposito<Dulce> panelSuper8;
    private PanelDeposito<Dulce> panelSnickers;

    private PanelDeposito<Moneda> panelRec;
    private PanelDeposito<Moneda> panelVuelto;

    private Image imgCoca, imgSprite, imgFanta, imgSuper8, imgSnickers;

    public PanelExpendedor(int x, int y, int ancho, int alto, Expendedor expendedor) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.expendedor = expendedor;

        int depositosPosX = x + 20;
        int anchoDepositos = 640;
        int altoDepositos = 75;
        int altoMonedas = 55;

        panelCoca = new PanelDeposito<Bebida>(depositosPosX, y + 35,  anchoDepositos, altoDepositos, expendedor.getCoca());
        panelSprite = new PanelDeposito<Bebida>(depositosPosX,y + 115, anchoDepositos, altoDepositos, expendedor.getSprite());
        panelFanta = new PanelDeposito<Bebida>(depositosPosX,y + 195, anchoDepositos, altoDepositos, expendedor.getFanta());
        panelSuper8 = new PanelDeposito<Dulce> (depositosPosX, y + 275, anchoDepositos, altoDepositos, expendedor.getSuper8());
        panelSnickers = new PanelDeposito<Dulce> (depositosPosX, y + 355, anchoDepositos, altoDepositos, expendedor.getSnickers());

        panelRec = new PanelDeposito<Moneda>(depositosPosX, y + 438, anchoDepositos, altoMonedas, expendedor.getMonRec());
        panelVuelto = new PanelDeposito<Moneda>(depositosPosX, y + 498, 555, altoMonedas, expendedor.getMonVu());

        cargarImagenesRetiro();
    }

    private Image cargar(String rutaImagen, SizeImagen sizes) {
        URL urlImagen = getClass().getResource(rutaImagen);

        if (urlImagen != null) {
            ImageIcon icono = new ImageIcon(urlImagen);
            Image imgEscalada = icono.getImage().getScaledInstance(sizes.getAncho(), sizes.getAlto(), Image.SCALE_SMOOTH);
            return new ImageIcon(imgEscalada).getImage();
        }
        return null;
    }

    private void cargarImagenesRetiro() {
        imgCoca = cargar("/imagenes/cocacola.png", SizeImagen.PRODUCTO);
        imgSprite = cargar("/imagenes/sprite.png", SizeImagen.PRODUCTO);
        imgFanta = cargar("/imagenes/fanta.png", SizeImagen.PRODUCTO);
        imgSuper8 = cargar("/imagenes/super8.png", SizeImagen.PRODUCTO);
        imgSnickers = cargar("/imagenes/snickers.png", SizeImagen.PRODUCTO);
    }

    private Image obtenerImagenProducto(OpcProducto tipo) {

        // retorna la imagen correspondiente segun el tipo escogido
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

    public void paintComponent(Graphics g) {

        // dibujo de fondo gris oscuro del expendedor
        g.setColor(Color.DARK_GRAY);
        g.fillRect(x, y, ancho, alto);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, ancho, alto);


        // dibujo de panel lateral derecho (el fondo gris en el que van pantalla tactil y panel numerico)
        int panelX = x + 662;
        g.setColor(Color.GRAY);
        g.fillRect(panelX, y + 35, 68, 430);
        g.setColor(Color.BLACK);
        g.drawRect(panelX, y + 35, 68, 430);

        // pantalla tactil
        g.setColor(Color.CYAN);
        g.fillRect(panelX + 5, y + 120, 55, 40);
        g.setColor(Color.BLACK);
        g.drawRect(panelX + 5, y + 120, 55, 40);

        // dibujo ranura de monedas
        g.setColor(Color.BLACK);
        g.fillRect(panelX + 30, y + 55, 8, 35);

        // dibujo panel numerico
        g.setColor(new Color(80, 80, 80));
        g.fillRect(panelX + 5, y + 140, 55, 95);
        g.setColor(Color.BLACK);
        g.drawRect(panelX + 5, y + 140, 55, 95);

        // dibujo del deposito de las 5 opciones de productos
        panelCoca.paintComponent(g);
        panelSprite.paintComponent(g);
        panelFanta.paintComponent(g);
        panelSuper8.paintComponent(g);
        panelSnickers.paintComponent(g);

        // dibujo de deposito de recaudacion
        panelRec.paintComponent(g);

        // dibujo de deposito de vuelto
        panelVuelto.paintComponent(g);

        // posicion y medidas deposito retiro
        int retiroX = x + 580;
        int retiroY = y + 498;
        int retiroAncho = 80;
        int retiroAlto  = 70;

        // dibujo deposito retiro
        g.setColor(Color.BLACK);
        g.fillRect(retiroX, retiroY, retiroAncho, retiroAlto);
        g.setColor(Color.BLACK);
        g.drawRect(retiroX, retiroY, retiroAncho, retiroAlto);

        // dibujo del producto en deposito de retiro, si es que hay
        DepositoRetiro depositoRetiro = expendedor.verProductoComprado();
        if (depositoRetiro.tieneProducto()) {
            Producto prod = depositoRetiro.verDepositoRetiro();
            Image imgProd = obtenerImagenProducto(prod.getProducto());

            if (imgProd != null) {
                int prodX = retiroX + (retiroAncho / 2) - (SizeImagen.PRODUCTO.getAncho() / 2);
                int prodY = retiroY + (retiroAlto / 2) - (SizeImagen.PRODUCTO.getAlto() / 2);
                g.drawImage(imgProd, prodX, prodY, null);

                g.setColor(Color.WHITE);
                g.setFont(new Font("Arial", Font.BOLD, 10));
                g.drawString("#" + prod.getNumSerie(), prodX + 2, prodY + SizeImagen.PRODUCTO.getAlto() - 2);
            }
        }
    }

    public void verificarClick(int clickX, int clickY) {

        // si el click ocurre dentro de las coordenadas del expendedor
        if (clickX >= x && clickX <= (x + ancho) && clickY >= y && clickY <= (y + alto)) {

            // coordenadas deposito retiro
            int retiroX = x + 580;
            int retiroY = y + 498;
            int retiroAncho = 80;
            int retiroAlto  = 70;

            // click en la bandeja de retiro, llama a retirar el producto mediante getProducto
            if (clickX >= retiroX && clickX <= (retiroX + retiroAncho) && clickY >= retiroY && clickY <= (retiroY + retiroAlto)) {
                expendedor.getProducto();
                return;
            }

            // coordenadas deposito vuelto
            int vueltoX = x + 20;
            int vueltoY = y + 498;
            int vueltoAncho = 555;
            int vueltoAlto = 55;

            // click en el deposito de vuelto, retira monedas de vuelto mediante el bucle formado en expendedor
            if (clickX >= vueltoX && clickX <= (vueltoX + vueltoAncho) && clickY >= vueltoY && clickY <= (vueltoY + vueltoAlto)) {
                expendedor.getVuelto();
                return;
            }

            // click en el fondo de la maquina expendedora, accion que rellena los depositos
            expendedor.rellenarDepositosVacios();
        }
    }

    // setter de las coordenadas (x,y) del expendedor
    public void setXY(int nuevoX, int nuevoY) {
        this.x = nuevoX;
        this.y = nuevoY;
    }
}