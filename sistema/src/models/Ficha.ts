import { Paciente } from './Paciente';

export class Ficha {
  private paciente: Paciente;
  private fechaSolicitud: Date;
  private tipoServicio: string;
  private profesionalAsignado: string;
  private prioridad: number;
  private nroFicha: string;
  private siguienteNodo: Ficha | null;

  constructor(paciente: Paciente, tipoServicio: string, profesionalAsignado: string, prioridad: number, nroFicha: string) {
    this.paciente = paciente;
    this.fechaSolicitud = new Date();
    this.tipoServicio = tipoServicio;
    this.profesionalAsignado = profesionalAsignado;
    this.prioridad = prioridad;
    this.nroFicha = nroFicha;
    this.siguienteNodo = null;
  }

  public getPaciente(): Paciente { return this.paciente; }
  public getFechaSolicitud(): Date { return this.fechaSolicitud; }
  public getTipoServicio(): string { return this.tipoServicio; }
  public getProfesionalAsignado(): string { return this.profesionalAsignado; }
  public getPrioridad(): number { return this.prioridad; }
  public getNroFicha(): string { return this.nroFicha; }
  
  public getSiguienteNodo(): Ficha | null { return this.siguienteNodo; }
  public setSiguienteNodo(nodo: Ficha | null): void { this.siguienteNodo = nodo; }
}
