/**
 * Clase que representa una Cola (Queue) implementada con POO en Java.
 * Sigue el principio FIFO (First In, First Out).
 * 
 * @author Raul Heredia
 * @version 1.0
 */
public class Cola<T> {
    private Nodo<T> frente;
    private Nodo<T> fin;
    private int tamanio;

    /**
     * Clase interna Nodo para representar cada elemento de la cola.
     * Utiliza genéricos para aceptar cualquier tipo de dato.
     */
    private static class Nodo<T> {
        T dato;
        Nodo<T> siguiente;

        public Nodo(T dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    /**
     * Constructor de la clase Cola.
     * Inicializa la cola vacía.
     */
    public Cola() {
        this.frente = null;
        this.fin = null;
        this.tamanio = 0;
    }

    /**
     * Agrega un elemento al final de la cola (enqueue).
     * @param dato Elemento a agregar
     */
    public void enqueue(T dato) {
        Nodo<T> nuevoNodo = new Nodo<>(dato);
        if (estaVacia()) {
            frente = nuevoNodo;
            fin = nuevoNodo;
        } else {
            fin.siguiente = nuevoNodo;
            fin = nuevoNodo;
        }
        tamanio++;
        System.out.println("Elemento encolado: " + dato);
    }

    /**
     * Elimina y retorna el elemento del frente de la cola (dequeue).
     * @return Elemento del frente, o null si la cola está vacía
     */
    public T dequeue() {
        if (estaVacia()) {
            System.out.println("La cola está vacía. No se puede desencolar.");
            return null;
        }
        T dato = frente.dato;
        frente = frente.siguiente;
        if (frente == null) {
            fin = null;
        }
        tamanio--;
        System.out.println("Elemento desencolado: " + dato);
        return dato;
    }

    /**
     * Retorna el elemento del frente sin eliminarlo (peek).
     * @return Elemento del frente, o null si la cola está vacía
     */
    public T peek() {
        if (estaVacia()) {
            System.out.println("La cola está vacía. No hay frente.");
            return null;
        }
        return frente.dato;
    }

    /**
     * Verifica si la cola está vacía.
     * @return true si está vacía, false en caso contrario
     */
    public boolean estaVacia() {
        return frente == null;
    }

    /**
     * Retorna el tamaño actual de la cola.
     * @return Número de elementos en la cola
     */
    public int getTamanio() {
        return tamanio;
    }

    /**
     * Muestra todos los elementos de la cola.
     */
    public void mostrarCola() {
        if (estaVacia()) {
            System.out.println("La cola está vacía.");
            return;
        }
        System.out.println("\n=== COLA ACTUAL ===");
        System.out.println("Tamaño: " + tamanio);
        System.out.println("FRENTE ->");
        Nodo<T> actual = frente;
        while (actual != null) {
            System.out.println("  [" + actual.dato + "]");
            actual = actual.siguiente;
        }
        System.out.println("<- FINAL");
        System.out.println("==================\n");
    }

    /**
     * Limpia la cola, eliminando todos los elementos.
     */
    public void limpiar() {
        frente = null;
        fin = null;
        tamanio = 0;
        System.out.println("Cola limpiada.");
    }
}
