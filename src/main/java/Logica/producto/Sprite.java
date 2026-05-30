package Logica.producto;

import Logica.OpcProducto;

/**
 * Clase que representa una bebida Sprite en el expendedor
 */
public class Sprite extends Bebida {

    /**
     * Constructor de la clase Sprite, de enum OpcProducto se extrae su tag y precio
     * Hace llamado a constructor de la superclase Bebida (que a su vez hace llamado al de Producto)
     * para asignar tanto su numero de serie como tag y precio de la bebida Sprite
     * @param numSerie numero de serie de la bebida
     */
    public Sprite(int numSerie){
        super(numSerie, OpcProducto.SPRITE);
    }

    /**
     * Metodo que devuelve el sabor de la bebida
     * @return sabor de la bebida
     */
    @Override
    public String consumir(){
        return "Sprite";
    }
}
