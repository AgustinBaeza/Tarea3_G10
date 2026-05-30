package Logica.producto;

import Logica.OpcProducto;

/**
 * Clase que representa una bebida Fanta en el expendedor
 */
public class Fanta extends Bebida {

    /**
     * Constructor de la clase Fanta, de enum OpcProducto se extrae su tag y precio
     * Hace llamado a constructor de la superclase Bebida (que a su vez hace llamado al de Producto)
     * para asignar tanto su numero de serie como tag y precio de la bebida Fanta
     * @param numSerie numero de serie de la bebida
     */
    public Fanta(int numSerie){
        super(numSerie, OpcProducto.FANTA);
    }

    /**
     * Metodo que devuelve el sabor del producto
     * @return sabor de la bebida mediante un string con su nombre "Fanta"
     */
    @Override
    public String consumir(){
        return "Fanta";
    }
}
