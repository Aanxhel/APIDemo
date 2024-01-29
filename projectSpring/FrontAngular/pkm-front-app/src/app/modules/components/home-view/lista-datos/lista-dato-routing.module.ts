import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListaDatosComponent } from './lista-datos.component';

const routes: Routes = [{ path: '', component: ListaDatosComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ListaDatosRoutingModule { }