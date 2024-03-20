import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
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
  }

  logar() {
    if (this.formulario.invalid) {
      return;
    }

    this.service
      .logar(this.f().login, this.f().senha)
      .pipe(first())
      .subscribe(
        data => {
          this.service.setUserName(this.f().login);
          this.router.navigate([this.returnUrl]);
        },
        error => {
          this.error = error;
          console.log(error);
        }
      );
  }

  f() {
    return this.formulario.value;
  }

  ngOnInit(): void {
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '';
  }
}
