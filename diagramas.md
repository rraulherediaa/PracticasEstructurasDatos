# 🗺️ Diagramas Mermaid - Gestión Avanzada de Base de Datos II

Este archivo contiene todos los diagramas Mermaid utilizados en la documentación de la Microevaluación 2.

---

## 🏗️ 1. Administración Avanzada de SGBD

### 1.1. SGBD Modernos y Cloud - Resumen

```mermaid
flowchart TD
    Root["SGBD MODERNOS"]
    
    Root -->|Open Source| OSS["PostgreSQL, MariaDB, SQLite"]
    Root -->|Propietario| PROP["SQL Server, Oracle"]
    
    OSS -->|Cloud| CLOUD1["AWS Aurora, GCP Cloud SQL, Azure Postgres"]
    PROP -->|Cloud| CLOUD2["AWS RDS, GCP Cloud SQL, Azure SQL"]
    
    OSS -->|On-Premise| ON1["Self-hosted (Control total)"]
    PROP -->|On-Premise| ON2["Self-hosted (Control total)"]
```

### 1.2. Infraestructura Híbrida - Resumen

```mermaid
flowchart LR
    Main["INFRAESTRUCTURA"]
    
    Main -->|On-Premise| OP["Control total, CAPEX, Latencia"]
    Main -->|Cloud| CL["OPEX, Escalabilidad, Managed"]
    Main -->|Híbrido| HYB["Burst to cloud, DR, Data lake"]
```

### 1.3. Tuning y Rendimiento - Resumen

```mermaid
flowchart TD
    Tuning["TUNING"]
    
    Tuning -->|Memory| MEM["PostgreSQL: shared_buffers, SQL Server: max memory, Oracle: SGA_TARGET"]
    Tuning -->|CPU| CPU["PostgreSQL: parallel workers, SQL Server: MAXDOP, Oracle: parallelism"]
    Tuning -->|I/O| IO["PostgreSQL: random_page_cost, SQL Server: tempdb, MySQL: innodb_io_capacity"]
    Tuning -->|Monitoring| MON["pg_stat_statements, DMVs, AWR/ASH"]
```

### 1.4. Herramientas de Administración - Resumen

```mermaid
flowchart TD
    Tools["HERRAMIENTAS"]
    
    Tools -->|GUI| GUI["DBeaver, Azure Data Studio, pgAdmin, SQL Developer"]
    Tools -->|CLI| CLI["psql, sqlcmd, sqlplus, pg_dump, mysqldump"]
    Tools -->|Scripting| SCRIPT["Python, Bash, Ansible, Terraform"]
```

---

## 📊 2. Consultas SQL Avanzadas y Analítica de Datos

### 2.1. Subconsultas, CTE y Big Data - Resumen

```mermaid
flowchart TD
    Complex["PROCESAMIENTO"]
    
    Complex -->|Subconsultas| SUB["Correlacionadas, EXISTS, ANY/ALL"]
    Complex -->|CTE| CTE["Básicas, Múltiples, Recursivas"]
    Complex -->|Big Data| BIG["Partitioning, Sharding, Materialized Views"]
```

### 2.2. JOINs, Window Functions y Transformación - Resumen

```mermaid
flowchart LR
    Trans["TRANSFORMACIÓN"]
    
    Trans -->|JOINs| JOIN["INNER, LEFT, RIGHT, FULL, LATERAL"]
    Trans -->|Window| WIN["Agregación, Ranking, LAG/LEAD"]
    Trans -->|Set Ops| SET["UNION, INTERSECT, EXCEPT"]
    Trans -->|Pivot| PIV["PIVOT, UNPIVOT"]
```

### 2.3. Analítica para BI - Resumen

```mermaid
flowchart TD
    BI["ANALÍTICA BI"]
    
    BI -->|Agregaciones| AGG["ROLLUP, CUBE, GROUPING SETS"]
    BI -->|Advanced| ADV["Percentiles, Histogramas, Tendencias YoY"]
```

### 2.4. Optimización de Consultas - Resumen

```mermaid
flowchart TD
    Opt["OPTIMIZACIÓN"]
    
    Opt -->|EXPLAIN| EXP["PostgreSQL EXPLAIN, SQL Server Plan, Oracle Plan"]
    Opt -->|Indexing| IDX["B-Tree, Partial, Composite, Covering"]
    Opt -->|Tuning| TUN["VACUUM, Query Store, SQL Tuning Advisor"]
```

---

## ⚙️ 3. Procedimientos, Funciones y Automatización

### 3.1. Procedimientos, Funciones y Triggers - Resumen

```mermaid
flowchart TD
    Prog["PROGRAMABILIDAD"]
    
    Prog -->|Procedures| SP["plpgsql, T-SQL, PL/SQL"]
    Prog -->|Functions| UDF["Escalar, Table-Valued, Inline"]
    Prog -->|Triggers| TRG["BEFORE/AFTER, AUDITORÍA"]
```

### 3.2. Orquestación y Automatización - Resumen

```mermaid
flowchart LR
    Orq["ORQUESTACIÓN"]
    
    Orq -->|Nativos| NAT["SQL Agent, pg_cron, Events"]
    Orq -->|Externos| EXT["Airflow, dbt, Prefect"]
    Orq -->|Scripts| SCR["Python, Bash Cron, systemd"]
```

### 3.3. Auditoría y Logging - Resumen

```mermaid
flowchart TD
    Sec["AUDITORÍA Y LOGGING"]
    
    Sec -->|Auditoría| AUD["pg_audit, CDC, Triggers"]
    Sec -->|Logging| LOG["Slow query log, Extended events"]
    Sec -->|Centralización| CEN["ELK, Prometheus+Grafana, Splunk"]
```

### 3.4. Buenas Prácticas - Resumen

```mermaid
flowchart TD
    Best["BUENAS PRÁCTICAS"]
    
    Best -->|Diseño| DES["Single Responsibility, Nombres descriptivos"]
    Best -->|Portabilidad| POR["SQL estándar, Abstracción"]
    Best -->|Mantenimiento| MAINT["Git, Code Review, Monitoring"]
```
