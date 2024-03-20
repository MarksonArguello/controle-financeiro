import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthenticationService } from '../services/authentication.service';
import { first } from 'rxjs';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  formulario!: FormGroup;
  returnUrl!: string;
  error = '';
  submitted = false;

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private service: AuthenticationService
  ) {
    this.formulario = this.formBuilder.group({
      login: ['', Validators.required],
      senha: ['', Validators.required],
    });

    this.formulario.valueChanges.subscribe(() => (this.error = ''));
  }

  logar() {
    this.submitted = true;

    if (this.formulario.invalid) {
      return;
    }

    this.service
      .logar(this.formulario.value.login, this.formulario.value.senha)
      .pipe(first())
      .subscribe(
        data => {
          this.service.setUserName(this.formulario.value.login);
          this.router.navigate([this.returnUrl]);
        },
        error => {
          if (error.status == 403) {
            this.error = 'Usuário ou senha inválidos';
          }
        }
      );
  }

  ngOnInit(): void {
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '';
  }

  get login() {
    return this.formulario.get('login') as FormControl;
  }

  get senha() {
    return this.formulario.get('senha') as FormControl;
  }
}
