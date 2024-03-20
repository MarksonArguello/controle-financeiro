import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { AuthGuard } from './services/guard/auth.guard';

const routes: Routes = [
  { path: 'home', children: [] },
  { path: 'login', component: LoginComponent },
  {
    path: 'controle-financeiro',
    loadChildren: () =>
      import('./controle-financeiro/controle-financeiro.module').then(
        m => m.ControleFinanceiroModule
      ),
    canActivate: [AuthGuard],
  },
  { path: '', redirectTo: 'home', pathMatch: 'full' },
]; // sets up routes constant where you define your routes

// configures NgModule imports and exports
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
