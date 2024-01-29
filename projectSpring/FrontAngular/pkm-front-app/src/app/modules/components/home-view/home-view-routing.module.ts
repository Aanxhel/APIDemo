import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeViewComponent } from './home-view.component';

const routes: Routes = [{
  path: '', component: HomeViewComponent,

  children: [

    { path: 'inicio', loadChildren: () => import('./inicio/inicio-routing.module').then(m => m.InicioRouting) },
    { path: 'insertar-dato', loadChildren: () => import('./insertar-dato/insertar-dato-routing.module').then(m => m.InsertarDatoRoutingModule) },
    { path: 'lista-datos', loadChildren: () => import ('./lista-datos/lista-dato-routing.module').then(m => m.ListaDatosRoutingModule)  },
    { path: 'eliminar-dato', loadChildren: () => import ('./eliminar-dato/eliminar-dato-routing.module').then(m => m.EliminarDatoRoutingModule)  },
    { path: 'buscar-id', loadChildren: () => import ('./buscar-id/buscar-id-routing.module').then(m => m.BuscarIdRoutingModule)  },
    { path: 'editar-dato', loadChildren: () => import ('./editar-dato/editar-dato-routing.module').then(m => m.EditarDatoRoutingModule)  },
   
    
    { path: '**', pathMatch: 'full', redirectTo: 'inicio' },

  ],
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HomeViewRoutingModule { }