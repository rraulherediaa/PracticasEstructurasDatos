/**
 * Clase principal que coordina el sistema de gestión de tráfico y caché.
 */
public class SistemaGestionTraficoCache {
    private ColaPaquetes cola;
    private ListaDobleHistorial historial;
    private TablaHashSeguras ipsSeguras;
    
    public SistemaGestionTraficoCache() {
        this.cola = new ColaPaquetes();
        this.historial = new ListaDobleHistorial(100);
        this.ipsSeguras = new TablaHashSeguras(16);
    }
    
    public void procesarPaquetes() {
        System.out.println("\n===== MÓDULO 1: RECEPCIÓN DE PAQUETES (Cola) =====");
        System.out.println("\n📥 Encolando 5 paquetes simulados...");
        cola.enqueue(new PaqueteRed("192.168.1.10", "8.8.8.8", 1450));
        cola.enqueue(new PaqueteRed("10.0.0.15", "192.168.1.1", 512));
        cola.enqueue(new PaqueteRed("172.16.0.5", "10.0.0.2", 1024));
        cola.enqueue(new PaqueteRed("192.168.0.50", "1.1.1.1", 2048));
        cola.enqueue(new PaqueteRed("10.1.1.20", "172.217.0.46", 900));
        
        cola.mostrarCola();
        
        System.out.println("\n📤 Procesando paquetes (FIFO)...");
        int procesados = 0;
        while (!cola.estaVacia()) {
            PaqueteRed paquete = cola.dequeue();
            procesados++;
            System.out.println("   → Procesando [" + procesados + "]: " + paquete);
            historial.agregarAlFrente(paquete.getIpOrigen());
            historial.agregarAlFrente(paquete.getIpDestino());
        }
        System.out.println("\n✅ Cola vacía. Total paquetes procesados: " + procesados);
    }
    
    public void demostrarHistorial() {
        System.out.println("\n===== MÓDULO 2: HISTORIAL BIDIRECCIONAL (Lista Doble) =====");
        System.out.println("\n📝 Agregando 10 IPs adicionales al historial...");
        String[] ipsExtras = {"192.168.1.100", "10.0.0.50", "172.16.0.25", 
                             "192.168.0.1", "10.0.0.99", "172.217.0.46",
                             "8.8.4.4", "208.67.222.222", "9.9.9.9", "94.140.14.14"};
        for (String ip : ipsExtras) {
            historial.agregarAlFrente(ip);
        }
        System.out.println("   Total IPs en historial: " + historial.getContador());
        System.out.println("\n🔍 Recorrido ADELANTE (Reciente → Antiguo):");
        historial.recorrerAdelante();
        System.out.println("\n🔍 Recorrido ATRÁS (Antiguo → Reciente):");
        historial.recorrerAtras();
        
        System.out.println("\n⚠️  Prueba de límite de 100 registros...");
        for (int i = 1; i <= 90; i++) {
            historial.agregarAlFrente("10.0.0." + i);
        }
        System.out.println("   Total actual: " + historial.getContador() + " IPs");
        historial.agregarAlFrente("192.168.100.100");
        System.out.println("   ✓ Nueva IP agregada. Total: " + historial.getContador() + " IPs (límite de 100)");
    }
    
    public void demostrarCacheSeguridad() {
        System.out.println("\n===== MÓDULO 3: MOTOR DE CONFIANZA (Tabla Hash) =====");
        System.out.println("\n🔐 Marcando IPs como seguras...");
        ipsSeguras.insertar("192.168.1.10", "Servidor Local");
        ipsSeguras.insertar("8.8.8.8", "DNS Google");
        ipsSeguras.insertar("1.1.1.1", "DNS Cloudflare");
        ipsSeguras.insertar("172.16.0.5", "Estación Trabajo");
        
        System.out.println("\n🔍 Búsqueda de IP 8.8.8.8...");
        String resultado = ipsSeguras.buscar("8.8.8.8");
        if (resultado != null) {
            System.out.println("   ⚡ Búsqueda O(1): IP encontrada");
            System.out.println("   📋 Metadata: " + resultado);
        }
        
        System.out.println("\n🔍 Búsqueda de IP insegura 10.0.0.99...");
        resultado = ipsSeguras.buscar("10.0.0.99");
        if (resultado == null) {
            System.out.println("   ⚡ Búsqueda O(1): IP no encontrada");
            System.out.println("   ⚠️  Acceso bloqueado - IP no está en lista de confianza");
        }
        
        System.out.println("\n📊 Estado de Tabla Hash:");
        ipsSeguras.mostrarEstado();
        
        System.out.println("\n📎 Demostración de manejo de colisiones:");
        ipsSeguras.insertar("192.168.1.20", "Servidor Backup");
        ipsSeguras.insertar("192.168.1.30", "Servidor Web");
        System.out.println("   Elementos actuales: " + ipsSeguras.getElementos());
    }
    
    public void demostrarLimpieza() {
        System.out.println("\n===== MÓDULO 4: SINCRONIZACIÓN Y LIMPIEZA =====");
        String ipLimpieza = "192.168.1.10";
        System.out.println("\n🧹 Ejecutando Limpieza de Seguridad para IP: " + ipLimpieza);
        limpiezaDeSeguridad(ipLimpieza);
        
        System.out.println("\n🔍 Verificación post-limpieza:");
        boolean enHash = ipsSeguras.contiene(ipLimpieza);
        boolean enHistorial = historial.contiene(ipLimpieza);
        System.out.println("   ¿" + ipLimpieza + " en tabla hash? " + (enHash ? "SÍ ❌" : "NO ✓"));
        System.out.println("   ¿" + ipLimpieza + " en historial? " + (enHistorial ? "SÍ ❌" : "NO ✓"));
        if (!enHash && !enHistorial) System.out.println("   ✅ Integridad referencial mantenida");
    }
    
    public void limpiezaDeSeguridad(String ip) {
        boolean eliminadaHash = ipsSeguras.eliminar(ip);
        int eliminadasHistorial = historial.buscarYEliminar(ip);
        System.out.println("   ✓ Eliminada de Tabla Hash: " + (eliminadaHash ? "SÍ" : "NO"));
        System.out.println("   ✓ Eliminada de Lista Historial: " + eliminadasHistorial + " ocurrencias");
    }
    
    public static void main(String[] args) {
        SistemaGestionTraficoCache sistema = new SistemaGestionTraficoCache();
        System.out.println("========================================");
        System.out.println("  SISTEMA REFACTORIZADO");
        System.out.println("========================================");
        sistema.procesarPaquetes();
        sistema.demostrarHistorial();
        sistema.demostrarCacheSeguridad();
        sistema.demostrarLimpieza();
    }
}
