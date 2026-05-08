/**
 * Nodo para la Tabla Hash de IPs seguras.
 */
public class NodoHash {
    private String ip;
    private String metadata;
    private NodoHash siguiente;
    
    public NodoHash(String ip, String metadata) {
        this.ip = ip;
        this.metadata = metadata;
        this.siguiente = null;
    }
    
    public String getIp() { return ip; }
    public String getMetadata() { return metadata; }
    public NodoHash getSiguiente() { return siguiente; }
    public void setSiguiente(NodoHash siguiente) { this.siguiente = siguiente; }
}
