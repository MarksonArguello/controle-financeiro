import { Component, OnInit } from '@angular/core';
import { Despesa } from 'src/app/controle-financeiro/models/despesa';
import { DespesaService } from 'src/app/controle-financeiro/services/despesa.service';
import { Utils } from 'src/app/controle-financeiro/utils/Utils';

@Component({
  selector: 'app-listar-despesa',
  templateUrl: './listar-despesa.component.html',
  styleUrls: ['./listar-despesa.component.css']
})
export class ListarDespesaComponent implements OnInit {
  despesas: Despesa[] = [];
  showModalDeletar = false;
  despesaSelecionada: Despesa = {} as Despesa;
  utils = new Utils();


  constructor(
    private service: DespesaService,
  ) { }

  ngOnInit(): void {
    this.listarDespesas();
  }

  listarDespesas() {
    this.service.get().subscribe(
      despesaPage => {
        this.despesas = despesaPage.content.sort((a, b) => a.id - b.id);
        this.despesas.forEach(despesa => {
          despesa.data = new Date(despesa.data);
        });
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
