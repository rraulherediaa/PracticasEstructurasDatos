import { Paciente } from './Paciente';

export class NodoSkipList {
  private paciente: Paciente;
  private siguiente: (NodoSkipList | null)[];
  private nivel: number;

  constructor(paciente: Paciente, nivel: number) {
    this.paciente = paciente;
    this.nivel = nivel;
    this.siguiente = new Array(nivel).fill(null);
  }

  public getPaciente(): Paciente { return this.paciente; }
  public getSiguiente(): (NodoSkipList | null)[] { return this.siguiente; }
  public getNivel(): number { return this.nivel; }
}
