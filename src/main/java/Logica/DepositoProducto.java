package Logica;

import Logica.producto.*;

public class DepositoProducto {
    private Producto producto;

    public DepositoProducto() {
    }

    public void depositar(Producto prod) {
        if (prod == null) {
            producto = prod;
        } else {
            System.out.println("Lleno");
        }
    }

    public Producto retirar() {
        if (producto == null) {
            System.out.println("El depósito está vacío.");
            return null;
        }

        Producto aux = producto;
        producto = null;
        return aux;
    }
}
