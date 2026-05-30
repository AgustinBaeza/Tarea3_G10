package Logica.excepciones;

/**
 * Se lanza cuando no hay productos disponibles en el depósito
 */
public class NoHayProductoException extends Exception {

    public NoHayProductoException(String mensaje) {
        super(mensaje);
    }
}