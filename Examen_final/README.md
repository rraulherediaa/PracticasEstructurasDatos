# Proyecto Examen Final — Infraestructura Híbrida Simplificada (SQL + NoSQL)

Este repositorio contiene la resolución del proyecto del Examen Final de la materia **SIS220: Gestión y Manejo de Base de Datos II**. 

El sistema implementa el despliegue, la configuración y la inicialización automatizada de una infraestructura multi-motor que combina bases de datos relacionales y no relacionales bajo un único entorno de orquestación.

---

## 🛠️ Requisitos Previos

* **Docker Engine** instalado y activo en el sistema.
* **Docker Compose** configurado.
* **Bash Shell** (Linux/macOS) para ejecutar scripts auxiliares.

---

## 📁 Estructura del Proyecto

```text
Examen_final/
├── docker-compose.yml       # Orquestador multi-contenedor (Postgres + Mongo)
├── iniciar.sh               # Script ejecutable de inicio automático (Docker + Compose)
├── detener.sh               # Script ejecutable de apagado y limpieza
├── README.md                # Este manual técnico y justificativo
├── postgres-init/
│   └── init.sql             # Esquema y datos semilla relacionales (PostgreSQL)
└── mongo-init/
    └── init.js              # Datos semilla documentales (MongoDB JS)
```

---

## 🧠 1. Justificación de la Arquitectura y Decisiones de Diseño

### 1.1 ¿Por qué una arquitectura híbrida (PostgreSQL + MongoDB)?
En el desarrollo de software corporativo moderno, ningún motor es óptimo para todas las tareas. Combinamos ambos para separar responsabilidades lógicas:
* **PostgreSQL (OLTP - Transaccional):** Funciona como el "cajero" del negocio. Su modelo relacional normalizado y su cumplimiento del estándar **ACID** garantizan que cada inserción, modificación o referencia de llave foránea sea 100% segura e consistente. Es ideal para registrar operaciones diarias sin riesgo de pérdida de datos.
* **MongoDB (OLAP - Analítico/Data Lake):** Funciona como el "contador" del negocio. Almacena registros analíticos e históricos de forma desnormalizada en documentos JSON/BSON. Permite ejecutar agrupaciones y consultas analíticas masivas a gran velocidad al evitar cruces de tablas complejos.
* **Analogía sencilla:** Si le pidiéramos al cajero de una tienda que haga la contabilidad mensual mientras cobra en la caja, la fila de clientes se detendría. Al usar ambos motores, el cajero (PostgreSQL) atiende rápido al cliente, y el contador (MongoDB) hace sus reportes analíticos sin interferir en la caja de cobro.

---

### 1.2 ¿Por qué NO usamos un ORM o un ODM?
En lugar de herramientas automáticas de mapeo de objetos (como SQLAlchemy o MongoEngine), nos conectamos de forma directa usando los drivers oficiales (`psycopg2` y `pymongo`). Esta decisión responde a:
* **Demostración de dominio técnico:** En el contexto académico de la materia, es fundamental demostrar que sabemos escribir, estructurar y optimizar manualmente sentencias SQL puras y pipelines de agregación NoSQL nativos, sin delegar esta tarea al código autogenerado por un ORM.
* **Eficiencia y rendimiento:** Los ORM añaden una capa intermedia de traducción que genera latencia ("overhead"). Al usar drivers directos, enviamos consultas nativas que aprovechan al máximo el rendimiento de los motores.

---

### 1.3 Justificación en el uso de `INNER JOIN`
* **¿Para qué sirve?** El `INNER JOIN` permite combinar registros de múltiples tablas asociándolas mediante sus columnas en común (Primary Keys y Foreign Keys), retornando únicamente aquellas filas que tengan coincidencia en ambas tablas.
* **¿Por qué no usamos otros JOINs?** En las tablas de `departamentos` y `empleados`, cada empleado pertenece obligatoriamente a un departamento debido a la llave foránea `id_depto INT NOT NULL`. Un `LEFT JOIN` o `RIGHT JOIN` traería registros con valores nulos (`NULL`) si existieran entidades sin asociar (por ejemplo, departamentos sin personal). El `INNER JOIN` garantiza la obtención de datos completos y con integridad referencial, listos para análisis.

---

### 1.4 ¿Por qué usamos Docker Compose?
Docker Compose nos ayuda a:
* **Portabilidad:** Permite levantar exactamente el mismo entorno en cualquier computadora (Windows, Mac o Linux) sin necesidad de instalar o configurar localmente los servicios de PostgreSQL o MongoDB.
* **Automatización:** Ejecuta en secuencia los scripts de inicialización SQL (`postgres-init/`) y JavaScript (`mongo-init/`) de forma transparente en el primer arranque de los contenedores.

---

### 1.5 ¿Por qué creamos los scripts de automatización (iniciar.sh y detener.sh)?
Creamos estos dos scripts para facilitar la gestión del entorno y prevenir problemas de red/puertos en Linux:
* **iniciar.sh (Arranque inteligente):** En muchos sistemas de escritorio (como Arch Linux), el servicio del demonio de Docker se mantiene inactivo en segundo plano para ahorrar recursos de CPU y RAM. Este script verifica si el servicio de Docker está apagado y, de ser así, lo inicia automáticamente (solicitando la contraseña `sudo` en tu terminal) antes de levantar los contenedores con `docker compose up -d`. Esto previene el clásico error de socket inaccesible de Docker.
* **detener.sh (Apagado y Limpieza de Recursos):** Detiene los contenedores de forma limpia (`docker compose down`). Esto asegura que no queden procesos huérfanos ocupando puertos importantes (`5433` o `27018`) ni consumiendo RAM en segundo plano después de finalizar tus pruebas.

---

## 🚀 2. Instrucciones de Despliegue y Arranque

1. Accede a la carpeta del proyecto:
   ```bash
   cd Examen_final
   ```

2. Ejecuta el script de inicialización automatizada:
   ```bash
   ./iniciar.sh
   ```
   *(El script activará el servicio de Docker en caso de estar apagado y levantará los contenedores en segundo plano. Requiere tu contraseña de sudo en terminal).*

3. Comprueba que los contenedores estén activos:
   ```bash
   docker compose ps
   ```

---

## 🐘 3. Verificación de PostgreSQL (Relacional)

Para verificar que las tablas relacionales fueron creadas y pobladas con éxito en PostgreSQL, conéctate al contenedor e ingresa al cliente `psql`:

```bash
docker exec -it examen-postgres psql -U postgres -d empresa_db
```

### Consultas de prueba a ejecutar en `psql`:

* **Listar las tablas del esquema (debe mostrar departamentos y empleados):**
  ```sql
  \dt
  ```
  **Salida esperada:**
  ```text
               List of relations
   Schema |     Name      | Type  |  Owner   
  --------+---------------+-------+----------
   public | departamentos | table | postgres
   public | empleados     | table | postgres
  (2 rows)
  ```

* **Visualizar los departamentos registrados:**
  ```sql
  SELECT * FROM departamentos;
  ```
  **Salida esperada:**
  ```text
   id_depto |         nombre          |     ubicacion     
  ----------+-------------------------+-------------------
          1 | Tecnología y Sistemas   | Bloque A - Piso 3
          2 | Recursos Humanos        | Bloque B - Piso 1
          3 | Marketing y Ventas      | Bloque A - Piso 2
          4 | Finanzas y Contabilidad | Bloque B - Piso 2
  (4 rows)
  ```

* **Visualizar los empleados registrados:**
  ```sql
  SELECT * FROM empleados;
  ```
  **Salida esperada:**
  ```text
   id_empleado |      nombre       |           puesto            | salario | id_depto | fecha_contratacion 
  -------------+-------------------+-----------------------------+---------+----------+--------------------
             1 | Carlos Mendoza    | Desarrollador Senior        | 8500.00 |        1 | 2024-01-15
             2 | Ana Gomez         | Especialista de Selección   | 4500.00 |        2 | 2024-03-10
             3 | David Mamani      | Administrador de Servidores | 7000.00 |        1 | 2024-06-01
             4 | Maria Flores      | Directora de Cuentas        | 6000.00 |        3 | 2023-11-20
             5 | Jose Perez        | Analista Financiero         | 5500.00 |        4 | 2024-02-18
             6 | Lucia Choque      | Diseñadora UX/UI            | 5000.00 |        3 | 2024-05-12
             7 | Roberto Gutierrez | Soporte Técnico             | 3800.00 |        1 | 2025-01-05
  (7 rows)
  ```

* **Ejecutar un `INNER JOIN` analítico para comprobar las relaciones de llaves foráneas:**
  ```sql
  SELECT e.nombre, e.puesto, e.salario, d.nombre AS departamento, d.ubicacion 
  FROM empleados e 
  INNER JOIN departamentos d ON e.id_depto = d.id_depto;
  ```
  **Salida esperada:**
  ```text
        nombre       |           puesto            | salario |      departamento       |     ubicacion     
  -------------------+-----------------------------+---------+-------------------------+-------------------
   Carlos Mendoza    | Desarrollador Senior        | 8500.00 | Tecnología y Sistemas   | Bloque A - Piso 3
   Ana Gomez         | Especialista de Selección   | 4500.00 | Recursos Humanos        | Bloque B - Piso 1
   David Mamani      | Administrador de Servidores | 7000.00 | Tecnología y Sistemas   | Bloque A - Piso 3
   Maria Flores      | Directora de Cuentas        | 6000.00 | Marketing y Ventas      | Bloque A - Piso 2
   Jose Perez        | Analista Financiero         | 5500.00 | Finanzas y Contabilidad | Bloque B - Piso 2
   Lucia Choque      | Diseñadora UX/UI            | 5000.00 | Marketing y Ventas      | Bloque A - Piso 2
   Roberto Gutierrez | Soporte Técnico             | 3800.00 | Tecnología y Sistemas   | Bloque A - Piso 3
  (7 rows)
  ```

* **Salir del cliente psql:**
  ```sql
  \q
  ```

---

## 🍃 4. Verificación de MongoDB (No Relacional)

Para comprobar que MongoDB se ha inicializado con los **10 registros analíticos**, conéctate al Shell de MongoDB (`mongosh`) dentro del contenedor:

```bash
docker exec -it examen-mongo mongosh -u admin -p examenpassword2026 --authenticationDatabase admin
```

### Consultas de prueba a ejecutar en `mongosh`:

* **Cambiar de contexto a la base de datos analítica:**
  ```javascript
  use analitica_db
  ```
  **Salida esperada:**
  ```text
  switched to db analitica_db
  ```

* **Listar las colecciones disponibles:**
  ```javascript
  show collections
  ```
  **Salida esperada (debe listar la colección de logs):**
  ```text
  logs_accesos
  ```

* **Contar el número de documentos cargados (debe retornar exactamente 10):**
  ```javascript
  db.logs_accesos.countDocuments()
  ```
  **Salida esperada:**
  ```text
  10
  ```

* **Visualizar los 10 registros cargados en formato JSON:**
  ```javascript
  db.logs_accesos.find().pretty()
  ```
  **Salida esperada (Muestra los documentos JSON en la colección):**
  ```json
  [
    {
      "_id": ObjectId("6a3c6bfe2e4e6dca159df8a3"),
      "id_log": 1,
      "timestamp": "2026-06-24T10:15:30Z",
      "ip_address": "192.168.1.105",
      "usuario": "carlos.mendoza",
      "navegador": "Zen Browser (Firefox Gecko)",
      "accion": "Login exitoso",
      "detalles": { "modulo": "Autenticación", "duracion_ms": 120 }
    },
    {
      "_id": ObjectId("6a3c6bfe2e4e6dca159df8a4"),
      "id_log": 2,
      "timestamp": "2026-06-24T10:17:12Z",
      "ip_address": "192.168.1.105",
      "usuario": "carlos.mendoza",
      "navegador": "Zen Browser (Firefox Gecko)",
      "accion": "Consulta de clientes",
      "detalles": { "modulo": "Ventas", "filtro_aplicado": "ciudad: Santa Cruz" }
    },
    {
      "_id": ObjectId("6a3c6bfe2e4e6dca159df8a5"),
      "id_log": 3,
      "timestamp": "2026-06-24T10:20:45Z",
      "ip_address": "200.58.120.44",
      "usuario": "david.mamani",
      "navegador": "Google Chrome (Blink Engine)",
      "accion": "Modificación de stock",
      "detalles": { "modulo": "Inventario", "id_producto_modificado": 12, "stock_anterior": 40, "stock_nuevo": 35 }
    },
    {
      "_id": ObjectId("6a3c6bfe2e4e6dca159df8a6"),
      "id_log": 4,
      "timestamp": "2026-06-24T10:22:10Z",
      "ip_address": "192.168.1.112",
      "usuario": "ana.gomez",
      "navegador": "Safari (WebKit Engine)",
      "accion": "Ver candidatos",
      "detalles": { "modulo": "Recursos Humanos", "seccion": "Búsquedas activas" }
    },
    {
      "_id": ObjectId("6a3c6bfe2e4e6dca159df8a7"),
      "id_log": 5,
      "timestamp": "2026-06-24T10:25:01Z",
      "ip_address": "200.58.120.44",
      "usuario": "david.mamani",
      "navegador": "Google Chrome (Blink Engine)",
      "accion": "Consulta de auditoría",
      "detalles": { "modulo": "Seguridad", "tabla_consultada": "log_auditoria" }
    },
    {
      "_id": ObjectId("6a3c6bfe2e4e6dca159df8a8"),
      "id_log": 6,
      "timestamp": "2026-06-24T10:30:15Z",
      "ip_address": "192.168.1.125",
      "usuario": "maria.flores",
      "navegador": "Google Chrome (Blink Engine)",
      "accion": "Generar Reporte BI",
      "detalles": { "modulo": "Marketing", "tipo_grafico": "Ingresos por Categoría" }
    },
    {
      "_id": ObjectId("6a3c6bfe2e4e6dca159df8a9"),
      "id_log": 7,
      "timestamp": "2026-06-24T10:35:40Z",
      "ip_address": "192.168.1.105",
      "usuario": "carlos.mendoza",
      "navegador": "Zen Browser (Firefox Gecko)",
      "accion": "Simulación de Venta",
      "detalles": { "modulo": "E-Commerce", "monto_simulado": 450.5 }
    },
    {
      "_id": ObjectId("6a3c6bfe2e4e6dca159df8aa"),
      "id_log": 8,
      "timestamp": "2026-06-24T10:40:02Z",
      "ip_address": "192.168.1.125",
      "usuario": "maria.flores",
      "navegador": "Google Chrome (Blink Engine)",
      "accion": "Exportar Excel",
      "detalles": { "modulo": "Ventas", "filas_exportadas": 32 }
    },
    {
      "_id": ObjectId("6a3c6bfe2e4e6dca159df8ab"),
      "id_log": 9,
      "timestamp": "2026-06-24T10:45:18Z",
      "ip_address": "192.168.1.112",
      "usuario": "ana.gomez",
      "navegador": "Safari (WebKit Engine)",
      "accion": "Actualizar perfil",
      "detalles": { "modulo": "Recursos Humanos", "campo_modificado": "email" }
    },
    {
      "_id": ObjectId("6a3c6bfe2e4e6dca159df8ac"),
      "id_log": 10,
      "timestamp": "2026-06-24T10:50:33Z",
      "ip_address": "186.2.33.90",
      "usuario": "jose.perez",
      "navegador": "Microsoft Edge (Blink Engine)",
      "accion": "Cierre de Caja",
      "detalles": { "modulo": "Finanzas", "monto_total_cierre": 12500.00 }
    }
  ]
  ```

* **Filtrar y visualizar registros específicos por usuario (ej. accesos de carlos.mendoza):**
  ```javascript
  db.logs_accesos.find({ usuario: "carlos.mendoza" })
  ```
  **Salida esperada (Muestra los 3 registros del usuario desnormalizados):**
  ```json
  [
    {
      "_id": ObjectId("6a3c6bfe2e4e6dca159df8a3"),
      "id_log": 1,
      "timestamp": "2026-06-24T10:15:30Z",
      "ip_address": "192.168.1.105",
      "usuario": "carlos.mendoza",
      "navegador": "Zen Browser (Firefox Gecko)",
      "accion": "Login exitoso",
      "detalles": {
        "modulo": "Autenticación",
        "duracion_ms": 120
      }
    },
    {
      "_id": ObjectId("6a3c6bfe2e4e6dca159df8a4"),
      "id_log": 2,
      "timestamp": "2026-06-24T10:17:12Z",
      "ip_address": "192.168.1.105",
      "usuario": "carlos.mendoza",
      "navegador": "Zen Browser (Firefox Gecko)",
      "accion": "Consulta de clientes",
      "detalles": {
        "modulo": "Ventas",
        "filtro_aplicado": "ciudad: Santa Cruz"
      }
    },
    {
      "_id": ObjectId("6a3c6bfe2e4e6dca159df8a9"),
      "id_log": 7,
      "timestamp": "2026-06-24T10:35:40Z",
      "ip_address": "192.168.1.105",
      "usuario": "carlos.mendoza",
      "navegador": "Zen Browser (Firefox Gecko)",
      "accion": "Simulación de Venta",
      "detalles": {
        "modulo": "E-Commerce",
        "monto_simulado": 450.5
      }
    }
  ]
  ```

* **Salir de mongosh:**
  ```javascript
  exit
  ```

---

## 🧹 5. Detener la Infraestructura

Cuando hayas terminado con las verificaciones y pruebas, apaga y limpia los recursos del contenedor ejecutando el script auxiliar de limpieza:

```bash
./detener.sh
```
