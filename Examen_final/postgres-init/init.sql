-- ============================================================================
-- SCRIPT DE INICIALIZACIÓN SQL — EXAMEN FINAL
-- Base de Datos Relacional: empresa_db
-- ============================================================================

-- Crear Tabla 1: Departamentos (Tabla Maestra)
CREATE TABLE IF NOT EXISTS departamentos (
    id_depto SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL UNIQUE,
    ubicacion VARCHAR(100) NOT NULL
);

-- Crear Tabla 2: Empleados (Tabla Relacionada)
CREATE TABLE IF NOT EXISTS empleados (
    id_empleado SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    puesto VARCHAR(100) NOT NULL,
    salario NUMERIC(10, 2) NOT NULL CHECK (salario > 0),
    id_depto INT NOT NULL,
    fecha_contratacion DATE NOT NULL DEFAULT CURRENT_DATE,
    CONSTRAINT fk_empleado_departamento
        FOREIGN KEY (id_depto) REFERENCES departamentos(id_depto)
        ON DELETE RESTRICT
);

-- ============================================================================
-- INSERTAR REGISTROS DE PRUEBA (Datos Semilla)
-- ============================================================================

-- 1. Insertar Departamentos
INSERT INTO departamentos (nombre, ubicacion) VALUES
('Tecnología y Sistemas', 'Bloque A - Piso 3'),
('Recursos Humanos', 'Bloque B - Piso 1'),
('Marketing y Ventas', 'Bloque A - Piso 2'),
('Finanzas y Contabilidad', 'Bloque B - Piso 2');

-- 2. Insertar Empleados
INSERT INTO empleados (nombre, puesto, salario, id_depto, fecha_contratacion) VALUES
('Carlos Mendoza', 'Desarrollador Senior', 8500.00, 1, '2024-01-15'),
('Ana Gomez', 'Especialista de Selección', 4500.00, 2, '2024-03-10'),
('David Mamani', 'Administrador de Servidores', 7000.00, 1, '2024-06-01'),
('Maria Flores', 'Directora de Cuentas', 6000.00, 3, '2023-11-20'),
('Jose Perez', 'Analista Financiero', 5500.00, 4, '2024-02-18'),
('Lucia Choque', 'Diseñadora UX/UI', 5000.00, 3, '2024-05-12'),
('Roberto Gutierrez', 'Soporte Técnico', 3800.00, 1, '2025-01-05');
