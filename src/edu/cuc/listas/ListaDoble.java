package edu.cuc.listas;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Objects;

/**
 *
 * @author alexisdelahoz
 */
public class ListaDoble<E> extends ListaSimple<E> {

    protected NodoDoble<E> nodoFinal;

    @Override
    public void addFirst(E element) {
        NodoDoble<E> nodoNuevo = new NodoDoble<>(element);
        if (isEmpty()) {
            //Caso Lista Vacia
            nodoInicial = nodoFinal = nodoNuevo;
        } else {
            //Caso con al menos un elemento en la lista
            NodoDoble<E> nodoDobleInicial = (NodoDoble<E>) nodoInicial;
            nodoNuevo.siguiente = nodoDobleInicial; //nodoNuevo -> nodoInicial 
            nodoDobleInicial.previo = nodoNuevo;    //nodoNuevo <- nodoInicial
            nodoInicial = nodoNuevo;
        }
    }

    public void addLast(E element) {
        //Lista vacía
        NodoDoble<E> nodoNuevo = new NodoDoble<>(element);
        if (isEmpty()) {
            nodoInicial = nodoFinal = nodoNuevo;
        } else {
            //Lista con elementos            
            nodoFinal.siguiente = nodoNuevo; //nodoFinal -> nodoNuevo            
            nodoNuevo.previo = nodoFinal;   //nodoFinal <- nodoNuevo
            nodoFinal = nodoNuevo;          //              nodoFinal
        }
    }

    public void addElement(E element, int index) {
        NodoDoble<E> nodoNuevo = new NodoDoble<>(element);
        if (isEmpty()) {
            nodoInicial = nodoFinal = nodoNuevo;
        } else {
            if (index == 0) {
                addFirst(element);
            } else {
                if (index < 0 || index > size() + 1) {
                    throw new IndexOutOfBoundsException("El index: " + index + " no se encuentra.");
                } else {

                    NodoDoble<E> nodoActual = (NodoDoble<E>) nodoInicial;
                    int contador = 0;
                    while (nodoActual != null && contador < index - 1) {
                        contador++;
                        nodoActual = (NodoDoble<E>) nodoActual.siguiente;
                    }
                    if (nodoActual.siguiente == null) {
                        addLast(element);
                    } else {
                        if (nodoActual == null) {
                            throw new IndexOutOfBoundsException("El index: " + index + " no se encuentra.");
                        } else {

                            NodoDoble<E> almacenado = (NodoDoble<E>) nodoActual.siguiente;
                            nodoActual.setSiguiente(nodoNuevo);
                            nodoNuevo.setSiguiente(almacenado);

                            ((NodoDoble<E>) nodoNuevo.siguiente).previo = nodoNuevo;
                            nodoNuevo.previo = nodoActual;

                        }
                    }
                }
            }
        }

    }

    @Override
    public void clear() {
        nodoInicial = nodoFinal = null;
    }

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
    public E removeFirst() {
        return super.removeFirst(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean remove(E element) {
        if (isEmpty()) {
            return false;
        } else {
            // Nodo Inicial
            if (nodoInicial.dato.equals(element)) {
                nodoInicial = nodoInicial.siguiente;
                ((NodoDoble<E>) nodoInicial).previo = null;
                return true;
            } else {
                //Búsqueda
                NodoDoble<E> nodoActual = (NodoDoble<E>) nodoInicial;
                while (nodoActual != null && !nodoActual.dato.equals(element)) {
                    nodoActual = (NodoDoble<E>) nodoActual.siguiente;
                }
                //Dato no encontrado
                if (nodoActual == null) {
                    return false;
                } else {
                    //Ultimo nodo
                    NodoDoble<E> nodoPrevio = nodoActual.previo;
                    if (nodoActual.siguiente == null) {
                        nodoPrevio.siguiente = null;
                        nodoFinal = nodoPrevio;
                        return true;
                    } else {
                        //Nodo Intermedio
                        nodoPrevio.siguiente = nodoActual.siguiente;
                        ((NodoDoble<E>) nodoActual.siguiente).previo = nodoPrevio;
                        return true;
                    }
                }
            }
        }
    }

    /**
     * Inversion de la lista por enlaces
     */
    public void invertir() {
        if (!isEmpty() && size() >= 2) {
            NodoDoble<E> nodoActual = (NodoDoble<E>) nodoFinal;
            NodoDoble<E> nodoAnterior = nodoActual.previo;
            NodoDoble<E> nodoPrevio = nodoAnterior.previo;
            while (nodoActual != nodoInicial) {
                nodoActual.siguiente = nodoAnterior;
                nodoAnterior.previo = nodoActual;
                nodoActual = nodoAnterior;
                nodoAnterior = nodoPrevio;
                if (nodoPrevio != null) {
                    nodoPrevio = nodoPrevio.previo;
                }
            }
            nodoFinal.previo = null;
            nodoInicial.siguiente = null;
            NodoDoble<E> temp = nodoFinal;
            nodoFinal = (NodoDoble<E>) nodoInicial;
            nodoInicial = temp;
        }
    }

    /**
     * Segmentar la lista en 2 partes
     *
     * @return
     */
    public ContenedorListas<E> segmentar() {
        if (isEmpty()) {
            return null;
        } else {
            if (size() % 2 == 0) {
                return null;
            } else {
                NodoDoble<E> nodoActual = (NodoDoble<E>) nodoInicial;
                ListaDoble<E> nuevaLista1 = new ListaDoble<>();
                int contador = 0;
                while (nodoActual != null && contador != size() / 2) {
                    contador++;
                    nuevaLista1.addLast(nodoActual.dato);
                    nodoActual = (NodoDoble<E>) nodoActual.siguiente;
                }
                ListaDoble<E> nuevaLista2 = new ListaDoble<>();
                nodoActual = (NodoDoble<E>) nodoActual.siguiente;
                while (nodoActual != null) {
                    nuevaLista2.addLast(nodoActual.dato);
                    nodoActual = (NodoDoble<E>) nodoActual.siguiente;
                }
                ContenedorListas listasContenidas = new ContenedorListas<>(nuevaLista1, nuevaLista2);
                return listasContenidas;
            }
        }
    }

    /**
     * Agregar elemento antes de la aparición de otro
     */
    public void addBefore(E element, E elementToFind) {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("El elemento no se encuentra");
        } else {
            int contador = 0;
            NodoDoble<E> nodoActual = (NodoDoble<E>) nodoInicial;
            while (nodoActual != null && !nodoActual.getDato().equals(elementToFind)) {
                contador++;
                nodoActual = (NodoDoble<E>) nodoActual.siguiente;
            }
            if (nodoActual == null) {
                throw new IndexOutOfBoundsException("El elemento no se encuentra");
            } else {
                addElement(element, contador);

            }
        }
    }

    /**
     * Indica la última posición en donde se encontró un elemento
     *
     * @param element
     * @return
     */
    public int ultimaAparicion(E element) {
        if (isEmpty()) {
            return -1;
        } else {
            NodoDoble<E> nodoActual = nodoFinal;
            int contador = size();
            while (nodoActual != null) {
                contador--;
                if (nodoActual.dato.equals(element)) {
                    return contador;
                }
                nodoActual = nodoActual.previo;
            }

            return contador;
        }
    }

    /**
     * Indica los elementos que sólo tienen una aparición en la lista
     *
     * @return
     */
    public ListaDoble<E> elementosSoloUnaAparicion() {
        if (isEmpty()) {
            return null;
        } else {
            NodoDoble<E> nodoActual = (NodoDoble<E>) nodoInicial;
            ListaDoble<E> listaNueva = new ListaDoble<>();
            while (nodoActual != null) {
                if (numVecesQueSeRepite(nodoActual.dato) == 1) {
                    listaNueva.addLast(nodoActual.dato);
                }
                nodoActual = (NodoDoble<E>) nodoActual.siguiente;
            }
            return listaNueva;
        }
    }

    /**
     * Agrega una lista al final, creando una sola
     *
     * @param lista
     */
    public void agregarListaLast(ListaDoble<E> lista) {
        for (int i = 0; i < lista.size(); i++) {
            addLast(lista.get(i));
        }

    }

    /**
     * Agrega una lista al comienzo, creando una sola
     * @param lista 
     */
    public void agregarListaFirst(ListaDoble<E> lista) {
        for (int i = 0; i < lista.size(); i++) {
            addFirst(lista.get(i));
        }
    }

    public boolean recorridoHaciaIzq() {
        if (isEmpty()) {
            return false;
        } else {
            NodoDoble<E> nodoActual = (NodoDoble<E>) nodoFinal;
            while (nodoActual != null) {
                System.out.println(nodoActual.dato);
                nodoActual = (NodoDoble<E>) nodoActual.previo;
            }
            if (nodoActual == null) {
                return true;
            } else {
                return false;

            }
        }

    }

    /**
     * Nodo Doble
     */
    protected class NodoDoble<E> extends NodoSimple<E> {

        NodoDoble<E> previo;

        public NodoDoble(E dato) {
            super(dato);
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 19 * hash + Objects.hashCode(this.previo);
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
            final NodoDoble<?> other = (NodoDoble<?>) obj;
            if (!Objects.equals(this.dato, other.dato)) {
                return false;
            }
            return true;
        }

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
    public static ListaDoble abrir(String nombreArchivo) throws
            FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream fileInput = new FileInputStream(nombreArchivo);
        ObjectInputStream objectInput = new ObjectInputStream(fileInput);
        ListaDoble listaLeida = (ListaDoble) objectInput.readObject();
        return listaLeida;
    }
}
