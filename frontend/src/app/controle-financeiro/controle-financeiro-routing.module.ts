import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CriarDespesaComponent } from './app/despesas/criar-despesa/criar-despesa.component';
import { AtualizarDespesasComponent } from './app/despesas/atualizar-despesas/atualizar-despesas.component';
import { AppComponent } from './app/app.component';
import { CriarReceitaComponent } from './app/receitas/criar-receita/criar-receita.component';

const routes: Routes = [
  { path: 'criarDespesa', component: CriarDespesaComponent },
  { path: 'alterarDespesa/:id', component: AtualizarDespesasComponent },
  { path: 'criarReceita', component: CriarReceitaComponent },
  { path: '', component: AppComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ControleFinanceiroRoutingModule {}
