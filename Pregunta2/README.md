# Examen Primer Parcial - Quick Sort en Java

**Materia:** Estructura de Datos I  
**Examen Primer Parcial:** Implementación de Quick Sort en Java

## Descripción
Implementación completa del algoritmo de ordenamiento **Quick Sort** en Java. La implementación demuestra su uso con diferentes tipos de datos: enteros, strings y objetos personalizados, utilizando sobrecarga de métodos y **entrada de datos interactiva por consola**.

## ¿Qué es Quick Sort?
Quick Sort es un algoritmo de ordenamiento eficiente que sigue el paradigma "divide y vencerás". Selecciona un elemento pivote y particiona el arreglo en dos sub-arreglos: elementos menores al pivote y elementos mayores al pivote.

### Características principales:
- **Complejidad promedio:** O(n log n)
- **Complejidad peor caso:** O(n²)
- **In-place:** No requiere memoria adicional significativa
- **Divide y vencerás:** Divide el problema en sub-problemas más pequeños
- **Pivote:** Elemento clave para la partición

### Analogía del Mundo Real
Imagina organizar una pila de libros por altura:
- Eliges un libro como referencia (pivote)
- Colocas los libros más bajos a un lado
- Colocas los libros más altos al otro lado
- Repites el proceso para cada grupo

### Aplicaciones Reales
1. **Bases de datos:** Ordenamiento de registros
2. **Sistemas de archivos:** Organización de archivos
3. **Algoritmos de búsqueda:** Preprocesamiento de datos
4. **Compiladores:** Ordenamiento de símbolos
5. **Procesamiento de datos:** Ordenamiento masivo

## Requisitos previos

### 1. Instalar Java (JDK)

**Linux/Ubuntu:**
```bash
sudo apt-get update
sudo apt-get install default-jdk

# Verificar instalación
java -version
javac -version
```

**macOS (Homebrew):**
```bash
brew install openjdk

# Verificar instalación
java -version
```

**Windows:**
Descargar desde: https://www.oracle.com/java/technologies/downloads/

### 2. Estructura del proyecto
```
Pregunta2/
├── QuickSort.java      # Implementación de Quick Sort
├── Main.java          # Programa principal con ejemplos
├── Persona.java       # Clase auxiliar para objetos
├── README.md          # Documentación principal
└── MERMAID.md         # Diagramas de los ejemplos
```

## Uso

### Compilar:
```bash
javac QuickSort.java Main.java Persona.java
```

### Ejecutar:
```bash
java Main
```

## Algoritmo Quick Sort

### Funcionamiento
1. **Elegir pivote:** Generalmente el último elemento
2. **Partición:** Reorganizar el arreglo alrededor del pivote
3. **Recursión:** Aplicar Quick Sort a los sub-arreglos izquierdo y derecho

### Código del algoritmo
```java
public static void quickSort(int[] arr, int low, int high) {
    if (low < high) {
        int pi = partition(arr, low, high);
        quickSort(arr, low, pi - 1);
        quickSort(arr, pi + 1, high);
    }
}
```

### Método de partición
```java
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
```

## Casos de Uso Incluidos

### Caso 1: Quick Sort de Enteros
```java
int[] arr = {64, 34, 25, 12, 22, 11, 90};
QuickSort.quickSort(arr, 0, arr.length - 1);
```

### Caso 2: Quick Sort de Strings
```java
String[] arr = {"z", "a", "m", "k", "b"};
QuickSort.quickSort(arr, 0, arr.length - 1);
```

### Caso 3: Quick Sort de Objetos Persona (por edad)
```java
Persona[] arr = {
    new Persona("Juan", 30),
    new Persona("María", 25),
    new Persona("Carlos", 35)
};
QuickSort.quickSort(arr, 0, arr.length - 1);
```

## Ejemplo de Ejecución

El programa es **interactivo** y solicita datos por consola en cada caso de uso:

```
==========================================
   EXAMEN PRIMER PARCIAL - QUICK SORT
   Implementación en Java
==========================================

--- CASO 1: QUICK SORT DE ENTEROS ---
Ingrese cantidad de enteros: 7
Ingrese entero #1: 64
Ingrese entero #2: 34
Ingrese entero #3: 25
Ingrese entero #4: 12
Ingrese entero #5: 22
Ingrese entero #6: 11
Ingrese entero #7: 90
Arreglo original: 64 34 25 12 22 11 90 
Arreglo ordenado: 11 12 22 25 34 64 90 

--- CASO 2: QUICK SORT DE STRINGS ---
Ingrese cantidad de strings: 5
Ingrese string #1: z
Ingrese string #2: a
Ingrese string #3: m
Ingrese string #4: k
Ingrese string #5: b
Arreglo original: z a m k b 
Arreglo ordenado: a b k m z 

--- CASO 3: QUICK SORT DE PERSONAS (POR EDAD) ---
Ingrese cantidad de personas: 4
Ingrese nombre #1: Juan
Ingrese edad #1: 30
Ingrese nombre #2: María
Ingrese edad #2: 25
Ingrese nombre #3: Carlos
Ingrese edad #3: 35
Ingrese nombre #4: Ana
Ingrese edad #4: 28
Arreglo original: Juan (30 años) María (25 años) Carlos (35 años) Ana (28 años) 
Arreglo ordenado: María (25 años) Ana (28 años) Juan (30 años) Carlos (35 años) 

==========================================
   FIN DE LA DEMOSTRACIÓN
==========================================
```

## Complejidad Temporal

| Caso | Complejidad | Explicación |
|------|-------------|-------------|
| Mejor caso | O(n log n) | Partición balanceada |
| Caso promedio | O(n log n) | Partición razonablemente balanceada |
| Peor caso | O(n²) | Partición desbalanceada (ya ordenado) |
| Espacio | O(log n) | Stack de llamadas recursivas |

## Ventajas de Quick Sort

1. **Eficiente:** O(n log n) en el caso promedio
2. **In-place:** No requiere memoria adicional significativa
3. **Cache-friendly:** Buen rendimiento en memoria cache
4. **Versátil:** Funciona con diferentes tipos de datos
5. **Adaptable:** Se puede optimizar con diferentes estrategias de pivote

## Comparación con otros algoritmos

| Algoritmo | Mejor caso | Promedio | Peor caso | Estable |
|-----------|-----------|----------|-----------|---------|
| Quick Sort | O(n log n) | O(n log n) | O(n²) | No |
| Merge Sort | O(n log n) | O(n log n) | O(n log n) | Sí |
| Heap Sort | O(n log n) | O(n log n) | O(n log n) | No |
| Bubble Sort | O(n) | O(n²) | O(n²) | Sí |

## Autor
Raul Heredia
