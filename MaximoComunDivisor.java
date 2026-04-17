import java.util.Random;

/**
 * MaximoComunDivisor.java
 * Calcula el Máximo Común Divisor (MCD) de dos números usando el algoritmo de Euclides.
 * 
 * Teoría:
 * El algoritmo de Euclides se basa en el principio de que el MCD de dos números
 * también divide al resto de la división del mayor entre el menor.
 * 
 * Caso base: Si b es 0, entonces MCD(a, b) = a
 * Caso recursivo: MCD(a, b) = MCD(b, a % b)
 * 
 * Ejemplo: MCD(48, 18)
 * MCD(48, 18) = MCD(18, 12) = MCD(12, 6) = MCD(6, 0) = 6
 */
public class MaximoComunDivisor {
    
    /**
     * Calcula el Máximo Común Divisor de dos números usando el algoritmo de Euclides.
     * 
     * @param a Primer número (debe ser positivo)
     * @param b Segundo número (debe ser positivo)
     * @return El Máximo Común Divisor de a y b
     */
    public static int mcd(int a, int b) {
        // Caso base: si b es 0, el MCD es a
        if (b == 0) {
            return a;
        }
        // Caso recursivo: MCD(b, a % b)
        return mcd(b, a % b);
    }
    
    public static void main(String[] args) {
        Random random = new Random();
        
        // Generar números aleatorios entre 1 y 500
        int num1 = random.nextInt(500) + 1;
        int num2 = random.nextInt(500) + 1;
        
        System.out.println("=== Máximo Común Divisor (Algoritmo de Euclides) ===");
        System.out.println("Número 1: " + num1);
        System.out.println("Número 2: " + num2);
        System.out.println("MCD(" + num1 + ", " + num2 + "): " + mcd(num1, num2));
        
        // Ejemplos adicionales con números aleatorios
        System.out.println("\n--- Ejemplos adicionales ---");
        System.out.println("MCD(" + (random.nextInt(100) + 1) + ", " + (random.nextInt(100) + 1) + "): " + mcd(random.nextInt(100) + 1, random.nextInt(100) + 1));
        System.out.println("MCD(" + (random.nextInt(200) + 1) + ", " + (random.nextInt(200) + 1) + "): " + mcd(random.nextInt(200) + 1, random.nextInt(200) + 1));
        System.out.println("MCD(" + (random.nextInt(50) + 1) + ", " + (random.nextInt(50) + 1) + "): " + mcd(random.nextInt(50) + 1, random.nextInt(50) + 1));
        System.out.println("MCD(" + (random.nextInt(300) + 1) + ", " + (random.nextInt(300) + 1) + "): " + mcd(random.nextInt(300) + 1, random.nextInt(300) + 1));
        System.out.println("MCD(" + (random.nextInt(150) + 1) + ", " + (random.nextInt(150) + 1) + "): " + mcd(random.nextInt(150) + 1, random.nextInt(150) + 1));
    }
}
