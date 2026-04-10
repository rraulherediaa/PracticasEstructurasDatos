# ED-p5-colas

**Materia:** Estructura de Datos I  
**Práctica 5:** Colas (Queues)

## Descripción
Programa en consola escrito en **Ruby** que implementa una cola (queue) de alumnos con operaciones de gestión: encolar, desencolar, ver frente, calcular promedios y estadísticas.

## ¿Qué es una Cola?
Una cola es una estructura de datos lineal que sigue el principio **FIFO** (First In, First Out) - el primer elemento en entrar es el primero en salir. Imagina una fila en un supermercado: el primero que llega es el primero en ser atendido.

### Características principales:
- **FIFO:** First In, First Out
- **Operaciones limitadas:** Solo se puede agregar al final (enqueue) y quitar del frente (dequeue)
- **Eficiente:** O(1) para enqueue y dequeue
- **Aplicaciones:** Colas de impresión, planificación de procesos, sistemas de tickets

## Requisitos previos

### 1. Instalar Ruby

**Linux/Ubuntu:**
```bash
sudo apt-get update
sudo apt-get install ruby-full

# Verificar instalación
ruby --version
```

**macOS (Homebrew):**
```bash
brew install ruby

# Verificar instalación
ruby --version
```

**Windows:**
Descargar desde: https://rubyinstaller.org/

### 2. Estructura del proyecto
```
Practicas/
├── colas_alumnos.rb   # Código fuente
└── README.md
```

## Uso

### Ejecutar directamente:
```bash
ruby colas_alumnos.rb
```

### Hacer ejecutable y ejecutar:
```bash
chmod +x colas_alumnos.rb
./colas_alumnos.rb
```

## Funcionalidades del programa
1. **Mostrar cola:** Ver todos los alumnos en orden de llegada
2. **Encolar (enqueue):** Agregar alumno al final de la cola
3. **Desencolar (dequeue):** Quitar alumno del frente de la cola
4. **Ver frente (peek):** Ver el alumno del frente sin quitarlo
5. **Verificar si está vacía**
6. **Promedio general:** Calcular el promedio de notas
7. **Estadísticas:** Ver aprobados, reprobados y promedio

## Estructura de datos

### Clase Alumno:
```ruby
class Alumno
  attr_accessor :nombre, :carnet, :nota, :materia

  def initialize(nombre, carnet, nota, materia)
    @nombre = nombre
    @carnet = carnet
    @nota = nota
    @materia = materia
  end
end
```

### Clase ColaAlumnos:
```ruby
class ColaAlumnos
  def initialize
    @alumnos = []
  end

  def enqueue(alumno)
    @alumnos << alumno  # Agregar al final
  end

  def dequeue
    @alumnos.shift  # Quitar del frente
  end

  def peek
    @alumnos.first  # Ver el frente
  end

  def mostrar_cola
    # Mostrar cola completa
  end

  def promedio_general
    # Calcular promedio
  end
end
```

## Ejemplo de ejecución
```
--- MENÚ COLA DE ALUMNOS ---
1. Mostrar cola de alumnos
2. Encolar alumno (agregar al final)
3. Desencolar alumno (quitar del frente)
4. Ver frente de la cola
5. Ver si está vacía
6. Ver promedio general
7. Ver estadísticas
8. Salir
Elige una opción: 2
Nombre del alumno: Laura Gómez
Carnet: 12350
Nota (0-100): 88
Materia: Estructura de Datos
Alumno encolado: Laura Gómez

Elige una opción: 1

=== COLA DE ALUMNOS ===
Total: 6 alumnos

FRENTE DE LA COLA:
[0] Juan Pérez (12345) - Estructura de Datos: 85
[1] María García (12346) - Estructura de Datos: 72
[2] Carlos López (12347) - Estructura de Datos: 45
[3] Ana Martínez (12348) - Estructura de Datos: 90
[4] Pedro Sánchez (12349) - Estructura de Datos: 60
[5] Laura Gómez (12350) - Estructura de Datos: 88
======================

Elige una opción: 3
Alumno desencolado: Juan Pérez

Elige una opción: 7

=== ESTADÍSTICAS ===
Total alumnos: 5
Aprobados (>= 51): 4
Reprobados (< 51): 1
Promedio general: 71.00
=====================
```

## Complejidad Temporal

| Operación | Complejidad |
|-----------|-------------|
| Encolar (enqueue) | O(1) |
| Desencolar (dequeue) | O(n) |
| Ver frente (peek) | O(1) |
| Mostrar cola | O(n) |
| Promedio general | O(n) |
| Estadísticas | O(n) |

*Nota: En Ruby, `shift()` es O(n) porque desplaza todos los elementos. Con implementación con punteros sería O(1).

## Explicación del código Ruby

### Conceptos clave:
- **attr_accessor:** Crea getters y setters automáticamente
- **Array:** Contenedor dinámico para la cola
- **push/<<:** Agregar al final del array
- **shift:** Quitar elemento del inicio del array
- **first:** Ver el primer elemento
- **each_with_index:** Iterar con índice
- **find:** Buscar elemento que cumpla condición
- **select:** Filtrar elementos
- **sum:** Sumar valores de colección
- **to_f:** Convertir a float para cálculos precisos

### Características Ruby usadas:
- **Interpolación de strings:** `"#{variable}"`
- **Símbolos:** `:nombre` para identificadores
- **Bloques:** `{ |param| ... }` o `do |param| ... end`
- **Métodos bang:** métodos que modifican el objeto (como `shift`)

## Diferencias Cola vs Pila vs Lista

| Aspecto | Cola (FIFO) | Pila (LIFO) | Lista |
|---------|-------------|-------------|-------|
| Principio | First In, First Out | Last In, First Out | Acceso aleatorio |
| Inserción | Solo al final | Solo al final | Cualquier posición |
| Eliminación | Solo del frente | Solo del final | Cualquier posición |
| Acceso | Solo frente | Solo cima | Cualquier elemento |
| Aplicación | Filas, buffers | Deshacer/Rehacer | Datos generales |

## Diferencias Ruby vs Python vs C++

| Aspecto | Ruby | Python | C++ |
|---------|------|--------|-----|
| Tipado | Dinámico | Dinámico | Estático |
| Compilación | No | No | Sí |
| Sintaxis | Muy limpia | Limpia | Verborosa |
| Orientación a objetos | Puro | Mixto | Opcional |
| Bloques | Nativos | Lambdas | No |
| Rendimiento | Medio | Medio | Máximo |

## Autor
Raul Heredia
