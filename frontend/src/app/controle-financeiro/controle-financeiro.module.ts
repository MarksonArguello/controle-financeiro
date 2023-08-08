import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ControleFinanceiroRoutingModule } from './controle-financeiro-routing.module';
import { AppComponent } from './app/app.component';
import { ListarDespesaComponent } from './app/despesas/listar-despesa/listar-despesa.component';
import { DespesasComponent } from './app/despesas/despesas.component';




@NgModule({
  declarations: [
    AppComponent,
    ListarDespesaComponent,
    DespesasComponent
  ],
  imports: [
    CommonModule,
    ControleFinanceiroRoutingModule
  ]
})
export class ControleFinanceiroModule { }
