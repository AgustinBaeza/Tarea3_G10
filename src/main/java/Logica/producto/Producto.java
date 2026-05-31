package Logica.producto;

import Logica.OpcProducto;
import java.awt.Graphics;
/**
 * Clase abstracta que se utiliza polimorficamente para representar un producto dentro del expendedor
 * Establece los atributos basicos de cada bebida y dulce
 */

public abstract class Producto {

    private int numSerie;
    private OpcProducto producto;

    protected int x; // se declaran protected para que clases hijas puedan utilizarlas
    protected int y;

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
     * Setter de las coordenadas (x,y) del producto para mostrar mediante Vista
     * @param x coordenada x del producto
     * @param y coordenada y del producto
     */
    public void setXY(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * Getter de la coordenada x del producto
     * @return coordenada x del producto como int
     */
    public int getX(){
        return x;
    }

    /**
     * Getter de la coordenada y del producto
     * @return coordenada y del producto como int
     */
    public int getY(){
        return y;
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
