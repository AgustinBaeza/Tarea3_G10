package Logica.moneda;

/**
 * Clase que representa una moneda con valor de 500
 */
public class Moneda500 extends Moneda{

    /**
     * Constructor de Moneda de 500
     * Inicializa la Moneda usando el constructor de la superclase
     */
    public Moneda500(){
        super();
    }

    /**
     * Getter del valor de la Moneda
     * @return valor en int
     */
    @Override
    public int getValor() {
        return 500;
    }
}