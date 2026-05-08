/**
 * Implementación de una Tabla Hash para almacenar y buscar IPs seguras.
 */
public class TablaHashSeguras {
    private NodoHash[] tabla;
    private int capacidad;
    private int elementos;
    
    public TablaHashSeguras(int capacidad) {
        this.capacidad = capacidad;
        this.elementos = 0;
        this.tabla = new NodoHash[capacidad];
    }
    
    public int funcionHash(String ip) {
        return (ip.hashCode() & 0x7FFFFFFF) % capacidad;
    }
    
    public void insertar(String ip, String metadata) {
        if (getFactorCarga() > 0.75) {
            rehash();
        }
        
        int indice = funcionHash(ip);
        NodoHash nuevo = new NodoHash(ip, metadata);
        
        if (tabla[indice] == null) {
            tabla[indice] = nuevo;
        } else {
            NodoHash actual = tabla[indice];
            while (actual.getSiguiente() != null) {
                if (actual.getIp().equals(ip)) {
                    return;
                }
                actual = actual.getSiguiente();
            }
            if (actual.getIp().equals(ip)) return;
            actual.setSiguiente(nuevo);
        }
        elementos++;
    }
    
    private void rehash() {
        int nuevaCapacidad = capacidad * 2;
        NodoHash[] tablaAntigua = tabla;
        
        this.capacidad = nuevaCapacidad;
        this.tabla = new NodoHash[nuevaCapacidad];
        this.elementos = 0;
        
        for (int i = 0; i < tablaAntigua.length; i++) {
            NodoHash actual = tablaAntigua[i];
            while (actual != null) {
                insertar(actual.getIp(), actual.getMetadata());
                actual = actual.getSiguiente();
            }
        }
    }
    
    public String buscar(String ip) {
        int indice = funcionHash(ip);
        long inicio = System.nanoTime();
        
        NodoHash actual = tabla[indice];
        while (actual != null) {
            if (actual.getIp().equals(ip)) {
                long fin = System.nanoTime();
                return actual.getMetadata() + " [Tiempo: " + ((fin - inicio) / 1000) + "μs]";
            }
            actual = actual.getSiguiente();
        }
        
        return null;
    }
    
    public boolean contiene(String ip) {
        return buscar(ip) != null;
    }
    
    public boolean eliminar(String ip) {
        int indice = funcionHash(ip);
        NodoHash actual = tabla[indice];
        NodoHash anterior = null;
        
        while (actual != null) {
            if (actual.getIp().equals(ip)) {
                if (anterior == null) {
                    tabla[indice] = actual.getSiguiente();
                } else {
                    anterior.setSiguiente(actual.getSiguiente());
                }
                elementos--;
                return true;
            }
            anterior = actual;
            actual = actual.getSiguiente();
        }
        return false;
    }
    
    public double getFactorCarga() {
        return (double) elementos / capacidad;
    }
    
    public int getElementos() { return elementos; }
    public int getCapacidad() { return capacidad; }
    
    public void mostrarEstado() {
        System.out.println("   Capacidad: " + capacidad + " buckets");
        System.out.println("   Elementos: " + elementos + " IPs seguras");
        System.out.println("   Factor de carga: " + String.format("%.2f", getFactorCarga()));
        
        int colisiones = 0;
        for (int i = 0; i < capacidad; i++) {
            if (tabla[i] != null && tabla[i].getSiguiente() != null) {
                colisiones++;
            }
        }
        System.out.println("   Buckets con colisiones: " + colisiones);
    }
}
