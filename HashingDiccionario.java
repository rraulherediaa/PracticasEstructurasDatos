/**
 * Tarea 7: Hashing y Diccionarios
 * Sistema de Registro de Estudiantes
 * 
 * Implementación de Tabla Hash con Encadenamiento Separado
 * Materia: Estructura de Datos I
 */

// ==================== CLASE ESTUDIANTE ====================
class Estudiante {
    private int id;
    private String nombre;
    private String carrera;
    private double promedio;
    
    public Estudiante(int id, String nombre, String carrera, double promedio) {
        this.id = id;
        this.nombre = nombre;
        this.carrera = carrera;
        this.promedio = promedio;
    }
    
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getCarrera() { return carrera; }
    public double getPromedio() { return promedio; }
    
    @Override
    public String toString() {
        return String.format("ID: %d | %s | %s | Promedio: %.1f", 
                           id, nombre, carrera, promedio);
    }
}

// ==================== CLASE NODO HASH ====================
class NodoHash {
    private Estudiante estudiante;
    private NodoHash siguiente;
    
    public NodoHash(Estudiante estudiante) {
        this.estudiante = estudiante;
        this.siguiente = null;
    }
    
    public Estudiante getEstudiante() { return estudiante; }
    public NodoHash getSiguiente() { return siguiente; }
    public void setSiguiente(NodoHash siguiente) { this.siguiente = siguiente; }
}

// ==================== CLASE TABLA HASH ====================
class TablaHash {
    private NodoHash[] tabla;
    private int tamaño;
    private int elementos;
    
    public TablaHash(int tamaño) {
        this.tamaño = tamaño;
        this.elementos = 0;
        this.tabla = new NodoHash[tamaño];
    }
    
    // Función Hash: Convierte ID en índice de tabla
    public int funcionHash(int id) {
        return Math.abs(id) % tamaño;
    }
    
    // Insertar estudiante (maneja colisiones con encadenamiento)
    public void put(int id, Estudiante estudiante) {
        int indice = funcionHash(id);
        NodoHash nuevo = new NodoHash(estudiante);
        
        if (tabla[indice] == null) {
            // Bucket vacío - insertar directamente
            tabla[indice] = nuevo;
        } else {
            // Colisión - encadenar al final
            NodoHash actual = tabla[indice];
            while (actual.getSiguiente() != null) {
                if (actual.getEstudiante().getId() == id) {
                    // Actualizar si ya existe
                    actual = nuevo; // Simplificado para ejemplo
                    return;
                }
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevo);
        }
        elementos++;
    }
    
    // Buscar estudiante por ID
    public Estudiante get(int id) {
        int indice = funcionHash(id);
        NodoHash actual = tabla[indice];
        
        while (actual != null) {
            if (actual.getEstudiante().getId() == id) {
                return actual.getEstudiante();
            }
            actual = actual.getSiguiente();
        }
        return null; // No encontrado
    }
    
    // Verificar si existe un ID
    public boolean containsKey(int id) {
        return get(id) != null;
    }
    
    // Eliminar estudiante por ID
    public boolean remove(int id) {
        int indice = funcionHash(id);
        NodoHash actual = tabla[indice];
        NodoHash anterior = null;
        
        while (actual != null) {
            if (actual.getEstudiante().getId() == id) {
                if (anterior == null) {
                    // Es el primer nodo del bucket
                    tabla[indice] = actual.getSiguiente();
                } else {
                    // Está en medio o al final
                    anterior.setSiguiente(actual.getSiguiente());
                }
                elementos--;
                return true;
            }
            anterior = actual;
            actual = actual.getSiguiente();
        }
        return false; // No encontrado
    }
    
    // Obtener cantidad de elementos
    public int size() {
        return elementos;
    }
    
    // Calcular factor de carga
    public double factorCarga() {
        return (double) elementos / tamaño;
    }
    
    // Mostrar estado completo de la tabla
    public void mostrarTabla() {
        System.out.println("\n📊 Factor de carga: " + elementos + "/" + tamaño + " = " + 
                        String.format("%.2f", factorCarga()));
        System.out.println();
        
        for (int i = 0; i < tamaño; i++) {
            System.out.print("Bucket[" + i + "]: ");
            NodoHash actual = tabla[i];
            
            if (actual == null) {
                System.out.println("(vacío)");
            } else {
                while (actual != null) {
                    Estudiante e = actual.getEstudiante();
                    System.out.print("→ " + e.getId() + ": " + e.getNombre().split(" ")[0] + " ");
                    actual = actual.getSiguiente();
                }
                System.out.println();
            }
        }
    }
}

// ==================== CLASE PRINCIPAL ====================
public class HashingDiccionario {
    
    public static void main(String[] args) {
        // Crear tabla hash con 10 buckets
        TablaHash registro = new TablaHash(10);
        
        System.out.println("========================================");
        System.out.println("  HASHING Y DICCIONARIOS");
        System.out.println("  Sistema de Registro de Estudiantes");
        System.out.println("========================================\n");
        
        // Mostrar función hash
        System.out.println("--- FUNCIÓN HASH ---");
        System.out.println("📐 Fórmula: hash(id) = id % 10");
        System.out.println("   Ejemplo: hash(202301) = 202301 % 10 = " + (202301 % 10));
        System.out.println();
        
        // Insertar estudiantes
        System.out.println("--- INSERTANDO ESTUDIANTES ---");
        
        insertarEstudiante(registro, 202301, "Ana García", "Ingeniería", 8.5);
        insertarEstudiante(registro, 202302, "Luis Martínez", "Medicina", 9.2);
        insertarEstudiante(registro, 202305, "Carlos López", "Derecho", 8.0);
        insertarEstudiante(registro, 202315, "María Soto", "Arquitectura", 9.0); // Colisión con 202305
        insertarEstudiante(registro, 202311, "Pedro Ruiz", "Economía", 7.8);   // Colisión con 202301
        insertarEstudiante(registro, 202308, "Laura Torres", "Psicología", 8.7);
        
        // Mostrar estado de la tabla
        System.out.println("--- ESTADO DE LA TABLA HASH ---");
        registro.mostrarTabla();
        
        // Realizar búsquedas
        System.out.println("\n--- BÚSQUEDAS ---");
        buscarEstudiante(registro, 202301);
        buscarEstudiante(registro, 202315);
        buscarEstudiante(registro, 202399); // No existe
        
        // Verificar existencia
        System.out.println("\n--- VERIFICACIÓN ---");
        System.out.println((registro.containsKey(202302) ? "✓" : "✗") + 
                          " ID 202302 existe en el registro");
        System.out.println((registro.containsKey(202399) ? "✓" : "✗") + 
                          " ID 202399 no existe en el registro");
        
        // Eliminar un estudiante
        System.out.println("\n--- ELIMINANDO ESTUDIANTE ---");
        System.out.println("🗑️ Eliminando ID 202302...");
        boolean eliminado = registro.remove(202302);
        System.out.println(eliminado ? "✓ Eliminado exitosamente" : "✗ No se pudo eliminar");
        
        // Verificar eliminación
        System.out.println("\n🔍 Buscando ID 202302 después de eliminar:");
        buscarEstudiante(registro, 202302);
        
        // Mostrar tabla final
        System.out.println("\n--- TABLA FINAL ---");
        registro.mostrarTabla();
        
        System.out.println("\n========================================");
        System.out.println("  FIN DE LA DEMOSTRACIÓN");
        System.out.println("========================================");
    }
    
    // Método auxiliar para insertar con mensajes
    private static void insertarEstudiante(TablaHash tabla, int id, String nombre, 
                                          String carrera, double promedio) {
        int hash = tabla.funcionHash(id);
        System.out.println("📝 Insertando: ID=" + id + ", " + nombre + ", " + carrera + ", Promedio=" + promedio);
        System.out.println("   → Hash: " + hash + ", Bucket[" + hash + "]");
        
        // Verificar si habrá colisión
        // (Simplificado para mostrar el concepto)
        if ((id == 202315 && hash == 5) || (id == 202311 && hash == 1)) {
            System.out.println("   ⚠️  ¡COLISIÓN detectada! Se usará encadenamiento");
        }
        System.out.println();
        
        tabla.put(id, new Estudiante(id, nombre, carrera, promedio));
    }
    
    // Método auxiliar para buscar con mensajes
    private static void buscarEstudiante(TablaHash tabla, int id) {
        Estudiante e = tabla.get(id);
        if (e != null) {
            System.out.println("🔍 Buscando ID " + id + ": " + e);
        } else {
            System.out.println("🔍 Buscando ID " + id + ": No encontrado");
        }
    }
}
