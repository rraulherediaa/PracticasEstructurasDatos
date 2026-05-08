/**
 * Nodo para la Lista Doblemente Enlazada de Historial.
 */
public class NodoHistorial {
    private String ip;
    private long timestamp;
    private NodoHistorial anterior;
    private NodoHistorial siguiente;
    
    public NodoHistorial(String ip) {
        this.ip = ip;
        this.timestamp = System.currentTimeMillis();
        this.anterior = null;
        this.siguiente = null;
    }
    
    public String getIp() { return ip; }
    public long getTimestamp() { return timestamp; }
    public NodoHistorial getAnterior() { return anterior; }
    public NodoHistorial getSiguiente() { return siguiente; }
    public void setAnterior(NodoHistorial anterior) { this.anterior = anterior; }
    public void setSiguiente(NodoHistorial siguiente) { this.siguiente = siguiente; }
}
