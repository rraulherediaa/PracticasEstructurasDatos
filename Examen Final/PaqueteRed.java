/**
 * Representa un paquete de red con información de origen, destino y tamaño.
 */
public class PaqueteRed {
    private String ipOrigen;
    private String ipDestino;
    private int tamañoPayload;
    private long timestamp;
    
    public PaqueteRed(String ipOrigen, String ipDestino, int tamañoPayload) {
        this.ipOrigen = ipOrigen;
        this.ipDestino = ipDestino;
        this.tamañoPayload = tamañoPayload;
        this.timestamp = System.currentTimeMillis();
    }
    
    public String getIpOrigen() { return ipOrigen; }
    public String getIpDestino() { return ipDestino; }
    public int getTamaño() { return tamañoPayload; }
    public long getTimestamp() { return timestamp; }
    
    @Override
    public String toString() {
        return ipOrigen + " → " + ipDestino + " (" + tamañoPayload + " bytes)";
    }
}
