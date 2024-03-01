import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Categoria } from 'src/app/controle-financeiro/models/categoria';
import { DespesaService } from 'src/app/controle-financeiro/services/despesa.service';

@Component({
  selector: 'app-atualizar-despesas',
  templateUrl: './atualizar-despesas.component.html',
  styleUrls: ['./atualizar-despesas.component.css'],
})
export class AtualizarDespesasComponent implements OnInit {
  formulario!: FormGroup;

  categorias: Categoria[] = [];

  constructor(
    private service: DespesaService,
    private formBuilder: FormBuilder,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');

    this.listarCategorias();

    this.service.getDespesaById(id!).subscribe(despesa => {
      this.formulario = this.formBuilder.group({
        id: [despesa.id],
        descricao: [despesa.descricao],
        valor: [despesa.valor],
        data: [despesa.data],
        categoria: [despesa.categoria],
      });
      this.formulario
        .get('valor')
        ?.valueChanges.subscribe(this.mantemValorFormatado);
    });
  }

  listarCategorias() {
    this.service.getCategorias().subscribe(categorias => {
      this.categorias = categorias;
    });
  }

  atualizarDespesa() {
    this.service.update(this.formulario.value).subscribe(despesa => {
      this.redirecionarParaListagem();
    });
  }

  private redirecionarParaListagem() {
    this.router.navigate(['/controle-financeiro']);
  }

  mantemValorFormatado(valorDigitado: string) {
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
      const valorFormatado = parseFloat(valorDigitado).toFixed(MAX_DECIMAL_LEN);
      this.formulario.get('valor')?.setValue(valorFormatado);
    }
  }
}
