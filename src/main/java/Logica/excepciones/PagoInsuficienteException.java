package Logica.excepciones;

/**
 * Se lanza cuando el pago no alcanza el precio del producto
 */
public class PagoInsuficienteException extends Exception {

    public PagoInsuficienteException(String mensaje) {
        super(mensaje);
    }
}
