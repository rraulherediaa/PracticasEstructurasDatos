/**
 * Clase principal para demostrar el uso de la Cola implementada con POO.
 * Incluye diferentes casos de uso y ejemplos.
 * 
 * @author Raul Heredia
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("==========================================");
        System.out.println("   MICROEVALUACIÓN 3 - COLAS EN JAVA");
        System.out.println("   Implementación con POO");
        System.out.println("==========================================\n");

        // CASO 1: Cola de enteros
        System.out.println("--- CASO 1: COLA DE ENTEROS ---");
        Cola<Integer> colaEnteros = new Cola<>();
        colaEnteros.enqueue(10);
        colaEnteros.enqueue(20);
        colaEnteros.enqueue(30);
        colaEnteros.mostrarCola();
        colaEnteros.dequeue();
        colaEnteros.mostrarCola();
        System.out.println("Frente actual: " + colaEnteros.peek());
        System.out.println();

        // CASO 2: Cola de Strings
        System.out.println("--- CASO 2: COLA DE STRINGS ---");
        Cola<String> colaStrings = new Cola<>();
        colaStrings.enqueue("Cliente 1");
        colaStrings.enqueue("Cliente 2");
        colaStrings.enqueue("Cliente 3");
        colaStrings.mostrarCola();
        System.out.println("Atendiendo: " + colaStrings.dequeue());
        System.out.println("Atendiendo: " + colaStrings.dequeue());
        colaStrings.mostrarCola();
        System.out.println();

        // CASO 3: Cola de objetos personalizados
        System.out.println("--- CASO 3: COLA DE OBJETOS PERSONA ---");
        Cola<Persona> colaPersonas = new Cola<>();
        colaPersonas.enqueue(new Persona("Juan", 25));
        colaPersonas.enqueue(new Persona("María", 30));
        colaPersonas.enqueue(new Persona("Carlos", 28));
        colaPersonas.mostrarCola();
        System.out.println("Siguiente en atender: " + colaPersonas.peek());
        colaPersonas.dequeue();
        colaPersonas.mostrarCola();
        System.out.println();

        // CASO 4: Cola vacía - manejo de errores
        System.out.println("--- CASO 4: COLA VACÍA - MANEJO DE ERRORES ---");
        Cola<Double> colaVacia = new Cola<>();
        System.out.println("¿Está vacía? " + colaVacia.estaVacia());
        colaVacia.dequeue(); // Intentar desencolar de cola vacía
        colaVacia.peek(); // Intentar ver frente de cola vacía
        System.out.println();

        // CASO 5: Cola con muchos elementos
        System.out.println("--- CASO 5: COLA CON MUCHOS ELEMENTOS ---");
        Cola<Integer> colaGrande = new Cola<>();
        for (int i = 1; i <= 10; i++) {
            colaGrande.enqueue(i * 10);
        }
        colaGrande.mostrarCola();
        System.out.println("Tamaño: " + colaGrande.getTamanio());
        
        // Desencolar varios elementos
        System.out.println("\nDesencolando 5 elementos:");
        for (int i = 0; i < 5; i++) {
            colaGrande.dequeue();
        }
        colaGrande.mostrarCola();
        System.out.println();

        // CASO 6: Limpiar cola
        System.out.println("--- CASO 6: LIMPIAR COLA ---");
        Cola<String> colaLimpiar = new Cola<>();
        colaLimpiar.enqueue("A");
        colaLimpiar.enqueue("B");
        colaLimpiar.enqueue("C");
        System.out.println("Antes de limpiar:");
        colaLimpiar.mostrarCola();
        colaLimpiar.limpiar();
        System.out.println("Después de limpiar:");
        colaLimpiar.mostrarCola();
        System.out.println();

        System.out.println("==========================================");
        System.out.println("   FIN DE LA DEMOSTRACIÓN");
        System.out.println("==========================================");
    }
}

/**
 * Clase Persona para demostrar colas de objetos personalizados.
 */
class Persona {
    private String nombre;
    private int edad;

    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    @Override
    public String toString() {
        return nombre + " (" + edad + " años)";
    }
}
