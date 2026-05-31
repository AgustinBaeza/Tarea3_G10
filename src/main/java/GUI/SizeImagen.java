package GUI;

public enum SizeImagen {

    PRODUCTO(55,65),
    MONEDA(45,45);

    private int ancho;
    private int alto;

    SizeImagen(int ancho, int alto){
        this.ancho = ancho;
        this.alto = alto;
    }

    public int getAncho(){
        return ancho;
    }
    public int getAlto(){
        return alto;
    }
}
