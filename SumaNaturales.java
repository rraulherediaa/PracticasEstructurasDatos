import java.util.Random;

/**
 * SumaNaturales.java
 * Calcula la suma de los primeros N números naturales de forma recursiva.
 * 
 * Teoría:
 * La suma de los primeros N números naturales puede expresarse como:
 * S(N) = 1 + 2 + 3 + ... + N
 * 
 * Caso base: Si N = 0, la suma es 0.
 * Caso recursivo: S(N) = N + S(N-1)
 * 
 * Ejemplo: S(5) = 5 + S(4) = 5 + 4 + S(3) = 5 + 4 + 3 + S(2) = 5 + 4 + 3 + 2 + S(1) = 5 + 4 + 3 + 2 + 1 + S(0) = 15
 */
public class SumaNaturales {
    
    /**
     * Calcula la suma de los primeros N números naturales de forma recursiva.
     * 
     * @param n El número hasta el cual se quiere sumar (debe ser >= 0)
     * @return La suma de los números de 1 hasta n
     */
    public static int sumaNaturales(int n) {
        // Caso base: si n es 0, la suma es 0
        if (n == 0) {
            return 0;
        }
        // Caso recursivo: n + suma de los números anteriores
        return n + sumaNaturales(n - 1);
    }
    
    public static void main(String[] args) {
        Random random = new Random();
        
        // Generar número aleatorio entre 1 y 100
        int numero = random.nextInt(100) + 1;
        
        System.out.println("=== Suma de los Primeros N Números Naturales ===");
        System.out.println("Número: " + numero);
        System.out.println("Suma de 1 hasta " + numero + ": " + sumaNaturales(numero));
        
        // Ejemplos adicionales con números aleatorios
        System.out.println("\n--- Ejemplos adicionales ---");
        System.out.println("Suma de 1 hasta " + (random.nextInt(20) + 1) + ": " + sumaNaturales(random.nextInt(20) + 1));
        System.out.println("Suma de 1 hasta " + (random.nextInt(50) + 1) + ": " + sumaNaturales(random.nextInt(50) + 1));
        System.out.println("Suma de 1 hasta " + (random.nextInt(100) + 1) + ": " + sumaNaturales(random.nextInt(100) + 1));
    }
}
