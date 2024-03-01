import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { Despesa } from '../models/despesa';
import { DespesaPage } from '../models/despesaPage';
import { Categoria } from '../models/categoria';

@Injectable({
  providedIn: 'root',
})
export class DespesaService {
  private API = 'http://localhost:8080/despesas';

  constructor(private http: HttpClient) {}

  get(): Observable<DespesaPage> {
    return this.http.get<DespesaPage>(this.API);
  }

  getCategorias(): Observable<Categoria[]> {
    return this.http.get<Categoria[]>(`${this.API}/categorias`);
  }

  delete(despesa: Despesa): Observable<any> {
    return this.http.delete(`${this.API}/${despesa.id}`);
  }

  create(despesa: Despesa): Observable<Despesa> {
    return this.http.post<Despesa>(this.API, despesa);
  }

  update(despesa: Despesa): Observable<Despesa> {
    return this.http.put<Despesa>(`${this.API}/${despesa.id}`, despesa);
  }

  getDespesaById(id: string) {
    return this.http.get<Despesa>(`${this.API}/${id}`);
  }
}
