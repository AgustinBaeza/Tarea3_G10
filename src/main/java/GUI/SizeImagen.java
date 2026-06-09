package GUI;

/**
 * Clase para guardar las dimensiones de una imagen
 * Permite guardar el ancho y alto que se usaran para dibujar imagenes
 * dentro de los paneles graficos del proyecto.
 */
public enum SizeImagen {

    PRODUCTO(55,65),
    MONEDA(45,45);

    private int ancho;
    private int alto;

    SizeImagen(int ancho, int alto){
        this.ancho = ancho;
        this.alto = alto;
    }

    /**
     * Obtiene el ancho de la imagen.
     * @return ancho de la imagen
     */
    public int getAncho(){
        return ancho;
    }

    /**
     * Obtiene el alto de la imagen.
     * @return alto de la imagen
     */
    public int getAlto(){
        return alto;
    }
}
