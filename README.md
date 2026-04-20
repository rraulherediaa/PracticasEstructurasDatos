# Lista Simple con Recursividad - Playlist de Canciones

**Materia:** Estructura de Datos I  
**Implementación:** Lista Simple Enlazada con Recursividad en Java  
**Aplicación:** Playlist de Música

## Descripción
Una lista simple enlazada es una estructura de datos lineal donde cada elemento (nodo) contiene un dato y una referencia al siguiente nodo. En esta implementación, utilizamos **recursividad** para las operaciones de visualización e inserción al final, demostrando cómo las técnicas recursivas pueden simplificar el código y hacerlo más elegante.

## ¿Qué es una Lista Simple?
Una lista simple enlazada es una colección de nodos donde:
- Cada nodo contiene datos y un puntero al siguiente nodo
- El primer nodo se llama "cabeza" (head)
- El último nodo apunta a null
- Solo se puede recorrer en una dirección (hacia adelante)

### Características principales:
- **Acceso secuencial:** O(n) para acceder a un elemento específico
- **Inserción eficiente:** O(1) al inicio, O(n) al final
- **Eliminación eficiente:** O(n) en general
- **Memoria dinámica:** Crece según sea necesario
- **Recursividad:** Simplifica operaciones complejas

### Analogía
Imagina una cadena de personas donde cada persona sostiene la mano de la siguiente:
- **Primera persona:** Cabeza de la lista
- **Cada persona:** Un nodo con información
- **Mano sostenida:** Puntero al siguiente
- **Última persona:** No sostiene a nadie (apunta a null)

Para encontrar a alguien, debes seguir la cadena desde el principio.

### Aplicación en Playlist
En una playlist de música, la lista simple permite:
- **Canciones ordenadas:** En orden de adición a la playlist
- **Recursivo:** Mostrar playlist de forma elegante
- **Inserción al final:** Agregar nuevas canciones al final
- **Recorrido inverso:** Mostrar canciones de la más reciente a la más antigua

### Otras Aplicaciones
- Sistemas de colas de impresión
- Gestión de historiales de navegación
- Implementación de pilas y colas
- Gestión de listas de tareas

## Requisitos Previos
- **Java JDK 11 o superior**
- **IDE:** IntelliJ IDEA, Eclipse, VS Code, o cualquier editor compatible con Java
- **Sistema Operativo:** Windows, Linux, macOS

## Estructura del Proyecto
```
Practicas/
├── README.md                    # Documentación completa
├── MERMAID.md                   # Diagramas Mermaid
└── ListaSimpleRecursiva.java    # Implementación con recursividad
```

## Uso

### Compilación
```bash
javac ListaSimpleRecursiva.java
```

### Ejecución
```bash
java ListaSimpleRecursiva
```

## Estructura de Datos con POO

### Clases Principales

#### 1. NodoCancion
Nodo de la lista que representa una canción:
- **titulo:** Título de la canción
- **artista:** Artista o banda
- **duracion:** Duración en segundos
- **siguiente:** Referencia a la siguiente canción en la lista

#### 2. Playlist
Clase principal que implementa la lista:
- **cabeza:** Primera canción de la playlist
- **métodos recursivos:** Para mostrar e insertar

### Operaciones Principales con Recursividad

#### 1. Mostrar Canciones (Recursivo - Orden Normal)
- Caso base: Si el nodo es null, termina
- Caso recursivo: Imprime el nodo actual y llama recursivamente al siguiente
- **Complejidad:** O(n)
- **En playlist:** Muestra canciones de la primera a la última

#### 2. Mostrar Canciones (Recursivo - Orden Inverso)
- Caso base: Si el nodo es null, termina
- Caso recursivo: Llama recursivamente al siguiente, luego imprime al regresar
- **Complejidad:** O(n)
- **En playlist:** Muestra canciones de la última a la primera

#### 3. Insertar al Final (Recursivo)
- Caso base: Si el nodo es null, crea nuevo nodo
- Caso recursivo: Avanza al siguiente nodo hasta encontrar null
- **Complejidad:** O(n)
- **En playlist:** Agrega nueva canción al final de la playlist

## Casos de Uso - Playlist de Canciones

### 1. Mostrar Playlist (Recursivo)
```java
Playlist playlist = new Playlist();
playlist.agregarCancionRecursivo("Bohemian Rhapsody", "Queen", 354);
playlist.agregarCancionRecursivo("Stairway to Heaven", "Led Zeppelin", 482);

playlist.mostrarCancionesRecursivo();
// Salida:
// 🎵 Playlist (Orden Normal):
//    1. "Bohemian Rhapsody" - Queen (5:54)
//    2. "Stairway to Heaven" - Led Zeppelin (8:02)
```

### 2. Mostrar Playlist en Orden Inverso
```java
playlist.mostrarCancionesInversoRecursivo();
// Salida:
// 🎵 Playlist (Orden Inverso):
//    2. "Stairway to Heaven" - Led Zeppelin (8:02)
//    1. "Bohemian Rhapsody" - Queen (5:54)
```

### 3. Insertar Canción al Final (Recursivo)
```java
playlist.agregarCancionRecursivo("Hotel California", "Eagles", 390);
// La canción se agrega al final de la playlist
```

## Ejemplo de Ejecución

```
========================================
  LISTAS SIMPLES CON RECURSIVIDAD
  Playlist de Canciones
========================================

--- AGREGANDO CANCIONES AL FINAL (RECURSIVO) ---
🎶 Agregando: "Bohemian Rhapsody" de Queen
🎶 Agregando: "Stairway to Heaven" de Led Zeppelin
🎶 Agregando: "Hotel California" de Eagles
🎶 Agregando: "Sweet Child O' Mine" de Guns N' Roses
🎶 Agregando: "Smells Like Teen Spirit" de Nirvana

🎵 Playlist (Orden Normal):
   1. "Bohemian Rhapsody" - Queen (5:54)
   2. "Stairway to Heaven" - Led Zeppelin (8:02)
   3. "Hotel California" - Eagles (6:30)
   4. "Sweet Child O' Mine" - Guns N' Roses (5:56)
   5. "Smells Like Teen Spirit" - Nirvana (5:01)
   Fin de la playlist

🎵 Playlist (Iterativo):
   1. "Bohemian Rhapsody" - Queen (5:54)
   2. "Stairway to Heaven" - Led Zeppelin (8:02)
   3. "Hotel California" - Eagles (6:30)
   4. "Sweet Child O' Mine" - Guns N' Roses (5:56)
   5. "Smells Like Teen Spirit" - Nirvana (5:01)
   Fin de la playlist

--- MOSTRAR EN ORDEN INVERSO (RECURSIVO) ---
🎵 Playlist (Orden Inverso):
   5. "Smells Like Teen Spirit" - Nirvana (5:01)
   4. "Sweet Child O' Mine" - Guns N' Roses (5:56)
   3. "Hotel California" - Eagles (6:30)
   2. "Stairway to Heaven" - Led Zeppelin (8:02)
   1. "Bohemian Rhapsody" - Queen (5:54)

========================================
  FIN DE LA DEMOSTRACIÓN
========================================
```

## Complejidad Temporal

| Operación | Recursivo | Iterativo | Espacio |
|-----------|-----------|-----------|---------|
| Mostrar | O(n) | O(n) | O(n) pila |
| Insertar Final | O(n) | O(n) | O(n) pila |
| Insertar Inicio | O(1) | O(1) | O(1) |
| Eliminar | O(n) | O(n) | O(n) |

## Ventajas de Recursividad

1. **Elegancia:** Código más limpio y fácil de entender
2. **Simplicidad:** Menos código para operaciones complejas
3. **Natural:** Refleja la estructura de datos recursivamente
4. **Mantenibilidad:** Más fácil de modificar y extender

## Desventajas de Recursividad

1. **Memoria:** Usa más memoria por la pila de llamadas
2. **Rendimiento:** Puede ser más lento que iterativo
3. **Stack Overflow:** Riesgo con listas muy largas
4. **Depuración:** Más difícil de depurar

## Comparación: Recursivo vs Iterativo

| Aspecto | Recursivo | Iterativo |
|---------|-----------|-----------|
| Código | Más elegante | Más explícito |
| Memoria | O(n) pila | O(1) |
| Rendimiento | Más lento | Más rápido |
| Legibilidad | Alta | Media |
| Mantenimiento | Fácil | Medio |

## Referencias
- Cormen, T. H., et al. "Introduction to Algorithms" - Capítulo sobre Listas Enlazadas
- Sedgewick, R., Wayne, K. "Algorithms" - Sección sobre Recursión
- https://en.wikipedia.org/wiki/Linked_list
- https://en.wikipedia.org/wiki/Recursion_(computer_science)

## Créditos
- **Implementación:** Java con Programación Orientada a Objetos
- **Técnica:** Recursividad para operaciones de lista
- **Caso de estudio:** Playlist de Canciones
- **Materia:** Estructura de Datos I
