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

  public listarProfesionales(): string[] {
    return this.profesionales.slice(0, this.cantidad);
  }
}
