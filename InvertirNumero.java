import java.util.Random;

/**
 * InvertirNumero.java
 * Invierte las cifras de un número entero de forma recursiva.
 * 
 * Teoría:
 * Para invertir un número, extraemos el último dígito y lo colocamos al principio.
 * Usamos un método auxiliar que acumula el número invertido.
 * 
 * Caso base: Si el número es 0, retornamos el acumulador
 * Caso recursivo: invertir(n, acum) = invertir(n/10, acum*10 + n%10)
 * 
 * Ejemplo: invertir(1234, 0)
 * invertir(1234, 0) = invertir(123, 4) = invertir(12, 43) = invertir(1, 432) = invertir(0, 4321) = 4321
 */
public class InvertirNumero {
    
    /**
     * Invierte un número entero de forma recursiva (método auxiliar).
     * 
     * @param n El número a invertir
     * @param acumulador El acumulador para construir el número invertido
     * @return El número invertido
     */
    private static int invertirAux(int n, int acumulador) {
        // Caso base: si n es 0, retornamos el acumulador
        if (n == 0) {
            return acumulador;
        }
        // Caso recursivo: extraemos el último dígito y lo agregamos al acumulador
        return invertirAux(n / 10, acumulador * 10 + (n % 10));
    }
    
    /**
     * Invierte un número entero de forma recursiva (método público).
     * 
     * @param n El número a invertir
     * @return El número invertido
     */
    public static int invertir(int n) {
        // Caso especial: si el número es 0
        if (n == 0) {
            return 0;
        }
        // Llamada al método auxiliar
        return invertirAux(n, 0);
    }
    
    public static void main(String[] args) {
        Random random = new Random();
        
        // Generar número aleatorio entre 1 y 999999999
        int numero = random.nextInt(999999999) + 1;
        
        System.out.println("=== Invertir un Número Entero ===");
        System.out.println("Número original: " + numero);
        System.out.println("Número invertido: " + invertir(numero));
        
        // Ejemplos adicionales con números aleatorios
        System.out.println("\n--- Ejemplos adicionales ---");
        System.out.println("Invertir " + random.nextInt(10) + ": " + invertir(random.nextInt(10)));
        System.out.println("Invertir " + (random.nextInt(90) + 10) + ": " + invertir(random.nextInt(90) + 10));
        System.out.println("Invertir " + (random.nextInt(900) + 100) + ": " + invertir(random.nextInt(900) + 100));
        System.out.println("Invertir " + (random.nextInt(9000) + 1000) + ": " + invertir(random.nextInt(9000) + 1000));
        System.out.println("Invertir " + (random.nextInt(900000000) + 100000000) + ": " + invertir(random.nextInt(900000000) + 100000000));
        System.out.println("Invertir " + (random.nextInt(9000) + 1000) + ": " + invertir(random.nextInt(9000) + 1000));
    }
}
