/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cuc.listas;

import java.util.ArrayList;

/**
 *
 * @author Sebastian
 */
public class DigitosListas<Integer> {

    private ListaDoble<Integer> lista;
    
    public DigitosListas(ListaDoble<Integer> lista) {
        this.lista = lista;
    }

    public ListaDoble<Integer> getLista() {
        return lista;
    }


}
