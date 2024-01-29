import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeViewComponent } from './home-view.component';
import { HomeViewRoutingModule } from './home-view-routing.module';
import { ListaDatosComponent } from './lista-datos/lista-datos.component';
import { BuscarIdComponent } from './buscar-id/buscar-id.component';
import { EditarDatoComponent } from './editar-dato/editar-dato.component';
import { EliminarDatoComponent } from './eliminar-dato/eliminar-dato.component';



@NgModule({
  declarations: [
    HomeViewComponent,
    ListaDatosComponent,
    BuscarIdComponent,
    EditarDatoComponent,
    EliminarDatoComponent
  ],
  imports: [
    CommonModule,
    HomeViewRoutingModule
  ]
})
export class HomeViewModule { }
