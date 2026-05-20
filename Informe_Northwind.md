# RESTAURACIÓN Y CONSULTAS DE LA BASE DE DATOS NORTHWIND

**Gestión y Manejo de Base de Datos II**  
**Estudiante:** Raúl  
**Fecha:** 19 de mayo de 2026  
**Ubicación del proyecto:** `/home/raulito/Documentos/Gestión y Manejo de Base de Datos II/Practicas_Docker`

---

## 1. Introducción
El presente informe documenta el proceso de restauración y consulta de la base de datos de ejemplo **Northwind**, ampliamente utilizada en el ámbito educativo para el aprendizaje de SQL y sistemas de gestión de bases de datos.

Northwind es una base de datos que simula la operación de una empresa de ventas de productos alimenticios, conteniendo tablas como `products`, `categories`, `suppliers`, `orders`, `customers`, entre otras. Su estructura relacional permite practicar desde consultas simples hasta operaciones complejas con múltiples JOINs y subconsultas.

### Objetivos específicos de esta práctica:
*   Restaurar la base de datos Northwind utilizando **Docker** y **MySQL** como motor de base de datos.
*   Ejecutar consultas SQL de diferentes niveles de complejidad para demostrar el manejo de los datos.
*   Documentar el proceso con los comandos utilizados y capturas de pantalla.
*   Aplicar operaciones de modificación de datos (`INSERT`, `UPDATE`) sobre la base de datos restaurada.

### Ubicación de los archivos utilizados:
```text
/home/raulito/Documentos/Gestión y Manejo de Base de Datos II/Practicas_Docker/
├── northwind-schema.sql
├── northwind-data.sql
└── (otros archivos de la práctica)
```

---

## 2. Restauración de la Base de Datos Northwind

### 2.1 Entorno utilizado
Para esta práctica se utilizó un entorno de contenedores con las siguientes características:

| Componente | Especificación |
| :--- | :--- |
| **Sistema operativo** | CachyOS Linux |
| **Plataforma de contenedores** | Docker |
| **Imagen de base de datos** | MySQL:8.0 |
| **Nombre del contenedor** | `ucatec-mysql` |
| **Cliente SQL** | MySQL CLI (terminal) / MySQL Workbench |
| **Ruta de los scripts SQL** | `/home/raulito/Documentos/Gestión y Manejo de Base de Datos II/Practicas_Docker/` |

### 2.2 Comandos ejecutados
A continuación, se detallan los comandos utilizados para la restauración de la base de datos:

**Paso 1: Verificar que el contenedor existe**
```bash
docker ps -a | grep ucatec-mysql
```

**Paso 2: Iniciar el contenedor MySQL**
```bash
docker start ucatec-mysql
```

**Paso 3: Copiar los archivos SQL al contenedor**
```bash
docker cp /home/raulito/Documentos/Gestión\ y\ Manejo\ de\ Base\ de\ Datos\ II/Practicas_Docker/northwind-schema.sql ucatec-mysql:/tmp/
docker cp /home/raulito/Documentos/Gestión\ y\ Manejo\ de\ Base\ de\ Datos\ II/Practicas_Docker/northwind-data.sql ucatec-mysql:/tmp/
```

**Paso 4: Acceder al contenedor e importar la base de datos**
```bash
docker exec -it ucatec-mysql mysql -uroot -p
```

**Paso 5: Comandos SQL dentro de MySQL**
```sql
CREATE DATABASE northwind;
USE northwind;
SOURCE /tmp/northwind-schema.sql;
SOURCE /tmp/northwind-data.sql;
SHOW TABLES;
```

### 2.3 Verificación de la restauración
Para confirmar que la base de datos se restauró correctamente, se ejecutó el siguiente comando que muestra todas las tablas creadas:

```sql
SHOW TABLES;
```

**Resultado esperado:**
*   categories
*   customers
*   employees
*   order_details
*   orders
*   products
*   shippers
*   suppliers

> 📸 **Captura sugerida N°1:** Pantalla de la terminal mostrando la ejecución del comando `docker ps` con el contenedor `ucatec-mysql` en estado "Up".
>
> 📸 **Captura sugerida N°2:** Pantalla mostrando el resultado de `SHOW TABLES;` con todas las tablas listadas.

---

## 3. Consultas Realizadas

### 3.1 Consulta 1: Consulta Simple (SELECT con ordenamiento)
**Objetivo:** Mostrar los 10 productos más caros de la base de datos, ordenados de mayor a menor precio.

**Código SQL:**
```sql
SELECT * FROM products 
ORDER BY UnitPrice DESC 
LIMIT 10;
```

**Explicación:**
*   `SELECT *`: Selecciona todas las columnas de la tabla products.
*   `ORDER BY UnitPrice DESC`: Ordena los registros de mayor a menor precio.
*   `LIMIT 10`: Restringe el resultado a solo las 10 primeras filas.

| ProductID | ProductName | UnitPrice |
| :--- | :--- | :--- |
| 38 | Côte de Blaye | 263.50 |
| 29 | Thüringer Rostbratwurst | 123.79 |
| 9 | Mishi Kobe Niku | 97.00 |
| ... | ... | ... |

> 📸 **Captura sugerida N°3:** Resultado de esta consulta en MySQL Workbench o terminal.

### 3.2 Consulta 2: SELECT con INNER JOIN
**Objetivo:** Relacionar la tabla `products` con `categories` para mostrar cada producto junto con el nombre de su categoría.

**Código SQL:**
```sql
SELECT 
    p.ProductName,
    c.CategoryName,
    p.UnitPrice,
    p.UnitsInStock
FROM products p
INNER JOIN categories c ON p.CategoryID = c.CategoryID
ORDER BY p.UnitPrice DESC
LIMIT 15;
```

**Explicación:**
*   `INNER JOIN`: Une las dos tablas mediante la clave foránea `CategoryID`.
*   `p` y `c`: Alias para simplificar la escritura de las tablas.

> 📸 **Captura sugerida N°4:** Resultado de la consulta con JOIN.

### 3.3 Consulta 3: Consulta con Agrupación (GROUP BY)
**Objetivo:** Mostrar, por categoría, la cantidad de productos y el precio promedio, ordenado por cantidad.

**Código SQL:**
```sql
SELECT 
    c.CategoryName,
    COUNT(p.ProductID) AS Cantidad_Productos,
    ROUND(AVG(p.UnitPrice), 2) AS Precio_Promedio
FROM categories c
INNER JOIN products p ON c.CategoryID = p.CategoryID
GROUP BY c.CategoryName
ORDER BY Cantidad_Productos DESC;
```

**Explicación:**
*   `COUNT()`: Cuenta los productos por categoría.
*   `ROUND(AVG(), 2)`: Calcula el promedio y lo redondea a 2 decimales.
*   `GROUP BY`: Agrupa los resultados por el nombre de la categoría.

> 📸 **Captura sugerida N°5:** Resultados agrupados por categoría.

### 3.4 Consulta 4: INSERT + UPDATE (Modificación de datos)
**Objetivo:** Insertar un nuevo producto, actualizar su precio y verificar los cambios.

**Paso 1: Insertar un nuevo producto**
```sql
INSERT INTO products (ProductName, CategoryID, SupplierID, UnitPrice, UnitsInStock)
VALUES ('Galletas de Chocolate Bolivianas', 1, 1, 12.50, 100);
```

**Paso 2: Actualizar el precio**
```sql
UPDATE products 
SET UnitPrice = 11.99 
WHERE ProductName = 'Galletas de Chocolate Bolivianas';
```

**Paso 3: Verificar el resultado**
```sql
SELECT * FROM products 
WHERE ProductName LIKE '%Galletas%';
```

> 📸 **Captura sugerida N°6:** Secuencia completa: INSERT, UPDATE y el SELECT final.

---

## 4. Conclusiones
*   **Restauración exitosa:** Se logró restaurar la base de datos utilizando Docker, demostrando la portabilidad y aislamiento que ofrecen los contenedores.
*   **Dominio de SQL:** Se validaron operaciones fundamentales como filtrado, ordenamiento, uniones de tablas (JOIN), funciones de agregación y manipulación de datos (DML).
*   **Organización:** La estructura de archivos en `Practicas_Docker` facilitó la ejecución de scripts y la documentación del proceso.

---

## 5. Anexos

### 5.1 Ruta completa de los archivos
```text
/home/raulito/Documentos/Gestión y Manejo de Base de Datos II/Practicas_Docker/
├── northwind-schema.sql
├── northwind-data.sql
```

### 5.2 Resumen de comandos Bash
```bash
cd "/home/raulito/Documentos/Gestión y Manejo de Base de Datos II/Practicas_Docker"
docker start ucatec-mysql
docker cp northwind-schema.sql ucatec-mysql:/tmp/
docker cp northwind-data.sql ucatec-mysql:/tmp/
docker exec -it ucatec-mysql mysql -uroot -p
```
