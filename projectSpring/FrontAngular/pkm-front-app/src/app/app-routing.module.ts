import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
    
  { path: 'homeview',loadChildren: () => import('./modules/components/home-view/home-view.module').then( (m) => m.HomeViewModule)},

  //se coloca siempre al finalr para encontrar el path home
  { path: '**', pathMatch: 'full', redirectTo: 'homeview' },
  { path: '', pathMatch: 'full', redirectTo: 'homeview' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true })],
  exports: [RouterModule],
})

export class AppRoutingModule {}
