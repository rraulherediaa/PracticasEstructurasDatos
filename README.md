# Grafos - Red de Conexiones de Ciudades

**Materia:** Estructura de Datos I  
**Implementación:** Grafo No Dirigido Ponderado en Java  
**Aplicación:** Red de Conexiones entre Ciudades

## Descripción

Un **grafo** es una estructura de datos no lineal compuesta por un conjunto de **vértices** (nodos) y **aristas** (conexiones) que relacionan estos vértices. En esta implementación, utilizamos un **grafo no dirigido ponderado** para modelar una red de conexiones entre ciudades, donde cada arista tiene un peso que representa la distancia en kilómetros.

## ¿Qué es un Grafo?

Un grafo G = (V, E) consiste en:
- **V:** Conjunto de vértices (nodos)
- **E:** Conjunto de aristas (conexiones entre pares de vértices)

### Tipos de Grafos:

- **Dirigido:** Las aristas tienen dirección (una vía)
- **No Dirigido:** Las aristas son bidireccionales (ida y vuelta)
- **Ponderado:** Las aristas tienen un peso o costo asociado
- **No ponderado:** Todas las aristas tienen el mismo valor

### Características principales:

- **Representación:** Matriz de adyacencia o lista de adyacencia
- **Conectividad:** Determina si existe camino entre vértices
- **Ciclos:** Camino que empieza y termina en el mismo vértice
- **Caminos:** Secuencia de vértices conectados por aristas

### Analogía

Imagina un **mapa de carreteras** entre ciudades:

- **Ciudades:** Vértices del grafo
- **Carreteras:** Aristas que conectan ciudades
- **Distancia:** Peso de cada arista (kilómetros)
- **Ruta:** Camino desde una ciudad a otra
- **Conexión directa:** Arista entre dos ciudades adyacentes

### Aplicación en Red de Ciudades

En una red de conexiones entre ciudades, el grafo permite:

- **Encontrar rutas:** Determinar caminos entre dos ciudades
- **Calcular distancias:** Sumar pesos de aristas en una ruta
- **Verificar conectividad:** Saber si todas las ciudades están conectadas
- **Optimizar viajes:** Encontrar la ruta más corta

### Otras Aplicaciones

- **Redes sociales:** Amistades y conexiones entre personas
- **Internet:** Enrutamiento de paquetes entre routers
- **GPS/Navegación:** Cálculo de rutas óptimas
- **Circuitos eléctricos:** Conexiones entre componentes
- **Organigramas:** Jerarquías empresariales

## Requisitos Previos

- **Java JDK 11 o superior**
- **IDE:** IntelliJ IDEA, Eclipse, VS Code, o cualquier editor compatible con Java
- **Sistema Operativo:** Windows, Linux, macOS

## Estructura del Proyecto

```
Practicas/
├── README.md                    # Documentación completa
├── MERMAID.md                   # Diagramas Mermaid
└── Grafos.java                  # Implementación de grafo
```

## Uso

### Compilación
```bash
javac Grafos.java
```

### Ejecución
```bash
java Grafos
```

## Estructura de Datos con POO

### Clases Principales

#### 1. Ciudad (Vértice)
Clase que representa una ciudad:
- **nombre:** Nombre de la ciudad
- **codigo:** Código identificador único

#### 2. Arista
Clase que representa una conexión:
- **origen:** Ciudad de origen
- **destino:** Ciudad de destino
- **peso:** Distancia en kilómetros

#### 3. Grafo
Clase principal que implementa el grafo:
- **listaAdyacencia:** Mapa de ciudades y sus conexiones
- **dirigido:** Tipo de grafo (dirigido/no dirigido)

### Operaciones Principales

#### 1. Agregar Vértice (Ciudad)
- Añade una nueva ciudad al grafo
- **Complejidad:** O(1)

#### 2. Agregar Arista (Conexión)
- Crea una carretera entre dos ciudades con distancia
- En grafo no dirigido, crea conexión bidireccional
- **Complejidad:** O(1)

#### 3. Recorrido DFS (Profundidad)
- Explora caminos hasta el final antes de retroceder
- Usa pila (recursivo o iterativo)
- **Complejidad:** O(V + E)

#### 4. Recorrido BFS (Anchura)
- Explora nivel por nivel desde el origen
- Usa cola
- **Complejidad:** O(V + E)

#### 5. Existe Camino
- Verifica si hay conexión directa o indirecta entre ciudades
- **Complejidad:** O(V + E)

## Casos de Uso - Red de Ciudades

### 1. Agregar Ciudades
```java
Grafo red = new Grafo(false); // No dirigido
red.agregarVertice("Bogotá", "BOG");
red.agregarVertice("Medellín", "MED");
red.agregarVertice("Cali", "CAL");
```

### 2. Establecer Conexiones
```java
red.agregarArista("BOG", "MED", 420); // Bogotá-Medellín: 420 km
red.agregarArista("MED", "CAL", 420); // Medellín-Cali: 420 km
red.agregarArista("BOG", "CAL", 460); // Bogotá-Cali: 460 km
```

### 3. Recorrido DFS
```java
red.dfs("BOG");
// Salida: BOG → MED → CAL
// O: BOG → CAL → MED (depende del orden)
```

### 4. Recorrido BFS
```java
red.bfs("BOG");
// Salida: BOG → MED → CAL
// Explora primero los vecinos directos
```

### 5. Verificar Conectividad
```java
boolean conectado = red.existeCamino("BOG", "CAL");
// Salida: true (existe conexión)
```

## Ejemplo de Ejecución

```
========================================
  GRAFOS - RED DE CONEXIONES
  Sistema de Ciudades
========================================

--- AGREGANDO CIUDADES ---
🏙️  Ciudad: Bogotá (BOG)
🏙️  Ciudad: Medellín (MED)
🏙️  Ciudad: Cali (CAL)
🏙️  Ciudad: Barranquilla (BAR)
🏙️  Ciudad: Cartagena (CTG)

--- ESTABLECIENDO CONEXIONES ---
🛣️  BOG ↔ MED: 420 km
🛣️  MED ↔ CAL: 420 km
🛣️  BOG ↔ CAL: 460 km
🛣️  BAR ↔ CTG: 120 km
🛣️  BOG ↔ BAR: 1050 km

--- LISTA DE ADYACENCIA ---
📍 BOG (Bogotá):
   → MED (420 km) → CAL (460 km) → BAR (1050 km)

📍 MED (Medellín):
   → BOG (420 km) → CAL (420 km)

📍 CAL (Cali):
   → MED (420 km) → BOG (460 km)

📍 BAR (Barranquilla):
   → CTG (120 km) → BOG (1050 km)

📍 CTG (Cartagena):
   → BAR (120 km)

--- RECORRIDO DFS (Bogotá) ---
🔍 Orden de visita (Profundidad):
   BOG → MED → CAL → BAR → CTG

--- RECORRIDO BFS (Bogotá) ---
🔍 Orden de visita (Anchura):
   BOG → MED → CAL → BAR → CTG

--- VERIFICACIÓN DE RUTAS ---
✓ Existe camino de BOG a CTG: true
  Ruta: BOG → BAR → CTG (1170 km total)

✗ Existe camino de CTG a MED: false
  No hay conexión disponible

========================================
  FIN DE LA DEMOSTRACIÓN
========================================
```

## Complejidad Temporal

| Operación | Complejidad |
|-----------|-------------|
| Agregar vértice | O(1) |
| Agregar arista | O(1) |
| DFS | O(V + E) |
| BFS | O(V + E) |
| Existe camino | O(V + E) |
| Eliminar vértice | O(V + E) |
| Eliminar arista | O(E) |

Donde V = vértices, E = aristas

## Representaciones de Grafos

### Lista de Adyacencia
- **Ventaja:** Eficiente en espacio para grafos dispersos
- **Uso:** Grafos con pocas conexiones
- **Memoria:** O(V + E)

### Matriz de Adyacencia
- **Ventaja:** Verificación de aristas en O(1)
- **Uso:** Grafos densos
- **Memoria:** O(V²)

## Ventajas de los Grafos

1. **Versatilidad:** Modelan relaciones complejas
2. **Eficiencia:** Algoritmos optimizados para búsquedas
3. **Intuitivos:** Representan problemas del mundo real
4. **Escalables:** Manejan grandes redes

## Desventajas de los Grafos

1. **Complejidad:** Algoritmos pueden ser difíciles de implementar
2. **Memoria:** Puede consumir mucho espacio
3. **Recorrido:** Sin un nodo inicial definido
4. **Ciclos:** Requieren manejo especial

## Comparación: Grafos vs Otras Estructuras

| Aspecto | Grafo | Lista | Árbol |
|---------|-------|-------|-------|
| Relaciones | Complejas | Lineales | Jerárquicas |
| Búsqueda | O(V+E) | O(n) | O(log n) |
| Flexibilidad | Alta | Baja | Media |
| Uso típico | Redes | Secuencias | Jerarquías |

## Algoritmos Importantes en Grafos

### Búsqueda
- **DFS:** Depth First Search (Profundidad)
- **BFS:** Breadth First Search (Anchura)

### Caminos
- **Dijkstra:** Camino más corto desde un origen
- **Floyd-Warshall:** Caminos más cortos entre todos los pares
- **Bellman-Ford:** Caminos más cortos con pesos negativos

### Árboles
- **Prim:** Árbol de expansión mínima
- **Kruskal:** Árbol de expansión mínima

## Referencias

- Cormen, T. H., et al. "Introduction to Algorithms" - Capítulo sobre Grafos
- Sedgewick, R., Wayne, K. "Algorithms" - Sección sobre Grafos
- https://en.wikipedia.org/wiki/Graph_(abstract_data_type)
- https://en.wikipedia.org/wiki/Graph_traversal

## Créditos

- **Implementación:** Java con Programación Orientada a Objetos
- **Técnica:** Lista de Adyacencia para Grafo No Dirigido Ponderado
- **Caso de estudio:** Red de Conexiones entre Ciudades
- **Materia:** Estructura de Datos I
