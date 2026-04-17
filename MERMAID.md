# Diagramas Mermaid - Funciones Recursivas

## 1. Suma de los Primeros N Números Naturales

### Diagrama de Flujo

```mermaid
flowchart TD
    A[Inicio: sumaNaturales n] --> B{n == 0?}
    B -->|Sí| C[Retornar 0]
    B -->|No| D[Retornar n + sumaNaturales n-1]
    D --> E[Fin]
    C --> E
```

### Diagrama de Recursión (Ejemplo: sumaNaturales(5))

```mermaid
graph TD
    A[sumaNaturales 5] --> B[5 + sumaNaturales 4]
    B --> C[5 + 4 + sumaNaturales 3]
    C --> D[5 + 4 + 3 + sumaNaturales 2]
    D --> E[5 + 4 + 3 + 2 + sumaNaturales 1]
    E --> F[5 + 4 + 3 + 2 + 1 + sumaNaturales 0]
    F --> G[5 + 4 + 3 + 2 + 1 + 0]
    G --> H[Resultado: 15]
```

---

## 2. Contar Dígitos de un Número

### Diagrama de Flujo

```mermaid
flowchart TD
    A[Inicio: contarDigitos n] --> B{n == 0?}
    B -->|Sí| C[Retornar 1]
    B -->|No| D{Math.abs n < 10?}
    D -->|Sí| E[Retornar 1]
    D -->|No| F[Retornar 1 + contarDigitos n/10]
    F --> G[Fin]
    C --> G
    E --> G
```

### Diagrama de Recursión (Ejemplo: contarDigitos(1234))

```mermaid
graph TD
    A[contarDigitos 1234] --> B[1 + contarDigitos 123]
    B --> C[1 + 1 + contarDigitos 12]
    C --> D[1 + 1 + 1 + contarDigitos 1]
    D --> E[1 + 1 + 1 + 1]
    E --> F[Resultado: 4]
```

---

## 3. Máximo Común Divisor (MCD) - Algoritmo de Euclides

### Diagrama de Flujo

```mermaid
flowchart TD
    A[Inicio: mcd a, b] --> B{b == 0?}
    B -->|Sí| C[Retornar a]
    B -->|No| D[Retornar mcd b, a % b]
    D --> E[Fin]
    C --> E
```

### Diagrama de Recursión (Ejemplo: mcd(48, 18))

```mermaid
graph TD
    A[mcd 48, 18] --> B[mcd 18, 12]
    B --> C[mcd 12, 6]
    C --> D[mcd 6, 0]
    D --> E[Resultado: 6]
```

---

## 4. Invertir un Número Entero

### Diagrama de Flujo

```mermaid
flowchart TD
    A[Inicio: invertir n] --> B{n == 0?}
    B -->|Sí| C[Retornar 0]
    B -->|No| D[Llamar invertirAux n, 0]
    D --> E[invertirAux n, acum]
    E --> F{n == 0?}
    F -->|Sí| G[Retornar acum]
    F -->|No| H[Retornar invertirAux n/10, acum*10 + n%10]
    H --> E
    G --> I[Fin]
    C --> I
```

### Diagrama de Recursión (Ejemplo: invertir(1234))

```mermaid
graph TD
    A[invertir 1234] --> B[invertirAux 1234, 0]
    B --> C[invertirAux 123, 4]
    C --> D[invertirAux 12, 43]
    D --> E[invertirAux 1, 432]
    E --> F[invertirAux 0, 4321]
    F --> G[Resultado: 4321]
```

---

## 5. Potencia usando Multiplicaciones Sucesivas

### Diagrama de Flujo

```mermaid
flowchart TD
    A[Inicio: potencia base, exp] --> B{exp == 0?}
    B -->|Sí| C[Retornar 1]
    B -->|No| D[Retornar base * potencia base, exp-1]
    D --> E[Fin]
    C --> E
```

### Diagrama de Recursión (Ejemplo: potencia(2, 3))

```mermaid
graph TD
    A[potencia 2, 3] --> B[2 * potencia 2, 2]
    B --> C[2 * 2 * potencia 2, 1]
    C --> D[2 * 2 * 2 * potencia 2, 0]
    D --> E[2 * 2 * 2 * 1]
    E --> F[Resultado: 8]
```

---

## 6. Verificar si un Número es Primo

### Diagrama de Flujo

```mermaid
flowchart TD
    A[Inicio: esPrimo n] --> B{n <= 1?}
    B -->|Sí| C[Retornar false]
    B -->|No| D{n == 2?}
    D -->|Sí| E[Retornar true]
    D -->|No| F[Llamar esPrimoAux n, 2]
    F --> G[esPrimoAux n, divisor]
    G --> H{divisor * divisor > n?}
    H -->|Sí| I[Retornar true]
    H -->|No| J{n % divisor == 0?}
    J -->|Sí| K[Retornar false]
    J -->|No| L[Retornar esPrimoAux n, divisor + 1]
    L --> G
    I --> M[Fin]
    K --> M
    C --> M
    E --> M
```

### Diagrama de Recursión (Ejemplo: esPrimo(17))

```mermaid
graph TD
    A[esPrimo 17] --> B[esPrimoAux 17, 2]
    B --> C[17 % 2 != 0, continuar]
    C --> D[esPrimoAux 17, 3]
    D --> E[17 % 3 != 0, continuar]
    E --> F[esPrimoAux 17, 4]
    F --> G[4 * 4 > 17, es primo]
    G --> H[Resultado: true]
```

---

## Pila de Ejecución (Call Stack)

```mermaid
graph LR
    A[sumaNaturales 5] --> B[sumaNaturales 4]
    B --> C[sumaNaturales 3]
    C --> D[sumaNaturales 2]
    D --> E[sumaNaturales 1]
    E --> F[sumaNaturales 0]
    F --> G[Retorno: 0]
    G --> H[Retorno: 1]
    H --> I[Retorno: 3]
    I --> J[Retorno: 6]
    J --> K[Retorno: 10]
    K --> L[Retorno: 15]
```
