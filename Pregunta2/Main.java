/**
 * Clase principal para demostrar el uso de Quick Sort en Java.
 * Incluye 3 ejemplos: enteros, strings y objetos personalizados con entrada interactiva.
 * 
 * @author Raul Heredia
 * @version 1.0
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        System.out.println("==========================================");
        System.out.println("   EXAMEN PRIMER PARCIAL - QUICK SORT");
        System.out.println("   Implementación en Java");
        System.out.println("==========================================\n");

        // CASO 1: Quick Sort de enteros
        System.out.println("--- CASO 1: QUICK SORT DE ENTEROS ---");
        System.out.print("Ingrese cantidad de enteros: ");
        System.out.flush();
        int cantidadEnteros = 0;
        try {
            cantidadEnteros = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException e) {
            System.out.println("Error: Debe ingresar un número válido.");
            cantidadEnteros = 0;
        }
        
        int[] arrEnteros = new int[cantidadEnteros];
        
        for (int i = 0; i < cantidadEnteros; i++) {
            System.out.print("Ingrese entero #" + (i + 1) + ": ");
            System.out.flush();
            try {
                arrEnteros[i] = Integer.parseInt(reader.readLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número válido.");
                arrEnteros[i] = 0;
            }
        }
        
        System.out.print("Arreglo original: ");
        QuickSort.printArray(arrEnteros);
        
        QuickSort.quickSort(arrEnteros, 0, arrEnteros.length - 1);
        
        System.out.print("Arreglo ordenado: ");
        QuickSort.printArray(arrEnteros);
        System.out.println();

        // CASO 2: Quick Sort de strings
        System.out.println("--- CASO 2: QUICK SORT DE STRINGS ---");
        System.out.print("Ingrese cantidad de strings: ");
        System.out.flush();
        int cantidadStrings = 0;
        try {
            cantidadStrings = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException e) {
            System.out.println("Error: Debe ingresar un número válido.");
            cantidadStrings = 0;
        }
        
        String[] arrStrings = new String[cantidadStrings];
        
        for (int i = 0; i < cantidadStrings; i++) {
            System.out.print("Ingrese string #" + (i + 1) + ": ");
            System.out.flush();
            try {
                arrStrings[i] = reader.readLine();
            } catch (IOException e) {
                System.out.println("Error al leer el string.");
                arrStrings[i] = "";
            }
        }
        
        System.out.print("Arreglo original: ");
        QuickSort.printArray(arrStrings);
        
        QuickSort.quickSort(arrStrings, 0, arrStrings.length - 1);
        
        System.out.print("Arreglo ordenado: ");
        QuickSort.printArray(arrStrings);
        System.out.println();

        // CASO 3: Quick Sort de objetos Persona (por edad)
        System.out.println("--- CASO 3: QUICK SORT DE PERSONAS (POR EDAD) ---");
        System.out.print("Ingrese cantidad de personas: ");
        System.out.flush();
        int cantidadPersonas = 0;
        try {
            cantidadPersonas = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException e) {
            System.out.println("Error: Debe ingresar un número válido.");
            cantidadPersonas = 0;
        }
        
        Persona[] arrPersonas = new Persona[cantidadPersonas];
        
        for (int i = 0; i < cantidadPersonas; i++) {
            System.out.print("Ingrese nombre #" + (i + 1) + ": ");
            System.out.flush();
            String nombre = "";
            try {
                nombre = reader.readLine();
            } catch (IOException e) {
                System.out.println("Error al leer el nombre.");
                nombre = "Desconocido";
            }
            
            System.out.print("Ingrese edad #" + (i + 1) + ": ");
            System.out.flush();
            int edad = 0;
            try {
                edad = Integer.parseInt(reader.readLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número válido.");
                edad = 0;
            }
            
            arrPersonas[i] = new Persona(nombre, edad);
        }
        
        System.out.print("Arreglo original: ");
        QuickSort.printArray(arrPersonas);
        
        QuickSort.quickSort(arrPersonas, 0, arrPersonas.length - 1);
        
        System.out.print("Arreglo ordenado: ");
        QuickSort.printArray(arrPersonas);
        System.out.println();

        System.out.println("==========================================");
        System.out.println("   FIN DE LA DEMOSTRACIÓN");
        System.out.println("==========================================");
        
        reader.close();
    }
}
