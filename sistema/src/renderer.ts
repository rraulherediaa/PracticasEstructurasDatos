interface Window {
  api: {
    getCatalogos: () => Promise<any>;
    generarFicha: (datos: any) => Promise<any>;
    atenderSiguiente: () => Promise<any>;
    cancelarSiguiente: () => Promise<any>;
    getCola: () => Promise<any>;
    getHistorial: () => Promise<any>;
    getPacientes: () => Promise<any>;
    buscarPaciente: (ci: string) => Promise<any>;
    resetearSistema: () => Promise<any>;
    onPacienteLlamado: (callback: (data: any) => void) => void;
  }
}

document.addEventListener('DOMContentLoaded', async () => {
  // Navegación (Tabs)
  const navBtns = document.querySelectorAll('.nav-btn');
  const views = document.querySelectorAll('.view');

  navBtns.forEach(btn => {
    btn.addEventListener('click', () => {
      // Remover active de todos
      navBtns.forEach(b => b.classList.remove('active'));
      views.forEach(v => v.classList.remove('active-view'));
      
      // Agregar active al clickeado
      btn.classList.add('active');
      const targetId = btn.getAttribute('data-target');
      if (targetId) {
        document.getElementById(targetId)?.classList.add('active-view');
        
        // Refresh data based on view
        if (targetId === 'view-historial') {
          cargarHistorial();
        } else if (targetId === 'view-pacientes') {
          cargarPacientes();
        } else if (targetId === 'view-consultorio') {
          cargarCola();
        }
      }
    });
  });

  // Referencias a DOM
  const form = document.getElementById('fichaForm') as HTMLFormElement;
  const selectGravedad = document.getElementById('gravedad') as HTMLSelectElement;
  const selectEspecialidad = document.getElementById('especialidad') as HTMLSelectElement;
  const inputProfesional = document.getElementById('profesional') as HTMLInputElement;
  const tablaCola = document.getElementById('tablaCola') as HTMLTableSectionElement;
  const tablaHistorial = document.getElementById('tablaHistorial') as HTMLTableSectionElement;
  const tablaPacientes = document.getElementById('tablaPacientes') as HTMLTableSectionElement;
  
  const btnAtender = document.getElementById('btnAtender') as HTMLButtonElement;
  const btnCancelar = document.getElementById('btnCancelar') as HTMLButtonElement;

  // 1. Cargar Catálogos
  const catalogos = await window.api.getCatalogos();
  
  catalogos.gravedades.forEach((s: string) => {
    const opt = document.createElement('option');
    opt.value = s;
    opt.innerText = s;
    selectGravedad.appendChild(opt);
  });

  catalogos.especialidades.forEach((item: any) => {
    const opt = document.createElement('option');
    opt.value = item.especialidad;
    opt.innerText = item.especialidad;
    selectEspecialidad.appendChild(opt);
  });

  const actualizarDoctor = () => {
    const especialidadElegida = selectEspecialidad.value;
    const espObj = catalogos.especialidades.find((e: any) => e.especialidad === especialidadElegida);
    if (espObj) {
      inputProfesional.value = espObj.doctor;
    }
  };

  selectEspecialidad.addEventListener('change', actualizarDoctor);
  // Set initial value
  if (catalogos.especialidades.length > 0) {
    actualizarDoctor();
  }

  // 2. Renderizar Cola
  const renderizarCola = (cola: any[]) => {
    tablaCola.innerHTML = '';
    cola.forEach(ficha => {
      const tr = document.createElement('tr');
      tr.innerHTML = `
        <td><span style="font-weight: bold; color: var(--primary);">${ficha.nroFicha}</span></td>
        <td>${ficha.nombre}</td>
        <td>${ficha.edad}</td>
        <td>${ficha.profesionalAsignado}</td>
        <td>${ficha.tipoServicio}</td>
        <td><span class="badge p-${ficha.prioridad}">${ficha.prioridad === 1 ? 'Urgencia' : 'Normal'}</span></td>
      `;
      tablaCola.appendChild(tr);
    });
  };

  const renderizarHistorial = (historial: any[]) => {
    tablaHistorial.innerHTML = '';
    historial.forEach(ficha => {
      const tr = document.createElement('tr');
      tr.innerHTML = `
        <td><span style="font-weight: bold;">${ficha.nroFicha}</span></td>
        <td>${ficha.nombre}</td>
        <td>${ficha.tipoServicio}</td>
      `;
      tablaHistorial.appendChild(tr);
    });
  };

  const renderizarPacientes = (pacientes: any[]) => {
    tablaPacientes.innerHTML = '';
    pacientes.forEach(paciente => {
      const tr = document.createElement('tr');
      tr.innerHTML = `
        <td>${paciente.nroDoc}</td>
        <td>${paciente.nombreCompleto}</td>
        <td>${paciente.edad}</td>
        <td>${paciente.telefono}</td>
      `;
      tablaPacientes.appendChild(tr);
    });
  };

  // Cargas Iniciales y Auxiliares
  const cargarCola = async () => {
    const cola = await window.api.getCola();
    renderizarCola(cola);
  };
  const cargarHistorial = async () => {
    const hist = await window.api.getHistorial();
    renderizarHistorial(hist);
  };
  const cargarPacientes = async () => {
    const pac = await window.api.getPacientes();
    renderizarPacientes(pac);
  };

  await cargarCola();

  // 3. Eventos
  form.addEventListener('submit', async (e) => {
    e.preventDefault();
    
    const datos = {
      nombre: (document.getElementById('nombre') as HTMLInputElement).value,
      apellidos: (document.getElementById('apellidos') as HTMLInputElement).value,
      edad: (document.getElementById('edad') as HTMLInputElement).value,
      nroDoc: (document.getElementById('nroDoc') as HTMLInputElement).value,
      telefono: (document.getElementById('telefono') as HTMLInputElement).value,
      gravedad: selectGravedad.value,
      especialidad: selectEspecialidad.value,
      profesionalAsignado: inputProfesional.value
    };

    const res = await window.api.generarFicha(datos);
    if (res.success) {
      renderizarCola(res.cola);
      form.reset(); // Limpiar form
      actualizarDoctor(); // Reset doctor based on first especialidad
      (document.getElementById('nombre') as HTMLInputElement).focus();
    } else {
      alert("Error: " + res.error);
    }
  });

  btnAtender.addEventListener('click', async () => {
    const res = await window.api.atenderSiguiente();
    if (res.success) {
      renderizarCola(res.cola);
    } else {
      alert("No hay pacientes en la cola.");
    }
  });

  btnCancelar.addEventListener('click', async () => {
    if (confirm("¿Estás seguro de cancelar la ficha de mayor prioridad sin atenderla?")) {
      const res = await window.api.cancelarSiguiente();
      if (res.success) {
        renderizarCola(res.cola);
      } else {
        alert("No hay fichas para cancelar.");
      }
    }
  });

  const inputBuscador = document.getElementById('buscadorSkipList') as HTMLInputElement;
  const btnBuscarSkipList = document.getElementById('btnBuscarSkipList') as HTMLButtonElement;
  const btnLimpiarBusqueda = document.getElementById('btnLimpiarBusqueda') as HTMLButtonElement;
  const resultadoBusqueda = document.getElementById('resultadoBusqueda') as HTMLDivElement;
  const contenedorTablaPacientes = document.getElementById('contenedorTablaPacientes') as HTMLDivElement;

  btnBuscarSkipList.addEventListener('click', async () => {
    const ci = inputBuscador.value.trim();
    if (!ci) return;

    const paciente = await window.api.buscarPaciente(ci);
    
    if (paciente) {
      (document.getElementById('resDoc') as HTMLSpanElement).innerText = paciente.nroDoc;
      (document.getElementById('resNombre') as HTMLSpanElement).innerText = paciente.nombreCompleto;
      (document.getElementById('resEdad') as HTMLSpanElement).innerText = paciente.edad;
      (document.getElementById('resTel') as HTMLSpanElement).innerText = paciente.telefono;
      
      resultadoBusqueda.style.display = 'block';
      contenedorTablaPacientes.style.display = 'none';
      btnLimpiarBusqueda.style.display = 'inline-block';
    } else {
      alert('Paciente no encontrado en la SkipList.');
    }
  });

  btnLimpiarBusqueda.addEventListener('click', () => {
    inputBuscador.value = '';
    resultadoBusqueda.style.display = 'none';
    contenedorTablaPacientes.style.display = 'block';
    btnLimpiarBusqueda.style.display = 'none';
  });

  const btnReset = document.getElementById('btnReset') as HTMLButtonElement;
  if (btnReset) {
    btnReset.addEventListener('click', async () => {
      if (confirm("🚨 PRECAUCIÓN 🚨\n\n¿Estás seguro de que quieres borrar TODOS los pacientes, vaciar el historial y reiniciar las fichas a 001?\n\nEsta acción no se puede deshacer.")) {
        const res = await window.api.resetearSistema();
        if (res.success) {
          window.location.reload();
        }
      }
    });
  }

});