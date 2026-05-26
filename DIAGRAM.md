# 📊 Deep Dive: Arquitectura Visual y Flujos Corregidos

## 1. Topología de Infraestructura

```mermaid
graph TB
    classDef hardware fill:#1a1a1a,stroke:#d4d4d8,stroke-width:2px,color:#fff;
    classDef docker fill:#0db7ed,stroke:#005f87,stroke-width:2px,color:#fff;
    classDef dbserver fill:#336791,stroke:#254e6a,stroke-width:2px,color:#fff;
    classDef volume fill:#f59e0b,stroke:#b45309,stroke-width:2px,color:#fff;
    classDef logic fill:#10b981,stroke:#065f46,stroke-width:2px,color:#fff;

    subgraph OS [Host: CachyOS / Linux Kernel]
        Daemon[Docker Daemon]
        Net[Virtual Bridge Network]
    end

    subgraph Containers [Docker Stack]
        MySQL(ucatec-mysql-compose)
        Postgres(ucatec-postgres)
    end

    subgraph Storage [Persistencia]
        M_Vol[(mysql-data)]
        P_Vol[(postgres-data)]
    end

    subgraph DevLogic [PL/pgSQL Layer]
        P_Func[Function: contar_actores]
        P_Proc[Procedure: insertar_actor]
    end

    Daemon --> Containers
    Containers --- Storage
    Postgres --> DevLogic

    class Daemon,Net docker;
    class MySQL,Postgres dbserver;
    class M_Vol,P_Vol volume;
    class P_Func,P_Proc logic;
```

---

## 2. Flujo de Normalización de Datos

Representa cómo la lógica PL/pgSQL procesa las entradas para evitar errores de sintaxis y discrepancias.

```mermaid
sequenceDiagram
    participant User as Usuario/Cliente SQL
    participant Logic as PL/pgSQL (UPPER)
    participant Table as Tabla: actor (Postgres)

    User->>Logic: Llamada con 'guiness' (minúsculas)
    Note over Logic: Aplicando UPPER('guiness') -> 'GUINESS'
    Logic->>Table: SELECT COUNT(*) WHERE last_name = 'GUINESS'
    Table-->>Logic: Resultado: 1
    Logic-->>User: Retorna INTEGER: 1

    User->>Logic: CALL insertar_nuevo_actor('Raul', 'Rocha')
    Note over Logic: Normalizando a 'RAUL' / 'ROCHA'
    Logic->>Table: INSERT INTO actor VALUES ('RAUL', 'ROCHA', NOW())
    Table-->>Logic: Commit OK
    Logic-->>User: RAISE NOTICE 'Éxito'
```

---

## 3. Matriz de Validación de Datos

```mermaid
erDiagram
    ACTOR {
        string first_name "NORMALIZED_UPPER"
        string last_name "NORMALIZED_UPPER"
        timestamp last_update "AUTO_NOW"
    }
    
    VALIDATION_LOGIC ||--o{ ACTOR : "Normaliza y Valida"
```
