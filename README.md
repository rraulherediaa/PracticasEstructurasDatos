# Hashing y Diccionarios - Sistema de Gestión de Estudiantes

**Materia:** Estructura de Datos I  
**Implementación:** Tabla Hash con Encadenamiento en Java  
**Aplicación:** Sistema de Registro de Estudiantes

## Descripción

El **hashing** es una técnica que permite almacenar y recuperar datos de forma eficiente mediante una función hash que convierte una clave en un índice de tabla. Los **diccionarios** (o mapas) utilizan hashing para implementar operaciones de búsqueda, inserción y eliminación en tiempo promedio O(1).

Esta implementación utiliza **encadenamiento separado** para resolver colisiones, donde cada bucket de la tabla hash apunta a una lista de elementos que comparten el mismo índice hash.

## ¿Qué es una Tabla Hash?

Una tabla hash es una estructura de datos que:
- Almacena pares **clave-valor**
- Usa una **función hash** para calcular el índice donde se almacenará el dato
- Permite acceso directo a los elementos mediante su clave
- Resuelve **colisiones** cuando dos claves generan el mismo índice

### Características principales:

- **Acceso directo:** O(1) promedio para búsqueda, inserción y eliminación
- **Función hash:** Convierte la clave en un índice numérico
- **Colisiones:** Ocurren cuando dos claves tienen el mismo hash
- **Resolución de colisiones:** Encadenamiento o direccionamiento abierto
- **Factor de carga:** Controla cuándo redimensionar la tabla

### Analogía

Imagina un **sistema de casilleros** en una universidad:

- **Número de estudiante:** La clave única
- **Función hash:** El algoritmo que asigna casillero según el número
- **Casillero:** El bucket donde se guarda la información
- **Colisión:** Dos estudiantes asignados al mismo casillero
- **Lista de espera:** Encadenamiento para manejar colisiones

### Aplicación en Sistema de Estudiantes

En un sistema de registro académico, la tabla hash permite:

- **Matrícula rápida:** Registrar estudiantes en O(1)
- **Búsqueda inmediata:** Encontrar datos por ID de estudiante
- **Gestión eficiente:** Actualizar calificaciones y datos personales
- **Prevención de duplicados:** Detectar IDs duplicados instantáneamente

### Otras Aplicaciones

- **Bases de datos:** Índices hash para búsqueda rápida
- **Cachés de sistemas:** Almacenamiento temporal de datos frecuentes
- **Compiladores:** Tablas de símbolos para identificadores
- **Sistemas de archivos:** Asignación de bloques de disco
- **Criptografía:** Verificación de integridad de datos

## Requisitos Previos

- **Java JDK 11 o superior**
- **IDE:** IntelliJ IDEA, Eclipse, VS Code, o cualquier editor compatible con Java
- **Sistema Operativo:** Windows, Linux, macOS

## Estructura del Proyecto

```
Practicas/
├── README.md                    # Documentación completa
├── MERMAID.md                   # Diagramas Mermaid
└── HashingDiccionario.java      # Implementación de tabla hash
```

## Uso

### Compilación
```bash
javac HashingDiccionario.java
```

### Ejecución
```bash
java HashingDiccionario
```

## Estructura de Datos con POO

### Clases Principales

#### 1. Estudiante
Clase que representa un estudiante:
- **id:** Identificador único (clave)
- **nombre:** Nombre completo
- **carrera:** Carrera universitaria
- **promedio:** Promedio académico

#### 2. NodoHash
Nodo para el encadenamiento:
- **estudiante:** Dato almacenado
- **siguiente:** Referencia al siguiente nodo (colisión)

#### 3. TablaHash
Clase principal que implementa el diccionario:
- **tabla:** Array de buckets
- **tamaño:** Capacidad de la tabla
- **elementos:** Cantidad de elementos almacenados
- **función hash:** Método de dispersión

### Operaciones Principales

#### 1. Función Hash
```java
hash(id) = id % tamañoTabla
```
- Convierte el ID en un índice válido
- Distribuye elementos uniformemente
- **Complejidad:** O(1)

#### 2. Insertar (Put)
- Calcula índice con función hash
- Crea nuevo nodo en el bucket correspondiente
- Maneja colisiones con encadenamiento
- **Complejidad:** O(1) promedio, O(n) peor caso

#### 3. Buscar (Get)
- Calcula índice con función hash
- Recorre lista enlazada del bucket
- Retorna el estudiante encontrado
- **Complejidad:** O(1) promedio, O(n) peor caso

#### 4. Eliminar (Remove)
- Calcula índice con función hash
- Busca y desenlaza el nodo
- **Complejidad:** O(1) promedio, O(n) peor caso

#### 5. Contiene (ContainsKey)
- Verifica si una clave existe
- **Complejidad:** O(1) promedio

## Casos de Uso - Sistema de Estudiantes

### 1. Registrar Estudiante
```java
TablaHash registro = new TablaHash(10);
registro.put(202301, new Estudiante(202301, "Ana García", "Ingeniería", 8.5));
registro.put(202302, new Estudiante(202302, "Luis Martínez", "Medicina", 9.2));
// Hash asigna automáticamente el bucket
```

### 2. Buscar Estudiante
```java
Estudiante e = registro.get(202301);
// Salida: Ana García - Ingeniería - Promedio: 8.5
```

### 3. Verificar Existencia
```java
boolean existe = registro.containsKey(202301);
// Salida: true
```

### 4. Manejo de Colisiones
```java
// Si dos IDs generan el mismo hash:
registro.put(202305, new Estudiante(202305, "Carlos López", "Derecho", 8.0));
registro.put(202315, new Estudiante(202315, "María Soto", "Arquitectura", 9.0));
// Ambos se almacenan en el mismo bucket como lista enlazada
```

## Ejemplo de Ejecución

```
========================================
  HASHING Y DICCIONARIOS
  Sistema de Registro de Estudiantes
========================================

--- FUNCIÓN HASH ---
📐 Fórmula: hash(id) = id % 10
   Ejemplo: hash(202301) = 202301 % 10 = 1

--- INSERTANDO ESTUDIANTES ---
📝 Insertando: ID=202301, Ana García, Ingeniería, Promedio=8.5
   → Hash: 1, Bucket[1] (0 colisiones)

📝 Insertando: ID=202302, Luis Martínez, Medicina, Promedio=9.2
   → Hash: 2, Bucket[2] (0 colisiones)

📝 Insertando: ID=202305, Carlos López, Derecho, Promedio=8.0
   → Hash: 5, Bucket[5] (0 colisiones)

📝 Insertando: ID=202315, María Soto, Arquitectura, Promedio=9.0
   → Hash: 5, Bucket[5] (¡COLISIÓN! Encadenando...)

📝 Insertando: ID=202311, Pedro Ruiz, Economía, Promedio=7.8
   → Hash: 1, Bucket[1] (¡COLISIÓN! Encadenando...)

--- ESTADO DE LA TABLA HASH ---
📊 Factor de carga: 5/10 = 0.50

Bucket[0]: (vacío)
Bucket[1]: → 202301: Ana García → 202311: Pedro Ruiz
Bucket[2]: → 202302: Luis Martínez
Bucket[3]: (vacío)
Bucket[4]: (vacío)
Bucket[5]: → 202305: Carlos López → 202315: María Soto
Bucket[6-9]: (vacíos)

--- BÚSQUEDAS ---
🔍 Buscando ID 202301: Ana García - Ingeniería - 8.5
🔍 Buscando ID 202315: María Soto - Arquitectura - 9.0
🔍 Buscando ID 202399: No encontrado

--- VERIFICACIÓN ---
✓ ID 202302 existe en el registro
✗ ID 202399 no existe en el registro

========================================
  FIN DE LA DEMOSTRACIÓN
========================================
```

## Complejidad Temporal

| Operación | Promedio | Peor Caso | Espacio |
|-----------|----------|-----------|---------|
| Insertar (put) | O(1) | O(n) | O(n) |
| Buscar (get) | O(1) | O(n) | O(1) |
| Eliminar (remove) | O(1) | O(n) | O(1) |
| Contiene (containsKey) | O(1) | O(n) | O(1) |
| Función Hash | O(1) | O(1) | O(1) |

## Ventajas del Hashing

1. **Velocidad:** Acceso directo en tiempo constante
2. **Eficiencia:** Ideal para búsquedas frecuentes
3. **Flexibilidad:** Claves de cualquier tipo hashable
4. **Escalabilidad:** Redimensionamiento dinámico

## Desventajas del Hashing

1. **Colisiones:** Requieren estrategias de resolución
2. **Desorden:** Los elementos no están ordenados
3. **Memoria:** Puede desperdiciar espacio en buckets vacíos
4. **Rendimiento:** Degradación con factor de carga alto

## Comparación: Hashing vs Otras Estructuras

| Aspecto | Tabla Hash | Lista | Árbol Binario |
|---------|------------|-------|---------------|
| Búsqueda | O(1) | O(n) | O(log n) |
| Inserción | O(1) | O(1)* | O(log n) |
| Eliminación | O(1) | O(n) | O(log n) |
| Orden | No | Sí | Sí |
| Memoria | Más | Menos | Más |

*Al inicio

## Resolución de Colisiones

### Encadenamiento (Usado en esta implementación)
- Cada bucket es una lista enlazada
- Múltiples elementos pueden coexistir
- Simple de implementar
- Memoria dinámica según necesidad

### Direccionamiento Abierto
- Busca siguiente bucket disponible
- Variantes: Lineal, Cuadrático, Doble Hash
- Mejor uso de memoria
- Más complejo de implementar

## Referencias

- Cormen, T. H., et al. "Introduction to Algorithms" - Capítulo sobre Tablas Hash
- Sedgewick, R., Wayne, K. "Algorithms" - Sección sobre Hashing
- https://en.wikipedia.org/wiki/Hash_table
- https://en.wikipedia.org/wiki/Hash_function

## Créditos

- **Implementación:** Java con Programación Orientada a Objetos
- **Técnica:** Hashing con Encadenamiento Separado
- **Caso de estudio:** Sistema de Registro de Estudiantes
- **Materia:** Estructura de Datos I
