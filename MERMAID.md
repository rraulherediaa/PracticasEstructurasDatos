# Diagramas Mermaid - Grafos

## 1. Estructura del Grafo - Red de Ciudades

```mermaid
graph LR
    subgraph "Grafo No Dirigido Ponderado"
        BOG[Bogotá BOG]
        MED[Medellín MED]
        CAL[Cali CAL]
        BAR[Barranquilla BAR]
        CTG[Cartagena CTG]
        
        BOG -- 420 km --- MED
        MED -- 420 km --- CAL
        BOG -- 460 km --- CAL
        BAR -- 120 km --- CTG
        BOG -- 1050 km --- BAR
    end
    
    style BOG fill:#ff9999
    style MED fill:#99ff99
    style CAL fill:#9999ff
    style BAR fill:#ffff99
    style CTG fill:#ff99ff
```

## 2. Lista de Adyacencia

```mermaid
graph TB
    subgraph "Representación en Lista de Adyacencia"
        A[BOG Bogotá] --> B[MED 420km]
        A --> C[CAL 460km]
        A --> D[BAR 1050km]
        
        E[MED Medellín] --> F[BOG 420km]
        E --> G[CAL 420km]
        
        H[CAL Cali] --> I[MED 420km]
        H --> J[BOG 460km]
        
        K[BAR Barranquilla] --> L[CTG 120km]
        K --> M[BOG 1050km]
        
        N[CTG Cartagena] --> O[BAR 120km]
    end
```

## 3. Recorrido DFS (Depth First Search)

```mermaid
sequenceDiagram
    participant U as Usuario
    participant G as Grafo
    participant V as Vértice Actual
    participant P as Pila
    participant Vst as Visitados
    
    U->>G: dfs("BOG")
    G->>P: push(BOG)
    G->>Vst: marcar BOG
    
    loop Mientras pila no vacía
        P->>V: pop() → BOG
        V->>U: Imprimir BOG
        
        V->>G: obtener vecinos(BOG)
        G->>V: [MED, CAL, BAR]
        
        loop Para cada vecino no visitado
            G->>Vst: ¿MED visitado?
            Vst-->>G: No
            G->>P: push(MED)
            G->>Vst: marcar MED
        end
        
        P->>V: pop() → MED
        V->>U: Imprimir MED
    end
```

## 4. Recorrido BFS (Breadth First Search)

```mermaid
sequenceDiagram
    participant U as Usuario
    participant G as Grafo
    participant Q as Cola
    participant V as Vértice
    participant Vst as Visitados
    
    U->>G: bfs("BOG")
    G->>Q: enqueue(BOG)
    G->>Vst: marcar BOG
    
    loop Mientras cola no vacía
        Q->>V: dequeue() → BOG
        V->>U: Imprimir BOG
        
        V->>G: obtener vecinos(BOG)
        G->>V: [MED, CAL, BAR]
        
        loop Para cada vecino no visitado
            G->>Vst: ¿MED visitado?
            Vst-->>G: No
            G->>Q: enqueue(MED)
            G->>Vst: marcar MED
        end
        
        Q->>V: dequeue() → MED
        V->>U: Imprimir MED
    end
```

## 5. Flujo de Operaciones del Grafo

```mermaid
flowchart TD
    subgraph "Operaciones Grafo"
        Start([Inicio]) --> Op{Operación}
        
        Op -->|Agregar Ciudad| AddV[Validar código único]
        AddV --> AddV2[Crear vértice]
        AddV2 --> AddV3[Agregar a lista adyacencia]
        AddV3 --> End1([Fin])
        
        Op -->|Agregar Carretera| AddE[Validar ciudades existen]
        AddE --> AddE2{Grafo dirigido?}
        AddE2 -->|No| AddE3[Crear arista bidireccional]
        AddE2 -->|Sí| AddE4[Crear arista unidireccional]
        AddE3 --> AddE5[Actualizar listas]
        AddE4 --> AddE5
        AddE5 --> End2([Fin])
        
        Op -->|DFS| DFS1[Inicializar pila y visitados]
        DFS1 --> DFS2[Push vértice inicial]
        DFS2 --> DFS3{Pila vacía?}
        DFS3 -->|No| DFS4[Pop vértice]
        DFS4 --> DFS5{Visitado?}
        DFS5 -->|No| DFS6[Marcar e imprimir]
        DFS6 --> DFS7[Push vecinos no visitados]
        DFS7 --> DFS3
        DFS5 -->|Sí| DFS3
        DFS3 -->|Sí| End3([Fin])
        
        Op -->|BFS| BFS1[Inicializar cola y visitados]
        BFS1 --> BFS2[Enqueue vértice inicial]
        BFS2 --> BFS3{Cola vacía?}
        BFS3 -->|No| BFS4[Dequeue vértice]
        BFS4 --> BFS5{Visitado?}
        BFS5 -->|No| BFS6[Marcar e imprimir]
        BFS6 --> BFS7[Enqueue vecinos no visitados]
        BFS7 --> BFS3
        BFS5 -->|Sí| BFS3
        BFS3 -->|Sí| End4([Fin])
    end
```

## 6. Diagrama de Clases UML

```mermaid
classDiagram
    class Ciudad {
        -String nombre
        -String codigo
        +Ciudad(String, String)
        +getNombre() String
        +getCodigo() String
        +equals(Object) boolean
        +hashCode() int
        +toString() String
    }
    
    class Arista {
        -Ciudad destino
        -int peso
        +Arista(Ciudad, int)
        +getDestino() Ciudad
        +getPeso() int
        +toString() String
    }
    
    class Grafo {
        -Map~String, Ciudad~ ciudades
        -Map~String, List~Arista~~ adyacencia
        -boolean dirigido
        +Grafo(boolean)
        +agregarVertice(String, String) boolean
        +agregarArista(String, String, int) boolean
        +dfs(String) void
        +bfs(String) void
        +existeCamino(String, String) boolean
        +obtenerVecinos(String) List~Arista~
        +mostrarGrafo() void
        +esDirigido() boolean
    }
    
    Grafo --> Ciudad : contiene
    Grafo --> Arista : adyacencia
    Arista --> Ciudad : destino
```

## 7. Comparación: Representaciones de Grafos

```mermaid
graph TB
    subgraph "Matriz de Adyacencia"
        direction TB
        M1[ ]
        M2[BOG]
        M3[MED]
        M4[CAL]
        
        M1 --> M5[BOG]
        M1 --> M6[MED]
        M1 --> M7[CAL]
        
        M2 --> M5 --> M8[0]
        M2 --> M6 --> M9[420]
        M2 --> M7 --> M10[460]
        
        M3 --> M5 --> M11[420]
        M3 --> M6 --> M12[0]
        M3 --> M7 --> M13[420]
        
        M4 --> M5 --> M14[460]
        M4 --> M6 --> M15[420]
        M4 --> M7 --> M16[0]
    end
    
    subgraph "Lista de Adyacencia"
        direction TB
        L1[BOG] --> L2[MED: 420]
        L1 --> L3[CAL: 460]
        
        L4[MED] --> L5[BOG: 420]
        L4 --> L6[CAL: 420]
        
        L7[CAL] --> L8[BOG: 460]
        L7 --> L9[MED: 420]
    end
    
    MatrizNote[O(V²) espacio] --> M1
    ListaNote[O(V+E) espacio] --> L1
```

## 8. Algoritmo de Búsqueda de Camino

```mermaid
flowchart LR
    subgraph "Existe Camino (BOG → CTG)"
        Start([Inicio]) --> Init[Inicializar visitados<br/>Cola con BOG]
        Init --> Loop{Cola vacía?}
        Loop -->|No| Dequeue[Dequeue → ciudad]
        Dequeue --> Check{Ciudad == CTG?}
        Check -->|Sí| ReturnTrue[Retornar true]
        Check -->|No| Mark[Marcar visitado]
        Mark --> Enqueue[Enqueue vecinos<br/>no visitados]
        Enqueue --> Loop
        Loop -->|Sí| ReturnFalse[Retornar false]
        
        ReturnTrue --> End([Fin])
        ReturnFalse --> End
    end
    
    style ReturnTrue fill:#90EE90
    style ReturnFalse fill:#FFB6C1
```

## 9. Tipos de Grafos

```mermaid
graph TB
    subgraph "Grafo Dirigido"
        D1[A] --> D2[B]
        D2 --> D3[C]
        D1 -.-> D3
    end
    
    subgraph "Grafo No Dirigido"
        U1[X] --- U2[Y]
        U2 --- U3[Z]
        U1 --- U3
    end
    
    subgraph "Grafo Ponderado"
        W1[P] --5-- W2[Q]
        W2 --3-- W3[R]
        W1 --7-- W3
    end
```

## 10. Red de Ciudades Ampliada

```mermaid
graph TB
    subgraph "Red Nacional de Transporte"
        BOG((Bogotá))
        MED((Medellín))
        CAL((Cali))
        BAR((Barranquilla))
        CTG((Cartagena))
        BUC((Bucaramanga))
        
        BOG ---|420| MED
        MED ---|420| CAL
        BOG ---|460| CAL
        BOG ---|1050| BAR
        BAR ---|120| CTG
        BOG ---|400| BUC
        BUC ---|550| BAR
    end
    
    style BOG fill:#ff6b6b,stroke:#333,stroke-width:3px
    style MED fill:#4ecdc4,stroke:#333
    style CAL fill:#45b7d1,stroke:#333
    style BAR fill:#f9ca24,stroke:#333
    style CTG fill:#f0932b,stroke:#333
    style BUC fill:#6c5ce7,stroke:#333
```
