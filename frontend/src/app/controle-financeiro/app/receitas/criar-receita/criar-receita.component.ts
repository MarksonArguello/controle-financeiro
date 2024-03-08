import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { receitaService } from 'src/app/controle-financeiro/services/receita.service';
import { Utils } from 'src/app/controle-financeiro/utils/Utils';

@Component({
  selector: 'app-criar-receita',
  templateUrl: './criar-receita.component.html',
  styleUrls: ['./criar-receita.component.css'],
})
export class CriarReceitaComponent implements OnInit {
  formulario!: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private service: receitaService,
    private router: Router
  ) {
    this.formulario = formBuilder.group({
      descricao: [''],
      valor: [''],
      data: [''],
    });

    this.formulario.get('valor')?.valueChanges.subscribe(valorDigitado => {
      Utils.mantemValorFormatado(valorDigitado, this.formulario);
    });
  }

  criarReceita() {
    this.service.create(this.formulario.value).subscribe(receita => {
      this.redirecionarParaListagem();
    });
  }

  private redirecionarParaListagem() {
    this.router.navigate(['/controle-financeiro']);
  }

  ngOnInit(): void {}
}
