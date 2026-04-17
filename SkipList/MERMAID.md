# Diagramas Mermaid - Skip List en Java

## Diagrama de Clases UML

```mermaid
classDiagram
    class SkipList~T~ {
        -SkipListNode~T~ head
        -int maxLevel
        -double probability
        -Random random
        -int currentLevel
        +SkipList(int maxLevel, double probability)
        -int randomLevel()
        +boolean search(T key)
        +void insert(T key)
        +void delete(T key)
        +void print()
    }
    
    class SkipListNode~T~ {
        -T value
        -SkipListNode~T~[] next
        +SkipListNode(T value, int level)
    }
    
    SkipList~T~ --> SkipListNode~T~ : contains
    SkipList~T~ --> Paciente : uses
    
    class Paciente {
        -int id
        -String nombre
        -int prioridad
        -String especialidad
        +Paciente(int id, String nombre, int prioridad, String especialidad)
        +int compareTo(Paciente other)
        +String toString()
    }
```

## Estructura de Skip List

```mermaid
graph TB
    subgraph "Nivel 3"
        H3[Head] --> N3_1[20] --> N3_2[null]
    end
    
    subgraph "Nivel 2"
        H2[Head] --> N2_1[10] --> N2_2[20] --> N2_3[30] --> N2_4[null]
    end
    
    subgraph "Nivel 1"
        H1[Head] --> N1_1[10] --> N1_2[15] --> N1_3[20] --> N1_4[25] --> N1_5[30] --> N1_6[null]
    end
    
    subgraph "Nivel 0"
        H0[Head] --> N0_1[10] --> N0_2[15] --> N0_3[20] --> N0_4[25] --> N0_5[30] --> N0_6[null]
    end
    
    H3 -.-> H2
    H2 -.-> H1
    H1 -.-> H0
    
    style H3 fill:#e1f5ff
    style H2 fill:#e1f5ff
    style H1 fill:#e1f5ff
    style H0 fill:#e1f5ff
```

## Diagrama de Flujo - Búsqueda (Search)

```mermaid
flowchart TD
    A[Inicio: Búsqueda de key] --> B[Iniciar en nivel más alto de head]
    B --> C{¿Siguiente nodo existe y es menor que key?}
    C -->|Sí| D[Avanzar al siguiente nodo]
    C -->|No| E{¿Estamos en nivel 0?}
    D --> C
    E -->|No| F[Bajar un nivel]
    F --> C
    E -->|Sí| G{¿Nodo actual es igual a key?}
    G -->|Sí| H[Retornar true: Encontrado]
    G -->|No| I[Retornar false: No encontrado]
    H --> J[Fin]
    I --> J
```

## Diagrama de Flujo - Inserción (Insert)

```mermaid
flowchart TD
    A[Inicio: Insertar key] --> B[Buscar posición de inserción]
    B --> C[Generar nivel aleatorio para nuevo nodo]
    C --> D[Crear nuevo nodo con key y nivel]
    D --> E[Actualizar punteros en todos los niveles]
    E --> F{¿Nivel del nuevo nodo > nivel actual?}
    F -->|Sí| G[Actualizar head si es necesario]
    F -->|No| H[Fin]
    G --> H
```

## Diagrama de Secuencia - Inserción

```mermaid
sequenceDiagram
    participant User
    participant SkipList
    participant Head
    participant Nodes
    
    User->>SkipList: Insert(20)
    SkipList->>Head: Buscar posición desde nivel más alto
    Head->>Nodes: Avanzar mientras siguiente < 20
    Nodes-->>Head: Posición encontrada
    Head->>SkipList: Determinar nivel aleatorio
    SkipList->>SkipList: Crear nodo nivel 2
    SkipList->>Nodes: Actualizar punteros nivel 0
    SkipList->>Nodes: Actualizar punteros nivel 1
    SkipList->>Nodes: Actualizar punteros nivel 2
    SkipList-->>User: Inserción completada
```

## Diagrama de Flujo - Eliminación (Delete)

```mermaid
flowchart TD
    A[Inicio: Eliminar key] --> B[Buscar nodo a eliminar]
    B --> C{¿Nodo encontrado?}
    C -->|No| D[Retornar: No encontrado]
    C -->|Sí| E[Para cada nivel donde aparece]
    E --> F[Actualizar punteros para saltar el nodo]
    F --> G{¿Más niveles?}
    G -->|Sí| E
    G -->|No| H[Eliminar nodo]
    H --> I[Fin]
    D --> I
```

## Diagrama de Secuencia - Búsqueda

```mermaid
sequenceDiagram
    participant User
    participant SkipList
    participant Level3
    participant Level2
    participant Level1
    participant Level0
    
    User->>SkipList: Search(25)
    SkipList->>Level3: Iniciar búsqueda nivel 3
    Level3-->>SkipList: 20 < 25, avanzar
    Level3-->>SkipList: null, bajar nivel 2
    SkipList->>Level2: Continuar nivel 2
    Level2-->>SkipList: 30 > 25, bajar nivel 1
    SkipList->>Level1: Continuar nivel 1
    Level1-->>SkipList: 20 < 25, avanzar
    Level1-->>SkipList: 30 > 25, bajar nivel 0
    SkipList->>Level0: Continuar nivel 0
    Level0-->>SkipList: 20 < 25, avanzar
    Level0-->>SkipList: 25 encontrado
    SkipList-->>User: Retornar true
```

## Evolución de Skip List durante Inserciones

```mermaid
graph LR
    subgraph "Estado Inicial"
        I0[Head → null]
    end
    
    subgraph "Insertar 10"
        S1[Head → 10 → null]
    end
    
    subgraph "Insertar 20"
        S2[Head → 10 → 20 → null<br/>Head → 20 → null]
    end
    
    subgraph "Insertar 30"
        S3[Head → 10 → 20 → 30 → null<br/>Head → 20 → 30 → null<br/>Head → 30 → null]
    end
    
    I0 --> S1
    S1 --> S2
    S2 --> S3
    
    style I0 fill:#fff4e6
    style S1 fill:#e6f7ff
    style S2 fill:#e6ffe6
    style S3 fill:#ffe6e6
```

## Comparación de Complejidades

```mermaid
graph TB
    subgraph "Skip List"
        SL1[Búsqueda: O log n]
        SL2[Inserción: O log n]
        SL3[Eliminación: O log n]
    end
    
    subgraph "Linked List"
        LL1[Búsqueda: O n]
        LL2[Inserción: O 1]
        LL3[Eliminación: O n]
    end
    
    subgraph "AVL Tree"
        AVL1[Búsqueda: O log n]
        AVL2[Inserción: O log n]
        AVL3[Eliminación: O log n]
    end
    
    style SL1 fill:#90EE90
    style SL2 fill:#90EE90
    style SL3 fill:#90EE90
    style LL1 fill:#FFB6C1
    style AVL1 fill:#87CEEB
```

## Analogía del Edificio

```mermaid
graph TB
    subgraph "Piso 3 (Express)"
        P3[Head] --> R3[20] --> E3[null]
    end
    
    subgraph "Piso 2 (Fast)"
        P2[Head] --> R2[10] --> R2_2[20] --> R2_3[30] --> E2[null]
    end
    
    subgraph "Piso 1 (Local)"
        P1[Head] --> R1[10] --> R1_2[15] --> R1_3[20] --> R1_4[25] --> R1_5[30] --> E1[null]
    end
    
    subgraph "Piso 0 (Base)"
        P0[Head] --> R0[10] --> R0_2[15] --> R0_3[20] --> R0_4[25] --> R0_5[30] --> E0[null]
    end
    
    P3 -.->|Escalera| P2
    P2 -.->|Escalera| P1
    P1 -.->|Escalera| P0
    
    style P3 fill:#FFD700
    style P2 fill:#C0C0C0
    style P1 fill:#CD7F32
    style P0 fill:#808080
```
