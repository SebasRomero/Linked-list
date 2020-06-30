/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cuc.pruebas;

import edu.cuc.listas.ListaDoble;
import edu.cuc.listas.ListaSimple;

/**
 *
 * @author alexisdelahoz
 */
public class PruebaLista1 {

    public static void main(String[] args) {

        ListaDoble<Integer> lista1 = new ListaDoble<>();
        ListaDoble<Integer> lista2 = new ListaDoble<>();
        
        lista1.addLast(1);
        lista1.addLast(3);
        lista1.addLast(20);
        lista1.addLast(8);
        lista1.addLast(4);
        lista1.addLast(11);
        lista1.addLast(12);
        lista1.addLast(2);
        lista1.addLast(13);
        lista1.addLast(26);
        lista1.addLast(10);

        System.out.println("La lista es: " + lista1);
        
    }
}
