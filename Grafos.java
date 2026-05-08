/**
 * Practica 9: Grafos
 * Red de Conexiones de Ciudades
 * 
 * Implementación de Grafo No Dirigido Ponderado
 * Materia: Estructura de Datos I
 */

import java.util.*;

// ==================== CLASE CIUDAD (VÉRTICE) ====================
class Ciudad {
    private String nombre;
    private String codigo;
    
    public Ciudad(String nombre, String codigo) {
        this.nombre = nombre;
        this.codigo = codigo.toUpperCase();
    }
    
    public String getNombre() { return nombre; }
    public String getCodigo() { return codigo; }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Ciudad ciudad = (Ciudad) obj;
        return Objects.equals(codigo, ciudad.codigo);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }
    
    @Override
    public String toString() {
        return codigo + " (" + nombre + ")";
    }
}

// ==================== CLASE ARISTA ====================
class Arista {
    private Ciudad destino;
    private int peso; // Distancia en km
    
    public Arista(Ciudad destino, int peso) {
        this.destino = destino;
        this.peso = peso;
    }
    
    public Ciudad getDestino() { return destino; }
    public int getPeso() { return peso; }
    
    @Override
    public String toString() {
        return destino.getCodigo() + " (" + peso + " km)";
    }
}

// ==================== CLASE GRAFO ====================
class Grafo {
    private Map<String, Ciudad> ciudades;
    private Map<String, List<Arista>> adyacencia;
    private boolean dirigido;
    
    public Grafo(boolean dirigido) {
        this.dirigido = dirigido;
        this.ciudades = new HashMap<>();
        this.adyacencia = new HashMap<>();
    }
    
    // Agregar vértice (ciudad)
    public boolean agregarVertice(String nombre, String codigo) {
        String cod = codigo.toUpperCase();
        if (ciudades.containsKey(cod)) {
            System.out.println("⚠️  La ciudad con código " + cod + " ya existe");
            return false;
        }
        
        Ciudad ciudad = new Ciudad(nombre, cod);
        ciudades.put(cod, ciudad);
        adyacencia.put(cod, new ArrayList<>());
        return true;
    }
    
    // Agregar arista (conexión entre ciudades)
    public boolean agregarArista(String origen, String destino, int peso) {
        String codOrigen = origen.toUpperCase();
        String codDestino = destino.toUpperCase();
        
        if (!ciudades.containsKey(codOrigen) || !ciudades.containsKey(codDestino)) {
            System.out.println("⚠️  Una o ambas ciudades no existen");
            return false;
        }
        
        Ciudad ciudadOrigen = ciudades.get(codOrigen);
        Ciudad ciudadDestino = ciudades.get(codDestino);
        
        // Agregar conexión origen → destino
        adyacencia.get(codOrigen).add(new Arista(ciudadDestino, peso));
        
        // Si es no dirigido, agregar conexión destino → origen
        if (!dirigido) {
            adyacencia.get(codDestino).add(new Arista(ciudadOrigen, peso));
        }
        
        return true;
    }
    
    // Recorrido DFS (Depth First Search) - Profundidad
    public void dfs(String codigoInicio) {
        String codInicio = codigoInicio.toUpperCase();
        if (!ciudades.containsKey(codInicio)) {
            System.out.println("⚠️  Ciudad no encontrada");
            return;
        }
        
        Set<String> visitados = new HashSet<>();
        Stack<String> pila = new Stack<>();
        
        pila.push(codInicio);
        
        System.out.print("🔍 Orden de visita (DFS): ");
        
        while (!pila.isEmpty()) {
            String actual = pila.pop();
            
            if (!visitados.contains(actual)) {
                visitados.add(actual);
                System.out.print(actual);
                
                // Agregar vecinos no visitados a la pila
                List<Arista> vecinos = adyacencia.get(actual);
                for (int i = vecinos.size() - 1; i >= 0; i--) {
                    String codVecino = vecinos.get(i).getDestino().getCodigo();
                    if (!visitados.contains(codVecino)) {
                        pila.push(codVecino);
                    }
                }
                
                if (!pila.isEmpty()) {
                    System.out.print(" → ");
                }
            }
        }
        System.out.println();
    }
    
    // Recorrido BFS (Breadth First Search) - Anchura
    public void bfs(String codigoInicio) {
        String codInicio = codigoInicio.toUpperCase();
        if (!ciudades.containsKey(codInicio)) {
            System.out.println("⚠️  Ciudad no encontrada");
            return;
        }
        
        Set<String> visitados = new HashSet<>();
        Queue<String> cola = new LinkedList<>();
        
        cola.offer(codInicio);
        visitados.add(codInicio);
        
        System.out.print("🔍 Orden de visita (BFS): ");
        
        while (!cola.isEmpty()) {
            String actual = cola.poll();
            System.out.print(actual);
            
            // Agregar vecinos no visitados a la cola
            for (Arista arista : adyacencia.get(actual)) {
                String codVecino = arista.getDestino().getCodigo();
                if (!visitados.contains(codVecino)) {
                    visitados.add(codVecino);
                    cola.offer(codVecino);
                }
            }
            
            if (!cola.isEmpty()) {
                System.out.print(" → ");
            }
        }
        System.out.println();
    }
    
    // Verificar si existe camino entre dos ciudades
    public boolean existeCamino(String origen, String destino) {
        String codOrigen = origen.toUpperCase();
        String codDestino = destino.toUpperCase();
        
        if (!ciudades.containsKey(codOrigen) || !ciudades.containsKey(codDestino)) {
            return false;
        }
        
        if (codOrigen.equals(codDestino)) return true;
        
        Set<String> visitados = new HashSet<>();
        Queue<String> cola = new LinkedList<>();
        
        cola.offer(codOrigen);
        visitados.add(codOrigen);
        
        while (!cola.isEmpty()) {
            String actual = cola.poll();
            
            for (Arista arista : adyacencia.get(actual)) {
                String codVecino = arista.getDestino().getCodigo();
                
                if (codVecino.equals(codDestino)) {
                    return true;
                }
                
                if (!visitados.contains(codVecino)) {
                    visitados.add(codVecino);
                    cola.offer(codVecino);
                }
            }
        }
        
        return false;
    }
    
    // Obtener vecinos de una ciudad
    public List<Arista> obtenerVecinos(String codigo) {
        String cod = codigo.toUpperCase();
        return adyacencia.getOrDefault(cod, new ArrayList<>());
    }
    
    // Mostrar el grafo completo
    public void mostrarGrafo() {
        System.out.println("\n📍 LISTA DE ADYACENCIA:");
        System.out.println("Tipo: " + (dirigido ? "Dirigido" : "No Dirigido"));
        System.out.println("Ciudades: " + ciudades.size());
        System.out.println();
        
        for (String codigo : ciudades.keySet()) {
            Ciudad ciudad = ciudades.get(codigo);
            List<Arista> conexiones = adyacencia.get(codigo);
            
            System.out.print(ciudad.getCodigo() + " (" + ciudad.getNombre() + "): ");
            
            if (conexiones.isEmpty()) {
                System.out.println("(sin conexiones)");
            } else {
                for (int i = 0; i < conexiones.size(); i++) {
                    Arista arista = conexiones.get(i);
                    System.out.print("→ " + arista.getDestino().getCodigo() + 
                                   " (" + arista.getPeso() + " km)");
                    if (i < conexiones.size() - 1) {
                        System.out.print(" ");
                    }
                }
                System.out.println();
            }
        }
    }
    
    public boolean esDirigido() {
        return dirigido;
    }
    
    public int cantidadCiudades() {
        return ciudades.size();
    }
}

// ==================== CLASE PRINCIPAL ====================
public class Grafos {
    
    public static void main(String[] args) {
        // Crear grafo no dirigido
        Grafo redNacional = new Grafo(false);
        
        System.out.println("========================================");
        System.out.println("  GRAFOS - RED DE CONEXIONES");
        System.out.println("  Sistema de Ciudades Colombianas");
        System.out.println("========================================\n");
        
        // Agregar ciudades
        System.out.println("--- AGREGANDO CIUDADES ---");
        agregarCiudad(redNacional, "Bogotá", "BOG");
        agregarCiudad(redNacional, "Medellín", "MED");
        agregarCiudad(redNacional, "Cali", "CAL");
        agregarCiudad(redNacional, "Barranquilla", "BAR");
        agregarCiudad(redNacional, "Cartagena", "CTG");
        agregarCiudad(redNacional, "Bucaramanga", "BUC");
        
        // Establecer conexiones (carreteras con distancias aproximadas)
        System.out.println("\n--- ESTABLECIENDO CONEXIONES ---");
        agregarConexion(redNacional, "BOG", "MED", 420);
        agregarConexion(redNacional, "MED", "CAL", 420);
        agregarConexion(redNacional, "BOG", "CAL", 460);
        agregarConexion(redNacional, "BAR", "CTG", 120);
        agregarConexion(redNacional, "BOG", "BAR", 1050);
        agregarConexion(redNacional, "BOG", "BUC", 400);
        agregarConexion(redNacional, "BUC", "BAR", 550);
        
        // Mostrar la estructura del grafo
        redNacional.mostrarGrafo();
        
        // Realizar recorridos
        System.out.println("\n--- RECORRIDOS DESDE BOGOTÁ ---");
        redNacional.dfs("BOG");
        redNacional.bfs("BOG");
        
        System.out.println("\n--- RECORRIDOS DESDE BARRANQUILLA ---");
        redNacional.dfs("BAR");
        redNacional.bfs("BAR");
        
        // Verificar conectividad
        System.out.println("\n--- VERIFICACIÓN DE RUTAS ---");
        verificarRuta(redNacional, "BOG", "CTG");
        verificarRuta(redNacional, "CTG", "CAL");
        verificarRuta(redNacional, "BOG", "MED");
        verificarRuta(redNacional, "CAL", "BAR");
        
        System.out.println("\n========================================");
        System.out.println("  FIN DE LA DEMOSTRACIÓN");
        System.out.println("========================================");
    }
    
    // Método auxiliar para agregar ciudad
    private static void agregarCiudad(Grafo grafo, String nombre, String codigo) {
        System.out.println("🏙️  Agregando: " + nombre + " (" + codigo + ")");
        grafo.agregarVertice(nombre, codigo);
    }
    
    // Método auxiliar para agregar conexión
    private static void agregarConexion(Grafo grafo, String origen, String destino, int distancia) {
        System.out.println("🛣️  " + origen + " ↔ " + destino + ": " + distancia + " km");
        grafo.agregarArista(origen, destino, distancia);
    }
    
    // Método auxiliar para verificar ruta
    private static void verificarRuta(Grafo grafo, String origen, String destino) {
        boolean existe = grafo.existeCamino(origen, destino);
        String icono = existe ? "✓" : "✗";
        String mensaje = existe ? "existe conexión" : "no hay conexión";
        System.out.println(icono + " " + origen + " → " + destino + ": " + mensaje);
    }
}
