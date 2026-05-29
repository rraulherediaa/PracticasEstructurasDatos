-- ============================================================================
-- REQUERIMIENTO: AUTOMATIZACIÓN, TRIGGERS Y AUDITORÍA
-- Materia: Gestión y Manejo de Base de Datos II

-- ============================================================================
-- Este script proporciona la solución completa tanto en PostgreSQL como en
-- SQL Server (T-SQL) siguiendo estándares de optimización, control de rendimiento
-- y portabilidad, asegurando que solo se registren cambios reales de precio.
-- ============================================================================

-- ============================================================================
-- SECCIÓN 1: IMPLEMENTACIÓN EN POSTGRESQL (PL/pgSQL)
-- ============================================================================
-- PostgreSQL utiliza un modelo donde el Trigger invoca a una Función Disparadora
-- (Trigger Function) que ejecuta la lógica.

-- 1.1. Estructura de las tablas
-- Tabla Maestra de Inventarios (Donde actúa el Trigger)
CREATE TABLE IF NOT EXISTS productos (
    id_producto INT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    precio NUMERIC(10, 2) NOT NULL
);

-- Tabla de Auditoría de Cambios de Precios
CREATE TABLE IF NOT EXISTS log_precios_productos (
    id_log SERIAL PRIMARY KEY,
    id_producto INT NOT NULL,
    precio_anterior NUMERIC(10, 2) NOT NULL,
    precio_nuevo NUMERIC(10, 2) NOT NULL,
    usuario_cambio VARCHAR(100) NOT NULL,
    fecha_cambio TIMESTAMP NOT NULL,
    CONSTRAINT fk_log_producto FOREIGN KEY (id_producto) REFERENCES productos(id_producto) ON DELETE CASCADE
);

-- Tablas Transaccionales del Caso de Estudio
CREATE TABLE IF NOT EXISTS ventas (
    id_venta INT PRIMARY KEY,
    id_cliente INT NOT NULL,
    fecha DATE NOT NULL,
    total NUMERIC(10, 2) NOT NULL,
    id_sucursal INT NOT NULL
);

CREATE TABLE IF NOT EXISTS detalles_ventas (
    id_detalle INT PRIMARY KEY,
    id_venta INT NOT NULL,
    id_producto INT NOT NULL,
    cantidad INT NOT NULL,
    precio_unitario NUMERIC(10, 2) NOT NULL,
    CONSTRAINT fk_detalle_venta FOREIGN KEY (id_venta) REFERENCES ventas(id_venta) ON DELETE CASCADE,
    CONSTRAINT fk_detalle_producto FOREIGN KEY (id_producto) REFERENCES productos(id_producto) ON DELETE CASCADE
);

-- 1.2. Creación de la Función Disparadora (Trigger Function)
-- Optimizada para registrar el usuario de sesión y la fecha exacta del servidor.
CREATE OR REPLACE FUNCTION fn_auditar_cambio_precio()
RETURNS TRIGGER AS $$
BEGIN
    -- Control de rendimiento y lógica: solo inserta si el precio nuevo es diferente al anterior.
    -- Se utiliza IS DISTINCT FROM para manejar de forma robusta posibles valores NULL (si aplicase).
    IF NEW.precio IS DISTINCT FROM OLD.precio THEN
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
            CURRENT_USER,       -- Usuario actual del sistema que ejecuta la transacción
            CURRENT_TIMESTAMP   -- Hora y fecha exacta del servidor con zona horaria
        );
    END IF;
    
    RETURN NEW; -- Retorna el nuevo registro para permitir que la transacción continúe
END;
$$ LANGUAGE plpgsql;

-- 1.3. Creación del Trigger
-- Se ejecuta AFTER UPDATE para asegurar que los datos ya fueron validados y guardados en la tabla maestra.
-- Se añade la cláusula WHEN a nivel de trigger para evitar invocar la función si no hay cambio de precio,
-- maximizando el rendimiento (performance tuning).
CREATE TRIGGER trg_auditar_precio
AFTER UPDATE OF precio ON productos
FOR EACH ROW
WHEN (NEW.precio IS DISTINCT FROM OLD.precio) -- Optimización a nivel de motor de BD
EXECUTE FUNCTION fn_auditar_cambio_precio();


-- ============================================================================
-- SECCIÓN 2: IMPLEMENTACIÓN EN SQL SERVER (T-SQL)
-- ============================================================================
-- SQL Server ejecuta los triggers a nivel de conjunto (Set-based) y utiliza las 
-- tablas lógicas temporales "inserted" (nuevos valores) y "deleted" (valores anteriores).

/*
-- 2.1. Estructura de las tablas en SQL Server
-- Tabla Maestra de Inventarios (Donde actúa el Trigger)
CREATE TABLE productos (
    id_producto INT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    precio DECIMAL(10, 2) NOT NULL
);

-- Tabla de Auditoría de Cambios de Precios
CREATE TABLE log_precios_productos (
    id_log INT IDENTITY(1,1) PRIMARY KEY,
    id_producto INT NOT NULL,
    precio_anterior DECIMAL(10, 2) NOT NULL,
    precio_nuevo DECIMAL(10, 2) NOT NULL,
    usuario_cambio VARCHAR(100) NOT NULL,
    fecha_cambio DATETIME NOT NULL,
    CONSTRAINT fk_log_producto FOREIGN KEY (id_producto) REFERENCES productos(id_producto) ON DELETE CASCADE
);

-- Tablas Transaccionales del Caso de Estudio
CREATE TABLE ventas (
    id_venta INT PRIMARY KEY,
    id_cliente INT NOT NULL,
    fecha DATE NOT NULL,
    total DECIMAL(10, 2) NOT NULL,
    id_sucursal INT NOT NULL
);

CREATE TABLE detalles_ventas (
    id_detalle INT PRIMARY KEY,
    id_venta INT NOT NULL,
    id_producto INT NOT NULL,
    cantidad INT NOT NULL,
    precio_unitario DECIMAL(10, 2) NOT NULL,
    CONSTRAINT fk_detalle_venta FOREIGN KEY (id_venta) REFERENCES ventas(id_venta) ON DELETE CASCADE,
    CONSTRAINT fk_detalle_producto FOREIGN KEY (id_producto) REFERENCES productos(id_producto) ON DELETE CASCADE
);
GO

-- 2.2. Creación del Trigger en SQL Server
-- El trigger de SQL Server está optimizado para procesar múltiples registros a la vez (Multi-row updates).
CREATE TRIGGER trg_auditar_precio_sqlserver
ON productos
AFTER UPDATE
AS
BEGIN
    -- Evitar que se devuelvan mensajes de número de filas afectadas para mejorar el rendimiento
    SET NOCOUNT ON;

    -- Verificar si la columna 'precio' fue modificada en la sentencia UPDATE
    IF UPDATE(precio)
    BEGIN
        -- Insertar los registros de auditoría haciendo un JOIN entre 'inserted' y 'deleted'
        -- para capturar los cambios. Se asegura que solo se inserten filas donde el precio realmente cambió.
        INSERT INTO log_precios_productos (
            id_producto,
            precio_anterior,
            precio_nuevo,
            usuario_cambio,
            fecha_cambio
        )
        SELECT 
            d.id_producto,
            d.precio AS precio_anterior,
            i.precio AS precio_nuevo,
            SYSTEM_USER AS usuario_cambio, -- Usuario actual de la conexión de SQL Server
            GETDATE() AS fecha_cambio       -- Fecha y hora exacta del sistema
        FROM inserted i
        INNER JOIN deleted d ON i.id_producto = d.id_producto
        WHERE i.precio <> d.precio;        -- Filtro estricto para asegurar que el precio realmente cambió
    END;
END;
GO
*/


-- ============================================================================
-- SECCIÓN 3: GUÍA PASO A PASO PARA PRUEBAS (VALIDACIÓN)
-- ============================================================================
-- Para verificar que el Trigger funciona correctamente, ejecute los siguientes pasos:

/*
-- Paso 1: Insertar datos de prueba en la tabla maestra
INSERT INTO productos (id_producto, nombre, precio) VALUES 
(1, 'Laptop Asus ROG', 1200.00),
(2, 'Mouse Logitech G502', 50.00);

-- Paso 2: Consultar estado inicial
SELECT * FROM productos;
SELECT * FROM log_precios_productos;

-- Paso 3: Realizar una actualización que REALMENTE cambia el precio
UPDATE productos 
SET precio = 1150.00 
WHERE id_producto = 1;

-- Paso 4: Realizar una actualización con el MISMO precio (No debería generar log)
UPDATE productos 
SET precio = 50.00 
WHERE id_producto = 2;

-- Paso 5: Consultar la tabla de auditoría para verificar el resultado
-- Debería haber exactamente 1 registro (el del producto 1 con id_producto = 1).
SELECT * FROM log_precios_productos;
*/
