import java.util.Random;

/**
 * ContarDigitos.java
 * Cuenta la cantidad de dígitos de un número entero de forma recursiva.
 * 
 * Teoría:
 * Para contar los dígitos de un número, podemos dividir el número por 10
 * sucesivamente hasta que el número sea 0.
 * 
 * Caso base: Si el número es 0, tiene 1 dígito (caso especial) o si n < 10, tiene 1 dígito
 * Caso recursivo: digitos(n) = 1 + digitos(n/10)
 * 
 * Ejemplo: digitos(1234) = 1 + digitos(123) = 1 + 1 + digitos(12) = 1 + 1 + 1 + digitos(1) = 4
 */
public class ContarDigitos {
    
    /**
     * Cuenta la cantidad de dígitos de un número entero de forma recursiva.
     * 
     * @param n El número del cual se quieren contar los dígitos
     * @return La cantidad de dígitos del número
     */
    public static int contarDigitos(int n) {
        // Caso especial: si el número es 0, tiene 1 dígito
        if (n == 0) {
            return 1;
        }
        // Caso base: si el número está entre -9 y 9, tiene 1 dígito
        if (Math.abs(n) < 10) {
            return 1;
        }
        // Caso recursivo: 1 + dígitos del número sin el último dígito
        return 1 + contarDigitos(n / 10);
    }
    
    public static void main(String[] args) {
        Random random = new Random();
        
        // Generar número aleatorio entre 1 y 999999
        int numero = random.nextInt(999999) + 1;
        
        System.out.println("=== Contar Dígitos de un Número ===");
        System.out.println("Número: " + numero);
        System.out.println("Cantidad de dígitos: " + contarDigitos(numero));
        
        // Ejemplos adicionales con números aleatorios
        System.out.println("\n--- Ejemplos adicionales ---");
        System.out.println("Dígitos de " + random.nextInt(10) + ": " + contarDigitos(random.nextInt(10)));
        System.out.println("Dígitos de " + (random.nextInt(90) + 10) + ": " + contarDigitos(random.nextInt(90) + 10));
        System.out.println("Dígitos de " + (random.nextInt(900) + 100) + ": " + contarDigitos(random.nextInt(900) + 100));
        System.out.println("Dígitos de " + (random.nextInt(900000) + 100000) + ": " + contarDigitos(random.nextInt(900000) + 100000));
        System.out.println("Dígitos de -" + (random.nextInt(900) + 100) + ": " + contarDigitos(-(random.nextInt(900) + 100)));
    }
}
