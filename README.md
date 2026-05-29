# Guía de Laboratorio: Automatización, Triggers y Auditoría de Precios

Este repositorio contiene la documentación técnica, instrucciones de despliegue paso a paso y los **resultados reales de ejecución** de un sistema de auditoría automatizada de precios junto al modelo de transacciones comerciales para una empresa de comercio electrónico internacional.

La solución ha sido diseñada bajo estrictos estándares de integridad de datos, optimización del rendimiento y portabilidad en **PostgreSQL**.

---

## 1. Despliegue del Entorno de Base de Datos en Docker

Para asegurar un entorno limpio e independiente de la máquina host, desplegamos una base de datos centralizada de **PostgreSQL** a través de Docker.

### Paso 1.1: Iniciar el Servicio de Docker
Inicie el motor de Docker en su terminal principal de Linux:
```bash
sudo systemctl start docker
```

### Paso 1.2: Crear y Correr el Contenedor PostgreSQL
Levantamos un contenedor mapeando el puerto interno `5432` al puerto externo de la máquina host **`5433`** (para evitar colisiones con servicios locales de PostgreSQL en el puerto `5432`) y creando una base de datos específica llamada `empresa_ecommerce`:

```bash
sudo docker run -d \
  --name pg_auditoria \
  -e POSTGRES_PASSWORD=mi_clave_secreta \
  -e POSTGRES_DB=empresa_ecommerce \
  -p 5433:5432 \
  postgres:latest
```

---

## 2. Arquitectura de Base de Datos y Creación de Tablas (DDL)

El diseño cuenta con **4 tablas** organizadas en tres niveles lógicos (Maestra, Transaccional y Seguridad). El orden de creación de las tablas es sumamente importante para respetar las restricciones de integridad referencial (`FOREIGN KEY`).

Conéctese a su base de datos PostgreSQL mediante su cliente preferido (usando el host `127.0.0.1`, puerto `5433`, usuario `postgres`, base de datos `empresa_ecommerce` y contraseña `mi_clave_secreta`) y ejecute el siguiente script DDL:

```sql
-- 1. TABLA MAESTRA: Inventario de Productos
CREATE TABLE productos (
    id_producto INT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    precio NUMERIC(10, 2) NOT NULL
);

-- 2. TABLA DE SEGURIDAD: Auditoría de Modificaciones de Precios
CREATE TABLE log_precios_productos (
    id_log SERIAL PRIMARY KEY,
    id_producto INT NOT NULL,
    precio_anterior NUMERIC(10, 2) NOT NULL,
    precio_nuevo NUMERIC(10, 2) NOT NULL,
    usuario_cambio VARCHAR(100) NOT NULL,
    fecha_cambio TIMESTAMP NOT NULL,
    CONSTRAINT fk_log_producto FOREIGN KEY (id_producto) REFERENCES productos(id_producto) ON DELETE CASCADE
);

-- 3. TABLA TRANSACCIONAL (Cabecera): Ventas Generales
CREATE TABLE ventas (
    id_venta INT PRIMARY KEY,
    id_cliente INT NOT NULL,
    fecha DATE NOT NULL,
    total NUMERIC(10, 2) NOT NULL,
    id_sucursal INT NOT NULL
);

-- 4. TABLA TRANSACCIONAL (Detalle): Líneas de Venta por Producto
CREATE TABLE detalles_ventas (
    id_detalle INT PRIMARY KEY,
    id_venta INT NOT NULL,
    id_producto INT NOT NULL,
    cantidad INT NOT NULL,
    precio_unitario NUMERIC(10, 2) NOT NULL,
    CONSTRAINT fk_detalle_venta FOREIGN KEY (id_venta) REFERENCES ventas(id_venta) ON DELETE CASCADE,
    CONSTRAINT fk_detalle_producto FOREIGN KEY (id_producto) REFERENCES productos(id_producto) ON DELETE CASCADE
);
```

#### Resultado esperado de la ejecución DDL:
Al ejecutar el bloque anterior, el cliente de base de datos reportará la creación exitosa de las 4 entidades:
* `CREATE TABLE productos` -> *Query returned successfully in 8ms.*
* `CREATE TABLE log_precios_productos` -> *Query returned successfully in 9ms.*
* `CREATE TABLE ventas` -> *Query returned successfully in 7ms.*
* `CREATE TABLE detalles_ventas` -> *Query returned successfully in 11ms.*

---

## 3. Implementación del Trigger y Función Disparadora de Auditoría

Para garantizar el cumplimiento de las políticas de resiliencia y seguridad de la información, se requiere registrar cualquier cambio en los precios de los productos de catálogo.

### Paso 3.1: Crear la Función Disparadora
Esta función lee los estados previos (`OLD`) e internos posteriores (`NEW`) de la fila que está siendo modificada:

```sql
CREATE OR REPLACE FUNCTION fn_auditar_cambio_precio()
RETURNS TRIGGER AS $$
BEGIN
    INSERT INTO log_precios_productos (
        id_producto, 
        precio_anterior, 
        precio_nuevo, 
        usuario_cambio, 
        fecha_cambio
    )
    VALUES (
        OLD.id_producto,
        OLD.precio,
        NEW.precio,
        CURRENT_USER,       -- Captura el usuario real del motor que realizó el UPDATE
        CURRENT_TIMESTAMP   -- Marca de tiempo inmutable del servidor de base de datos
    );
    
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;
```

### Paso 3.2: Crear el Trigger con Ajuste de Rendimiento
Para evitar degradar la velocidad del servidor de base de datos en entornos transaccionales de alto volumen, el trigger implementa dos optimizaciones cruciales:
1. `AFTER UPDATE OF precio`: Solo se dispara si la columna `precio` fue afectada en la sentencia SQL.
2. Cláusula `WHEN`: El motor evalúa la diferencia de valor *antes* de cargar el entorno de ejecución PL/pgSQL. Si no hay diferencia real en el precio, el trigger se detiene inmediatamente sin consumir recursos del servidor.

```sql
CREATE TRIGGER trg_auditar_precio
AFTER UPDATE OF precio ON productos
FOR EACH ROW
WHEN (NEW.precio IS DISTINCT FROM OLD.precio) -- Condición estricta de cambio real
EXECUTE FUNCTION fn_auditar_cambio_precio();
```

---

## 4. Carga de Datos y Pruebas Prácticas

### Paso 4.1: Población de Datos Iniciales (Inventario y Transacciones)
Cargue los catálogos y registre las primeras operaciones de venta:

```sql
-- Insertar Productos en Catálogo
INSERT INTO productos (id_producto, nombre, precio) VALUES
(1, 'Laptop Asus ROG', 1200.00),
(2, 'Mouse Logitech G502', 50.00),
(3, 'Teclado Mecánico Razer BlackWidow', 120.00),
(4, 'Monitor Gamer Asus 27"', 350.00),
(5, 'Auriculares HyperX Cloud II', 80.00);

-- Registrar Ventas (Cabecera)
INSERT INTO ventas (id_venta, id_cliente, fecha, total, id_sucursal) VALUES
(1, 101, CURRENT_DATE, 1200.00, 10),
(2, 102, CURRENT_DATE, 200.00, 10),
(3, 103, CURRENT_DATE, 470.00, 20),
(4, 104, CURRENT_DATE - 1, 80.00, 20),
(5, 105, CURRENT_DATE, 430.00, 10),
(6, 106, CURRENT_DATE, 170.00, 20);

-- Registrar Detalles de Venta
INSERT INTO detalles_ventas (id_detalle, id_venta, id_producto, cantidad, precio_unitario) VALUES
(1, 1, 1, 1, 1200.00),
(2, 2, 5, 1, 80.00),
(3, 2, 3, 1, 120.00),
(4, 3, 4, 1, 350.00),
(5, 3, 3, 1, 120.00),
(6, 4, 5, 1, 80.00),
(7, 5, 4, 1, 350.00),
(8, 5, 5, 1, 80.00),
(9, 6, 3, 1, 120.00),
(10, 6, 2, 1, 50.00);
```

#### Salida esperada en la tabla `productos` despues de la carga (`SELECT * FROM productos;`):
| id_producto | nombre | precio |
| :--- | :--- | :--- |
| 1 | Laptop Asus ROG | 1200.00 |
| 2 | Mouse Logitech G502 | 50.00 |
| 3 | Teclado Mecánico Razer BlackWidow | 120.00 |
| 4 | Monitor Gamer Asus 27" | 350.00 |
| 5 | Auriculares HyperX Cloud II | 80.00 |

---

### Paso 4.2: Simulación y Comprobación del Trigger de Auditoría

Ejecutamos dos operaciones de actualización (`UPDATE`) para verificar que el disparador únicamente actúe cuando hay un cambio de valor real.

1. **Caso A (Cambio de Precio Real):** Modificamos el precio de catálogo de la Laptop de `1200.00` a `1150.00`.
   ```sql
   UPDATE productos SET precio = 1150.00 WHERE id_producto = 1;
   ```
   * *Resultado:* `UPDATE 1` -> *Fila actualizada. El Trigger se dispara al haber diferencia de valor.*

2. **Caso B (Actualización sin cambio de precio):** Intentamos actualizar el precio del Mouse Logitech a `50.00` (que ya es su precio actual).
   ```sql
   UPDATE productos SET precio = 50.00 WHERE id_producto = 2;
   ```
   * *Resultado:* `UPDATE 1` -> *Fila actualizada. Sin embargo, el Trigger NO se dispara debido a la condición `WHEN (NEW.precio IS DISTINCT FROM OLD.precio)`.*

3. **Verificación de Auditoría:** Consultamos el registro histórico de logs.
   ```sql
   SELECT * FROM log_precios_productos;
   ```

#### Resultado de Salida Real de la Auditoría:
| id_log | id_producto | precio_anterior | precio_nuevo | usuario_cambio | fecha_cambio |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **1** | **1** | **1200.00** | **1150.00** | `postgres` | `2026-05-28 19:38:54` |

* **Conclusión de Seguridad:** Se demuestra que la tabla contiene **únicamente 1 registro** correspondiente al Caso A. El Trigger filtró de forma inteligente el caso B, previniendo el crecimiento excesivo e inútil de la tabla de auditorías.

---

## 5. Consultas Analíticas y de Negocio (Resultados Reales de Ejecución)

A continuación, se listan consultas analíticas complejas útiles para la toma de decisiones y reportes de negocio, junto con las tablas de resultados exactos que arroja su ejecución en base a los datos poblados:

### Consulta A: Reporte de Clientes VIP (Volumen de compra)
Identifica qué clientes han generado más ingresos acumulados en la tienda y calcula su ticket promedio de compra.
```sql
SELECT 
    id_cliente,
    COUNT(id_venta) AS total_compras,
    SUM(total) AS monto_total_gastado,
    ROUND(AVG(total), 2) AS ticket_promedio
FROM ventas
GROUP BY id_cliente
ORDER BY monto_total_gastado DESC;
```

#### Salida de Ejecución A:
| id_cliente | total_compras | monto_total_gastado | ticket_promedio |
| :--- | :--- | :--- | :--- |
| **101** | 1 | **1200.00** | 1200.00 |
| **103** | 1 | **470.00** | 470.00 |
| **105** | 1 | **430.00** | 430.00 |
| **102** | 1 | **200.00** | 200.00 |
| **106** | 1 | **170.00** | 170.00 |
| **104** | 1 | **80.00** | 80.00 |

---

### Consulta B: Reconstrucción e Impresión de Facturas (INNER JOIN de 3 Tablas)
Asocia la cabecera y el detalle de una compra específica (ej. Venta #5) para formatear la factura impresa del cliente detallando cada artículo comprado.
```sql
SELECT 
    v.id_venta,
    v.fecha,
    v.id_cliente,
    v.id_sucursal AS sucursal,
    dv.id_detalle,
    p.nombre AS articulo,
    dv.cantidad,
    dv.precio_unitario,
    (dv.cantidad * dv.precio_unitario) AS importe_por_articulo
FROM ventas v
INNER JOIN detalles_ventas dv ON v.id_venta = dv.id_venta
INNER JOIN productos p ON dv.id_producto = p.id_producto
WHERE v.id_venta = 5
ORDER BY dv.id_detalle;
```

#### Salida de Ejecución B:
| id_venta | fecha | id_cliente | sucursal | id_detalle | articulo | cantidad | precio_unitario | importe_por_articulo |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| **5** | `2026-05-28` | 105 | 10 | **7** | Monitor Gamer Asus 27" | 1 | 350.00 | **350.00** |
| **5** | `2026-05-28` | 105 | 10 | **8** | Auriculares HyperX Cloud II | 1 | 80.00 | **80.00** |

---

### Consulta C: Ranking de Productos más Vendidos
Analiza la demanda de inventario sumando las unidades físicas vendidas por artículo y los ingresos reales acumulados que estas unidades representaron para el comercio.
```sql
SELECT 
    p.id_producto,
    p.nombre AS producto,
    SUM(dv.cantidad) AS unidades_totales_vendidas,
    SUM(dv.cantidad * dv.precio_unitario) AS ingresos_totales_generados
FROM detalles_ventas dv
INNER JOIN productos p ON dv.id_producto = p.id_producto
GROUP BY p.id_producto, p.nombre
ORDER BY unidades_totales_vendidas DESC;
```

#### Salida de Ejecución C:
| id_producto | producto | unidades_totales_vendidas | ingresos_totales_generados |
| :--- | :--- | :--- | :--- |
| **3** | Teclado Mecánico Razer BlackWidow | **3** | 360.00 |
| **5** | Auriculares HyperX Cloud II | **3** | 240.00 |
| **4** | Monitor Gamer Asus 27" | **2** | 700.00 |
| **1** | Laptop Asus ROG | **1** | 1200.00 |
| **2** | Mouse Logitech G502 | **1** | 50.00 |
