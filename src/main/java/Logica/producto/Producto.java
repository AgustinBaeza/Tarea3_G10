package Logica.producto;

import Logica.OpcProducto;

/**
 * Clase abstracta que se utiliza polimorficamente para representar un producto dentro del expendedor
 * Establece los atributos basicos de cada bebida y dulce
 */

public abstract class Producto {

    /** Numero de serie del producto*/
    private int numSerie;
    /** Tipo de producto*/
    private OpcProducto producto;

    /**
     * Constructor de la clase Producto
     * Inicializa los atributos numero de serie y tipo que todo producto debe poseer
     * @param numSerie Numero de serie del producto
     * @param producto Tipo de producto
     */
    public Producto(int numSerie, OpcProducto producto){
        this.numSerie = numSerie;
        this.producto = producto;
    }

    /**
     * Getter del numero de serie del producto
     * @return numero de serie como int
     */
    public int getNumSerie(){
        return numSerie;
    }

    /**
     * Getter de la opcion de producto
     * @return tipo de producto como OpcProducto
     */
    public OpcProducto getProducto(){
        return producto;
    }

    /**
     * Getter del precio del producto, hace llamado a metodo dentro de enum OpcProducto
     * @return precio de la opcion de producto como int
     */
    public int getPrecio(){
        return producto.getPrecio();
    }

    /**
     * Metodo abstracto que simboliza el consumo del producto, varía según de que producto se trate
     * @return sabor del producto como string con el nombre del producto
     */
    public abstract String consumir();
}
