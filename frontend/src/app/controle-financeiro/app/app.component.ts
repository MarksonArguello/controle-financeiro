import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Despesa } from '../models/despesa';
import { DespesaService } from '../services/despesa.service';


@Component({
  selector: 'app-app',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  despesas: Despesa[] = [];
  showModalDeletar = false;
  despesaSelecionada: Despesa = {
    id: 0,
    descricao: '',
    valor: 0,
    data: '',
    categoria: '',
  };

  constructor(
    private service: DespesaService
  ) { }

  ngOnInit(): void {
    this.listarDespesas();
  }

  listarDespesas() {
    this.service.get().subscribe(
      despesaPage => {
        this.despesas = despesaPage.content;
      }
    );
  }

  excluir(despesa: Despesa) {
    this.service.delete(despesa).subscribe(
      () => {
        this.despesas.splice(this.despesas.indexOf(despesa), 1);
      }
    );
  }

  abrirModalDeletar(despesa: Despesa) {
    this.showModalDeletar = true;
    this.despesaSelecionada = despesa;
  }
}
