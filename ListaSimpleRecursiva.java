/**
 * Lista Simple con Recursividad - Playlist de Canciones
 * 1. Mostrar canciones con recursividad
 * 2. Insertar canción al final con recursividad
 */

// Clase Nodo (Canción)
class NodoCancion {
    String titulo;
    String artista;
    int duracion; // en segundos
    NodoCancion siguiente;

    public NodoCancion(String titulo, String artista, int duracion) {
        this.titulo = titulo;
        this.artista = artista;
        this.duracion = duracion;
        this.siguiente = null;
    }

    @Override
    public String toString() {
        return String.format("\"%s\" - %s (%d:%02d)", titulo, artista, duracion / 60, duracion % 60);
    }
}

// Clase Lista Simple de Canciones
class Playlist {
    NodoCancion cabeza;

    public Playlist() {
        this.cabeza = null;
    }

    // ==========================================
    // TAREA 1: Mostrar elementos con recursividad
    // ==========================================
    
    // Mostrar canciones en orden normal
    public void mostrarCancionesRecursivo() {
        System.out.println("\n🎵 Playlist (Orden Normal):");
        mostrarCancionesRecursivoAux(cabeza, 1);
    }

    private void mostrarCancionesRecursivoAux(NodoCancion actual, int indice) {
        if (actual == null) {
            System.out.println("   Fin de la playlist");
            return; // Caso base
        }
        System.out.printf("   %d. %s\n", indice, actual);
        mostrarCancionesRecursivoAux(actual.siguiente, indice + 1); // Llamada recursiva
    }

    // Mostrar canciones en orden inverso
    public void mostrarCancionesInversoRecursivo() {
        System.out.println("\n🎵 Playlist (Orden Inverso):");
        mostrarCancionesInversoRecursivoAux(cabeza, contarCanciones());
    }

    private void mostrarCancionesInversoRecursivoAux(NodoCancion actual, int indice) {
        if (actual == null) {
            return; // Caso base
        }
        mostrarCancionesInversoRecursivoAux(actual.siguiente, indice - 1); // Primero recorre al final
        System.out.printf("   %d. %s\n", indice, actual); // Luego imprime al regresar
    }

    private int contarCanciones() {
        return contarCancionesAux(cabeza);
    }

    private int contarCancionesAux(NodoCancion actual) {
        if (actual == null) return 0;
        return 1 + contarCancionesAux(actual.siguiente);
    }

    // ==========================================
    // TAREA 2: Insertar al final con recursividad
    // ==========================================
    
    // Versión funcional: Retorna el nuevo nodo
    public void agregarCancionRecursivo(String titulo, String artista, int duracion) {
        System.out.printf("🎶 Agregando: \"%s\" de %s\n", titulo, artista);
        cabeza = agregarCancionRecursivoAux(cabeza, titulo, artista, duracion);
    }

    private NodoCancion agregarCancionRecursivoAux(NodoCancion actual, String titulo, String artista, int duracion) {
        if (actual == null) {
            return new NodoCancion(titulo, artista, duracion); // Caso base: crear nuevo nodo
        }
        actual.siguiente = agregarCancionRecursivoAux(actual.siguiente, titulo, artista, duracion);
        return actual;
    }

    // ==========================================
    // MÉTODOS ADICIONALES PARA PRUEBAS
    // ==========================================
    
    // Insertar al inicio (iterativo para facilitar pruebas)
    public void agregarAlInicio(String titulo, String artista, int duracion) {
        NodoCancion nuevo = new NodoCancion(titulo, artista, duracion);
        nuevo.siguiente = cabeza;
        cabeza = nuevo;
    }

    // Mostrar iterativo (para comparación)
    public void mostrarCancionesIterativo() {
        System.out.println("\n🎵 Playlist (Iterativo):");
        NodoCancion actual = cabeza;
        int indice = 1;
        while (actual != null) {
            System.out.printf("   %d. %s\n", indice++, actual);
            actual = actual.siguiente;
        }
        System.out.println("   Fin de la playlist");
    }
}

// Clase Principal con Ejemplos
public class ListaSimpleRecursiva {
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("  LISTAS SIMPLES CON RECURSIVIDAD");
        System.out.println("  Playlist de Canciones");
        System.out.println("========================================");

        Playlist playlist = new Playlist();

        System.out.println("\n--- AGREGANDO CANCIONES AL FINAL (RECURSIVO) ---");
        playlist.agregarCancionRecursivo("Bohemian Rhapsody", "Queen", 354);
        playlist.agregarCancionRecursivo("Stairway to Heaven", "Led Zeppelin", 482);
        playlist.agregarCancionRecursivo("Hotel California", "Eagles", 390);
        playlist.agregarCancionRecursivo("Sweet Child O' Mine", "Guns N' Roses", 356);
        playlist.agregarCancionRecursivo("Smells Like Teen Spirit", "Nirvana", 301);
        
        playlist.mostrarCancionesRecursivo();
        playlist.mostrarCancionesIterativo(); // Para verificar

        System.out.println("\n--- MOSTRAR EN ORDEN INVERSO (RECURSIVO) ---");
        playlist.mostrarCancionesInversoRecursivo();

        System.out.println("\n========================================");
        System.out.println("  FIN DE LA DEMOSTRACIÓN");
        System.out.println("========================================");
    }
}
