# Índice de Presentación - Sistema de Gestión Hospitalaria

**Materia:** Estructura de Datos I  
**Autor:** Raul Heredia  
**Fecha:** Abril 2026

---

## 📁 Estructura del Proyecto (4 Archivos Principales)

Este proyecto ha sido consolidado en **4 archivos** para facilitar la revisión docente:

### Orden de Presentación Recomendado:

| Orden | Archivo | Contenido | Tiempo Est. |
|-------|---------|-----------|-------------|
| **1** | `1_DIAGRAMAS_ESTRUCTURALES.md` | **ESTRUCTURA DEL SISTEMA** | 10-15 min |
| **2** | `2_DIAGRAMAS_COMPORTAMIENTO.md` | **COMPORTAMIENTO DEL SISTEMA** | 10-15 min |
| **3** | `3_DIAGRAMAS_FLUJO_ESTADOS.md` | **ALGORITMOS Y PROCESOS** | 10-15 min |
| **4** | `4_DOCUMENTACION_TECNICA.md` | **INTEGRACIÓN Y CÓDIGO** | 15-20 min |

**Total:** ~45-65 min de presentación

---

## 🎯 Guía de Presentación Paso a Paso

### **PASO 1: Estructura del Sistema** (Archivo 1)

**Abrir:** `1_DIAGRAMAS_ESTRUCTURALES.md`

**Diagramas a mostrar:**
1. **Diagrama de Clases** - Mostrar las 7 estructuras integradas
2. **Diagrama de Integración** - Explicar cómo se conectan
3. **Diagrama de Paquetes** - Mostrar arquitectura en capas
4. **Diagrama de Componentes** - Mostrar módulos funcionales

**Puntos clave a mencionar:**
- ✅ Se integraron las 7 estructuras del repositorio
- ✅ Cada estructura se usa donde es óptima
- ✅ Cola de Prioridad es la estructura principal

---

### **PASO 2: Comportamiento del Sistema** (Archivo 2)

**Abrir:** `2_DIAGRAMAS_COMPORTAMIENTO.md`

**Diagramas a mostrar:**
1. **Casos de Uso General** - Actores y funcionalidades
2. **Caso de Uso: Generar Ficha** - Flujo recepcionista
3. **Caso de Uso: Atender Paciente** - Flujo médico
4. **Secuencia: Generar Ficha** - Interacción técnica
5. **Secuencia: Atender Paciente** - Interacción con Pila

**Puntos clave a mencionar:**
- 3 actores: Recepcionista, Médico, Administrador
- Uso de SkipList para búsqueda de pacientes
- Uso de Pila para historial y "deshacer"

---

### **PASO 3: Algoritmos y Procesos** (Archivo 3)

**Abrir:** `3_DIAGRAMAS_FLUJO_ESTADOS.md`

**Diagramas a mostrar:**
1. **Estados de la Ficha** - Ciclo de vida completo
2. **Actividad del Sistema** - Flujo general con todas las estructuras
3. **Flujo: Generar Ficha** - Algoritmo paso a paso
4. **Flujo: Atender Paciente** - Proceso con cola y pila
5. **Flujo: Triage** - Asignación de prioridades

**Puntos clave a mencionar:**
- Complejidad temporal de cada operación
- Cómo la inserción ordenada mantiene la prioridad
- Uso de recursividad en recorridos

---

### **PASO 4: Integración y Código** (Archivo 4)

**Abrir:** `4_DOCUMENTACION_TECNICA.md`

**Secciones a mostrar:**
1. **Tabla de Integración** - 7/7 estructuras verificadas
2. **Arquitectura del Sistema** - Diagrama visual
3. **Flujos de Datos** - Casos de uso reales
4. **Justificación** - ¿Por qué cada estructura?
5. **Código Fuente** - Implementaciones clave

**Puntos clave a mencionar:**
- ✅ Todas las estructuras del repositorio están integradas
- ✅ Análisis de complejidad O(1), O(log n), O(n)
- ✅ Código funcional listo para implementar

---

## 📊 Resumen de Estructuras Integradas

| Rama Repo | Estructura | Uso en Sistema | Complejidad |
|-----------|-----------|----------------|-------------|
| ED-p1 | **Arreglos** | Catálogos profesionales | O(1) acceso |
| ED-p2 | **Listas Enlazadas** | Nodos de cola, pila, skiplist | O(1) inserción |
| ED-p3 | **Ordenamiento** | Inserción ordenada por prioridad | O(n) |
| ED-p4 | **Pilas** | Historial LIFO, deshacer | O(1) push/pop |
| ED-p5 | **Colas** | **ESTRUCTURA PRINCIPAL** | O(n) encolar |
| ED-p6 | **Recursividad** | Recorrer estructuras | O(n) |
| ED-SkipList | **Skip List** | Búsqueda pacientes O(log n) | O(log n) |

**Total: 7/7 estructuras del repositorio** ✅

---

## 💡 Tips para la Presentación

### Antes de empezar:
1. **Abrir Graphviz** - Verificar que los diagramas se rendericen bien
2. **Preparar IDE** - Tener los 4 archivos abiertos en tabs
3. **Preview listo** - Ctrl+Shift+V para ver diagramas

### Durante la presentación:
1. **Empezar por el Índice** (este archivo) - Dar contexto general
2. **Mostrar archivo 1** - La estructura (lo "estático")
3. **Mostrar archivo 2** - El comportamiento (lo "dinámico")
4. **Mostrar archivo 3** - Los algoritmos (cómo funciona)
5. **Terminar con archivo 4** - La integración completa y el código

### Frases clave para destacar:
- "Este proyecto integra las 7 estructuras de datos del curso en un sistema real"
- "Cada estructura se usa donde es algorítmicamente óptima"
- "No solo implementamos colas, sino que elegimos la mejor estructura para cada caso de uso"
- "La cola de prioridad es el núcleo, pero está complementada con otras 6 estructuras"

---

## 📂 Archivos en el Proyecto

```
Competencias/
├── 0_INDICE_PRESENTACION.md      ← ESTE ARCHIVO (guía)
├── 1_DIAGRAMAS_ESTRUCTURALES.md    ← Estructura (4 diagramas)
├── 2_DIAGRAMAS_COMPORTAMIENTO.md  ← Comportamiento (5 diagramas)
├── 3_DIAGRAMAS_FLUJO_ESTADOS.md   ← Algoritmos (5 diagramas)
├── 4_DOCUMENTACION_TECNICA.md     ← Integración completa + código
└── interfaz.xlsx                   ← Mockup de interfaz (opcional)
```

**Total:** 4 archivos Markdown consolidados

---

## ✅ Checklist Pre-Presentación

- [ ] Graphviz instalado y funcionando
- [ ] Preview de PlantUML activo en IDE
- [ ] Los 4 archivos abiertos en tabs
- [ ] Diagramas renderizan correctamente
- [ ] Tiempo de presentación ensayado
- [ ] Puntos clave preparados

---

## 🎓 Mensaje para el Docente

> "Este proyecto demuestra la comprensión profunda de las estructuras de datos mediante su aplicación práctica en un sistema hospitalario real. No se trata solo de implementar colas, sino de integrar inteligentemente las 7 estructuras del curso, utilizando cada una donde es algorítmicamente óptima según su complejidad temporal."

---

**¡Proyecto listo para presentación!** 🚀

*Estructura de Datos I - Competencias*
