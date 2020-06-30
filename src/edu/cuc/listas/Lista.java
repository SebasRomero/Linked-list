
package edu.cuc.listas;

/**
 * Interfaz Lista
 * @author adelahoz6
 */
public interface Lista<E> {
    void addFirst(E element);   
    void addLast(E element);   
    E removeFirst();
    E removeLast();
    boolean contains(E element);
    boolean isEmpty();
    boolean remove(E element);
    void clear();   
    int size();     
    E get(int index);
    ListaSimple posicionesElemento(E element);
    ListaSimple invertirLista();    
    void agregarElemento(E element, int index);
    boolean listasIguales(ListaSimple<E> lista2);
    void set(E element, int index);
    boolean moverDato(int posicionAmover, int posicionDestino);
    boolean removeDatosRepetidos(E element);
    int numVecesQueSeRepite(E element);
    ListaSimple elementosPosicionesPares();
    ListaSimple elementosPosicionesImpares();
    
}
