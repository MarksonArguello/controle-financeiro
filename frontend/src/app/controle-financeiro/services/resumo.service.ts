import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Resumo } from '../models/resumos/resumo';
import { ChartConfiguration } from 'chart.js';

@Injectable({
  providedIn: 'root',
})
export class ResumoService {
  private API = 'http://localhost:8080/resumo';

  constructor(private http: HttpClient) {}

  get(ano: number, mes: number): Observable<Resumo> {
    mes = mes + 1;
    return this.http.get<Resumo>(`${this.API}/${ano}/${mes}`);
  }

  getChartConfig(
    title: string,
    labels: string[],
    data: number[]
  ): ChartConfiguration {
    return {
      type: 'bar',
      data: {
        labels,
        datasets: [
          {
            label: 'Gasto',
            data,
            borderWidth: 1,
          },
        ],
      },
      options: {
        backgroundColor: '#f44336',
        plugins: {
          title: {
            display: true,
            text: title,
            font: { size: 15 },
          },

          legend: { labels: { font: { size: 15 } } },
        },
        scales: {
          y: {
            beginAtZero: true,
            ticks: { font: { size: 15, weight: 'bold' } },
          },
          x: {
            ticks: { font: { size: 15, weight: 'bold' } },
          },
        },
      },
    };
  }
}
