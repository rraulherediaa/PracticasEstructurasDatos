/**
 * Clase que implementa el algoritmo Quick Sort en Java.
 * Quick Sort es un algoritmo de ordenamiento eficiente con complejidad promedio O(n log n).
 * 
 * @author Raul Heredia
 * @version 1.0
 */
public class QuickSort {

    /**
     * Método principal de Quick Sort.
     * @param arr Arreglo a ordenar
     * @param low Índice inferior
     * @param high Índice superior
     */
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    /**
     * Método de partición - elige el pivote y particiona el arreglo.
     * @param arr Arreglo a particionar
     * @param low Índice inferior
     * @param high Índice superior
     * @return Índice del pivote
     */
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);
        
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    /**
     * Intercambia dos elementos en el arreglo.
     * @param arr Arreglo
     * @param i Primer índice
     * @param j Segundo índice
     */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * Imprime el arreglo con formato elegante.
     * @param arr Arreglo a imprimir
     */
    public static void printArray(int[] arr) {
        System.out.print("[ ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) System.out.print(", ");
        }
        System.out.println(" ]");
    }

    /**
     * Quick Sort para Strings (sobrecarga).
     * @param arr Arreglo de strings a ordenar
     * @param low Índice inferior
     * @param high Índice superior
     */
    public static void quickSort(String[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    /**
     * Método de partición para Strings.
     * @param arr Arreglo de strings a particionar
     * @param low Índice inferior
     * @param high Índice superior
     * @return Índice del pivote
     */
    private static int partition(String[] arr, int low, int high) {
        String pivot = arr[high];
        int i = (low - 1);
        
        for (int j = low; j < high; j++) {
            if (arr[j].compareTo(pivot) < 0) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    /**
     * Intercambia dos elementos en el arreglo de strings.
     * @param arr Arreglo
     * @param i Primer índice
     * @param j Segundo índice
     */
    private static void swap(String[] arr, int i, int j) {
        String temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * Imprime el arreglo de strings con formato elegante.
     * @param arr Arreglo a imprimir
     */
    public static void printArray(String[] arr) {
        System.out.print("[ ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) System.out.print(", ");
        }
        System.out.println(" ]");
    }

    /**
     * Quick Sort para objetos Persona (sobrecarga).
     * @param arr Arreglo de personas a ordenar
     * @param low Índice inferior
     * @param high Índice superior
     */
    public static void quickSort(Persona[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    /**
     * Método de partición para Persona (ordena por edad).
     * @param arr Arreglo de personas a particionar
     * @param low Índice inferior
     * @param high Índice superior
     * @return Índice del pivote
     */
    private static int partition(Persona[] arr, int low, int high) {
        Persona pivot = arr[high];
        int i = (low - 1);
        
        for (int j = low; j < high; j++) {
            if (arr[j].getEdad() < pivot.getEdad()) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    /**
     * Intercambia dos elementos en el arreglo de personas.
     * @param arr Arreglo
     * @param i Primer índice
     * @param j Segundo índice
     */
    private static void swap(Persona[] arr, int i, int j) {
        Persona temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * Imprime el arreglo de personas con formato elegante.
     * @param arr Arreglo a imprimir
     */
    public static void printArray(Persona[] arr) {
        System.out.print("[ ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) System.out.print(", ");
        }
        System.out.println(" ]");
    }
}
