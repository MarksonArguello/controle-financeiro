import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../models/User';
import { BehaviorSubject, Observable, map } from 'rxjs';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root',
})
export class AuthenticationService {
  private API = 'http://localhost:8080/login';

  constructor(
    private http: HttpClient,
    private router: Router
  ) {}

  public logar(login: string, senha: string) {
    return this.http.post<User>(this.API, { login, senha }).pipe(
      map(token => {
        localStorage.setItem('token', JSON.stringify(token));
        this.setUserName(login);
        return token;
      })
    );
  }

  public getToken(): string {
    return localStorage.getItem('token') as string;
  }

  public logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('username');

    this.router.navigate(['/login']);
  }

  public setUserName(username: string) {
    localStorage.setItem('username', JSON.stringify(username));
  }

  public getUserName(): string {
    return localStorage.getItem('username') as string;
  }

  public isLoggedIn(): boolean {
    return this.getToken() !== null;
  }
}
