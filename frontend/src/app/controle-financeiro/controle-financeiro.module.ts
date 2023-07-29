import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ControleFinanceiroRoutingModule } from './controle-financeiro-routing.module';
import { AppComponent } from './app/app.component';


@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    CommonModule,
    ControleFinanceiroRoutingModule
  ]
})
export class ControleFinanceiroModule { }
