# ED-p4-pilas

**Materia:** Estructura de Datos I  
**Práctica 4:** Pilas (Stacks)

## Descripción
Programa en consola escrito en **C++** que implementa una pila (stack) para gestionar materiales de escritorio (lápices, bolígrafos, gomas, reglas, etc.).

## ¿Qué es una Pila?
Una pila es una estructura de datos lineal que sigue el principio **LIFO** (Last In, First Out) - el último elemento en entrar es el primero en salir. Imagina una pila de platos: solo puedes agregar o quitar el plato que está arriba.

### Características principales:
- **LIFO:** Last In, First Out
- **Operaciones limitadas:** Solo se puede acceder al elemento superior
- **Eficiente:** O(1) para push y pop
- **Aplicaciones:** Deshacer/Rehacer, navegación web, evaluación de expresiones

## Requisitos previos

### 1. Instalar C++ (g++)
```bash
# Linux/Ubuntu
sudo apt-get install g++

# Verificar instalación
g++ --version
```

### 2. Estructura del proyecto
```
Practicas/
├── pilas_escritorio.cpp   # Código fuente
└── README.md
```

## Uso

### Compilar y ejecutar:
```bash
g++ pilas_escritorio.cpp -o pilas
./pilas
```

### O compilar y ejecutar en un solo comando:
```bash
g++ pilas_escritorio.cpp && ./a.out
```

## Funcionalidades del programa
1. **Apilar (push):** Agregar material a la pila
2. **Desapilar (pop):** Quitar material de la pila
3. **Ver cima (peek):** Ver el material superior sin quitarlo
4. **Mostrar pila:** Ver todos los materiales en orden
5. **Verificar si está vacía**
6. **Verificar si está llena**

## Estructura de datos

### Clase PilaEscritorio:
```cpp
class PilaEscritorio {
private:
    vector<MaterialEscritorio> materiales;
    int tamanioMaximo;
    
public:
    void apilar(MaterialEscritorio);
    MaterialEscritorio desapilar();
    MaterialEscritorio cima();
    bool estaVacia();
    bool estaLlena();
    void mostrarPila();
};
```

### Struct MaterialEscritorio:
```cpp
struct MaterialEscritorio {
    string nombre;
    string tipo;
    int cantidad;
};
```

## Ejemplo de ejecución
```
========================================
   PILAS - MATERIALES DE ESCRITORIO
========================================

--- MENÚ PILAS DE ESCRITORIO ---
1. Apilar material
2. Desapilar material
3. Ver cima (tope)
4. Mostrar toda la pila
5. Ver si está vacía
6. Ver si está llena
7. Salir
Elige una opción: 1

Nombre del material: Lápiz
Tipo (lápiz, bolígrafo, goma, regla, etc.): lápiz
Cantidad: 5
Apilado: Lápiz (lápiz)

Elige una opción: 1
Nombre del material: Bolígrafo
Tipo (lápiz, bolígrafo, goma, regla, etc.): bolígrafo
Cantidad: 3
Apilado: Bolígrafo (bolígrafo)

Elige una opción: 4

=== PILA DE MATERIALES DE ESCRITORIO ===
Total: 2/10

[1] Bolígrafo - bolígrafo (x3)
[0] Lápiz - lápiz (x5)
==========================================

Elige una opción: 2
Desapilado: Bolígrafo (bolígrafo)
```

## Complejidad Temporal

| Operación | Complejidad |
|-----------|-------------|
| Apilar (push) | O(1) |
| Desapilar (pop) | O(1) |
| Ver cima (peek) | O(1) |
| Verificar vacía | O(1) |
| Mostrar pila | O(n) |

## Explicación del código C++

### Librerías utilizadas:
- `<iostream>`: Entrada/salida
- `<string>`: Manejo de cadenas
- `<vector>`: Contenedor dinámico para la pila
- `<limits>`: Limpiar buffer de entrada

### Conceptos clave:
- **vector::push_back()**: Agregar al final (cima de la pila)
- **vector::pop_back()**: Eliminar del final
- **vector::back()`: Acceder al último elemento
- **vector::empty()**: Verificar si está vacío

## Diferencias C++ vs Python vs Go

| Aspecto | C++ | Python | Go |
|---------|-----|--------|-----|
| Tipado | Estático | Dinámico | Estático |
| Compilación | Necesaria | No | Necesaria |
| Contenedor pila | vector | list | slice |
| Gestión memoria | Manual | Automática | Automática |
| Rendimiento | Máximo | Medio | Alto |

## Autor
Raul Heredia
