package Logica;


import Logica.excepciones.*;
import Logica.moneda.*;
import Logica.producto.*;

/**
 * Clase que permite llenar el expendedor de productos, comprarlos y calcular el vuelto.
 */
public class Expendedor {
    /**
     * Declara distintos depositos para los productos
     * y para el deposito del vuelto.
     */
    private Deposito<Bebida> coca;
    private Deposito<Bebida> sprite;
    private Deposito<Bebida> fanta;
    private Deposito<Dulce> super8;
    private Deposito<Dulce> snickers;
    private Deposito<Moneda> monVu;
    private Deposito<Moneda> monRec;
    private DepositoRetiro productoComprado;
    private static int contadorSerie = 0;

    /**
     * Constructor de la clase Expendedor.
     * Crea los distintos depositos para productos y monedas.
     * Contiene un ciclo for para llenarlos con i cantidad de productos cada uno.
     * @param cantidad la cantidad de cada producto que se desee
     */
    public Expendedor(int cantidad) {
        this.coca = new Deposito<Bebida>();
        this.sprite = new Deposito<Bebida>();
        this.fanta = new Deposito<Bebida>();
        this.super8 = new Deposito<Dulce>();
        this.snickers = new Deposito<Dulce>();
        this.monVu = new Deposito<Moneda>();
        this.monRec = new Deposito<Moneda>();
        this.productoComprado = new DepositoRetiro();

        rellenarDepositos(cantidad);
    }

    public void rellenarDepositos(int cantidad) {
        for (int i = 0; i < cantidad; i++) {

            // cada depósito de productos se limita a un máximo de 10 productos simultaneos
            if (coca.depositoSize() < 10) {
                coca.add(new CocaCola(contadorSerie++));
            }
            if (sprite.depositoSize() < 10) {
                sprite.add(new Sprite(contadorSerie++));
            }
            if (fanta.depositoSize() < 10) {
                fanta.add(new Fanta(contadorSerie++));
            }
            if (super8.depositoSize() < 10) {
                super8.add(new Super8(contadorSerie++));
            }
            if (snickers.depositoSize() < 10) {
                snickers.add(new Snickers(contadorSerie++));
            }
        }
    }

    public void rellenarDepositosVacios() {

        // si algun deposito esta vacio, se rellena en la llamada del metodo
        if (coca.depositoSize() == 0) {
            for (int i = 0; i < 10; i++) coca.add(new CocaCola(contadorSerie++));
        }
        if (sprite.depositoSize() == 0) {
            for (int i = 0; i < 10; i++) sprite.add(new Sprite(contadorSerie++));
        }
        if (fanta.depositoSize() == 0) {
            for (int i = 0; i < 10; i++) fanta.add(new Fanta(contadorSerie++));
        }
        if (super8.depositoSize() == 0) {
            for (int i = 0; i < 10; i++) super8.add(new Super8(contadorSerie++));
        }
        if (snickers.depositoSize() == 0) {
            for (int i = 0; i < 10; i++) snickers.add(new Snickers(contadorSerie++));
        }
    }

    public void rellenarVuelto(int cantidad) {
        for (int i = 0; i < cantidad; i++) {

            // el deposito de monedas de vuelto se limita a un tope de 10 simultaneas
            if (monVu.depositoSize() < 10) {
                monVu.add(new Moneda100());
            }
        }
    }

    /**
     * Metodo para comprar productos, recibe una moneda y el numero del producto que se desee comprar,
     * luego analiza cada caso posible y devuelve lo correspondiente en cada uno.
     * Tambien calcula el vuelto si corresponde.
     * @param mon1 moneda con la que se compra el producto
     * @param numeroProducto numero del producto que se desee comprar
     * @return el producto comprado
     * @throws PagoIncorrectoException si la moneda es nula
     * @throws PagoInsuficienteException si el dinero es insuficiente
     * @throws NoHayProductoException si el producto no existe o no hay stock
     */
    public void comprarProducto(Moneda mon1, int numeroProducto)
            throws PagoIncorrectoException, PagoInsuficienteException, NoHayProductoException {

        /* Verificar que la moneda y el producto no sean nulos */
        if (mon1 == null) {
            throw new PagoIncorrectoException("No se ingreso moneda");
        }

        /* Convierte el numero al enum del producto deseado*/
        OpcProducto producto = null;
        if ( numeroProducto == OpcProducto.COCA_COLA.getTag()){
            producto = OpcProducto.COCA_COLA;
        }
        if ( numeroProducto == OpcProducto.SPRITE.getTag()){
            producto = OpcProducto.SPRITE;
        }
        if ( numeroProducto == OpcProducto.FANTA.getTag()){
            producto = OpcProducto.FANTA;
        }
        if ( numeroProducto == OpcProducto.SUPER8.getTag()){
            producto = OpcProducto.SUPER8;
        }
        if ( numeroProducto == OpcProducto.SNICKERS.getTag()){
            producto = OpcProducto.SNICKERS;
        }

        // Verificar que el producto sea valido
        if (producto == null) {
            monVu.add(mon1);
            throw new NoHayProductoException("Selección de producto inválida");
        }

        /* Verificar que la moneda cubre el precio del producto */
        if (mon1.getValor() < producto.getPrecio()) {
            monVu.add(mon1);
            throw new PagoInsuficienteException("Dinero insuficiente para comprar el producto");
        }

        Producto productoAComprar = obtenerDelDeposito(producto);
        if (productoAComprar == null) {
            monVu.add(mon1);
            throw new NoHayProductoException("No hay stock del producto");
        }

        // limite de 12 monedas recaudadas en el deposito
        if (monRec.depositoSize() < 12) {
            monRec.add(mon1);
        }
        productoComprado.depositar(productoAComprar);




        /* Calculo de vuelto */
        int vuelto = mon1.getValor() - producto.getPrecio();
        vuelto = depositarVuelto(vuelto);


    }

    public Producto obtenerDelDeposito(OpcProducto producto){

        /* Otorga el producto deseado */
        Producto productoAComprar = null;
        if (producto == OpcProducto.COCA_COLA) {
            productoAComprar = coca.get();
        }
        if (producto == OpcProducto.SPRITE) {
            productoAComprar = sprite.get();
        }
        if (producto == OpcProducto.FANTA) {
            productoAComprar = fanta.get();
        }
        if (producto == OpcProducto.SUPER8) {
            productoAComprar = super8.get();
        }
        if (producto == OpcProducto.SNICKERS) {
            productoAComprar = snickers.get();
        }
        return productoAComprar;
    }

    private int depositarVuelto(int vuelto){
        while (vuelto >= 1000) {
            monVu.add(new Moneda1000());
            vuelto -= 1000;
        }
        while (vuelto >= 500) {
            monVu.add(new Moneda500());
            vuelto -= 500;
        }
        while (vuelto >= 100) {
            monVu.add(new Moneda100());
            vuelto -= 100;
        }
        return vuelto;
    }

    public Producto getProducto(){
        return productoComprado.retirar();
    }

    /**
     * Getter del vuelto
     * @return una moneda del deposito del vuelto
     */
    public Moneda getVuelto(){
        return monVu.get();
    }

    public Deposito<Bebida> getCoca(){
        return coca;
    }

    public Deposito<Bebida> getSprite(){
        return sprite;
    }

    public Deposito<Bebida> getFanta(){
        return fanta;
    }

    public Deposito<Dulce> getSuper8(){
        return super8;
    }

    public Deposito<Dulce> getSnickers(){
        return snickers;
    }

    public Deposito<Moneda> getMonVu(){
        return monVu;
    }

    public Deposito<Moneda> getMonRec(){
        return monRec;
    }

    // getter usado unicamente para ver el producto en el deposito de retiro
    public DepositoRetiro verProductoComprado(){
        return productoComprado;
    }

}