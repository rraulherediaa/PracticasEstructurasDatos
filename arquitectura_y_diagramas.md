# Arquitectura del Sistema: Diagramas y Flujos (Mermaid)

Este documento contiene la representación visual completa de la arquitectura de la base de datos, el flujo lógico de ejecución del Trigger de seguridad y la secuencia de operaciones del laboratorio de comercio electrónico.

---

## 1. Diagrama Entidad-Relación (DER) de la Base de Datos

Este diagrama representa el diseño físico y lógico de las **4 tablas** conectadas en la base de datos `empresa_ecommerce`. Ilustra las llaves primarias (`PK`), llaves foráneas (`FK`), tipos de datos y la cardinalidad de las relaciones.

```mermaid
erDiagram
    productos ||--o{ detalles_ventas : "se vende en"
    productos ||--o{ log_precios_productos : "audita cambios de precio"
    ventas ||--o{ detalles_ventas : "contiene"

    productos {
        int id_producto PK
        varchar nombre "Nombre del artículo"
        numeric precio "Precio base en catálogo (Auditable)"
    }

    log_precios_productos {
        int id_log PK "SERIAL Autoincremental"
        int id_producto FK "Referencia a productos"
        numeric precio_anterior "Precio previo al UPDATE"
        numeric precio_nuevo "Precio nuevo establecido"
        varchar usuario_cambio "Usuario de BD que hizo el cambio"
        timestamp fecha_cambio "Marca de tiempo exacta del servidor"
    }

    ventas {
        int id_venta PK
        int id_cliente "ID del comprador"
        date fecha "Fecha de la transacción"
        numeric total "Monto total de la compra"
        int id_sucursal "Identificador de la tienda física"
    }

    detalles_ventas {
        int id_detalle PK
        int id_venta FK "Enlace a la cabecera ventas"
        int id_producto FK "Enlace al catálogo productos"
        int cantidad "Unidades compradas"
        numeric precio_unitario "Precio cobrado al momento de la venta"
    }
```

---

## 2. Diagrama de Despliegue e Infraestructura (Docker)

Muestra cómo se mapea el entorno aislado de base de datos desde la máquina física del desarrollador hacia el contenedor Docker, aislando los servicios locales.

```mermaid
graph TD
    subgraph Maquina_Fisica [Máquina Host del Desarrollador]
        direction TB
        ClienteVisual[Gestor Visual de BD] -- Conexión TCP Puerto 5433 --> PuertoHost[Puerto Externo 5433]
        ServicioLocalPSQL[PostgreSQL Local de la Computadora] -- Ocupa el puerto --> PuertoHost5432[Puerto Local 5432]
    end

    subgraph Contenedor_Docker [Contenedor pg_auditoria]
        PuertoHost -- Mapeado a --> PuertoInterno[Puerto Interno de Docker 5432]
        PuertoInterno --> MotorPostgres[Motor PostgreSQL 16]
        subgraph Base_de_Datos [BD: empresa_ecommerce]
            MotorPostgres --> Tablas[4 Tablas del Sistema]
            MotorPostgres --> Trigger[Disparador de Auditoría]
        end
    end

    style Maquina_Fisica fill:#f9f9f9,stroke:#333,stroke-width:2px
    style Contenedor_Docker fill:#e1f5fe,stroke:#0288d1,stroke-width:2px
    style Base_de_Datos fill:#e8f5e9,stroke:#2e7d32,stroke-width:1px
```

---

## 3. Flujo de Ejecución Lógico del Trigger (Optimización)

Este diagrama de flujo detalla el comportamiento del disparador **`trg_auditar_precio`** ante una sentencia `UPDATE` de precio, demostrando el control de rendimiento a nivel de base de datos.

```mermaid
flowchart TD
    Inicio([Sentencia SQL UPDATE sobre la tabla 'productos']) --> CondicionColumna{¿La consulta modifica la columna 'precio'?}
    
    CondicionColumna -->|NO| FinSinHacerNada([Termina UPDATE sin disparar Trigger - Óptimo])
    
    CondicionColumna -->|SÍ| CondicionTrigger{¿NEW.precio != OLD.precio?}
    
    CondicionTrigger -->|"NO (Mismo Precio)"| FinTransaccion([Termina UPDATE - Omitido a nivel de motor])
    
    CondicionTrigger -->|"SÍ (Cambio Real)"| CargarPLpgSQL[Ejecutar Función fn_auditar_cambio_precio]
    
    CargarPLpgSQL --> InsertLog[Insertar fila en log_precios_productos]
    InsertLog --> CapturarMetadatos[Capturar CURRENT_USER y CURRENT_TIMESTAMP]
    CapturarMetadatos --> FinConLog([Transacción completada y auditada con éxito])

    style FinSinHacerNada fill:#ffebee,stroke:#c62828
    style FinTransaccion fill:#fff3e0,stroke:#ef6c00
    style FinConLog fill:#e8f5e9,stroke:#2e7d32
```

---

## 4. Secuencia Completa del Laboratorio

Representa el flujo de trabajo cronológico desde que se levanta el servidor hasta que se extraen los reportes finales de negocio.

```mermaid
sequenceDiagram
    autonumber
    actor Raul as "Desarrollador (Raúl)"
    participant Docker as "Servidor Docker (pg_auditoria)"
    participant BD as "Base de Datos (empresa_ecommerce)"
    participant Log as "Tabla log_precios_productos"

    Raul->>Docker: Iniciar contenedor mapeando puerto 5433:5432
    Docker-->>Raul: Contenedor corriendo y listo
    Raul->>BD: Ejecutar DDL (Crear 4 tablas y relaciones)
    BD-->>Raul: Tablas creadas con éxito
    Raul->>BD: Registrar Función y Trigger AFTER UPDATE OF precio
    BD-->>Raul: Mecanismo de auditoría instalado
    Raul->>BD: Poblar datos de prueba (INSERT productos, ventas y detalles)
    BD-->>Raul: 5 productos, 6 ventas y 10 detalles guardados
    
    Note over Raul, BD: Simulación de Auditoría
    Raul->>BD: UPDATE precio Laptop de 1200 a 1150
    BD->>Log: Disparar Trigger: Insertar auditoría del producto 1
    Log-->>BD: Log guardado
    BD-->>Raul: Fila de producto actualizada con éxito
    
    Raul->>BD: UPDATE precio Mouse a 50 (Mismo valor)
    Note over BD: Trigger evalúa la condición de precio y la rechaza
    BD-->>Raul: Fila de producto actualizada (Sin cambios en log)

    Note over Raul, BD: Consulta de Reportes
    Raul->>BD: SELECT * FROM log_precios_productos
    BD-->>Raul: Retorna 1 fila auditada
    Raul->>BD: Ejecutar consultas de negocio (VIPs, Facturas, Rankings)
    BD-->>Raul: Retorna reportes analíticos para capturas
```
