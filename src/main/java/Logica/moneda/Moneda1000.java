package Logica.moneda;

/**
 * Clase que representa una moneda con valor de 1000
 */
public class Moneda1000 extends Moneda{

    /**
     * Constructor de Moneda de 1000
     * Inicializa la Moneda usando el constructor de la superclase
     */
    public Moneda1000(){
        super();
    }

    /**
     * Getter del valor de la Moneda
     * @return valor en int
     */
    @Override
    public int getValor() {
        return 1000;
    }
}
