import { Component, OnInit } from '@angular/core';
import { Empleado } from './empleado';
import { EMPLEADOS } from './empleados.json';
import { EmpleadoService } from './empleado.service';

@Component({
  selector: 'app-empleado',
  templateUrl: './empleado.component.html'
})
export class EmpleadoComponent implements OnInit {
  
  empleados: Empleado[]; 

  constructor(private empleadoService: EmpleadoService) { 
  }

  ngOnInit(): void {
    this.empleados = this.empleadoService.getEmpleado();
  }

}
