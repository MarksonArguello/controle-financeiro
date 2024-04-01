import { Component, OnInit } from '@angular/core';
import Chart from 'chart.js/auto';
import { ResumoService } from '../../services/resumo.service';
import { Resumo } from '../../models/resumos/resumo';

@Component({
  selector: 'app-resumo',
  templateUrl: './resumo.component.html',
  styleUrls: ['./resumo.component.css'],
})
export class ResumoComponent implements OnInit {
  chart: any = [];
  data: number[] = [];
  labels: string[] = [];
  resumo: Resumo = {} as Resumo;

  constructor(private service: ResumoService) {
    this.updateData();
  }

  public updateData() {
    this.service
      .get(new Date().getFullYear(), new Date().getMonth())
      .subscribe(data => {
        this.resumo = data;
        this.data = data.gastoPorCategoria.map(
          gastoPorCategoria => gastoPorCategoria.valor
        );

        this.labels = data.gastoPorCategoria.map(
          gastoPorCategoria => gastoPorCategoria.categoria
        );

        this.chart = new Chart(
          'canvas',
          this.service.getChartConfig(
            'Gasto por categoria',
            this.labels,
            this.data
          )
        );
      });
  }

  ngOnInit(): void {}
}
