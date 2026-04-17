import java.util.Random;

/**
 * PotenciaRecursiva.java
 * Calcula la potencia de un número usando multiplicaciones sucesivas de forma recursiva.
 * 
 * Teoría:
 * La potencia base^exponente se calcula multiplicando la base por sí misma exponente veces.
 * No usamos el operador ** ni Math.pow(), solo multiplicaciones.
 * 
 * Caso base: Si el exponente es 0, el resultado es 1 (cualquier número elevado a 0 es 1)
 * Caso recursivo: potencia(base, exp) = base * potencia(base, exp-1)
 * 
 * Ejemplo: potencia(2, 3)
 * potencia(2, 3) = 2 * potencia(2, 2) = 2 * 2 * potencia(2, 1) = 2 * 2 * 2 * potencia(2, 0) = 2 * 2 * 2 * 1 = 8
 */
public class PotenciaRecursiva {
    
    /**
     * Calcula la potencia de un número usando multiplicaciones sucesivas.
     * 
     * @param base La base de la potencia
     * @param exponente El exponente (debe ser >= 0)
     * @return El resultado de base elevado a exponente
     */
    public static long potencia(int base, int exponente) {
        // Caso base: cualquier número elevado a 0 es 1
        if (exponente == 0) {
            return 1;
        }
        // Caso recursivo: base * potencia(base, exponente-1)
        return base * potencia(base, exponente - 1);
    }
    
    public static void main(String[] args) {
        Random random = new Random();
        
        // Generar base aleatoria entre 2 y 10 y exponente aleatorio entre 0 y 15
        int base = random.nextInt(9) + 2;
        int exponente = random.nextInt(16);
        
        System.out.println("=== Potencia usando Multiplicaciones Sucesivas ===");
        System.out.println("Base: " + base);
        System.out.println("Exponente: " + exponente);
        System.out.println(base + "^" + exponente + " = " + potencia(base, exponente));
        
        // Ejemplos adicionales con números aleatorios
        System.out.println("\n--- Ejemplos adicionales ---");
        System.out.println((random.nextInt(9) + 2) + "^" + random.nextInt(16) + " = " + potencia(random.nextInt(9) + 2, random.nextInt(16)));
        System.out.println((random.nextInt(9) + 2) + "^" + random.nextInt(16) + " = " + potencia(random.nextInt(9) + 2, random.nextInt(16)));
        System.out.println((random.nextInt(9) + 2) + "^" + random.nextInt(16) + " = " + potencia(random.nextInt(9) + 2, random.nextInt(16)));
        System.out.println((random.nextInt(9) + 2) + "^" + random.nextInt(16) + " = " + potencia(random.nextInt(9) + 2, random.nextInt(16)));
        System.out.println((random.nextInt(9) + 2) + "^" + random.nextInt(16) + " = " + potencia(random.nextInt(9) + 2, random.nextInt(16)));
        System.out.println((random.nextInt(9) + 2) + "^" + random.nextInt(16) + " = " + potencia(random.nextInt(9) + 2, random.nextInt(16)));
    }
}
