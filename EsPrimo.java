import java.util.Random;

/**
 * EsPrimo.java
 * Verifica si un número es primo de forma recursiva.
 * 
 * Teoría:
 * Un número es primo si solo es divisible por 1 y por sí mismo.
 * Para verificarlo recursivamente, intentamos dividir el número por todos
 * los valores desde 2 hasta la raíz cuadrada del número.
 * 
 * Caso base: Si el divisor es mayor que la raíz cuadrada de n, es primo
 * Caso base: Si n es divisible por divisor, no es primo
 * Caso recursivo: esPrimo(n, divisor) = esPrimo(n, divisor + 1)
 * 
 * Ejemplo: esPrimo(17, 2)
 * esPrimo(17, 2) -> 17%2 != 0 -> esPrimo(17, 3)
 * esPrimo(17, 3) -> 17%3 != 0 -> esPrimo(17, 4)
 * esPrimo(17, 4) -> 4 > sqrt(17) -> true (es primo)
 */
public class EsPrimo {
    
    /**
     * Verifica si un número es primo de forma recursiva (método auxiliar).
     * 
     * @param n El número a verificar
     * @param divisor El divisor actual (comienza en 2)
     * @return true si el número es primo, false en caso contrario
     */
    private static boolean esPrimoAux(int n, int divisor) {
        // Caso base: si el divisor es mayor que la raíz cuadrada de n, es primo
        if (divisor * divisor > n) {
            return true;
        }
        // Caso base: si n es divisible por divisor, no es primo
        if (n % divisor == 0) {
            return false;
        }
        // Caso recursivo: probar con el siguiente divisor
        return esPrimoAux(n, divisor + 1);
    }
    
    /**
     * Verifica si un número es primo de forma recursiva (método público).
     * 
     * @param n El número a verificar (debe ser > 1)
     * @return true si el número es primo, false en caso contrario
     */
    public static boolean esPrimo(int n) {
        // Casos especiales
        if (n <= 1) {
            return false;
        }
        if (n == 2) {
            return true;
        }
        // Llamada al método auxiliar comenzando desde el divisor 2
        return esPrimoAux(n, 2);
    }
    
    public static void main(String[] args) {
        Random random = new Random();
        
        // Generar número aleatorio entre 0 y 200
        int numero = random.nextInt(201);
        
        System.out.println("=== Verificar si un Número es Primo ===");
        System.out.println("Número: " + numero);
        System.out.println("¿Es primo? " + esPrimo(numero));
        
        // Ejemplos adicionales con números aleatorios
        System.out.println("\n--- Ejemplos adicionales ---");
        System.out.println("¿Es primo " + random.nextInt(201) + "? " + esPrimo(random.nextInt(201)));
        System.out.println("¿Es primo " + random.nextInt(201) + "? " + esPrimo(random.nextInt(201)));
        System.out.println("¿Es primo " + random.nextInt(201) + "? " + esPrimo(random.nextInt(201)));
        System.out.println("¿Es primo " + random.nextInt(201) + "? " + esPrimo(random.nextInt(201)));
        System.out.println("¿Es primo " + random.nextInt(201) + "? " + esPrimo(random.nextInt(201)));
        System.out.println("¿Es primo " + random.nextInt(201) + "? " + esPrimo(random.nextInt(201)));
        System.out.println("¿Es primo " + random.nextInt(201) + "? " + esPrimo(random.nextInt(201)));
        System.out.println("¿Es primo " + random.nextInt(201) + "? " + esPrimo(random.nextInt(201)));
    }
}
