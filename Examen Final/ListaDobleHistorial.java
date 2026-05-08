/**
 * Implementación de una Lista Doblemente Enlazada para mantener un historial de IPs.
 */
public class ListaDobleHistorial {
    private NodoHistorial cabeza;  // Más reciente
    private NodoHistorial cola;    // Más antiguo
    private int contador;
    private int limite;
    
    public ListaDobleHistorial(int limite) {
        this.cabeza = null;
        this.cola = null;
        this.contador = 0;
        this.limite = limite;
    }
    
    public void agregarAlFrente(String ip) {
        NodoHistorial nuevo = new NodoHistorial(ip);
        
        if (cabeza == null) {
            cabeza = cola = nuevo;
        } else {
            nuevo.setSiguiente(cabeza);
            cabeza.setAnterior(nuevo);
            cabeza = nuevo;
        }
        contador++;
        
        if (contador > limite) {
            eliminarUltimo();
        }
    }
    
    public void eliminarUltimo() {
        if (cola == null) return;
        
        if (cabeza == cola) {
            cabeza = cola = null;
        } else {
            cola = cola.getAnterior();
            cola.setSiguiente(null);
        }
        contador--;
    }
    
    public int buscarYEliminar(String ip) {
        int eliminados = 0;
        NodoHistorial actual = cabeza;
        
        while (actual != null) {
            NodoHistorial siguiente = actual.getSiguiente();
            
            if (actual.getIp().equals(ip)) {
                if (actual == cabeza) {
                    cabeza = actual.getSiguiente();
                    if (cabeza != null) cabeza.setAnterior(null);
                } else if (actual == cola) {
                    cola = actual.getAnterior();
                    if (cola != null) cola.setSiguiente(null);
                } else {
                    actual.getAnterior().setSiguiente(actual.getSiguiente());
                    actual.getSiguiente().setAnterior(actual.getAnterior());
                }
                contador--;
                eliminados++;
            }
            actual = siguiente;
        }
        
        if (cabeza == null) cola = null;
        if (cola == null) cabeza = null;
        
        return eliminados;
    }
    
    public void recorrerAdelante() {
        NodoHistorial actual = cabeza;
        int count = 0;
        System.out.print("   ");
        while (actual != null && count < 10) {
            System.out.print(actual.getIp());
            actual = actual.getSiguiente();
            if (actual != null && count < 9) System.out.print(" → ");
            count++;
        }
        if (contador > 10) System.out.print(" → ... (" + (contador - 10) + " más)");
        System.out.println();
    }
    
    public void recorrerAtras() {
        NodoHistorial actual = cola;
        int count = 0;
        System.out.print("   ");
        while (actual != null && count < 10) {
            System.out.print(actual.getIp());
            actual = actual.getAnterior();
            if (actual != null && count < 9) System.out.print(" → ");
            count++;
        }
        if (contador > 10) System.out.print(" → ... (" + (contador - 10) + " más)");
        System.out.println();
    }
    
    public boolean contiene(String ip) {
        NodoHistorial actual = cabeza;
        while (actual != null) {
            if (actual.getIp().equals(ip)) return true;
            actual = actual.getSiguiente();
        }
        return false;
    }
    
    public int getContador() { return contador; }
}
