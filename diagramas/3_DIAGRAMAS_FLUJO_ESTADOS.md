# 3. Diagramas de Flujo y Estados

**Materia:** Estructura de Datos I | **Autor:** Raul Heredia

---

## 3.1 Diagrama de Estados - Ciclo de Vida de la Ficha

Estados por los que pasa una ficha médica:

```plantuml
@startuml
[*] --> Creada : Generar ficha

Creada --> EnEspera : Encolar en cola de prioridad
EnEspera --> EnEspera : Reasignar prioridad
EnEspera --> Atendiendo : Desencolar (atender siguiente)
EnEspera --> Cancelada : Cancelar ficha

Atendiendo --> Completada : Finalizar atención
Atendiendo --> EnEspera : Reencolar (reagendar)
Atendiendo --> Cancelada : Cancelar durante atención

Completada --> [*] : Archivar en historial (Pila LIFO)
Cancelada --> [*] : Eliminar registro

note right of EnEspera
  **Estructura:** Cola de Prioridad
  Estado donde la ficha
  espera según su prioridad:
  1. Emergencia (inmediata)
  2. Urgente (rápida)
  3. Normal (estándar)
  4. Control (programada)
end note

note right of Atendiendo
  **Estructura:** En proceso
  Paciente está siendo
  atendido por el profesional
  consultorio asignado
end note

note right of Completada
  **Estructura:** Pila LIFO
  Ficha se apila en el
  historial de atenciones
  para permitir "deshacer"
end note

@enduml
```

**Transiciones de Estados:**
- **Creada → EnEspera:** Inserción ordenada en cola por prioridad
- **EnEspera → Atendiendo:** Desencolar del frente (mayor prioridad)
- **Atendiendo → Completada:** Apilar en PilaHistorial (LIFO)
- **EnEspera → Cancelada:** Eliminación de la cola

---

## 3.2 Diagrama de Actividad - Sistema General

Flujo completo del sistema hospitalario:

```plantuml
@startuml
start

partition "Recepción (Cola de Prioridad)" {
  :Enfermera inicia sistema;
  :Selecciona opción "Generar Ficha";
  :Ingresa datos del paciente;
  if (¿Datos válidos?) then (no)
    :Corregir datos;
    stop
  else (si)
    :Selecciona tipo de servicio;
    :Sistema calcula prioridad (Triage);
    :Crear objeto Ficha (Nodo);
    :Crear objeto Paciente;
    note right
      **Estructura:** Lista Enlazada
      Ficha contiene referencia
      al siguiente nodo
    end note
    :Encolar Ficha en ColaPrioridadHospital;
    note right
      **Estructura:** Cola con inserción
      ordenada por prioridad O(n)
    end note
    :Insertar en SkipListPacientes;
    note right
      **Estructura:** SkipList
      Búsqueda futura O(log n)
    end note
    :Mostrar confirmación;
  endif
}

partition "Atención Médica (Cola + Pila)" {
  :Consultorio inicia sistema;
  :Selecciona opción "Atender Siguiente";
  if (¿Cola vacía?) then (si)
    :Mostrar mensaje "Sin pacientes";
  else (no)
    :Desencolar ficha del frente;
    note right
      **Estructura:** Cola FIFO
      por prioridad
      Complejidad: O(1)
    end note
    :Obtener datos del paciente;
    :Mostrar información en pantalla;
    :Consultorio atiende al paciente;
    :Registrar atención completada;
    :Apilar ficha en PilaHistorial;
    note right
      **Estructura:** Pila LIFO
      Permite "deshacer" última
      atención si es necesario
    end note
  endif
}

partition "Administración (Todas las Estructuras)" {
  :Administrador consulta estadísticas;
  :Ver lista de espera (recorrer Cola);
  :Buscar paciente por CI (SkipList O(log n));
  :Ver historial de atenciones (Pila LIFO);
  :Listar profesionales (Arreglo O(1) acceso);
  :Generar reportes ordenados (QuickSort);
  note right
    **Estructura:** Ordenamiento
    QuickSort O(n log n) para
    reportes y estadísticas
  end note
}

stop

@enduml
```

---

## 3.3 Diagrama de Flujo - Generar Ficha (Algoritmo)

Algoritmo detallado con estructuras de datos:

```plantuml
@startuml
start

:Enfermera inicia proceso;

:Ingresar datos del paciente;

if (¿Datos completos?) then (no)
  :Solicitar datos faltantes;
  stop
else (si)
  :Validar información;
endif

if (¿Información válida?) then (no)
  :Mostrar error de validación;
  stop
else (si)
  :Seleccionar tipo de servicio;
endif

:Consultar prioridad en ArregloServicios;
note right
  **Estructura:** Arreglo
  Acceso directo O(1)
  para obtener prioridad
end note

:Asignar prioridad según triage;
:Crear objeto Paciente;
:Crear objeto Ficha;
note right
  **Estructura:** Nodo
  Ficha es un nodo con
  referencia siguiente
end note

:Encolar Ficha en ColaPrioridadHospital;
note right
  **Estructura:** Cola
  Inserción ordenada O(n)
  mantiene prioridades
end note

:Insertar Paciente en SkipList;
note right
  **Estructura:** SkipList
  Inserción O(log n)
  para búsquedas futuras
end note

:Mostrar confirmación;

stop
@enduml
```

---

## 3.4 Diagrama de Flujo - Atender Paciente (Algoritmo)

Proceso de desencolar y atender:

```plantuml
@startuml
start

:Consultorio solicita atender siguiente;
:Verificar si cola está vacía;

if (¿Cola vacía?) then (si)
  :Mostrar alerta "No hay pacientes";
  stop
else (no)
  :Desencolar ficha del frente;
  note right
    **Estructura:** Cola
    Desencolar O(1)
    obtiene mayor prioridad
  end note
endif

:Obtener datos del paciente;
:Mostrar información en pantalla;
:Consultorio atiende al paciente;
:Registrar atención médica;
:Actualizar estado de ficha;

:Apilar ficha en PilaHistorial;
note right
  **Estructura:** Pila LIFO
  Apilar O(1)
  Permite deshacer operación
end note

:Confirmar atención completada;

if (¿Deshacer atención?) then (si)
  :Desapilar de PilaHistorial;
  :Reencolar ficha en ColaPrioridad;
  note right
    **Deshacer:** Pop de pila
    y reinsertar en cola
  end note
else (no)
  :Atención confirmada;
endif

stop
@enduml
```

---

## 3.5 Diagrama de Flujo - Proceso de Triage (Asignación de Prioridad)

Algoritmo de clasificación médica:

```plantuml
@startuml
start

:Enfermera evalúa paciente;
:Evaluar síntomas principales;

if (¿Signos vitales críticos?) then (si)
  :Asignar Prioridad 1 (Emergencia);
  :Marcar como atención inmediata;
  note right
    **Cola:** Inserta al frente
    O(n) pero prioridad máxima
  end note
else (no)
  if (¿Dolor intenso o sangrado?) then (si)
    :Asignar Prioridad 2 (Urgente);
    note right
      **Cola:** Inserta después
      de todas las P1
    end note
  else (no)
    if (¿Síntomas moderados?) then (si)
      :Asignar Prioridad 3 (Normal);
    else (no)
      :Asignar Prioridad 4 (No urgente);
      note right
        **Cola:** Inserta al final
        de su grupo de prioridad
      end note
    endif
  endif
endif

:Insertar en cola según prioridad;
note right
  **Algoritmo:** Inserción ordenada
  Recorre cola hasta encontrar
  posición correcta O(n)
end note

:Generar ficha con prioridad asignada;
:Notificar tiempo estimado de espera;

stop
@enduml
```

---

## Resumen de Diagramas de Flujo y Estados

| Diagrama | Tipo | Estructura Principal | Complejidad |
|----------|------|---------------------|-------------|
| **Estados Ficha** | Dinámico | Cola + Pila | Ciclo de vida completo |
| **Actividad Sistema** | Procesos | Todas las estructuras | Flujo general |
| **Flujo Generar** | Algoritmo | Cola + SkipList + Arreglo | O(n) inserción |
| **Flujo Atender** | Algoritmo | Cola + Pila | O(1) operaciones |
| **Flujo Triage** | Algoritmo | Cola (inserción ordenada) | O(n) por prioridad |

**Navegación:**
- [← Anterior: Diagramas de Comportamiento](./2_DIAGRAMAS_COMPORTAMIENTO.md)
- [→ Siguiente: Documentación Técnica](./4_DOCUMENTACION_TECNICA.md)
