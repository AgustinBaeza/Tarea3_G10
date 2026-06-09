package Logica.excepciones;

/**
 * Excepcion que se lanza cuando la bandeja del expendedor esta con
 * un producto sin retirar
 */
public class BandejaLlenaException extends Exception {

    public BandejaLlenaException(String message) {
        super(message);
    }
}
