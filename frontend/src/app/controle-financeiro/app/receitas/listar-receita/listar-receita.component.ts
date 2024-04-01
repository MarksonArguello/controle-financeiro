import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Receita } from 'src/app/controle-financeiro/models/receitas/receita';
import { receitaService } from 'src/app/controle-financeiro/services/receita.service';
import { Utils } from 'src/app/controle-financeiro/utils/Utils';
import { ResumoComponent } from '../../resumo/resumo.component';

@Component({
  selector: 'app-listar-receita',
  templateUrl: './listar-receita.component.html',
  styleUrls: ['./listar-receita.component.css'],
})
export class ListarReceitaComponent implements OnInit {
  @Output() listaReceitaUpdate = new EventEmitter();

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
        receita.data = new Date(receita.data + ' ');
      });
    });
  }

  excluir(receita: Receita) {
    this.service.delete(receita).subscribe(() => {
      this.receitas.splice(this.receitas.indexOf(receita), 1);
      this.showModalDeletar = false;
      this.listarReceitas();
      this.listaReceitaUpdate.emit();
    });
  }

  abrirModalDeletar(receita: Receita) {
    this.receitaSelecionada = receita;
    this.showModalDeletar = true;
  }
}
