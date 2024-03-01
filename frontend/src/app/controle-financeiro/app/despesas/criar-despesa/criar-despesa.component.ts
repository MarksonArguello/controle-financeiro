import { Component, OnInit } from '@angular/core';
import { Categoria } from 'src/app/controle-financeiro/models/categoria';
import { DespesaService } from 'src/app/controle-financeiro/services/despesa.service';

import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { Utils } from 'src/app/controle-financeiro/utils/Utils';

@Component({
  selector: 'app-criar-despesa',
  templateUrl: './criar-despesa.component.html',
  styleUrls: ['./criar-despesa.component.css'],
})
export class CriarDespesaComponent implements OnInit {
  formulario!: FormGroup;

  categorias: Categoria[] = [];

  constructor(
    private service: DespesaService,
    private formBuilder: FormBuilder,
    private router: Router
  ) {}

  ngOnInit() {
    this.formulario = this.formBuilder.group({
      descricao: [''],
      valor: [''],
      data: [''],
      categoria: ['OUTRAS'],
    });

    this.formulario
      .get('valor')
      ?.valueChanges.subscribe(valorDigitado =>
        Utils.mantemValorFormatado(valorDigitado, this.formulario)
      );

    this.listarCategorias();
  }

  listarCategorias() {
    this.service.getCategorias().subscribe(categorias => {
      this.categorias = categorias;
    });
  }

  criarDespesa() {
    this.service.create(this.formulario.value).subscribe(despesa => {
      this.redirecionarParaListagem();
    });
  }

  private redirecionarParaListagem() {
    this.router.navigate(['/controle-financeiro']);
  }
}
