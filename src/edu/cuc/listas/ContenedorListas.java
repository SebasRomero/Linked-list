/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cuc.listas;

/**
 *
 * @author Sebastian
 */
public class ContenedorListas<E> {
    private ListaSimple<E> lista1;
    private ListaSimple<E> lista2;
    private ListaDoble<E> lista3;
    private ListaDoble<E> lista4;

    public ContenedorListas(ListaSimple<E> lista1, ListaSimple<E> lista2) {
        this.lista1 = lista1;
        this.lista2 = lista2;
    }

    public ListaSimple<E> getLista1() {
        return lista1;
    }

    public ListaSimple<E> getLista2() {
        return lista2;
    }

    
    public ContenedorListas(ListaDoble<E> lista1, ListaDoble<E> lista2) {
        this.lista1 = lista1;
        this.lista2 = lista2;
    }

    public ListaDoble<E> getLista3() {
        return lista3;
    }

    public ListaDoble<E> getLista4() {
        return lista4;
    }
    
}
