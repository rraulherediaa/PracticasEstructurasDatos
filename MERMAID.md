# MERMAID - Diagramas de los Ejemplos

**Microevaluación 3:** Implementación de Colas con POO en Java

Este archivo contiene todos los diagramas Mermaid de los ejemplos (casos de uso) implementados en Main.java.

---

## Diagrama - Caso 1: Cola de Enteros

```mermaid
sequenceDiagram
    participant Usuario
    participant Cola as Cola<Integer>
    Usuario->>Cola: enqueue(10)
    Usuario->>Cola: enqueue(20)
    Usuario->>Cola: enqueue(30)
    Usuario->>Cola: mostrarCola()
    Usuario->>Cola: dequeue()
    Usuario->>Cola: mostrarCola()
    Usuario->>Cola: peek()
```

---

## Diagrama - Caso 2: Cola de Strings (Clientes)

```mermaid
sequenceDiagram
    participant Cliente
    participant Cola as Cola<String>
    Cliente->>Cola: enqueue("Cliente 1")
    Cliente->>Cola: enqueue("Cliente 2")
    Cliente->>Cola: enqueue("Cliente 3")
    Cliente->>Cola: mostrarCola()
    Cliente->>Cola: dequeue()
    Cliente->>Cola: dequeue()
    Cliente->>Cola: mostrarCola()
```

---

## Diagrama - Caso 3: Cola de Objetos Personalizados (Personas)

```mermaid
sequenceDiagram
    participant Usuario
    participant Cola as Cola<Persona>
    Usuario->>Cola: enqueue(Persona("Juan", 25))
    Usuario->>Cola: enqueue(Persona("María", 30))
    Usuario->>Cola: enqueue(Persona("Carlos", 28))
    Usuario->>Cola: mostrarCola()
    Usuario->>Cola: peek()
    Usuario->>Cola: dequeue()
    Usuario->>Cola: mostrarCola()
```

---

## Diagrama - Caso 4: Manejo de Cola Vacía

```mermaid
flowchart TD
    A[Crear cola vacía] --> B[estaVacia?]
    B -->|true| C[Intentar dequeue]
    C --> D[Retorna null + mensaje error]
    B --> E[Intentar peek]
    E --> F[Retorna null + mensaje error]
```

---

## Diagrama - Caso 5: Cola con Muchos Elementos

```mermaid
graph LR
    A[Loop i=1 to 10] --> B[enqueue i*10]
    B --> A
    A --> C[mostrarCola<br/>10 elementos]
    C --> D[Loop i=1 to 5]
    D --> E[dequeue]
    E --> D
    D --> F[mostrarCola<br/>5 elementos]
```

---

## Diagrama - Caso 6: Limpiar Cola

```mermaid
flowchart TD
    A[Crear cola] --> B[enqueue A]
    B --> C[enqueue B]
    C --> D[enqueue C]
    D --> E[mostrarCola<br/>3 elementos]
    E --> F[limpiar]
    F --> G[mostrarCola<br/>vacía]
```

---

**Autor:** Raul Heredia
