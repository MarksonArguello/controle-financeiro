import { Component, OnInit } from '@angular/core';
import { Categoria } from 'src/app/controle-financeiro/models/categoria';
import { DespesaService } from 'src/app/controle-financeiro/services/despesa.service';

@Component({
  selector: 'app-criar-despesa',
  templateUrl: './criar-despesa.component.html',
  styleUrls: ['./criar-despesa.component.css']
})
export class CriarDespesaComponent implements OnInit {
  categorias: Categoria[] = [];

  constructor(private service: DespesaService) { }

  ngOnInit() {
    this.listarCategorias();
  }

  listarCategorias() {
    this.service.getCategorias().subscribe(
      categorias => {
        this.categorias = categorias;
      }
    );
  }
}
