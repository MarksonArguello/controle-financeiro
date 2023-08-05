import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BrowserModule } from '@angular/platform-browser';

import { ControleFinanceiroRoutingModule } from './controle-financeiro-routing.module';
import { AppComponent } from './app/app.component';
import { ListarDespesaComponent } from './app/depesas/listar-despesa/listar-despesa.component';




@NgModule({
  declarations: [
    AppComponent,
    ListarDespesaComponent
  ],
  imports: [
    BrowserModule,
    CommonModule,
    ControleFinanceiroRoutingModule
  ]
})
export class ControleFinanceiroModule { }
