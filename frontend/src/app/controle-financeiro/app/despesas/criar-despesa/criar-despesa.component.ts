import { Component, OnInit } from '@angular/core';
import { Categoria } from 'src/app/controle-financeiro/models/categoria';
import { DespesaService } from 'src/app/controle-financeiro/services/despesa.service';

import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';

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

    this.formulario.get('valor')?.valueChanges.subscribe(valorDigitado => {
      if (!valorDigitado) return;

      const MAX_INT_LEN = 5;
      const MAX_DECIMAL_LEN = 2;
      if (valorDigitado.toString().split('.')[0].length > MAX_INT_LEN) {
        this.formulario
          .get('valor')
          ?.setValue(valorDigitado.toString().slice(0, MAX_INT_LEN));
      }

      // Check if the value has more than 2 decimal places, if so, round it to 2 decimal places
      if (
        valorDigitado &&
        valorDigitado.toString().split('.')[1]?.length > MAX_DECIMAL_LEN
      ) {
        const valorFormatado =
          parseFloat(valorDigitado).toFixed(MAX_DECIMAL_LEN);
        this.formulario.get('valor')?.setValue(valorFormatado);
      }
    });

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
