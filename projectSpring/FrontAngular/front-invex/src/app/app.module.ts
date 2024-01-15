import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { EmpleadoComponent } from './empleado/empleado.component';
import { EmpleadoService } from "./empleado/empleado.service";

@NgModule({
  declarations: [
    AppComponent,
    EmpleadoComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [
    EmpleadoService,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
