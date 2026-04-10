# Microevaluación 3 - Colas en Java

**Materia:** Estructura de Datos I  
**Microevaluación 3:** Implementación de Colas con POO en Java

## Descripción
Implementación completa de una estructura de datos **Cola (Queue)** en Java utilizando **Programación Orientada a Objetos (POO)**. La implementación usa genéricos para aceptar cualquier tipo de dato y demuestra diferentes casos de uso.

## ¿Qué es una Cola?
Una cola es una estructura de datos lineal que sigue el principio **FIFO** (First In, First Out) - el primer elemento en entrar es el primero en salir.

### Características principales:
- **FIFO:** First In, First Out
- **Operaciones limitadas:** Solo se puede agregar al final (enqueue) y quitar del frente (dequeue)
- **Eficiente:** O(1) para enqueue y dequeue con punteros
- **Aplicaciones:** Colas de impresión, planificación de procesos, sistemas de tickets

## Diagrama de la Estructura

```
    ENQUEUE (agregar al final)
    ↓
    ┌─────┬─────┬─────┬─────┐
    │  A  │  B  │  C  │  D  │
    └─────┴─────┴─────┴─────┘
      ↑                   ↑
   FRENTE              FINAL
      ↑
    DEQUEUE (quitar del frente)
```

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
Practicas/
├── Cola.java          # Implementación de la cola con POO
├── Main.java          # Programa principal con casos de uso
└── README.md
```

## Uso

### Compilar:
```bash
javac Cola.java Main.java
```

### Ejecutar:
```bash
java Main
```

## Estructura de Datos con POO

### Clase Cola<T> (Genérica):
```java
public class Cola<T> {
    private Nodo<T> frente;    // Puntero al frente
    private Nodo<T> final;     // Puntero al final
    private int tamanio;       // Tamaño de la cola

    // Clase interna Nodo
    private static class Nodo<T> {
        T dato;
        Nodo<T> siguiente;
    }
}
```

### Conceptos de POO utilizados:
1. **Encapsulamiento:** Atributos privados con acceso controlado
2. **Genéricos:** `` para aceptar cualquier tipo de dato
3. **Clase Interna:** `Nodo<T>` definida dentro de `Cola<T>`
4. **Constructores:** Inicialización de objetos
5. **Métodos:** Operaciones de la cola

## Operaciones de la Cola

### 1. Enqueue (Encolar)
Agrega un elemento al final de la cola.
```java
public void enqueue(T dato) {
    Nodo<T> nuevoNodo = new Nodo<>(dato);
    if (estaVacia()) {
        frente = nuevoNodo;
        final = nuevoNodo;
    } else {
        final.siguiente = nuevoNodo;
        final = nuevoNodo;
    }
    tamanio++;
}
```
**Complejidad:** O(1)

### 2. Dequeue (Desencolar)
Elimina y retorna el elemento del frente.
```java
public T dequeue() {
    if (estaVacia()) return null;
    T dato = frente.dato;
    frente = frente.siguiente;
    if (frente == null) final = null;
    tamanio--;
    return dato;
}
```
**Complejidad:** O(1)

### 3. Peek (Ver frente)
Retorna el elemento del frente sin eliminarlo.
```java
public T peek() {
    if (estaVacia()) return null;
    return frente.dato;
}
```
**Complejidad:** O(1)

## Casos de Uso Incluidos

### Caso 1: Cola de Enteros
```java
Cola<Integer> colaEnteros = new Cola<>();
colaEnteros.enqueue(10);
colaEnteros.enqueue(20);
colaEnteros.enqueue(30);
```

### Caso 2: Cola de Strings
```java
Cola<String> colaStrings = new Cola<>();
colaStrings.enqueue("Cliente 1");
colaStrings.enqueue("Cliente 2");
```

### Caso 3: Cola de Objetos Personalizados
```java
Cola<Persona> colaPersonas = new Cola<>();
colaPersonas.enqueue(new Persona("Juan", 25));
colaPersonas.enqueue(new Persona("María", 30));
```

### Caso 4: Manejo de Cola Vacía
```java
Cola<Double> colaVacia = new Cola<>();
colaVacia.dequeue(); // Retorna null y muestra mensaje
colaVacia.peek();    // Retorna null y muestra mensaje
```

### Caso 5: Cola con Muchos Elementos
```java
Cola<Integer> colaGrande = new Cola<>();
for (int i = 1; i <= 10; i++) {
    colaGrande.enqueue(i * 10);
}
```

### Caso 6: Limpiar Cola
```java
Cola<String> colaLimpiar = new Cola<>();
// ... agregar elementos
colaLimpiar.limpiar(); // Elimina todos los elementos
```

## Ejemplo de Ejecución

```
==========================================
   MICROEVALUACIÓN 3 - COLAS EN JAVA
   Implementación con POO
==========================================

--- CASO 1: COLA DE ENTEROS ---
Elemento encolado: 10
Elemento encolado: 20
Elemento encolado: 30

=== COLA ACTUAL ===
Tamaño: 3
FRENTE ->
  [10]
  [20]
  [30]
<- FINAL
==================

Elemento desencolado: 10

=== COLA ACTUAL ===
Tamaño: 2
FRENTE ->
  [20]
  [30]
<- FINAL
==================

Frente actual: 20

--- CASO 2: COLA DE STRINGS ---
Elemento encolado: Cliente 1
Elemento encolado: Cliente 2
Elemento encolado: Cliente 3

=== COLA ACTUAL ===
Tamaño: 3
FRENTE ->
  [Cliente 1]
  [Cliente 2]
  [Cliente 3]
<- FINAL
==================

Atendiendo: Cliente 1
Atendiendo: Cliente 2

=== COLA ACTUAL ===
Tamaño: 1
FRENTE ->
  [Cliente 3]
<- FINAL
==================

--- CASO 3: COLA DE OBJETOS PERSONA ---
Elemento encolado: Juan (25 años)
Elemento encolado: María (30 años)
Elemento encolado: Carlos (28 años)

=== COLA ACTUAL ===
Tamaño: 3
FRENTE ->
  [Juan (25 años)]
  [María (30 años)]
  [Carlos (28 años)]
<- FINAL
==================

Siguiente en atender: Juan (25 años)
Elemento desencolado: Juan (25 años)

=== COLA ACTUAL ===
Tamaño: 2
FRENTE ->
  [María (30 años)]
  [Carlos (28 años)]
<- FINAL
==================

--- CASO 4: COLA VACÍA - MANEJO DE ERRORES ---
¿Está vacía? true
La cola está vacía. No se puede desencolar.
La cola está vacía. No hay frente.

--- CASO 5: COLA CON MUCHOS ELEMENTOS ---
Elemento encolado: 10
Elemento encolado: 20
... (hasta 100)
Elemento encolado: 100

=== COLA ACTUAL ===
Tamaño: 10
FRENTE ->
  [10]
  [20]
  [30]
  [40]
  [50]
  [60]
  [70]
  [80]
  [90]
  [100]
<- FINAL
==================

Tamaño: 10

Desencolando 5 elementos:
Elemento desencolado: 10
Elemento desencolado: 20
Elemento desencolado: 30
Elemento desencolado: 40
Elemento desencolado: 50

=== COLA ACTUAL ===
Tamaño: 5
FRENTE ->
  [60]
  [70]
  [80]
  [90]
  [100]
<- FINAL
==================

--- CASO 6: LIMPIAR COLA ---
Elemento encolado: A
Elemento encolado: B
Elemento encolado: C

Antes de limpiar:

=== COLA ACTUAL ===
Tamaño: 3
FRENTE ->
  [A]
  [B]
  [C]
<- FINAL
==================

Cola limpiada.

Después de limpiar:

La cola está vacía.

==========================================
   FIN DE LA DEMOSTRACIÓN
==========================================
```

## Complejidad Temporal

| Operación | Complejidad | Explicación |
|-----------|-------------|-------------|
| Enqueue | O(1) | Solo actualiza punteros |
| Dequeue | O(1) | Solo actualiza punteros |
| Peek | O(1) | Solo accede al frente |
| EstaVacia | O(1) | Verifica si frente es null |
| GetTamanio | O(1) | Retorna variable de instancia |
| MostrarCola | O(n) | Recorre toda la cola |
| Limpiar | O(1) | Solo nullifica punteros |

## Ventajas de la Implementación con POO

1. **Reutilización:** La clase `Cola<T>` puede usarse con cualquier tipo de dato
2. **Encapsulamiento:** Los detalles de implementación están ocultos
3. **Mantenibilidad:** Código organizado y fácil de modificar
4. **Extensibilidad:** Fácil agregar nuevas funcionalidades
5. **Type Safety:** Genéricos previenen errores en tiempo de compilación

## Diferencias Cola vs Pila vs Lista

| Aspecto | Cola (FIFO) | Pila (LIFO) | Lista |
|---------|-------------|-------------|-------|
| Principio | First In, First Out | Last In, First Out | Acceso aleatorio |
| Inserción | Solo al final | Solo al final | Cualquier posición |
| Eliminación | Solo del frente | Solo del final | Cualquier posición |
| Acceso | Solo frente | Solo cima | Cualquier elemento |
| Aplicación | Filas, buffers | Deshacer/Rehacer | Datos generales |

## Autor
Raul Heredia
