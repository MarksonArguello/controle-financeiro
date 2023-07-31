import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { Despesa } from '../models/despesa';
import { DespesaPage } from '../models/despesaPage';

@Injectable({
  providedIn: 'root'
})
export class DespesaService {

  private API = "http://localhost:8080/despesas";

  constructor(
    private http: HttpClient
  ) { }

  get(): Observable<DespesaPage> {
    return this.http.get<DespesaPage>(this.API);
  }

  delete(despesa: Despesa): Observable<any> {
    return this.http.delete(`${this.API}/${despesa.id}`);
  }

}
