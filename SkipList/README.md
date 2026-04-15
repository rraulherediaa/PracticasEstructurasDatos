# Skip List en C# .NET

**Materia:** Estructura de Datos I  
**Implementación:** Skip List con POO en C# .NET

## Descripción
Skip List es una estructura de datos probabilística que permite búsqueda, inserción y eliminación en tiempo logarítmico promedio. Es una alternativa a los árboles balanceados como AVL y Red-Black Trees.

## ¿Qué es Skip List?
Skip List es una lista enlazada jerárquica con múltiples niveles. El nivel más bajo contiene todos los elementos, y los niveles superiores son "expres highways" que permiten saltar sobre múltiples elementos, acelerando las búsquedas.

### Características principales:
- **Complejidad promedio:** O(log n) para búsqueda, inserción y eliminación
- **Complejidad peor caso:** O(n) (muy improbable)
- **Probabilística:** Usa lanzamiento de moneda para determinar niveles
- **Simplicidad:** Más fácil de implementar que árboles balanceados
- **Memoria:** O(n) espacio adicional por los niveles extra

### Analogía
Imagina un edificio con múltiples pisos:
- **Piso 1 (base):** Todas las habitaciones (elementos)
- **Piso 2:** Algunas habitaciones conectadas por pasillos
- **Piso 3:** Menos habitaciones con pasillos más largos
- **Piso N:** Solo unas pocas habitaciones con pasillos muy largos

Para encontrar una habitación, puedes empezar en el piso más alto y bajar según necesites.

### Aplicaciones
- Bases de datos distribuidas
- Sistemas de archivos
- Implementaciones de diccionarios ordenados
- Algoritmos de enrutamiento en redes

## Requisitos Previos
- **.NET SDK 6.0 o superior**
- **IDE:** Visual Studio, VS Code, o cualquier editor compatible con C#
- **Sistema Operativo:** Windows, Linux, macOS

## Estructura del Proyecto
```
SkipList/
├── README.md          # Documentación completa
├── MERMAID.md         # Diagramas Mermaid
├── SkipList.cs        # Implementación de Skip List
└── Program.cs         # Ejemplos de uso (pendiente)
```

## Uso

### Instalación
```bash
# Crear nuevo proyecto de consola
dotnet new console -n SkipListApp
cd SkipListApp

# Copiar los archivos SkipList.cs y Program.cs
```

### Compilación
```bash
dotnet build
```

### Ejecución
```bash
dotnet run
```

## Estructura de Datos con POO

### Clases Principales

#### 1. SkipListNode<T>
Nodo de la Skip List que contiene:
- **Valor:** Dato almacenado
- **Next:** Arreglo de punteros a los siguientes nodos en cada nivel

#### 2. SkipList<T>
Clase principal que implementa:
- **Head:** Nodo cabeza (sentinela) con máximo nivel
- **MaxLevel:** Nivel máximo permitido
- **Probability:** Probabilidad de promoción de nivel
- **Random:** Generador de números aleatorios

### Operaciones Principales

#### 1. Búsqueda (Search)
- Comienza en el nivel más alto del nodo head
- Avanza mientras el siguiente nodo sea menor al buscado
- Baja un nivel cuando no puede avanzar más
- Repite hasta encontrar el elemento o llegar al nivel 0

#### 2. Inserción (Insert)
- Busca la posición correcta para el nuevo elemento
- Determina aleatoriamente el nivel del nuevo nodo
- Actualiza los punteros en todos los niveles correspondientes
- Complejidad promedio: O(log n)

#### 3. Eliminación (Delete)
- Busca el elemento a eliminar
- Actualiza los punteros en todos los niveles donde aparece
- Libera memoria del nodo
- Complejidad promedio: O(log n)

## Casos de Uso

### 1. Búsqueda de elementos
```csharp
SkipList<int> skipList = new SkipList<int>();
skipList.Insert(10);
skipList.Insert(20);
skipList.Insert(30);

bool found = skipList.Search(20); // true
```

### 2. Inserción de elementos
```csharp
SkipList<string> skipList = new SkipList<string>();
skipList.Insert("Juan");
skipList.Insert("María");
skipList.Insert("Carlos");
```

### 3. Eliminación de elementos
```csharp
SkipList<int> skipList = new SkipList<int>();
skipList.Insert(10);
skipList.Insert(20);
skipList.Delete(20); // Elimina 20
```

### 4. Recorrido de elementos
```csharp
SkipList<int> skipList = new SkipList<int>();
skipList.Insert(10);
skipList.Insert(20);
skipList.Insert(30);

skipList.Print(); // Imprime todos los elementos ordenados
```

## Ejemplo de Ejecución

```
==========================================
   SKIP LIST EN C# .NET
   Implementación con POO
==========================================

--- INSERCIÓN DE ELEMENTOS ---
Insertando: 10
Insertando: 20
Insertando: 30
Insertando: 15
Insertando: 25

Skip List después de inserciones:
Nivel 3: Head -> 20 -> null
Nivel 2: Head -> 10 -> 20 -> 30 -> null
Nivel 1: Head -> 10 -> 15 -> 20 -> 25 -> 30 -> null
Nivel 0: Head -> 10 -> 15 -> 20 -> 25 -> 30 -> null

--- BÚSQUEDA DE ELEMENTOS ---
Buscando: 15 -> Encontrado
Buscando: 40 -> No encontrado

--- ELIMINACIÓN DE ELEMENTOS ---
Eliminando: 15
Skip List después de eliminación:
Nivel 2: Head -> 10 -> 20 -> 30 -> null
Nivel 1: Head -> 10 -> 20 -> 25 -> 30 -> null
Nivel 0: Head -> 10 -> 20 -> 25 -> 30 -> null

==========================================
   FIN DE LA DEMOSTRACIÓN
==========================================
```

## Complejidad Temporal

| Operación | Promedio | Peor Caso |
|-----------|----------|-----------|
| Búsqueda | O(log n) | O(n) |
| Inserción | O(log n) | O(n) |
| Eliminación | O(log n) | O(n) |
| Espacio | O(n) | O(n log n) |

## Ventajas de Skip List

1. **Simplicidad:** Más fácil de implementar y entender que árboles balanceados
2. **Eficiencia:** Rendimiento comparable a árboles balanceados en promedio
3. **Sin rotaciones:** No requiere operaciones complejas de balanceo
4. **Paralelismo:** Fácil de paralelizar ciertas operaciones
5. **Cache-friendly:** Mejor localidad de referencia que árboles

## Comparación con Otras Estructuras

| Estructura | Búsqueda | Inserción | Eliminación | Complejidad |
|------------|----------|-----------|------------|-------------|
| Skip List | O(log n)* | O(log n)* | O(log n)* | Simple |
| AVL Tree | O(log n) | O(log n) | O(log n) | Complejo |
| Red-Black | O(log n) | O(log n) | O(log n) | Moderado |
| Linked List | O(n) | O(1) | O(n) | Muy simple |

*Promedio

## Referencias
- Pugh, W. (1990). "Skip Lists: A Probabilistic Alternative to Balanced Trees"
- Cormen, T. H., et al. "Introduction to Algorithms" - Capítulo sobre Skip Lists
- https://en.wikipedia.org/wiki/Skip_list
