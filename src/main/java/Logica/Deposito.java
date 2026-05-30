package Logica;


import java.util.ArrayList;

/**
 * Clase Deposito que permite almacenar elementos de forma flexible mediante el uso de parámetros tipo T
 * Esta pensado para variar entre el almacenaje de Monedas y Productos dentro del expendedor
 */
public class Deposito<T>{

    /** ArrayList que corresponde al almacenamiento de elementos del deposito*/
    private ArrayList<T> almac;

    /**
     * Constructor de la clase Deposito, crea una nueva instancia inicializando el almacenamiento de elementos como un ArrayList
     */
    public Deposito(){
        almac = new ArrayList<>();
    }

    /**
     * Metodo para agregar elemento al final del depósito
     * @param elemento elemento a agregar
     */
    public void add(T elemento){
        almac.add(elemento);
    }

    /**
     * Metodo para extraer el primer elemento que se encuentre en el depósito
     * @return elemento retirado o null si el deposito esta vacio
     */
    public T get(){
        if(almac.size() > 0){
            return almac.remove(0);
        } else {
            return null;
        }
    }

}
