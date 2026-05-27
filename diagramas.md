# 🗺️ Diagramas Mermaid - Gestión Avanzada de Base de Datos II

Este archivo contiene todos los diagramas Mermaid utilizados en la documentación de la Microevaluación 2.

---

## 🏗️ 1. Administración Avanzada de SGBD

### 1.1. SGBD Modernos y Cloud - Resumen

**PostgreSQL**: MVCC, extensible, tipos avanzados, replicación física/lógica
**SQL Server**: Integración Microsoft, columnstore, Always On, PolyBase, ML Services
**Oracle**: Multitenant CDB/PDB, AMM, Exadata, compresión, GoldenGate
**MariaDB**: Storage engines pluggables, Galera cluster, window functions
**SQLite**: Serverless, ACID, single-file, zero-config

**Cloud Services**: AWS RDS/Aurora, GCP Cloud SQL, Azure Database - managed services con alta disponibilidad, backups automáticos, escalabilidad

```mermaid
flowchart TD
    Root(("SGBD MODERNOS"))
    
    Root -->|Open Source| OSS["PostgreSQL<br/>MVCC/Extensible<br/>MariaDB<br/>Storage Engines<br/>SQLite<br/>Serverless"]
    Root -->|Propietario| PROP["SQL Server<br/>Columnstore/Always On<br/>Oracle<br/>Multitenant/Exadata"]
    
    OSS -->|Cloud| CLOUD1["AWS Aurora<br/>GCP Cloud SQL<br/>Azure Postgres"]
    PROP -->|Cloud| CLOUD2["AWS RDS<br/>GCP Cloud SQL<br/>Azure SQL"]
    
    OSS -->|On-Premise| ON1["Self-hosted<br/>Control total"]
    PROP -->|On-Premise| ON2["Self-hosted<br/>Control total"]

    classDef central fill:#2563eb,color:#fff,font-weight:bold,stroke:#1e40af
    classDef cloud fill:#10b981,color:#fff,stroke:#059669
    classDef onprem fill:#f59e0b,color:#fff,stroke:#d97706
    class Root central
    class CLOUD1,CLOUD2 cloud
    class ON1,ON2 onprem
```

### 1.2. Infraestructura Híbrida - Resumen

```mermaid
flowchart LR
    Main{{"INFRAESTRUCTURA"}}
    
    Main -->|On-Premise| OP["Control total<br/>CAPEX<br/>Latencia mínima<br/>Compliance"]
    Main -->|Cloud| CL["OPEX<br/>Escalabilidad<br/>Managed services<br/>Time-to-market"]
    Main -->|Híbrido| HYB["Burst to cloud<br/>DR en cloud<br/>Data lake<br/>Multi-cloud"]

    classDef hybrid fill:#8b5cf6,color:#fff,stroke:#6d28d9
    classDef onprem fill:#f59e0b,color:#fff,stroke:#d97706
    classDef cloud fill:#10b981,color:#fff,stroke:#059669
    class OP onprem
    class CL cloud
    class HYB hybrid
```

### 1.3. Tuning y Rendimiento - Resumen

```mermaid
flowchart TD
    Tuning(("TUNING"))
    
    Tuning -->|Memory| MEM["PostgreSQL: shared_buffers<br/>SQL Server: max server memory<br/>Oracle: SGA_TARGET"]
    Tuning -->|CPU| CPU["PostgreSQL: parallel workers<br/>SQL Server: MAXDOP<br/>Oracle: parallelism"]
    Tuning -->|I/O| IO["PostgreSQL: random_page_cost<br/>SQL Server: tempdb<br/>MySQL: innodb_io_capacity"]
    Tuning -->|Monitoring| MON["pg_stat_statements<br/>DMVs<br/>AWR/ASH"]

    classDef core fill:#ef4444,color:#fff,stroke:#b91c1c
    classDef memory fill:#3b82f6,color:#fff,stroke:#1d4ed8
    classDef cpu fill:#10b981,color:#fff,stroke:#059669
    classDef io fill:#f59e0b,color:#fff,stroke:#d97706
    class Tuning core
    class MEM memory
    class CPU cpu
    class IO io
```

### 1.4. Herramientas de Administración - Resumen

```mermaid
flowchart TD
    Tools(("HERRAMIENTAS"))
    
    Tools -->|GUI| GUI["DBeaver<br/>Azure Data Studio<br/>pgAdmin<br/>SQL Developer"]
    Tools -->|CLI| CLI["psql/sqlcmd/sqlplus<br/>pg_dump/mysqldump<br/>bcp/expdp"]
    Tools -->|Scripting| SCRIPT["Python<br/>Bash<br/>Ansible<br/>Terraform"]

    classDef central fill:#2563eb,color:#fff,font-weight:bold,stroke:#1e40af
    classDef gui fill:#10b981,color:#fff,stroke:#059669
    classDef cli fill:#f59e0b,color:#fff,stroke:#d97706
    classDef script fill:#8b5cf6,color:#fff,stroke:#6d28d9
    class Tools central
    class GUI gui
    class CLI cli
    class SCRIPT script
```

---

## 📊 2. Consultas SQL Avanzadas y Analítica de Datos

### 2.1. Subconsultas, CTE y Big Data - Resumen

```mermaid
flowchart TD
    Complex(("PROCESAMIENTO"))
    
    Complex -->|Subconsultas| SUB["Escalares/Fila/Columna<br/>Correlacionadas<br/>EXISTS/ANY/ALL"]
    Complex -->|CTE| CTE["Básicas/Múltiples<br/>Recursivas<br/>Jerarquías/Grafos"]
    Complex -->|Big Data| BIG["Partitioning<br/>Sharding<br/>Materialized Views<br/>Batch Processing"]

    classDef central fill:#2563eb,color:#fff,font-weight:bold,stroke:#1e40af
    classDef sub fill:#10b981,color:#fff,stroke:#059669
    classDef cte fill:#f59e0b,color:#fff,stroke:#d97706
    classDef big fill:#ef4444,color:#fff,stroke:#b91c1c
    class Complex central
    class SUB sub
    class CTE cte
    class BIG big
```

### 2.2. JOINs, Window Functions y Transformación - Resumen

```mermaid
flowchart LR
    Trans(("TRANSFORMACIÓN"))
    
    Trans -->|JOINs| JOIN["INNER/LEFT/RIGHT/FULL<br/>CROSS/SELF<br/>LATERAL/APPLY"]
    Trans -->|Window| WIN["Agregación<br/>Ranking<br/>LAG/LEAD<br/>Frames"]
    Trans -->|Set Ops| SET["UNION/INTERSECT<br/>EXCEPT"]
    Trans -->|Pivot| PIV["PIVOT<br/>UNPIVOT"]

    classDef core fill:#ef4444,color:#fff,stroke:#b91c1c
    classDef join fill:#3b82f6,color:#fff,stroke:#1d4ed8
    classDef window fill:#10b981,color:#fff,stroke:#059669
    classDef transform fill:#f59e0b,color:#fff,stroke:#d97706
    class Trans core
    class JOIN join
    class WIN window
    class PIV transform
```

### 2.3. Analítica para BI - Resumen

```mermaid
flowchart TD
    BI(("ANALÍTICA BI"))
    
    BI -->|Agregaciones| AGG["ROLLUP<br/>Subtotales<br/>CUBE<br/>Matriz<br/>GROUPING SETS<br/>Custom"]
    BI -->|Advanced| ADV["Percentiles<br/>Histogramas<br/>Tendencias YoY<br/>Cohort Analysis"]

    classDef central fill:#2563eb,color:#fff,font-weight:bold,stroke:#1e40af
    classDef rollup fill:#10b981,color:#fff,stroke:#059669
    classDef agg fill:#8b5cf6,color:#fff,stroke:#6d28d9
    class BI central
    class AGG rollup
    class ADV agg
```

### 2.4. Optimización de Consultas - Resumen

```mermaid
flowchart TD
    Opt(("OPTIMIZACIÓN"))
    
    Opt -->|EXPLAIN| EXP["PostgreSQL EXPLAIN ANALYZE<br/>SQL Server Execution Plan<br/>Oracle EXPLAIN PLAN"]
    Opt -->|Indexing| IDX["B-Tree<br/>Partial/Functional<br/>Composite<br/>Covering<br/>GIN/GiST"]
    Opt -->|Tuning| TUN["VACUUM/ANALYZE<br/>Query Store<br/>SQL Tuning Advisor"]
    Opt -->|Anti-patrones| ANTI["SELECT *<br/>Funciones en índices<br/>LIKE %...%"]

    classDef core fill:#ef4444,color:#fff,stroke:#b91c1c
    classDef explain fill:#3b82f6,color:#fff,stroke:#1d4ed8
    classDef index fill:#10b981,color:#fff,stroke:#059669
    classDef tune fill:#f59e0b,color:#fff,stroke:#d97706
    class Opt core
    class EXP explain
    class IDX index
    class TUN tune
```

---

## ⚙️ 3. Procedimientos, Funciones y Automatización

### 3.1. Procedimientos, Funciones y Triggers - Resumen

```mermaid
flowchart TD
    Prog(("PROGRAMABILIDAD"))
    
    Prog -->|Procedures| SP["plpgsql/T-SQL/PL/SQL<br/>Parámetros IN/OUT<br/>Control de flujo"]
    Prog -->|Functions| UDF["Escalar<br/>Table-Valued<br/>Inline<br/>Usables en SQL"]
    Prog -->|Triggers| TRG["BEFORE/AFTER<br/>INSERT/UPDATE/DELETE<br/>Auditoría"]
    Prog -->|Portabilidad| PORT["SQL estándar<br/>Abstracción<br/>Testing"]

    classDef central fill:#2563eb,color:#fff,font-weight:bold,stroke:#1e40af
    classDef sp fill:#10b981,color:#fff,stroke:#059669
    classDef udf fill:#f59e0b,color:#fff,stroke:#d97706
    classDef trig fill:#ef4444,color:#fff,stroke:#b91c1c
    class Prog central
    class SP sp
    class UDF udf
    class TRG,PORT trig
```

### 3.2. Orquestación y Automatización - Resumen

```mermaid
flowchart LR
    Orq(("ORQUESTACIÓN"))
    
    Orq -->|Nativos| NAT["SQL Agent<br/>DBMS_SCHEDULER<br/>pg_cron<br/>Events"]
    Orq -->|Externos| EXT["Airflow<br/>dbt<br/>Prefect<br/>K8s CronJobs"]
    Orq -->|Scripts| SCR["Python<br/>Bash Cron<br/>systemd<br/>PowerShell"]

    classDef central fill:#2563eb,color:#fff,font-weight:bold,stroke:#1e40af
    classDef native fill:#10b981,color:#fff,stroke:#059669
    classDef external fill:#f59e0b,color:#fff,stroke:#d97706
    classDef script fill:#8b5cf6,color:#fff,stroke:#6d28d9
    class Orq central
    class NAT native
    class EXT external
    class SCR script
```

### 3.3. Auditoría y Logging - Resumen

```mermaid
flowchart TD
    Sec(("AUDITORÍA Y LOGGING"))
    
    Sec -->|Auditoría| AUD["pg_audit<br/>SQL Audit/CDC<br/>Oracle FGA<br/>Triggers"]
    Sec -->|Logging| LOG["Query log<br/>Slow query<br/>Error log<br/>Extended events"]
    Sec -->|Centralización| CEN["ELK Stack<br/>Prometheus+Grafana<br/>Splunk<br/>CloudWatch"]

    classDef central fill:#ef4444,color:#fff,stroke:#b91c1c
    classDef audit fill:#3b82f6,color:#fff,stroke:#1d4ed8
    classDef log fill:#10b981,color:#fff,stroke:#059669
    classDef centralize fill:#f59e0b,color:#fff,stroke:#d97706
    class Sec central
    class AUD audit
    class LOG log
    class CEN centralize
```

### 3.4. Buenas Prácticas - Resumen

```mermaid
flowchart TD
    Best(("BUENAS PRÁCTICAS"))
    
    Best -->|Diseño| DES["Single Responsibility<br/>Nombres descriptivos<br/>Error handling<br/>Documentación"]
    Best -->|Portabilidad| POR["SQL estándar<br/>Abstracción<br/>No vendor lock-in<br/>Testing"]
    Best -->|Mantenimiento| MAINT["Git<br/>Code Review<br/>Monitoring<br/>Automated Testing"]

    classDef central fill:#2563eb,color:#fff,font-weight:bold,stroke:#1e40af
    classDef design fill:#10b981,color:#fff,stroke:#059669
    classDef portable fill:#f59e0b,color:#fff,stroke:#d97706
    classDef maintain fill:#8b5cf6,color:#fff,stroke:#6d28d9
    class Best central
    class DES design
    class POR portable
    class MAINT maintain
```
