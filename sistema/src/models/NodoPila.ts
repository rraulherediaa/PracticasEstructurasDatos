import { Ficha } from '../models/Ficha';

export class NodoPila {
  private ficha: Ficha;
  private siguiente: NodoPila | null;

  constructor(ficha: Ficha) {
    this.ficha = ficha;
    this.siguiente = null;
  }

  public getFicha(): Ficha { return this.ficha; }
  public getSiguiente(): NodoPila | null { return this.siguiente; }
  public setSiguiente(nodo: NodoPila | null): void { this.siguiente = nodo; }
}
