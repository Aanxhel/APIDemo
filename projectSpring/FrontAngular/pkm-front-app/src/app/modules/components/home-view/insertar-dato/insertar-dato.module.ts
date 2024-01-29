import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { InsertarDatoComponent } from './insertar-dato.component';
import { InsertarDatoRoutingModule } from './insertar-dato-routing.module';



@NgModule({
  declarations: [
    InsertarDatoComponent
  ],
  imports: [
    CommonModule,
    InsertarDatoRoutingModule
  ]
})
export class InsertarDatoModule { }
