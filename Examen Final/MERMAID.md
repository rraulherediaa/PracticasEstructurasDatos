# Diagramas Mermaid - Examen Final ED1

## Diagrama 1: Arquitectura del Sistema

```mermaid
graph TB
    subgraph "Sistema de Gestión de Tráfico y Caché"
        A[Paquetes de Red<br/>Entrantes] --> B[Cola de Recepción<br/>FIFO]
        B --> C[Procesamiento]
        
        C --> D[Historial de IPs<br/>Lista Doblemente Enlazada]
        C --> E[Verificación de Seguridad]
        
        E --> F{¿IP Segura?}
        F -->|Sí| G[Tabla Hash<br/>IPs Seguras]
        F -->|No| H[Bloquear Paquete]
        
        I[Limpieza de Seguridad] --> J[Eliminar de Hash]
        I --> K[Eliminar de Historial]
    end
    
    style A fill:#ff9999
    style B fill:#99ff99
    style G fill:#9999ff
    style H fill:#ff6666
```

## Diagrama 2: Estructura de Datos - Cola

```mermaid
graph LR
    subgraph "Cola de Paquetes - FIFO"
        Frente[FRENTE<br/>Primer Paquete] --> P1[Paquete 1<br/>192.168.1.10]
        P1 --> P2[Paquete 2<br/>10.0.0.15]
        P2 --> P3[Paquete 3<br/>172.16.0.5]
        P3 --> P4[Paquete 4<br/>192.168.0.50]
        P4 --> P5[Paquete 5<br/>10.1.1.20]
        P5 --> Final[FINAL<br/>Último Paquete]
    end
    
    style Frente fill:#90EE90
    style Final fill:#FFB6C1
```

## Diagrama 3: Lista Doblemente Enlazada - Historial

```mermaid
graph LR
    subgraph "Lista Doblemente Enlazada - Historial de IPs"
        Null1[null] -->|anterior| N1[Nodo 1<br/>192.168.1.100<br/>Más Antiguo]
        N1 -->|siguiente| N2[Nodo 2<br/>10.0.0.50]
        N2 -->|siguiente| N3[Nodo 3<br/>172.16.0.25]
        N3 -->|siguiente| N4[Nodo 4<br/>192.168.0.1]
        N4 -->|siguiente| N100[Nodo 100<br/>10.0.0.99<br/>Más Reciente]
        N100 -->|siguiente| Null2[null]
        
        N2 -->|anterior| N1
        N3 -->|anterior| N2
        N4 -->|anterior| N3
        N100 -->|anterior| N4
    end
    
    subgraph "Nuevo Nodo #101"
        N101[Nodo 101<br/>Nueva IP] -.->|elimina| N1
    end
    
    style N1 fill:#ff9999
    style N100 fill:#90EE90
    style N101 fill:#66ff66
```

## Diagrama 4: Tabla Hash con Manejo de Colisiones

```mermaid
graph TB
    subgraph "Tabla Hash - IPs Seguras"
        T0[Bucket 0] --> V0[(vacío)]
        T1[Bucket 1] --> V1[(vacío)]
        T2[Bucket 2] --> N1[Nodo: 192.168.1.10<br/>Servidor Local]
        T3[Bucket 3] --> V3[(vacío)]
        T4[Bucket 4] --> N2[Nodo: 8.8.8.8<br/>DNS Google]
        T5[Bucket 5] --> N3[Nodo: 1.1.1.1<br/>DNS Cloudflare]
        T6[Bucket 6] --> V6[(vacío)]
        T7[Bucket 7] --> N4[Nodo: 172.16.0.5<br/>→ Nodo: 10.0.0.50]
        N4 --> N5[Colisión resuelta<br/>por encadenamiento]
        
        T8[Bucket 8-15] --> V8[(vacíos)]
    end
    
    style T2 fill:#90EE90
    style T4 fill:#90EE90
    style T5 fill:#90EE90
    style T7 fill:#FFD700
```

## Diagrama 5: Flujo de Operaciones - Cola

```mermaid
flowchart TD
    subgraph "Operaciones de Cola"
        Start([Inicio]) --> Op{Operación}
        
        Op -->|Enqueue| E1[Crear nuevo nodo]
        E1 --> E2{Cola vacía?}
        E2 -->|Sí| E3[Inicio = Final = nuevo]
        E2 -->|No| E4[Final.siguiente = nuevo]
        E4 --> E5[Final = nuevo]
        E3 --> E6[Incrementar tamaño]
        E5 --> E6
        E6 --> End1([Fin])
        
        Op -->|Dequeue| D1{Cola vacía?}
        D1 -->|Sí| D2[Retornar null]
        D1 -->|No| D3[Guardar dato del inicio]
        D3 --> D4[Inicio = Inicio.siguiente]
        D4 --> D5{Inicio == null?}
        D5 -->|Sí| D6[Final = null]
        D5 -->|No| D7[Decrementar tamaño]
        D6 --> D7
        D7 --> D8[Retornar dato]
        D8 --> End2([Fin])
        D2 --> End2
    end
```

## Diagrama 6: Flujo de Limpieza de Seguridad

```mermaid
sequenceDiagram
    participant U as Usuario
    participant S as Sistema
    participant TH as Tabla Hash
    participant LH as Lista Historial
    participant I as Integridad
    
    U->>S: limpiezaDeSeguridad(ip)
    
    S->>TH: eliminar(ip)
    TH-->>S: eliminadaHash = true/false
    
    S->>LH: buscarYEliminar(ip)
    LH-->>S: eliminadaHistorial = count
    
    S->>I: verificar()
    I-->>S: ambas estructuras actualizadas?
    
    alt Éxito
        S-->>U: ✅ IP eliminada de ambas estructuras
    else Fallo parcial
        S-->>U: ⚠️ Verificar inconsistencias
    end
```

## Diagrama 7: Diagrama de Clases UML

```mermaid
classDiagram
    class PaqueteRed {
        -String ipOrigen
        -String ipDestino
        -int tamañoPayload
        +PaqueteRed(String, String, int)
        +getIpOrigen() String
        +getIpDestino() String
        +getTamaño() int
        +toString() String
    }
    
    class NodoCola {
        -PaqueteRed paquete
        -NodoCola siguiente
        +NodoCola(PaqueteRed)
        +getPaquete() PaqueteRed
        +getSiguiente() NodoCola
        +setSiguiente(NodoCola)
    }
    
    class ColaPaquetes {
        -NodoCola inicio
        -NodoCola fin
        -int tamaño
        +ColaPaquetes()
        +enqueue(PaqueteRed) void
        +dequeue() PaqueteRed
        +peek() PaqueteRed
        +estaVacia() boolean
        +getTamaño() int
    }
    
    class NodoHistorial {
        -String ip
        -long timestamp
        -NodoHistorial anterior
        -NodoHistorial siguiente
        +NodoHistorial(String)
        +getIp() String
        +getTimestamp() long
        +getAnterior() NodoHistorial
        +getSiguiente() NodoHistorial
        +setAnterior(NodoHistorial)
        +setSiguiente(NodoHistorial)
    }
    
    class ListaDobleHistorial {
        -NodoHistorial cabeza
        -NodoHistorial cola
        -int contador
        -int limite
        +ListaDobleHistorial(int limite)
        +agregarAlFrente(String) void
        +recorrerAdelante() void
        +recorrerAtras() void
        +eliminarUltimo() void
        +buscarYEliminar(String) int
        +getContador() int
    }
    
    class NodoHash {
        -String ip
        -String metadata
        -NodoHash siguiente
        +NodoHash(String, String)
        +getIp() String
        +getMetadata() String
        +getSiguiente() NodoHash
        +setSiguiente(NodoHash)
    }
    
    class TablaHashSeguras {
        -NodoHash[] tabla
        -int capacidad
        -int elementos
        +TablaHashSeguras(int)
        +funcionHash(String) int
        +insertar(String, String) void
        +buscar(String) String
        +eliminar(String) boolean
        +getFactorCarga() double
        -rehash() void
    }
    
    class SistemaGestionTraficoCache {
        -ColaPaquetes cola
        -ListaDobleHistorial historial
        -TablaHashSeguras ipsSeguras
        +SistemaGestionTraficoCache()
        +procesarPaquetes() void
        +demostrarHistorial() void
        +demostrarCacheSeguridad() void
        +demostrarLimpieza() void
        +limpiezaDeSeguridad(String) void
        +main(String[] args)$ void
    }
    
    ColaPaquetes --> NodoCola
    NodoCola --> PaqueteRed
    ListaDobleHistorial --> NodoHistorial
    TablaHashSeguras --> NodoHash
    SistemaGestionTraficoCache --> ColaPaquetes
    SistemaGestionTraficoCache --> ListaDobleHistorial
    SistemaGestionTraficoCache --> TablaHashSeguras
```

## Diagrama 8: Comparación de Complejidades

```mermaid
xychart-beta
    title "Complejidad Temporal - Operaciones Principales"
    x-axis [Cola, Cola, Lista, Lista, Hash, Hash]
    y-axis "Complejidad"
    bar [1, 1, 1, 100, 1, 1]
    
    annotation "O(1)" at 0, 1
    annotation "O(1)" at 1, 1
    annotation "O(1)" at 2, 1
    annotation "O(n)" at 3, 100
    annotation "O(1)" at 4, 1
    annotation "O(1)" at 5, 1
```

---

**Nota:** Los diagramas anteriores ilustran la arquitectura completa del sistema de gestión de tráfico y caché implementado para el Examen Final de Estructura de Datos I.
