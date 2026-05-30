package Logica.moneda;


/**
 * Clase que representa una moneda con valor de 100
 */
public class Moneda100 extends Moneda{

    /**
     * Constructor de Moneda de 100
     * Inicializa la Moneda usando el constructor de la superclase
     */
    public Moneda100(){
        super();
    }

    /**
     * Getter del valor de la Moneda
     * @return valor en int
     */
    @Override
    public int getValor() {
        return 100;
    }
}
