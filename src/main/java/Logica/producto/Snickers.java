package Logica.producto;

import Logica.OpcProducto;

/**
 * Clase que representa un dulce Snickers en el expendedor
 */
public class Snickers extends Dulce {

    /**
     * Constructor de la clase Snickers, de enum OpcProducto se extrae su tag y precio
     * y envía tanto el numero de serie como el tag y el precio del dulce Snickers al constructor de la superclase Dulce
     * y esta ultima, a su vez, envia esos atributos a la superclase Producto
     * @param numSerie numero de serie del dulce
     */
    public Snickers(int numSerie){
        super(numSerie, OpcProducto.SNICKERS);
    }

    /**
     * Metodo que devuelve el sabor del dulce
     * @return sabor del dulce mediante el string "Snickers"
     */
    @Override
    public String consumir(){
        return "Snickers";
    }
}
