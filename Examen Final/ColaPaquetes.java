/**
 * Implementación de una Cola (FIFO) para la gestión de paquetes de red.
 */
public class ColaPaquetes {
    private NodoCola inicio;
    private NodoCola fin;
    private int tamaño;
    
    public ColaPaquetes() {
        this.inicio = null;
        this.fin = null;
        this.tamaño = 0;
    }
    
    public void enqueue(PaqueteRed paquete) {
        NodoCola nuevo = new NodoCola(paquete);
        
        if (estaVacia()) {
            inicio = fin = nuevo;
        } else {
            fin.setSiguiente(nuevo);
            fin = nuevo;
        }
        tamaño++;
    }
    
    public PaqueteRed dequeue() {
        if (estaVacia()) {
            return null;
        }
        
        PaqueteRed paquete = inicio.getPaquete();
        inicio = inicio.getSiguiente();
        
        if (inicio == null) {
            fin = null;
        }
        
        tamaño--;
        return paquete;
    }
    
    public PaqueteRed peek() {
        return estaVacia() ? null : inicio.getPaquete();
    }
    
    public boolean estaVacia() {
        return inicio == null;
    }
    
    public int getTamaño() {
        return tamaño;
    }
    
    public void mostrarCola() {
        NodoCola actual = inicio;
        int contador = 1;
        while (actual != null) {
            System.out.println("   [" + contador + "] " + actual.getPaquete());
            actual = actual.getSiguiente();
            contador++;
        }
    }
}
