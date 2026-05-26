-- Active: 1779754265385@@127.0.0.1@5432@pagila
-- =====================================================================
-- 1. FUNCIÓN CORREGIDA
-- Objetivo: Recibe el apellido y busca de forma insensible a mayúsculas.
-- =====================================================================
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

-- =====================================================================
-- 2. PROCEDIMIENTO CORREGIDO
-- Objetivo: Insertar un nuevo actor normalizando a mayúsculas.
-- =====================================================================
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
