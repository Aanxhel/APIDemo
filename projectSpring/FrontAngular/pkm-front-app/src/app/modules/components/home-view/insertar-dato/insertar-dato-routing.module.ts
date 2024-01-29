import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { InsertarDatoComponent } from './insertar-dato.component';

const routes: Routes = [{ path: '', component: InsertarDatoComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class InsertarDatoRoutingModule { }