package Logica.producto;

import Logica.OpcProducto;

/**
 * Clase que representa un dulce Super8 en el expendedor
 */
public class Super8 extends Dulce {

    /**
     * Constructor de la clase Super8, de enum OpcProducto se extrae su tag y precio
     * y envía tanto el numero de serie como el tag y el precio del dulce Super8 al constructor de la superclase Dulce
     * y esta ultima, a su vez, envia esos atributos a la superclase Producto
     */
    public Super8(int numSerie){
        super(numSerie, OpcProducto.SUPER8);
    }

    /**
     * Metodo que devuelve el sabor del dulce
     * @return sabor del dulce mediante el string "Super8"
     */
    @Override
    public String consumir(){
        return "Super8";
    }
}
