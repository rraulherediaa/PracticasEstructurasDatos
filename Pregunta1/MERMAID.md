# MERMAID - Diagramas de los Ejemplos

**Examen Primer Parcial:** Implementación de Pilas con POO en C# .NET

Este archivo contiene todos los diagramas Mermaid de los ejemplos (casos de uso) implementados en Main.cs.

---

## Diagrama - Caso 1: Pila de Enteros

```mermaid
sequenceDiagram
    participant Usuario
    participant Pila as Pila<int>
    Usuario->>Pila: Push(10)
    Usuario->>Pila: Push(20)
    Usuario->>Pila: Push(30)
    Usuario->>Pila: MostrarPila()
    Usuario->>Pila: Pop()
    Usuario->>Pila: MostrarPila()
    Usuario->>Pila: Peek()
```

---

## Diagrama - Caso 2: Pila de Strings (Documentos)

```mermaid
sequenceDiagram
    participant Usuario
    participant Pila as Pila<string>
    Usuario->>Pila: Push("Documento 1")
    Usuario->>Pila: Push("Documento 2")
    Usuario->>Pila: Push("Documento 3")
    Usuario->>Pila: MostrarPila()
    Usuario->>Pila: Pop()
    Usuario->>Pila: Pop()
    Usuario->>Pila: MostrarPila()
```

---

## Diagrama - Caso 3: Pila de Objetos Personalizados (Personas)

```mermaid
sequenceDiagram
    participant Usuario
    participant Pila as Pila<Persona>
    Usuario->>Pila: Push(Persona("Juan", 25))
    Usuario->>Pila: Push(Persona("María", 30))
    Usuario->>Pila: Push(Persona("Carlos", 28))
    Usuario->>Pila: MostrarPila()
    Usuario->>Pila: Peek()
    Usuario->>Pila: Pop()
    Usuario->>Pila: MostrarPila()
```

---

## Diagrama - Caso 4: Manejo de Pila Vacía

```mermaid
flowchart TD
    A[Crear pila vacía] --> B[EstaVacia?]
    B -->|true| C[Intentar Pop]
    C --> D[Retorna default + mensaje error]
    B --> E[Intentar Peek]
    E --> F[Retorna default + mensaje error]
```

---

## Diagrama - Caso 5: Pila con Muchos Elementos

```mermaid
graph LR
    A[Loop i=1 to 10] --> B[Push i*10]
    B --> A
    A --> C[MostrarPila<br/>10 elementos]
    C --> D[Loop i=1 to 5]
    D --> E[Pop]
    E --> D
    D --> F[MostrarPila<br/>5 elementos]
```

---

## Diagrama - Caso 6: Limpiar Pila

```mermaid
flowchart TD
    A[Crear pila] --> B[Push A]
    B --> C[Push B]
    C --> D[Push C]
    D --> E[MostrarPila<br/>3 elementos]
    E --> F[Limpiar]
    F --> G[MostrarPila<br/>vacía]
```

---

**Autor:** Raul Heredia
