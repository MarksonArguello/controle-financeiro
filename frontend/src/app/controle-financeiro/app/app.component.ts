import { Component, OnInit } from '@angular/core';
import { Despesa } from '../models/despesa';
import { DespesaService } from '../services/despesa.service';


@Component({
  selector: 'app-app',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  despesas: Despesa[] = [];

  constructor(
    private service: DespesaService
  ) { }

  ngOnInit(): void {
    this.listarDespesas();
  }

  listarDespesas() {
    this.service.getDespesas().subscribe(
      dados => this.despesas = dados['content']
    );
  }

}
