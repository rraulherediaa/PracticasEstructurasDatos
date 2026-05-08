# Diagramas Mermaid - Hashing y Diccionarios

## 1. Estructura de la Tabla Hash

```mermaid
graph TB
    subgraph "Tabla Hash - Tamaño 10"
        B0[Bucket 0] --> V0[(vacío)]
        B1[Bucket 1] --> N1[Nodo: ID=202301]
        N1 --> N2[Nodo: ID=202311]
        N2 --> NULL1[null]
        
        B2[Bucket 2] --> N3[Nodo: ID=202302]
        N3 --> NULL2[null]
        
        B3[Bucket 3] --> V1[(vacío)]
        B4[Bucket 4] --> V2[(vacío)]
        
        B5[Bucket 5] --> N4[Nodo: ID=202305]
        N4 --> N5[Nodo: ID=202315]
        N5 --> NULL3[null]
        
        B6[Bucket 6] --> V3[(vacío)]
        B7[Bucket 7] --> V4[(vacío)]
        B8[Bucket 8] --> V5[(vacío)]
        B9[Bucket 9] --> V6[(vacío)]
    end
    
    style B1 fill:#ff9999
    style B2 fill:#99ff99
    style B5 fill:#9999ff
```

## 2. Funcionamiento de la Función Hash

```mermaid
flowchart LR
    subgraph "Proceso de Hashing"
        A[ID Estudiante] --> B{Función Hash}
        B -->|id % 10| C[Índice Bucket]
        C --> D[Almacenar/Recuperar]
    end
    
    subgraph "Ejemplos"
        E[202301] -->|202301 % 10 = 1| F[Bucket 1]
        G[202302] -->|202302 % 10 = 2| H[Bucket 2]
        I[202305] -->|202305 % 10 = 5| J[Bucket 5]
        K[202315] -->|202315 % 10 = 5| L[Bucket 5]
    end
```

## 3. Resolución de Colisiones - Encadenamiento

```mermaid
sequenceDiagram
    participant U as Usuario
    participant T as TablaHash
    participant B5 as Bucket 5
    participant N1 as Nodo 202305
    participant N2 as Nodo 202315
    
    U->>T: put(202305, Carlos)
    T->>T: hash(202305) = 5
    T->>B5: ¿Está vacío?
    B5-->>T: Sí
    T->>B5: Crear nodo 202305
    
    U->>T: put(202315, María)
    T->>T: hash(202315) = 5
    T->>B5: ¿Está vacío?
    B5-->>T: No, contiene 202305
    T->>N1: Encadenar 202315
    N1->>N2: siguiente = nuevo nodo
```

## 4. Flujo de Operaciones

```mermaid
flowchart TD
    subgraph "Operaciones Tabla Hash"
        Start([Inicio]) --> Op{Operación}
        
        Op -->|Insertar| Insert[Calcular Hash]
        Insert --> Insert2[Ir al Bucket]
        Insert2 --> Insert3{Bucket vacío?}
        Insert3 -->|Sí| Insert4[Crear nodo]
        Insert3 -->|No| Insert5[Encadenar al final]
        Insert4 --> End1([Fin])
        Insert5 --> End1
        
        Op -->|Buscar| Search[Calcular Hash]
        Search --> Search2[Ir al Bucket]
        Search2 --> Search3{Encontrado?}
        Search3 -->|Sí| Search4[Retornar dato]
        Search3 -->|No| Search5[Recorrer lista]
        Search5 --> Search6{Encontrado?}
        Search6 -->|Sí| Search4
        Search6 -->|No| Search7[Retornar null]
        Search4 --> End2([Fin])
        Search7 --> End2
        
        Op -->|Eliminar| Delete[Calcular Hash]
        Delete --> Delete2[Buscar en Bucket]
        Delete2 --> Delete3{Encontrado?}
        Delete3 -->|Sí| Delete4[Desenlazar nodo]
        Delete3 -->|No| Delete5[Retornar false]
        Delete4 --> Delete6[Retornar true]
        Delete6 --> End3([Fin])
        Delete5 --> End3
    end
```

## 5. Diagrama de Clases UML

```mermaid
classDiagram
    class Estudiante {
        -int id
        -String nombre
        -String carrera
        -double promedio
        +Estudiante(int, String, String, double)
        +getId() int
        +getNombre() String
        +getCarrera() String
        +getPromedio() double
        +toString() String
    }
    
    class NodoHash {
        -Estudiante estudiante
        -NodoHash siguiente
        +NodoHash(Estudiante)
        +getEstudiante() Estudiante
        +getSiguiente() NodoHash
        +setSiguiente(NodoHash)
    }
    
    class TablaHash {
        -NodoHash[] tabla
        -int tamaño
        -int elementos
        +TablaHash(int)
        +funcionHash(int) int
        +put(int, Estudiante) void
        +get(int) Estudiante
        +remove(int) boolean
        +containsKey(int) boolean
        +size() int
        +mostrarTabla() void
    }
    
    NodoHash --> Estudiante : contiene
    NodoHash --> NodoHash : siguiente
    TablaHash --> NodoHash : tabla[]
```

## 6. Comparación de Estructuras

```mermaid
graph LR
    subgraph "Búsqueda de Elemento"
        direction TB
        
        subgraph "Lista"
            L1[Nodo 1] --> L2[Nodo 2] --> L3[Nodo 3] --> L4[Nodo 4]
            L4 --> L5[...] --> L6[Nodo n]
            style L4 fill:#ff6666
        end
        
        subgraph "Árbol Binario"
            A1[Nodo Raíz] --> A2[Nodo Izq]
            A1 --> A3[Nodo Der]
            A2 --> A4[Nodo]
            A3 --> A5[Nodo]
            style A3 fill:#66ff66
        end
        
        subgraph "Tabla Hash"
            H1[Índice Hash] --> H2[Bucket]
            H2 --> H3[Dato]
            style H2 fill:#6666ff
        end
    end
    
    ListaNote[O(n)] --> L4
    ArbolNote[O(log n)] --> A3
    HashNote[O(1)] --> H2
```

## 7. Evolución del Factor de Carga

```mermaid
xychart-beta
    title "Factor de Carga vs Rendimiento"
    x-axis [0.0, 0.25, 0.5, 0.75, 1.0, 1.5, 2.0]
    y-axis "Colisiones Promedio" 0 --> 5
    line [0, 0.1, 0.3, 0.7, 1.5, 3.0, 5.0]
    
    annotation "Óptimo" at 0.5, 0.3
    annotation "Redimensionar" at 0.75, 0.7
```

## 8. Caso de Estudio - Registro Académico

```mermaid
flowchart TB
    subgraph "Sistema de Estudiantes"
        A[Menú Principal] --> B{Opción}
        
        B -->|1| C[Registrar Estudiante]
        C --> D[Ingresar Datos]
        D --> E[Calcular Hash]
        E --> F[Guardar en Tabla]
        
        B -->|2| G[Buscar Estudiante]
        G --> H[Ingresar ID]
        H --> I[Calcular Hash]
        I --> J[Recuperar Datos]
        
        B -->|3| K[Actualizar Datos]
        K --> L[Buscar por ID]
        L --> M[Modificar Registro]
        
        B -->|4| N[Eliminar Estudiante]
        N --> O[Buscar por ID]
        O --> P[Remover de Tabla]
        
        B -->|5| Q[Mostrar Reporte]
        Q --> R[Recorrer Tabla]
        R --> S[Imprimir Estadísticas]
        
        F --> T([Volver])
        J --> T
        M --> T
        P --> T
        S --> T
        T --> A
    end
```
