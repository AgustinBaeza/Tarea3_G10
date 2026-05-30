package Logica;

import Logica.moneda.Moneda;
import Logica.producto.Producto;

/**
 * Clase que se encarga de simular un comprador.
 * Recibe una Moneda, un producto a querer comprar, y el Expendedor en que se compra.
 */
public class Comprador {
    private String sabor = null;
    private int vuelto;
    private Expendedor exp;

    /**
     * Constructor de Comprador, recibe Moneda, un producto y un Expendedor.
     * Realiza los calculos del sabor si es valido y calcula el vuelto sacando
     * las monedas del Expendedor.
     * @param m la moneda utilizada para comprar
     * @param numeroProducto numero del producto que se desea comprar
     * @param exp el expendedor en que se desea comprar
     */
    public Comprador(Moneda m, int numeroProducto, Expendedor exp) {
        this.exp = exp;

        /* compra el producto y calcula el vuelto*/
        Producto prod1 = null;
        Moneda mon;

        try {
            prod1 = exp.comprarProducto(m, numeroProducto);

            if (prod1 != null) {
                sabor = prod1.consumir();
            }

        } catch (Exception e) {
            sabor = e.getMessage(); // guardas el error como resultado
        }

        while ((mon = exp.getVuelto()) != null) {
            vuelto += mon.getValor();
        }
    }

    /**
     * Getter del sabor.
     * @return el sabor en String
     */
    public String queConsumiste() {
        return sabor;
    }

    /**
     * Getter del vuelto.
     * @return el vuelto en int
     */
    public int cuantoVuelto(){
        return vuelto;
    }
}