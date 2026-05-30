package Logica.producto;


import Logica.OpcProducto;

/**
 * Clase abstracta que representa a las distintas bebidas en el expendedor
 * Hereda atributos de clase Producto
 */
public abstract class Bebida extends Producto {

    /**
     * Constructor de la clase Bebida
     * Actúa como puente en la inicializacion, traspasando el numero de serie y el tipo de producto
     * al constructor de la superclase Producto.
     * @param numSerie numero de serie de la bebida
     * @param producto tipo de producto
     */
    public Bebida(int numSerie, OpcProducto producto){
        super(numSerie, producto);
    }
}

