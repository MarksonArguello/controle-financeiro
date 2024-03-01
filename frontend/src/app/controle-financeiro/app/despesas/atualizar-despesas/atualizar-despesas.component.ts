import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Categoria } from 'src/app/controle-financeiro/models/categoria';
import { DespesaService } from 'src/app/controle-financeiro/services/despesa.service';
import { Utils } from 'src/app/controle-financeiro/utils/Utils';

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
        ?.valueChanges.subscribe(valorDigitado =>
          Utils.mantemValorFormatado(valorDigitado, this.formulario)
        );
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
}
