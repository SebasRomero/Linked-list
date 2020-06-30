/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cuc.externos;

import edu.cuc.listas.DigitosListas;
import edu.cuc.listas.ListaDoble;
import edu.cuc.listas.ListaSimple;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author Sebastian
 */
public class Metodos {

    /**
     * Mayor de una lista de enteros
     */
    public static int mayorLista(ListaSimple<Integer> listado) {
        if (listado.isEmpty()) {
            return -1;
        } else {
            int mayor = listado.get(0);
            for (int i = 0; i < listado.size(); i++) {
                Integer actual = listado.get(i);
                if (actual > mayor) {
                    mayor = actual;
                }
            }
            return mayor;
        }
    }

    /**
     * Promedio de una lista de reales
     */
    public static double promedioLista(ListaSimple<Double> listado) {
        if (listado.isEmpty()) {
            return 0;
        } else {
            double contador = 0;
            for (int i = 0; i < listado.size(); i++) {
                contador = listado.get(i) + contador;
            }

            return contador / listado.size();
        }
    }

    /**
     * Invierte los elementos pares consecutivos de una lista
     *
     * @param listado
     * @return
     */
    public static ListaDoble<Integer> invertirSoloElementosPares(ListaDoble<Integer> listado) {
        ListaDoble<Integer> nuevaLista = new ListaDoble<>();
        if (listado.isEmpty() && listado.size() >= 2) {
            throw new IndexOutOfBoundsException("La lista está vacía");
        } else {
            ListaDoble<Integer> listaAlmacen = new ListaDoble<>();

            int actual = 0;
            int siguiente = 0;

            for (int i = 0; i < listado.size(); i++) {
                actual = listado.get(i);
                if (i < listado.size() - 1) {
                    siguiente = listado.get(i + 1);
                }
                if (actual % 2 == 0) {
                    listaAlmacen.addLast(actual);
                    if (siguiente % 2 == 0) {
                        listaAlmacen.addLast(siguiente);
                        i++;
                    } else {
                        listaAlmacen.invertir();
                        nuevaLista.agregarListaLast(listaAlmacen);
                        listaAlmacen.clear();
                    }
                } else {
                    listaAlmacen.invertir();
                    nuevaLista.agregarListaLast(listaAlmacen);
                    nuevaLista.addLast(actual);
                    listaAlmacen.clear();
                }
                if (listado.get(i + 1) == null) {
                    listaAlmacen.invertir();
                    nuevaLista.agregarListaLast(listaAlmacen);
                }
            }
        }
        return nuevaLista;
    }

    /**
     * Muestra los elementos consecutivos de la lista
     *
     * @param args
     * @return
     */
    public static ListaDoble<String> cadenasRepetidas(ListaDoble<String> listado) {
        ListaDoble<String> listaAlmacen = new ListaDoble<>();
        ListaDoble<String> listaNueva = new ListaDoble<>();
        if (listado.isEmpty()) {
            throw new IndexOutOfBoundsException("La lista está vacía");
        } else {
            String actual = null;
            String siguiente = null;
            String previo = null;
            for (int i = 0; i < listado.size(); i++) {
                previo = actual;
                actual = listado.get(i);
                siguiente = listado.get(i + 1);
                if (actual.equals(siguiente) || actual.equals(previo)) {
                    listaAlmacen.addLast(actual);
                    listaNueva.agregarListaLast(listaAlmacen);
                    listaAlmacen.clear();
                }

            }
        }
        return listaNueva;
    }

    /**
     * Invierte los elementos seleccionados en un rango
     *
     * @param args
     */
    public static ListaDoble<Integer> invertirDesde(ListaDoble<Integer> listado, int desde, int hasta) {
        ListaDoble<Integer> listaNueva = new ListaDoble<>();
        if (listado.isEmpty()) {
            throw new IndexOutOfBoundsException("La lista está vacía");
        } else {
            if (desde >= 0 && hasta < listado.size() && desde < hasta) {
                ListaDoble<Integer> almacen = new ListaDoble<>();
                int actual = 0;
                for (int i = 0; i < listado.size(); i++) {
                    actual = listado.get(i);
                    if (i >= desde && i <= hasta) {
                        almacen.addLast(actual);
                    } else {
                        listaNueva.addLast(actual);
                    }
                    if (hasta == i) {
                        almacen.invertir();
                        listaNueva.agregarListaLast(almacen);
                    }
                }
            } else {
                if (hasta <= listado.size() && desde >= 0 && hasta < desde) {
                    ListaDoble<Integer> almacen = new ListaDoble<>();
                    int actual = 0;
                    for (int i = listado.size() - 1; i >= 0; i--) {
                        actual = listado.get(i);
                        if (i >= hasta && i <= desde) {     //Desde = 3; Hasta = 1
                            almacen.addLast(actual);
                        } else {
                            listaNueva.addFirst(actual);

                        }
                        if (hasta == i) {
                            System.out.println("xd");
//                        listaNueva.invertir();
                            almacen.invertir();
                            listaNueva.agregarListaFirst(almacen);
                        }
                    }
                } else {
                    throw new IndexOutOfBoundsException("Verifique las posiciones");
                }
                return listaNueva;
            }
        }
        return listaNueva;
    }

    /**
     * Retorna los elementos con los dígitos especificados
     *
     * @param listado
     * @param cantidadDigitos
     * @return
     */
    public static ListaDoble<Integer>[] agrupacionCantidadDigitos(ListaDoble<Integer> listado) {
        ListaDoble<Integer> listaNueva = new ListaDoble<>();
        if (listado.isEmpty()) {
            throw new IndexOutOfBoundsException("La lista está vacía");
        } else {

        }
        return null;
    }

    /**
     * Eliminacion números que su suma sea 0
     *
     * @param listado
     * @param args
     */
    public static void eliminacionSumaCero(ListaDoble<Integer> listado) {
        int tamañoLista = listado.size();
        if (listado.isEmpty()) {
            throw new IndexOutOfBoundsException("La lista está vacía");
        } else {
            int actual;
            int siguiente = 0;
            for (int i = 0; i < listado.size(); i++) {
                actual = listado.get(i);

                if (i < listado.size() - 1) {
                    siguiente = listado.get(i + 1);
                }

                if (actual + siguiente == 0) {
                    listado.remove(actual);
                    listado.remove(siguiente);
                    i = 0;
                }
            }
        }
        if (listado.size() == tamañoLista) {
            throw new IndexOutOfBoundsException("La lista no tuvo elementos para eliminar");
        }
    }

    public static void main(String[] args) {
        ListaDoble<Integer> lista1 = new ListaDoble<>();
//        int[] vector = new int[3];
//        vector[0] = 3;
//        vector[1] = 5;
//        vector[2] = 3;
////        for (int i = 0; i < vector.length; i++) {
//            System.out.println(vector[1]);
//        }
//        System.out.println(vector[2]);
        lista1.addLast(1);
        lista1.addLast(2);
        lista1.addLast(5);
        lista1.addLast(51);

        System.out.println("La lista es: " + lista1);

        System.out.println("Los elementos con dígito especificado son: "+Metodos.agrupacionCantidadDigitos(lista1)[2]);

    }

}
