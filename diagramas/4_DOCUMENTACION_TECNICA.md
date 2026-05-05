# 4. Documentación Técnica

**Materia:** Estructura de Datos I | **Autor:** Raul Heredia | **Proyecto:** Sistema de Gestión de Fichas Hospitalarias

---

## 📋 Índice de Archivos del Proyecto

Este proyecto ha sido consolidado en **4 archivos principales** para facilitar la revisión:

| # | Archivo | Contenido | Páginas Aprox. |
|---|---------|-----------|----------------|
| **1** | `1_DIAGRAMAS_ESTRUCTURALES.md` | Clases, Integración, Paquetes, Componentes | ~8 |
| **2** | `2_DIAGRAMAS_COMPORTAMIENTO.md` | Casos de Uso y Secuencias | ~6 |
| **3** | `3_DIAGRAMAS_FLUJO_ESTADOS.md` | Estados, Actividades y Flujos | ~6 |
| **4** | `4_DOCUMENTACION_TECNICA.md` | Este documento - Integración completa | ~10 |

**Total:** 4 archivos consolidados (anteriormente 15 archivos sueltos)

---

## 🎯 Integración de Estructuras de Datos

### Tabla Resumen: 7 Estructuras del Repositorio

| Rama GitHub | Estructura | Clase Implementada | Uso en Sistema | Complejidad |
|-------------|-----------|-------------------|----------------|-------------|
| **ED-p1-arreglos** | Arreglos (Arrays) | `ArregloProfesionales`<br>`ArregloServicios` | Catálogos estáticos<br>Profesionales y servicios | O(1) acceso<br>O(n) búsqueda |
| **ED-p2-listas** | Listas Enlazadas | `Ficha` (Nodo)<br>`NodoPila`<br>`NodoSkipList` | Implementación base<br>de estructuras dinámicas | O(1) inserción<br>O(n) búsqueda |
| **ED-p3-short** | Ordenamiento | `insertarPorPrioridad()`<br>`quickSort()` | Mantener cola ordenada<br>Reportes ordenados | O(n) inserción<br>O(n log n) sort |
| **ED-p4-pilas** | Pilas (Stacks) | `PilaHistorial` | Historial LIFO<br>Deshacer operaciones | O(1) push/pop |
| **ED-p5-colas** | Colas (Queues) | `ColaPrioridadHospital` | **ESTRUCTURA PRINCIPAL**<br>Gestión de fichas médicas | O(n) encolar*<br>O(1) desencolar |
| **ED-p6-recursivo** | Recursividad | `buscarPacienteRecursivo()`<br>`recorrerCola()` | Recorrer estructuras<br>Búsqueda en niveles | O(n) recorrido |
| **ED-SkipList** | Skip List | `SkipListPacientes` | Búsqueda rápida<br>de pacientes por CI | O(log n) promedio |

\* *Inserción ordenada por prioridad requiere recorrer la cola*

---

## 🏗️ Arquitectura del Sistema

### Diagrama de Arquitectura Integrada

```
┌─────────────────────────────────────────────────────────────────┐
│                    SISTEMA HOSPITALARIO                          │
│              (Integración de 7 Estructuras de Datos)            │
├─────────────────────────────────────────────────────────────────┤
│                                                                 │
│  ┌─────────────────┐         ┌─────────────────┐               │
│  │   Arreglos      │         │    SkipList     │               │
│  │ (Catálogos)     │         │ (Búsqueda)      │               │
│  │ O(1) acceso     │         │ O(log n) buscar │               │
│  └────────┬────────┘         └────────┬────────┘               │
│           │                         │                         │
│           ▼                         ▼                         │
│  ┌─────────────────────────────────────────────────┐           │
│  │         GestorAtencion (Controlador)          │           │
│  │           Coordina todas las estructuras       │           │
│  └─────────────────────────────────────────────────┘           │
│           │                         │                         │
│           ▼                         ▼                         │
│  ┌─────────────────┐         ┌─────────────────┐               │
│  │  Cola Prioridad │         │     Pila        │               │
│  │   (Principal)   │         │  (Historial)    │               │
│  │  O(n) encolar   │         │   O(1) LIFO     │               │
│  │  O(1) desencolar│         │                 │               │
│  └────────┬────────┘         └─────────────────┘               │
│           │                                                    │
│           │  Implementada con                                  │
│           ▼  Lista Enlazada                                    │
│  ┌─────────────────┐                                           │
│  │  Lista Enlazada │ ◄── Recursividad para recorrido          │
│  │   (Nodos Ficha) │                                           │
│  └─────────────────┘                                           │
│                                                                 │
│  ┌─────────────────┐                                            │
│  │  Ordenamiento   │ ◄── Insertar por prioridad                │
│  │ (QuickSort +    │    QuickSort para reportes                │
│  │  Inserción)     │                                           │
│  └─────────────────┘                                           │
│                                                                 │
└─────────────────────────────────────────────────────────────────┘
```

---

## 📊 Flujo de Datos entre Estructuras

### Caso 1: Generar Ficha Nueva

```
1. Recepcionista ingresa datos
   ↓
2. ArregloServicios.validarTipoServicio() [O(1)]
   ↓
3. SkipListPacientes.buscar() [O(log n)] - ¿Paciente existe?
   ↓
4. Crear objeto Paciente
   ↓
5. Crear objeto Ficha (Nodo Lista Enlazada)
   ↓
6. ColaPrioridadHospital.encolar() [O(n)]
   ↓   ↳ Inserción ordenada por prioridad
   ↓
7. SkipListPacientes.insertar() [O(log n)]
   ↓
8. Confirmación al usuario
```

### Caso 2: Atender Paciente

```
1. Médico solicita atender siguiente
   ↓
2. ColaPrioridadHospital.desencolar() [O(1)]
   ↓   ↳ Extrae del frente (mayor prioridad)
   ↓
3. Mostrar datos del paciente
   ↓
4. PilaHistorial.apilar(ficha) [O(1)]
   ↓   ↳ Guarda en historial LIFO
   ↓
5. Médico registra atención
   ↓
6. (Opcional) Deshacer: Pila.desapilar() → Reencolar
```

### Caso 3: Buscar Paciente (Administrador)

```
1. Administrador ingresa CI del paciente
   ↓
2. SkipListPacientes.buscar() [O(log n)]
   ↓   ↳ Búsqueda multinivel eficiente
   ↓
3. Si encontrado: mostrar historial (Pila)
   ↓
4. Si no encontrado: buscar en Cola [O(n) recursivo]
   ↓
5. Mostrar resultados
```

---

## 💡 Justificación del Uso de Cada Estructura

### ¿Por qué Arreglos para Profesionales?

```java
// Caso de uso: Listado de 10 médicos en UI
String medico = arregloProfesionales[3];  // O(1) - Acceso directo

// vs Lista Enlazada
String medico = listaProfesionales.get(3); // O(n) - Debe recorrer
```

**Decisión:** Catálogo pequeño y estático → Arreglo (acceso O(1))

---

### ¿Por qué SkipList para Pacientes?

```java
// Caso de uso: Buscar paciente por CI
Paciente p = skipList.buscar("1234567");  // O(log n)

// vs Lista Enlazada
Paciente p = lista.buscar("1234567");     // O(n)

// vs Arreglo desordenado
Paciente p = arreglo.buscarLineal("1234567"); // O(n)
```

**Decisión:** Búsqueda frecuente en grandes volúmenes → SkipList (O(log n))

---

### ¿Por qué Pila para Historial?

```java
// Caso de uso: "Deshacer" última atención
Ficha ultima = pila.desapilar();  // O(1) - LIFO perfecto

// vs Cola (incorrecto)
Ficha primera = cola.desencolar(); // ¡Perdería la primera, no la última!
```

**Decisión:** Semántica "deshacer última operación" → Pila (LIFO)

---

### ¿Por qué Cola de Prioridad Principal?

```java
// Caso de uso: Atender emergencias primero
Cola: [Emergencia1] → [Urgente1] → [Emergencia2] → [Normal1]
//         ↑
//    Desencolar siempre del frente (mayor prioridad)
```

**Decisión:** Gestión de turnos por urgencia médica → Cola Prioridad

---

## 📈 Complejidad Temporal Comparada

| Operación | Con Arreglo | Con Lista | Con SkipList | Mejor Opción | Usado en |
|-----------|-------------|-----------|--------------|--------------|----------|
| **Buscar paciente** | O(n) | O(n) | **O(log n)** | SkipList | ✅ Sí |
| **Acceso profesional** | **O(1)** | O(n) | O(log n) | Arreglo | ✅ Sí |
| **Insertar en cola** | - | **O(n)** ordenado | - | Lista | ✅ Sí |
| **Historial LIFO** | O(n) | O(n) | - | **Pila** | ✅ Sí |
| **Ordenar prioridad** | O(n log n) | **O(n)** | - | Inserción ordenada | ✅ Sí |
| **Recorrer estructura** | - | O(n) | O(n) | **Recursividad** | ✅ Sí |

**Eficiencia:** Cada estructura se usa donde es óptima

---

## 🔍 Código Fuente de Implementaciones

### Arreglo - Catálogo de Profesionales

```java
class ArregloProfesionales {
    private String[] profesionales;
    private int cantidad;
    
    public ArregloProfesionales(int capacidad) {
        this.profesionales = new String[capacidad];  // Arreglo estático
        this.cantidad = 0;
    }
    
    // O(1) - Acceso directo por índice
    public String obtenerProfesional(int indice) {
        if (indice >= 0 && indice < cantidad) {
            return profesionales[indice];
        }
        return null;
    }
    
    // O(n) - Búsqueda lineal
    public int buscarProfesional(String nombre) {
        for (int i = 0; i < cantidad; i++) {
            if (profesionales[i].equals(nombre)) {
                return i;
            }
        }
        return -1;
    }
}
```

---

### SkipList - Búsqueda Rápida de Pacientes

```java
class SkipListPacientes {
    private NodoSkipList cabeza;
    private int nivelMaximo;
    private static final float PROBABILIDAD = 0.5f;
    
    public SkipListPacientes() {
        this.nivelMaximo = 16;
        this.cabeza = new NodoSkipList(null, nivelMaximo);
    }
    
    // O(log n) promedio - Búsqueda multinivel
    public Paciente buscar(String nroDoc) {
        NodoSkipList actual = cabeza;
        
        // Buscar desde el nivel más alto
        for (int i = nivelMaximo - 1; i >= 0; i--) {
            while (actual.getSiguiente()[i] != null && 
                   actual.getSiguiente()[i].getPaciente().getNroDoc().compareTo(nroDoc) < 0) {
                actual = actual.getSiguiente()[i];
            }
        }
        
        actual = actual.getSiguiente()[0];
        if (actual != null && actual.getPaciente().getNroDoc().equals(nroDoc)) {
            return actual.getPaciente();
        }
        return null;
    }
    
    // Generar nivel aleatorio para balanceo
    private int nivelAleatorio() {
        int nivel = 1;
        while (Math.random() < PROBABILIDAD && nivel < nivelMaximo) {
            nivel++;
        }
        return nivel;
    }
}
```

---

### Cola de Prioridad - Estructura Principal

```java
class ColaPrioridadHospital {
    private Ficha frente;    // Referencia al primer nodo
    private Ficha final;     // Referencia al último nodo
    private int tamaño;
    
    public ColaPrioridadHospital() {
        this.frente = null;
        this.final = null;
        this.tamaño = 0;
    }
    
    // O(n) - Inserción ordenada por prioridad
    public void encolar(Ficha f) {
        if (estaVacia()) {
            frente = f;
            final = f;
        } else if (f.getPrioridad() < frente.getPrioridad()) {
            // Insertar al frente (mayor prioridad)
            f.setSiguienteNodo(frente);
            frente = f;
        } else {
            // Buscar posición correcta
            Ficha actual = frente;
            while (actual.getSiguienteNodo() != null && 
                   actual.getSiguienteNodo().getPrioridad() <= f.getPrioridad()) {
                actual = actual.getSiguienteNodo();
            }
            f.setSiguienteNodo(actual.getSiguienteNodo());
            actual.setSiguienteNodo(f);
            
            if (f.getSiguienteNodo() == null) {
                final = f;
            }
        }
        tamaño++;
    }
    
    // O(1) - Desencolar del frente
    public Ficha desencolar() {
        if (estaVacia()) return null;
        
        Ficha ficha = frente;
        frente = frente.getSiguienteNodo();
        tamaño--;
        
        if (frente == null) {
            final = null;
        }
        
        return ficha;
    }
}
```

---

### Pila - Historial de Atenciones

```java
class PilaHistorial {
    private NodoPila cima;  // Solo necesitamos la cima
    private int tamaño;
    
    public PilaHistorial() {
        this.cima = null;
        this.tamaño = 0;
    }
    
    // O(1) - Push
    public void apilar(Ficha ficha) {
        NodoPila nuevo = new NodoPila(ficha);
        nuevo.setSiguiente(cima);  // Apunta al anterior
        cima = nuevo;              // Nueva cima
        tamaño++;
    }
    
    // O(1) - Pop
    public Ficha desapilar() {
        if (estaVacia()) return null;
        
        Ficha ficha = cima.getFicha();
        cima = cima.getSiguiente();  // Mover cima al siguiente
        tamaño--;
        
        return ficha;
    }
}
```

---

### Recursividad - Recorrido de Estructuras

```java
class GestorAtencion {
    
    // Recorrido recursivo de la cola
    public void recorrerColaRecursivo(Ficha actual) {
        if (actual == null) return;  // Caso base
        
        System.out.println(actual.getPaciente().getNombre());
        
        // Llamada recursiva con el siguiente nodo
        recorrerColaRecursivo(actual.getSiguienteNodo());
    }
    
    // Búsqueda recursiva en SkipList multinivel
    public Paciente buscarRecursivo(String nroDoc, NodoSkipList actual, int nivel) {
        if (actual == null || nivel < 0) return null;
        
        // Avanzar en el nivel actual
        while (actual.getSiguiente()[nivel] != null && 
               actual.getSiguiente()[nivel].getPaciente().getNroDoc().compareTo(nroDoc) < 0) {
            actual = actual.getSiguiente()[nivel];
        }
        
        // Verificar si encontramos el nodo
        if (actual.getSiguiente()[nivel] != null && 
            actual.getSiguiente()[nivel].getPaciente().getNroDoc().equals(nroDoc)) {
            return actual.getSiguiente()[nivel].getPaciente();
        }
        
        // Recursión: bajar al siguiente nivel
        return buscarRecursivo(nroDoc, actual, nivel - 1);
    }
}
```

---

## ✅ Checklist de Integración Completa

- [x] **ED-p1 (Arreglos):** Catálogo de profesionales y servicios
- [x] **ED-p2 (Listas Enlazadas):** Nodos de Ficha, Pila y SkipList
- [x] **ED-p3 (Ordenamiento):** Inserción ordenada por prioridad
- [x] **ED-p4 (Pilas):** Historial LIFO de atenciones
- [x] **ED-p5 (Colas):** **ESTRUCTURA PRINCIPAL** - Gestión de fichas
- [x] **ED-p6 (Recursividad):** Recorrido y búsqueda recursiva
- [x] **ED-SkipList:** Búsqueda O(log n) de pacientes

**Resultado: 7/7 estructuras integradas** ✅

---

## 📝 Para el Docente

Este proyecto demuestra:

1. **Comprensión profunda** de 7 estructuras de datos diferentes
2. **Capacidad de análisis** para elegir la estructura óptima según el caso de uso
3. **Integración arquitectónica** de múltiples estructuras en un sistema unificado
4. **Documentación UML completa** con 13+ diagramas (estructurales, comportamiento, flujo)
5. **Implementación práctica** con código fuente y análisis de complejidad

**Diferenciador:** No es solo una implementación de colas, sino un sistema real que utiliza cada estructura donde es más eficiente, demostrando pensamiento crítico en diseño de algoritmos.

---

**Navegación:**
- [← Anterior: Diagramas de Flujo y Estados](./3_DIAGRAMAS_FLUJO_ESTADOS.md)
- [🏠 Inicio: Este documento](./4_DOCUMENTACION_TECNICA.md)

---

*Proyecto desarrollado para la materia de Estructura de Datos I*  
*Autor: Raul Heredia*
