import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  { path: 'home', children: [] },
  { path: 'controle-financeiro', loadChildren: () => import('./controle-financeiro/controle-financeiro.module').then(m => m.ControleFinanceiroModule) },
  { path: '', redirectTo: 'home', pathMatch: 'full'}
]; // sets up routes constant where you define your routes

// configures NgModule imports and exports
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
