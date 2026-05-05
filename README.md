<div align="center">
  <h1>🏥 Hospital Rufus</h1>
  <p><i>Sistema Avanzado de Gestión Médica & Estructuras de Datos</i></p>
  
  ![TypeScript](https://img.shields.io/badge/TypeScript-007ACC?style=for-the-badge&logo=typescript&logoColor=white)
  ![Electron.js](https://img.shields.io/badge/Electron-191970?style=for-the-badge&logo=Electron&logoColor=white)
  ![HTML5](https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white)
  ![CSS3](https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white)
  ![Node.js](https://img.shields.io/badge/Node.js-43853D?style=for-the-badge&logo=node.js&logoColor=white)
</div>

<br/>

> **📚 Proyecto Académico:** Demostración práctica de competencias para la materia de **Estructura de Datos 1**. Este sistema renuncia a las bases de datos comerciales tradicionales para implementar motores de búsqueda y colas personalizados **100% en memoria RAM**, demostrando el funcionamiento interno de las estructuras.

---

## 🚀 Flujo del Sistema (Cómo Funciona)

El sistema simula un hospital real utilizando una arquitectura de doble ventana interactiva. Así es el ciclo de vida de un paciente:

| Paso | Acción en la Clínica | Estructura / Tecnología Responsable |
| :---: | :--- | :--- |
| **1️⃣** | **Triaje:** La enfermera registra la gravedad y especialidad. | `Interfaz UI` + `Arreglos Estáticos` |
| **2️⃣** | **Clasificación:** El sistema asigna un ticket inteligente (`EME-CAR-001`). | `Algoritmo de Ordenamiento Inserción` |
| **3️⃣** | **Espera:** El paciente entra en la sala según su nivel de urgencia. | `Cola de Prioridad (Listas Enlazadas)` |
| **4️⃣** | **Llamado:** El médico presiona el botón "Atender". | `Google TTS (Síntesis de Voz)` e `IPC` |
| **5️⃣** | **Archivo:** El paciente pasa al historial médico para registro. | `Pila (Stack LIFO)` |
| **6️⃣** | **Directorio:** Búsqueda ultrarrápida del paciente por Carnet. | `SkipList Probabilística` |

---

## 🧠 Estructuras de Datos Interactivas

*¡Haz clic en cada bloque a continuación para descubrir cómo funciona la estructura de datos por debajo del capó!*

<details>
<summary><b>👉 1. Arreglos Estáticos (Catálogos) ⏱️ <code>O(1)</code></b></summary>
<br>

- **Ubicación en el código:** `ArregloProfesionales.ts` y `ArregloServicios.ts`
- **¿Para qué sirve?:** Almacena catálogos inmutables del hospital (como los nombres de los doctores y las especialidades médicas). 
- **La Magia:** Nos brinda un acceso directo e instantáneo a las configuraciones sin tener que recorrer punteros, ideal para información que no va a cambiar durante el día.

</details>

<details>
<summary><b>👉 2. Listas Enlazadas (Memoria Dinámica)</b></summary>
<br>

- **Ubicación en el código:** Clases Base (`NodoFicha`, `NodoSkipList`)
- **¿Para qué sirve?:** Es el bloque de construcción fundamental de todo el sistema. Cada paciente es un "Nodo" que apunta al siguiente (`siguienteNodo`).
- **La Magia:** Permite memoria dinámica perfecta. El hospital puede recibir 1 o 1.000 pacientes sin desperdiciar memoria RAM asignando bloques vacíos como lo haría un Arreglo estático.

</details>

<details>
<summary><b>👉 3. Cola de Prioridad (Priority Queue) ⏱️ <code>O(1) Extracción</code></b></summary>
<br>

- **Ubicación en el código:** `ColaPrioridadHospital.ts`
- **¿Para qué sirve?:** No es una fila normal (FIFO). Si llega un paciente sangrando con gravedad "Emergencia", esta estructura rompe la fila y lo coloca inmediatamente al frente para ser atendido de primero.

</details>

<details>
<summary><b>👉 4. Algoritmo de Ordenamiento (Insertion Sort) ⏱️ <code>O(n)</code></b></summary>
<br>

- **Ubicación en el código:** Método `encolar()`
- **¿Para qué sirve?:** Trabaja junto con la Cola de Prioridad. Cuando llega un paciente nuevo, el algoritmo recorre la lista comparando niveles de urgencia e "inserta" el nodo en el lugar matemáticamente correcto de la cadena.

</details>

<details>
<summary><b>👉 5. Pilas (Stacks LIFO) ⏱️ <code>O(1)</code></b></summary>
<br>

- **Ubicación en el código:** `PilaHistorial.ts`
- **¿Para qué sirve?:** Funciona como un montón de platos. Cada vez que el médico atiende a alguien, ese paciente se "apila" en la cima del historial. El último paciente atendido es siempre el primero que ves.
- **La Magia:** Al usar la filosofía Último en Entrar, Primero en Salir (LIFO), el sistema queda preparado para añadir un botón de "Deshacer" (Undo) en caso de que el médico se equivoque al llamar.

</details>

<details>
<summary><b>👉 6. Skip List (El Buscador Mágico) ⏱️ <code>O(log n)</code></b></summary>
<br>

- **Ubicación en el código:** `SkipListPacientes.ts`
- **¿Para qué sirve?:** En lugar de revisar paciente por paciente para encontrar a alguien (lo cual sería lento en un hospital masivo), usa "carriles rápidos" aleatorios creados por probabilidad y lanzamiento de monedas.
- **La Magia:** Encuentra un Carnet de Identidad casi instantáneamente saltando a través de múltiples niveles, dándonos la eficiencia de un Árbol Binario Balanceado pero con un algoritmo mucho más elegante.

</details>

<details>
<summary><b>👉 7. Recursividad</b></summary>
<br>

- **Ubicación en el código:** Método `recorrerRecursivo()`
- **¿Para qué sirve?:** En vez de usar un ciclo `while` tradicional para extraer los datos e imprimirlos en la pantalla, la función se llama a sí misma pasando el testigo al siguiente nodo hasta llegar al final. Es la demostración máxima de control del *Stack de ejecución* del procesador.

</details>

<br>

---

## 🎨 Toques Profesionales (Bonus)
Además de las estructuras puras, el software implementa detalles dignos de un sistema comercial:
- **Persistencia (Rehidratación de Datos):** El estado de la memoria RAM (SkipList, Colas y Pilas) se auto-guarda en un archivo local (`data.json`) en tiempo real. Al reiniciar la aplicación, el sistema lee el archivo y vuelve a inyectar nodo por nodo, recuperando a todos los pacientes en espera y el historial sin perder un solo dato.
- **Pantalla Dividida IPC:** El backend de Node dispara una segunda ventana flotante (Visor de Sala de Espera) comunicándose de manera asíncrona mediante mensajes *Inter-Process Communication* (IPC).
- **Voz Artificial Automática:** Al desencolar a un paciente, la IA de Google dicta el ticket en voz alta (Ej: *"Ficha E M E, C A R, cero cero uno"*) manteniendo la privacidad del nombre del paciente.
- **Estética "Verde Salvia":** Una paleta UI/UX de tonos menta y grises suaves elegidos para transmitir relajación y evitar la fatiga visual del personal médico.

<br>

<div align="center">
  <b>Desarrollado con 💚 por Raúl Heredia</b><br>
  <i>Para la demostración de Estructura de Datos 1 © 2026. Todos los derechos reservados.</i>
</div>
