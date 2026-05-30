package Logica.producto;

import Logica.OpcProducto;

/**
 * Clase abstracta que representa a los distintos dulces en el expendedor
 * Hereda atributos de clase Producto
 */
public abstract class Dulce extends Producto {

    /**
     * Constructor de la clase Dulce
     * Actúa como puente en la inicializacion, traspasando el numero de serie y el tipo de producto
     * al constructor de la superclase Producto.
     * @param numSerie numero de serie del dulce
     * @param producto tipo de producto
     */
    public Dulce(int numSerie, OpcProducto producto){
        super(numSerie, producto);
    }
}

