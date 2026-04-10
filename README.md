# ED-p2-listas

**Materia:** Estructura de Datos I  
**Práctica 2:** Listas Enlazadas

## Descripción
Programa interactivo en consola escrito en **Python** que implementa una lista enlazada simple para gestionar frutas tropicales.

## ¿Qué es una Lista Enlazada?
Una lista enlazada es una estructura de datos lineal donde cada elemento (nodo) contiene un dato y una referencia al siguiente nodo. A diferencia de los arreglos, los elementos no están en posiciones contiguas de memoria.

### Características principales:
- **Dinámica:** Tamaño puede crecer o reducirse
- **Eficiente inserción/eliminación:** O(1) si conocemos la posición
- **Acceso secuencial:** O(n) para acceder a elementos
- **Memoria dispersa:** Nodos pueden estar en cualquier posición de memoria

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
├── frutas_tropicales.py   # Código fuente
└── README.md
```

## Uso

### Ejecutar directamente:
```bash
python3 frutas_tropicales.py
```

### O simplemente:
```bash
python frutas_tropicales.py
```

## Funcionalidades del programa
1. **Ver lista:** Muestra todas las frutas en orden
2. **Agregar al inicio:** Agregar fruta al principio de la lista
3. **Agregar al final:** Agregar fruta al final de la lista
4. **Agregar en posición:** Agregar fruta en posición específica
5. **Eliminar del inicio:** Quitar primera fruta
6. **Eliminar del final:** Quitar última fruta
7. **Eliminar por posición:** Quitar fruta en posición específica
8. **Buscar fruta:** Buscar por nombre
9. **Obtener por posición:** Ver fruta en posición específica

## Estructura de datos

### Clase Nodo:
```python
class Nodo:
    def __init__(self, dato):
        self.dato = dato
        self.siguiente = None
```

### Clase ListaEnlazada:
```python
class ListaEnlazada:
    def __init__(self):
        self.cabeza = None
        self.tamanio = 0
    
    def agregar_inicio(self, dato):
        # Agregar al principio
    
    def agregar_final(self, dato):
        # Agregar al final
    
    def agregar_posicion(self, dato, posicion):
        # Agregar en posición específica
    
    def eliminar_inicio(self):
        # Eliminar del principio
    
    def eliminar_final(self):
        # Eliminar del final
    
    def buscar(self, dato):
        # Buscar elemento
```

## Frutas iniciales
- Plátano
- Piña
- Mango
- Papaya
- Sandía

## Ejemplo de ejecución
```
--- Menú Listas Enlazadas ---
1. Ver lista de frutas
2. Agregar al inicio
3. Agregar al final
4. Agregar en posición específica
5. Eliminar del inicio
6. Eliminar del final
7. Eliminar por posición
8. Buscar fruta
9. Obtener fruta por posición
10. Salir

Elige una opción: 1

=== Lista de Frutas Tropicales ===
[0] Plátano
[1] Piña
[2] Mango
[3] Papaya
[4] Sandía
================================

Elige una opción: 2
Nombre de la fruta: Naranja
Agregado al inicio: Naranja
```

## Complejidad Temporal

| Operación | Complejidad |
|-----------|-------------|
| Agregar al inicio | O(1) |
| Agregar al final | O(n) / O(1)* |
| Agregar en posición | O(n) |
| Eliminar del inicio | O(1) |
| Eliminar del final | O(n) / O(1)* |
| Eliminar por posición | O(n) |
| Buscar | O(n) |
| Obtener por posición | O(n) |

*O(1) si se mantiene referencia al final

## Explicación del código Python

### Conceptos clave:
- **Clases:** Definición de estructuras de datos
- **Self:** Referencia a la instancia actual
- **Punteros:** Referencias a otros nodos (self.siguiente)
- **Recorrido:** Navegar a través de la lista usando punteros
- **Casos especiales:** Lista vacía, un solo elemento, etc.

### Ventajas vs Desventajas

**Ventajas:**
- ✅ Tamaño dinámico
- ✅ Inserción/eliminación eficiente
- ✅ No requiere reubicación de elementos

**Desventajas:**
- ❌ Mayor uso de memoria (punteros)
- ❌ Acceso secuencial obligatorio
- ❌ Sin acceso aleatorio

## Autor
Raul Heredia
