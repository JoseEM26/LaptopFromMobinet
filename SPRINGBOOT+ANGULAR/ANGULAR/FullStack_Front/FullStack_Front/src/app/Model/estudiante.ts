export class Estudiante {
  constructor(
    public nombre: string = '',
    public apellido: string = '',
    public telefono: number = 0,
    public correo: string = '',
    public idEstudiante?: number 
  ) {}
}
