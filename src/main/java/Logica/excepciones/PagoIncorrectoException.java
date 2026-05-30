package Logica.excepciones;

/**
 * Se lanza cuando se intenta pagar con moneda null
 */
public class PagoIncorrectoException extends Exception {

    public PagoIncorrectoException(String mensaje) {
        super(mensaje);
    }
}