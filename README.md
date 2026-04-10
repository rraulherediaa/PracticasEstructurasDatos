# ED-p1-arreglos

**Materia:** Estructura de Datos I  
**Práctica 1:** Arreglos (Arrays)

## Descripción
Programa interactivo en consola escrito en **Python** que gestiona un arreglo de equipos de fútbol boliviano de Primera División.

## ¿Qué es Python?
Python es un lenguaje de programación interpretado, de alto nivel y multiparadigma. Es conocido por su sintaxis clara y legible, lo que lo hace ideal para principiantes y para desarrollo rápido.

### Características principales:
- **Interpretado:** No necesita compilación
- **Tipado dinámico:** Variables no necesitan declaración de tipo
- **Sintaxis limpia:** Código legible y conciso
- **Gran ecosistema:** Extensa biblioteca estándar
- **Multi-paradigma:** Soporta POO, funcional y programación imperativa

## Requisitos previos

### 1. Instalar Python

**Linux/Ubuntu:**
```bash
sudo apt-get update
sudo apt-get install python3

# Verificar instalación
python3 --version
```

**macOS (Homebrew):**
```bash
brew install python3

# Verificar instalación
python3 --version
```

**Windows:**
Descargar desde: https://www.python.org/downloads/

### 2. Estructura del proyecto
```
Practicas/
├── equipos_futbol.py   # Código fuente
└── README.md
```

## Uso

### Ejecutar directamente:
```bash
python3 equipos_futbol.py
```

### O simplemente:
```bash
python equipos_futbol.py
```

## Funcionalidades del programa
1. **Ver equipos:** Muestra todos los equipos de fútbol
2. **Buscar equipo:** Buscar por nombre
3. **Agregar equipo:** Agregar nuevo equipo al arreglo
4. **Eliminar equipo:** Eliminar equipo por posición

## Estructura de datos

### Arreglo en Python:
```python
equipos = [
    "Club Bolívar",
    "The Strongest",
    "Oriente Petrolero",
    "Club Wilstermann",
    "Always Ready"
]
```

### Operaciones disponibles:
- `append()`: Agregar al final
- `pop()`: Eliminar del final
- `index()`: Buscar posición
- `remove()`: Eliminar por valor
- Acceso por índice: `equipos[0]`

## Ejemplo de ejecución
```
--- Menú ---
1. Ver todos los equipos
2. Buscar equipo
3. Agregar equipo
4. Eliminar equipo
5. Salir

Elige una opción: 1
[0] Club Bolívar
[1] The Strongest
[2] Oriente Petrolero
[3] Club Wilstermann
[4] Always Ready

Elige una opción: 3
Nombre del equipo: Blooming
Agregado: Blooming
```

## Complejidad Temporal

| Operación | Complejidad |
|-----------|-------------|
| Acceso por índice | O(1) |
| Agregar al final | O(1) amortizado |
| Buscar por valor | O(n) |
| Eliminar por posición | O(n) |
| Eliminar por valor | O(n) |

## Explicación del código Python

### Conceptos clave:
- **Listas:** Arreglos dinámicos en Python
- **input():** Leer entrada del usuario
- **int() / str():** Conversión de tipos
- **len():** Obtener tamaño de lista
- **enumerate():** Iterar con índice

### Características Python usadas:
- **List comprehensions:** `[x for x in iterable]`
- **Métodos de lista:** `append()`, `pop()`, `remove()`
- **Manejo de excepciones:** `try/except`

## Autor
Raul Heredia
