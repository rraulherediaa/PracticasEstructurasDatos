import { Ficha } from '../models/Ficha';

export class ColaPrioridadHospital {
  private frente: Ficha | null;
  private final: Ficha | null;
  private tamaño: number;

  constructor() {
    this.frente = null;
    this.final = null;
    this.tamaño = 0;
  }

  public estaVacia(): boolean {
    return this.tamaño === 0;
  }

  public getTamaño(): number {
    return this.tamaño;
  }

  public encolar(f: Ficha): void {
    if (this.estaVacia()) {
      this.frente = f;
      this.final = f;
    } else if (this.frente && f.getPrioridad() < this.frente.getPrioridad()) {
      f.setSiguienteNodo(this.frente);
      this.frente = f;
    } else {
      let actual = this.frente;
      while (
        actual !== null &&
        actual.getSiguienteNodo() !== null &&
        actual.getSiguienteNodo()!.getPrioridad() <= f.getPrioridad()
      ) {
        actual = actual.getSiguienteNodo();
      }
      
      if (actual !== null) {
        f.setSiguienteNodo(actual.getSiguienteNodo());
        actual.setSiguienteNodo(f);
      }

      if (f.getSiguienteNodo() === null) {
        this.final = f;
      }
    }
    this.tamaño++;
  }

  public desencolar(): Ficha | null {
    if (this.estaVacia()) return null;

    const ficha = this.frente;
    this.frente = this.frente!.getSiguienteNodo();
    this.tamaño--;

    if (this.frente === null) {
      this.final = null;
    }

    // Desconectar el nodo para evitar referencias cruzadas
    if (ficha) ficha.setSiguienteNodo(null);

    return ficha;
  }

  public listarFichas(): Ficha[] {
    const result: Ficha[] = [];
    this.recorrerRecursivo(this.frente, result);
    return result;
  }

  private recorrerRecursivo(actual: Ficha | null, result: Ficha[]): void {
    if (actual === null) return;
    result.push(actual);
    this.recorrerRecursivo(actual.getSiguienteNodo(), result);
  }
}
