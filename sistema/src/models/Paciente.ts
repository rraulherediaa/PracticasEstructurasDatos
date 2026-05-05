export class Paciente {
  private nombre: string;
  private apellidos: string;
  private edad: number;
  private nroDoc: string;
  private telefono: string;

  constructor(nombre: string, apellidos: string, edad: number, nroDoc: string, telefono: string) {
    this.nombre = nombre;
    this.apellidos = apellidos;
    this.edad = edad;
    this.nroDoc = nroDoc;
    this.telefono = telefono;
  }

  public getNombre(): string { return this.nombre; }
  public getApellidos(): string { return this.apellidos; }
  public getEdad(): number { return this.edad; }
  public getNroDoc(): string { return this.nroDoc; }
  public getTelefono(): string { return this.telefono; }
}
