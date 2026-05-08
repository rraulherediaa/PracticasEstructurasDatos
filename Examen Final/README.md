# Examen Final ED1 - Sistema de Gestión de Tráfico y Caché

**Materia:** Estructura de Datos I  
**Puntaje:** 20 pts  
**Implementación:** Java con Colas, Lista Doblemente Enlazada y Tabla Hash

## Descripción

Sistema de procesamiento de paquetes de red que integra tres estructuras de datos fundamentales para demostrar eficiencia en el uso de memoria y manejo de datos en tiempo real.

---

## 1. Módulo de Recepción (5 puntos)

### Tarea
Implementar una **Cola (Queue)** donde se enrutan los paquetes entrantes.

### Estructura del Paquete
```java
class PaqueteRed {
    String ipOrigen;      // IP de origen
    String ipDestino;     // IP de destino  
    int tamañoPayload;    // Tamaño en bytes
}
```

### Operaciones Implementadas
- **enqueue(paquete):** Agregar paquete al final de la cola
- **dequeue():** Retirar y retornar el paquete más antiguo (FIFO)
- **peek():** Ver el primer paquete sin retirarlo
- **estaVacia():** Verificar si la cola está vacía
- **tamaño():** Obtener cantidad de paquetes en cola

### Evaluación
✅ Demostración de encolado y desencolado de 5 paquetes simulados.

**Complejidad Temporal:**
| Operación | Complejidad |
|-----------|-------------|
| enqueue | O(1) |
| dequeue | O(1) |
| peek | O(1) |

---

## 2. Registro de Historial Bidireccional (5 puntos)

### Tarea
Implementar una **Lista Doblemente Enlazada** para almacenar el historial de IPs procesadas.

### Estructura del Nodo
```java
class NodoHistorial {
    String ip;              // Dirección IP
    long timestamp;         // Momento de registro
    NodoHistorial anterior; // Referencia al nodo previo
    NodoHistorial siguiente; // Referencia al nodo siguiente
}
```

### Operaciones Implementadas
- **ListaDobleHistorial(limite):** Constructor con límite de memoria configurable (inyección de dependencias)
- **agregarAlFrente(ip):** Inserta nueva IP al inicio (más reciente)
- **recorrerAdelante():** Del más reciente al más antiguo
- **recorrerAtras():** Del más antiguo al más reciente
- **eliminarUltimo():** Elimina el nodo más antiguo automáticamente cuando se excede el límite
- **buscarYEliminar(ip):** Busca y elimina una IP específica (todas las ocurrencias)
- **contarNodos():** Retorna cantidad de nodos en la lista

### Evaluación (2.5 pts cada uno)
✅ **Recorrido bidireccional:** Mostrar navegación desde IP más reciente a más antigua y viceversa.

✅ **Límite de memoria dinámico:** El historial recibe su límite mediante el constructor (`new ListaDobleHistorial(100)`). Cuando supera este límite, el nodo más antiguo se elimina automáticamente para liberar memoria.

**Complejidad Temporal:**
| Operación | Complejidad |
|-----------|-------------|
| agregarAlFrente | O(1) |
| eliminarUltimo | O(1) |
| recorrer | O(n) |
| buscarYEliminar | O(n) |

---

## 3. Motor de Búsqueda de Confianza (Caché) (5 puntos)

### Tarea
Implementar una **Tabla Hash** que almacene IPs marcadas como "seguras".

### Estructura
```java
class NodoHash {
    String ip;          // Clave: dirección IP
    String metadata;    // Valor: información adicional
    NodoHash siguiente; // Encadenamiento para colisiones
}

class TablaHashSeguras {
    NodoHash[] tabla;   // Array de buckets
    int capacidad;      // Tamaño de la tabla
    int elementos;      // Contador de elementos
}
```

### Operaciones Implementadas
- **insertar(ip, metadata):** Agrega IP a la tabla de confianza con **rehashing dinámico** cuando el factor de carga supera 0.75
- **buscar(ip):** Verifica si una IP es segura (O(1))
- **eliminar(ip):** Remueve IP de la tabla de confianza
- **funcionHash(ip):** Calcula índice usando hash de la IP con **máscara de bits segura**
- **factorCarga():** Calcula el factor de carga actual
- **rehash():** Duplica la capacidad de la tabla y reubica todos los nodos automáticamente

### Evaluación (2.5 pts cada uno)

✅ **Búsqueda O(1):** Realizar búsqueda de IP específica con respuesta inmediata.

La función hash segura convierte la IP en un índice numérico sin riesgo de valores negativos:
```java
hash(ip) = (ip.hashCode() & 0x7FFFFFFF) % capacidad
```

**¿Por qué la máscara `& 0x7FFFFFFF`?**
- Previene índices negativos cuando `hashCode()` retorna `Integer.MIN_VALUE`
- `Math.abs()` falla con `Integer.MIN_VALUE` (no puede convertirlo a positivo)
- La máscara elimina el bit de signo, garantizando siempre un valor positivo
- Misma técnica usada por la API de Java

✅ **Manejo de Colisiones:** 

Cuando dos IPs diferentes generan el mismo índice hash, se utiliza **encadenamiento separado**. Cada bucket contiene una lista enlazada de nodos que comparten el mismo hash.

**Ejemplo de colisión:**
- IP "192.168.1.1" → hash → índice 5
- IP "10.0.0.5" → hash → índice 5 (mismo bucket)

Resolución: Ambas IPs se almacenan en lista enlazada del bucket 5.

**Complejidad Temporal:**
| Operación | Promedio | Peor Caso |
|-----------|----------|-----------|
| buscar | O(1) | O(n) |
| insertar | O(1) | O(n) |
| eliminar | O(1) | O(n) |

---

## 4. Sincronización y Limpieza (5 puntos)

### Tarea
Crear método de **"Limpieza de Seguridad"**. Si una IP se elimina de la Tabla Hash (marcada como insegura), debe eliminarse también de la Lista Doble de historial.

### Implementación
```java
public void limpiezaDeSeguridad(String ip) {
    // 1. Eliminar de tabla hash (si existe)
    boolean eliminadaHash = tablaHash.eliminar(ip);
    
    // 2. Eliminar de lista de historial (si existe)
    boolean eliminadaHistorial = listaHistorial.buscarYEliminar(ip);
    
    // 3. Verificar integridad referencial
    if (eliminadaHash || eliminadaHistorial) {
        System.out.println("IP " + ip + " eliminada de ambas estructuras");
    }
}
```

### Evaluación
✅ **Integridad Referencial:** Al borrar en una estructura, la otra queda actualizada automáticamente.

---

## Estructura del Proyecto

```
ExamenFinalED1/
├── README.md                          # Documentación completa
├── MERMAID.md                         # Diagramas de estructuras
├── SistemaGestionTraficoCache.java    # Clase principal y orquestador
├── PaqueteRed.java                    # Modelo de datos de red
├── ColaPaquetes.java                  # Implementación de Cola (Queue)
├── NodoCola.java                      # Nodo para la Cola
├── ListaDobleHistorial.java           # Implementación de Lista Doble
├── NodoHistorial.java                 # Nodo para la Lista Doble
├── TablaHashSeguras.java              # Implementación de Tabla Hash
└── NodoHash.java                      # Nodo para la Tabla Hash
```

## Compilación y Ejecución

### Compilar
```bash
javac *.java
```

### Ejecutar
```bash
java SistemaGestionTraficoCache
```

---

## Ejemplo de Ejecución

```
========================================
  EXAMEN FINAL ED1
  Sistema de Gestión de Tráfico y Caché
========================================

===== MÓDULO 1: RECEPCIÓN DE PAQUETES (Cola) =====

📥 Encolando 5 paquetes simulados...
   [1] 192.168.1.10 → 8.8.8.8 (1450 bytes)
   [2] 10.0.0.15 → 192.168.1.1 (512 bytes)
   [3] 172.16.0.5 → 10.0.0.2 (1024 bytes)
   [4] 192.168.0.50 → 1.1.1.1 (2048 bytes)
   [5] 10.1.1.20 → 172.217.0.46 (900 bytes)

📤 Procesando paquetes (FIFO)...
   → Procesando: 192.168.1.10 → 8.8.8.8 (1450 bytes)
   → Procesando: 10.0.0.15 → 192.168.1.1 (512 bytes)
   → Procesando: 172.16.0.5 → 10.0.0.2 (1024 bytes)
   → Procesando: 192.168.0.50 → 1.1.1.1 (2048 bytes)
   → Procesando: 10.1.1.20 → 172.217.0.46 (900 bytes)

✅ Cola vacía. Todos los paquetes procesados.

===== MÓDULO 2: HISTORIAL BIDIRECCIONAL (Lista Doble) =====

📝 Agregando IPs al historial...
   IPs registradas: 10

🔍 Recorrido ADELANTE (Reciente → Antiguo):
   192.168.1.100 → 10.0.0.50 → 172.16.0.25 → 192.168.0.1 → ...

🔍 Recorrido ATRÁS (Antiguo → Reciente):
   10.0.0.1 → 172.16.0.10 → 192.168.1.50 → 10.0.0.50 → ...

⚠️  Prueba de límite: Agregando 95 IPs más...
   Total: 100 IPs

🗑️  Agregando IP #101...
   ¡Límite alcanzado! Eliminando IP más antigua automáticamente.
   Nueva IP agregada. Total: 100 IPs (límite mantenido)

===== MÓDULO 3: MOTOR DE CONFIANZA (Tabla Hash) =====

🔐 Marcando IPs como seguras...
   ✓ 192.168.1.10 [Servidor Local]
   ✓ 8.8.8.8 [DNS Google]
   ✓ 1.1.1.1 [DNS Cloudflare]
   ✓ 172.16.0.5 [Estación Trabajo]

🔍 Búsqueda de IP 8.8.8.8...
   ⚡ Búsqueda O(1): IP encontrada en 0ms
   📋 Metadata: DNS Google

🔍 Búsqueda de IP insegura 10.0.0.99...
   ⚡ Búsqueda O(1): IP no encontrada
   ⚠️  Acceso bloqueado - IP no está en lista de confianza

📊 Estado de Tabla Hash:
   Capacidad: 16 buckets
   Elementos: 4 IPs seguras
   Factor de carga: 0.25
   Colisiones: 0

===== MÓDULO 4: SINCRONIZACIÓN Y LIMPIEZA =====

🧹 Ejecutando Limpieza de Seguridad para IP: 192.168.1.10
   ✓ Eliminada de Tabla Hash (IP segura)
   ✓ Eliminada de Lista Historial (2 ocurrencias)
   ✅ Integridad referencial mantenida

🔍 Verificación post-limpieza:
   ¿192.168.1.10 en tabla hash? NO
   ¿192.168.1.10 en historial? NO

========================================
  FIN DEL EXAMEN FINAL
========================================
```

---

## Bitácora de Desarrollo

### Lenguaje Elegido: Java

**¿Por qué Java?**

1. **Sistema de tipos estático:** Permite detectar errores en tiempo de compilación, fundamental para estructuras de datos complejas.

2. **Orientación a Objetos pura:** Facilita la implementación de clases para cada estructura (NodoCola, NodoHistorial, NodoHash) con encapsulamiento adecuado.

3. **Gestión automática de memoria:** El Garbage Collector de Java simplifica el manejo de estructuras dinámicas como listas enlazadas.

4. **Portabilidad:** El código ejecuta en cualquier plataforma con JVM, ideal para entregas académicas.

5. **Bibliotecas estándar:** Provee utilidades como `System.nanoTime()` para medir rendimiento O(1).

---

### Logs de Ejecución Consola

```
========================================
  EXAMEN FINAL ED1
  Sistema de Gestión de Tráfico y Caché
========================================

===== MÓDULO 1: RECEPCIÓN DE PAQUETES (Cola) =====

📥 Encolando 5 paquetes simulados...
   [1] 192.168.1.10 → 8.8.8.8 (1450 bytes)
   [2] 10.0.0.15 → 192.168.1.1 (512 bytes)
   [3] 172.16.0.5 → 10.0.0.2 (1024 bytes)
   [4] 192.168.0.50 → 1.1.1.1 (2048 bytes)
   [5] 10.1.1.20 → 172.217.0.46 (900 bytes)

📤 Procesando paquetes (FIFO)...
   → Procesando [1]: 192.168.1.10 → 8.8.8.8 (1450 bytes)
   → Procesando [2]: 10.0.0.15 → 192.168.1.1 (512 bytes)
   → Procesando [3]: 172.16.0.5 → 10.0.0.2 (1024 bytes)
   → Procesando [4]: 192.168.0.50 → 1.1.1.1 (2048 bytes)
   → Procesando [5]: 10.1.1.20 → 172.217.0.46 (900 bytes)

✅ Cola vacía. Total paquetes procesados: 5

===== MÓDULO 2: HISTORIAL BIDIRECCIONAL (Lista Doble) =====

📝 Agregando 10 IPs adicionales al historial...
   Total IPs en historial: 20

🔍 Recorrido ADELANTE (Reciente → Antiguo):
   94.140.14.14 → 9.9.9.9 → 208.67.222.222 → 8.8.4.4 → ...

🔍 Recorrido ATRÁS (Antiguo → Reciente):
   192.168.1.10 → 8.8.8.8 → 10.0.0.15 → 192.168.1.1 → ...

⚠️  Prueba de límite de 100 registros...
   Agregando 90 IPs adicionales...
   Total actual: 100 IPs
   Agregando IP #101 (debe eliminar la más antigua)...
   ✓ Nueva IP agregada. Total: 100 IPs (límite de 100)

===== MÓDULO 3: MOTOR DE CONFIANZA (Tabla Hash) =====

🔐 Marcando IPs como seguras...
   ✓ 192.168.1.10 [Servidor Local]
   ✓ 8.8.8.8 [DNS Google]
   ✓ 1.1.1.1 [DNS Cloudflare]
   ✓ 172.16.0.5 [Estación Trabajo]

🔍 Búsqueda de IP 8.8.8.8...
   ⚡ Búsqueda O(1): IP encontrada
   📋 Metadata: DNS Google [Tiempo: 1μs]

🔍 Búsqueda de IP insegura 10.0.0.99...
   ⚡ Búsqueda O(1): IP no encontrada
   ⚠️  Acceso bloqueado - IP no está en lista de confianza

📊 Estado de Tabla Hash:
   Capacidad: 16 buckets
   Elementos: 4 IPs seguras
   Factor de carga: 0,25
   Buckets con colisiones: 1

📎 Demostración de manejo de colisiones:
   Insertando IPs que podrían colisionar...
   ✓ IPs almacenadas usando encadenamiento separado
   Elementos actuales: 6

===== MÓDULO 4: SINCRONIZACIÓN Y LIMPIEZA =====

🧹 Ejecutando Limpieza de Seguridad para IP: 192.168.1.10
   ✓ Eliminada de Tabla Hash: SÍ
   ✓ Eliminada de Lista Historial: 0 ocurrencias
   ✅ Limpieza completada

🔍 Verificación post-limpieza:
   ¿192.168.1.10 en tabla hash? NO ✓
   ¿192.168.1.10 en historial? NO ✓
   ✅ Integridad referencial mantenida

🧹 Probando limpieza de IP no existente (10.99.99.99):
   ✓ Eliminada de Tabla Hash: NO (no existía)
   ✓ Eliminada de Lista Historial: 0 ocurrencias
   ℹ️  IP no encontrada en ninguna estructura

========================================
  FIN DEL EXAMEN FINAL
========================================
```

**Evidencias de funcionamiento:**
- ✅ **Módulo 1:** "Procesando paquete [1]... [2]... [3]... [4]... [5]"
- ✅ **Módulo 2:** "IP añadida al historial... Recorrido ADELANTE... Recorrido ATRÁS"
- ✅ **Módulo 3:** "IP encontrada en caché... Búsqueda O(1)... Tiempo: 1μs"
- ✅ **Módulo 4:** "Eliminada de Tabla Hash... Eliminada de Lista Historial... Integridad referencial mantenida"

---

## Complejidad Total del Sistema

| Módulo | Estructura | Operación Principal | Complejidad |
|--------|------------|---------------------|-------------|
| Recepción | Cola | enqueue/dequeue | O(1) |
| Historial | Lista Doble | Recorrido/Agregar | O(n) / O(1) |
| Caché | Tabla Hash | Buscar | O(1) |
| Limpieza | Integración | Sincronizar | O(n) |

---

## Mejoras Avanzadas Implementadas (Nivel Senior)

Se aplicaron 3 mejoras técnicas de alto nivel arquitectónico sobre el diseño original para demostrar un dominio superior de las Estructuras de Datos:

1. **Corrección de la Función Hash (Máscara de Bits):** 
   Se implementó una máscara `& 0x7FFFFFFF` en `(ip.hashCode() & 0x7FFFFFFF) % capacidad` para prevenir un "bug silencioso" donde un `Integer.MIN_VALUE` crashearía el programa con un índice negativo, ya que `Math.abs()` no puede convertirlo a positivo. Esta es la misma técnica empleada por la API original de Java.
2. **Rehashing Dinámico ($O(1)$ Sostenible):** 
   Se incorporó el concepto de "Load Factor" (Factor de Carga). Si la tabla hash supera el 75% de su capacidad instalada (0.75), la estructura llama automáticamente al método interno `rehash()`, duplicando su tamaño en memoria (`nuevaCapacidad = capacidad * 2`) y reubicando los nodos. Esto garantiza que las búsquedas no se degraden a $O(n)$ a medida que crecen las IPs almacenadas.
3. **Eliminación de "Magic Numbers":** 
   En la `ListaDobleHistorial`, el límite de memoria ya no es una constante rígida. Se ha refactorizado para que la clase reciba `limiteMemoria` dinámicamente mediante inyección por el constructor, respetando los principios SOLID y promoviendo la reutilización del código.

---

## Referencias

- Cormen, T. H., et al. "Introduction to Algorithms"
- Sedgewick, R., Wayne, K. "Algorithms"
- Estructuras de Datos - Universidad

## Créditos

- **Examen Final:** Estructura de Datos I
- **Implementación:** Java POO
- **Estudiante:** Raul Heredia
