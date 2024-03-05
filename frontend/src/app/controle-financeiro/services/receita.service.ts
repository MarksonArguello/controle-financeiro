import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ReceitaPage } from '../models/receitas/receitaPage';
import { Receita } from '../models/receitas/receita';

@Injectable({
  providedIn: 'root',
})
export class receitaService {
  private API = 'http://localhost:8080/receitas';

  constructor(private http: HttpClient) {}

  get(): Observable<ReceitaPage> {
    return this.http.get<ReceitaPage>(this.API);
  }

  delete(receita: Receita): Observable<any> {
    return this.http.delete(`${this.API}/${receita.id}`);
  }

  create(receita: Receita): Observable<Receita> {
    return this.http.post<Receita>(this.API, receita);
  }

  update(receita: Receita): Observable<Receita> {
    return this.http.put<Receita>(`${this.API}/${receita.id}`, receita);
  }

  getreceitaById(id: string): Observable<Receita> {
    return this.http.get<Receita>(`${this.API}/${id}`);
  }
}
