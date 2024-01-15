import { Injectable } from '@angular/core';
import { EMPLEADOS } from './empleados.json';
import { Empleado } from './empleado';

@Injectable({
  providedIn: 'root'
})
export class EmpleadoService {

  constructor() { }

  getEmpleado(): Empleado[]{
    return EMPLEADOS; 
  }

}
