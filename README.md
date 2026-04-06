# EA-p3-short

**Materia:** Estructura de Datos I  
**Práctica 3:** Ordenamiento Shell Sort en Go

## Descripción
Programa en consola escrito en **Go (Golang)** que solicita exactamente **10 números** y los ordena utilizando el algoritmo Shell Sort.

## ¿Qué es Go?
Go (o Golang) es un lenguaje de programación desarrollado por Google. Es compilado, estáticamente tipado y diseñado para ser simple, eficiente y concurrente.

### Características principales:
- **Compilado:** El código se compila a código máquina nativo
- **Rendimiento:** Similar a C/C++ pero más simple
- **Gestión de memoria:** Tiene recolector de basura (garbage collector)
- **Tipado estático:** Detecta errores en tiempo de compilación
- **Sintaxis limpia:** Fácil de leer y escribir

## Requisitos previos

### 1. Instalar Go
Descarga e instala Go desde: https://go.dev/dl/

**Verificar instalación:**
```bash
go version
```

### 2. Estructura del proyecto
```
Practicas/
├── go.mod          # Módulo de Go
├── shell_sort.go   # Código fuente
└── README.md
```

## Configuración inicial

Crear módulo de Go (ejecutar una sola vez):
```bash
go mod init practica3
```

## Uso

### Compilar y ejecutar:
```bash
go run shell_sort.go
```

### O compilar primero y luego ejecutar:
```bash
go build shell_sort.go
./shell_sort
```

## Funcionalidades del programa
1. **Ingresar exactamente 10 números:** El programa solicita 10 números uno por uno
2. **Validación de entrada:** Si se ingresa algo que no es número, muestra error y vuelve a pedir
3. **Ordenamiento automático:** Aplica Shell Sort inmediatamente después de recibir los 10 números
4. **Ver resultado:** Muestra la lista original y la ordenada

## Algoritmo Shell Sort

El Shell Sort es una generalización del ordenamiento por inserción que permite el intercambio de elementos distantes. Comienza con un `gap` grande y lo va reduciendo hasta 1.

### Implementación en Go:
```go
func shellSort(arr []int) []int {
    n := len(arr)
    gap := n / 2

    for gap > 0 {
        for i := gap; i < n; i++ {
            temp := arr[i]
            j := i
            for j >= gap && arr[j-gap] > temp {
                arr[j] = arr[j-gap]
                j -= gap
            }
            arr[j] = temp
        }
        gap /= 2
    }
    return arr
}
```

## Explicación del código Go

### Paquetes utilizados:
- `fmt`: Formateo de entrada/salida
- `bufio`: Lectura de entrada con buffer
- `os`: Interacción con el sistema operativo
- `strconv`: Conversión de strings a números
- `strings`: Manipulación de strings

### Tipos de datos Go usados:
- `[]int`: Slice (arreglo dinámico) de enteros
- `string`: Cadena de texto

### Funciones clave:
- `append()`: Agregar elementos a un slice
- `copy()`: Copiar slices
- `strconv.Atoi()`: Convertir string a int
- `bufio.NewReader()`: Leer entrada del usuario

## Ejemplo de ejecución
```
========================================
   ORDENAMIENTO SHELL SORT
========================================
Ingresa exactamente 10 números:
Número 1/10: 64
Número 2/10: 34
Número 3/10: 25
Número 4/10: 12
Número 5/10: 22
Número 6/10: 11
Número 7/10: 90
Número 8/10: 5
Número 9/10: 77
Número 10/10: 42

Lista original: [64 34 25 12 22 11 90 5 77 42]
Lista ordenada: [5 11 12 22 25 34 42 64 77 90]
========================================
```

## Ejemplo con entrada inválida
```
Número 3/10: abc
  Error: Ingresa un número válido.
Número 3/10: 25
```

## Diferencias Python vs Go

| Aspecto | Python | Go |
|---------|--------|-----|
| Tipo de lenguaje | Interpretado | Compilado |
| Declaración de variables | Dinámica | Estática (:= o var) |
| Arreglos | Listas | Slices |
| Lectura input | input() | bufio.Reader |
| Conversión tipo | int() | strconv.Atoi() |
| Manejo de errores | try/except | if err != nil |

## Autor
Raul Heredia
