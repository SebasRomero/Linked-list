/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cuc.pruebas;

import edu.cuc.listas.ListaSimple;

/**
 *
 * @author Sebastian
 */
public class PruebaListaEliminar {
    public static void main(String[] args) {
        ListaSimple<String> lista = new ListaSimple<>();
        System.out.println("Eliminar (vacía): "+lista.remove("PC"));
        lista.addFirst("Casa");
        lista.addFirst("Carro");
        lista.addFirst("PC");
        lista.addFirst("Cielo");
        lista.addFirst("Mesa");
        System.out.println("Lista: "+lista);
        System.out.println("Eliminar (nodo inicial): "+lista.remove("Mesa"));
        System.out.println("Lista: "+lista);
        System.out.println("Eliminar (no está): "+lista.remove("Java"));
        System.out.println("Lista: "+lista);
        System.out.println("Eliminar (nodo final): "+lista.remove("Casa"));
        System.out.println("Lista: "+lista);
        System.out.println("Eliminar (intermedio): "+lista.remove("PC"));
        System.out.println("Lista: "+lista);
    }
}
