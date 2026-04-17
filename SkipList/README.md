# Skip List en Java - Sistema de Hospital

**Materia:** Estructura de Datos I  
**Implementación:** Skip List con POO en Java  
**Aplicación:** Sistema de Triaje Hospitalario

## Descripción
Skip List es una estructura de datos probabilística que permite búsqueda, inserción y eliminación en tiempo logarítmico promedio. Es una alternativa a los árboles balanceados como AVL y Red-Black Trees.

En este proyecto, implementamos una SkipList aplicada a un sistema de hospital para gestionar pacientes según su prioridad de atención en urgencias.

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

### Aplicación en Hospital
En un sistema de triaje hospitalario, la SkipList permite:
- **Prioridad 1 (Urgencia Máxima):** Pacientes críticos en niveles superiores
- **Prioridad 2-3 (Urgente):** Pacientes graves en niveles medios
- **Prioridad 4-5 (Normal):** Pacientes estables en nivel base

Esto permite encontrar rápidamente al paciente más urgente sin recorrer toda la lista.

### Otras Aplicaciones
- Bases de datos distribuidas
- Sistemas de archivos
- Implementaciones de diccionarios ordenados
- Algoritmos de enrutamiento en redes

## Requisitos Previos
- **Java JDK 11 o superior**
- **IDE:** IntelliJ IDEA, Eclipse, VS Code, o cualquier editor compatible con Java
- **Sistema Operativo:** Windows, Linux, macOS

## Estructura del Proyecto
```
SkipList/
├── README.md              # Documentación completa
├── MERMAID.md             # Diagramas Mermaid
├── DiagramasASCII.md      # Diagramas ASCII de casos de hospital
├── SkipList.java          # Implementación de Skip List genérica
├── Paciente.java          # Clase Paciente para sistema hospitalario
└── HospitalSkipListDemo.java  # Demo con casos de uso del hospital
```

## Uso

### Compilación
```bash
# Compilar todos los archivos Java
javac SkipList.java Paciente.java HospitalSkipListDemo.java
```

### Ejecución
```bash
# Ejecutar la demo
java HospitalSkipListDemo
```

## Estructura de Datos con POO

### Clases Principales

#### 1. SkipListNode<T> (clase interna privada)
Nodo de la Skip List que contiene:
- **value:** Dato almacenado (genérico)
- **next:** Arreglo de punteros a los siguientes nodos en cada nivel

#### 2. SkipList<T>
Clase principal genérica que implementa:
- **head:** Nodo cabeza (sentinela) con máximo nivel
- **maxLevel:** Nivel máximo permitido
- **probability:** Probabilidad de promoción de nivel (default 0.5)
- **random:** Generador de números aleatorios
- **currentLevel:** Nivel actual de la SkipList

#### 3. Paciente
Clase que representa un paciente del hospital:
- **id:** Identificador único del paciente
- **nombre:** Nombre del paciente
- **prioridad:** Nivel de urgencia (1-5, donde 1 es urgencia máxima)
- **especialidad:** Especialidad médica requerida
- Implementa Comparable para ordenamiento por prioridad

### Operaciones Principales

#### 1. Búsqueda (Search)
- Comienza en el nivel más alto del nodo head
- Avanza mientras el siguiente nodo sea menor al buscado
- Baja un nivel cuando no puede avanzar más
- Repite hasta encontrar el elemento o llegar al nivel 0
- **En hospital:** Busca paciente por prioridad rápidamente

#### 2. Inserción (Insert)
- Busca la posición correcta para el nuevo elemento
- Determina aleatoriamente el nivel del nuevo nodo
- Actualiza los punteros en todos los niveles correspondientes
- Complejidad promedio: O(log n)
- **En hospital:** Registra nuevo paciente manteniendo orden por prioridad

#### 3. Eliminación (Delete)
- Busca el elemento a eliminar
- Actualiza los punteros en todos los niveles donde aparece
- Libera memoria del nodo
- Complejidad promedio: O(log n)
- **En hospital:** Elimina paciente después de ser atendido

## Casos de Uso - Sistema de Hospital

### 1. Registro de Pacientes en Urgencias
```java
SkipList<Paciente> listaPacientes = new SkipList<>(4, 0.5);

// Registrar pacientes con diferentes prioridades
listaPacientes.insert(new Paciente(101, "Juan Pérez", 3, "Cardiología"));
listaPacientes.insert(new Paciente(102, "María García", 1, "Urgencias")); // Urgencia máxima
listaPacientes.insert(new Paciente(103, "Carlos López", 2, "Traumatología"));
```

### 2. Búsqueda de Paciente Específico
```java
Paciente buscar = new Paciente(103, "Carlos López", 2, "Traumatología");
boolean encontrado = listaPacientes.search(buscar);
if (encontrado) {
    System.out.println("Paciente encontrado en sistema");
}
```

### 3. Atención y Eliminación de Paciente
```java
// Después de atender al paciente
Piente atendido = new Paciente(102, "María García", 1, "Urgencias");
listaPacientes.delete(atendido); // Elimina de la lista de espera
```

### 4. Visualización del Orden de Atención
```java
listaPacientes.print(); // Muestra estructura completa por niveles
// Los pacientes con prioridad 1 estarán al inicio en todos los niveles
```

### 5. Manejo de Nuevas Urgencias
```java
// Llega un paciente crítico
Paciente urgente = new Paciente(108, "Roberto Díaz", 1, "Urgencias");
listaPacientes.insert(urgente); // Se inserta al inicio por prioridad
```

## Ejemplo de Ejecución - Sistema de Hospital

```
==========================================
   SKIP LIST - SISTEMA DE HOSPITAL
   Implementación en Java
==========================================

--- CASO 1: INSERCIÓN DE PACIENTES ---
Insertando pacientes en el sistema de urgencias:

Insertando: P101-Juan(Pri:3) - Cardiología
Insertando: P102-María(Pri:1) - Urgencias
Insertando: P103-Carlos(Pri:2) - Traumatología
Insertando: P104-Ana(Pri:4) - Pediatría
Insertando: P105-Pedro(Pri:5) - Medicina General
Insertando: P106-Laura(Pri:1) - Urgencias
Insertando: P107-Miguel(Pri:2) - Neurología

Skip List después de inserciones:
Level 3: Head -> P102-María(Pri:1) -> null
Level 2: Head -> P102-María(Pri:1) -> P103-Carlos(Pri:2) -> null
Level 1: Head -> P102-María(Pri:1) -> P103-Carlos(Pri:2) -> P101-Juan(Pri:3) -> P104-Ana(Pri:4) -> P105-Pedro(Pri:5) -> null
Level 0: Head -> P102-María(Pri:1) -> P106-Laura(Pri:1) -> P103-Carlos(Pri:2) -> P107-Miguel(Pri:2) -> P101-Juan(Pri:3) -> P104-Ana(Pri:4) -> P105-Pedro(Pri:5) -> null

--- CASO 2: BÚSQUEDA DE PACIENTES ---
Buscando: P103-Carlos(Pri:2)
Resultado: ENCONTRADO

Buscando: P999-Inexistente
Resultado: NO ENCONTRADO

--- CASO 3: ELIMINACIÓN DE PACIENTES ---
Eliminando: P102-María(Pri:1) (Paciente atendido)
Eliminando: P105-Pedro(Pri:5) (Paciente atendido)

Skip List después de eliminaciones:
Level 2: Head -> P103-Carlos(Pri:2) -> null
Level 1: Head -> P103-Carlos(Pri:2) -> P101-Juan(Pri:3) -> P104-Ana(Pri:4) -> null
Level 0: Head -> P106-Laura(Pri:1) -> P103-Carlos(Pri:2) -> P107-Miguel(Pri:2) -> P101-Juan(Pri:3) -> P104-Ana(Pri:4) -> null

--- CASO 4: NUEVOS PACIENTES DE URGENCIA ---
Llegan nuevos pacientes con alta prioridad:

Insertando: P108-Roberto(Pri:1)
Insertando: P109-Carmen(Pri:1)

Skip List con nuevos urgentes:
Level 3: Head -> P108-Roberto(Pri:1) -> null
Level 2: Head -> P108-Roberto(Pri:1) -> null
Level 1: Head -> P108-Roberto(Pri:1) -> P109-Carmen(Pri:1) -> P103-Carlos(Pri:2) -> null
Level 0: Head -> P108-Roberto(Pri:1) -> P109-Carmen(Pri:1) -> P106-Laura(Pri:1) -> P103-Carlos(Pri:2) -> P107-Miguel(Pri:2) -> P101-Juan(Pri:3) -> P104-Ana(Pri:4) -> null

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
| Linked List | O(n) | O(1)* | O(n) | Muy simple |

*Promedio (en Linked List, inserción es O(1) solo si se conoce la posición)

## Diagramas Detallados
Para ver diagramas ASCII detallados de cada caso de uso del hospital, consulta el archivo:
- **[DiagramasASCII.md](DiagramasASCII.md)** - Contiene diagramas visuales de:
  - Caso 1: Inserción de pacientes
  - Caso 2: Búsqueda de pacientes
  - Caso 3: Eliminación de pacientes
  - Caso 4: Inserción de nuevos urgentes
  - Caso 5: Listado ordenado por prioridad
  - Analogía visual del sistema de triaje

## Referencias
- Pugh, W. (1990). "Skip Lists: A Probabilistic Alternative to Balanced Trees"
- Cormen, T. H., et al. "Introduction to Algorithms" - Capítulo sobre Skip Lists
- https://en.wikipedia.org/wiki/Skip_list

## Créditos
- **Implementación:** Java con Programación Orientada a Objetos
- **Caso de estudio:** Sistema de Triaje Hospitalario
- **Materia:** Estructura de Datos I
