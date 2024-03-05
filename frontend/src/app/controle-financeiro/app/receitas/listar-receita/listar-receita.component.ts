import { Component, OnInit } from '@angular/core';
import { Receita } from 'src/app/controle-financeiro/models/receitas/receita';
import { receitaService } from 'src/app/controle-financeiro/services/receita.service';
import { Utils } from 'src/app/controle-financeiro/utils/Utils';

@Component({
  selector: 'app-listar-receita',
  templateUrl: './listar-receita.component.html',
  styleUrls: ['./listar-receita.component.css'],
})
export class ListarReceitaComponent implements OnInit {
  receitas: Receita[] = [];
  utils: Utils;

  showModalDeletar = false;
  receitaSelecionada: Receita = {} as Receita;

  constructor(private service: receitaService) {
    this.utils = new Utils();
  }

  ngOnInit(): void {
    this.listarReceitas();
  }

  private listarReceitas() {
    this.service.get().subscribe(receitaPage => {
      this.receitas = receitaPage.content.sort((a, b) => a.id - b.id);
      this.receitas.forEach(receita => {
        receita.data = new Date(receita.data);
      });
    });
  }

  excluir(receita: Receita) {
    this.service.delete(receita).subscribe(() => {
      this.receitas.splice(this.receitas.indexOf(receita), 1);
      this.showModalDeletar = false;
      this.listarReceitas();
    });
  }

  abrirModalDeletar(receita: Receita) {
    this.receitaSelecionada = receita;
    this.showModalDeletar = true;
  }
}
