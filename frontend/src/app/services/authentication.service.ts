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
  private currentUserSubject: BehaviorSubject<User>;
  public currentUser: Observable<User>;

  constructor(
    private http: HttpClient,
    private router: Router
  ) {
    this.currentUserSubject = new BehaviorSubject<User>(
      localStorage.getItem('currentUser')
        ? JSON.parse(localStorage.getItem('currentUser') || '')
        : null
    );

    this.currentUser = this.currentUserSubject.asObservable();
  }

  public get currentUserValue(): User {
    return this.currentUserSubject.value;
  }

  logar(login: string, senha: string) {
    return this.http.post<User>(this.API, { login, senha }).pipe(
      map(user => {
        localStorage.setItem('token', JSON.stringify(user));
        this.currentUserSubject.next(user);
        return user;
      })
    );
  }

  getToken() {
    return localStorage.getItem('token');
  }

  logout() {
    localStorage.removeItem('currentUser');
    localStorage.removeItem('token');

    this.currentUserSubject.next({} as User);
    this.router.navigate(['/login']);
  }

  setUserName(username: string) {
    localStorage.setItem('username', JSON.stringify(username));
  }

  getUserName() {
    return JSON.parse(localStorage.getItem('username') as string);
  }
}
