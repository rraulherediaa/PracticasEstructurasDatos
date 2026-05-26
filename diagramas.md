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
flowchart DT
    classDef central fill:#2563eb,color:#fff,font-weight:bold,stroke:#1e40af
    classDef cloud fill:#10b981,color:#fff,stroke:#059669
    classDef onprem fill:#f59e0b,color:#fff,stroke:#d97706
    
    Root(("SGBD MODERNOS")):::central
    
    Root -->|Open Source| OSS[PostgreSQL<br/>MVCC/Extensible<br/>MariaDB<br/>Storage Engines<br/>SQLite<br/>Serverless]
    Root -->|Propietario| PROP[SQL Server<br/>Columnstore/Always On<br/>Oracle<br/>Multitenant/Exadata]
    
    OSS -->|Cloud| CLOUD1[AWS Aurora<br/>GCP Cloud SQL<br/>Azure Postgres]:::cloud
    PROP -->|Cloud| CLOUD2[AWS RDS<br/>GCP Cloud SQL<br/>Azure SQL]:::cloud
    
    OSS -->|On-Premise| ON1[Self-hosted<br/>Control total]:::onprem
    PROP -->|On-Premise| ON2[Self-hosted<br/>Control total]:::onprem
```

### 1.2. Infraestructura Híbrida - Resumen

**On-Premise**: Control total, CAPEX, latencia mínima, compliance regulatorio
**Cloud**: OPEX, escalabilidad elástica, managed services, time-to-market rápido
**Híbrido**: Burst to cloud, DR en cloud, data lake, multi-cloud

**Estrategias de migración**: Lift and Shift, Replatforming, Refactoring, Retire

```mermaid
flowchart LR
    classDef hybrid fill:#8b5cf6,color:#fff,stroke:#6d28d9
    classDef onprem fill:#f59e0b,color:#fff,stroke:#d97706
    classDef cloud fill:#10b981,color:#fff,stroke:#059669
    
    Main{{"INFRAESTRUCTURA"}}
    
    Main -->|On-Premise| OP[Control total<br/>CAPEX<br/>Latencia mínima<br/>Compliance]:::onprem
    Main -->|Cloud| CL[OPEX<br/>Escalabilidad<br/>Managed services<br/>Time-to-market]:::cloud
    Main -->|Híbrido| HYB[Burst to cloud<br/>DR en cloud<br/>Data lake<br/>Multi-cloud]:::hybrid
```

### 1.3. Tuning y Rendimiento - Resumen

**PostgreSQL**: shared_buffers (25% RAM), work_mem, effective_cache_size, autovacuum tuning
**SQL Server**: max server memory, MAXDOP, tempdb configuration, Query Store
**Oracle**: SGA_TARGET, PGA_AGGREGATE_TARGET, MEMORY_TARGET, optimizer hints
**MariaDB/MySQL**: innodb_buffer_pool_size (70-80% RAM), innodb_io_capacity

**Jerarquía de optimización**: Hardware/OS → Configuración motor → Diseño físico → Diseño lógico → Consultas

```mermaid
flowchart TD
    classDef core fill:#ef4444,color:#fff,stroke:#b91c1c
    classDef memory fill:#3b82f6,color:#fff,stroke:#1d4ed8
    classDef cpu fill:#10b981,color:#fff,stroke:#059669
    classDef io fill:#f59e0b,color:#fff,stroke:#d97706
    
    Tuning(("TUNING")):::core
    
    Tuning -->|Memory| MEM[PostgreSQL<br/>shared_buffers<br/>SQL Server<br/>max server memory<br/>Oracle<br/>SGA_TARGET]:::memory
    Tuning -->|CPU| CPU[PostgreSQL<br/>parallel workers<br/>SQL Server<br/>MAXDOP<br/>Oracle<br/>parallelism]:::cpu
    Tuning -->|I/O| IO[PostgreSQL<br/>random_page_cost<br/>SQL Server<br/>tempdb<br/>MySQL<br/>innodb_io_capacity]:::io
    Tuning -->|Monitoring| MON[pg_stat_statements<br/>DMVs<br/>AWR/ASH]
```

### 1.4. Herramientas de Administración - Resumen

**GUI Universales**: DBeaver (multi-database), Azure Data Studio (SQL Server), pgAdmin (PostgreSQL), SQL Developer (Oracle)
**CLI**: psql (PostgreSQL), sqlcmd (SQL Server), sqlplus (Oracle), mysql (MariaDB/MySQL), pg_dump/mysqldump (backup)
**Scripting**: Python (psycopg2, pyodbc), Bash scripts, Ansible (config management), Terraform (IaC)

```mermaid
flowchart TD
    classDef central fill:#2563eb,color:#fff,font-weight:bold,stroke:#1e40af
    classDef gui fill:#10b981,color:#fff,stroke:#059669
    classDef cli fill:#f59e0b,color:#fff,stroke:#d97706
    classDef script fill:#8b5cf6,color:#fff,stroke:#6d28d9
    
    Tools(("HERRAMIENTAS")):::central
    
    Tools -->|GUI| GUI[DBeaver<br/>Azure Data Studio<br/>pgAdmin<br/>SQL Developer]:::gui
    Tools -->|CLI| CLI[psql/sqlcmd/sqlplus<br/>pg_dump/mysqldump<br/>bcp/expdp]:::cli
    Tools -->|Scripting| SCRIPT[Python<br/>Bash<br/>Ansible<br/>Terraform]:::script
```

---

## 📊 2. Consultas SQL Avanzadas y Analítica de Datos

### 2.1. Subconsultas, CTE y Big Data - Resumen

**Subconsultas**: Escalares, fila, columna, tabla, correlacionadas, EXISTS/NOT EXISTS, ANY/ALL
**CTE**: Básicas, múltiples, recursivas (WITH RECURSIVE) - para jerarquías y grafos
**Big Data**: Partitioning (range, list, hash), sharding, materialized views, batch processing

```mermaid
flowchart TD
    classDef central fill:#2563eb,color:#fff,font-weight:bold,stroke:#1e40af
    classDef sub fill:#10b981,color:#fff,stroke:#059669
    classDef cte fill:#f59e0b,color:#fff,stroke:#d97706
    classDef big fill:#ef4444,color:#fff,stroke:#b91c1c
    
    Complex(("PROCESAMIENTO")):::central
    
    Complex -->|Subconsultas| SUB[Escalares/Fila/Columna<br/>Correlacionadas<br/>EXISTS/ANY/ALL]:::sub
    Complex -->|CTE| CTE[Básicas/Múltiples<br/>Recursivas<br/>Jerarquías/Grafos]:::cte
    Complex -->|Big Data| BIG[Partitioning<br/>Sharding<br/>Materialized Views<br/>Batch Processing]:::big
```

### 2.2. JOINs, Window Functions y Transformación - Resumen

**JOINs**: INNER, LEFT/RIGHT, FULL OUTER, CROSS, SELF, LATERAL/APPLY
**Window Functions**: Agregación (AVG, SUM), Ranking (ROW_NUMBER, RANK, DENSE_RANK), Offset (LAG, LEAD), Frames
**Set Operations**: UNION, UNION ALL, INTERSECT, EXCEPT/MINUS
**PIVOT/UNPIVOT**: Rotación de datos (filas ↔ columnas)

```mermaid
flowchart LR
    classDef core fill:#ef4444,color:#fff,stroke:#b91c1c
    classDef join fill:#3b82f6,color:#fff,stroke:#1d4ed8
    classDef window fill:#10b981,color:#fff,stroke:#059669
    classDef transform fill:#f59e0b,color:#fff,stroke:#d97706
    
    Trans(("TRANSFORMACIÓN")):::core
    
    Trans -->|JOINs| JOIN[INNER/LEFT/RIGHT/FULL<br/>CROSS/SELF<br/>LATERAL/APPLY]:::join
    Trans -->|Window| WIN[Agregación<br/>Ranking<br/>LAG/LEAD<br/>Frames]:::window
    Trans -->|Set Ops| SET[UNION/INTERSECT<br/>EXCEPT]
    Trans -->|Pivot| PIVOT[PIVOT<br/>UNPIVOT]:::transform
```

### 2.3. Analítica para BI - Resumen

**ROLLUP**: Subtotales y gran total con jerarquía
**CUBE**: Todas las combinaciones posibles (matriz multidimensional)
**GROUPING SETS**: Combinaciones específicas de agregación
**Advanced**: Percentiles (PERCENTILE_CONT), histogramas (NTILE, WIDTH_BUCKET), análisis de tendencias (YoY, moving averages), cohort analysis

```mermaid
flowchart TD
    classDef central fill:#2563eb,color:#fff,font-weight:bold,stroke:#1e40af
    classDef rollup fill:#10b981,color:#fff,stroke:#059669
    classDef cube fill:#f59e0b,color:#fff,stroke:#d97706
    classDef agg fill:#8b5cf6,color:#fff,stroke:#6d28d9
    
    BI(("ANALÍTICA BI")):::central
    
    BI -->|Agregaciones| AGG[ROLLUP<br/>Subtotales<br/>CUBE<br/>Matriz<br/>GROUPING SETS<br/>Custom]:::rollup
    BI -->|Advanced| ADV[Percentiles<br/>Histogramas<br/>Tendencias YoY<br/>Cohort Analysis]:::agg
```

### 2.4. Optimización de Consultas - Resumen

**EXPLAIN**: PostgreSQL EXPLAIN ANALYZE, SQL Server Execution Plan, Oracle EXPLAIN PLAN
**Indexing**: B-Tree (default), Partial/Functional, Composite, Covering, GIN/GiST (PostgreSQL)
**Tuning por motor**: PostgreSQL VACUUM/ANALYZE, SQL Server Query Store, Oracle SQL Tuning Advisor
**Anti-patrones**: SELECT *, funciones en columnas indexadas, LIKE con wildcard al inicio

```mermaid
flowchart TD
    classDef core fill:#ef4444,color:#fff,stroke:#b91c1c
    classDef explain fill:#3b82f6,color:#fff,stroke:#1d4ed8
    classDef index fill:#10b981,color:#fff,stroke:#059669
    classDef tune fill:#f59e0b,color:#fff,stroke:#d97706
    
    Opt(("OPTIMIZACIÓN")):::core
    
    Opt -->|EXPLAIN| EXP[PostgreSQL<br/>EXPLAIN ANALYZE<br/>SQL Server<br/>Execution Plan<br/>Oracle<br/>EXPLAIN PLAN]:::explain
    Opt -->|Indexing| IDX[B-Tree<br/>Partial/Functional<br/>Composite<br/>Covering<br/>GIN/GiST]:::index
    Opt -->|Tuning| TUN[VACUUM/ANALYZE<br/>Query Store<br/>SQL Tuning Advisor]:::tune
    Opt -->|Anti-patrones| ANTI[SELECT *<br/>Funciones en índices<br/>LIKE %...%]
```

---

## ⚙️ 3. Procedimientos, Funciones y Automatización

### 3.1. Procedimientos, Funciones y Triggers - Resumen

**Stored Procedures**: PostgreSQL plpgsql, SQL Server T-SQL, Oracle PL/SQL, MariaDB/MySQL - con parámetros IN/OUT, control de flujo
**Functions**: Escalares (retornan valor), Table-Valued (retornan tabla), Inline - usables en consultas SQL
**Triggers**: BEFORE/AFTER/INSTEAD OF, por evento (INSERT/UPDATE/DELETE), auditoría automática
**Portabilidad**: Usar SQL estándar, abstraer diferencias, evitar funciones propietarias

```mermaid
flowchart TD
    classDef central fill:#2563eb,color:#fff,font-weight:bold,stroke:#1e40af
    classDef sp fill:#10b981,color:#fff,stroke:#059669
    classDef udf fill:#f59e0b,color:#fff,stroke:#d97706
    classDef trig fill:#ef4444,color:#fff,stroke:#b91c1c
    
    Prog(("PROGRAMABILIDAD")):::central
    
    Prog -->|Procedures| SP[plpgsql/T-SQL/PL/SQL<br/>Parámetros IN/OUT<br/>Control de flujo]:::sp
    Prog -->|Functions| UDF[Escalar<br/>Table-Valued<br/>Inline<br/>Usables en SQL]:::udf
    Prog -->|Triggers| TRG[BEFORE/AFTER<br/>INSERT/UPDATE/DELETE<br/>Auditoría]:::trig
    Prog -->|Portabilidad| PORT[SQL estándar<br/>Abstracción<br/>Testing]:::trig
```

### 3.2. Orquestación y Automatización - Resumen

**Nativos**: SQL Server Agent, Oracle DBMS_SCHEDULER, PostgreSQL pg_cron, MariaDB Event Scheduler
**Externos**: Airflow (workflows), dbt (transformación datos), Prefect, Kubernetes CronJobs
**Scripts**: Python APScheduler, Bash Cron, systemd timers, PowerShell Scheduled Tasks

```mermaid
flowchart LR
    classDef central fill:#2563eb,color:#fff,font-weight:bold,stroke:#1e40af
    classDef native fill:#10b981,color:#fff,stroke:#059669
    classDef external fill:#f59e0b,color:#fff,stroke:#d97706
    classDef script fill:#8b5cf6,color:#fff,stroke:#6d28d9
    
    Orq(("ORQUESTACIÓN")):::central
    
    Orq -->|Nativos| NAT[SQL Agent<br/>DBMS_SCHEDULER<br/>pg_cron<br/>Events]:::native
    Orq -->|Externos| EXT[Airflow<br/>dbt<br/>Prefect<br/>K8s CronJobs]:::external
    Orq -->|Scripts| SCR[Python<br/>Bash Cron<br/>systemd<br/>PowerShell]:::script
```

### 3.3. Auditoría y Logging - Resumen

**Auditoría**: PostgreSQL pg_audit, SQL Server Audit/CDC, Oracle FGA/Unified Auditing, triggers
**Logging**: Query logging, slow query log, error logging, extended events
**Centralización**: ELK Stack (Elasticsearch, Logstash, Kibana), Prometheus + Grafana, Splunk, CloudWatch

```mermaid
flowchart TD
    classDef central fill:#ef4444,color:#fff,stroke:#b91c1c
    classDef audit fill:#3b82f6,color:#fff,stroke:#1d4ed8
    classDef log fill:#10b981,color:#fff,stroke:#059669
    classDef centralize fill:#f59e0b,color:#fff,stroke:#d97706
    
    Sec(("AUDITORÍA Y LOGGING")):::central
    
    Sec -->|Auditoría| AUD[pg_audit<br/>SQL Audit/CDC<br/>Oracle FGA<br/>Triggers]:::audit
    Sec -->|Logging| LOG[Query log<br/>Slow query<br/>Error log<br/>Extended events]:::log
    Sec -->|Centralización| CEN[ELK Stack<br/>Prometheus+Grafana<br/>Splunk<br/>CloudWatch]:::centralize
```

### 3.4. Buenas Prácticas - Resumen

**Diseño**: Single Responsibility, nombres descriptivos, manejo de errores robusto, documentación
**Portabilidad**: SQL estándar, abstracción de diferencias, evitar vendor lock-in, testing
**Mantenimiento**: Version Control (Git), Code Review, Monitoring, Automated Testing
**Cuándo usar lógica en BD**: Validaciones complejas, cálculos complejos, auditoría, batch processing

```mermaid
flowchart TD
    classDef central fill:#2563eb,color:#fff,font-weight:bold,stroke:#1e40af
    classDef design fill:#10b981,color:#fff,stroke:#059669
    classDef portable fill:#f59e0b,color:#fff,stroke:#d97706
    classDef maintain fill:#8b5cf6,color:#fff,stroke:#6d28d9
    
    Best(("BUENAS PRÁCTICAS")):::central
    
    Best -->|Diseño| DES[Single Responsibility<br/>Nombres descriptivos<br/>Error handling<br/>Documentación]:::design
    Best -->|Portabilidad| POR[SQL estándar<br/>Abstracción<br/>No vendor lock-in<br/>Testing]:::portable
    Best -->|Mantenimiento| MAINT[Git<br/>Code Review<br/>Monitoring<br/>Automated Testing]:::maintain
```

---

## 📚 Notas sobre los Diagramas

- **Colores utilizados**:
  - Azul (#2563eb): Elementos centrales principales
  - Verde (#10b981): Elementos nativos o estándar
  - Naranja (#f59e0b): Elementos externos o de transformación
  - Rojo (#ef4444): Elementos críticos o de seguridad
  - Púrpura (#8b5cf6): Elementos híbridos o de scripting

- **Tipos de diagramas**:
  - `flowchart TD`: Diagramas de flujo top-down
  - `flowchart LR`: Diagramas de flujo left-right

- **Convenciones**:
  - Los nodos con bordes gruesos indican elementos principales
  - Las flechas etiquetadas indican relaciones específicas
  - Los nodos agrupados representan categorías relacionadas
