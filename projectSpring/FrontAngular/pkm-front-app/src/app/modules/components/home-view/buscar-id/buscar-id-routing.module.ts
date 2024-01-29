import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BuscarIdComponent } from './buscar-id.component';

const routes: Routes = [{ path: '', component: BuscarIdComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BuscarIdRoutingModule { }