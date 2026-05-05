# 1. Diagramas Estructurales

**Materia:** Estructura de Datos I | **Autor:** Raul Heredia

---

## 1.1 Diagrama de Clases - Integración Completa de Estructuras

Este diagrama muestra cómo se integran las **7 estructuras de datos** del repositorio:

```plantuml
@startuml
skinparam classAttributeIconSize 0

class Paciente {
  - String nombre
  - int edad
  - String nroDoc
  - String telefono
  - String correo
  + Paciente(String nombre, int edad, String nroDoc, String telefono, String correo)
  + String getNombre()
  + int getEdad()
  + String getNroDoc()
  + String getTelefono()
  + String getCorreo()
  + void setNombre(String nombre)
  + void setEdad(int edad)
  + String toString()
}

class Ficha {
  - Paciente paciente
  - LocalDateTime fechaSolicitud
  - String tipoServicio
  - String profesionalAsignado
  - Ficha siguienteNodo
  + Ficha(Paciente paciente, String tipoServicio, String profesionalAsignado)
  + int getPrioridad()
  + Paciente getPaciente()
  + LocalDateTime getFechaSolicitud()
  + String getTipoServicio()
  + String getProfesionalAsignado()
  + Ficha getSiguienteNodo()
  + void setSiguienteNodo(Ficha nodo)
  + String toString()
}

class ColaPrioridadHospital {
  - Ficha frente
  - Ficha final
  - int tamaño
  + ColaPrioridadHospital()
  + void encolar(Ficha f)
  + Ficha desencolar()
  + boolean estaVacia()
  + Ficha verFrente()
  + int getTamaño()
  + List<Ficha> listarFichas()
  - void insertarPorPrioridad(Ficha f)
}

class GestorAtencion {
  - ColaPrioridadHospital colaPrincipal
  - PilaHistorial historialAtenciones
  - ArregloProfesionales profesional[]
  - ArregloServicios servicios[]
  - SkipListPacientes skipListPacientes
  + GestorAtencion()
  + Ficha generarFicha(Paciente p, String servicio, String profesional)
  + Ficha atenderSiguiente()
  + List<Ficha> verListaEspera()
  + boolean estaVacia()
  + int cantidadPacientes()
  + void ordenarFichasPorPrioridad()
  + Paciente buscarPacienteRecursivo(String nroDoc, Nodo actual)
  + void deshacerUltimaAtencion()
  + void mostrarHistorial()
}

class PilaHistorial {
  - NodoPila cima
  - int tamaño
  + PilaHistorial()
  + void apilar(Ficha ficha)
  + Ficha desapilar()
  + boolean estaVacia()
  + Ficha verCima()
  + void mostrarHistorial()
}

class NodoPila {
  - Ficha ficha
  - NodoPila siguiente
  + NodoPila(Ficha ficha)
}

class ArregloProfesionales {
  - String[] profesionales
  - int cantidad
  + ArregloProfesionales(int capacidad)
  + void agregarProfesional(String nombre)
  + String obtenerProfesional(int indice)
  + int buscarProfesional(String nombre)
  + void listarProfesionales()
  + void ordenarProfesionales()
}

class ArregloServicios {
  - String[] tiposServicio
  - int[] prioridades
  - int cantidad
  + ArregloServicios(int capacidad)
  + void agregarServicio(String tipo, int prioridad)
  + int obtenerPrioridad(String tipo)
  + void ordenarPorPrioridad()
  + void listarServicios()
}

class SkipListPacientes {
  - NodoSkipList cabeza
  - int nivelMaximo
  - float probabilidad
  + SkipListPacientes()
  + void insertar(Paciente p)
  + Paciente buscar(String nroDoc)
  + void eliminar(String nroDoc)
  - int nivelAleatorio()
}

class NodoSkipList {
  - Paciente paciente
  - NodoSkipList[] siguiente
  - int nivel
  + NodoSkipList(Paciente paciente, int nivel)
}

Ficha "1" *-- "1" Paciente : Contiene >
ColaPrioridadHospital "1" o-- "*" Ficha : Administra nodos >
GestorAtencion "1" *-- "1" ColaPrioridadHospital : Utiliza >
GestorAtencion "1" *-- "1" PilaHistorial : Usa historial >
GestorAtencion "1" *-- "1" ArregloProfesionales : Usa arreglo >
GestorAtencion "1" *-- "1" ArregloServicios : Usa arreglo >
GestorAtencion "1" *-- "1" SkipListPacientes : Usa skip list >
PilaHistorial "1" o-- "*" NodoPila : Contiene nodos >
SkipListPacientes "1" o-- "*" NodoSkipList : Contiene nodos >
NodoPila "1" *-- "1" Ficha : Almacena >
NodoSkipList "1" *-- "1" Paciente : Almacena >
@enduml
```

**Estructuras Integradas:**
- ✅ **ED-p1 (Arreglos):** `ArregloProfesionales`, `ArregloServicios`
- ✅ **ED-p2 (Listas):** Nodos de Ficha, NodoPila, NodoSkipList
- ✅ **ED-p4 (Pilas):** `PilaHistorial` para historial LIFO
- ✅ **ED-p5 (Colas):** `ColaPrioridadHospital` - **ESTRUCTURA PRINCIPAL**
- ✅ **ED-SkipList:** `SkipListPacientes` búsqueda O(log n)

---

## 1.2 Diagrama de Integración de Estructuras

Visualización de cómo las 7 estructuras se conectan en el sistema:

```plantuml
@startuml

package "Sistema Hospitalario - Integración de Estructuras" {

    package "Colas de Prioridad (ED-p5)" {
        [ColaPrioridadHospital] as Cola
        note right of Cola
            **Uso:** Gestión de fichas
            por prioridad médica
            **Operaciones:**
            - encolar()
            - desencolar()
            - insertarPorPrioridad()
        end note
    }

    package "Listas Enlazadas (ED-p2)" {
        [NodoFicha] as Nodo
        note right of Nodo
            **Uso:** Implementación
            interna de la cola
            **Nodo:** Ficha con 
            referencia siguiente
        end note
    }

    package "Pilas (ED-p4)" {
        [PilaHistorial] as Pila
        note right of Pila
            **Uso:** Historial de
            atenciones (LIFO)
            **Operaciones:**
            - apilar() - nueva atención
            - desapilar() - deshacer
            - verCima() - última
        end note
    }

    package "Arreglos (ED-p1)" {
        [ArregloProfesionales] as ArrProf
        [ArregloServicios] as ArrServ
        note right of ArrProf
            **Uso:** Catálogo de
            profesionales médicos
            **Operaciones:**
            - acceso por índice O(1)
            - búsqueda lineal O(n)
        end note
    }

    package "Ordenamiento (ED-p3)" {
        [QuickSort] as Sort
        [InsercionOrdenada] as Insert
        note right of Sort
            **Uso:** Ordenar listas
            y arreglos
            **Algoritmos:**
            - QuickSort: O(n log n)
            - Inserción: O(n)
        end note
    }

    package "SkipList (ED-SkipList)" {
        [SkipListPacientes] as Skip
        note right of Skip
            **Uso:** Búsqueda rápida
            de pacientes por CI
            **Complejidad:**
            - Búsqueda: O(log n)
            - Inserción: O(log n)
        end note
    }

    package "Recursividad (ED-p6)" {
        [RecorridoRecursivo] as Rec
        [BusquedaRecursiva] as Busq
        note right of Rec
            **Uso:** Recorrer y buscar
            en estructuras
            **Funciones:**
            - recorrerCola()
            - buscarPaciente()
        end note
    }
}

' Relaciones de uso
Cola ..> Nodo : Implementa con >
Pila ..> Nodo : Usa nodos >
Skip ..> Nodo : Usa nodos multinivel >

Cola ..> Insert : Usa inserción ordenada >
ArrProf ..> Sort : Usa ordenamiento >
ArrServ ..> Sort : Usa ordenamiento >

Cola ..> Rec : Recorrido recursivo >
Skip ..> Busq : Búsqueda recursiva >

' Interacciones con el sistema
[UI] ..> Cola : Encolar/Desencolar
[UI] ..> Pila : Ver historial
[UI] ..> ArrProf : Listar médicos
[UI] ..> ArrServ : Ver servicios
[UI] ..> Skip : Buscar paciente

@enduml
```

---

## 1.3 Diagrama de Paquetes

Arquitectura en capas del sistema:

```plantuml
@startuml
package "Capa_Presentacion (UI)" {
  [VistaGenerarFichas]
  [VistaAtencionMedica]
}

package "Capa_Negocio (Controladores)" {
  [GestorAtencion]
}

package "Capa_Estructuras_Datos" {
  [ColaPrioridadHospital]
  [PilaHistorial]
  [SkipListPacientes]
  [ArregloProfesionales]
  [ArregloServicios]
}

package "Capa_Modelos (Entidades)" {
  [Ficha (Nodo)]
  [Paciente]
  [NodoPila]
  [NodoSkipList]
}

"Capa_Presentacion (UI)" ..> "Capa_Negocio (Controladores)" : Llama a métodos
"Capa_Negocio (Controladores)" ..> "Capa_Estructuras_Datos" : Manipula estructuras
"Capa_Negocio (Controladores)" ..> "Capa_Modelos (Entidades)" : Instancia objetos
"Capa_Estructuras_Datos" ..> "Capa_Modelos (Entidades)" : Almacena nodos
@enduml
```

---

## 1.4 Diagrama de Componentes

Componentes del sistema y sus interdependencias:

```plantuml
@startuml
package "Sistema de Gestión Hospitalaria" {
  [Interfaz de Usuario] as UI
  [Controlador de Atención] as Controller
  [Módulo de Colas] as QueueModule
  [Módulo de Pilas] as StackModule
  [Módulo de Pacientes] as PatientModule
  [Módulo de Validación] as ValidationModule
  [Módulo de Reportes] as ReportModule
}

database "Base de Datos" as DB

UI --> Controller : Llamadas a métodos
Controller --> QueueModule : Operaciones de cola
Controller --> StackModule : Operaciones de pila
Controller --> PatientModule : Gestión de pacientes
Controller --> ValidationModule : Validar datos
Controller --> ReportModule : Generar estadísticas

QueueModule --> DB : Persistencia (opcional)
PatientModule --> DB : Persistencia (opcional)
ReportModule --> DB : Consultas

note right of QueueModule
  Implementa Cola de Prioridad
  con lista enlazada
end note

note right of StackModule
  Implementa Pila LIFO
  para historial de
  atenciones médicas
end note

note right of Controller
  GestorAtencion
  Coordina todos los módulos
  y estructuras de datos
end note
@enduml
```

---

## Resumen de Diagramas Estructurales

| Diagrama | Descripción | Estructuras Incluidas |
|----------|-------------|---------------------|
| **Clases** | Modelo completo con todas las clases | 7 estructuras integradas |
| **Integración** | Visual de conexiones entre estructuras | Arquitectura unificada |
| **Paquetes** | Capas del sistema | 4 capas organizadas |
| **Componentes** | Módulos funcionales | 7 componentes principales |

**Navegación:**
- [← Volver al INICIO](../INDICE_DIAGRAMAS.md)
- [→ Siguiente: Diagramas de Comportamiento](./2_DIAGRAMAS_COMPORTAMIENTO.md)
