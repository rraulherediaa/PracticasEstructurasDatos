/**
 * Nodo para la estructura de Cola de Paquetes.
 */
public class NodoCola {
    private PaqueteRed paquete;
    private NodoCola siguiente;
    
    public NodoCola(PaqueteRed paquete) {
        this.paquete = paquete;
        this.siguiente = null;
    }
    
    public PaqueteRed getPaquete() { return paquete; }
    public NodoCola getSiguiente() { return siguiente; }
    public void setSiguiente(NodoCola siguiente) { this.siguiente = siguiente; }
}
