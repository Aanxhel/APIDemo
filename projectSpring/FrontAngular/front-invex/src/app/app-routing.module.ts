import { NgModule } from '@angular/core';
//import { CommonModule  } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';


const routes: Routes = [


  { path: 'listaEmpleado', loadChildren: () => import('./modules/components/home-view/empleado/empleado-routing.module').then(m => m.EmpleadoRoutingModule) },

  //se coloca siempre al finar para encontrar el path home
  { path: '**', pathMatch: 'full', redirectTo: 'listaEmpleado' },
  { path: '', pathMatch: 'full', redirectTo: 'listaEmpleado' },  

];


@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true })],
  exports: [RouterModule],
})
export class AppRoutingModule { }