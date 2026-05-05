# 2. Diagramas de Comportamiento

**Materia:** Estructura de Datos I | **Autor:** Raul Heredia

---

## 2.1 Diagrama de Casos de Uso - General

Actores y casos de uso principales del sistema:

```plantuml
@startuml
left to right direction
actor Enfermera as R
actor Profesional_Consultorio as M
actor Administrador as A

package "Sistema de Gestión de Fichas" {
  usecase "Ingresar Datos del Paciente" as UC1
  usecase "Validar Información del Paciente" as UC2
  usecase "Generar Ficha" as UC3
  usecase "Asignar Tipo de Servicio (Triage)" as UC4
  usecase "Visualizar Lista de Fichas" as UC5
  usecase "Atender Ficha (Desencolar)" as UC6
  usecase "Consultar Estadísticas" as UC7
  usecase "Ver Tiempo de Espera" as UC8
  usecase "Cancelar Ficha" as UC9
  usecase "Reasignar Prioridad" as UC10
  usecase "Deshacer Última Atención" as UC11
  usecase "Buscar Paciente (SkipList)" as UC12
}

R --> UC1
R --> UC2
R --> UC3
R --> UC9
UC3 ..> UC4 : <<include>>
UC3 ..> UC2 : <<include>>

M --> UC5
M --> UC6
M --> UC8
M --> UC10
M --> UC11

A --> UC7
A --> UC5
A --> UC12

@enduml
```

**Actores:**
- **Enfermera:** Genera fichas, ingresa datos, cancela fichas
- **Profesional Consultorio:** Atiende pacientes, reasigna prioridades, usa pila para deshacer
- **Administrador:** Consulta estadísticas, busca pacientes con SkipList

---

## 2.2 Diagrama de Uso - Generar Ficha (Detallado)

Flujo completo para la generación de una ficha:

```plantuml
@startuml
left to right direction
actor Enfermera as R
actor Sistema as S

usecase "Ingresar Datos del Paciente" as UC1
usecase "Validar Información" as UC2
usecase "Seleccionar Tipo de Servicio" as UC3
usecase "Asignar Prioridad (Triage)" as UC4
usecase "Generar Ficha" as UC5
usecase "Confirmar Generación" as UC6

R --> UC1
UC1 ..> UC2 : <<include>>
UC2 ..> UC3 : <<include>>
UC3 ..> UC4 : <<include>>
UC4 ..> UC5 : <<include>>
UC5 ..> UC6 : <<include>>
UC6 --> S : Almacenar en cola

@enduml
```

**Flujo:**
1. Ingresar datos → Validar información
2. Seleccionar servicio → Asignar prioridad automáticamente
3. Generar ficha → Confirmar al usuario
4. Almacenar en Cola de Prioridad

---

## 2.3 Diagrama de Uso - Atender Paciente (Detallado)

Proceso de atención médica:

```plantuml
@startuml
left to right direction
actor Consultorio as M
actor Sistema as S

usecase "Ver Lista de Espera" as UC1
usecase "Seleccionar Paciente" as UC2
usecase "Verificar Disponibilidad" as UC3
usecase "Atender Paciente" as UC4
usecase "Registrar Atención" as UC5
usecase "Eliminar de Cola" as UC6
usecase "Apilar en Historial" as UC7

M --> UC1
UC1 ..> UC2 : <<include>>
UC2 ..> UC3 : <<include>>
UC3 ..> UC4 : <<include>>
UC4 ..> UC5 : <<include>>
UC5 ..> UC6 : <<include>>
UC5 ..> UC7 : <<include>>
UC6 --> S : Actualizar cola
UC7 --> S : Guardar en pila LIFO

@enduml
```

**Flujo:**
1. Ver lista ordenada por prioridad
2. Seleccionar siguiente paciente (frente de cola)
3. Atender y registrar
4. Eliminar de Cola + Apilar en Historial (Pila LIFO)

---

## 2.4 Diagrama de Secuencia - Generar Ficha

Interacción entre componentes:

```plantuml
@startuml
actor Enfermera
boundary "UI : Formulario Ficha" as Vista
control "Controlador : GestorAtencion" as Gestor
entity "Estructura : ColaPrioridadHospital" as Cola
entity "Estructura : ArregloServicios" as ArrServ
entity "Estructura : SkipListPacientes" as Skip

Enfermera -> Vista : Ingresar datos del paciente
Vista -> Vista : Validar campos

alt Datos inválidos
    Vista --> Enfermera : Mostrar errores de validación
else Datos válidos
    Vista -> Gestor : crearPaciente(datos)
    Gestor --> Vista : Paciente creado
    
    Enfermera -> Vista : Seleccionar tipo de servicio
    Vista -> ArrServ : obtenerPrioridad(servicio)
    ArrServ --> Vista : Prioridad asignada
    
    Enfermera -> Vista : Seleccionar profesional
    
    Vista -> Gestor : generarFicha(paciente, servicio, profesional)
    Gestor -> Gestor : crearFicha(paciente, servicio, profesional)
    Gestor -> Gestor : calcularPrioridad(servicio)
    Gestor -> Cola : encolar(ficha)
    Cola -> Cola : insertarPorPrioridad(ficha)
    Cola --> Gestor : Ficha encolada
    
    Gestor -> Skip : insertar(paciente)
    Skip --> Gestor : Paciente indexado
    
    Gestor --> Vista : Confirmación con posición en cola
    Vista --> Enfermera : Mostrar ficha generada
end

@enduml
```

---

## 2.5 Diagrama de Secuencia - Atender Paciente

Flujo cuando un consultorio atiende al siguiente paciente:

```plantuml
@startuml
actor Consultorio
boundary "UI : Tabla Atender" as Vista
control "Controlador : GestorAtencion" as Gestor
entity "Estructura : ColaPrioridadHospital" as Cola
entity "Estructura : PilaHistorial" as Pila

Consultorio -> Vista : Clic en "Atender (x)"
Vista -> Gestor : atenderSiguiente()
Gestor -> Cola : desencolar()

alt Cola vacía
    Cola --> Gestor : null
    Gestor --> Vista : Mostrar alerta: "No hay pacientes en espera"
else Nodo disponible
    Cola --> Gestor : Ficha (Nodo extraído)
    Gestor -> Pila : apilar(fichaAtendida)
    Pila --> Gestor : Ficha apilada en historial
    Gestor --> Vista : Actualizar tabla (eliminar fila)
    Vista --> Consultorio : Desplegar datos del Paciente a atender
end

@enduml
```

**Nota:** La Pila (LIFO) permite al consultorio "deshacer" la última atención si fue un error.

---

## Resumen de Diagramas de Comportamiento

| Diagrama | Tipo | Actores | Estructuras Destacadas |
|----------|------|---------|---------------------|
| **Casos de Uso General** | Comportamiento | 3 actores | Cola principal |
| **Generar Ficha** | Caso de uso detallado | Enfermera | Cola + SkipList |
| **Atender Paciente** | Caso de uso detallado | Consultorio | Cola + Pila |
| **Secuencia Generar** | Interacción | Enfermera | Cola + Arreglo + SkipList |
| **Secuencia Atender** | Interacción | Consultorio | Cola + Pila |

**Navegación:**
- [← Anterior: Diagramas Estructurales](./1_DIAGRAMAS_ESTRUCTURALES.md)
- [→ Siguiente: Diagramas de Flujo y Estados](./3_DIAGRAMAS_FLUJO_ESTADOS.md)
