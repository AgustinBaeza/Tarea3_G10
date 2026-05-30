package Logica;

/**
 * Enumeracion de las opciones disponibles de productos en el expendedor, cada producto cumple con tener un tag asociado y un precio respectivo
 * los cuales estan representados en las constantes definidas dentro de la Enumeración
 */
public enum OpcProducto {

    /** Bebida CocaCola con tag 1 y precio $1500*/
    COCA_COLA(1,1500),

    /** Bebida Sprite con tag 2 y precio $1000*/
    SPRITE(2,1000),

    /** Bebida Fanta con tag 3 y precio $800*/
    FANTA(3,800),

    /** Dulce Snickers con tag 4 y precio $700*/
    SNICKERS(4,700),

    /** Dulce Super8 con tag 5 y precio $300*/
    SUPER8(5,300);

    /** Tag identificador del producto*/
    private int tag;

    /** Precio del producto*/
    private int precio;

    /**
     * Constructor del enum OpcProducto
     * Se encarga de asignar el tag y precio a cada constante definida previamente
     * @param tag tag identificador del producto
     * @param precio valor del producto
     */
    OpcProducto(int tag, int precio){
        this.tag = tag;
        this.precio = precio;
    }

    /**
     * Getter del tag del producto
     * @return tag del producto como int
     */
    public int getTag(){
        return tag;
    }

    /**
     * Getter del precio del producto
     * @return valor del producto como int
     */
    public int getPrecio(){
        return precio;
    }

}


