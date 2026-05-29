-- ============================================================================
-- SCRIPT DE DEMOSTRACIÓN Y CONSULTAS PARA CAPTURAS DE PANTALLA
-- Materia: Gestión y Manejo de Base de Datos II

-- ============================================================================
-- Este archivo contiene agrupadas todas las sentencias de carga de datos, 
-- simulaciones y reportes analíticos descritos en el README.md.
-- Puede ejecutarse bloque por bloque en su gestor visual para tomar capturas.
-- ============================================================================

-- ============================================================================
-- BLOQUE 1: CARGA COMPLETA DE DATOS DE PRUEBA (PRODUCTOS, VENTAS Y DETALLES)
-- ============================================================================

-- 1. Insertar Catálogo de Productos
INSERT INTO productos (id_producto, nombre, precio) VALUES
(1, 'Laptop Asus ROG', 1200.00),
(2, 'Mouse Logitech G502', 50.00),
(3, 'Teclado Mecánico Razer BlackWidow', 120.00),
(4, 'Monitor Gamer Asus 27"', 350.00),
(5, 'Auriculares HyperX Cloud II', 80.00)
ON CONFLICT (id_producto) DO NOTHING;

-- 2. Registrar Ventas (Cabeceras)
INSERT INTO ventas (id_venta, id_cliente, fecha, total, id_sucursal) VALUES
(1, 101, CURRENT_DATE, 1200.00, 10),
(2, 102, CURRENT_DATE, 200.00, 10),
(3, 103, CURRENT_DATE, 470.00, 20),
(4, 104, CURRENT_DATE - 1, 80.00, 20),
(5, 105, CURRENT_DATE, 430.00, 10),
(6, 106, CURRENT_DATE, 170.00, 20)
ON CONFLICT (id_venta) DO NOTHING;

-- 3. Registrar Detalles de Ventas
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
(10, 6, 2, 1, 50.00)
ON CONFLICT (id_detalle) DO NOTHING;


-- ============================================================================
-- BLOQUE 2: SIMULACIÓN Y VERIFICACIÓN DEL TRIGGER DE AUDITORÍA
-- ============================================================================

-- A. Cambio de Precio Real (Laptop de 1200.00 a 1150.00) -> Genera log
UPDATE productos SET precio = 1150.00 WHERE id_producto = 1;

-- B. Intento de actualizar al mismo precio (Mouse sigue en 50.00) -> No genera log
UPDATE productos SET precio = 50.00 WHERE id_producto = 2;

-- C. CONSULTA DE VERIFICACIÓN (Captura ideal para mostrar el Trigger en acción)
-- Deberá mostrar únicamente la Laptop Asus con su precio anterior y nuevo.
SELECT * FROM log_precios_productos;


-- ============================================================================
-- BLOQUE 3: CONSULTAS ANALÍTICAS Y REPORTES DE NEGOCIO
-- ============================================================================

-- CONSULTA A: Reporte de Clientes VIP (Volumen de compra y ticket promedio)
-- Ideal para mostrar agregaciones y ordenamientos.
SELECT 
    id_cliente,
    COUNT(id_venta) AS total_compras,
    SUM(total) AS monto_total_gastado,
    ROUND(AVG(total), 2) AS ticket_promedio
FROM ventas
GROUP BY id_cliente
ORDER BY monto_total_gastado DESC;


-- CONSULTA B: Reconstrucción y Detalle de Factura (Ejemplo de la Venta #5)
-- Ideal para mostrar un INNER JOIN complejo de 3 tablas con cálculos.
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


-- CONSULTA C: Ranking de los Productos más Vendidos
-- Muestra el rendimiento de la demanda y facturación total generada por artículo.
SELECT 
    p.id_producto,
    p.nombre AS producto,
    SUM(dv.cantidad) AS unidades_totales_vendidas,
    SUM(dv.cantidad * dv.precio_unitario) AS ingresos_totales_generados
FROM detalles_ventas dv
INNER JOIN productos p ON dv.id_producto = p.id_producto
GROUP BY p.id_producto, p.nombre
ORDER BY unidades_totales_vendidas DESC;
