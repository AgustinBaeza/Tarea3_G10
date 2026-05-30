package Logica.producto;

import Logica.OpcProducto;

/**
 * Clase que representa una bebida CocaCola en el expendedor
 * Implementa la logica de consumo
 */
public class CocaCola extends Bebida {

    /**
     * Constructor de la clase CocaCola, de enum OpcProducto se extrae su tag y precio
     * Hace llamado a constructor de la superclase Bebida (que a su vez hace llamado al de Producto)
     * para asignar tanto su numero de serie como tag y precio de la bebida Coca Cola
     * @param numSerie numero de serie de la bebida
     */
    public CocaCola(int numSerie){
        super(numSerie, OpcProducto.COCA_COLA);
    }

    /**
     * Metodo que devuelve el sabor de la bebida
     * @return sabor de la bebida mediante un string con su nombre "CocaCola"
     */
    @Override
    public String consumir(){
        return "CocaCola";
    }
}
