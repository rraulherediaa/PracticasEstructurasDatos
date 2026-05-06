# 🏥 Código de las 7 Estructuras de Datos - Sistema Hospitalario
> **Proyecto:** Hospital Rufus | **Materia:** Estructura de Datos 1

Este documento es una guía interactiva del código fuente que da vida al sistema. Haz clic en cada sección para desplegar el código y los detalles técnicos.

---

## 🗺️ Índice de Contenidos
*   [1. Arreglos Estáticos](#1-arreglos-estáticos-o1)
*   [2. Listas Enlazadas](#2-listas-enlazadas-memoria-dinámica)
*   [3. Cola de Prioridad](#3-cola-de-prioridad-priority-queue)
*   [4. Insertion Sort](#4-algoritmo-de-ordenamiento-por-inserción-insertion-sort)
*   [5. Pilas LIFO](#5-pilas-lifo-stacks)
*   [6. Skip List](#6-skip-list-lista-por-saltos)
*   [7. Recursividad](#7-recursividad)

---

## 📦 1. Arreglos Estáticos `O(1)`
*Ideales para catálogos que no cambian con frecuencia.*

<details>
<summary><b>📂 Ver Código: ArregloProfesionales.ts y ArregloServicios.ts</b></summary>

### ArregloProfesionales.ts
```typescript
export class ArregloProfesionales {
  private profesionales: string[];
  private cantidad: number;

  constructor(capacidad: number) {
    this.profesionales = new Array(capacidad);
    this.cantidad = 0;
  }

  public agregarProfesional(nombre: string): void {
    if (this.cantidad < this.profesionales.length) {
      this.profesionales[this.cantidad] = nombre;
      this.cantidad++;
    }
  }

  public obtenerProfesional(indice: number): string | null {
    if (indice >= 0 && indice < this.cantidad) {
      return this.profesionales[indice];
    }
    return null;
  }
}
```

### ArregloServicios.ts
```typescript
export class ArregloServicios {
  private tiposServicio: string[];
  private prioridades: number[];
  private cantidad: number;

  constructor(capacidad: number) {
    this.tiposServicio = new Array(capacidad);
    this.prioridades = new Array(capacidad);
    this.cantidad = 0;
  }

  public agregarServicio(tipo: string, prioridad: number): void {
    if (this.cantidad < this.tiposServicio.length) {
      this.tiposServicio[this.cantidad] = tipo;
      this.prioridades[this.cantidad] = prioridad;
      this.cantidad++;
    }
  }

  public obtenerPrioridad(tipo: string): number {
    for (let i = 0; i < this.cantidad; i++) {
      if (this.tiposServicio[i] === tipo) {
        return this.prioridades[i];
      }
    }
    return 99;
  }
}
```
</details>

---

## 🔗 2. Listas Enlazadas (Memoria Dinámica)
*La base de la flexibilidad de nuestro sistema.*

<details>
<summary><b>📂 Ver Código: NodoFicha.ts y NodoSkipList.ts</b></summary>

### NodoFicha.ts
```typescript
import { Ficha } from '../models/Ficha';

export class NodoFicha {
  private ficha: Ficha;
  private siguiente: NodoFicha | null;

  constructor(ficha: Ficha) {
    this.ficha = ficha;
    this.siguiente = null;
  }

  public getFicha(): Ficha { return this.ficha; }
  public getSiguiente(): NodoFicha | null { return this.siguiente; }
  public setSiguiente(nodo: NodoFicha | null): void { this.siguiente = nodo; }
}
```

### NodoSkipList.ts
```typescript
export class NodoSkipList {
  private paciente: Paciente;
  private siguiente: (NodoSkipList | null)[];

  constructor(paciente: Paciente, nivel: number) {
    this.paciente = paciente;
    this.siguiente = new Array(nivel).fill(null);
  }
}
```
</details>

---

## 🚑 3. Cola de Prioridad (Priority Queue)
*Atención inteligente según la urgencia del paciente.*

<details>
<summary><b>📂 Ver Código: ColaPrioridadHospital.ts</b></summary>

```typescript
export class ColaPrioridadHospital {
  private frente: Ficha | null;
  private tamaño: number;

  public encolar(f: Ficha): void {
    if (this.estaVacia()) {
      this.frente = f;
    } else if (f.getPrioridad() < this.frente!.getPrioridad()) {
      f.setSiguienteNodo(this.frente);
      this.frente = f;
    } else {
      // Aplicando Estructura 4: Insertion Sort
      let actual = this.frente;
      while (actual !== null && actual.getSiguienteNodo() !== null &&
             actual.getSiguienteNodo()!.getPrioridad() <= f.getPrioridad()) {
        actual = actual.getSiguienteNodo();
      }
      f.setSiguienteNodo(actual!.getSiguienteNodo());
      actual!.setSiguienteNodo(f);
    }
    this.tamaño++;
  }
}
```
</details>

---

## 🔢 4. Algoritmo de Ordenamiento por Inserción
*Garantiza que el paciente más grave siempre esté adelante.*

> [!TIP]
> Este algoritmo se ejecuta dentro de la Cola de Prioridad para mantener los elementos ordenados en tiempo real.

```typescript
// Lógica de búsqueda de posición
while (actual.getSiguienteNodo() !== null && 
       actual.getSiguienteNodo()!.getPrioridad() <= f.getPrioridad()) {
  actual = actual.getSiguienteNodo();
}
```

---

## 📚 5. Pilas LIFO (Stacks)
*Último atendido, primero en el historial.*

<details>
<summary><b>📂 Ver Código: PilaHistorial.ts</b></summary>

```typescript
export class PilaHistorial {
  private cima: NodoPila | null;

  public apilar(ficha: Ficha): void {
    const nuevoNodo = new NodoPila(ficha);
    nuevoNodo.setSiguiente(this.cima);
    this.cima = nuevoNodo;
  }

  public desapilar(): Ficha | null {
    if (this.cima === null) return null;
    const nodo = this.cima;
    this.cima = this.cima.getSiguiente();
    return nodo.getFicha();
  }
}
```
</details>

---

## ⚡ 6. Skip List (Lista por Saltos) `O(log n)`
*Búsqueda ultrarrápida mediante niveles de "carriles rápidos".*

<details>
<summary><b>📂 Ver Código: SkipListPacientes.ts</b></summary>

```typescript
export class SkipListPacientes {
  private cabeza: NodoSkipList;
  private nivelMaximo: number = 16;

  public buscar(nroDoc: string): Paciente | null {
    let actual = this.cabeza;
    for (let i = this.nivelActual - 1; i >= 0; i--) {
      while (actual.getSiguiente()[i] !== null &&
             actual.getSiguiente()[i]!.getPaciente().getNroDoc().localeCompare(nroDoc) < 0) {
        actual = actual.getSiguiente()[i]!;
      }
    }
    actual = actual.getSiguiente()[0];
    return (actual && actual.getPaciente().getNroDoc() === nroDoc) ? actual.getPaciente() : null;
  }
}
```
</details>

---

## 🔄 7. Recursividad
*Recorrido elegante sin ciclos imperativos.*

<details>
<summary><b>📂 Ver Código: Métodos Recursivos</b></summary>

### Recorrido de Cola
```typescript
public recorrerRecursivo(nodo: Ficha | null, accion: (ficha: Ficha) => void): void {
  if (nodo === null) return; // Caso base
  accion(nodo);
  this.recorrerRecursivo(nodo.getSiguienteNodo(), accion); // Llamada recursiva
}
```

### Conteo en Skip List
```typescript
private contarRecursivo(nodo: NodoSkipList | null): number {
  if (nodo === null) return 0;
  return 1 + this.contarRecursivo(nodo.getSiguiente()[0]);
}
```
</details>

---

## 📊 Resumen de Rendimiento
| Estructura | Archivo | Complejidad |
| :--- | :--- | :--- |
| **Arreglos** | `ArregloProfesionales` | $O(1)$ |
| **Listas Enlazadas** | `NodoFicha` | $O(n)$ |
| **Cola Prioridad** | `ColaPrioridadHospital` | $O(n)$ |
| **Pila LIFO** | `PilaHistorial` | $O(1)$ |
| **Skip List** | `SkipListPacientes` | $O(\log n)$ |
| **Recursividad** | `recorrerRecursivo` | $O(n)$ |

---
<div align="center">
  <b>Desarrollado por Raúl Heredia © 2026</b>
</div>
