package Logica;

import Logica.producto.*;

/**
 * Deposito de capacidad unica para el producto que se haya escogido comprar
 * Representa la ranura en la maquina de la cual el comprador puede retirar su producto comprado
 */
public class DepositoRetiro {
    private Producto producto;

    public DepositoRetiro() {
        this.producto = null;
    }

    /**
     * Metodo para depositar el producto en la ranura del deposito, solo acepta si el espacio esta libre
     * @param prod producto de la compra
     */
    public void depositar(Producto prod) {
        if (producto == null) {
            producto = prod;
        } else {
            System.out.println("Lleno");
        }
    }

    /**
     * Metodo para retirar el producto de la ranura del deposito, dejando la ranura vacio
     * @return producto que estaba en el deposito para retirar
     */
    public Producto retirar() {
        if (producto == null) {
            System.out.println("El depósito está vacío.");
            return null;
        }

        Producto aux = producto;
        producto = null;
        return aux;
    }

    /**
     * Metodo para ver el estado actual del deposito especial, utilizado posteriormente en la vista
     * @return producto en el deposito actual
     */
    public Producto verDepositoRetiro(){
        return producto;
    }

    /**
     * Metodo para verificar si el deposito especial posee algun producto
     * @return True si es que la bandeja de retiro posee un producto dentro, False de lo contrario
     */
    public boolean tieneProducto() {
        return producto != null;
    }
}
