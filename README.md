# 🚀 Microevaluación 2: Gestión Avanzada de Base de Datos II

[![DB Excellence](https://img.shields.io/badge/Database-Excellence-blue.svg)](https://github.com/your-repo)
[![Course](https://img.shields.io/badge/Course-GYMBDI-orange.svg)](#)
[![SQL](https://img.shields.io/badge/Level-Professional-green.svg)](#)

Esta guía representa una inmersión profunda en la administración, analítica y automatización de SGBD modernos. Abarca desde la infraestructura física hasta la lógica de negocio distribuida.

---

## 📑 Tabla de Contenidos
1.  [🏗️ Administración Avanzada de SGBD](#-1-administración-avanzada-de-sgbd)
2.  [📊 Consultas SQL Avanzadas y Analítica](#-2-consultas-sql-avanzadas-y-analítica)
3.  [⚙️ Procedimientos, Funciones y Automatización](#-3-procedimientos-funciones-y-automatización)
4.  [🗺️ Visualizaciones Directas (Mapas Pro)](diagramas.md)

---

## 🏗️ 1. Administración Avanzada de SGBD

### 1.1. SGBD Avanzados y Cloud
El ecosistema actual se divide entre motores tradicionales y soluciones administradas.

```mermaid
flowchart TD
    Root(("SGBD MODERNOS"))
    Root -->|Tradicionales| Rel["PostgreSQL, Oracle, SQL Server"]
    Root -->|Administrados| CL["AWS RDS / Cloud SQL"]
    Root -->|Portátiles| Lite(["SQLite"])

    classDef central fill:#2563eb,color:#fff,font-weight:bold,stroke:#1e40af
    class Root central
```

### 1.2. Gestión Multi-plataforma
```mermaid
flowchart LR
    Main{{"INFRAESTRUCTURA"}} -->|Modelos| OP["On-Premise"] & CL["Cloud"] & HYB(("HÍBRIDO"))

    classDef hybrid fill:#8b5cf6,color:#fff,stroke:#6d28d9
    class HYB hybrid
```

### 1.3. Tuning y Rendimiento
```mermaid
flowchart TD
    Tuning(("TUNING"))
    Tuning -->|RAM| P1["shared_buffers / Buffer Pool"]
    Tuning -->|CPU| P2["MAXDOP / Parallel Workers"]

    classDef core fill:#ef4444,color:#fff,stroke:#b91c1c
    class Tuning core
```

### 1.4. Herramientas y CLI
```mermaid
flowchart TD
    Tools(("TOOLS"))
    Tools -->|GUI| D["DBeaver / Azure Data Studio"]
    Tools -->|CLI| Py["Python / Shell Scripts / psql"]

    classDef central fill:#2563eb,color:#fff,font-weight:bold,stroke:#1e40af
    class Tools central
```

---

## 📊 2. Consultas SQL Avanzadas y Analítica

### 2.1. Procesamiento Complejo y Big Data
```mermaid
flowchart TD
    Data(("BIG DATA"))
    Data --> CTE["CTEs / Recursividad"]
    Data --> Part["Particionamiento Lógico"]

    classDef central fill:#2563eb,color:#fff,font-weight:bold,stroke:#1e40af
    class Data central
```

### 2.2. Transformaciones y Ventanas
```mermaid
flowchart LR
    Trans(("TRANSFORMACIÓN"))
    Trans --> Win["Window Functions: Rank/Lead"]
    Trans --> Pivot["Pivot / Unpivot"]

    classDef core fill:#ef4444,color:#fff,stroke:#b91c1c
    class Trans core
```

### 2.3. Analítica para BI
```mermaid
flowchart TD
    BI(("ANALÍTICA"))
    BI --> ROLL["ROLLUP / CUBE / Grouping Sets"]

    classDef central fill:#2563eb,color:#fff,font-weight:bold,stroke:#1e40af
    class BI central
```

### 2.4. Optimización (El Cerebro)
```mermaid
flowchart TD
    Opt(("OPTIMIZER"))
    Opt --> P["Parser"] --> B["Binder"] --> C["Cost Estimator"]

    classDef core fill:#ef4444,color:#fff,stroke:#b91c1c
    class Opt core
```

---

## ⚙️ 3. Procedimientos, Funciones y Automatización

### 3.1. Programabilidad
```mermaid
flowchart TD
    Prog(("LOGIC"))
    Prog --> SP["Stored Procedures"] & UDF["Functions"] & TRG["Triggers"]

    classDef central fill:#2563eb,color:#fff,font-weight:bold,stroke:#1e40af
    class Prog central
```

### 3.2. Orquestación
```mermaid
flowchart LR
    Orq(("ORQUESTACIÓN")) --> Cron["Cron / SQL Agent"] & Air["Airflow / DBT"]
```

### 3.3. Auditoría
```mermaid
flowchart TD
    Sec(("AUDIT"))
    Sec --> CDC["Change Data Capture"] & Logs["Logging Histórico"]

    classDef core fill:#ef4444,color:#fff,stroke:#b91c1c
    class Sec core
```

### 3.4. Historia y Evolución
```mermaid
flowchart TD
    Hist(("HISTORIA"))
    Hist --> Past["Fat Client"] --> Mid["3-Tier"] --> Now["Cloud Native / Portable"]

    classDef history fill:#fef3c7,stroke:#d97706
    class Hist history
```

---
*Documentación avanzada para la asignatura Gestión y Manejo de Base de Datos II. Visita [diagramas.md](diagramas.md) para ver los mapas en tamaño completo.*
