import { NodoPila } from '../models/NodoPila';
import { Ficha } from '../models/Ficha';

export class PilaHistorial {
  private cima: NodoPila | null;
  private tamaño: number;

  constructor() {
    this.cima = null;
    this.tamaño = 0;
  }

  public estaVacia(): boolean {
    return this.tamaño === 0;
  }

  public apilar(ficha: Ficha): void {
    const nuevoNodo = new NodoPila(ficha);
    nuevoNodo.setSiguiente(this.cima);
    this.cima = nuevoNodo;
    this.tamaño++;
  }

  public desapilar(): Ficha | null {
    if (this.estaVacia()) return null;

    const nodo = this.cima;
    this.cima = this.cima!.getSiguiente();
    this.tamaño--;

    return nodo!.getFicha();
  }

  public verCima(): Ficha | null {
    if (this.estaVacia()) return null;
    return this.cima!.getFicha();
  }

  public listarHistorial(): Ficha[] {
    const result: Ficha[] = [];
    let actual = this.cima;
    while (actual !== null) {
      result.push(actual.getFicha());
      actual = actual.getSiguiente();
    }
    return result;
  }
}
