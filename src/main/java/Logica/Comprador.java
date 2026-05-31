package Logica;

import Logica.excepciones.*;
import Logica.moneda.*;
import Logica.producto.*;

/**
 * Clase que se encarga de simular un comprador.
 * Recibe una Moneda, un producto a querer comprar, y el Expendedor en que se compra.
 */
public class Comprador {
    private Deposito<Moneda> billetera;
    private Producto productoRecibido;
    private Deposito<Moneda> vueltoRec;
    private String Mensaje;
    private Expendedor exp;


    /**
     * Constructor del Comprador
     * Inicializa la billetera con monedas suficientes para compras
     *
     * @param exp expendedor que interactua con el comprador
     */
    public Comprador(Expendedor exp){
        this.exp = exp;
        this.billetera = new Deposito<Moneda>();
        this.vueltoRec = new Deposito<Moneda>();
        this.productoRecibido = null;
        this.Mensaje = "Selecciona una moneda y un producto.";

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
     * Intenta realizar compras usando una moneda y el tag del producto deseado
     * Si es exitosa, recoge el producto y el vuelto del expendedor
     *
     * @param valorMoneda valor de la moneda a usar
     * @param tagProducto tag del producto usando OpcProducto
     */
    public void comprar(int valorMoneda, int tagProducto){

        Moneda monedaAUsar = null;

        for(Moneda m : billetera.getDeposito()){
            if(m.getValor() == valorMoneda){
                monedaAUsar = m;
                break;
            }
        }

        if( monedaAUsar == null){
            Mensaje = "No tienes monedas de" + valorMoneda + "$ en tu billetera";
            return;
        }

        //sacar moneda antes de usar
        billetera.remove(monedaAUsar);

        try{

            exp.comprarProducto(monedaAUsar, tagProducto);

            productoRecibido = exp.getProducto();
            Mensaje = "Compraste "+ productoRecibido.consumir() +", Serie: " + productoRecibido.getNumSerie();

            Moneda vuelto;
            while ((vuelto = exp.getVuelto()) != null){
                vueltoRec.add(vuelto);
                billetera.add(vuelto);
            }
        }
        catch (PagoIncorrectoException | PagoInsuficienteException | NoHayProductoException e){

            Mensaje = e.getMessage();

            //Recuperar moneda
            Moneda devuelta = exp.getVuelto();
            if (devuelta != null) {
                billetera.add(devuelta);
            }
        }
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
        return Mensaje;
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