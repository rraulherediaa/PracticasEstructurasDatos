# Funciones Recursivas en Java

Este proyecto contiene 6 implementaciones de funciones recursivas en Java, cada una en un archivo separado. Los datos de prueba están "hardcodeados" en cada archivo para facilitar la ejecución y comprensión.

## Índice

1. [Suma de los Primeros N Números Naturales](#1-suma-de-los-primeros-n-números-naturales)
2. [Contar Dígitos de un Número](#2-contar-dígitos-de-un-número)
3. [Máximo Común Divisor (MCD) - Algoritmo de Euclides](#3-máximo-común-divisor-mcd---algoritmo-de-euclides)
4. [Invertir un Número Entero](#4-invertir-un-número-entero)
5. [Potencia usando Multiplicaciones Sucesivas](#5-potencia-usando-multiplicaciones-sucesivas)
6. [Verificar si un Número es Primo](#6-verificar-si-un-número-es-primo)

## Cómo Ejecutar

Para ejecutar cualquiera de los programas:

```bash
# Compilar el archivo
javack NombreDelArchivo.java

# Ejecutar el programa
java NombreDelArchivo
```

Ejemplo para SumaNaturales:
```bash
javack SumaNaturales.java
java SumaNaturales
```

---

## 1. Suma de los Primeros N Números Naturales

### Teoría

La suma de los primeros N números naturales es una de las funciones recursivas más básicas. La idea es sumar todos los números enteros positivos desde 1 hasta N.

**Fórmula matemática:**
```
S(N) = 1 + 2 + 3 + ... + N
```

**Definición recursiva:**
- **Caso base:** Si N = 0, la suma es 0
- **Caso recursivo:** S(N) = N + S(N-1)

### Cómo Funciona

La función descompone el problema en subproblemas más pequeños:
1. Para calcular S(N), necesitamos N más la suma de todos los números anteriores
2. Esto continúa hasta llegar al caso base (N = 0)
3. Luego, los resultados se van combinando en el camino de retorno

### Diagrama de Flujo (Mermaid)

Ver diagrama en: [diagramas.md](diagramas.md)

### Diagrama de Recursión (Ejemplo: sumaNaturales(5))

Ver diagrama en: [diagramas.md](diagramas.md)

### Archivo

`SumaNaturales.java`

### Salida de Ejemplo

```
=== Suma de los Primeros N Números Naturales ===
Número: 10
Suma de 1 hasta 10: 55

--- Ejemplos adicionales ---
Suma de 1 hasta 5: 15
Suma de 1 hasta 20: 210
Suma de 1 hasta 100: 5050
```

---

## 2. Contar Dígitos de un Número

### Teoría

Contar los dígitos de un número entero se realiza dividiendo el número por 10 sucesivamente hasta que el número es menor a 10. Cada división elimina el último dígito.

**Definición recursiva:**
- **Caso base:** Si |n| < 10, tiene 1 dígito
- **Caso recursivo:** digitos(n) = 1 + digitos(n/10)

**Nota:** El caso especial n = 0 tiene 1 dígito.

### Cómo Funciona

1. Extraemos el último dígito usando el operador módulo (%)
2. Dividimos el número por 10 para eliminar el último dígito
3. Contamos 1 dígito y recursivamente contamos los dígitos restantes
4. Continuamos hasta que el número tiene solo 1 dígito

### Diagrama de Flujo (Mermaid)

Ver diagrama en: [diagramas.md](diagramas.md)

### Diagrama de Recursión (Ejemplo: contarDigitos(1234))

Ver diagrama en: [diagramas.md](diagramas.md)

### Archivo

`ContarDigitos.java`

### Salida de Ejemplo

```
=== Contar Dígitos de un Número ===
Número: 12345
Cantidad de dígitos: 5

--- Ejemplos adicionales ---
Dígitos de 0: 1
Dígitos de 7: 1
Dígitos de 42: 2
Dígitos de 999999: 6
Dígitos de -789: 3
```

---

## 3. Máximo Común Divisor (MCD) - Algoritmo de Euclides

### Teoría

El algoritmo de Euclides es un método eficiente para calcular el Máximo Común Divisor (MCD) de dos números. Se basa en el principio de que el MCD de dos números también divide al resto de la división del mayor entre el menor.

**Definición recursiva:**
- **Caso base:** Si b = 0, entonces MCD(a, b) = a
- **Caso recursivo:** MCD(a, b) = MCD(b, a % b)

### Cómo Funciona

1. Calculamos el resto de la división de a entre b
2. Reemplazamos a con b y b con el resto
3. Repetimos hasta que el resto es 0
4. El último valor de a es el MCD

### Diagrama de Flujo (Mermaid)

Ver diagrama en: [diagramas.md](diagramas.md)

### Diagrama de Recursión (Ejemplo: mcd(48, 18))

Ver diagrama en: [diagramas.md](diagramas.md)

### Archivo

`MaximoComunDivisor.java`

### Salida de Ejemplo

```
=== Máximo Común Divisor (Algoritmo de Euclides) ===
Número 1: 48
Número 2: 18
MCD(48, 18): 6

--- Ejemplos adicionales ---
MCD(12, 8): 4
MCD(100, 25): 25
MCD(17, 13): 1
MCD(270, 192): 6
MCD(56, 98): 14
```

---

## 4. Invertir un Número Entero

### Teoría

Invertir un número significa cambiar el orden de sus dígitos. Por ejemplo, 1234 se convierte en 4321. Usamos un método auxiliar con un acumulador para construir el número invertido.

**Definición recursiva:**
- **Caso base:** Si n = 0, retornar el acumulador
- **Caso recursivo:** invertir(n, acum) = invertir(n/10, acum*10 + n%10)

### Cómo Funciona

1. Extraemos el último dígito usando n % 10
2. Multiplicamos el acumulador por 10 (desplaza los dígitos existentes)
3. Agregamos el último dígito extraído al acumulador
4. Dividimos n por 10 para eliminar el último dígito
5. Repetimos hasta que n es 0

### Diagrama de Flujo (Mermaid)

Ver diagrama en: [diagramas.md](diagramas.md)

### Diagrama de Recursión (Ejemplo: invertir(1234))

Ver diagrama en: [diagramas.md](diagramas.md)

### Archivo

`InvertirNumero.java`

### Salida de Ejemplo

```
=== Invertir un Número Entero ===
Número original: 12345
Número invertido: 54321

--- Ejemplos adicionales ---
Invertir 0: 0
Invertir 7: 7
Invertir 42: 24
Invertir 100: 1
Invertir 987654321: 123456789
Invertir 1200: 21
```

---

## 5. Potencia usando Multiplicaciones Sucesivas

### Teoría

Calculamos la potencia de un número multiplicando la base por sí misma el número de veces indicado por el exponente. No utilizamos el operador `**` ni `Math.pow()`, solo multiplicaciones sucesivas.

**Definición recursiva:**
- **Caso base:** Si exponente = 0, retornar 1 (cualquier número elevado a 0 es 1)
- **Caso recursivo:** potencia(base, exp) = base * potencia(base, exp-1)

### Cómo Funciona

1. Si el exponente es 0, retornamos 1 (caso base)
2. Multiplicamos la base por el resultado de la potencia con exponente-1
3. Esto continúa hasta que el exponente es 0
4. Los resultados se van multiplicando en el camino de retorno

### Diagrama de Flujo (Mermaid)

Ver diagrama en: [diagramas.md](diagramas.md)

### Diagrama de Recursión (Ejemplo: potencia(2, 3))

Ver diagrama en: [diagramas.md](diagramas.md)

### Archivo

`PotenciaRecursiva.java`

### Salida de Ejemplo

```
=== Potencia usando Multiplicaciones Sucesivas ===
Base: 2
Exponente: 10
2^10 = 1024

--- Ejemplos adicionales ---
2^0 = 1
3^1 = 3
5^3 = 125
2^15 = 32768
10^5 = 100000
7^4 = 2401
```

---

## 6. Verificar si un Número es Primo

### Teoría

Un número es primo si solo es divisible por 1 y por sí mismo. Para verificarlo recursivamente, intentamos dividir el número por todos los valores desde 2 hasta la raíz cuadrada del número.

**Definición recursiva:**
- **Caso base:** Si divisor² > n, es primo
- **Caso base:** Si n % divisor == 0, no es primo
- **Caso recursivo:** esPrimo(n, divisor) = esPrimo(n, divisor + 1)

**Casos especiales:**
- n <= 1: no es primo
- n = 2: es primo

### Cómo Funciona

1. Verificamos casos especiales (n <= 1, n = 2)
2. Comenzamos con divisor = 2
3. Si el divisor al cuadrado es mayor que n, el número es primo
4. Si n es divisible por el divisor, no es primo
5. Si no, incrementamos el divisor y repetimos

### Diagrama de Flujo (Mermaid)

Ver diagrama en: [diagramas.md](diagramas.md)

### Diagrama de Recursión (Ejemplo: esPrimo(17))

Ver diagrama en: [diagramas.md](diagramas.md)

### Archivo

`EsPrimo.java`

### Salida de Ejemplo

```
=== Verificar si un Número es Primo ===
Número: 17
¿Es primo? true

--- Ejemplos adicionales ---
¿Es primo 0? false
¿Es primo 1? false
¿Es primo 2? true
¿Es primo 7? true
¿Es primo 15? false
¿Es primo 23? true
¿Es primo 100? false
¿Es primo 97? true
```

---

## Conceptos Generales de Recursión

### ¿Qué es la Recursión?

La recursión es una técnica de programación donde una función se llama a sí misma para resolver subproblemas más pequeños del mismo tipo.

### Elementos de una Función Recursiva

Toda función recursiva debe tener:

1. **Caso Base:** La condición que detiene la recursión. Sin ella, la función se llamaría infinitamente (stack overflow).
2. **Caso Recursivo:** La llamada a la función misma con parámetros modificados que acercan al caso base.

### Ventajas de la Recursión

- Código más limpio y legible para ciertos problemas
- Solución natural para problemas con estructura recursiva (árboles, grafos)
- Simplifica la implementación de algoritmos divide y vencerás

### Desventajas de la Recursión

- Puede ser menos eficiente que la iteración (overhead de llamadas)
- Riesgo de stack overflow si la recursión es muy profunda
- Puede ser más difícil de depurar

### Pila de Ejecución (Call Stack)

Cuando una función se llama a sí misma, cada llamada se apila en la pila de ejecución. Cuando se alcanza el caso base, las llamadas se van desapilando y retornando sus resultados.

Ver diagrama de pila de ejecución en: [diagramas.md](diagramas.md)

---

## Requisitos

- Java Development Kit (JDK) 8 o superior
- Compilador javac
- Máquina virtual java

## Estructura del Proyecto

```
.
├── SumaNaturales.java
├── ContarDigitos.java
├── MaximoComunDivisor.java
├── InvertirNumero.java
├── PotenciaRecursiva.java
├── EsPrimo.java
└── README.md
```

## Autor

Práctica de Estructuras de Datos - Funciones Recursivas
