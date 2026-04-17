# Diagramas ASCII - SkipList Sistema de Hospital

## CASO 1: INSERCIÓN DE PACIENTES

### Estado Inicial
```
SkipList vacía:
Level 0: Head -> null
```

### Después de insertar P101 (Prioridad 3)
```
Level 0: Head -> P101-Juan(Pri:3) -> null
```

### Después de insertar P102 (Prioridad 1 - URGENTE)
```
Level 1: Head -> P102-María(Pri:1) -> null
Level 0: Head -> P102-María(Pri:1) -> P101-Juan(Pri:3) -> null
```

### Después de insertar P103 (Prioridad 2)
```
Level 1: Head -> P102-María(Pri:1) -> null
Level 0: Head -> P102-María(Pri:1) -> P103-Carlos(Pri:2) -> P101-Juan(Pri:3) -> null
```

### Después de insertar P104 (Prioridad 4)
```
Level 1: Head -> P102-María(Pri:1) -> null
Level 0: Head -> P102-María(Pri:1) -> P103-Carlos(Pri:2) -> P101-Juan(Pri:3) -> P104-Ana(Pri:4) -> null
```

### Estado Final después de todas las inserciones
```
Level 3: Head -> P102-María(Pri:1) -> null
Level 2: Head -> P102-María(Pri:1) -> P103-Carlos(Pri:2) -> null
Level 1: Head -> P102-María(Pri:1) -> P103-Carlos(Pri:2) -> P101-Juan(Pri:3) -> P104-Ana(Pri:4) -> P105-Pedro(Pri:5) -> null
Level 0: Head -> P102-María(Pri:1) -> P106-Laura(Pri:1) -> P103-Carlos(Pri:2) -> P107-Miguel(Pri:2) -> P101-Juan(Pri:3) -> P104-Ana(Pri:4) -> P105-Pedro(Pri:5) -> null
```

---

## CASO 2: BÚSQUEDA DE PACIENTES

### Buscando P103-Carlos (Prioridad 2)

**Proceso de búsqueda:**
```
Inicio en Level 3:
  Head -> P102-María(Pri:1) -> null
  P102 < P103? NO (1 < 2), avanzar
  null, bajar a Level 2

Level 2:
  Head -> P102-María(Pri:1) -> P103-Carlos(Pri:2) -> null
  P102 < P103? NO (1 < 2), avanzar
  P103 == P103? ¡ENCONTRADO!
```

### Buscando P999-Inexistente

**Proceso de búsqueda:**
```
Inicio en Level 3:
  Head -> P102-María(Pri:1) -> null
  P102 < P999? SÍ (1 < 999), avanzar
  null, bajar a Level 2

Level 2:
  Head -> P102-María(Pri:1) -> P103-Carlos(Pri:2) -> null
  P102 < P999? SÍ, avanzar
  P103 < P999? SÍ, avanzar
  null, bajar a Level 1

Level 1:
  ...continúa bajando niveles...
  
Level 0:
  Head -> P102 -> P106 -> P103 -> P107 -> P101 -> P104 -> P105 -> null
  Todos los elementos < P999, llegar a null
  
Resultado: NO ENCONTRADO
```

---

## CASO 3: ELIMINACIÓN DE PACIENTES

### Estado antes de eliminar P102-María (Prioridad 1)
```
Level 3: Head -> P102-María(Pri:1) -> null
Level 2: Head -> P102-María(Pri:1) -> P103-Carlos(Pri:2) -> null
Level 1: Head -> P102-María(Pri:1) -> P103-Carlos(Pri:2) -> P101-Juan(Pri:3) -> P104-Ana(Pri:4) -> P105-Pedro(Pri:5) -> null
Level 0: Head -> P102-María(Pri:1) -> P106-Laura(Pri:1) -> P103-Carlos(Pri:2) -> P107-Miguel(Pri:2) -> P101-Juan(Pri:3) -> P104-Ana(Pri:4) -> P105-Pedro(Pri:5) -> null
```

### Proceso de eliminación de P102-María
```
1. Buscar P102 en todos los niveles
2. Actualizar punteros en Level 3:
   Head -> null (antes apuntaba a P102)
   
3. Actualizar punteros en Level 2:
   Head -> P103-Carlos (antes apuntaba a P102)
   
4. Actualizar punteros en Level 1:
   Head -> P103-Carlos (antes apuntaba a P102)
   
5. Actualizar punteros en Level 0:
   Head -> P106-Laura (antes apuntaba a P102)
```

### Estado después de eliminar P102-María
```
Level 2: Head -> P103-Carlos(Pri:2) -> null
Level 1: Head -> P103-Carlos(Pri:2) -> P101-Juan(Pri:3) -> P104-Ana(Pri:4) -> P105-Pedro(Pri:5) -> null
Level 0: Head -> P106-Laura(Pri:1) -> P103-Carlos(Pri:2) -> P107-Miguel(Pri:2) -> P101-Juan(Pri:3) -> P104-Ana(Pri:4) -> P105-Pedro(Pri:5) -> null
```

### Estado después de eliminar P105-Pedro (Prioridad 5)
```
Level 2: Head -> P103-Carlos(Pri:2) -> null
Level 1: Head -> P103-Carlos(Pri:2) -> P101-Juan(Pri:3) -> P104-Ana(Pri:4) -> null
Level 0: Head -> P106-Laura(Pri:1) -> P103-Carlos(Pri:2) -> P107-Miguel(Pri:2) -> P101-Juan(Pri:3) -> P104-Ana(Pri:4) -> null
```

---

## CASO 4: INSERCIÓN DE NUEVOS PACIENTES DE URGENCIA

### Estado antes de insertar nuevos urgentes
```
Level 2: Head -> P103-Carlos(Pri:2) -> null
Level 1: Head -> P103-Carlos(Pri:2) -> P101-Juan(Pri:3) -> P104-Ana(Pri:4) -> null
Level 0: Head -> P106-Laura(Pri:1) -> P103-Carlos(Pri:2) -> P107-Miguel(Pri:2) -> P101-Juan(Pri:3) -> P104-Ana(Pri:4) -> null
```

### Después de insertar P108-Roberto (Prioridad 1 - URGENTE)
```
Level 2: Head -> P108-Roberto(Pri:1) -> null
Level 1: Head -> P108-Roberto(Pri:1) -> P103-Carlos(Pri:2) -> null
Level 0: Head -> P108-Roberto(Pri:1) -> P106-Laura(Pri:1) -> P103-Carlos(Pri:2) -> P107-Miguel(Pri:2) -> P101-Juan(Pri:3) -> P104-Ana(Pri:4) -> null
```

### Después de insertar P109-Carmen (Prioridad 1 - URGENTE)
```
Level 3: Head -> P108-Roberto(Pri:1) -> null
Level 2: Head -> P108-Roberto(Pri:1) -> null
Level 1: Head -> P108-Roberto(Pri:1) -> P109-Carmen(Pri:1) -> P103-Carlos(Pri:2) -> null
Level 0: Head -> P108-Roberto(Pri:1) -> P109-Carmen(Pri:1) -> P106-Laura(Pri:1) -> P103-Carlos(Pri:2) -> P107-Miguel(Pri:2) -> P101-Juan(Pri:3) -> P104-Ana(Pri:4) -> null
```

---

## CASO 5: LISTADO ORDENADO POR PRIORIDAD

### Recorrido del Level 0 (todos los pacientes ordenados)
```
Orden de atención por prioridad:

PRIORIDAD 1 (URGENTE MÁXIMA):
  → P108-Roberto (Urgencias)
  → P109-Carmen (Urgencias)
  → P106-Laura (Urgencias)

PRIORIDAD 2 (MUY URGENTE):
  → P103-Carlos (Traumatología)
  → P107-Miguel (Neurología)

PRIORIDAD 3 (URGENTE):
  → P101-Juan (Cardiología)

PRIORIDAD 4 (NORMAL):
  → P104-Ana (Pediatría)
```

---

## ANALOGÍA VISUAL: SISTEMA DE TRIAJE HOSPITALARIO

```
┌─────────────────────────────────────────────────────────────┐
│                 SISTEMA DE TRIAJE HOSPITALARIO               │
│                    (SkipList por Prioridad)                  │
├─────────────────────────────────────────────────────────────┤
│                                                             │
│  NIVEL 3 (EXPRESS - Solo pacientes críticos)               │
│  ┌─────┐    ┌─────┐    ┌─────┐                             │
│  │Head │───▶│P108 │───▶│null │                             │
│  └─────┘    └─────┘    └─────┘                             │
│              (Roberto - Urgencias)                           │
│                                                             │
│  NIVEL 2 (FAST - Críticos y muy urgentes)                  │
│  ┌─────┐    ┌─────┐    ┌─────┐    ┌─────┐                   │
│  │Head │───▶│P108 │───▶│P109 │───▶│null │                   │
│  └─────┘    └─────┘    └─────┘    └─────┘                   │
│              (Roberto)   (Carmen)                            │
│                                                             │
│  NIVEL 1 (RAPIDO - Críticos hasta normales)                │
│  ┌─────┐    ┌─────┐    ┌─────┐    ┌─────┐    ┌─────┐      │
│  │Head │───▶│P108 │───▶│P109 │───▶│P103 │───▶│null │      │
│  └─────┘    └─────┘    └─────┘    └─────┘    └─────┘      │
│              (Roberto)   (Carmen)   (Carlos)                │
│                                                             │
│  NIVEL 0 (BASE - Todos los pacientes)                      │
│  ┌─────┐    ┌─────┐    ┌─────┐    ┌─────┐    ┌─────┐      │
│  │Head │───▶│P108 │───▶│P109 │───▶│P106 │───▶│P103 │...   │
│  └─────┘    └─────┘    └─────┘    └─────┘    └─────┘      │
│                                                             │
├─────────────────────────────────────────────────────────────┤
│                                                             │
│  ORDEN DE ATENCIÓN:                                         │
│  1. P108 Roberto (Prioridad 1 - Urgencias)                 │
│  2. P109 Carmen (Prioridad 1 - Urgencias)                  │
│  3. P106 Laura (Prioridad 1 - Urgencias)                   │
│  4. P103 Carlos (Prioridad 2 - Traumatología)              │
│  5. P107 Miguel (Prioridad 2 - Neurología)                 │
│  6. P101 Juan (Prioridad 3 - Cardiología)                  │
│  7. P104 Ana (Prioridad 4 - Pediatría)                     │
│                                                             │
└─────────────────────────────────────────────────────────────┘
```

---

## COMPARACIÓN DE COMPLEJIDAD EN CONTEXTO HOSPITALARIO

```
┌─────────────────────────────────────────────────────────────┐
│  OPERACIONES EN SISTEMA DE URGENCIAS                        │
├─────────────────────────────────────────────────────────────┤
│                                                             │
│  BÚSQUEDA DE PACIENTE:                                      │
│  - Lista Enlazada: O(n) - Recorrer todos los pacientes      │
│  - SkipList: O(log n) - Saltar niveles inteligentemente    │
│                                                             │
│  INSERCIÓN DE NUEVO PACIENTE:                               │
│  - Lista Enlazada: O(1) - Insertar al final                │
│  - SkipList: O(log n) - Insertar ordenado por prioridad    │
│                                                             │
│  ELIMINACIÓN DE PACIENTE ATENDIDO:                          │
│  - Lista Enlazada: O(n) - Buscar y eliminar                │
│  - SkipList: O(log n) - Encontrar y eliminar rápido        │
│                                                             │
│  ESPACIO:                                                   │
│  - Lista Enlazada: O(n)                                     │
│  - SkipList: O(n log n) - Niveles adicionales              │
│                                                             │
└─────────────────────────────────────────────────────────────┘
```
