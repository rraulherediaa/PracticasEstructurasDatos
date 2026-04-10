# ED-p5-listas

**Materia:** Estructura de Datos I  
**Práctica 5:** Listas en Ruby

## Descripción
Programa en consola escrito en **Ruby** que implementa una lista de alumnos con operaciones de gestión: agregar, eliminar, buscar, calcular promedios y estadísticas.

## ¿Qué es Ruby?
Ruby es un lenguaje de programación dinámico, orientado a objetos, conocido por su sintaxis elegante y productividad. Diseñado por Yukihiro Matsumoto ("Matz") en Japón, enfatiza la simplicidad y la felicidad del programador.

### Características principales:
- **Dinámico:** Tipado dinámico, no necesita compilación
- **Orientado a objetos:** Todo es un objeto
- **Sintaxis limpia:** Código legible y expresivo
- **Gestión automática:** Garbage collector incluido
- **Multi-paradigma:** Soporta programación funcional, imperativa y orientada a objetos

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
├── listas_alumnos.rb   # Código fuente
└── README.md
```

## Uso

### Ejecutar directamente:
```bash
ruby listas_alumnos.rb
```

### Hacer ejecutable y ejecutar:
```bash
chmod +x listas_alumnos.rb
./listas_alumnos.rb
```

## Funcionalidades del programa
1. **Mostrar alumnos:** Ver todos los alumnos registrados
2. **Agregar alumno:** Ingresar nombre, carnet, nota y materia
3. **Eliminar alumno:** Eliminar por número de carnet
4. **Buscar alumno:** Buscar por número de carnet
5. **Promedio general:** Calcular el promedio de notas
6. **Estadísticas:** Ver aprobados, reprobados y promedio

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

### Clase ListaAlumnos:
```ruby
class ListaAlumnos
  def initialize
    @alumnos = []
  end

  def agregar_alumno(alumno)
    @alumnos << alumno
  end

  def eliminar_alumno(carnet)
    # Lógica de eliminación
  end

  def buscar_alumno(carnet)
    # Lógica de búsqueda
  end

  def mostrar_alumnos
    # Mostrar lista completa
  end

  def promedio_general
    # Calcular promedio
  end
end
```

## Ejemplo de ejecución
```
--- MENÚ LISTA DE ALUMNOS ---
1. Mostrar todos los alumnos
2. Agregar nuevo alumno
3. Eliminar alumno por carnet
4. Buscar alumno por carnet
5. Ver promedio general
6. Ver estadísticas
7. Salir
Elige una opción: 2
Nombre del alumno: Laura Gómez
Carnet: 12350
Nota (0-100): 88
Materia: Estructura de Datos
Alumno agregado: Laura Gómez

Elige una opción: 1

=== LISTA DE ALUMNOS ===
Total: 6 alumnos

[0] Juan Pérez (12345) - Estructura de Datos: 85
[1] María García (12346) - Estructura de Datos: 72
[2] Carlos López (12347) - Estructura de Datos: 45
[3] Ana Martínez (12348) - Estructura de Datos: 90
[4] Pedro Sánchez (12349) - Estructura de Datos: 60
[5] Laura Gómez (12350) - Estructura de Datos: 88
=========================

Elige una opción: 6

=== ESTADÍSTICAS ===
Total alumnos: 6
Aprobados (>= 51): 5
Reprobados (< 51): 1
Promedio general: 73.33
=====================
```

## Complejidad Temporal

| Operación | Complejidad |
|-----------|-------------|
| Agregar alumno | O(1) |
| Eliminar alumno | O(n) |
| Buscar alumno | O(n) |
| Mostrar alumnos | O(n) |
| Promedio general | O(n) |
| Estadísticas | O(n) |

## Explicación del código Ruby

### Conceptos clave:
- **attr_accessor:** Crea getters y setters automáticamente
- **Array:** Contenedor dinámico para la lista
- **each_with_index:** Iterar con índice
- **find:** Buscar elemento que cumpla condición
- **select:** Filtrar elementos
- **sum:** Sumar valores de colección
- **to_f:** Convertir a float para cálculos precisos

### Características Ruby usadas:
- **Interpolación de strings:** `"#{variable}"`
- **Símbolos:** `:nombre` para identificadores
- **Bloques:** `{ |param| ... }` o `do |param| ... end`
- **Métodos bang:** métodos que modifican el objeto (como `delete`)

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
