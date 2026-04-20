# Diagramas Mermaid - Lista Simple con Recursividad

## Diagrama de Clases UML

```mermaid
classDiagram
    class Playlist {
        -NodoCancion cabeza
        +Playlist()
        +void mostrarCancionesRecursivo()
        +void mostrarCancionesInversoRecursivo()
        +void agregarCancionRecursivo(String titulo, String artista, int duracion)
        -void mostrarCancionesRecursivoAux(NodoCancion actual, int indice)
        -void mostrarCancionesInversoRecursivoAux(NodoCancion actual, int indice)
        -NodoCancion agregarCancionRecursivoAux(NodoCancion actual, String titulo, String artista, int duracion)
    }
    
    class NodoCancion {
        -String titulo
        -String artista
        -int duracion
        -NodoCancion siguiente
        +NodoCancion(String titulo, String artista, int duracion)
        +String toString()
    }
    
    Playlist --> NodoCancion : contains
```

## Estructura de Lista Simple

```mermaid
graph LR
    A[Cabeza] --> B[Canción 1]
    B --> C[Canción 2]
    C --> D[Canción 3]
    D --> E[null]
    
    style A fill:#e1f5ff
    style B fill:#fff4e6
    style C fill:#fff4e6
    style D fill:#fff4e6
    style E fill:#f0f0f0
```

## Diagrama de Flujo - Mostrar Recursivo (Orden Normal)

```mermaid
flowchart TD
    A[Inicio: mostrarCancionesRecursivo] --> B[Llamar a mostrarCancionesRecursivoAux con cabeza]
    B --> C{¿actual == null?}
    C -->|Sí| D[Imprimir Fin de la playlist]
    C -->|No| E[Imprimir canción actual con índice]
    E --> F[Llamada recursiva: mostrarCancionesRecursivoAux actual.siguiente, indice+1]
    F --> C
    D --> G[Fin]
```

## Diagrama de Flujo - Mostrar Recursivo (Orden Inverso)

```mermaid
flowchart TD
    A[Inicio: mostrarCancionesInversoRecursivo] --> B[Obtener total de canciones]
    B --> C[Llamar a mostrarCancionesInversoRecursivoAux con cabeza y total]
    C --> D{¿actual == null?}
    D -->|Sí| E[Retornar sin imprimir]
    D -->|No| F[Llamada recursiva: mostrarCancionesInversoRecursivoAux actual.siguiente, indice-1]
    F --> G[Imprimir canción actual con índice]
    G --> D
    E --> H[Fin]
```

## Diagrama de Secuencia - Mostrar Recursivo

```mermaid
sequenceDiagram
    participant Main
    participant Playlist
    participant Aux1
    participant Aux2
    participant Aux3
    participant Aux4
    
    Main->>Playlist: mostrarCancionesRecursivo()
    Playlist->>Aux1: mostrarCancionesRecursivoAux(Cancion1, 1)
    Aux1->>Aux1: Imprimir "1. Bohemian Rhapsody"
    Aux1->>Aux2: mostrarCancionesRecursivoAux(Cancion2, 2)
    Aux2->>Aux2: Imprimir "2. Stairway to Heaven"
    Aux2->>Aux3: mostrarCancionesRecursivoAux(Cancion3, 3)
    Aux3->>Aux3: Imprimir "3. Hotel California"
    Aux3->>Aux4: mostrarCancionesRecursivoAux(Cancion4, 4)
    Aux4->>Aux4: Imprimir "4. Sweet Child O' Mine"
    Aux4->>Playlist: mostrarCancionesRecursivoAux(Cancion5, 5)
    Playlist->>Playlist: Imprimir "5. Smells Like Teen Spirit"
    Playlist->>Playlist: mostrarCancionesRecursivoAux(null, 6)
    Playlist->>Playlist: Caso base: null
    Playlist-->>Main: Fin de la playlist
```

## Diagrama de Flujo - Insertar al Final (Recursivo)

```mermaid
flowchart TD
    A[Inicio: agregarCancionRecursivo] --> B[Llamar a agregarCancionRecursivoAux con cabeza]
    B --> C{¿actual == null?}
    C -->|Sí| D[Crear nuevo NodoCancion]
    C -->|No| E[Llamada recursiva: agregarCancionRecursivoAux actual.siguiente]
    E --> F[actual.siguiente = resultado recursivo]
    F --> G[Retornar actual]
    D --> H[Retornar nuevo nodo]
    G --> I[cabeza = resultado]
    H --> I
    I --> J[Fin]
```

## Diagrama de Secuencia - Insertar al Final (Recursivo)

```mermaid
sequenceDiagram
    participant Main
    participant Playlist
    participant Aux1
    participant Aux2
    participant Aux3
    participant Aux4
    
    Main->>Playlist: agregarCancionRecursivo("Hotel California", "Eagles", 390)
    Playlist->>Aux1: agregarCancionRecursivoAux(Cancion1, ...)
    Aux1->>Aux2: agregarCancionRecursivoAux(Cancion2, ...)
    Aux2->>Aux3: agregarCancionRecursivoAux(Cancion3, ...)
    Aux3->>Aux4: agregarCancionRecursivoAux(null, ...)
    Aux4->>Aux4: Caso base: null
    Aux4->>Aux4: Crear NodoCancion("Hotel California")
    Aux4-->>Aux3: Retornar nuevo nodo
    Aux3->>Aux3: Cancion3.siguiente = nuevo nodo
    Aux3-->>Aux2: Retornar Cancion3
    Aux2->>Aux2: Cancion2.siguiente = Cancion3
    Aux2-->>Aux1: Retornar Cancion2
    Aux1->>Aux1: Cancion1.siguiente = Cancion2
    Aux1-->>Playlist: Retornar Cancion1
    Playlist->>Playlist: cabeza = Cancion1
    Playlist-->>Main: Canción agregada
```

## Evolución de la Lista durante Inserciones

```mermaid
graph LR
    subgraph "Estado Inicial"
        I0[cabeza → null]
    end
    
    subgraph "Insertar Canción 1"
        S1[cabeza → Canción1 → null]
    end
    
    subgraph "Insertar Canción 2"
        S2[cabeza → Canción1 → Canción2 → null]
    end
    
    subgraph "Insertar Canción 3"
        S3[cabeza → Canción1 → Canción2 → Canción3 → null]
    end
    
    I0 --> S1
    S1 --> S2
    S2 --> S3
    
    style I0 fill:#fff4e6
    style S1 fill:#e6f7ff
    style S2 fill:#e6ffe6
    style S3 fill:#ffe6e6
```

## Comparación: Recursivo vs Iterativo

```mermaid
graph TB
    subgraph "Enfoque Recursivo"
        R1[Código elegante]
        R2[Usa pila de llamadas]
        R3[Fácil de entender]
        R4[Riesgo de stack overflow]
    end
    
    subgraph "Enfoque Iterativo"
        I1[Código explícito]
        I2[Usa bucles]
        I3[Más eficiente]
        I4[Sin riesgo de stack overflow]
    end
    
    style R1 fill:#90EE90
    style R2 fill:#FFB6C1
    style R3 fill:#90EE90
    style R4 fill:#FFB6C1
    style I1 fill:#87CEEB
    style I2 fill:#87CEEB
    style I3 fill:#90EE90
    style I4 fill:#90EE90
```

## Analogía Visual: Cadena de Personas

```mermaid
graph LR
    subgraph "Cadena de Personas"
        P1[Persona 1<br/>Cabeza] --> P2[Persona 2]
        P2 --> P3[Persona 3]
        P3 --> P4[Persona 4]
        P4 --> P5[null<br/>Nadie]
    end
    
    style P1 fill:#FFD700
    style P2 fill:#C0C0C0
    style P3 fill:#C0C0C0
    style P4 fill:#C0C0C0
    style P5 fill:#808080
```

## Pila de Recursión - Mostrar Canciones

```mermaid
graph TB
    subgraph "Pila de Llamadas"
        Nivel4["Nivel 4: mostrarCancionesRecursivoAux(null, 5)<br/>CASO BASE: null → Retorna"]
        Nivel3["Nivel 3: mostrarCancionesRecursivoAux(Cancion4, 4)<br/>Imprime Cancion4"]
        Nivel2["Nivel 2: mostrarCancionesRecursivoAux(Cancion3, 3)<br/>Imprime Cancion3"]
        Nivel1["Nivel 1: mostrarCancionesRecursivoAux(Cancion2, 2)<br/>Imprime Cancion2"]
        Nivel0["Nivel 0: mostrarCancionesRecursivoAux(Cancion1, 1)<br/>Imprime Cancion1"]
    end
    
    Nivel0 --> Nivel1
    Nivel1 --> Nivel2
    Nivel2 --> Nivel3
    Nivel3 --> Nivel4
    
    style Nivel0 fill:#e6f7ff
    style Nivel1 fill:#e6f7ff
    style Nivel2 fill:#e6f7ff
    style Nivel3 fill:#e6f7ff
    style Nivel4 fill:#ffe6e6
```

## Pila de Recursión - Insertar al Final

```mermaid
graph TB
    subgraph "Pila de Llamadas"
        Nivel4["Nivel 4: agregarCancionRecursivoAux(null, ...)<br/>CASO BASE: null → Crea nuevo nodo"]
        Nivel3["Nivel 3: agregarCancionRecursivoAux(Cancion4, ...)<br/>Cancion4.siguiente = nuevo nodo"]
        Nivel2["Nivel 2: agregarCancionRecursivoAux(Cancion3, ...)<br/>Cancion3.siguiente = Cancion4"]
        Nivel1["Nivel 1: agregarCancionRecursivoAux(Cancion2, ...)<br/>Cancion2.siguiente = Cancion3"]
        Nivel0["Nivel 0: agregarCancionRecursivoAux(Cancion1, ...)<br/>Cancion1.siguiente = Cancion2"]
    end
    
    Nivel0 --> Nivel1
    Nivel1 --> Nivel2
    Nivel2 --> Nivel3
    Nivel3 --> Nivel4
    
    style Nivel0 fill:#e6f7ff
    style Nivel1 fill:#e6f7ff
    style Nivel2 fill:#e6f7ff
    style Nivel3 fill:#e6f7ff
    style Nivel4 fill:#ffe6e6
```
