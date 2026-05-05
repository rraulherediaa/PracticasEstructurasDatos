export class ArregloServicios {
  private tiposServicio: string[];
  private prioridades: number[];
  private cantidad: number;

  constructor(capacidad: number) {
    this.tiposServicio = new Array(capacidad);
    this.prioridades = new Array(capacidad);
    this.cantidad = 0;
  }

  public agregarServicio(tipo: string, prioridad: number): void {
    if (this.cantidad < this.tiposServicio.length) {
      this.tiposServicio[this.cantidad] = tipo;
      this.prioridades[this.cantidad] = prioridad;
      this.cantidad++;
    }
  }

  public obtenerPrioridad(tipo: string): number {
    for (let i = 0; i < this.cantidad; i++) {
      if (this.tiposServicio[i] === tipo) {
        return this.prioridades[i];
      }
    }
    return 99; // Prioridad por defecto (muy baja) si no se encuentra
  }

  public listarServicios(): string[] {
    return this.tiposServicio.slice(0, this.cantidad);
  }
}
