# Examen Primer Parcial - Pilas en C# .NET

**Materia:** Estructura de Datos I  
**Examen Primer Parcial:** Implementación de Pilas con POO en C# .NET

## Descripción
Implementación completa de una estructura de datos **Pila (Stack)** en C# .NET utilizando **Programación Orientada a Objetos (POO)**. La implementación usa genéricos para aceptar cualquier tipo de dato y demuestra diferentes casos de uso con **entrada de datos interactiva por consola**.

## ¿Qué es una Pila?
Una pila es una estructura de datos lineal que sigue el principio **LIFO** (Last In, First Out) - el último elemento en entrar es el primero en salir.

### Analogía del Mundo Real
Imagina una pila de platos:
- El último plato que colocas en la pila es el primero que puedes tomar
- Solo puedes acceder al plato que está en la cima
- Para tomar un plato del fondo, primero debes quitar todos los de arriba

### Aplicaciones Reales
1. **Historial de navegación:** Los sitios web se apilan y se desapilan al usar "atrás"
2. **Deshacer/Rehacer:** Las acciones se almacenan en una pila
3. **Evaluación de expresiones:** Cálculos matemáticos con paréntesis
4. **Llamadas a funciones:** El stack de llamadas del sistema
5. **Editor de texto:** Función de deshacer en editores

### Características principales:
- **LIFO:** Last In, First Out
- **Operaciones limitadas:** Solo se puede agregar a la cima (push) y quitar de la cima (pop)
- **Eficiente:** O(1) para push y pop con punteros
- **Aplicaciones:** Deshacer/rehacer, navegación web, evaluación de expresiones

**Nota:** Los diagramas Mermaid de los ejemplos están en el archivo `MERMAID.md`

## Requisitos previos

### 1. Instalar .NET SDK

**Linux/Ubuntu:**
```bash
wget https://packages.microsoft.com/config/ubuntu/20.04/packages-microsoft-prod.deb -O packages-microsoft-prod.deb
sudo dpkg -i packages-microsoft-prod.deb
sudo apt-get update
sudo apt-get install -y dotnet-sdk-7.0

# Verificar instalación
dotnet --version
```

**macOS (Homebrew):**
```bash
brew install dotnet

# Verificar instalación
dotnet --version
```

**Windows:**
Descargar desde: https://dotnet.microsoft.com/download

### 2. Estructura del proyecto
```
Practicas/
├── Pila.cs            # Implementación de la pila con POO
├── Main.cs            # Programa principal con casos de uso
├── README.md          # Documentación principal
└── MERMAID.md         # Diagramas de los ejemplos
```

## Uso

### Compilar:
```bash
dotnet build
```

### Ejecutar:
```bash
dotnet run
```

## Estructura de Datos con POO

### Clase Pila<T> (Genérica):
```csharp
public class Pila<T>
{
    private Nodo<T> cima;
    private int tamanio;

    // Clase interna Nodo
    private class Nodo<T>
    {
        public T dato;
        public Nodo<T> siguiente;
    }
}
```

### Conceptos de POO utilizados:
1. **Encapsulamiento:** Atributos privados con acceso controlado
2. **Genéricos:** `` para aceptar cualquier tipo de dato
3. **Clase Interna:** `Nodo<T>` definida dentro de `Pila<T>`
4. **Constructores:** Inicialización de objetos
5. **Métodos:** Operaciones de la pila

## Operaciones de la Pila

### 1. Push (Apilar)
Agrega un elemento a la cima de la pila.
```csharp
public void Push(T dato)
{
    Nodo<T> nuevoNodo = new Nodo<T>(dato);
    nuevoNodo.siguiente = cima;
    cima = nuevoNodo;
    tamanio++;
}
```
**Complejidad:** O(1)

### 2. Pop (Desapilar)
Elimina y retorna el elemento de la cima.
```csharp
public T Pop()
{
    if (EstaVacia()) return default(T);
    T dato = cima.dato;
    cima = cima.siguiente;
    tamanio--;
    return dato;
}
```
**Complejidad:** O(1)

### 3. Peek (Ver cima)
Retorna el elemento de la cima sin eliminarlo.
```csharp
public T Peek()
{
    if (EstaVacia()) return default(T);
    return cima.dato;
}
```
**Complejidad:** O(1)

## Casos de Uso Incluidos

### Caso 1: Pila de Enteros
```csharp
Pila<int> pilaEnteros = new Pila<int>();
pilaEnteros.Push(10);
pilaEnteros.Push(20);
pilaEnteros.Push(30);
```

### Caso 2: Pila de Strings
```csharp
Pila<string> pilaStrings = new Pila<string>();
pilaStrings.Push("Documento 1");
pilaStrings.Push("Documento 2");
```

### Caso 3: Pila de Objetos Personalizados
```csharp
Pila<Persona> pilaPersonas = new Pila<Persona>();
pilaPersonas.Push(new Persona("Juan", 25));
pilaPersonas.Push(new Persona("María", 30));
```

### Caso 4: Manejo de Pila Vacía
```csharp
Pila<double> pilaVacia = new Pila<double>();
pilaVacia.Pop(); // Retorna default y muestra mensaje
pilaVacia.Peek(); // Retorna default y muestra mensaje
```

### Caso 5: Pila con Muchos Elementos
```csharp
Pila<int> pilaGrande = new Pila<int>();
for (int i = 1; i <= 10; i++) {
    pilaGrande.Push(i * 10);
}
```

### Caso 6: Limpiar Pila
```csharp
Pila<string> pilaLimpiar = new Pila<string>();
// ... agregar elementos
pilaLimpiar.Limpiar(); // Elimina todos los elementos
```

## Ejemplo de Ejecución

El programa es **interactivo** y solicita datos por consola en cada caso de uso:

```
==========================================
   EXAMEN PRIMER PARCIAL - PILAS EN C#
   Implementación con POO
==========================================

--- CASO 1: PILA DE ENTEROS ---
Ingrese cantidad de enteros a apilar: 3
Ingrese entero #1: 10
Ingrese entero #2: 20
Ingrese entero #3: 30
Elemento apilado: 10
Elemento apilado: 20
Elemento apilado: 30

=== PILA ACTUAL ===
Tamaño: 3
CIMA ->
  [30]
  [20]
  [10]
<- FONDO
==================

¿Desea desapilar un elemento? (s/n): s
Elemento desapilado: 30

=== PILA ACTUAL ===
Tamaño: 2
CIMA ->
  [20]
  [10]
<- FONDO
==================

Cima actual: 20

--- CASO 2: PILA DE STRINGS ---
Ingrese cantidad de strings a apilar: 3
Ingrese string #1: Documento 1
Ingrese string #2: Documento 2
Ingrese string #3: Documento 3
Elemento apilado: Documento 1
Elemento apilado: Documento 2
Elemento apilado: Documento 3

=== PILA ACTUAL ===
Tamaño: 3
CIMA ->
  [Documento 3]
  [Documento 2]
  [Documento 1]
<- FONDO
==================

¿Cuántos elementos desea desapilar? 2
Desapilando: Documento 3
Desapilando: Documento 2

=== PILA ACTUAL ===
Tamaño: 1
CIMA ->
  [Documento 1]
<- FONDO
==================

--- CASO 3: PILA DE OBJETOS PERSONA ---
Ingrese cantidad de personas a apilar: 3
Ingrese nombre #1: Juan
Ingrese edad #1: 25
Ingrese nombre #2: María
Ingrese edad #2: 30
Ingrese nombre #3: Carlos
Ingrese edad #3: 28
Elemento apilado: Juan (25 años)
Elemento apilado: María (30 años)
Elemento apilado: Carlos (28 años)

=== PILA ACTUAL ===
Tamaño: 3
CIMA ->
  [Carlos (28 años)]
  [María (30 años)]
  [Juan (25 años)]
<- FONDO
==================

Cima: Carlos (28 años)
Elemento desapilado: Carlos (28 años)

=== PILA ACTUAL ===
Tamaño: 2
CIMA ->
  [María (30 años)]
  [Juan (25 años)]
<- FONDO
==================

--- CASO 4: PILA VACÍA - MANEJO DE ERRORES ---
¿Está vacía? True
Intentando desapilar de pila vacía...
La pila está vacía. No se puede desapilar.
Intentando ver cima de pila vacía...
La pila está vacía. No hay cima.

--- CASO 5: PILA CON MUCHOS ELEMENTOS ---
Ingrese cantidad de elementos a apilar: 10
Elemento apilado: 10
Elemento apilado: 20
... (hasta 100)
Elemento apilado: 100

=== PILA ACTUAL ===
Tamaño: 10
CIMA ->
  [100]
  [90]
  [80]
  [70]
  [60]
  [50]
  [40]
  [30]
  [20]
  [10]
<- FONDO
==================

Tamaño: 10

¿Cuántos elementos desea desapilar? 5
Desapilando 5 elementos:
Elemento desapilado: 100
Elemento desapilado: 90
Elemento desapilado: 80
Elemento desapilado: 70
Elemento desapilado: 60

=== PILA ACTUAL ===
Tamaño: 5
CIMA ->
  [50]
  [40]
  [30]
  [20]
  [10]
<- FONDO
==================

--- CASO 6: LIMPIAR PILA ---
Ingrese cantidad de elementos a apilar: 3
Ingrese elemento #1: A
Ingrese elemento #2: B
Ingrese elemento #3: C
Elemento apilado: A
Elemento apilado: B
Elemento apilado: C
Antes de limpiar:

=== PILA ACTUAL ===
Tamaño: 3
CIMA ->
  [C]
  [B]
  [A]
<- FONDO
==================

Limpiando pila...
Pila limpiada.
Después de limpiar:
La pila está vacía.

==========================================
   FIN DE LA DEMOSTRACIÓN
==========================================
```

## Complejidad Temporal

| Operación | Complejidad | Explicación |
|-----------|-------------|-------------|
| Push | O(1) | Solo actualiza punteros |
| Pop | O(1) | Solo actualiza punteros |
| Peek | O(1) | Solo accede a la cima |
| EstaVacia | O(1) | Verifica si cima es null |
| GetTamanio | O(1) | Retorna variable de instancia |
| MostrarPila | O(n) | Recorre toda la pila |
| Limpiar | O(1) | Solo nullifica punteros |

## Ventajas de la Implementación con POO

1. **Reutilización:** La clase `Pila<T>` puede usarse con cualquier tipo de dato
2. **Encapsulamiento:** Los detalles de implementación están ocultos
3. **Mantenibilidad:** Código organizado y fácil de modificar
4. **Extensibilidad:** Fácil agregar nuevas funcionalidades
5. **Type Safety:** Genéricos previenen errores en tiempo de compilación

## Diferencias Pila vs Cola vs Lista

| Aspecto | Pila (LIFO) | Cola (FIFO) | Lista |
|---------|-------------|-------------|-------|
| Principio | Last In, First Out | First In, First Out | Acceso aleatorio |
| Inserción | Solo a la cima | Solo al final | Cualquier posición |
| Eliminación | Solo de la cima | Solo del frente | Cualquier posición |
| Acceso | Solo cima | Solo frente | Cualquier elemento |
| Aplicación | Deshacer/Rehacer | Filas, buffers | Datos generales |

## Autor
Raul Heredia
