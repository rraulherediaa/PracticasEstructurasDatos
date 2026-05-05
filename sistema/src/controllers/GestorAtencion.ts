import * as fs from 'fs';
import * as path from 'path';
import { Paciente } from '../models/Paciente';
import { Ficha } from '../models/Ficha';
import { ColaPrioridadHospital } from '../estructuras/ColaPrioridadHospital';
import { PilaHistorial } from '../estructuras/PilaHistorial';
import { SkipListPacientes } from '../estructuras/SkipListPacientes';
import { ArregloProfesionales } from '../estructuras/ArregloProfesionales';
import { ArregloServicios } from '../estructuras/ArregloServicios';

export class GestorAtencion {
  private colaPrincipal: ColaPrioridadHospital;
  private historialAtenciones: PilaHistorial;
  private profesionales: ArregloProfesionales;
  private servicios: ArregloServicios;
  private pacientes: SkipListPacientes;
  private contadorEme: number;
  private contadorN: number;
  private dbPath: string;

  constructor() {
    this.colaPrincipal = new ColaPrioridadHospital();
    this.historialAtenciones = new PilaHistorial();
    this.pacientes = new SkipListPacientes();
    this.contadorEme = 1;
    this.contadorN = 1;
    this.dbPath = path.join(__dirname, '../../data.json');

    // Inicializar catálogos
    this.profesionales = new ArregloProfesionales(20);
    this.profesionales.agregarProfesional('Dr. Carlos Mendoza');
    this.profesionales.agregarProfesional('Dra. Ana Suarez');
    this.profesionales.agregarProfesional('Dr. Luis Rojas');
    this.profesionales.agregarProfesional('Dra. Maria Fernandez');
    this.profesionales.agregarProfesional('Dr. Roberto Castro');
    this.profesionales.agregarProfesional('Dra. Sofia Luna');
    this.profesionales.agregarProfesional('Dra. Elena Vargas');
    this.profesionales.agregarProfesional('Dr. Javier Pineda');
    this.profesionales.agregarProfesional('Dra. Carmen Diaz');
    this.profesionales.agregarProfesional('Dr. Miguel Angel Perez');
    this.profesionales.agregarProfesional('Dr. Fernando Ruiz');
    this.profesionales.agregarProfesional('Dr. Hector Salas');
    this.profesionales.agregarProfesional('Dra. Patricia Gomez');
    this.profesionales.agregarProfesional('Dr. Ricardo Silva');
    this.profesionales.agregarProfesional('Dra. Lucia Medina');

    this.servicios = new ArregloServicios(20);
    this.servicios.agregarServicio('Emergencia / Urgente', 1);
    this.servicios.agregarServicio('Normal', 2);

    this.cargarDatos();
  }

  private cargarDatos(): void {
    if (fs.existsSync(this.dbPath)) {
      try {
        const raw = fs.readFileSync(this.dbPath, 'utf-8');
        const data = JSON.parse(raw);
        
        this.contadorEme = data.contadorEme || 1;
        this.contadorN = data.contadorN || 1;

        for (const p of data.pacientes || []) {
          const paciente = new Paciente(p.nombre, p.apellidos, p.edad, p.nroDoc, p.telefono);
          this.pacientes.insertar(paciente);
        }

        for (const f of data.cola || []) {
          const paciente = this.pacientes.buscar(f.pacienteDoc);
          if (paciente) {
             const ficha = new Ficha(paciente, f.tipoServicio, f.profesionalAsignado, f.prioridad, f.nroFicha);
             this.colaPrincipal.encolar(ficha);
          }
        }

        const histArray = data.historial || [];
        for (let i = histArray.length - 1; i >= 0; i--) {
          const f = histArray[i];
          const paciente = this.pacientes.buscar(f.pacienteDoc);
          if (paciente) {
             const ficha = new Ficha(paciente, f.tipoServicio, f.profesionalAsignado, f.prioridad, f.nroFicha);
             this.historialAtenciones.apilar(ficha);
          }
        }
      } catch (error) {
        console.error("Error al cargar datos:", error);
      }
    }
  }

  private guardarDatos(): void {
    try {
      const data = {
        contadorEme: this.contadorEme,
        contadorN: this.contadorN,
        pacientes: this.pacientes.listarTodos().map(p => ({
          nombre: p.getNombre(),
          apellidos: p.getApellidos(),
          edad: p.getEdad(),
          nroDoc: p.getNroDoc(),
          telefono: p.getTelefono()
        })),
        cola: this.colaPrincipal.listarFichas().map(f => ({
          pacienteDoc: f.getPaciente().getNroDoc(),
          tipoServicio: f.getTipoServicio(),
          profesionalAsignado: f.getProfesionalAsignado(),
          prioridad: f.getPrioridad(),
          nroFicha: f.getNroFicha()
        })),
        historial: this.historialAtenciones.listarHistorial().map(f => ({
          pacienteDoc: f.getPaciente().getNroDoc(),
          tipoServicio: f.getTipoServicio(),
          profesionalAsignado: f.getProfesionalAsignado(),
          prioridad: f.getPrioridad(),
          nroFicha: f.getNroFicha()
        }))
      };
      fs.writeFileSync(this.dbPath, JSON.stringify(data, null, 2), 'utf-8');
    } catch (error) {
      console.error("Error al guardar datos:", error);
    }
  }

  public getCatalogosUI() {
    const profs = this.profesionales.listarProfesionales();
    const servs = this.servicios.listarServicios();
    return {
      gravedades: servs,
      especialidades: [
        { especialidad: 'Cardiología', doctor: profs[0] },
        { especialidad: 'Pediatría', doctor: profs[1] },
        { especialidad: 'Traumatología', doctor: profs[2] },
        { especialidad: 'Medicina General', doctor: profs[3] },
        { especialidad: 'Neurología', doctor: profs[4] },
        { especialidad: 'Dermatología', doctor: profs[5] },
        { especialidad: 'Ginecología', doctor: profs[6] },
        { especialidad: 'Oftalmología', doctor: profs[7] },
        { especialidad: 'Oncología', doctor: profs[8] },
        { especialidad: 'Psiquiatría', doctor: profs[9] },
        { especialidad: 'Urología', doctor: profs[10] },
        { especialidad: 'Otorrinolaringología', doctor: profs[11] },
        { especialidad: 'Endocrinología', doctor: profs[12] },
        { especialidad: 'Gastroenterología', doctor: profs[13] },
        { especialidad: 'Neumología', doctor: profs[14] }
      ]
    };
  }

  public generarFicha(datos: any): Ficha {
    let paciente = this.pacientes.buscar(datos.nroDoc);
    
    if (!paciente) {
      paciente = new Paciente(datos.nombre, datos.apellidos, parseInt(datos.edad), datos.nroDoc, datos.telefono);
      this.pacientes.insertar(paciente);
    }

    const prioridad = this.servicios.obtenerPrioridad(datos.gravedad);
    
    const isEmergencia = prioridad === 1;
    const prefGravedad = isEmergencia ? 'EME' : 'N';
    const prefEsp = datos.especialidad.substring(0, 3).toUpperCase();
    
    let numeroStr = '';
    if (isEmergencia) {
      numeroStr = this.contadorEme.toString().padStart(3, '0');
      this.contadorEme++;
    } else {
      numeroStr = this.contadorN.toString().padStart(3, '0');
      this.contadorN++;
    }
    
    const nroFicha = `${prefGravedad}-${prefEsp}-${numeroStr}`;
    const ficha = new Ficha(paciente, datos.especialidad, datos.profesionalAsignado, prioridad, nroFicha);
    
    this.colaPrincipal.encolar(ficha);
    this.guardarDatos();
    return ficha;
  }

  public atenderSiguiente(): Ficha | null {
    const ficha = this.colaPrincipal.desencolar();
    if (ficha) {
      this.historialAtenciones.apilar(ficha);
      this.guardarDatos();
    }
    return ficha;
  }

  public cancelarSiguiente(): Ficha | null {
    const ficha = this.colaPrincipal.desencolar();
    if (ficha) {
      this.guardarDatos();
    }
    return ficha;
  }

  public listarCola(): any[] {
    return this.colaPrincipal.listarFichas().map(f => ({
      nroFicha: f.getNroFicha(),
      nombre: f.getPaciente().getNombre() + ' ' + f.getPaciente().getApellidos(),
      edad: f.getPaciente().getEdad(),
      profesionalAsignado: f.getProfesionalAsignado(),
      tipoServicio: f.getTipoServicio(),
      prioridad: f.getPrioridad()
    }));
  }

  public listarHistorial(): any[] {
    return this.historialAtenciones.listarHistorial().map(f => ({
      nroFicha: f.getNroFicha(),
      nombre: f.getPaciente().getNombre() + ' ' + f.getPaciente().getApellidos(),
      tipoServicio: f.getTipoServicio()
    }));
  }

  public listarPacientes(): any[] {
    return this.pacientes.listarTodos().map(p => ({
      nroDoc: p.getNroDoc(),
      nombreCompleto: p.getNombre() + ' ' + p.getApellidos(),
      edad: p.getEdad(),
      telefono: p.getTelefono()
    }));
  }

  public buscarPaciente(nroDoc: string): any | null {
    const p = this.pacientes.buscar(nroDoc);
    if (p) {
      return {
        nroDoc: p.getNroDoc(),
        nombreCompleto: p.getNombre() + ' ' + p.getApellidos(),
        edad: p.getEdad(),
        telefono: p.getTelefono()
      };
    }
    return null;
  }

  public resetearDatos(): void {
    // Vaciar estructuras de memoria
    this.colaPrincipal = new ColaPrioridadHospital();
    this.historialAtenciones = new PilaHistorial();
    this.pacientes = new SkipListPacientes();
    
    // Resetear contadores de tickets
    this.contadorEme = 1;
    this.contadorN = 1;
    
    // Borrar archivo físico si existe
    if (fs.existsSync(this.dbPath)) {
      try {
        fs.unlinkSync(this.dbPath);
      } catch (error) {
        console.error("Error al borrar data.json", error);
      }
    }
  }
}
