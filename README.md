# 🚀 Enterprise Database Orchestration Lab: Multi-Engine Architecture

## 📋 Resumen Ejecutivo
Este laboratorio implementa una infraestructura de datos híbrida y orquestada, diseñada para simular entornos de producción de alta disponibilidad y persistencia. Se integra **MySQL 8.0** y **PostgreSQL 16** bajo un mismo stack de red virtualizada, permitiendo el aislamiento de procesos y la gestión centralizada de datos históricos a gran escala (Dataset Pagila).

---

## 🏗️ Especificaciones Técnicas del Entorno

### 💻 Stack de Software (Host)
- **Sistema Operativo:** CachyOS (Kernel optimizado para Linux).
- **Motor de Contenerización:** Docker Engine v24.x / v26.x.
- **Orquestador:** Docker Compose V2.
- **IDE de Desarrollo:** VS Code con Database Client Engine.

### 🔌 Configuración de Red y Puertos
| Servicio | Imagen | Puerto Host | Puerto Interno | Driver de Red |
| :--- | :--- | :--- | :--- | :--- |
| `mysql-compose` | `mysql:latest` | `3306` | `3306` | Bridge (Default) |
| `postgres` | `postgres:16` | `5432` | `5432` | Bridge (Default) |

---

## 🛠️ Fase 1: Despliegue de Infraestructura Declarativa

La orquestación se define mediante el principio de **Infrastructure as Code (IaC)**. El archivo `docker-compose.yml` gestiona no solo los ciclos de vida de los contenedores, sino también la persistencia física en el host.

```yaml
services:
  # Nodo A: MySQL (Northwind Database)
  mysql-compose:
    image: mysql:latest
    container_name: ucatec-mysql-compose
    restart: always
    environment:
      MYSQL_ROOT_PASSWORD: mariane2019
      MYSQL_DATABASE: northwind
    ports:
      - "3306:3306"
    volumes:
      - mysql-data:/var/lib/mysql

  # Nodo B: PostgreSQL (Pagila Database)
  postgres:
    image: postgres:16
    container_name: ucatec-postgres
    restart: always
    environment:
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: mariane2019
      POSTGRES_DB: pagila
    ports:
      - "5432:5432"
    volumes:
      - postgres-data:/var/lib/postgresql/data

volumes:
  mysql-data:
  postgres-data:
```

---

## 📊 Fase 2: Gestión de Datos e Inyección SQL (Pagila)

La restauración del dataset **Pagila** se realiza mediante un flujo de datos asíncrono. **Nota:** En este dataset, los apellidos siguen convenciones específicas (ej. 'GUINESS' con una sola 'N').

```bash
# Inyección de Estructura y Datos
docker exec -i ucatec-postgres psql -U postgres -d pagila < pagila-schema.sql
docker exec -i ucatec-postgres psql -U postgres -d pagila < pagila-data.sql
```

---

## 🔧 Fase 3: Troubleshooting y Optimización

| Problema Detectado | Diagnóstico Técnico | Resolución |
| :--- | :--- | :--- |
| **ETIMEDOUT** | Uso de driver MySQL en puerto 5432. | Cambio a Driver nativo de PostgreSQL. |
| **Syntax Error** | Comillas curvas (‘ ’) y errores de tipado. | Uso estricto de comillas rectas (' ') y normalización UPPER. |
| **Data Mismatch** | Búsqueda de 'GUINNESS' (incorrecto) vs 'GUINESS'. | Validación con datos reales del esquema actor. |

---

## 💻 Fase 4: Lógica de Negocio PL/pgSQL (Código Corregido)

### A. Función: Conteo Robusto de Actores
Implementa una comparación insensible a mayúsculas/minúsculas mediante `UPPER` simétrico.

```sql
CREATE OR REPLACE FUNCTION contar_actores_por_apellido(p_apellido VARCHAR)
RETURNS INTEGER AS $$
DECLARE
    v_total INTEGER;
BEGIN
    SELECT COUNT(*) INTO v_total 
    FROM actor 
    WHERE UPPER(last_name) = UPPER(p_apellido);
    
    RETURN v_total;
END;
$$ LANGUAGE plpgsql;
```

### B. Procedimiento: Inserción Transaccional Normalizada
Garantiza que toda entrada sea convertida a mayúsculas antes de la persistencia física.

```sql
CREATE OR REPLACE PROCEDURE insertar_nuevo_actor(p_nombre VARCHAR, p_apellido VARCHAR)
AS $$
BEGIN
    INSERT INTO actor (first_name, last_name, last_update)
    VALUES (UPPER(p_nombre), UPPER(p_apellido), NOW());
    
    RAISE NOTICE 'Transacción Exitosa: Actor % % registrado.', p_nombre, p_apellido;
END;
$$ LANGUAGE plpgsql;
```

### C. Queries de Validación Final (Paso a Paso)

```sql
-- 1. Prueba de Función con dato real (PENELOPE GUINESS)
SELECT contar_actores_por_apellido('guiness') AS total_guiness;

-- 2. Ejecución del Procedimiento
CALL insertar_nuevo_actor('RAUL', 'ROCHA');

-- 3. Auditoría de Datos Insertados
SELECT * FROM actor WHERE first_name = 'RAUL' AND last_name = 'ROCHA';
```

---

## 📈 Conclusiones
El sistema garantiza la integridad de los datos mediante lógica procedural robusta y un entorno orquestado altamente disponible.

> **Ver Diagramas Detallados:** [DIAGRAM.md](./DIAGRAM.md)
