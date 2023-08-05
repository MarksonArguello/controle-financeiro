import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListarDespesaComponent } from './app/despesas/listar-despesa/listar-despesa.component';
import { CriarDespesaComponent } from './app/despesas/criar-despesa/criar-despesa.component';

const routes: Routes = [
  { path: 'controle-financeiro', children: [
    { path: 'criarDespesa', component: CriarDespesaComponent },
    { path: '', component: ListarDespesaComponent }
  ]}

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ControleFinanceiroRoutingModule { }
