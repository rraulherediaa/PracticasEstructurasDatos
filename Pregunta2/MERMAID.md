# MERMAID - Diagramas de los Ejemplos

**Examen Primer Parcial:** Implementación de Quick Sort en Java

Este archivo contiene todos los diagramas Mermaid de los ejemplos (casos de uso) implementados en Main.java.

---

## Diagrama - Caso 1: Quick Sort de Enteros

```mermaid
flowchart TD
    A[Arreglo original<br/>64 34 25 12 22 11 90] --> B[QuickSort 0-6]
    B --> C[Partición<br/>Pivote: 90]
    C --> D[Sub-arreglo izquierdo<br/>64 34 25 12 22 11]
    C --> E[Sub-arreglo derecho<br/>vacío]
    D --> F[QuickSort 0-5]
    F --> G[Partición<br/>Pivote: 11]
    G --> H[Sub-arreglo izquierdo<br/>vacío]
    G --> I[Sub-arreglo derecho<br/>64 34 25 12 22]
    I --> J[QuickSort 1-5]
    J --> K[Partición<br/>Pivote: 22]
    K --> L[Sub-arreglo izquierdo<br/>12]
    K --> M[Sub-arreglo derecho<br/>64 34 25]
    M --> N[QuickSort 3-5]
    N --> O[Arreglo ordenado<br/>11 12 22 25 34 64 90]
```

---

## Diagrama - Caso 2: Quick Sort de Strings

```mermaid
flowchart TD
    A[Arreglo original<br/>z a m k b] --> B[QuickSort 0-4]
    B --> C[Partición<br/>Pivote: b]
    C --> D[Sub-arreglo izquierdo<br/>a]
    C --> E[Sub-arreglo derecho<br/>z m k]
    D --> F[QuickSort 0-0<br/>a ordenado]
    E --> G[QuickSort 1-4]
    G --> H[Partición<br/>Pivote: k]
    H --> I[Sub-arreglo izquierdo<br/>vacío]
    H --> J[Sub-arreglo derecho<br/>z m]
    J --> K[QuickSort 3-4]
    K --> L[Partición<br/>Pivote: m]
    L --> M[Sub-arreglo izquierdo<br/>vacío]
    L --> N[Sub-arreglo derecho<br/>z]
    N --> O[Arreglo ordenado<br/>a b k m z]
```

---

## Diagrama - Caso 3: Quick Sort de Personas (por edad)

```mermaid
flowchart TD
    A[Arreglo original<br/>Juan 30<br/>María 25<br/>Carlos 35<br/>Ana 28] --> B[QuickSort 0-3]
    B --> C[Partición<br/>Pivote: Ana 28]
    C --> D[Sub-arreglo izquierdo<br/>María 25]
    C --> E[Sub-arreglo derecho<br/>Juan 30<br/>Carlos 35]
    D --> F[QuickSort 0-0<br/>María ordenado]
    E --> G[QuickSort 1-3]
    G --> H[Partición<br/>Pivote: Carlos 35]
    H --> I[Sub-arreglo izquierdo<br/>Juan 30]
    H --> J[Sub-arreglo derecho<br/>vacío]
    I --> K[QuickSort 1-1<br/>Juan ordenado]
    K --> L[Arreglo ordenado<br/>María 25<br/>Ana 28<br/>Juan 30<br/>Carlos 35]
```

---

## Diagrama - Flujo General de Quick Sort

```mermaid
flowchart TD
    A[Inicio] --> B{low < high?}
    B -->|No| C[Fin]
    B -->|Sí| D[Partición<br/>elegir pivote]
    D --> E[QuickSort<br/>sub-arreglo izquierdo]
    D --> F[QuickSort<br/>sub-arreglo derecho]
    E --> B
    F --> B
```

---

## Diagrama - Proceso de Partición

```mermaid
flowchart TD
    A[Elegir pivote<br/>último elemento] --> B[i = low - 1]
    B --> C{j < high?}
    C -->|Sí| D{arr[j] < pivot?}
    D -->|Sí| E[i++<br/>swap i, j]
    D -->|No| F[j++]
    E --> F
    F --> C
    C -->|No| G[swap i+1, high]
    G --> H[Retornar i+1]
```

---

**Autor:** Raul Heredia
