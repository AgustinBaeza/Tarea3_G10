package Logica;

import Logica.excepciones.*;
import Logica.moneda.*;
import Logica.producto.*;
import java.util.ArrayList;
/**
 * Clase que se encarga de simular un comprador.
 * Recibe una Moneda, un producto a querer comprar, y el Expendedor en que se compra.
 */
public class Comprador {
    private Deposito<Moneda> billetera;
    private Producto productoRecibido;
    private Deposito<Moneda> vueltoRec;
    private String mensaje;
    private Expendedor exp;


    /**
     * Constructor del Comprador
     * Inicializa la billetera con monedas de 5*$1000 + 2*$500 + 3*$100, total $6400
     *
     * @param exp expendedor que interactua con el comprador
     */
    public Comprador(Expendedor exp){
        this.exp = exp;
        this.billetera = new Deposito<Moneda>();
        this.vueltoRec = new Deposito<Moneda>();
        this.productoRecibido = null;
        this.mensaje = "Selecciona una moneda y un producto.";

        //añadir monedas
        billetera.add(new Moneda1000());
        billetera.add(new Moneda1000());
        billetera.add(new Moneda1000());
        billetera.add(new Moneda1000());
        billetera.add(new Moneda1000());
        billetera.add(new Moneda500());
        billetera.add(new Moneda500());
        billetera.add(new Moneda100());
        billetera.add(new Moneda100());
        billetera.add(new Moneda100());

    }

    /**
     * Intenta realizar una compra usando una sola moneda y el tag del producto
     * Este metodo mantiene el funcionamiento original de compra con una moneda,
     * pero por dentro convierte esa moneda en una lista para poder usar la logica de la tarea 1
     * de compra con una o varias monedas
     *
     * @param valorMoneda valor de la moneda que se desea usar
     * @param tagProducto tag del producto seleccionado segun OpcProducto
     */
    public void comprar(int valorMoneda, int tagProducto){

        ArrayList<Integer> valoresMonedas = new ArrayList<>();
        valoresMonedas.add(valorMoneda);

        comprar(valoresMonedas, tagProducto);
    }

    /**
     * Intenta realizar una compra usando una o varias monedas
     * El metodo busca en la billetera del comprador las monedas que coincidan con los
     * valores elegidos, si las encuentra, las saca de la billetera y las pasa
     * al expendedor para hacer la compra
     * Si se hace la compra, el comprador recibe el vuelto entregado
     * por el expendedor y llama al usuario que lo retire manualmente.
     * Si llega a fallar, como dinero insuficiente, producto agotado
     * o falta de monedas disponibles, se muestra un mensaje y se devuelven las monedas
     * a la billetera
     *
     * @param valoresMonedas lista con los valores de las monedas seleccionadas
     * @param tagProducto tag del producto seleccionado segun OpcProducto
     */
    public void comprar(ArrayList<Integer> valoresMonedas, int tagProducto){

        productoRecibido = null;
        if (valoresMonedas == null || valoresMonedas.isEmpty()) {
            mensaje = "Seleccione una moneda.";
            return;
        }

        ArrayList<Moneda> monedasAUsar = new ArrayList<>();

        for (int valor : valoresMonedas) {

            Moneda monedaEncontrada = null;

            for (Moneda m : billetera.getDeposito()) {
                if (m.getValor() == valor && !monedasAUsar.contains(m)) {
                    monedaEncontrada = m;
                    break;
                }
            }

            if (monedaEncontrada == null) {
                mensaje = "No tienes suficientes monedas de $" + valor + " en tu billetera.";
                return;
            }

            monedasAUsar.add(monedaEncontrada);
        }

        // Sacar las monedas de la billetera antes de usarlas
        for (Moneda moneda : monedasAUsar) {
            billetera.remove(moneda);
        }

        try {

            exp.comprarProducto(monedasAUsar, tagProducto);

            mensaje = "Compra realizada, retirar en bandeja!";

            if (productoRecibido != null) {
                mensaje = "Compraste " + productoRecibido.consumir() + ", Serie: " + productoRecibido.getNumSerie();
            } else {
                mensaje = "Compra realizada.";
            }

            Moneda vuelto;
            while ((vuelto = exp.getVuelto()) != null) {
                vueltoRec.add(vuelto);
                billetera.add(vuelto);
            }

        } catch (PagoIncorrectoException | PagoInsuficienteException | NoHayProductoException | BandejaLlenaException e) {

            mensaje = e.getMessage();

            // Recuperar las monedas si la compra falla
            Moneda devuelta;
            while ((devuelta = exp.getVuelto()) != null) {
                billetera.add(devuelta);
            }
        }
    }


    /**
     * Retira el producto de la bandeja del expendedor y lo guarda.
     * Este metodo debe llamarse cuando el usuario hace click en la bandeja de retiro.
     *
     * @return true si hay un producto para retirar, false si no hay producto a retirar
     */
    public boolean recibirProducto() {
        Producto prod = exp.getProducto();
        if ( prod != null ) {
            productoRecibido = prod;
            mensaje = "Retiraste: " + prod.consumir() + ", Serie: " + prod.getNumSerie();
            return true;
        }
        mensaje = "No hay producto en la bandeja" ;
        return false;
    }

    /**
     * Retorna el producto de la compra y lo limpia
     *
     * @return producto recibido o ninguno si no hay
     */
    public Producto getProductoRecibido() {
        Producto productoReturn = productoRecibido;
        productoRecibido = null;
        return productoReturn;
    }

    /**
     * Retorna el ultimo mensaje recibido
     * @return mensaje en forma de String
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * Retorna el deposito en que estan las monedas
     * @return deposito de monedas
     */
    public Deposito<Moneda> getBilletera() {
        return billetera;
    }

    /**
     * Retorna el valor total de las monedas en la billetera
     * @return suma de todas las monedas
     */
    public int getTotalBilletera(){
        int total = 0;

        for(Moneda m : billetera.getDeposito()){
            total += m.getValor();
        }

        return total;
    }

    /**
     * Retorna el deposito en que esta el vuelto
     * @return deposito de monedas
     */
    public Deposito<Moneda> getVueltoRec() {
        return vueltoRec;
    }
}