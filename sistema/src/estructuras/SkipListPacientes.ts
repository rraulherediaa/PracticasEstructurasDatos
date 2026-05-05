import { Paciente } from '../models/Paciente';
import { NodoSkipList } from '../models/NodoSkipList';

export class SkipListPacientes {
  private cabeza: NodoSkipList;
  private nivelMaximo: number;
  private probabilidad: number;

  constructor() {
    this.nivelMaximo = 16;
    this.probabilidad = 0.5;
    // Dummy head con un paciente vacío para facilitar la lógica
    this.cabeza = new NodoSkipList(new Paciente("", "", 0, "", ""), this.nivelMaximo);
  }

  public insertar(p: Paciente): void {
    const actualizaciones: (NodoSkipList | null)[] = new Array(this.nivelMaximo).fill(null);
    let actual = this.cabeza;

    for (let i = this.nivelMaximo - 1; i >= 0; i--) {
      while (
        actual.getSiguiente()[i] !== null &&
        actual.getSiguiente()[i]!.getPaciente().getNroDoc().localeCompare(p.getNroDoc()) < 0
      ) {
        actual = actual.getSiguiente()[i]!;
      }
      actualizaciones[i] = actual;
    }

    actual = actual.getSiguiente()[0] || this.cabeza;

    if (actual.getPaciente().getNroDoc() !== p.getNroDoc()) {
      const nuevoNivel = this.nivelAleatorio();
      const nuevoNodo = new NodoSkipList(p, nuevoNivel);

      for (let i = 0; i < nuevoNivel; i++) {
        nuevoNodo.getSiguiente()[i] = actualizaciones[i]!.getSiguiente()[i];
        actualizaciones[i]!.getSiguiente()[i] = nuevoNodo;
      }
    }
  }

  public buscar(nroDoc: string): Paciente | null {
    let actual = this.cabeza;

    for (let i = this.nivelMaximo - 1; i >= 0; i--) {
      while (
        actual.getSiguiente()[i] !== null &&
        actual.getSiguiente()[i]!.getPaciente().getNroDoc().localeCompare(nroDoc) < 0
      ) {
        actual = actual.getSiguiente()[i]!;
      }
    }

    actual = actual.getSiguiente()[0] || this.cabeza;

    if (actual.getPaciente().getNroDoc() === nroDoc) {
      return actual.getPaciente();
    }
    return null;
  }

  private nivelAleatorio(): number {
    let nivel = 1;
    while (Math.random() < this.probabilidad && nivel < this.nivelMaximo) {
      nivel++;
    }
    return nivel;
  }

  public listarTodos(): Paciente[] {
    const result: Paciente[] = [];
    let actual = this.cabeza.getSiguiente()[0];
    
    while (actual !== null) {
      result.push(actual.getPaciente());
      actual = actual.getSiguiente()[0];
    }
    
    return result;
  }
}
