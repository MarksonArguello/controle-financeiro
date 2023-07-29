import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { ControleFinanceiroModule } from './controle-financeiro/controle-financeiro.module';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ControleFinanceiroModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
