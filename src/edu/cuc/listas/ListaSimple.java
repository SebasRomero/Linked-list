package edu.cuc.listas;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Objects;

/**
 * Implementación Lista Simple
 *
 * @author adelahoz6
 */
public class ListaSimple<E> implements Lista<E>, Serializable {

    protected NodoSimple<E> nodoInicial;

    /**
     * Adicionar dato al inicio
     *
     * @param element
     */
    @Override
    public void addFirst(E element) {
        NodoSimple<E> nodoNuevo = new NodoSimple<>(element);
        if (isEmpty()) {
            //Caso Lista Vacia
            nodoInicial = nodoNuevo;
        } else {
            //Caso con al menos un elemento en la lista            
            nodoNuevo.siguiente = nodoInicial; //nodoNuevo -> nodoInicial
            nodoInicial = nodoNuevo;
        }
    }

    /**
     * Adicionar dato al final
     *
     * @param element
     */
    @Override
    public void addLast(E element) {
        NodoSimple<E> nodoNuevo = new NodoSimple<>(element);
        if (isEmpty()) {
            nodoInicial = nodoNuevo;
        } else {
            NodoSimple<E> actual = nodoInicial;
            int aux = 0;
            while (aux < size() - 1) {
                actual = actual.siguiente;
                aux++;
            }
            actual.setSiguiente(nodoNuevo);
        }
    }

    /**
     * Eliminar el primer dato
     *
     * @return
     */
    @Override
    public E removeFirst() {
        if (isEmpty()) {
            //Caso Lista Vacia
            return null;
        } else {
            //Caso lista con elementos
            E dato = nodoInicial.dato;
            nodoInicial = nodoInicial.siguiente; //nodoInicial -> nodoInicial
            return dato;
        }
    }

    /**
     * Eliminar el último dato
     *
     * @return
     */
    @Override
    public E removeLast() {
        if (isEmpty()) {
            return null;
        } else {
            NodoSimple<E> actual = nodoInicial;
            NodoSimple<E> previo = null;
            int aux = 0;
            while (aux < size() - 1) {
                previo = actual;
                actual = actual.siguiente;
                aux++;
            }
            if (actual.siguiente == null) {
                previo.siguiente = null;
            }
            E dato = actual.dato;

            return dato;
        }
    }

    /**
     * Indica si el valor buscado está en la lista
     *
     * @param datoABuscar
     * @return
     */
    @Override
    public boolean contains(E datoABuscar) {
        //Caso Lista Vacia
        if (isEmpty()) {
            return false;
        } else {
            //Caso lista con elementos
            NodoSimple<E> actual = nodoInicial;
            while (actual != null) {
                if (actual.dato.equals(datoABuscar)) {
                    return true;
                }
                actual = actual.siguiente;
            }

            return false;
        }
    }

    /**
     * Indica si la lista está vacía
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return nodoInicial == null;
    }

    /**
     * Elimina todos los elementos de la lista
     */
    @Override
    public void clear() {
        nodoInicial = null;
    }

    /**
     * Indica la cantidad de elementos en la lista
     *
     * @return
     */
    @Override
    public int size() {
        NodoSimple<E> actual = nodoInicial;
        int contador = 0;
        while (actual != null) {
            actual = actual.siguiente;
            contador++;
        }
        return contador;
    }

    @Override
    public String toString() {
        String info = "";
        NodoSimple<E> actual = nodoInicial;
        while (actual != null) {
            info += actual.dato + " ";
            actual = actual.siguiente;
        }
        return info;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.nodoInicial);
        return hash;
    }

    /**
     * Elimina el valor indicado
     *
     * @param element
     * @return
     */
    @Override
    public boolean remove(E element) {
        //Lista vacía
        if (isEmpty()) {
            return false;
        } else {
            //Nodo inicial 
            if (nodoInicial.dato.equals(element)) {
                removeFirst();
                return true;
            } else {
                //Búsqueda de información
                NodoSimple<E> previo = null;
                NodoSimple<E> actual = nodoInicial;
                while (actual != null && !actual.dato.equals(element)) { // O actual.dato.equals(element) == false                  
                    previo = actual;
                    actual = actual.siguiente;
                }
                //Primer caso: dato no encontrado
                if (actual == null) {
                    return false;
                } else {
                    //Caso encontrado
                    if (actual.siguiente == null) {
                        //Dato se encuentra en el último nodo
                        previo.siguiente = null;
                        return true;
                    } else {
                        //Nodo intermedio
                        previo.siguiente = actual.siguiente;
                        return true;
                    }
                }
            }
        }
    }

    /**
     * Indica el valor en la posición indicada
     *
     * @param index
     * @return
     */
    @Override
    public E get(int index) {
        if (isEmpty() || index < 0) {
            throw new IndexOutOfBoundsException("El index: " + index + " es erroneo.");
        } else {
            NodoSimple<E> actual = nodoInicial;
            int contador = 0;
            while (actual != null && contador < index) {//Si por ej, contador == index, se saldrá del ciclo y retornará el dato del nodo           
                contador++;
                actual = actual.siguiente;
            }
            if (actual == null) {
                return null;
            } else {
                return actual.dato;
            }
        }
    }

    /**
     * Indica las posiciones donde aparece el elemento indicado
     *
     * @param element
     * @return
     */
    @Override
    public ListaSimple<Integer> posicionesElemento(E element) {
        if (isEmpty()) {
            return null;
        } else {
            ListaSimple nuevaLista = new ListaSimple();
            NodoSimple<E> actual = nodoInicial;
            for (int i = 0; i < size(); i++) {
                if (actual.getDato().equals(element)) {
                    nuevaLista.addLast(i);
                }
                actual = actual.siguiente;
            }
            return nuevaLista;
        }
    }

    /**
     * Invierte la lista indicada
     *
     * @param lista
     * @return
     */
    @Override
    public ListaSimple<E> invertirLista() {
        if (isEmpty()) {
            return null;
        } else {
            NodoSimple<E> actual = nodoInicial;
            ListaSimple nuevaLista = new ListaSimple<>();
            for (int i = 0; i < size(); i++) {
                nuevaLista.addFirst(actual);
                actual = actual.siguiente;
            }
            return nuevaLista;
        }
    }

    /**
     * Agrega un elemento en la posición indicada
     *
     * @param element
     * @param index
     * @return
     */
    @Override
    public void agregarElemento(E element, int index) {
        NodoSimple<E> nodoNuevo = new NodoSimple<>(element);
        nodoNuevo.setDato(element);
        if (isEmpty()) {
            nodoInicial = nodoNuevo;
        } else {
            if (index < 0) {
                throw new IndexOutOfBoundsException("El index: " + index + " no se encuentra.");
            } else {

                if (index == 0) {
                    addFirst(element);

                } else {
                    NodoSimple<E> actual = nodoInicial;
                    int contador = 0;
                    while (actual != null && contador < index - 1) {
                        contador++;
                        actual = actual.siguiente;
                    }
                    if (actual.siguiente == null) {
                        addLast(element);
                    } else {
                        if (actual == null) {
                            throw new IndexOutOfBoundsException("El index: " + index + " no se encuentra.");
                        } else {
                            NodoSimple<E> almacenado = actual.siguiente;
                            actual.setSiguiente(nodoNuevo);
                            nodoNuevo.setSiguiente(almacenado);
                        }
                    }
                }
            }
        }
    }

    /**
     * Indica si las listas son iguales
     *
     * @param lista
     * @param lista2
     * @return
     */
    @Override
    public boolean listasIguales(ListaSimple lista2) {
        if (isEmpty() || lista2.size() != size()) {
            return false;
        } else {
            for (int i = 0; i < lista2.size(); i++) {
                if (!lista2.get(i).equals(get(i))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Cambia un elemento en la posición indicada
     *
     * @param element
     * @param index
     */
    @Override
    public void set(E element, int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("El index: " + index + " es erroneo");
        } else {
            if (isEmpty()) {
                throw new IndexOutOfBoundsException("La lista está vacía");
            } else {

                NodoSimple<E> actual = nodoInicial;
                int contador = 0;
                while (actual != null && contador < index) {//Búsqueda de información
                    contador++;
                    actual = actual.siguiente;
                }
                if (actual == null) {//Dato no encontrado
                    throw new IndexOutOfBoundsException("El index: " + index + " no se encuentra.");
                } else {
                    actual.setDato(element);
                }
            }
        }
    }

    /**
     * Mueve un elemento a la posición deseada
     *
     */
    @Override
    public boolean moverDato(int posicionAmover, int posiciondestino) {
        if (isEmpty() || posiciondestino > size() || posicionAmover > size()) {
            return false;
        } else {
            if (posicionAmover < 0 || posiciondestino < 0) {
                return false;
            } else {
                if (posicionAmover == posiciondestino) {
                    return false;
                } else {
                    NodoSimple<E> actual = nodoInicial;
                    NodoSimple<E> previo = null;
                    int contador = 0;
                    while (contador < posicionAmover) {
                        previo = actual;
                        contador++;
                        actual = actual.siguiente;
                    }
                    if (actual == null) {
                        return false;
                    } else {
                        if (actual.siguiente == null) {
                            agregarElemento(actual.getDato(), posiciondestino);
                            previo.siguiente = null;
                            return false;
                        } else {
                            if (previo == null) {
                                agregarElemento(actual.getDato(), posiciondestino);
                                removeFirst();
                            } else {
                                agregarElemento(actual.getDato(), posiciondestino);
                                previo.siguiente = actual.siguiente;
                            }
                            return true;
                        }
                    }
                }
            }
        }
    }

    /**
     * Elimina todos los datos repetidos de la lista
     *
     * @param element
     */
    @Override
    public boolean removeDatosRepetidos(E element) {
        if (isEmpty()) {
            return false;
        } else {
            NodoSimple<E> actual = nodoInicial;
            while (actual != null) {
                if (actual.getDato().equals(element)) {
                    remove(element);
                }
                actual = actual.siguiente;
            }
        }
        return true;
    }

    /**
     * Indica el número de veces que se repite un elemento
     *
     * @param element
     * @return
     */
    @Override
    public int numVecesQueSeRepite(E element) {
        if (isEmpty()) {
            return -1;
        } else {
            NodoSimple<E> nodoActual = nodoInicial;
            int contador = 0;
            while (nodoActual != null) {
                if (nodoActual.dato.equals(element)) {
                    contador++;
                }
                nodoActual = nodoActual.siguiente;
            }
            return contador;
        }
    }

    /**
     * Elimina los datos que elimine las apariciones de números consecutivos
     * donde el primero sea menor que el segundo
     *
     * @return
     */
    public boolean eliminacionNumConsecutivo() {
        int tamaño = size();
        if (isEmpty()) {
            return false;
        } else {
            NodoSimple<Double> nodoActual = (NodoSimple<Double>) nodoInicial;

            while (nodoActual != null && nodoActual.siguiente != null) {
                double actual = nodoActual.dato;
                double siguiente = nodoActual.siguiente.dato;
                double resultado = siguiente - actual;
                if (nodoActual.siguiente.dato == null) {
                    break;
                }
                if (resultado > 0) {
                    remove((E) nodoActual.dato);
                    remove((E) nodoActual.siguiente.dato);
                }
                nodoActual = nodoActual.siguiente;
            }
        }
        if (tamaño == size()) {
            return false;
        }
        return true;
    }

    /**
     * Método que saque los elementos indicados y los meta en una lista y los no
     * indicados en otra
     *
     * @return
     */
    public ContenedorListas<E> creacionDosListasApartirDeUnRango(int posUno, int posDos) {
        if (isEmpty()) {
            return null;
        } else {
            if (posDos < posUno) {
                return null;
            } else {
                if (posDos < 0 && posUno < 0) {
                    return null;
                } else {
                    ListaSimple<E> nuevaLista1 = new ListaDoble<>();
                    ListaSimple<E> nuevaLista2 = new ListaDoble<>();
                    int contador = 0;
                    NodoSimple<E> nodoActual = nodoInicial;
                    while (nodoActual != null) {
                        if (contador >= posUno && contador <= posDos) {
                            nuevaLista2.addLast(nodoActual.dato);
                        } else {
                            nuevaLista1.addLast(nodoActual.dato);
                        }
                        contador++;
                        nodoActual = nodoActual.siguiente;
                    }
                    ContenedorListas contenedorListas = new ContenedorListas(nuevaLista1, nuevaLista2);
                    return contenedorListas;
                }
            }
        }
    }

    /**
     * Mètodo que verifique si el nùmero total concatenado de los nodos es
     * divisible entre 11
     *
     * @return
     */
    public boolean verificacionDiv11() {
        if (isEmpty()) {
            return false;
        } else {
            if (elementosPosicionesPares().sumaElementos() == elementosPosicionesImpares().sumaElementos()) {
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * Método que indique los elementos de las posiciones pares
     *
     * @return
     */
    @Override
    public ListaSimple elementosPosicionesPares() {
        if (isEmpty()) {
            return null;
        } else {
            NodoSimple<E> nodoActual = nodoInicial;
            ListaSimple<E> listaPares = new ListaSimple<>();
            int contador = 0;
            while (nodoActual != null) {
                if (contador % 2 == 0) {
                    listaPares.addLast(nodoActual.dato);
                }
                nodoActual = nodoActual.siguiente;
                contador++;
            }
            return listaPares;
        }
    }

    /**
     * Método que indique los elementos de las posiciones impares
     *
     * @return
     */
    @Override
    public ListaSimple elementosPosicionesImpares() {
        if (isEmpty()) {
            return null;
        } else {
            NodoSimple<E> nodoActual = nodoInicial;
            ListaSimple<E> listaPares = new ListaSimple<>();
            int contador = 0;
            while (nodoActual != null) {
                if (!(contador % 2 == 0)) {
                    listaPares.addLast(nodoActual.dato);
                }
                nodoActual = nodoActual.siguiente;
                contador++;
            }
            return listaPares;
        }
    }
    
    /**
     * Método que sume los elementos de todos los nodos
     */
    public int sumaElementos(){
        if (isEmpty()) {
            return -1;
        } else {
            NodoSimple<E> nodoActual = nodoInicial;
            int almacenador = 0;
            while (nodoActual != null) {                
                almacenador =(Integer) nodoActual.dato + almacenador ;
                nodoActual = nodoActual.siguiente;
            }
            return almacenador;
        }
    }

    /**
     * Método que haga primero un bloque de los números pares y luego los impares
     * @return 
     */
    public ListaSimple<Integer> organizacionListaParesLuegoImpares(){
        if (isEmpty()) {
            return null;
        } else {
            NodoSimple<E> nodoActual = nodoInicial;
            ListaSimple<Integer> nuevaLista = new ListaSimple<>();
                    
            int actual = 0;
            while (nodoActual != null) {
                actual = (Integer) nodoActual.dato;
                if ((actual %2==0)) {
                    nuevaLista.addLast((Integer) nodoActual.dato);
                }
                nodoActual = nodoActual.siguiente;
            }
            nodoActual = nodoInicial;
            while (nodoActual != null) {                
                actual = (Integer) nodoActual.dato;
                if (!(actual%2 == 0)) {
                    nuevaLista.addLast((Integer) nodoActual.dato);
                }
                nodoActual = nodoActual.siguiente;
            }
        return nuevaLista;
        }
    }
    
    public void orgListaParesLuegoImpares(){
        if (isEmpty()) {
        }else{
            NodoSimple<E> nodoActual = nodoInicial;
            int actual;
            while (nodoActual != null) { 
                actual = (Integer) nodoActual.dato;
                if (!(actual % 2 == 0)) {
                    remove(nodoActual.dato);
                    addLast(nodoActual.dato);
                }
                nodoActual = nodoActual.siguiente;
            }
        }
    }
    
    /**
     * Clase Nodo Simple
     */
    protected class NodoSimple<E> implements Serializable {

        E dato;
        NodoSimple<E> siguiente;

        public NodoSimple(E dato) {
            this.dato = dato;
        }

        public E getDato() {
            return dato;
        }

        public void setDato(E dato) {
            this.dato = dato;
        }

        public NodoSimple<E> getSiguiente() {
            return siguiente;
        }

        public void setSiguiente(NodoSimple<E> siguiente) {
            this.siguiente = siguiente;
        }

        @Override
        public int hashCode() {
            int hash = 3;
            hash = 89 * hash + Objects.hashCode(this.dato);
            hash = 89 * hash + Objects.hashCode(this.siguiente);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final NodoSimple<?> other = (NodoSimple<?>) obj;
            if (!Objects.equals(this.dato, other.dato)) {
                return false;
            }
            return true;
        }

        @Override
        public String toString() {
            return dato.toString();
        }

    }

    /**
     * Método para serializar la instancia actual de la lista
     *
     * @param nombreArchivo
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void guardar(String nombreArchivo) throws FileNotFoundException, IOException {
        FileOutputStream fileOutput = new FileOutputStream(nombreArchivo);
        ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
        objectOutput.writeObject(this);
        objectOutput.close();
        fileOutput.close();
    }

    /**
     * Método para deserializar una lista
     *
     * @param nombreArchivo
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static ListaSimple abrir(String nombreArchivo) throws
            FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream fileInput = new FileInputStream(nombreArchivo);
        ObjectInputStream objectInput = new ObjectInputStream(fileInput);
        ListaSimple listaLeida = (ListaSimple) objectInput.readObject();
        return listaLeida;
    }

}
