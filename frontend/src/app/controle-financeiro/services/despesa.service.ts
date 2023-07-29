import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { Despesa } from '../models/despesa';

@Injectable({
  providedIn: 'root'
})
export class DespesaService {

  private API = "http://localhost:8080/despesas";

  constructor(
    private http: HttpClient
  ) { }

  getDespesas(): Observable<any> {
    return this.http.get<any>(this.API);
  }

}
